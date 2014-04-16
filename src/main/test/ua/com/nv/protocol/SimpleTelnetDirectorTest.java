package ua.com.nv.protocol;

import org.junit.Test;


public class SimpleTelnetDirectorTest {
    @Test
    public void testGetResponseMsg() throws Exception {

        SimpleTelnetDirector director = new SimpleTelnetDirector(null);

        director.processRequest("HELP:");
        String rHelp=director.getResponseMsg();

        director.processRequest("LOGIN:");
        String response1 = director.getResponseMsg();

        director.processRequest("robert");
        String response2 = director.getResponseMsg();

        director.processRequest("nayman");
        String response3 = director.getResponseMsg();

        director.processRequest("erik");
        String response4 = director.getResponseMsg();

        director.processRequest("LOGIN:");
        String response6 = director.getResponseMsg();
        director.processRequest("LOGOUT:");
        String response7 = director.getResponseMsg();

        director.processRequest("katsman");
        String response5 = director.getResponseMsg();

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
