package ua.com.nv.protocol.commander;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrivatecastCommander extends AbstractCommander {
    public PrivatecastCommander() {
        this.concreteCommand = Commands.PRIVATE;
    }

    @Override
    public void processRequest(String clientRequest) {
        String toWhomName="-[A-Z]+-";

    }

    @Override
    public String getResponseMsg() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getReceiverId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isContinue() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
    private NameAndBody getClientCommandAndContent(String request) {
        String regex ="-[A-Z]+-";//LOGOUT:, BROADCAST:, PRIVATE: and so on
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
