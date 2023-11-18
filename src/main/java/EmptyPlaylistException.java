/**
 * @author elzackarias
 */
public class EmptyPlaylistException extends Exception{
    public EmptyPlaylistException(String message){
        super(message);
    }  
    public EmptyPlaylistException(){
        super("Aún no hay elementoss en la playlist");
    }
}
