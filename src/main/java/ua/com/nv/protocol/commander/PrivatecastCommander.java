package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.builder.SimpleTelnetEnveloper;
import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.commander.util.Commands;
import ua.com.nv.server.ClientSession;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrivatecastCommander  extends AbstractCommander<SimpleTelnetEnveloper> {

    String toWhom;

    public PrivatecastCommander() {
        this.concreteCommand = Commands.PRIVATE;
    }


    @Override
    public void processRequest(String clientRequest) {
        NameAndBody nb = getClientCommandAndContent(clientRequest);
        this.enveloper.setMsg(new SimpleTelnetMsg());
        if (!nb.name.isEmpty()) {
            this.toWhom = nb.name;
        }


        String msg = nb.body;
        this.enveloper.addMsgContent(msg);
        ClientSession session = director.getSession();
        putStampOn(session);


    }



    @Override
    public String getReceiverId() {
        return toWhom;  //To change body of implemented methods use File | Settings | File Templates.
    }



    private NameAndBody getClientCommandAndContent(String request) {
        String regex = "-[A-Z]+-";//LOGOUT:, BROADCAST:, PRIVATE: and so on
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(request);
        String command = "";
        int end = 0;
        if (m.find()) {
            end = m.end();
            command = request.substring(m.start(), end);
        }
        String content = request.substring(end);
        return new NameAndBody(command, content);
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
