package Dominio;

public class RigaOrdine {
    
    //----VARIABILI----
    
    private Prodotto prod;
    private int quantità;
    
    //----COSTRUTTORI----
    
    public RigaOrdine(Prodotto prod, int quantità){
        this.prod = prod;
        this.quantità = quantità;
    }
    
    //----GETTERS e SETTERS----
    
    public void setProdotto(Prodotto prod){ this.prod = prod; }
    public Prodotto getProdotto(){ return prod; }
    
    public void setQuantità(int quantità){ this.quantità = quantità; }
    public int getQuantità(){ return quantità; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        return prod + " - QUANTITÀ: " + quantità;
    }
}
