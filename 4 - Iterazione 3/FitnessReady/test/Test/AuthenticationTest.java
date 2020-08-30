package Test;

import java.util.*;

import Dominio.FitnessReady;
import Dominio.Utente;
import Dominio.Cliente;
import Dominio.Amministratore;
import Eccezioni.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {
    
    private static FitnessReady FR;
    private static List<Utente> listaUtenti;
    
    @Before
    public void setUp() {
        listaUtenti = new ArrayList<>();
        listaUtenti.add(new Cliente("Mario", "Nunez", "mR_95@live.it", "ciao", (float)1.75, (float)75, "Definizione"));
        listaUtenti.add(new Amministratore("Gianfranco", "Albertini", "gianfra@gmail.com", "password"));
        FR = FitnessReady.getInstance();
        FR.setListaUtenti(listaUtenti);
    }
    
    @Test
    //NOME: Login Test
    public void loginTest(){
        assertThrows(ExistingEmailException.class, () -> {
            FR.effettuaLogin("revylaroche@yahoo.it", "ciao");
        });
        
        assertThrows(PasswordNotEqualsException.class, () -> {
            FR.effettuaLogin("mR_95@live.it", "ciiao");
        });
        
        assertThrows(EmptyFieldException.class, () -> {
            FR.effettuaLogin("", "");
        });
        
        //In JUnit 4 il messaggio opzionale in caso di fallimento del test è il primpo parametro dell'assert
        
        /**
         * Assert corretti ma inutili poiché il controllo viene fatto con gli assertThrows.
         * Si possono anche rimuovere le varibili "expectedN".
         * assertNotEquals("L'e-mail inserita non è registrata", expected1.toString());
         * assertNotEquals("Password non corretta", expected2.toString());
         * assertEquals("Riempire tutti i campi", expected3.toString());
         */
        
        try{
            char userType = FR.effettuaLogin("mR_95@live.it", "ciao");
            assertEquals('C', userType);
            
            userType = FR.effettuaLogin("gianfra@gmail.com", "password");
            assertEquals('A', userType);
        }
        catch(EmptyFieldException | ExistingEmailException | PasswordNotEqualsException e){}
    }
    
    @Test
    //NOME: Sign-In Test
    public void signInTest(){
        assertThrows(ExistingEmailException.class, () -> {
            FR.registraCliente("Mario", "Nunez", "mR_95@live.it", "ciao", "ciao", "1.75", "75", "Definizione");
        });
        
        assertThrows(PasswordNotEqualsException.class, () -> {
            FR.registraCliente("Mario", "Nunez", "IL_MARIO@live.it", "ciao", "ciiao", "1.75", "75", "Definizione");
        });
        
        assertThrows(EmptyFieldException.class, () -> {
            FR.registraCliente("", "", "", "", "", "", "", "");
        });
        
        /**
         * Stessa cosa del test sopra.
         * assertEquals("L'e-mail inserita è già registrata", expected1.toString());
         * assertEquals("I campi 'Password' e 'Conferma Password' non coincidono", expected2.toString());
         * assertEquals("Riempire tutti i campi", expected3.toString());
         */
        
        try{
            Cliente expectedClient = new Cliente(5, "Sara", "Marino", "revylaroche@yahoo.it", "ciao", (float)1.50, (float)45, "Definizione");
            FR.registraCliente("Sara", "Marino", "revylaroche@yahoo.it", "ciao", "ciao", "1.50", "45", "Definizione");
            assertEquals(expectedClient.toString(), FR.getListaUtenti().get(2).toString());
        }catch(NotPositiveNumberException | EmptyFieldException | ExistingEmailException | PasswordNotEqualsException e){}
    }
}
