package ua.com.nv.server;

import ua.com.nv.protocol.SimpleTelnetDirector;
import ua.com.nv.server.util.ClientsBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;


class ConnectionController implements Callable<Object>, Sender<String> {

    private Socket clientSocket;
    private SimpleTelnetDirector director;
    private PrintWriter writer;


    public ConnectionController(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        director = new SimpleTelnetDirector(this);
        writer = new PrintWriter(clientSocket.getOutputStream());
    }

    @Override
    public void sendMsg(String msg) {
        writer.print(msg);
        writer.flush();
    }

    @Override
    public Object call() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String nextRequest = "";
        while ((nextRequest = reader.readLine()) != null) {
            director.processRequest(nextRequest);
            String msgResponse = director.getResponseMsg();
            if (!msgResponse.isEmpty()) {
                if (!director.getSession().isAuthenticated()) {
                    sendMsg(msgResponse);
                } else {
                    String clientId = director.getReceiverId();
                    ClientsBook.transmitMsg(clientId, msgResponse);
                }
            }

        }
        return new Object();
    }

}
