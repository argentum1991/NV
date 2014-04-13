package ua.com.nv.protocol.commander.util;

import junit.framework.Assert;
import org.junit.Test;


public class DirectedCommanderGraphTest {
    @Test
    public void testIsPossibleNextCommand() throws Exception {
        /*
        diGraph.addEdge("CONSUMERS:", "HELP:");
         */
        /*
           commanders.put("LOGIN:", LoginCommander.class);
        commanders.put("LOGOUT:", LogoutCommander.class);
        commanders.put("PRIVATE:", PrivatecastCommander.class);
        commanders.put("BROADCAST:", BroadcastCommander.class);
        commanders.put("CONSUMERS:", ConsumersCommander.class);
        commanders.put("HELP:", HelpCommander.class);
        commanders.put("HISTORY:", HelpCommander.class);
         */
        Assert.assertTrue(DirectedCommanderGraph.isPossibleNextCommand("CONSUMERS:","HELP:"));
        Assert.assertFalse(DirectedCommanderGraph.isPossibleNextCommand("LOGOUT:", "BROADCAST:"));
        Assert.assertFalse(DirectedCommanderGraph.isPossibleNextCommand("LOGOT:", "PRIVATE:"));
        Assert.assertFalse(DirectedCommanderGraph.isPossibleNextCommand("PRIVATE:", "BROADCAST:"));
        Assert.assertFalse(DirectedCommanderGraph.isPossibleNextCommand("PRIVATE:", "REGISTER:"));
        Assert.assertFalse(DirectedCommanderGraph.isPossibleNextCommand("PRIVATE:", "REGISTER:"));
        Assert.assertFalse(DirectedCommanderGraph.isPossibleNextCommand("LOGIN:", "LOGIN:"));
    }
}
