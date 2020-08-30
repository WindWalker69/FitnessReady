package Gestori;

import Dominio.*;
import Eccezioni.*;

import java.util.*;

public class GestoreOrdini {
    
    //----VARIABILI----
    
    private Map<String, List<Ordine>> mappaOrdiniClienti;
    
    //----COSTRUTTORI----
     
    public GestoreOrdini(){
        mappaOrdiniClienti = new HashMap<>();
    }
    
    //----GETTERS e SETTERS----
    
    public void setMappaOrdiniClienti(Map<String, List<Ordine>> mappaOrdiniClienti){ this.mappaOrdiniClienti = mappaOrdiniClienti; }
    public Map<String, List<Ordine>> getMappaOrdiniClienti(){ return mappaOrdiniClienti; }
    
    public void setListaOrdiniCliente(String emailCliente, List<Ordine> listaOrdini){
        mappaOrdiniClienti.put(emailCliente, listaOrdini);
    }
    public List<Ordine> getListaOrdiniCliente(String emailCliente){
        List<Ordine> listaOrdiniCliente = new ArrayList<>();
        if(mappaOrdiniClienti.containsKey(emailCliente))
            listaOrdiniCliente = mappaOrdiniClienti.get(emailCliente);
        return listaOrdiniCliente;
    }
    
    //----METODI DI PROGETTO----
    
    public Ordine getOrdine(String emailClienteAttuale, int idOrdine){
        List<Ordine> listaOrdini = mappaOrdiniClienti.get(emailClienteAttuale);
        
        for(Ordine o : listaOrdini){
            if(o.getId() == idOrdine)
                return o;
        }
        return null;
    }
    
    public void aggiungiOrdine(String emailClienteAttuale, Ordine newOrdine){
        if(mappaOrdiniClienti.containsKey(emailClienteAttuale))
            mappaOrdiniClienti.get(emailClienteAttuale).add(newOrdine);
        else{
            List<Ordine> ordiniCliente = new ArrayList<>();
            ordiniCliente.add(newOrdine);
            mappaOrdiniClienti.put(emailClienteAttuale, ordiniCliente);
        }
    }
    
    public Ordine effettuaOrdine(Acquisto newAcquisto, Date dataEsecuzione, Date dataArrivo, String indirizzo, String città, String cap, String metodoPagamento, String numCarta, String cvv){
        Prodotto prod;
        int quantità;
        Ordine newOrdine;
        RigaOrdine rigaOrdine;
        
        if(metodoPagamento.equals("Carta di credito"))
            newOrdine = new Ordine(dataEsecuzione, dataArrivo, indirizzo, città, cap, metodoPagamento, numCarta, cvv);
        else
            newOrdine = new Ordine(dataEsecuzione, dataArrivo, indirizzo, città, cap, metodoPagamento);
        for(RigaAcquisto riga : newAcquisto.getRigheAcquisto()){
            prod = riga.getProdotto();
            quantità = riga.getQuantità();
            rigaOrdine = new RigaOrdine(prod, quantità);
            newOrdine.getRigheOrdine().add(rigaOrdine);
        }
        return newOrdine;
    }
    
    public void modificaOrdine(int idOrdine, String emailClienteAttuale, String indirizzo, String città, String cap) throws GenericSystemException {
        Iterator<Ordine> it;
        Ordine ordine;
        
        it = mappaOrdiniClienti.get(emailClienteAttuale).iterator();
        while(it.hasNext()){
            ordine = it.next();
            if(ordine.getId() == idOrdine){
                ordine.setIndirizzo(indirizzo);
                ordine.setCittà(città);
                ordine.setCap(cap);
                return;
            }
        }
        throw new GenericSystemException();
    }
}
