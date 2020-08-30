package Dominio.Pasti;

import Dominio.Pasto;
import Dominio.Portata;

import java.util.*;

public class Cena extends Pasto {
    
    //----VARIABILI----
    
    //----COSTRUTTORI----
    
    public Cena(){
        super("CENA");
    }
    
    public Cena(List<Portata> elencoPortate){
        super("CENA", elencoPortate);
    }
    
    //----GETTERS e SETTERS----
}
