package Dominio;

import Eccezioni.*;
import Gestori.*;
import java.util.*;

public class FitnessReady {
    
    //----VARIABILI----
    
    private static FitnessReady istance = null; //riferimento all'istanza di FitnessReady
    private final StartUp SU;
    private Utente utenteAttuale;
    private Cliente clienteAttuale;
    private Amministratore adminAttuale;
    private List<Utente> listaUtenti;
    private GestoreAcquisto gestoreAcquisto;
    private GestoreProdotti gestoreProdotti;
    private GestoreOrdini gestoreOrdini;
    private GestoreSchede gestoreSchede;
    private GestorePrenotazioni gestorePrenotazioni;
    private Map<String, List<Acquisto>> mappaAcquistiClienti;
    
    //----COSTRUTTORI----
            
    private FitnessReady(){
        SU = new StartUp();
        listaUtenti = SU.startUpListaUtenti();
        mappaAcquistiClienti = new HashMap<>(); //SU.startUpMappaAcquistiClienti();
        gestoreAcquisto = null;
        setGestoreProdotti();
        setGestoreOrdini();
        setGestoreSchede();
        setGestorePrenotazioni();
    }
    
    //----METODI GENERICI----
    
    public static FitnessReady getInstance(){
        if(istance == null)
            istance = new FitnessReady();
        return istance;
    }
    
    public void destroyGestoreAcquisto(){ gestoreAcquisto = null; } //metodo per distruggere il GestoreAcquisto usato in precedenza
    
    //----GETTERS e SETTERS----
    
    public void setListaUtenti(List<Utente> listaUtenti){ this.listaUtenti = listaUtenti; }
    public List<Utente> getListaUtenti(){ return listaUtenti; }
    
    public void setGestoreAcquisto(){ gestoreAcquisto = new GestoreAcquisto(); }
    public GestoreAcquisto getGestoreAcquisto(){ return gestoreAcquisto; }
    
    //private perché il metodo viene chiamato una sola volta nel costruttore e mai più poiché FitnessReady è singleton
    private void setGestoreProdotti(){ gestoreProdotti = GestoreProdotti.getInstance(); }
    public GestoreProdotti getGestoreProdotti(){ return gestoreProdotti; }
    
    //private perché il metodo viene chiamato una sola volta nel costruttore e mai più poiché FitnessReady è singleton
    private void setGestoreOrdini(){ gestoreOrdini = new GestoreOrdini(); }
    public GestoreOrdini getGestoreOrdini(){ return gestoreOrdini; }
    
    //private perché il metodo viene chiamato una sola volta nel costruttore e mai più poiché FitnessReady è singleton
    private void setGestoreSchede(){ gestoreSchede = new GestoreSchede(); }
    public GestoreSchede getGestoreSchede(){ return gestoreSchede; }
    
    //private perché il metodo viene chiamato una sola volta nel costruttore e mai più poiché FitnessReady è singleton
    private void setGestorePrenotazioni(){ gestorePrenotazioni = new GestorePrenotazioni(); }
    public GestorePrenotazioni getGestorePrenotazioni(){ return gestorePrenotazioni; }
    
    public Utente getUtenteAttuale(){ return utenteAttuale; }
    
    public Cliente getClienteAttuale(){ return clienteAttuale; }
    
    public Amministratore getAdminAttuale(){ return adminAttuale; }
    
    public void setMappaAcquistiClienti(Map<String, List<Acquisto>> mappaAcquistiClienti){ this.mappaAcquistiClienti = mappaAcquistiClienti; }
    public Map<String, List<Acquisto>> getMappaAcquistiClienti(){ return mappaAcquistiClienti; }
    
    public void setListaAcquistiCliente(List<Acquisto> listaAcquisti){
        mappaAcquistiClienti.put(clienteAttuale.getEmail(), listaAcquisti);
    }
    public List<Acquisto> getListaAcquistiCliente(){
        List<Acquisto> listaAcquisti = new ArrayList<>();
        if(mappaAcquistiClienti.containsKey(clienteAttuale.getEmail()))
            listaAcquisti = mappaAcquistiClienti.get(clienteAttuale.getEmail());
        return listaAcquisti;
    }
    
    //----METODI DI PROGETTO----
    
    public void registraCliente(String nome, String cognome, String email, String password, String confermaPassword, String altezza, String peso, String obiettivo) throws ExistingEmailException, PasswordNotEqualsException, EmptyFieldException {
        List<String> textList = new LinkedList<>(Arrays.asList(
                nome, cognome, email, password, confermaPassword, altezza, peso, obiettivo));
        int i = textList.size();
        
        for(String text : textList)
            if(text.equals(""))
                i--;
        if(i == textList.size()){
            for(Utente u : listaUtenti)
                if(u.getEmail().equalsIgnoreCase(email))
                    throw new ExistingEmailException();
            if(password.equals(confermaPassword)){
                Cliente newCliente = new Cliente(nome, cognome, email, password, Float.parseFloat(altezza), Float.parseFloat(peso), obiettivo);
                listaUtenti.add(newCliente);
                gestoreSchede.assegnaScheda(newCliente);
            }
            else
                throw new PasswordNotEqualsException();
        }
        else
            throw new EmptyFieldException();
    }
    
