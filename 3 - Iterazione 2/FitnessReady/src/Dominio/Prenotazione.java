package Dominio;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Prenotazione implements Observer {
    
    //----VARIABILI----
    
    private final Prenotazione thisP = this;
    
    private int id, quantità, totaleGettoni;
    private float totale;
    private Date dataPrenotazione;
    private String indirizzo, città, metodoPagamento, cap, numCarta, cvv;
    private Prodotto prod;
    private static final AtomicLong counter = new AtomicLong(0);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    //----COSTRUTTORI----
    
    //----Con id manuale----
    public Prenotazione(int id, Date dataPrenotazione, Prodotto prod, int quantità, float totale, int totaleGettoni, String indirizzo, String città, String cap, String metodoPagamento){
        this.id = id;
        this.dataPrenotazione = dataPrenotazione;
        this.prod = prod;
        this.quantità = quantità;
        this.totale = totale;
        this.totaleGettoni = totaleGettoni;
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
        this.metodoPagamento = metodoPagamento;
        numCarta = "";
        cvv = "";
        
        this.prod.addObserver(thisP);
    }
    
    public Prenotazione(int id, Date dataPrenotazione, Prodotto prod, int quantità, float totale, int totaleGettoni, String indirizzo, String città, String cap, String metodoPagamento, String numCarta, String cvv){
        this.id = id;
        this.dataPrenotazione = dataPrenotazione;
        this.prod = prod;
        this.quantità = quantità;
        this.totale = totale;
        this.totaleGettoni = totaleGettoni;
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
        this.metodoPagamento = metodoPagamento;
        this.numCarta = numCarta;
        this.cvv = cvv;
        
        this.prod.addObserver(thisP);
    }
    
    //----Con id incrementale----
    public Prenotazione(Date dataPrenotazione, Prodotto prod, int quantità, float totale, int totaleGettoni, String indirizzo, String città, String cap, String metodoPagamento){
        setId();
        this.dataPrenotazione = dataPrenotazione;
        this.prod = prod;
        this.quantità = quantità;
        this.totale = totale;
        this.totaleGettoni = totaleGettoni;
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
        this.metodoPagamento = metodoPagamento;
        numCarta = "";
        cvv = "";
        
        this.prod.addObserver(thisP);
    }
    
    public Prenotazione(Date dataPrenotazione, Prodotto prod, int quantità, float totale, int totaleGettoni, String indirizzo, String città, String cap, String metodoPagamento, String numCarta, String cvv){
        setId();
        this.dataPrenotazione = dataPrenotazione;
        this.prod = prod;
        this.quantità = quantità;
        this.totale = totale;
        this.totaleGettoni = totaleGettoni;
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
        this.metodoPagamento = metodoPagamento;
        this.numCarta = numCarta;
        this.cvv = cvv;
        
        this.prod.addObserver(thisP);
    }
    
    //----GETTERS e SETTERS----
    
    //
    private void setId(){ id = (int)counter.incrementAndGet(); }
    //
    
    public void setId(int id){ this.id = id; }
    public int getId(){ return id; }
    
    public void setDataPrenotazione(Date dataPrenotazione){ this.dataPrenotazione = dataPrenotazione; }
    public Date getDataPrenotazione(){ return dataPrenotazione; }
    
    public void setProd(Prodotto prod){ this.prod = prod; }
    public Prodotto getProd(){ return prod; }
    
    public void setQuantità(int quantità){ this.quantità = quantità; }
    public int getQuantità(){ return quantità; }
    
    public void setTotale(float totale){ this.totale = totale; }
    public float getTotale(){ return totale; }
    
    public void setTotaleGettoni(int totaleGettoni){ this.totaleGettoni = totaleGettoni; }
    public int getTotaleGettoni(){ return totaleGettoni; }
    
    public void setIndirizzo(String indirizzo){ this.indirizzo = indirizzo; }
    public String getIndirizzo(){ return indirizzo; }
    
    public void setCittà(String città){ this.città = città; }
    public String getCittà(){ return città; }
    
    public void setCap(String cap){ this.cap = cap; }
    public String getCap(){ return cap; }
    
    public void setMetodoPagamento(String metodoPagamento){ this.metodoPagamento = metodoPagamento; }
    public String getMetodoPagamento(){ return metodoPagamento; }
    
    public void setNumCarta(String numCarta){ this.numCarta = numCarta; }
    public String getNumCarta(){ return numCarta; }
    
    public void setCvv(String cvv){ this.cvv = cvv; }
    public String getCvv(){ return cvv; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        String stringa = "ID PRENOTAZIONE: " + id + " (Data ed ora prenotazione: " + dateFormat.format(dataPrenotazione) + ")"
                + "\n\nPRODOTTO PRENOTATO:"
                + "\n" + prod.getNome() + " - Quantità prenotata: " + quantità + " - Totale: " + totale + " - Totale Gettoni: " + totaleGettoni
                + "\n\nDATI PRENOTAZIONE:"
                + "\nIndirizzo: " + indirizzo + " - Città: " + città + " - CAP: " + cap + " - Metodo di pagamento: " + metodoPagamento;
        if(metodoPagamento.equals("Carta di credito"))
            stringa += " - Numero di Carta: " + numCarta + " - CVV: " + cvv;
        return stringa;
    }
    
    private Date addDaysToDate(Date date, int days){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }
    
    @Override
    public void update(Observable prod, Object arg){
        int dispProd = ((Prodotto) prod).getDisp();
        
        if(dispProd >= quantità){
            FitnessReady FR = FitnessReady.getInstance();
            FR.setGestoreAcquisto();
            try{
                Date dataEsecuzione = new Date();
                Date dataArrivo = addDaysToDate(dataEsecuzione, 7);
                FR.getGestoreAcquisto().aggiungiAlCarrello((Prodotto)prod, quantità);
                FR.buyAndUpdate(dataEsecuzione, dataArrivo, indirizzo, città, cap, metodoPagamento, numCarta, cvv);
                FR.getGestorePrenotazioni().eliminaPrenotazione(FR.getClienteAttuale(), id);
                this.prod.deleteObserver(this);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
}
