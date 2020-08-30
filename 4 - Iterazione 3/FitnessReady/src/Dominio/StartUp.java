package Dominio;

import Dominio.Esercizi.*;
import Dominio.Pasti.*;

import java.util.*;

public class StartUp {
    
    public StartUp(){}
    
    public List<Utente> startUpListaUtenti(){
        List<Utente> listaUtenti = new ArrayList<>();
        
        listaUtenti.add(new Cliente("Mario", "Nunez", "mr_95@live.it", "password", (float)1.75, (float)75, "Definizione"));
        listaUtenti.add(new Cliente("Sara", "Marino", "revylaroche@yahoo.it", "ciao", (float)1.50, (float)45, "Definizione"));
        listaUtenti.add(new Amministratore("Orazio", "Tomarchio", "orazio.tomarchio@unict.it", "password"));
        
        return listaUtenti;
    }
    
    public List<Prodotto> startUpListaProdotti(){
        List<Prodotto> listaProdotti = new ArrayList<>();
        
        listaProdotti.add(new Prodotto("Oatbakes", "ALIMENTI", (float)17.49, (int)30, (int)10));
        listaProdotti.add(new Prodotto("Impact Whey Protein (250 gr)", "INTEGRATORI", (float)7.99, (int)12, (int)5));
        listaProdotti.add(new Prodotto("Crema Proteica", "ALIMENTI", (float)9.99, (int)17, (int)3));
        listaProdotti.add(new Prodotto("Multivitaminicio Alpha Men (120 Compresse)", "INTEGRATORI", (float)11.96, (int)26, (int)0));
        
        return listaProdotti;
    }
    
    public List<Promozione> startUpListaPromozioni(){
        List<Promozione> listaPromozioni = new ArrayList<>();
        
        listaPromozioni.add(new Promozione("Promozione 1", "ALIMENTI", (float)20));
        
        return listaPromozioni;
    }
    
    public Map<String, List<Acquisto>> startUpMappaAcquistiClienti(){
        Map<String, List<Acquisto>> mappaAcquistiClienti = new HashMap<>();
        List<Acquisto> listaAcquisti = new ArrayList<>();
        List<RigaAcquisto> righeAcquisto = new ArrayList<>();
        
        Prodotto prod = new Prodotto("Oatbakes", "ALIMENTI", (float)17.49, (int)30, (int)10);
        RigaAcquisto riga = new RigaAcquisto(prod, 1);
        righeAcquisto.add(riga);
        
        Date dataAcquisto = new Date();
        float totaleAcquisto = prod.getPrezzo()*riga.getQuantità();
        Acquisto newAcquisto = new Acquisto(dataAcquisto, totaleAcquisto, 0);
        newAcquisto.setRigheAcquisto(righeAcquisto);
        listaAcquisti.add(newAcquisto);
        
        mappaAcquistiClienti.put("mR_95@live.it", listaAcquisti);
        mappaAcquistiClienti.put("revylaroche@yahoo.it", listaAcquisti);
        
        return mappaAcquistiClienti;
    }
    
    public Map<String, Scheda> startUpMappaSchedeClienti(List<Scheda> listaSchede){
        Map<String, Scheda> mappaSchedeClienti = new HashMap<>();
        
        mappaSchedeClienti.put("mr_95@live.it", listaSchede.get(25));
        mappaSchedeClienti.put("revylaroche@yahoo.it", listaSchede.get(20));
        
        return mappaSchedeClienti;
    }
    
