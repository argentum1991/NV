package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.commander.util.ChatCommands;
import ua.com.nv.server.ClientSession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrivatecastCommander extends AbstractCommander {
    //<RECEIVER> BODY_MSG
    private static String forWhomPattern = "^<-.+?>";//start with <WHO>


    String toWhom;

    public PrivatecastCommander() {
        this.concreteCommand = ChatCommands.PRIVATE;
    }


    @Override
    public void processRequest(String clientRequest) {
        inProcess=true;
        NameAndBody nb = getClientCommandAndContent(clientRequest);
        this.enveloper.setMsg(new SimpleTelnetMsg());
        if (!nb.name.isEmpty()) {
            this.toWhom = nb.name;
        }
        String msg = nb.body;
        if (!msg.isEmpty()){
            this.enveloper.addMsgContent(msg);
            ClientSession session = director.getSession();
            putStampOn(session);
        }


    }


    @Override
    public String getReceiverId() {
        return toWhom;  //To change body of implemented methods use File | Settings | File Templates.
    }


    private NameAndBody getClientCommandAndContent(String request) {
        Pattern pattern = Pattern.compile(forWhomPattern);
        Matcher matcher = pattern.matcher(request);
        String receiver = "";
        String body = request;
        if (matcher.find()) {
            receiver = request.substring(1, matcher.end() - 1);
            body = request.substring(matcher.end() + 1, request.length());
        }
         return new NameAndBody(receiver, body);
    }

    private class NameAndBody {
        String name;
        String body;

        NameAndBody(String name, String body) {
            this.name = name;
            this.body = body;
        }
    }
}
