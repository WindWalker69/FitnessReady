package Dominio.Pasti;

import Dominio.Pasto;
import Dominio.Portata;

import java.util.*;

public class Colazione extends Pasto {
    
    //----VARIABILI----
    
    //----COSTRUTTORI----
    
    public Colazione(){
        super("COLAZIONE");
    }
    
    public Colazione(List<Portata> elencoPortate){
        super("COLAZIONE", elencoPortate);
    }
    
    //----GETTERS e SETTERS----
}
