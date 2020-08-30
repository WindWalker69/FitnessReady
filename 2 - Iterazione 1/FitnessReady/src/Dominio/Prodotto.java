package Dominio;

import java.util.concurrent.atomic.AtomicLong;

public class Prodotto {
    
    //----VARIABILI----
    
    private int id, disp;
    private float prezzo;
    private String nome, categoria;
    private static final AtomicLong counter = new AtomicLong(0);
    
    //----COSTRUTTORI----
    
    public Prodotto(){}
    
    public Prodotto(int id, String nome, String categoria, float prezzo, int disp){
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.prezzo = prezzo;
        this.disp = disp;
    }
    
    public Prodotto(String nome, String categoria, float prezzo, int disp){
        setId();
        this.nome = nome;
        this.categoria = categoria;
        this.prezzo = prezzo;
        this.disp = disp;
    }
    
    //----GETTERS e SETTERS----
    
    //
    private void setId(){ id = (int)counter.getAndIncrement(); }
    //
    
    public void setId(int id){ this.id = id; }
    public int getId(){ return id; }
    
    public void setNome(String nome){ this.nome = nome; }
    public String getNome(){ return nome; }
    
    public void setCategoria(String categoria){ this.categoria = categoria; }
    public String getCategoria(){ return categoria; }
    
    public void setPrezzo(float prezzo){ this.prezzo = prezzo; }
    public float getPrezzo(){ return prezzo; }
    
    public void setDisp(int disp){ this.disp = disp; }
    public int getDisp(){ return disp; }
    
    @Override
    public String toString(){
        return "NOME: " + nome + "; CATEGORIA: " + categoria + "; PREZZO: " + String.format("%.2f", prezzo);
    }
}
