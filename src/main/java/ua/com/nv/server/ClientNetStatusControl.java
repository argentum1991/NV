package ua.com.nv.server;

import java.io.IOException;


public interface ClientNetStatusControl {

    public void setOfflineMode();
    public void setOnlineMode(ConnectionController controller) throws IOException ;

}
