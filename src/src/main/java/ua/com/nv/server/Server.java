package ua.com.nv.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 11.04.14
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 */
public class Server {
    public static void main(String[] args) {



    }


    public void receiveClients(){

        try (ServerSocket socket = new ServerSocket(7000)) {

            while (true) {
                Socket nextClient = socket.accept();

            }

        } catch (IOException ix) {
        ix.printStackTrace();
        }
    }

}
