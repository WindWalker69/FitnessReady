package Gestori;

import Dominio.*;

import java.util.*;

public class GestoreSchede {
    
    //----VARIABILI----
    
    //private final static GestoreSchede istance = null;
    private final StartUp SU;
    private List<Scheda> listaSchede;
    private Map<String, Scheda> mappaSchedeClienti;
    
    //----COSTRUTTORI----
    
    public GestoreSchede(){
        SU = new StartUp();
        listaSchede = SU.startUpListaSchede();
        mappaSchedeClienti = SU.startUpMappaSchedeClienti(listaSchede);
    }
    
    //----GETTERS e SETTERS----
    
    public void setListaSchede(List<Scheda> listaSchede){ this.listaSchede = listaSchede; }
    public List<Scheda> getListaSchede(){ return listaSchede; }
    
    public void setMappaSchedeClienti(Map<String, Scheda> mappaSchedeClienti){ this.mappaSchedeClienti = mappaSchedeClienti; }
    public Map<String, Scheda> getMappaSchedeClienti(){ return mappaSchedeClienti; }
    
    //----METODI DI PROGETTO----
    
    public void assegnaScheda(Cliente cliente){
        float altC, pC, altMinS, altMaxS, pMinS, pMaxS;
        String emailC, obiettivoC, obiettivoS;
        
        emailC = cliente.getEmail();
        obiettivoC = cliente.getObiettivo();
        altC = cliente.getAltezza();
        pC = cliente.getPeso();
        for(Scheda s : listaSchede){
            altMinS = s.getAltMin();
            altMaxS = s.getAltMax();
            pMinS = s.getPMin();
            pMaxS = s.getPMax();
            obiettivoS = s.getObiettivo();
            if(altC >= altMinS && altC <= altMaxS && pC >= pMinS && pC <= pMaxS && obiettivoC.equals(obiettivoS))
                mappaSchedeClienti.put(emailC, s);
        }
    }
    
    public Scheda getSchedaCliente(String emailCliente){
        Scheda scheda = mappaSchedeClienti.get(emailCliente);
        return scheda;
    }
}
