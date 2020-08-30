package Dominio;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Acquisto {
    
    //----VARIABILI----
    
    private int id, totaleGettoni;
    private Date data;
    private float totale;
    private List<RigaAcquisto> righeAcquisto;
    private static final AtomicLong counter = new AtomicLong(0);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    //----COSTRUTTORI----
    
    //----Con id manuale----
    public Acquisto(int id, Date data, float totale, int totaleGettoni){
        this.id = id;
        this.data = data;
        this.totale = totale;
        this.totaleGettoni = totaleGettoni;
        righeAcquisto = new ArrayList<>();
    }
    
    public Acquisto(int id, Date data, float totale, int totaleGettoni, List<RigaAcquisto> righeAcquisto){
        this.id = id;
        this.data = data;
        this.totale = totale;
        this.totaleGettoni = totaleGettoni;
        this.righeAcquisto = righeAcquisto;
    }
    
    //----Con id incrementale----
    public Acquisto(Date data, float totale, int totaleGettoni){
        setId();
        this.data = data;
        this.totale = totale;
        this.totaleGettoni = totaleGettoni;
        righeAcquisto = new ArrayList<>();
    }
    
    public Acquisto(Date data, float totale, int totaleGettoni, List<RigaAcquisto> righeAcquisto){
        setId();
        this.data = data;
        this.totale = totale;
        this.totaleGettoni = totaleGettoni;
        this.righeAcquisto = righeAcquisto;
    }
    
    //----GETTERS e SETTERS----
    
    //
    private void setId(){ id = (int)counter.incrementAndGet(); }
    //
    
    public void setId(int id){ this.id = id; }
    public int getId(){ return id; }
    
    public void setData(Date data){ this.data = data; }
    public Date getDate(){ return data; }
    
    public void setTotale(float totale){ this.totale = totale; }
    public float getTotale(){ return totale; }
    
    public void setTotaleGettoni(int totaleGettoni){ this.totaleGettoni = totaleGettoni; }
    public int getTotaleGettoni(){ return totaleGettoni; }
    
    public void setRigheAcquisto(List<RigaAcquisto> righeAcquisto){ this.righeAcquisto = righeAcquisto; }
    public List<RigaAcquisto> getRigheAcquisto(){ return righeAcquisto; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        String stringa = "ACQUISTO N°: " + id + " (Data ed ora: " + dateFormat.format(data) + ")"
                + "\n\nPRODOTTI ACQUISTATI:";
        for(int i = 0; i < righeAcquisto.size(); i++)
            stringa += "\n" + righeAcquisto.get(i);
        stringa += "\n\nTOTALE EURO: " + String.format("%.2f", totale) + "€ - TOTALE GETTONI: " + totaleGettoni +
                "\n------------------------------------------------------------------------------------------------------\n\n";
        return stringa;
    }
}
