package ua.com.nv.server;

/**
 * Created with IntelliJ IDEA.
 * User: Land
 * Date: 13.04.14
 * Time: 9:05
 * To change this template use File | Settings | File Templates.
 */
public interface  Sender <T> {
    public void sendMsg(T message);
}
