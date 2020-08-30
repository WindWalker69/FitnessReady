package Gestori;

import Dominio.*;
import Eccezioni.*;

import java.util.*;

public class GestorePrenotazioni {
    
    //----VARIABILI----
    
    private Map<String, List<Prenotazione>> mappaPrenotazioniClienti;
    
    //----COSTRUTTORI----
    
    public GestorePrenotazioni(){
        mappaPrenotazioniClienti = new HashMap<>();
    }
    
    //----GETTERS e SETTERS----
    
    public void setMappaPrenotazioniClienti(Map<String, List<Prenotazione>> mappaPrenotazioniClienti){ this.mappaPrenotazioniClienti = mappaPrenotazioniClienti; }
    public Map<String, List<Prenotazione>> getMappaPrenotazioniClienti(){ return mappaPrenotazioniClienti; }
    
    public void setListaPrenotazioniCliente(String emailCliente, List<Prenotazione> listaPrenotazioni){
        mappaPrenotazioniClienti.put(emailCliente, listaPrenotazioni);
    }
    public List<Prenotazione> getListaPrenotazioniCliente(String emailCliente){
        List<Prenotazione> listaPrenotazioni = new ArrayList<>();
        if(mappaPrenotazioniClienti.containsKey(emailCliente))
            listaPrenotazioni = mappaPrenotazioniClienti.get(emailCliente);
        return listaPrenotazioni;
    }
    
    //----METODI DI PROGETTO----
    
    public Prenotazione getPrenotazione(String emailCliente, int idPrenotazione){
        List<Prenotazione> listaPrenotazioni = mappaPrenotazioniClienti.get(emailCliente);
        
        for(Prenotazione p : listaPrenotazioni)
            if(p.getId() == idPrenotazione)
                return p;
        return null;
    }
    
    public void effettuaPrenotazione(Cliente cliente, Date dataPrenotazione, Prodotto prod, int quantità, String indirizzo, String città, String cap, String metodoPagamento, String numCarta, String cvv) throws NotPositiveNumberException, GenericSystemException {
        Prenotazione newPrenotazione;
        String emailCliente;
        int gettoniCliente, prezzoGettoni, totaleGettoni;
        float prezzo, totale;
        
        if(quantità > 0){
            //Creazione nuova prenotazione
            gettoniCliente = cliente.getGettoni();
            prezzo = prod.getPrezzo();
            prezzoGettoni = prod.getPrezzoGettoni();
            totaleGettoni = prezzoGettoni * quantità;
            
            if(metodoPagamento.equals("Carta di credito")){
                totale = prezzo * quantità;
                newPrenotazione = new Prenotazione(dataPrenotazione, prod, quantità, totale, 0, indirizzo, città, cap, metodoPagamento, numCarta, cvv);
            }
            else if(gettoniCliente >= totaleGettoni){
                newPrenotazione = new Prenotazione(dataPrenotazione, prod, quantità, 0, totaleGettoni, indirizzo, città, cap, metodoPagamento);
                cliente.setGettoni(gettoniCliente - totaleGettoni);
            }
            else
                throw new GenericSystemException();
            
            //Aggiunta nuova prenotazione
            emailCliente = cliente.getEmail();
            
            if(mappaPrenotazioniClienti.containsKey(emailCliente))
                mappaPrenotazioniClienti.get(emailCliente).add(newPrenotazione);
            else{
                List<Prenotazione> listaPrenotazioni = new ArrayList<>();
                listaPrenotazioni.add(newPrenotazione);
                mappaPrenotazioniClienti.put(emailCliente, listaPrenotazioni);
            }
        }
        else
            throw new NotPositiveNumberException();
    }
    
    public Prenotazione modificaPrenotazione(Prenotazione oldPrenotazione, Cliente cliente, int quantità, String indirizzo, String città, String cap, String metodoPagamento, String numCarta, String cvv) throws NotPositiveNumberException, GenericSystemException {
        int pIndex, gettoniCliente, prezzoGettoni, oldTotaleGettoni, totaleGettoni;
        float prezzo, totale;
        String emailCliente;
        Prenotazione prenotazioneCliente;
        Prodotto prod;
        
        if(quantità > 0){
            //Rimborso gettoni cliente
            gettoniCliente = cliente.getGettoni();
            oldTotaleGettoni = oldPrenotazione.getTotaleGettoni();
            cliente.setGettoni(gettoniCliente + oldTotaleGettoni);
            
            //Modifica dati prenotazione
            emailCliente = cliente.getEmail();
            pIndex = mappaPrenotazioniClienti.get(emailCliente).indexOf(oldPrenotazione);
            prenotazioneCliente = mappaPrenotazioniClienti.get(emailCliente).get(pIndex);
            
            prod = oldPrenotazione.getProd();
            prezzo = prod.getPrezzo();
            prezzoGettoni = prod.getPrezzoGettoni();
            gettoniCliente = cliente.getGettoni(); //si prende il nuovo valore dopo aver eseguito il setGettoni al cliente
            totaleGettoni = prezzoGettoni * quantità;
            
            if(metodoPagamento.equals("Carta di credito")){
                totale = prezzo * quantità;
                prenotazioneCliente.setTotale(totale);
                prenotazioneCliente.setTotaleGettoni(0);
                prenotazioneCliente.setNumCarta(numCarta);
                prenotazioneCliente.setCvv(cvv);
            }
            else if(gettoniCliente >= totaleGettoni){
                cliente.setGettoni(gettoniCliente - totaleGettoni);
                prenotazioneCliente.setTotale(0);
                prenotazioneCliente.setTotaleGettoni(totaleGettoni);
                prenotazioneCliente.setNumCarta("");
                prenotazioneCliente.setCvv("");
            }
            else
                throw new GenericSystemException();
            
            prenotazioneCliente.setQuantità(quantità);
            prenotazioneCliente.setIndirizzo(indirizzo);
            prenotazioneCliente.setCittà(città);
            prenotazioneCliente.setCap(cap);
            prenotazioneCliente.setMetodoPagamento(metodoPagamento);
        }
        else
            throw new NotPositiveNumberException();
        
        return prenotazioneCliente;
    }
    
    public void eliminaPrenotazione(Cliente cliente, int idPrenotazione){
        Iterator<Prenotazione> it = mappaPrenotazioniClienti.get(cliente.getEmail()).iterator();
        Prenotazione p;
        
        while(it.hasNext()){
            p = it.next();
            if(p.getId() == idPrenotazione){
                cliente.setGettoni(cliente.getGettoni() + p.getTotaleGettoni()); //Rimborso gettoni
                it.remove();
                return;
            }
        }
    }
}
