package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.SimpleTelnetEnveloper;
import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.server.Client;
import ua.com.nv.server.util.ClientsBook;

import java.util.Collection;

public class ConsumersCommander  extends AbstractCommander<SimpleTelnetEnveloper> {
    public ConsumersCommander() {
        this.concreteCommand = Commands.CONSUMERS;
    }

    @Override
    public void processRequest(String clientRequest) {
        enveloper.setMsg(new SimpleTelnetMsg());
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

    @Override
    public String getReceiverId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}


