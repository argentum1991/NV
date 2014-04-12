package ua.com.nv.server;


import ua.com.nv.protocol.SimpleTelnetDirector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.concurrent.Callable;

public class Client {
    private ConnectionController controller;
    public String clientId;


    public Client(String clientId) {
        this.clientId = clientId;
    }

    public boolean inOnlineMode() {
        return (controller == null);
    }

    public void setOfflineMode() {
        controller = null;
    }

    public void setOnlineMode(ConnectionController controller) throws IOException {
        this.controller = controller;
    }

    public void printMsg(String msg) {
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
