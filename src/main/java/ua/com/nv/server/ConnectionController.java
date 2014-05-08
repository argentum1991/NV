package ua.com.nv.server;

import org.apache.log4j.Logger;
import ua.com.nv.protocol.director.SimpleTelnetDirector;
import ua.com.nv.server.util.ClientsBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.Callable;


public class ConnectionController implements Callable<Boolean>, Sender<String> {
    private Logger log = Logger.getLogger(ConnectionController.class);

    private Socket clientSocket;
    private SimpleTelnetDirector director;
    private PrintWriter writer;

    private Set<Callable> connections;

    public ConnectionController(Socket clientSocket, Set<Callable> connections) throws IOException {
        this.clientSocket = clientSocket;

        director = new SimpleTelnetDirector(this);
        writer = new PrintWriter(clientSocket.getOutputStream());
        this.connections = connections;
    }



    @Override
    public Boolean call() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String nextRequest = "";
        director.processRequest("");
        String hello = director.getResponseMsg();
        sendMsg(DELIVERY_MODE.CALLBACK, hello);
        while ((nextRequest = reader.readLine()) != null) {
            director.processRequest(nextRequest);

            String msgResponse = director.getResponseMsg();
            DELIVERY_MODE mode = director.getMode();

            if (!msgResponse.isEmpty()) {
                sendMsg(mode, msgResponse);
            }
        }

        connections.remove(this);
        return false;
    }

    private void sendMsg(DELIVERY_MODE mode, String msg) {

        switch (mode) {
            case CALLBACK:
                sendMsg(msg);
                break;
            default:
                ClientsBook.transmitMsg(mode, msg);
        }
    }
    @Override
    public void sendMsg(String msg) {
        writer.print(msg);
        writer.flush();
    }



}
