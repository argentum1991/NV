package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.builder.SimpleTelnetEnveloper;
import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.commander.util.Commands;
import ua.com.nv.server.Client;
import ua.com.nv.server.util.ClientsBook;

import java.util.Collection;

public class ConsumersCommander  extends AbstractCommander {
    public ConsumersCommander() {
        this.concreteCommand = Commands.CONSUMERS;
    }

    @Override
    public void processRequest(String clientRequest) {
    inProcess=true;
        Collection<Client> clients = ClientsBook.getAllClients();
        enveloper.addCommandInfoHeader(this.concreteCommand);
        for (Client curClient : clients) {
            String row;
            if (curClient.inOnlineMode()) {
                row = String.format("%s -- online \n", curClient.getUserName());
            } else {
                row = String.format("%s -- offline \n", curClient.getUserName());
            }
            enveloper.addMsgContent(row);
        }

    }


}


