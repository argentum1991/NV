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

    public void appendToHeader(char... symbols) {
        for (char curChar : symbols) {
            fullHeader.append(curChar);
        }
        ;
    }

    public String toString() {
        return String.format("%s %s", fullHeader, fullContent);
    }

}
