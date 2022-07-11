package in.ankitapps.expensetrackerapi.exceptions;

public class ItemsAlreadyExistsException extends RuntimeException{

    private static final long serialVersionUID=1L;

    public ItemsAlreadyExistsException(String message){
        super(message);
    }
}
