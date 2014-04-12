package ua.com.nv.server;

import ua.com.nv.protocol.SimpleTelnetDirector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;


class ConnectionController implements Callable<Object> {
    private ClientsBook book;
    Socket clientSocket;
    SimpleTelnetDirector director;
    PrintWriter writer;


    public ConnectionController(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        director = new SimpleTelnetDirector();
        writer = new PrintWriter(clientSocket.getOutputStream());
    }

    void sendMsg(String msg) {
        writer.print(msg);
        writer.flush();
    }

    @Override
    public Object call() throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String nextRequest = "";
        while ((nextRequest = reader.readLine()) != null) {
            String msgResponse = director.getResponseMsg(nextRequest);
            String clientAlias = director.getReceiverAlias();
            book.sendMsg(clientAlias, msgResponse);
        }
        return new Object();
    }

}
