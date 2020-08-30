package Gestori;

import Dominio.*;
import Eccezioni.*;
import java.util.*;

public class GestoreProdotti {
    
    //----VARIABILI----
    
    private static GestoreProdotti istance = null;
    private StartUp SU;
    private Catalogo catalogo;
    
    //----COSTRUTTORI----
    
    public GestoreProdotti(){
        SU = new StartUp();
        catalogo = new Catalogo(SU); //catalogo inizializzato con StartUp
    }
    
    //----METODI GENERICI----
    
    public static GestoreProdotti getInstance(){
        if(istance == null)
            istance = new GestoreProdotti();
        return istance;
    }
    
    //----GETTERS e SETTERS----
    
    public void setCatalogo(Catalogo catalogo){ this.catalogo = catalogo; }
    public Catalogo getCatalogo(){ return catalogo; }
    
    //----METODI DI PROGETTO----
    
    //Aggiornamento catalogo in caso di nuovo acquisto
    public void aggiornaCatalogo(Acquisto newAcquisto){
        int idProd, dispProd, quantità; //variabili di supporto
        
        for(RigaAcquisto riga : newAcquisto.getRigheAcquisto()){
            quantità = riga.getQuantità();
            idProd = riga.getProdotto().getId();
            dispProd = catalogo.getProdotto(idProd).getDisp();
            catalogo.getProdotto(idProd).setDisp(dispProd - quantità);
        }
    }
    
    public void aggiungiProdotto(String nome, String categoria, float prezzo, int prezzoGettoni, int disp) throws NotPositiveNumberException, GenericSystemException {
        List<Prodotto> listaProdotti = catalogo.getListaProdotti();
        Prodotto newProdotto;
        
        if(prezzo >= 0 && prezzoGettoni >= 0 && disp >= 0){
            for(Prodotto prod : listaProdotti)
                if(prod.getNome().equalsIgnoreCase(nome))
                    throw new GenericSystemException();
            newProdotto = new Prodotto(nome, categoria, prezzo, prezzoGettoni, disp);
            listaProdotti.add(newProdotto);
        }
        else
            throw new NotPositiveNumberException();
    }
    
    public void modificaProdotto(int idProd, String nome, String categoria, float prezzo, int prezzoGettoni, int disp) throws NotPositiveNumberException, GenericSystemException {
        List<Prodotto> listaProdotti = catalogo.getListaProdotti();
        
        if(prezzo >= 0 && prezzoGettoni >= 0 && disp >= 0){
            for(Prodotto prod : listaProdotti)
                if(prod.getId() != idProd && prod.getNome().equalsIgnoreCase(nome))
                    throw new GenericSystemException();
            for(Prodotto prod : listaProdotti)
                if(prod.getId() == idProd){
                    prod.setNome(nome);
                    prod.setCategoria(categoria);
                    prod.setPrezzo(prezzo);
                    prod.setPrezzoGettoni(prezzoGettoni);
                    prod.setDisp(disp);
                    return;
                }
        }
        else
            throw new NotPositiveNumberException();
    }
    
    public void eliminaProdotto(int idProd){
        Iterator<Prodotto> it = catalogo.getListaProdotti().iterator();
        Prodotto prod;
        
        while(it.hasNext()){
            prod = it.next();
            if(prod.getId() == idProd){
                it.remove();
                return;
            }
        }
    }
}
