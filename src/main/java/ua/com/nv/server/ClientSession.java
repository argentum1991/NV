package ua.com.nv.server;


public class ClientSession {
    public Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isAuthenticated() {
        return client != null;
    }

    public int getStatus() {
        if (isAuthenticated()) {
            return client.getStatus();
        }
        return 0;
    }

}
