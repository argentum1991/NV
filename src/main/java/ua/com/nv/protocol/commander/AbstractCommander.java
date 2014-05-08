package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.builder.SimpleTelnetEnveloper;
import ua.com.nv.protocol.commander.util.ChatCommands;
import ua.com.nv.protocol.director.SessionDirector;
import ua.com.nv.server.ClientSession;
import ua.com.nv.server.DELIVERY_MODE;

import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class AbstractCommander implements Commander {
    protected boolean inProcess = false;
    protected ChatCommands concreteCommand;
    protected SimpleTelnetEnveloper enveloper = new SimpleTelnetEnveloper();
    SessionDirector director;

    @Override
    public String getResponseMsg() {
        String response = enveloper.getResponseMsg();  //To change body of implemented methods use File | Settings | File Templates.
        enveloper.setMsg(new SimpleTelnetMsg());
        return response;
    }

    public void processRequest(String clientRequest, String preparedMsg) {
        enveloper.addMsgHeader(preparedMsg);
        processRequest(clientRequest);
    }

    protected void putStampOn(ClientSession session) {
        if (session.isAuthenticated()){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
            String date = sdf.format(new Date());
            System.out.println(date);
            String stamp = String.format("AUTOR:%s  TIME:%s \r\n", session.client.getUserName(),date);
            enveloper.addMsgHeader(stamp);
        }

    }

    @Override
    public String getCommandAlias() {
        return concreteCommand.toString() + ":";
    }

    @Override
    public void setSessionDirector(SessionDirector director) {
        this.director = director;

    }

    @Override
    public boolean inProcess() {
        return inProcess;
    }

    @Override
    public boolean isBreakable() {
        return true;
    }
    @Override
    public DELIVERY_MODE getMode(){
    return DELIVERY_MODE.CALLBACK;
    }





}
