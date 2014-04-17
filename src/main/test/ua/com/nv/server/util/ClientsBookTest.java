package ua.com.nv.server.util;


import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.com.nv.server.Client;
import ua.com.nv.server.ConnectionController;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class ClientsBookTest {

    @Before
    public void init() {
        ClientsBook.addClient("Robert", "Berman");
    }


    @Test
    public void testAddClients() throws Exception {
        ClientsBook.addClient("Nayman", "Mayman");
        ClientsBook.addClient("Katsman", "Sissfs");


    }

    @Test
    public void testGetAllClients() throws Exception {
        Collection<Client> clients = ClientsBook.getAllClients();
        Assert.assertEquals(3, ClientsBook.getAllClients().size());
    }


    @Test
    public void testTransmitMsg() throws Exception {
        ClientsBook.addClient("Nayman", "Mayman");
        ClientsBook.addClient("Katsman", "Sissfs");
        ClientsBook.transmitMsg("Katsman", "robert speaks");
        ClientsBook.transmitMsg("Nayman", "robert speaks to Nayman");
        Assert.assertTrue(ClientsBook.getUndeliveredMsg("Nayman").contains("robert speaks to Nayman"));
        Assert.assertTrue(ClientsBook.getUndeliveredMsg("Katsman").contains("robert speaks"));

    }


    @Test
    public void testBindSenderToClient() throws Exception {
        ClientsBook.addClient("Nayman", "Mayman");
        ClientsBook.addClient("Katsman", "Sissfs");
        Set<Callable> set = new HashSet<Callable>();
        ConnectionController controller = null;

        Assert.assertEquals(ClientsBook.bindSenderToClient("Nayman", "Mayman", controller).getUserName(), "Nayman");


    }

    @Test
    public void testUnbindSenderFromClient() throws Exception {
     ClientsBook.unbindSenderFromClient("Nayman");
    }


    @Test
    public void testRedeliverMsg() throws Exception {

    }


    @Test
    public void testAddUndeliveredMsg() throws Exception {

    }
}
