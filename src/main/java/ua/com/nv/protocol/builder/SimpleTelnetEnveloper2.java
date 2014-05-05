package ua.com.nv.protocol.builder;


import ua.com.nv.protocol.SimpleTelnetMsg2;
import ua.com.nv.protocol.commander.util.ChatCommands;
import ua.com.nv.protocol.commander.util.ServiceCommands;

public class SimpleTelnetEnveloper2 implements MsgEnveloper2 {


    private SimpleTelnetMsg2 msg;


    @Override
    public byte[] getResponseMsg() {
        return msg.getResponseMsg();
    }

    @Override
    public void formCommandHeader(ChatCommands command, String data) {
        msg.appendContent(ServiceCommands.IAC.getCode());
        msg.appendContent(ServiceCommands.SB.getCode());
        msg.appendContent(command.getCode());
        msg.appendContent(data.getBytes());
        msg.appendContent(ServiceCommands.IAC.getCode());
        msg.appendContent(ServiceCommands.SE.getCode());
    }


    @Override
    public void formData(byte... data) {
        for (byte curData : data) {
            msg.appendContent(data);
        }

    }


}
