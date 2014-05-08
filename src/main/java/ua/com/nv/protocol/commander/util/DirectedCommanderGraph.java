package ua.com.nv.protocol.commander.util;


import org._3pq.jgrapht.DirectedGraph;
import org._3pq.jgrapht.graph.DefaultDirectedGraph;

public final class DirectedCommanderGraph {
    static DirectedGraph diGraph =
            new DefaultDirectedGraph();

    /*
       HELP("list all commands"),
    CONSUMERS("list all online consumers"),
    PRIVATE("enter to private mode, your msg will be send only for selected consumer"),
    BROADCAST("enter to public mode, your msg will be broadcasted to everyone"),
    LOGOUT("logout from current profile"),
    LOGIN("enter your credentials and principals and enter to chat"),
    REGISTER("add new user "),
    SEND("send msg "),
    HISTORY("last 30 msg"),
    WELCOME("Welcome to chat!\n please try to login or register yourself.\n " +
            "Available for now commands are \n LOGIN:\n or REGISTER:\n");
     */
    static {

        diGraph.addAllVertices(CommanderBook.getCommanderAliases());




        diGraph.addEdge("LOGIN:", "LOGOUT:");
        diGraph.addEdge("BROADCAST:", "LOGOUT:");
        diGraph.addEdge("PRIVATE:", "LOGOUT:");
        diGraph.addEdge("CONSUMERS:", "LOGOUT:");

        diGraph.addEdge("LOGIN:", "LOGOUT:");
        diGraph.addEdge("BROADCAST:", "LOGOUT:");
        diGraph.addEdge("PRIVATE:", "LOGOUT:");

        diGraph.addEdge("LOGIN:", "CONSUMERS:");
        diGraph.addEdge("BROADCAST:", "CONSUMERS:");
        diGraph.addEdge("PRIVATE:", "CONSUMERS:");

        diGraph.addEdge("LOGIN:", "HISTORY:");
        diGraph.addEdge("BROADCAST:", "HISTORY:");
        diGraph.addEdge("PRIVATE:", "HISTORY:");

        diGraph.addEdge("LOGOUT:", "REGISTER:");
        diGraph.addEdge("LOGOUT:", "LOGIN:");

        diGraph.addEdge("BROADCAST:","PRIVATE:");
        diGraph.addEdge("CONSUMERS:","PRIVATE:");
        diGraph.addEdge("LOGIN:","PRIVATE:");
        diGraph.addEdge("HISTORY:","PRIVATE:");

        diGraph.addEdge("REGISTER:", "LOGIN:");

        diGraph.addEdge("WELCOME:", "LOGIN:");
        diGraph.addEdge("WELCOME:", "REGISTER:");

        diGraph.addEdge("PRIVATE:","BROADCAST:");
        diGraph.addEdge("CONSUMERS:","BROADCAST:");
        diGraph.addEdge("HISTORY:","BROADCAST:");



    }

    public static boolean isPossibleNextCommand(String prevCommand, String nextCommand) {
        return diGraph.containsEdge(prevCommand, nextCommand);
    }


}
