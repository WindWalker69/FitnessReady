package Dominio;

import java.util.*;

public class Catalogo {
    
    //----VARIABILI----
    
    private List<Prodotto> listaProdotti;
    
    //----COSTRUTTORI----
    
    public Catalogo(){
        this.listaProdotti = new ArrayList<>();
    }
    
    public Catalogo(StartUp SU){
        this.listaProdotti = SU.startUpListaProdotti();
    }
    
    //----GETTERS e SETTERS----
    
    public void setListaProdotti(List<Prodotto> listaProdotti){ this.listaProdotti = listaProdotti; }
    public List<Prodotto> getListaProdotti(){ return listaProdotti; }
    
    //----METODI DI PROGETTO----
    
    public Prodotto getProdotto(int idProd){
        for(Prodotto prod : listaProdotti)
            if(prod.getId() == idProd)
                return prod;
        return null;
    }
}
