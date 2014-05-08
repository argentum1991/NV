package ua.com.nv.server;


public enum DELIVERY_MODE {
    BROADCAST("BROADCAST"),
    PRIVATECAST(""),
    CALLBACK("");

    DELIVERY_MODE(String receiver) {
   this.receiver=receiver;
    }

 private    String receiver;

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public String getReceiver(){
        return receiver;
    }

}
