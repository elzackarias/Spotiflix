/**
 * @author elzackarias
 */
public class EmptyInputException extends Exception{
    public EmptyInputException(String message){
        super(message);
    }
    public EmptyInputException(){
        super("Por favor llena todos los campos");
    }
   
}