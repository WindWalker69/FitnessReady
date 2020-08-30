package Dominio.Esercizi;

import Dominio.Esercizio;

import java.util.*;

public class PianoEsercizi extends Esercizio {
    
    //----VARIABILI----
    
    private List<Esercizio> listaEsercizi;
    
    //----COSTRUTTORI----
    
    public PianoEsercizi(){
        super();
        listaEsercizi = new ArrayList<>();
    }
    
    public PianoEsercizi(List<Esercizio> listaEsercizi){
        super();
        this.listaEsercizi = listaEsercizi;
    }
    
    //----GETTERS e SETTERS----
    
    public void setListaEsercizi(List<Esercizio> listaEsercizi){ this.listaEsercizi = listaEsercizi; }
    public List<Esercizio> getListaEsercizi(){ return listaEsercizi; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        String stringa = "";
        
        stringa += "CARDIO:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("CARDIO"))
                stringa += es + "\n";
        
        stringa += "\nGAMBE:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("GAMBE"))
                stringa += es + "\n";
        
        stringa += "\nBRACCIA:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("BRACCIA"))
                stringa += es + "\n";
        
        stringa += "\nTORACE:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("TORACE"))
                stringa += es + "\n";
        
        stringa += "\nSPALLE:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("SPALLE"))
                stringa += es + "\n";
        
        stringa += "\nDORSALI:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("DORSALI"))
                stringa += es + "\n";
        
        stringa += "\nADDOME:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("ADDOME"))
                stringa += es + "\n";
        
        return stringa;
    }
    
    @Override
    public String toStringAdmin(){
        String stringa = "";
        
        stringa += "CARDIO:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("CARDIO"))
                stringa += es.toStringAdmin() + "\n";
        
        stringa += "\nGAMBE:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("GAMBE"))
                stringa += es.toStringAdmin() + "\n";
        
        stringa += "\nBRACCIA:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("BRACCIA"))
                stringa += es.toStringAdmin() + "\n";
        
        stringa += "\nTORACE:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("TORACE"))
                stringa += es.toStringAdmin() + "\n";
        
        stringa += "\nSPALLE:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("SPALLE"))
                stringa += es.toStringAdmin() + "\n";
        
        stringa += "\nDORSALI:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("DORSALI"))
                stringa += es.toStringAdmin() + "\n";
        
        stringa += "\nADDOME:\n";
        for(Esercizio es : listaEsercizi)
            if(es.getCategoria().equals("ADDOME"))
                stringa += es.toStringAdmin() + "\n";
        
        return stringa;
    }
}
