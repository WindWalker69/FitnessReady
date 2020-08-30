package Dominio;

import java.util.*;

public class StartUp {
    
    public StartUp(){}
    
    public List<Utente> startUpListaUtenti(){
        List<Utente> listaUtenti = new ArrayList<>();
        
        listaUtenti.add(new Cliente("Mario", "Nunez", "mR_95@live.it", "ciao", (float)1.75, (float)75));
        listaUtenti.add(new Cliente("Sara", "Marino", "revylaroche@yahoo.it", "ciao", (float)1.50, (float)45));
        
        return listaUtenti;
    }
    
    public List<Prodotto> startUpListaProdotti(){
        List<Prodotto> listaProdotti = new ArrayList<>();
        
        listaProdotti.add(new Prodotto("Oatbakes", "ALIMENTI", (float)17.49, (int)10));
        listaProdotti.add(new Prodotto("Impact Whey Protein (250 gr)", "INTEGRATORI", (float)7.99, (int)5));
        listaProdotti.add(new Prodotto("Crema Proteica", "ALIMENTI", (float)9.99, (int)3));
        
        return listaProdotti;
    }
    
    public Map<String, List<Acquisto>> startUpMappaAcquistiClienti(){
        Map<String, List<Acquisto>> mappaAcquistiClienti = new HashMap<>();
        List<Acquisto> listaAcquisti = new ArrayList<>();
        List<RigaAcquisto> righeAcquisto = new ArrayList<>();
        
        Prodotto prod = new Prodotto("Oatbakes", "ALIMENTI", (float)17.49, (int)10);
        RigaAcquisto riga = new RigaAcquisto(prod, 1);
        righeAcquisto.add(riga);
        
        Date dataAcquisto = new Date();
        float totaleAcquisto = prod.getPrezzo()*riga.getQuantità();
        Acquisto newAcquisto = new Acquisto(dataAcquisto, totaleAcquisto);
        newAcquisto.setRigheAcquisto(righeAcquisto);
        listaAcquisti.add(newAcquisto);
        
        mappaAcquistiClienti.put("mR_95@live.it", listaAcquisti);
        mappaAcquistiClienti.put("revylaroche@yahoo.it", listaAcquisti);
        
        return mappaAcquistiClienti;
    }
}
