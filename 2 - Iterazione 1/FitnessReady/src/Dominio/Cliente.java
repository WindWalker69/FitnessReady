package Dominio;

public class Cliente extends Utente {
    
    //----VARIABILI----
    
    private float altezza, peso;
    private int gettoni;
    
    //----COSTRUTTORI----
    
    public Cliente(){
        super();
        this.privilegi = 'C';
    }
    
    public Cliente(int id, String nome, String cognome, String email, String password, float altezza, float peso){
        super(id, nome, cognome, email, password);
        this.altezza = altezza;
        this.peso = peso;
        this.privilegi = 'C';
        gettoni = 5;
    }
    
    public Cliente(String nome, String cognome, String email, String password, float altezza, float peso){
        super(nome, cognome, email, password);
        this.altezza = altezza;
        this.peso = peso;
        this.privilegi = 'C';
        gettoni = 5;
    }
    
    //----GETTERS e SETTERS----
    
    public void setAltezza(float altezza){ this.altezza = altezza; }
    public float getAltezza(){ return altezza; }
    
    public void setPeso(float peso){ this.peso = peso; }
    public float getPeso(){ return peso; }
    
    public void setGettoni(int gettoni){ this.gettoni = gettoni; }
    public int getGettoni(){ return gettoni; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        return id + " " + nome + " " + cognome + " " + email + " " + password + " " + altezza + " " + peso + " " + privilegi;
    }
}
