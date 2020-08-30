package Dominio;

public class Amministratore extends Utente {
    
    //----COSTRUTTORI----
    
    public Amministratore(){
        super();
        this.privilegi = 'A';
    }
    
    public Amministratore(int id, String nome, String cognome, String email, String password){
        super(id, nome, cognome, email, password);
        this.privilegi = 'A';
    }
    
    public Amministratore(String nome, String cognome, String email, String password){
        super(nome, cognome, email, password);
        this.privilegi = 'A';
    }
    
    @Override
    public String toString(){
        return id + " " + nome + " " + cognome + " " + email + " " + password + " " + privilegi;
    }
}
