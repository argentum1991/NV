package ua.com.nv.protocol;

public class SimpleTelnetMsg {
    private StringBuilder fullContent = new StringBuilder();
    private StringBuilder fullHeader = new StringBuilder();

    public SimpleTelnetMsg() {

    }

    public void appendToContent(String content) {
        fullContent.append(content + "\r\n");
    }

    public void appendToHeader(String header) {
        fullHeader.append(header + "\r\n");
    }

    public String toString() {
        return String.format("%s\r\n%s", fullHeader, fullContent);
    }


}
