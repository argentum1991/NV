package ua.com.nv.server;

import ua.com.nv.dao.ClientDao;
import ua.com.nv.server.util.ClientsBook;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 11.04.14
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 */
public class Server {
    List<Callable> connections = new ArrayList<>();
    ClientsBook book = new ClientsBook();

    public static void main(String[] args) {


    }

    public void init() {
        List<String> clients = ClientDao.getClientsId();
        book.addClients(clients);

    }

    public void receiveClients() {

        try (ServerSocket socket = new ServerSocket(7000)) {

            while (true) {
                Socket nextClient = socket.accept();
                addNewClient(nextClient);
            }

        } catch (IOException ix) {
            ix.printStackTrace();
        }
    }

    private void addNewClient(Socket client) {
        try {

            ConnectionController controller = new ConnectionController(client);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
