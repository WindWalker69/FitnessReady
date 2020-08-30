package Dominio;

public class RigaAcquisto {
    
    //----VARIABILI----
    
    private Prodotto prod;
    private int quantità;
    
    //----COSTRUTTORI----
    
    public RigaAcquisto(Prodotto prod, int quantità){
        this.prod = prod;
        this.quantità = quantità;
    }
    
    //----GETTERS e SETTERS----
    
    public void setQuantità(int quantità){ this.quantità = quantità; }
    public int getQuantità(){ return quantità; }
    
    public Prodotto getProdotto(){ return prod; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        return prod + " - QUANTITÀ: " + quantità;
    }
}
