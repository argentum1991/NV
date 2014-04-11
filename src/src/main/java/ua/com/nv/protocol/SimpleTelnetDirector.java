package ua.com.nv.protocol;


public class SimpleTelnetDirector {
    SimpleTelnetEnveloper enveloper;

    public SimpleTelnetDirector() {
        enveloper = new SimpleTelnetEnveloper();
    }

    public String getResponseMsg(String clientRequest) {
        enveloper.setMsg(new SimpleTelnetMsg());
        return enveloper.getMsg().toString();
    }


    public String getReceiverAlias() {
        return "";
    }


}
