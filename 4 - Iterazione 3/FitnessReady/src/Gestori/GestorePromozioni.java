package Gestori;

import Dominio.*;
import Eccezioni.*;

import java.util.*;

public class GestorePromozioni {
    
    //----VARIABILI----
    
    private final StartUp SU;
    private List<Promozione> listaPromozioni;
    
    //----COSTRUTTORI----
    
    public GestorePromozioni(){
        SU = new StartUp();
        listaPromozioni = SU.startUpListaPromozioni();
    }
    
    //----GETTERS e SETTERS----
    
    public void setListaPromozioni(List<Promozione> listaPromozioni){ this.listaPromozioni = listaPromozioni; }
    public List<Promozione> getListaPromozioni(){ return listaPromozioni; }
    
    //----METODI DI PROGETTO----
    
    public Promozione getPromozione(int idPromo){
        for(Promozione promo : listaPromozioni)
            if(promo.getId()== idPromo)
                return promo;
        return null;
    }
    
    public void aggiungiPromozione(String nome, String cTarget, float percSconto) throws NotPositiveNumberException, GenericSystemException, Exception {
        Promozione newPromozione;
        
        if(percSconto >= 0){
            for(Promozione promo : listaPromozioni){
                if(promo.getNome().equalsIgnoreCase(nome))
                    throw new GenericSystemException();
                if(promo.getCTarget().equals(cTarget))
                    throw new Exception();
            }
            newPromozione = new Promozione(nome, cTarget, percSconto);
            listaPromozioni.add(newPromozione);
        }
        else
            throw new NotPositiveNumberException();
    }
    
    public void modificaDatiPromozione(int idPromo, String nome, String cTarget, float percSconto) throws NotPositiveNumberException, GenericSystemException, Exception {
        if(percSconto >= 0){
            for(Promozione promo : listaPromozioni){
                if(promo.getId() != idPromo && promo.getNome().equalsIgnoreCase(nome))
                    throw new GenericSystemException();
                if(promo.getId() != idPromo && promo.getCTarget().equals(cTarget))
                    throw new Exception();
            }
            for(Promozione promo : listaPromozioni){
                if(promo.getId() == idPromo){
                    promo.setNome(nome);
                    promo.setCTarget(cTarget);
                    promo.setPercSconto(percSconto);
                    return;
                }
            }
        }
        else
            throw new NotPositiveNumberException();
    }
    
    public void cambiaStatoPromozione(int idPromo){
        for(Promozione promo : listaPromozioni)
            if(promo.getId() == idPromo)
                if(promo.getStato().equals("NON ATTIVA")){
                    promo.setStato("ATTIVA");
                    return;
                }
                else{
                    promo.setStato("NON ATTIVA");
                    return;
                }
    }
    
    public void eliminaPromozione(int idPromo){
        Iterator<Promozione> it = listaPromozioni.iterator();
        Promozione promo;
        
        while(it.hasNext()){
            promo = it.next();
            if(promo.getId() == idPromo){
                it.remove();
                return;
            }
        }
    }
}
