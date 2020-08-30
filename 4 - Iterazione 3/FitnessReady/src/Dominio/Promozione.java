package Dominio;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Promozione {
    
    //----VARIABILI----
    
    private int id;
    private float percSconto;
    private Date dataCreazione;
    private String nome, cTarget, stato;
    private static final AtomicLong counter = new AtomicLong(0);
    
    //----COSTRUTTORI----
    
    //----Con id manuale----
    public Promozione(int id, String nome, String cTarget, float percSconto){
        this.id = id;
        this.nome = nome;
        this.cTarget = cTarget;
        this.percSconto = percSconto;
        dataCreazione = new Date();
        stato = "NON ATTIVA";
    }
    
    //----Con id incrementale----
    public Promozione(String nome, String cTarget, float percSconto){
        setId();
        this.nome = nome;
        this.cTarget = cTarget;
        this.percSconto = percSconto;
        dataCreazione = new Date();
        stato = "NON ATTIVA";
    }
    
    //----GETTERS e SETTERS----
    
    //
    private void setId(){ id = (int)counter.incrementAndGet(); }
    //
    
    public void setId(int id){ this.id = id; }
    public int getId(){ return id; }
    
    public void setNome(String nome){ this.nome = nome; }
    public String getNome(){ return nome; }
    
    public void setCTarget(String cTarget){ this.cTarget = cTarget; }
    public String getCTarget(){ return cTarget; }
    
    public void setPercSconto(float percSconto){ this.percSconto = percSconto; }
    public float getPercSconto(){ return percSconto; }
    
    public void setDataCreazione(Date dataCreazione){ this.dataCreazione = dataCreazione; }
    public Date getDataCreazione(){ return dataCreazione; }
    
    public void setStato(String stato){ this.stato = stato; }
    public String getStato(){ return stato; }
}
