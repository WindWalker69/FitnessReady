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
    
    public Utente(int id, String nome, String cognome, String email, String password){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }
    
    public Utente(String nome, String cognome, String email, String password){
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
    
    protected void setId(int id){ this.id = id; }
    protected int getId(){ return id; }
    
    protected void setNome(String nome){ this.nome = nome; }
    protected String getNome(){ return nome; }
    
    protected void setCognome(String cognome){ this.cognome = cognome; }
    protected String getCognome(){ return cognome; }
    
    protected void setEmail(String email){ this.email = email; }
    protected String getEmail(){ return email; }
    
    protected void setPassword(String password){ this.password = password; }
    protected String getPassword(){ return password; }
    
    protected char getPrivilegi(){ return privilegi; }
}
