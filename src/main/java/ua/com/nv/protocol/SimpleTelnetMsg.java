package ua.com.nv.protocol;

public class SimpleTelnetMsg {
    private StringBuilder fullContent = new StringBuilder();
    private StringBuilder fullHeader = new StringBuilder();

    public SimpleTelnetMsg(){

    }

    public void appendToContent(String content) {
    fullContent.append(content);
    }

    public void appendToHeader(String header) {
        fullHeader.append(header);
    }

    public String toString() {
        return String.format("%s\n\n%s", fullHeader, fullContent);
    }


}
