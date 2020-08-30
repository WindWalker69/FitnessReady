package Eccezioni;

public class PasswordNotEqualsException extends Exception{
    
    @Override
    public String toString(){
        return "I campi 'Password' e 'Conferma Password' non coincidono";
    }
}
