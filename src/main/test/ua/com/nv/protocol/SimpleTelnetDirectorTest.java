package ua.com.nv.protocol;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 15.04.14
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
public class SimpleTelnetDirectorTest {
    @Test
    public void testGetResponseMsg() throws Exception {

        SimpleTelnetDirector director = new SimpleTelnetDirector(null);
        director.processRequest("LOGIN:");
        String response1 = director.getResponseMsg();

        director.processRequest("robert");
        String response2 = director.getResponseMsg();

        director.processRequest("nayman");
        String response3 = director.getResponseMsg();

    }

    @Test
    public void testGetReceiverId() throws Exception {

    }

    @Test
    public void testSetDataForClientRegistration() throws Exception {

    }

    @Test
    public void testGetClientCommandAndContent() throws Exception {


    }
}
