package Dominio;

import java.util.*;

public class Carrello {
    
    //----VARIABILI----
    
    private List<RigaCarrello> righeCarrello;
    private float totaleCarrello;
    
    //----COSTRUTTORI----
    
    public Carrello(){
        righeCarrello = new ArrayList<>();
    }
    
    //----GETTERS e SETTERS----
    
    public void setRigheCarrello(List<RigaCarrello> listaCarrello){ this.righeCarrello = listaCarrello; }
    public List<RigaCarrello> getRigheCarrello(){ return righeCarrello; }
    
    public float getTotaleCarrello(){
        totaleCarrello = 0;
        for(int i = 0; i < righeCarrello.size(); i++)
            totaleCarrello += righeCarrello.get(i).getSubTotale();
        return totaleCarrello;
    }
    
    //----METODI DI PROGETTO----
}
