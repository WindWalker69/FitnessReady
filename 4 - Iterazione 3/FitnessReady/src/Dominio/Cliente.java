package Dominio;

public class Cliente extends Utente {
    
    //----VARIABILI----
    
    private String obiettivo;
    private float altezza, peso;
    private int gettoni;
    
    //----COSTRUTTORI----
    
    public Cliente(){
        super();
        this.privilegi = 'C';
    }
    
    //----Con id manuale---
    public Cliente(int id, String nome, String cognome, String email, String password, float altezza, float peso, String obiettivo){
        super(id, nome, cognome, email, password);
        this.altezza = altezza;
        this.peso = peso;
        this.obiettivo = obiettivo;
        this.privilegi = 'C';
        gettoni = 5;
    }
    
    public Cliente(int id, String nome, String cognome, String email, String password, float altezza, float peso, String obiettivo, int gettoni){
        super(id, nome, cognome, email, password);
        this.altezza = altezza;
        this.peso = peso;
        this.obiettivo = obiettivo;
        this.privilegi = 'C';
        this.gettoni = gettoni;
    }
    
    //----Con id incrementale---
    public Cliente(String nome, String cognome, String email, String password, float altezza, float peso, String obiettivo){
        super(nome, cognome, email, password);
        this.altezza = altezza;
        this.peso = peso;
        this.obiettivo = obiettivo;
        this.privilegi = 'C';
        gettoni = 5;
    }
    
    public Cliente(String nome, String cognome, String email, String password, float altezza, float peso, String obiettivo, int gettoni){
        super(nome, cognome, email, password);
        this.altezza = altezza;
        this.peso = peso;
        this.obiettivo = obiettivo;
        this.privilegi = 'C';
        this.gettoni = gettoni;
    }
    
    //----GETTERS e SETTERS----
    
    public void setAltezza(float altezza){ this.altezza = altezza; }
    public float getAltezza(){ return altezza; }
    
    public void setPeso(float peso){ this.peso = peso; }
    public float getPeso(){ return peso; }
    
    public void setObiettivo(String obiettivo){ this.obiettivo = obiettivo; }
    public String getObiettivo(){ return obiettivo; }
    
    public void setGettoni(int gettoni){ this.gettoni = gettoni; }
    public int getGettoni(){ return gettoni; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        return id + " " + nome + " " + cognome + " " + email + " " + password + " " + altezza + " " + peso + " " + privilegi;
    }
}
