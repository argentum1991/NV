package ua.com.nv.server;

import java.io.IOException;


public interface ClientNetStatusControl<T> {

    public void setOfflineMode();
    public void setOnlineMode(Sender<T> controller) ;

}
