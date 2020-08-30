package Dominio;

public abstract class Esercizio {
    
    //----VARIABILI----
    
    protected String nome, categoria, stato;
    protected int numSerie, numRipetizioni, durata;
    
    //----COSTRUTTORI----
    
    protected Esercizio(){}
    
    protected Esercizio(String nome, String categoria, int numSerie, int numRipetizioni, int durata){
        this.nome = nome;
        this.categoria = categoria;
        this.numSerie = numSerie;
        this.numRipetizioni = numRipetizioni;
        this.durata = durata; //in min
        stato = "NON ESEGUITO";
    }
    
    //----GETTERS e SETTERS----
    
    public void setNome(String nome){ this.nome = nome; }
    public String getNome(){ return nome; }
    
    public void setCategoria(String categoria){ this.categoria = categoria; }
    public String getCategoria(){ return categoria; }
    
    public void setNumSerie(int numSerie){ this.numSerie = numSerie; }
    public int getNumSerie(){ return numSerie; }
    
    public void setNumRipetizioni(int numRipetizioni){ this.numRipetizioni = numRipetizioni; }
    public int getNumRipetizioni(){ return numRipetizioni; }
    
    public void setDurata(int durata){ this.durata = durata; }
    public int getDurata(){ return durata; }
    
    public void setStato(String stato){ this.stato = stato; }
    public String getStato(){ return stato; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        return nome + ", " + numSerie + " serie X " + numRipetizioni + " ripetizioni ------- " + stato;
    }
}
