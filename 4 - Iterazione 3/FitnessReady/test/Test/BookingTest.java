package Test;

import Dominio.*;
import Eccezioni.*;
import Gestori.GestorePrenotazioni;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class BookingTest {
    
    private static FitnessReady FR;
    private static GestorePrenotazioni GP;
    private static Cliente cliente;
    private static Prodotto prod;
    
    @BeforeClass
    public static void setUpClass() {
        FR = FitnessReady.getInstance();
        GP = FR.getGestorePrenotazioni();
        cliente = (Cliente) FR.getListaUtenti().get(0);
        try{
            FR.effettuaLogin(cliente.getEmail(), cliente.getPassword());
        }catch(EmptyFieldException | ExistingEmailException | PasswordNotEqualsException e){}
        prod = FR.getGestoreProdotti().getCatalogo().getProdotto(0);
        prod.setDisp(0);
    }
    
    @Test
    //NOME: Execute-Booking-Test
    public void executeBookingTest(){
        assertThrows(NotPositiveNumberException.class, () -> {
            GP.effettuaPrenotazione(cliente, new Date(), prod, -1, "Via Nizzeti 18", "Valverde", "95028", "Carta di credito", "0123456789012345", "000");
        });
        
        assertThrows(GenericSystemException.class, () -> {
            GP.effettuaPrenotazione(cliente, new Date(), prod, 2, "Via Nizzeti 18", "Valverde", "95028", "Gettoni", "", "");
        });
        
        try{
            Prenotazione expectedP = new Prenotazione(1, new Date(), prod, 2, prod.getPrezzo()*2, 0, "Via Nizzeti 18", "Valverde", "95028", "Carta di credito", "0123456789012345", "000");
            
            GP.effettuaPrenotazione(cliente, new Date(), prod, 2, "Via Nizzeti 18", "Valverde", "95028", "Carta di credito", "0123456789012345", "000");
            Prenotazione actualP = GP.getListaPrenotazioniCliente(cliente.getEmail()).get(0);
            
            assertEquals(expectedP.toString(), actualP.toString());
        }catch(Exception e){}
    }

    @Test
    //NOME: Update-Booking-Test
    public void updateBookingTest(){
        //FR.getGestoreProdotti().getCatalogo().getProdotto(prod.getId()).setDisp(5);
        //prod.setDisp(5);
        //System.out.println("Ciao");
        //GP.getListaPrenotazioniCliente(cliente.getEmail()).get(0).getProd().setDisp(5);
        
        try{
            GP.effettuaPrenotazione(cliente, new Date(), prod, 2, "Via Nizzeti 18", "Valverde", "95028", "Carta di credito", "0123456789012345", "000");
            Prenotazione p = GP.getListaPrenotazioniCliente(cliente.getEmail()).get(0);
            FR.getGestoreProdotti().getCatalogo().getProdotto(prod.getId()).setDisp(5);
            for(Ordine o : FR.getGestoreOrdini().getListaOrdiniCliente(cliente.getEmail()))
                System.out.println(o);
        }catch(GenericSystemException | NotPositiveNumberException e){
            System.out.println(e);
        }
    }
}
