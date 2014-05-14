package ua.com.nv.server;


public class Client implements ClientNetStatusControl<String>,Sender<String> {
    private Sender<String> sender;
    private String userName;
    private int status;

    public int getStatus(){
     return  status;
    }
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String pass;


    public Client(String userName, String pass, int status) {
        this.status=status;
        this.userName = userName;
        this.pass=pass;
    }

    public boolean inOnlineMode() {
        return (sender != null);
    }
    @Override
    public void setOfflineMode() {
        sender = null;
    }
    @Override
    public void setOnlineMode(Sender sender) {
        this.sender = sender;
    }
    @Override
    public void sendMsg(String msg) {
        sender.sendMsg(msg);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client that = (Client) o;

        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return userName != null ? userName.hashCode() : 0;
    }


}