    public char effettuaLogin(String email, String password) throws ExistingEmailException, PasswordNotEqualsException, EmptyFieldException {
        boolean existingEmail = false;
        
        if(!email.equals("") && !password.equals("")){
            for(Utente u : listaUtenti)
                if(u.getEmail().equalsIgnoreCase(email)){
                    existingEmail = true;
                    utenteAttuale = u;
                    if(u instanceof Cliente)
                        clienteAttuale = (Cliente)u;
                    else if(u instanceof Amministratore)
                        adminAttuale = (Amministratore)u;
                }
            if(existingEmail)
                if(password.equals(utenteAttuale.getPassword()))
                    return utenteAttuale.getPrivilegi();
                else
                    throw new PasswordNotEqualsException();
            else
                throw new ExistingEmailException();
        }
        else
            throw new EmptyFieldException();
    }
    
    public void buyAndUpdate(Date dataEsecuzione, Date dataArrivo, String indirizzo, String città, String cap, String metodoPagamento, String numCarta, String cvv) throws GenericSystemException {
        float totaleCarrello = gestoreAcquisto.getCarrello().getTotaleCarrello(); //variabile di supporto
        int totaleGettoni = gestoreAcquisto.getCarrello().getTotaleGettoni(); //variabile di supporto
        int gettoniCliente = clienteAttuale.getGettoni();
        String emailClienteAttuale = clienteAttuale.getEmail();
        Acquisto newAcquisto;
        Ordine newOrdine;
        
        if(metodoPagamento.equals("Gettoni"))
            if(clienteAttuale.getGettoni() < totaleGettoni)
                throw new GenericSystemException();
            else
                clienteAttuale.setGettoni(gettoniCliente - totaleGettoni);
        else if(metodoPagamento.equals("Carta di credito"))
            if(totaleCarrello >= 5 && totaleCarrello < 30)
                clienteAttuale.setGettoni(gettoniCliente + 1);
            else if(totaleCarrello >= 30)
                clienteAttuale.setGettoni(gettoniCliente + 10);
        
        //Esecuzione ed aggiornamento per l'acquisto
        newAcquisto = gestoreAcquisto.effettuaAcquisto(metodoPagamento);
        if(metodoPagamento.equals("Carta di Credito") && totaleCarrello < 35)
            newAcquisto.setTotale(newAcquisto.getTotale() + 10);
        setMappaAcquistiClienti(gestoreAcquisto.aggiungiAcquisto(newAcquisto, emailClienteAttuale, mappaAcquistiClienti));
        destroyGestoreAcquisto();
        
        //Esecuzione ed aggiornamento per l'ordine
        newOrdine = gestoreOrdini.effettuaOrdine(newAcquisto, dataEsecuzione, dataArrivo, indirizzo, città, cap, metodoPagamento, numCarta, cvv);
        gestoreOrdini.aggiungiOrdine(emailClienteAttuale, newOrdine);
        gestoreProdotti.aggiornaCatalogo(newAcquisto);
    }
    
    public String visualizzaAcquisti(){
        String stringa = "";
        
        for(Acquisto acq : getListaAcquistiCliente())
            stringa += acq;
        return stringa;
    }
    
    public void modificaDati(String nome, String cognome, String email, String password, String altezza, String peso, String obiettivo) throws ExistingEmailException, EmptyFieldException {
        String oldEmail;
        List<Acquisto> listaAcquisti;
        List<Ordine> listaOrdini;
        List<String> textList = new LinkedList<>(Arrays.asList(
                nome, cognome, email, password, altezza, peso, obiettivo));
        Iterator<Utente> it = listaUtenti.iterator();
        int i = textList.size();
        
        for(String text : textList)
            if(text.equals(""))
                i--;
        if(i == textList.size()){
            for(Utente u : listaUtenti)
                if(u.getEmail().equalsIgnoreCase(email) && !email.equalsIgnoreCase(clienteAttuale.getEmail()))
                    throw new ExistingEmailException();
            
            oldEmail = clienteAttuale.getEmail();
            listaAcquisti = getListaAcquistiCliente();
            listaOrdini = gestoreOrdini.getListaOrdiniCliente(oldEmail);
            
            //Aggiornamento dati cliente
            clienteAttuale.setNome(nome);
            clienteAttuale.setCognome(cognome);
            clienteAttuale.setEmail(email);
            clienteAttuale.setPassword(password);
            clienteAttuale.setAltezza(Float.parseFloat(altezza));
            clienteAttuale.setPeso(Float.parseFloat(peso));
            clienteAttuale.setObiettivo(obiettivo);
            
            //Assegnazione scheda
            gestoreSchede.assegnaScheda(clienteAttuale);
            
            //Aggiornamento mappe e liste
            setListaAcquistiCliente(listaAcquisti);
            gestoreOrdini.setListaOrdiniCliente(email, listaOrdini);
            
            while(it.hasNext()){
                if(it.next().getEmail().equals(oldEmail)){
                    it.remove();
                    listaUtenti.add(clienteAttuale);
                }
            }
            
            mappaAcquistiClienti.remove(oldEmail);
            gestoreOrdini.getMappaOrdiniClienti().remove(oldEmail);
            gestoreSchede.getMappaSchedeClienti().remove(oldEmail);
        }
        else
            throw new EmptyFieldException();
    }
    
    public void eseguiEsercizio(int i){
        String emailCliente = clienteAttuale.getEmail();
        
        gestoreSchede.getSchedaCliente(emailCliente).getPianoEsercizi().getListaEsercizi().get(i).setStato("ESEGUITO");
    }
}
