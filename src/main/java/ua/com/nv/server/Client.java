package ua.com.nv.server;


import java.io.IOException;

public class Client implements ClientNetStatusControl,Sender<String> {
    private ConnectionController controller;
    public String clientId;


    public Client(String clientId) {
        this.clientId = clientId;
    }

    public boolean inOnlineMode() {
        return (controller == null);
    }
    @Override
    public void setOfflineMode() {
        controller = null;
    }
    @Override
    public void setOnlineMode(ConnectionController controller) throws IOException {
        this.controller = controller;
    }
    @Override
    public void sendMsg(String msg) {
        controller.sendMsg(msg);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client that = (Client) o;

        if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return clientId != null ? clientId.hashCode() : 0;
    }


}
