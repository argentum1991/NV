package ua.com.nv.protocol;

public class SimpleTelnetMsg {
    private StringBuilder fullContent = new StringBuilder();
    private StringBuilder fullHeader = new StringBuilder();

    public SimpleTelnetMsg() {
    }

    public void appendToHeader(String msg) {
        fullHeader.append(msg);
    }

    public void appendToContent(String msg) {
        fullContent.append(msg);
    }
    public String toString(){
    return String.format("%s\n %s",fullHeader,fullContent);
    }

}
