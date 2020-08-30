package Dominio;

public class RigaCarrello {
    
    //----VARIABILI----
    
    private Prodotto prod;
    private int quantità;
    private float subTotale;
    private int subTotaleGettoni;
    
    //----COSTRUTTORI----
    
    public RigaCarrello(Prodotto prod, int quantità, float prezzoProd, int prezzoGettoni){
        this.prod = prod;
        this.quantità = quantità;
        subTotale = prezzoProd * quantità;
        subTotaleGettoni = prezzoGettoni * quantità;
    }
    
    //----GETTERS e SETTERS----
    
    public void setQuantità(int quantità){ this.quantità = quantità; }
    public int getQuantità(){ return quantità; }
    
    public void setSubTotale(int quantità, float prezzoProd){ subTotale = quantità * prezzoProd; }
    public float getSubTotale(){ return subTotale; }
    
    public void setSubTotaleGettoni(int quantità, int prezzoGettoni){ subTotaleGettoni = quantità * prezzoGettoni; }
    public int getSubTotaleGettoni(){ return subTotaleGettoni; }
    
    public Prodotto getProdotto(){ return prod; }
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        return prod + " " + quantità + " " + subTotale;
    }
}
