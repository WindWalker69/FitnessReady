package Gestori;

import Dominio.*;
import Eccezioni.*;
import java.util.*;

public class GestoreAcquisto {
    
    //----VARIABILI----
    
    private Carrello carrello;
    //private Catalogo catalogo;
    
    //----COSTRUTTORI----
    
    public GestoreAcquisto(){
        carrello = new Carrello();
        //catalogo = new Catalogo();
    }
    
    //costruttore con StartUp
    public GestoreAcquisto(StartUp SU){
        carrello = new Carrello();
        //catalogo = new Catalogo(SU); //catalago inizializzato tramite la classe StartUp
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
                    RigaCarrello riga = new RigaCarrello(prod, quantity, prod.getPrezzo());
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
    
    public Acquisto effettuaAcquisto() throws GenericSystemException {
        Acquisto newAcquisto = null;
        List<RigaAcquisto> righeAcquisto = new ArrayList<>();
        RigaAcquisto rigaAcquisto;
        Prodotto prodCarrello;
        int quantità;
        
        if(!carrello.getRigheCarrello().isEmpty()){
            //newAcquisto = new Acquisto(new Date(), carrello.getTotaleCarrello());
            for(RigaCarrello rigaCarrello : carrello.getRigheCarrello()){
                prodCarrello = rigaCarrello.getProdotto();
                quantità = rigaCarrello.getQuantità();
                rigaAcquisto = new RigaAcquisto(prodCarrello, quantità);
                righeAcquisto.add(rigaAcquisto);
                //newAcquisto.getRigheAcquisto().add(rigaAcquisto);
            }
            newAcquisto = new Acquisto(new Date(), carrello.getTotaleCarrello(), righeAcquisto);
        }
        else
            throw new GenericSystemException();
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
                        return;
                    }
                    else
                        throw new GenericSystemException();
                else
                    throw new NotPositiveNumberException();
        }
    }
}
