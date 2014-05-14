package ua.com.nv.dao;

import junit.framework.Assert;
import org.junit.Test;
import ua.com.nv.server.Client;
import ua.com.nv.server.util.ClientsBook;

import java.util.Collection;


public class ClientDaoTest {
    @Test
    public void testGetClientsName() throws Exception {

    }

    @Test
    public void testGetAssotiatedMsgWith() throws Exception {
        Client client = new Client("Katsman", "robert", 1);
        if (!ClientDao.isClientExists("Katsman")) {
            ClientDao.addClient(client);
        }
        Client client1 = ClientDao.getClientByUserName("Katsman");
        Assert.assertTrue(client1.equals(client));
        String msg = "the Kid is my most favorite song from their first album";
        ClientDao.addMsgToClient(msg, "Katsman");
        Collection<String> messages = ClientDao.getAssotiatedMsgWith("Katsman");
        Assert.assertTrue(messages.contains(msg));
        Collection<Client> clients = ClientsBook.getAllClients();
        clients.size();
        ClientsBook.onLineClients.putIfAbsent(client.getUserName(), client);
        boolean is = ClientsBook.onLineClients.containsKey(client.getUserName());
        System.out.println(client.getUserName().hashCode());
        System.out.println(new String("Katsman").hashCode());
        client.getUserName();

    }

    @Test
    public void testAddClient() throws Exception {

    }

    @Test
    public void testIsClientExists() throws Exception {

    }

    @Test
    public void testAddMsgToClient() throws Exception {

    }

    @Test
    public void testGetClientByUserName() throws Exception {

    }
}
