package ua.com.nv.server;


public class ClientSession {
    public String clientId;

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public boolean isAuthenticated() {
        return clientId == null;
    }

}
