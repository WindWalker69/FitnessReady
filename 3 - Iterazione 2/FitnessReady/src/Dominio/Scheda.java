package Dominio;

import Dominio.Esercizi.*;
import Dominio.Pasti.*;

import java.util.concurrent.atomic.AtomicLong;

public class Scheda {
    
    //----VARIABILI----
    
    private int id;
    private String nome, obiettivo;
    private float altMin, altMax, pMin, pMax;
    private Dieta dieta;
    private PianoEsercizi pianoEsercizi;
    protected static final AtomicLong counter = new AtomicLong(0);
    
    //----COSTRUTTORI----
    
    //----Con id manuale----
    public Scheda(int id, String nome, float altMin, float altMax, float pMin, float pMax, String obiettivo, Dieta dieta, PianoEsercizi pianoEsercizi){
        this.id = id;
        this.nome = nome;
        this.altMin = altMin;
        this.altMax = altMax;
        this.pMin = pMin;
        this.pMax = pMax;
        this.obiettivo = obiettivo;
        this.dieta = dieta;
        this.pianoEsercizi = pianoEsercizi;
    }
    
    //----Con id incrementale----
    public Scheda(String nome, float altMin, float altMax, float pMin, float pMax, String obiettivo, Dieta dieta, PianoEsercizi pianoEsercizi){
        setId();
        this.nome = nome;
        this.altMin = altMin;
        this.altMax = altMax;
        this.pMin = pMin;
        this.pMax = pMax;
        this.obiettivo = obiettivo;
        this.dieta = dieta;
        this.pianoEsercizi = pianoEsercizi;
    }
    
    //----GETTERS e SETTERS----
    
    //
    private void setId(){ id = (int)counter.incrementAndGet(); }
    //
    
    public void setId(int id){ this.id = id; }
    public int getId(){ return id; }
    
    public void setNome(String nome){ this.nome = nome; }
    public String getNome(){ return nome; }
    
    public void setAltMin(float altMin){ this.altMin = altMin; }
    public float getAltMin(){ return altMin; }
    
    public void setAltMax(float altMax){ this.altMax = altMax; }
    public float getAltMax(){ return altMax; }
    
    public void setPMin(float pMin){ this.pMin = pMin; }
    public float getPMin(){ return pMin; }
    
    public void setPMax(float pMax){ this.pMax = pMax; }
    public float getPMax(){ return pMax; }
    
    public void setObiettivo(String obiettivo){ this.obiettivo = obiettivo; }
    public String getObiettivo(){ return obiettivo; }
    
    public void setDieta(Dieta dieta){ this.dieta = dieta; }
    public Dieta getDieta(){ return dieta; }
    
    public void setPianoEsercizi(PianoEsercizi pianoEsercizi){ this.pianoEsercizi = pianoEsercizi; }
    public PianoEsercizi getPianoEsercizi(){ return pianoEsercizi; }
    
    //----METODI DI PROGETTO----
}
