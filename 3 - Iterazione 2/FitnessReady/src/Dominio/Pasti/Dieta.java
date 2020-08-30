package Dominio.Pasti;

import Dominio.Pasto;

import java.util.*;

public class Dieta extends Pasto {
    
    //----VARIABILI----
    
    private List<Pasto> listaPasti;
    
    //----COSTRUTTORI----
    
    public Dieta(){
        super();
        listaPasti = new ArrayList<>();
    }
    
    public Dieta(List<Pasto> listaPasti){
        super();
        this.listaPasti = listaPasti;
    }
    
    //----GETTERS e SETTERS----
    
    public void setListaPasti(List<Pasto> listaPasti){ this.listaPasti = listaPasti; }
    public List<Pasto> getListaPasti(){ return listaPasti; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        String stringa = "";
        
        stringa += "COLAZIONE:\n";
        for(Pasto p : listaPasti)
            if(p.getCategoria().equals("COLAZIONE"))
                stringa += p;
        
        stringa += "\nPRANZO:\n";
        for(Pasto p : listaPasti)
            if(p.getCategoria().equals("PRANZO"))
                stringa += p;
        
        stringa += "\nCENA:\n";
        for(Pasto p : listaPasti)
            if(p.getCategoria().equals("CENA"))
                stringa += p;
        
        return stringa;
    }
}
