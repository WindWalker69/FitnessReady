package Gestori;

import Dominio.*;

public class GestoreProdotti {
    
    //----VARIABILI----
    
    private static GestoreProdotti istance = null;
    private StartUp SU;
    private Catalogo catalogo;
    
    //----COSTRUTTORI----
    
    public GestoreProdotti(){
        SU = new StartUp();
        catalogo = new Catalogo(SU); //catalogo inizializzato con StartUp
    }
    
    //----METODI GENERICI----
    
    public static GestoreProdotti getInstance(){
        if(istance == null)
            istance = new GestoreProdotti();
        return istance;
    }
    
    //----GETTERS e SETTERS----
    
    public void setCatalogo(Catalogo catalogo){ this.catalogo = catalogo; }
    public Catalogo getCatalogo(){ return catalogo; }
    
    //----METODI DI PROGETTO----
    
    //Aggiornamento catalogo in caso di nuovo acquisto
    public void aggiornaCatalogo(Acquisto newAcquisto){
        int idProd, dispProd, quantità; //variabili di supporto
        
        for(RigaAcquisto riga : newAcquisto.getRigheAcquisto()){
            quantità = riga.getQuantità();
            idProd = riga.getProdotto().getId();
            dispProd = catalogo.getProdotto(idProd).getDisp();
            catalogo.getProdotto(idProd).setDisp(dispProd - quantità);
        }
    }
}
