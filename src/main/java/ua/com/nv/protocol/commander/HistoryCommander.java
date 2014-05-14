package ua.com.nv.protocol.commander;


import java.util.Collection;

public class HistoryCommander extends AbstractCommander {
    @Override
    public void processRequest(String request) throws UnsupportedOperationException {
        Collection<String> storedMsg=director.getStoredMsg(director.getSession().client.getUserName());
        enveloper.addMsgContent(getCommandAlias());
         enveloper.addMsgContent(storedMsg.toString());
     }
}
