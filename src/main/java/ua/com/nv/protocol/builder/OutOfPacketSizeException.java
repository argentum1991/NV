package ua.com.nv.protocol.builder;


public class OutOfPacketSizeException extends RuntimeException {
    public OutOfPacketSizeException(String msg) {
        super(msg);
    }
}
