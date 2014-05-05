package ua.com.nv.protocol;

public class SimpleTelnetMsg2 {
    int mark;
    private byte[] box = new byte[1024];

    public void appendContent(byte... content) {
        if (mark < box.length && content.length <= (box.length - mark)) {
            for (byte curByte : content) {
                box[++mark] = curByte;
            }
        }
        else {
            throw  new
        }

    }

    public byte[] getResponseMsg() {
        return box;
    }

}
