package Gestori;

import Dominio.*;
import Eccezioni.*;
import java.util.*;

public class GestoreAcquisto {
    
    //----VARIABILI----
    
    private Carrello carrello;
    
    //----COSTRUTTORI----
    
    public GestoreAcquisto(){
        carrello = new Carrello();
    }
    
    //----GETTERS e SETTERS----
    
    public void setCarrello(Carrello carrello){ this.carrello = carrello; }
    public Carrello getCarrello(){ return carrello; }
    
    //----METODI DI PROGETTO----
    
    public boolean aggiungiAlCarrello(Prodotto prod, int quantity) throws Exception, GenericSystemException, NotPositiveNumberException {
        if(prod.getDisp() > 0)
            if(quantity > 0)
                if(quantity <= prod.getDisp()){
                    for(int i = 0; i < carrello.getRigheCarrello().size(); i++){
                        if(prod == carrello.getRigheCarrello().get(i).getProdotto())
                            return false;
                    }
                    RigaCarrello riga = new RigaCarrello(prod, quantity, prod.getPrezzo(), prod.getPrezzoGettoni());
                    carrello.getRigheCarrello().add(riga);
                    return true;
                }
                else
                    throw new Exception();
            else
                throw new NotPositiveNumberException();
        else
            throw new GenericSystemException();
    }
    
    public Map<String, List<Acquisto>> aggiungiAcquisto(Acquisto newAcquisto, String emailClienteAttuale, Map<String, List<Acquisto>> mappaAcquistiClienti){
        if(mappaAcquistiClienti.containsKey(emailClienteAttuale))
            mappaAcquistiClienti.get(emailClienteAttuale).add(newAcquisto);
        else{
            List<Acquisto> acquistiCliente = new ArrayList<>();
            acquistiCliente.add(newAcquisto);
            mappaAcquistiClienti.put(emailClienteAttuale, acquistiCliente);
        }
        
        return mappaAcquistiClienti;
    }
    
    public Acquisto effettuaAcquisto(String metodoPagamento, List<Promozione> listaPromozioni){
        String cTarget, cProdotto, statoPromo;
        float subTotale, percSconto, totProdScont = 0, totProdNonScont = 0;
        int quantità;
        
        Acquisto newAcquisto = null;
        List<RigaAcquisto> righeAcquisto = new ArrayList<>();
        RigaAcquisto rigaAcquisto;
        Prodotto prodCarrello;
        
        for(RigaCarrello rigaCarrello : carrello.getRigheCarrello()){
            prodCarrello = rigaCarrello.getProdotto();
            quantità = rigaCarrello.getQuantità();
            rigaAcquisto = new RigaAcquisto(prodCarrello, quantità);
            righeAcquisto.add(rigaAcquisto);
        }
        switch(metodoPagamento){
            case "Carta di credito":
                if(!listaPromozioni.isEmpty())
                    for(RigaCarrello rigaCarrello : carrello.getRigheCarrello()){
                        prodCarrello = rigaCarrello.getProdotto();
                        subTotale = rigaCarrello.getSubTotale();
                        for(Promozione promozione : listaPromozioni){
                            cTarget = promozione.getCTarget();
                            statoPromo = promozione.getStato();
                            cProdotto = prodCarrello.getCategoria();
                            if(cTarget.equals(cProdotto) && statoPromo.equals("ATTIVA")){
                                percSconto = promozione.getPercSconto();
                                totProdScont += subTotale - ((subTotale * percSconto) / 100);
                            }
                            else
                                totProdNonScont += subTotale;
                        }
                    }
                if(totProdScont != 0)
                    newAcquisto = new Acquisto(new Date(), totProdScont + totProdNonScont, 0, righeAcquisto);
                else
                    newAcquisto = new Acquisto(new Date(), carrello.getTotaleCarrello(), 0, righeAcquisto);
                break;
            
            case "Gettoni":
                newAcquisto = new Acquisto(new Date(), 0, carrello.getTotaleGettoni(), righeAcquisto);
                break;
        }
        return newAcquisto;
    }
    
    public void rimuoviProdotto(int idProd){
        Iterator<RigaCarrello> it = carrello.getRigheCarrello().iterator();
        Prodotto prodCarrello;
        
        while(it.hasNext()){
            prodCarrello = it.next().getProdotto();
            if(prodCarrello.getId() == idProd){
                it.remove();
                return;
            }
        }
    }
    
    public void modificaQuantità(int idProd, int newQuantity, Catalogo catalogo) throws NotPositiveNumberException, GenericSystemException {
        Iterator<RigaCarrello> it = carrello.getRigheCarrello().iterator();
        RigaCarrello rigaCarrello;
        Prodotto prodCarrello;
        
        while(it.hasNext()){
            rigaCarrello = it.next();
            prodCarrello = rigaCarrello.getProdotto();
            if(prodCarrello.getId() == idProd)
                if(newQuantity > 0)
                    if(newQuantity <= catalogo.getProdotto(idProd).getDisp()){
                        rigaCarrello.setQuantità(newQuantity);
                        rigaCarrello.setSubTotale(newQuantity, prodCarrello.getPrezzo());
                        rigaCarrello.setSubTotaleGettoni(newQuantity, prodCarrello.getPrezzoGettoni());
                        return;
                    }
                    else
                        throw new GenericSystemException();
                else
                    throw new NotPositiveNumberException();
        }
    }
}
