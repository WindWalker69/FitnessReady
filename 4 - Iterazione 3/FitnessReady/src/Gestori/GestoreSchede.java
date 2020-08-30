package Gestori;

import Dominio.*;
import Eccezioni.*;
import Dominio.Pasti.Dieta;
import Dominio.Esercizi.PianoEsercizi;

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
    
    public Scheda getScheda(int idScheda){
        for(Scheda scheda : listaSchede){
            if(scheda.getId() == idScheda)
                return scheda;
        }
        return null;
    }
    
    public Scheda getSchedaCliente(String emailCliente){
        Scheda scheda = mappaSchedeClienti.get(emailCliente);
        return scheda;
    }
    
    public void aggiungiScheda(String nome, float altMin, float altMax, float pMin, float pMax, String obiettivo, Dieta dieta, PianoEsercizi pianoEsercizi) throws NotPositiveNumberException, GenericSystemException {
        Scheda newScheda;
        
        if(altMin >= 0 && altMax >= 0 && pMin >= 0 && pMax >= 0){
            for(Scheda s : listaSchede)
                if(s.getNome().equalsIgnoreCase(nome))
                    throw new GenericSystemException();
            newScheda = new Scheda(nome, altMin, altMax, pMin, pMax, obiettivo, dieta, pianoEsercizi);
            listaSchede.add(newScheda);
        }
        else
            throw new NotPositiveNumberException();
    }
    
    public void modificaScheda(int idScheda, String nome, float altMin, float altMax, float pMin, float pMax, String obiettivo, Dieta dieta, PianoEsercizi pianoEsercizi) throws NotPositiveNumberException, GenericSystemException {
        if(altMin >= 0 && altMax >= 0 && pMin >= 0 && pMax >= 0){
            for(Scheda s : listaSchede)
                if(s.getNome().equalsIgnoreCase(nome))
                    throw new GenericSystemException();
            for(Scheda scheda : listaSchede)
                if(scheda.getId() == idScheda){
                    scheda.setNome(nome);
                    scheda.setAltMin(altMin);
                    scheda.setAltMax(altMax);
                    scheda.setPMin(pMin);
                    scheda.setPMax(pMax);
                    scheda.setObiettivo(obiettivo);
                    scheda.setDieta(dieta);
                    scheda.setPianoEsercizi(pianoEsercizi);
                    return;
                }
        }
        else
            throw new NotPositiveNumberException();
    }
    
    public void eliminaScheda(int idScheda){
        Iterator<Scheda> it = listaSchede.iterator();
        Scheda scheda;
        
        while(it.hasNext()){
            scheda = it.next();
            if(scheda.getId() == idScheda){
                it.remove();
                return;
            }
        }
    }
}
