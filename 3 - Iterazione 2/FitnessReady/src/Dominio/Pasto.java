package Dominio;

import java.util.*;

public abstract class Pasto {
    
    //----VARIABILI----
    
    protected String categoria;
    protected List<Portata> elencoPortate;
    
    //----COSTRUTTORI----
    
    protected Pasto(){}
    
    protected Pasto(String categoria){
        this.categoria = categoria;
        elencoPortate = new LinkedList<>();
    }
    
    protected Pasto(String categoria, List<Portata> elencoPortate){
        this.categoria = categoria;
        this.elencoPortate = elencoPortate;
    }
    
    //----GETTERS e SETTERS----
    
    public void setCategoria(String categoria){ this.categoria = categoria; }
    public String getCategoria(){ return categoria; }
    
    public void setElencoPortate(List<Portata> elencoPortate){ this.elencoPortate = elencoPortate; }
    public List<Portata> getElencoPortate(){ return elencoPortate; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        String stringa = "";
        
        for(Portata p : elencoPortate)
            stringa += p + "\n";
        
        return stringa;
    }
}
