package ua.com.nv.protocol.builder;


import ua.com.nv.protocol.commander.util.ChatCommands;

public interface MsgEnveloper2 {

    public byte[] getResponseMsg();
    public void formCommandHeader(ChatCommands header, String data);
    public void formData(byte... data);

}
