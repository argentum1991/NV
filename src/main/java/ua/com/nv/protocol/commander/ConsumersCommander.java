package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.commander.util.ChatCommands;
import ua.com.nv.server.Client;
import ua.com.nv.server.util.ClientsBook;

import java.util.Collection;

public class ConsumersCommander  extends AbstractCommander {
    public ConsumersCommander() {
        this.concreteCommand = ChatCommands.CONSUMERS;
    }

    @Override
    public void processRequest(String clientRequest) {

        Collection<Client> clients = ClientsBook.getAllClients();
        enveloper.addCommandInfoHeader(this.concreteCommand);
        for (Client curClient : clients) {
            String row;
            if (curClient.inOnlineMode()) {
                row = String.format("%s -- online\r\n", curClient.getUserName());
            } else {
                row = String.format("%s -- offline \r\n", curClient.getUserName());
            }
            enveloper.addMsgContent(row);
        }

    }


}


