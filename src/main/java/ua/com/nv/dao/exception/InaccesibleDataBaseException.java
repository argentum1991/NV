package ua.com.nv.dao.exception;


public class InaccesibleDataBaseException extends Exception {
    public InaccesibleDataBaseException(String message) {
        super(message);
    }

    public InaccesibleDataBaseException(String message, Throwable throwable) {
        super(message, throwable);
    }
    public InaccesibleDataBaseException(Throwable throwable){
        super(throwable);
    }

}
