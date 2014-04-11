package ua.com.nv.server;


import ua.com.nv.protocol.SimpleTelnetDirector;
import ua.com.nv.protocol.SimpleTelnetEnveloper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ClientController implements Callable<Object> {
    Socket client;
    SimpleTelnetDirector director;
    public ClientController(Socket client) {
        this.client = client;
        director=new SimpleTelnetDirector();
    }


    @Override
    public Object call() throws Exception {
        BufferedReader reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter writer=new PrintWriter(client.getOutputStream());
        String nextRequest="";
        while (( nextRequest=reader.readLine())!=null){

        String msgResponse=director.getResponseMsg(nextRequest);

        }
    }
}
