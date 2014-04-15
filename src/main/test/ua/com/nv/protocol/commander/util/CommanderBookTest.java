package ua.com.nv.protocol.commander.util;

import junit.framework.Assert;
import org.junit.Test;
import ua.com.nv.protocol.commander.*;


public class CommanderBookTest {
    @Test
    public void testGetCommander() throws Exception {
        /*
           commanders.put("LOGIN:", LoginCommander.class);
        commanders.put("LOGOUT:", LogoutCommander.class);
        commanders.put("PRIVATE:", PrivatecastCommander.class);
        commanders.put("BROADCAST:", BroadcastCommander.class);
        commanders.put("CONSUMERS:", ConsumersCommander.class);
        commanders.put("HELP:", HelpCommander.class);
        commanders.put("REGISTER:", RegisterCommander.class);
        commanders.put("HISTORY:", HelpCommander.class);
         */
        Assert.assertEquals(CommanderBook.getCommander("LOGIN:").getClass(), LoginCommander.class);
        Assert.assertEquals(CommanderBook.getCommander("PRIVATE:").getClass(), PrivatecastCommander.class);
        Assert.assertEquals(CommanderBook.getCommander("BROADCAST:").getClass(), BroadcastCommander.class);
        Assert.assertEquals(CommanderBook.getCommander("LOGOUT:").getClass(), LogoutCommander.class);
        Assert.assertEquals(CommanderBook.getCommander("CONSUMERS:").getClass(), ConsumersCommander.class);
        Assert.assertEquals(CommanderBook.getCommander("HELP:").getClass(), HelpCommander.class);
        Assert.assertEquals(CommanderBook.getCommander("REGISTER:").getClass(), RegisterCommander.class);




    }
}
