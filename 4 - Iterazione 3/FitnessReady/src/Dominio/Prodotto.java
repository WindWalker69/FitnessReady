package Dominio;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Prodotto extends Observable {
    
    //----VARIABILI----
    
    private int id, disp, prezzoGettoni;
    private float prezzo;
    private String nome, categoria;
    private static final AtomicLong counter = new AtomicLong(0);
    
    //----COSTRUTTORI----
    
    public Prodotto(int id, String nome, String categoria, float prezzo, int prezzoGettoni, int disp){
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.prezzo = prezzo;
        this.prezzoGettoni = prezzoGettoni;
        this.disp = disp;
    }
    
    public Prodotto(String nome, String categoria, float prezzo, int prezzoGettoni, int disp){
        setId();
        this.nome = nome;
        this.categoria = categoria;
        this.prezzo = prezzo;
        this.prezzoGettoni = prezzoGettoni;
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
    
    public void setPrezzoGettoni(int prezzoGettoni){ this.prezzoGettoni = prezzoGettoni; }
    public int getPrezzoGettoni(){ return prezzoGettoni; }
    
    public void setDisp(int disp){ 
        this.disp = disp;
        setChanged();
        notifyObservers(disp);
    }
    public int getDisp(){ return disp; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        return "Nome: " + nome + " - Categoria: " + categoria;
    }
}
