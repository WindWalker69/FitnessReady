package Dominio.Pasti;

import Dominio.Pasto;
import Dominio.Portata;

import java.util.*;

public class Pranzo extends Pasto {
    
    //----VARIABILI----
    
    //----COSTRUTTORI----
    
    public Pranzo(){
        super("PRANZO");
    }
    
    public Pranzo(List<Portata> elencoPortate){
        super("PRANZO", elencoPortate);
    }
    
    //----GETTERS e SETTERS----
}
