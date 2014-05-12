package ua.com.nv.server;

import org.apache.log4j.Logger;
import ua.com.nv.protocol.builder.MsgEnveloper;
import ua.com.nv.protocol.commander.DELIVERY_MODE;
import ua.com.nv.protocol.director.MsgDirector;
import ua.com.nv.protocol.director.SimpleTelnetDirector;
import ua.com.nv.server.util.ClientsBook;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.Callable;


public class ConnectionController<T extends MsgEnveloper> implements Callable<Boolean>, Sender<String> {
    private Logger log = Logger.getLogger(ConnectionController.class);

    private Socket clientSocket;
    private MsgDirector director;
    private PrintWriter writer;
    private T enveloper;

    private Set<Callable> connections;

    public ConnectionController(Socket clientSocket, Set<Callable> connections, Class<T> enveloperClass) throws Exception {
        this.clientSocket = clientSocket;
        enveloper = enveloperClass.newInstance();
        director=new SimpleTelnetDirector(this);
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
            log.info("request--" + nextRequest);
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
                if (!ClientsBook.transmitMsg(mode, msg)) {
                    enveloper.addUnknownReceiverHeader(mode.getReceiver());
                    sendMsg(enveloper.getResponseMsg());
                }

        }
    }

    @Override
    public void sendMsg(String msg) {
        writer.print(msg);
        writer.flush();
    }


}
