package Test;

import java.util.*;

import Dominio.*;
import Gestori.GestoreAcquisto;
import Eccezioni.*;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

public class PurchaseTest {
    
    private static FitnessReady FR;
    private static GestoreAcquisto GA;
    private static Prodotto prod;
    
    @BeforeClass
    public static void setUpClass() {
        FR = FitnessReady.getInstance();
        prod = new Prodotto("Oatbakes", "ALIMENTI", (float)17.49, (int)30, (int)10);
    }
    
    @Before
    public void setUp() {
        FR.setGestoreAcquisto();
        GA = FR.getGestoreAcquisto();
    }
    
    @Test
    //NOME: Add-To-Cart Test
    public void addToCartTest(){
        assertThrows(GenericSystemException.class, () -> {
            GA.aggiungiAlCarrello(new Prodotto("Oatbakes", "ALIMENTI", (float)17.49, (int)30, (int)0), 0);
        });
        
        assertThrows(NotPositiveNumberException.class, () -> {
            GA.aggiungiAlCarrello(new Prodotto("Oatbakes", "ALIMENTI", (float)17.49, (int)30, (int)10), 0);
        });
        
        assertThrows(Exception.class, () -> {
            GA.aggiungiAlCarrello(new Prodotto("Oatbakes", "ALIMENTI", (float)17.49, (int)30, (int)10), 11);
        });
        
        try{            
            boolean notAlreadyHave = GA.aggiungiAlCarrello(prod, 5);
            assertTrue(notAlreadyHave);
            
            notAlreadyHave = GA.aggiungiAlCarrello(prod, 5);
            assertFalse(notAlreadyHave);
        }catch(Exception e){}
    }
    
    @Test
    //NOME: Execute-Purchase Test
    public void executePurchaseTest(){
        try{
            List<RigaAcquisto> expectedRigheAcquisto = new ArrayList<>(Arrays.asList(new RigaAcquisto(prod, 1)));
            Acquisto expectedAcquisto = new Acquisto(2, new Date(), (float)17.49, 0, expectedRigheAcquisto); //id = 2 poiché con StartUp si ha già un acquisto in lista
            
            GA.aggiungiAlCarrello(prod, 1); //Aggiunta necessaria per via del setUp
            Acquisto actualAcquisto = GA.effettuaAcquisto("Carta di credito");
            
            assertEquals(expectedAcquisto.toString(), actualAcquisto.toString());
        }catch(Exception e){}
    }
}
