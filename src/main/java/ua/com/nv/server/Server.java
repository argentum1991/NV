package ua.com.nv.server;

import org.apache.log4j.Logger;
import ua.com.nv.dao.ClientDao;
import ua.com.nv.protocol.builder.SimpleTelnetEnveloper;
import ua.com.nv.server.util.ClientsBook;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private final Logger log = Logger.getLogger(Server.class);
    Set<Callable> connections = new HashSet<Callable>();
    ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        Server server = new Server();
        server.init();
        server.receiveClients();
    }

    public void init() {


    }

    public void receiveClients() {

        try (ServerSocket socket = new ServerSocket(7000)) {
            while (true) {
                Socket nextClient = socket.accept();
                addNewClient(nextClient);
                log.info("new client added:"+socket.getLocalSocketAddress()+", total:"+connections.size());
            }
        } catch (IOException ix) {
            ix.printStackTrace();
        }
    }

    private void addNewClient(Socket client) {
        try {

            ConnectionController controller = new ConnectionController(client, connections, SimpleTelnetEnveloper.class);
            connections.add(controller);
            pool.submit(controller);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
