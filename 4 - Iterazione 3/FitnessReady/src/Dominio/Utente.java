package Dominio;

import java.util.concurrent.atomic.AtomicLong;

public abstract class Utente {
    
    //----VARIABILI----
    
    protected int id;
    protected String nome, cognome, email, password;
    protected char privilegi;
    protected static final AtomicLong counter = new AtomicLong(0);
    
    //----COSTRUTTORI----
    
    public Utente(){}
    
    protected Utente(int id, String nome, String cognome, String email, String password){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }
    
    protected Utente(String nome, String cognome, String email, String password){
        setId();
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }
    
    //----GETTERS e SETTERS----
    
    //
    private void setId(){ id = (int)counter.incrementAndGet(); }
    //
    
    public void setId(int id){ this.id = id; }
    public int getId(){ return id; }
    
    public void setNome(String nome){ this.nome = nome; }
    public String getNome(){ return nome; }
    
    public void setCognome(String cognome){ this.cognome = cognome; }
    public String getCognome(){ return cognome; }
    
    public void setEmail(String email){ this.email = email; }
    public String getEmail(){ return email; }
    
    public void setPassword(String password){ this.password = password; }
    public String getPassword(){ return password; }
    
    public char getPrivilegi(){ return privilegi; }
}
