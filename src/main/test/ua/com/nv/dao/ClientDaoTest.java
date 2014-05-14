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
        Client client = new Client("Robert","robert", 1);
        if (!ClientDao.isClientExists("Robert")) {
            ClientDao.addClient(client);
        }
        Client client1 = ClientDao.getClientByUserName("Robert");
        Assert.assertTrue( client1.getUserName().equals(client.getUserName()));
        String msg = "the Kid is my most favorite song from their first album";
        ClientDao.addMsgToClient(msg, "Robert");
        Collection<String> messages = ClientDao.getAssotiatedMsgWith("Robert");
        Assert.assertTrue(messages.contains(msg));
        Collection<Client> clients=ClientsBook.getAllClients();


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
