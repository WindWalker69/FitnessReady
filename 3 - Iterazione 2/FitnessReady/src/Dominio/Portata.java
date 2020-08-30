package Dominio;

public class Portata {
    
    //----VARIABILI----
    
    private String nome;
    private float grammi;
    
    //----COSTRUTTORI----
    
    public Portata(String nome, float grammi){
        this.nome = nome;
        this.grammi = grammi;
    }
    
    //----GETTERS e SETTERS----
    
    public void setNome(String nome){ this.nome = nome; }
    public String getNome(){ return nome; }
    
    public void setGrammi(float grammi){ this.grammi = grammi; }
    public float getGrammi(){ return grammi; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        return grammi + " gr di " + nome;
    }
}