    public List<Scheda> startUpListaSchede(){
        List<Scheda> listaSchede = new ArrayList<>();
        List<Portata> elencoPortate;
        List<Pasto> listaPasti;
        List<Esercizio> listaEsercizi;
        
        Scheda scheda;
        Dieta dieta;
        PianoEsercizi pianoEsercizi;
        Colazione colazione;
        Pranzo pranzo;
        Cena cena;
        Portata portata1, portata2, portata3;
        
        //------------------------SCHEDA MASSA 1----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte scremato", (float)300);
        portata2 = new Portata("Fette Biscottate", (float)30);
        portata3 = new Portata("Marmellata", (float)25);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso integrale", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Pesce azzurro", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                new EsercizioGambe("Ponte su gamba", 4, 10),
                new EsercizioGambe("Mountain climber", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10),
                
                new EsercizioTorace("Panca piana", 4, 10),
                new EsercizioTorace("Croci con manubri", 4, 10),
                
                new EsercizioDorsali("Reverse fly", 4, 8),
                new EsercizioDorsali("Shoulder press cuban", 4, 8),
                
                new EsercizioBraccia("Hammer curl", 4, 8)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 1", (float)0, (float)1.44, (float)40, (float)60, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 2----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Albume d'uovo", (float)150);
        portata2 = new Portata("Gallette di farro", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta al pomodoro", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Bresaola", (float)150);
        portata2 = new Portata("Pane integrale", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 15),
                new EsercizioCardio("HIIT", 45),
                
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 2", (float)0, (float)1.44, (float)61, (float)80, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 3----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Tacchino", (float)100);
        portata2 = new Portata("Gallette di farro", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta con verdure", (float)100);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Patate", (float)120);
        portata2 = new Portata("Fagioli con verdure", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 60),
                new EsercizioCardio("Bicicletta", 30),
                new EsercizioCardio("Aerobica", 45)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 3", (float)0, (float)1.44, (float)81, (float)110, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 4----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte", (float)200);
        portata2 = new Portata("Cereali integrali", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso con zucca", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Bresaola", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Camminata", 60),
                new EsercizioCardio("Zumba", 60)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 4", (float)0, (float)1.44, (float)111, (float)500, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 5----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte scremato", (float)300);
        portata2 = new Portata("Fette Biscottate", (float)30);
        portata3 = new Portata("Marmellata", (float)25);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso integrale", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Pesce azzurro", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                new EsercizioGambe("Ponte su gamba", 4, 10),
                new EsercizioGambe("Mountain climber", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10),
                
                new EsercizioTorace("Panca piana", 4, 10),
                new EsercizioTorace("Croci con manubri", 4, 10),
                
                new EsercizioDorsali("Reverse fly", 4, 8),
                new EsercizioDorsali("Shoulder press cuban", 4, 8),
                
                new EsercizioBraccia("Hammer curl", 4, 8)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 5", (float)1.45, (float)1.65, (float)40, (float)60, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 6----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Albume d'uovo", (float)150);
        portata2 = new Portata("Gallette di farro", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta al pomodoro", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Bresaola", (float)150);
        portata2 = new Portata("Pane integrale", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 15),
                new EsercizioCardio("HIIT", 45),
                
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 6", (float)1.45, (float)1.65, (float)61, (float)80, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 7----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Tacchino", (float)100);
        portata2 = new Portata("Gallette di farro", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta con verdure", (float)100);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Patate", (float)120);
        portata2 = new Portata("Fagioli con verdure", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 60),
                new EsercizioCardio("Bicicletta", 30),
                new EsercizioCardio("Aerobica", 45)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 7", (float)1.45, (float)1.65, (float)81, (float)110, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 8----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte", (float)200);
        portata2 = new Portata("Cereali integrali", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso con zucca", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Bresaola", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Camminata", 60),
                new EsercizioCardio("Zumba", 60)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 8", (float)1.45, (float)1.65, (float)111, (float)500, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 9----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte scremato", (float)300);
        portata2 = new Portata("Fette Biscottate", (float)30);
        portata3 = new Portata("Marmellata", (float)25);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso integrale", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Pesce azzurro", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                new EsercizioGambe("Ponte su gamba", 4, 10),
                new EsercizioGambe("Mountain climber", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10),
                
                new EsercizioTorace("Panca piana", 4, 10),
                new EsercizioTorace("Croci con manubri", 4, 10),
                
                new EsercizioDorsali("Reverse fly", 4, 8),
                new EsercizioDorsali("Shoulder press cuban", 4, 8),
                
                new EsercizioBraccia("Hammer curl", 4, 8)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 9", (float)1.66, (float)1.85, (float)40, (float)60, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 10----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Albume d'uovo", (float)150);
        portata2 = new Portata("Gallette di farro", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta al pomodoro", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Bresaola", (float)150);
        portata2 = new Portata("Pane integrale", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 15),
                new EsercizioCardio("HIIT", 45),
                
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 10", (float)1.66, (float)1.85, (float)61, (float)80, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 11----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Tacchino", (float)100);
        portata2 = new Portata("Gallette di farro", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta con verdure", (float)100);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Patate", (float)120);
        portata2 = new Portata("Fagioli con verdure", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 60),
                new EsercizioCardio("Bicicletta", 30),
                new EsercizioCardio("Aerobica", 45)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 11", (float)1.66, (float)1.85, (float)81, (float)110, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 12----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte", (float)200);
        portata2 = new Portata("Cereali integrali", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso con zucca", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Bresaola", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Camminata", 60),
                new EsercizioCardio("Zumba", 60)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 12", (float)1.66, (float)1.85, (float)111, (float)500, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 13----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte scremato", (float)300);
        portata2 = new Portata("Fette Biscottate", (float)30);
        portata3 = new Portata("Marmellata", (float)25);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso integrale", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Pesce azzurro", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                new EsercizioGambe("Ponte su gamba", 4, 10),
                new EsercizioGambe("Mountain climber", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10),
                
                new EsercizioTorace("Panca piana", 4, 10),
                new EsercizioTorace("Croci con manubri", 4, 10),
                
                new EsercizioDorsali("Reverse fly", 4, 8),
                new EsercizioDorsali("Shoulder press cuban", 4, 8),
                
                new EsercizioBraccia("Hammer curl", 4, 8)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 13", (float)1.86, (float)3, (float)40, (float)60, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 14----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Albume d'uovo", (float)150);
        portata2 = new Portata("Gallette di farro", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta al pomodoro", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Bresaola", (float)150);
        portata2 = new Portata("Pane integrale", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 15),
                new EsercizioCardio("HIIT", 45),
                
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 14", (float)1.86, (float)3, (float)61, (float)80, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 15----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Tacchino", (float)100);
        portata2 = new Portata("Gallette di farro", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta con verdure", (float)100);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Patate", (float)120);
        portata2 = new Portata("Fagioli con verdure", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 60),
                new EsercizioCardio("Bicicletta", 30),
                new EsercizioCardio("Aerobica", 45)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 15", (float)1.86, (float)3, (float)81, (float)110, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA MASSA 16----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte", (float)200);
        portata2 = new Portata("Cereali integrali", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso con zucca", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Bresaola", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Camminata", 60),
                new EsercizioCardio("Zumba", 60)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA MASSA 16", (float)1.86, (float)3, (float)111, (float)500, "Aumento", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //----------------------SCHEDA DEFINIZIONE 1----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte scremato", (float)200);
        portata2 = new Portata("Fette biscottate integrali", (float)30);
        portata3 = new Portata("Marmellata", (float)25);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso integrale", (float)80);
        portata2 = new Portata("Parmigiano", (float)10);
        portata3 = new Portata("Petto di pollo", (float)150);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Pesce azzurro", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                new EsercizioGambe("Ponte su gamba", 4, 10),
                new EsercizioGambe("Mountain climber", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10),
                
                new EsercizioTorace("Panca piana", 4, 10),
                new EsercizioTorace("Croci con manubri", 4, 10),
                
                new EsercizioDorsali("Reverse fly", 4, 8),
                new EsercizioDorsali("Shoulder press cuban", 4, 8),
                
                new EsercizioBraccia("Hammer curl", 4, 8)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 1", (float)0, (float)1.44, (float)40, (float)60, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA DEFINIZIONE 2----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Albume d'uovo", (float)150);
        portata2 = new Portata("Gallette di farro", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta al pomodoro", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        portata3 = new Portata("Tonno", (float)100);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Bresaola", (float)150);
        portata2 = new Portata("Pane integrale", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 15),
                new EsercizioCardio("HIIT", 45),
                
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 2", (float)0, (float)1.44, (float)61, (float)80, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA DEFINIZIONE 3----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Tacchino", (float)100);
        portata2 = new Portata("Gallette di riso", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta con verdure", (float)100);
        portata2 = new Portata("Lonza di maiale", (float)150);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Patate", (float)120);
        portata2 = new Portata("Fagioli con verdure", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 60),
                new EsercizioCardio("Bicicletta", 30),
                new EsercizioCardio("Aerobica", 45)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 3", (float)0, (float)1.44, (float)81, (float)110, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA DEFINIZIONE 4----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte scremato", (float)200);
        portata2 = new Portata("Cereali integrali", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso con zucca", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        portata3 = new Portata("Merluzzo", (float)150);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Bresaola", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Camminata", 60),
                new EsercizioCardio("Zumba", 60)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 4", (float)0, (float)1.44, (float)111, (float)500, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //----------------------SCHEDA DEFINIZIONE 5----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte scremato", (float)200);
        portata2 = new Portata("Fette biscottate integrali", (float)30);
        portata3 = new Portata("Marmellata", (float)25);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso integrale", (float)80);
        portata2 = new Portata("Parmigiano", (float)10);
        portata3 = new Portata("Petto di pollo", (float)150);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Pesce azzurro", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                new EsercizioGambe("Ponte su gamba", 4, 10),
                new EsercizioGambe("Mountain climber", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10),
                
                new EsercizioTorace("Panca piana", 4, 10),
                new EsercizioTorace("Croci con manubri", 4, 10),
                
                new EsercizioDorsali("Reverse fly", 4, 8),
                new EsercizioDorsali("Shoulder press cuban", 4, 8),
                
                new EsercizioBraccia("Hammer curl", 4, 8)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 5", (float)1.45, (float)1.65, (float)40, (float)60, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA DEFINIZIONE 6----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Albume d'uovo", (float)150);
        portata2 = new Portata("Gallette di farro", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta al pomodoro", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        portata3 = new Portata("Tonno", (float)100);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Bresaola", (float)150);
        portata2 = new Portata("Pane integrale", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 15),
                new EsercizioCardio("HIIT", 45),
                
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 6", (float)1.45, (float)1.65, (float)61, (float)80, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA DEFINIZIONE 7----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Tacchino", (float)100);
        portata2 = new Portata("Gallette di riso", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta con verdure", (float)100);
        portata2 = new Portata("Lonza di maiale", (float)150);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Patate", (float)120);
        portata2 = new Portata("Fagioli con verdure", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 60),
                new EsercizioCardio("Bicicletta", 30),
                new EsercizioCardio("Aerobica", 45)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 7", (float)1.45, (float)1.65, (float)81, (float)110, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA DEFINIZIONE 8----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte scremato", (float)200);
        portata2 = new Portata("Cereali integrali", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso con zucca", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        portata3 = new Portata("Merluzzo", (float)150);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Bresaola", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Camminata", 60),
                new EsercizioCardio("Zumba", 60)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 8", (float)1.45, (float)1.65, (float)111, (float)500, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //----------------------SCHEDA DEFINIZIONE 9----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte scremato", (float)200);
        portata2 = new Portata("Fette biscottate integrali", (float)30);
        portata3 = new Portata("Marmellata", (float)25);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso integrale", (float)80);
        portata2 = new Portata("Parmigiano", (float)10);
        portata3 = new Portata("Petto di pollo", (float)150);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Pesce azzurro", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                new EsercizioGambe("Ponte su gamba", 4, 10),
                new EsercizioGambe("Mountain climber", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10),
                
                new EsercizioTorace("Panca piana", 4, 10),
                new EsercizioTorace("Croci con manubri", 4, 10),
                
                new EsercizioDorsali("Reverse fly", 4, 8),
                new EsercizioDorsali("Shoulder press cuban", 4, 8),
                
                new EsercizioBraccia("Hammer curl", 4, 8)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 9", (float)1.66, (float)1.85, (float)40, (float)60, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA DEFINIZIONE 10----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Albume d'uovo", (float)150);
        portata2 = new Portata("Gallette di farro", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta al pomodoro", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        portata3 = new Portata("Tonno", (float)100);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Bresaola", (float)150);
        portata2 = new Portata("Pane integrale", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 15),
                new EsercizioCardio("HIIT", 45),
                
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 10", (float)1.66, (float)1.85, (float)61, (float)80, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA DEFINIZIONE 11----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Tacchino", (float)100);
        portata2 = new Portata("Gallette di riso", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta con verdure", (float)100);
        portata2 = new Portata("Lonza di maiale", (float)150);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Patate", (float)120);
        portata2 = new Portata("Fagioli con verdure", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 60),
                new EsercizioCardio("Bicicletta", 30),
                new EsercizioCardio("Aerobica", 45)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 11", (float)1.66, (float)1.85, (float)81, (float)110, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA DEFINIZIONE 12----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte scremato", (float)200);
        portata2 = new Portata("Cereali integrali", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso con zucca", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        portata3 = new Portata("Merluzzo", (float)150);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Bresaola", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Camminata", 60),
                new EsercizioCardio("Zumba", 60)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 12", (float)1.66, (float)1.85, (float)111, (float)500, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //----------------------SCHEDA DEFINIZIONE 13----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte scremato", (float)200);
        portata2 = new Portata("Fette biscottate integrali", (float)30);
        portata3 = new Portata("Marmellata", (float)25);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso integrale", (float)80);
        portata2 = new Portata("Parmigiano", (float)10);
        portata3 = new Portata("Petto di pollo", (float)150);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Pesce azzurro", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                new EsercizioGambe("Ponte su gamba", 4, 10),
                new EsercizioGambe("Mountain climber", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10),
                
                new EsercizioTorace("Panca piana", 4, 10),
                new EsercizioTorace("Croci con manubri", 4, 10),
                
                new EsercizioDorsali("Reverse fly", 4, 8),
                new EsercizioDorsali("Shoulder press cuban", 4, 8),
                
                new EsercizioBraccia("Hammer curl", 4, 8)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 13", (float)1.86, (float)3, (float)40, (float)60, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA DEFINIZIONE 14----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Albume d'uovo", (float)150);
        portata2 = new Portata("Gallette di farro", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta al pomodoro", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        portata3 = new Portata("Tonno", (float)100);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Bresaola", (float)150);
        portata2 = new Portata("Pane integrale", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 15),
                new EsercizioCardio("HIIT", 45),
                
                new EsercizioGambe("Squat", 4, 10),
                new EsercizioGambe("Affondi", 4, 10),
                
                new EsercizioAddome("Foot crunch", 4, 20),
                new EsercizioAddome("Crunch inversi", 4, 20),
                new EsercizioAddome("Crunch obliqui", 4, 20),
                new EsercizioAddome("Pendolo", 4, 20),
                
                new EsercizioSpalle("Distensioni con manubri", 4, 10),
                new EsercizioSpalle("Alzate laterali", 4, 10)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 14", (float)1.86, (float)3, (float)61, (float)80, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA DEFINIZIONE 15----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Tacchino", (float)100);
        portata2 = new Portata("Gallette di riso", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Pasta con verdure", (float)100);
        portata2 = new Portata("Lonza di maiale", (float)150);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Patate", (float)120);
        portata2 = new Portata("Fagioli con verdure", (float)80);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Corsa", 60),
                new EsercizioCardio("Bicicletta", 30),
                new EsercizioCardio("Aerobica", 45)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 15", (float)1.86, (float)3, (float)81, (float)110, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        //------------------------SCHEDA DEFINIZIONE 16----------------------------
        
        //----COLAZIONE----
        portata1 = new Portata("Latte scremato", (float)200);
        portata2 = new Portata("Cereali integrali", (float)30);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2
        ));
        
        colazione = new Colazione(elencoPortate);
        
        //----PRANZO----
        portata1 = new Portata("Riso con zucca", (float)100);
        portata2 = new Portata("Parmigiano", (float)10);
        portata3 = new Portata("Merluzzo", (float)150);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        pranzo = new Pranzo(elencoPortate);
        
        //----CENA----
        portata1 = new Portata("Pane integrale", (float)80);
        portata2 = new Portata("Bresaola", (float)150);
        portata3 = new Portata("Insalata", (float)250);
        elencoPortate = new ArrayList<>(Arrays.asList(
                portata1, portata2, portata3
        ));
        
        cena = new Cena(elencoPortate);
        
        //----DIETA----
        listaPasti = new ArrayList<>(Arrays.asList(
                colazione, pranzo, cena
        ));
        
        dieta = new Dieta(listaPasti);
        
        //----PIANO ESERCIZI----
        listaEsercizi = new ArrayList<>(Arrays.asList(
                new EsercizioCardio("Camminata", 60),
                new EsercizioCardio("Zumba", 60)
        ));
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        //Aggiunta scheda
        scheda = new Scheda("SCHEDA DEFINIZIONE 16", (float)1.86, (float)3, (float)111, (float)500, "Definizione", dieta, pianoEsercizi);
        listaSchede.add(scheda);
        
        return listaSchede;
    }
}
