package http.exception;

/**
 * @author lifeng
 */
public class UDTEndStreamException extends Exception {
    public UDTEndStreamException(){
        super();
    }
    public UDTEndStreamException(String message){
        super(message);
    }
}
