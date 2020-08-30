package Dominio;

import Eccezioni.*;
import Gestori.*;
import java.util.*;

public class FitnessReady {
    
    //----VARIABILI----
    
    private static FitnessReady istance = null; //riferimento all'istanza di FitnessReady
    private StartUp SU;
    private List<Utente> listaUtenti;
    private Map<String, List<Acquisto>> mappaAcquistiClienti;
    private Utente utenteAttuale;
    private Catalogo catalogo;
    private GestoreAcquisto gestoreAcquisto;
    
    //----COSTRUTTORI----
            
    private FitnessReady(){
        SU = new StartUp();
        listaUtenti = SU.startUpListaUtenti();
        mappaAcquistiClienti = SU.startUpMappaAcquistiClienti();
        gestoreAcquisto = null;
        catalogo = new Catalogo(SU); //catalogo inizializzato con StartUp
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
    
    public void setCatalogo(Catalogo catalogo){ this.catalogo = catalogo; }
    public Catalogo getCatalogo(){ return catalogo; }
    
    public void setListaAcquistiCliente(List<Acquisto> listaAcquisti){
        mappaAcquistiClienti.put(utenteAttuale.getEmail(), listaAcquisti);
    }
    public List<Acquisto> getListaAcquistiCliente(){
        List<Acquisto> listaAcquisti = null;
        if(mappaAcquistiClienti.containsKey(utenteAttuale.getEmail()))
            listaAcquisti = mappaAcquistiClienti.get(utenteAttuale.getEmail());
        return listaAcquisti;
    }
    
    //----METODI DI PROGETTO----
    
    public void registraCliente(String nome, String cognome, String email, String password, String confermaPassword, String altezza, String peso) throws ExistingEmailException, PasswordNotEqualsException, EmptyFieldException {
        boolean existingEmail = false;
        List<String> textList = new LinkedList<>(Arrays.asList(
                nome, cognome, email, password, confermaPassword, altezza, peso));
        int i = textList.size();
        
        for(String text : textList)
            if(text.equals(""))
                i--;
        if(i == textList.size()){
            for(Utente u : listaUtenti)
                if(u.getEmail().equals(email))
                    existingEmail = true;
            if(!existingEmail){
                if(password.equals(confermaPassword)){
                    Cliente newCliente = new Cliente(nome, cognome, email, password, Float.parseFloat(altezza), Float.parseFloat(peso));
                    listaUtenti.add(newCliente);
                }
                else
                    throw new PasswordNotEqualsException();
            }
            else
                throw new ExistingEmailException();
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
    
    public void aggiungiAcquisto() throws GenericSystemException{
        Prodotto prodRiga, prodCatalogo; //variabili di supporto
        Acquisto newAcquisto = gestoreAcquisto.effettuaAcquisto();
        
        if(mappaAcquistiClienti.containsKey(utenteAttuale.getEmail()))
            mappaAcquistiClienti.get(utenteAttuale.getEmail()).add(newAcquisto);
        else{
            List<Acquisto> acquistiCliente = new ArrayList<>();
            acquistiCliente.add(newAcquisto);
            setListaAcquistiCliente(acquistiCliente);
            //mappaAcquistiClienti.put(utenteAttuale.getEmail(), acquistiCliente);
        }
            
        // Update del catalogo
        for(RigaAcquisto riga : newAcquisto.getRigheAcquisto()){
            prodRiga = riga.getProdotto();
            prodCatalogo = catalogo.getProdotto(prodRiga.getId());
            catalogo.getProdotto(prodRiga.getId()).setDisp(prodCatalogo.getDisp() - riga.getQuantità());
        }
    }
}
