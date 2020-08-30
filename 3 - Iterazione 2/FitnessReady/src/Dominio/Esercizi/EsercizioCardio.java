package Dominio.Esercizi;

import Dominio.Esercizio;

public class EsercizioCardio extends Esercizio {
    
    //----VARIABILI----
    
    //----COSTRUTTORI----
    
    public EsercizioCardio(String nome, int durata){
        this.nome = nome;
        this.durata = durata;
        this.categoria = "CARDIO";
        this.stato = "NON ESEGUITO";
    }
    
    //----GETTERS e SETTERS----
    
    //----METODI GENERICI----
    
    @Override
    public String toString(){
        return nome + " X " + durata + " minuti ------- " + stato;
    }
}
