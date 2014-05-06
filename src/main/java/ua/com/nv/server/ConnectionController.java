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
    private int socketLocalPort;
    private Set<Callable> connections;

    public ConnectionController(Socket clientSocket, Set<Callable> connections) throws IOException {
        this.clientSocket = clientSocket;
        socketLocalPort = clientSocket.getLocalPort();
        director = new SimpleTelnetDirector(this);
        writer = new PrintWriter(clientSocket.getOutputStream());
        this.connections = connections;
    }

    @Override
    public void sendMsg(String msg) {
        writer.print(msg);
        writer.flush();
    }

    @Override
    public Boolean call() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String nextRequest = "";
        director.processRequest("");
        String hello = director.getResponseMsg();
        sendMsg(hello);
        while ((nextRequest = reader.readLine()) != null) {
            director.processRequest(nextRequest);

            String msgResponse = director.getResponseMsg();
            log.info("MsgResponse:" + msgResponse);
            if (!msgResponse.isEmpty()) {
                if (!director.getSession().isAuthenticated()) {
                    sendMsg(msgResponse);
                } else {
                    String clientId = director.getReceiverId();
                    if (clientId != null) {

                        log.info("ReceiverId:" + clientId);

                        ClientsBook.transmitMsg(clientId, msgResponse);
                    } else {
                        sendMsg(msgResponse);
                    }
                }

            }
        }
        connections.remove(this);
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectionController that = (ConnectionController) o;

        if (socketLocalPort != that.socketLocalPort) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return socketLocalPort;
    }

}
