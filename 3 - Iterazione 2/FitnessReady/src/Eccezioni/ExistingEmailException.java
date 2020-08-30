package Eccezioni;

public class ExistingEmailException extends Exception{
    
    @Override
    public String toString(){
        return "L'e-mail inserita è già registrata";
    }
}
