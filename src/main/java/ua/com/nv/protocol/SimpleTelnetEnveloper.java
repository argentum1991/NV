package ua.com.nv.protocol;


public class SimpleTelnetEnveloper {

    public SimpleTelnetMsg getMsg() {
        return msg;
    }

    private SimpleTelnetMsg msg;

    public void addCommandInfoHeader(Commands command){
    StringBuilder header=new StringBuilder("Use: "+command.toString()+"\n");
    header.append(command.getExplanation());
    msg.appendToHeader(header.toString());
    }

    public void addStatusMsgHeader(String status){

    }
    public void  addWelcomeUserHeader(String user){

    }
    public void addUnknownCommandHeader(String command){

    }
    public void addResponseCommandHeader(String response){

    }

    public void addMsgContent(String content){

    }

    public void setMsg(SimpleTelnetMsg msg) {
        this.msg = msg;
    }
}
