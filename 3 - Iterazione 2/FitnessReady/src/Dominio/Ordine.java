package Dominio;

import java.util.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicLong;

public class Ordine {
    
    //TODO: Aggiungere codice per il tipo e sistemare il resto
    
    //----VARIABILI----
    
    private int id;
    private Date dataEsecuzione, dataArrivo;
    private String indirizzo, città, metodoPagamento, cap, numCarta, cvv, stato;
    private List<RigaOrdine> righeOrdine;
    private static final AtomicLong counter = new AtomicLong(0);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    //----COSTRUTTORI----
    
    //----Con id manuale----
    public Ordine(int id, Date dataEsecuzione, Date dataArrivo, String indirizzo, String città, String cap, String metodoPagamento){
        this.id = id;
        this.dataEsecuzione = dataEsecuzione;
        this.dataArrivo = dataArrivo;
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
        this.metodoPagamento = metodoPagamento;
        numCarta = "";
        cvv = "";
        righeOrdine = new ArrayList<>();
        stato = "IN CORSO";
    }
    
    public Ordine(int id, Date dataEsecuzione, Date dataArrivo, String indirizzo, String città, String cap, String metodoPagamento, String numCarta, String cvv){
        this.id = id;
        this.dataEsecuzione = dataEsecuzione;
        this.dataArrivo = dataArrivo;
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
        this.metodoPagamento = metodoPagamento;
        this.numCarta = numCarta;
        this.cvv = cvv;
        righeOrdine = new ArrayList<>();
        stato = "IN CORSO";
    }
    
    public Ordine(int id, Date dataEsecuzione, Date dataArrivo, String indirizzo, String città, String cap, String metodoPagamento, List<RigaOrdine> righeOrdine){
        this.id = id;
        this.dataEsecuzione = dataEsecuzione;
        this.dataArrivo = dataArrivo;
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
        this.metodoPagamento = metodoPagamento;
        this.righeOrdine = righeOrdine;
        numCarta = "";
        cvv = "";
        stato = "IN CORSO";
    }
    
    public Ordine(int id, Date dataEsecuzione, Date dataArrivo, String indirizzo, String città, String cap, String metodoPagamento, String numCarta, String cvv, List<RigaOrdine> righeOrdine){
        this.id = id;
        this.dataEsecuzione = dataEsecuzione;
        this.dataArrivo = dataArrivo;
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
        this.metodoPagamento = metodoPagamento;
        this.numCarta = numCarta;
        this.cvv = cvv;
        this.righeOrdine = righeOrdine;
        stato = "IN CORSO";
    }
    
    //----Con id incrementale----
    public Ordine(Date dataEsecuzione, Date dataArrivo, String indirizzo, String città, String cap, String metodoPagamento){
        setId();
        this.dataEsecuzione = dataEsecuzione;
        this.dataArrivo = dataArrivo;
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
        this.metodoPagamento = metodoPagamento;
        numCarta = "";
        cvv = "";
        righeOrdine = new ArrayList<>();
        stato = "IN CORSO";
    }
    
    public Ordine(Date dataEsecuzione, Date dataArrivo, String indirizzo, String città, String cap, String metodoPagamento, String numCarta, String cvv){
        setId();
        this.dataEsecuzione = dataEsecuzione;
        this.dataArrivo = dataArrivo;
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
        this.metodoPagamento = metodoPagamento;
        this.numCarta = numCarta;
        this.cvv = cvv;
        righeOrdine = new ArrayList<>();
        stato = "IN CORSO";
    }
    
    public Ordine(Date dataEsecuzione, Date dataArrivo, String indirizzo, String città, String cap, String metodoPagamento, List<RigaOrdine> righeOrdine){
        setId();
        this.dataEsecuzione = dataEsecuzione;
        this.dataArrivo = dataArrivo;
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
        this.metodoPagamento = metodoPagamento;
        numCarta = "";
        cvv = "";
        this.righeOrdine = righeOrdine;
        stato = "IN CORSO";
    }
    
    public Ordine(Date dataEsecuzione, Date dataArrivo, String indirizzo, String città, String cap, String metodoPagamento, String numCarta, String cvv, List<RigaOrdine> righeOrdine){
        setId();
        this.dataEsecuzione = dataEsecuzione;
        this.dataArrivo = dataArrivo;
        this.indirizzo = indirizzo;
        this.città = città;
        this.cap = cap;
        this.metodoPagamento = metodoPagamento;
        this.numCarta = numCarta;
        this.cvv = cvv;
        this.righeOrdine = righeOrdine;
        stato = "IN CORSO";
    }
    
    //----GETTERS e SETTERS----
    
    //
    private void setId(){ id = (int)counter.incrementAndGet(); }
    //
    
    public void setId(int id){ this.id = id; }
    public int getId(){ return id; }
    
    public void setDataEsecuzione(Date dataEsecuzione){ this.dataEsecuzione = dataEsecuzione; }
    public Date getDataEsecuzione(){ return dataEsecuzione; }
    
    public void setDataArrivo(Date dataArrivo){ this.dataArrivo = dataArrivo; }
    public Date getDataArrivo(){ return dataArrivo; }
    
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
    
    public void setStato(String stato){ this.stato = stato; }
    public String getStato(){ return stato; }
    
    public void setRigheOrdine(List<RigaOrdine> righeOrdine){ this.righeOrdine = righeOrdine; }
    public List<RigaOrdine> getRigheOrdine(){ return righeOrdine; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        String stringa = "ORDINE N°: " + id + " (Data esecuzione: " + dateFormat.format(dataEsecuzione) + "; Data arrivo: " + dateFormat.format(dataArrivo) + ")"
                + "\n\nPRODOTTI ORDINATI:";
        for(int i = 0; i < righeOrdine.size(); i++)
            stringa += "\n" + righeOrdine.get(i);
        stringa += "\n\nDATI ORDINE:"
                + "\nIndirizzo: " + indirizzo + " - Città: " + città + " - CAP: " + cap + " - Metodo di pagamento: " + metodoPagamento;
        if(metodoPagamento.equals("Carta di credito"))
            stringa += " - Numero di Carta: " + numCarta + " - CVV: " + cvv;
        stringa += "\n\nSTATO ORDINE: " + stato
                + "\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n";
        return stringa;
    }
}
