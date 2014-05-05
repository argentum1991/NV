package ua.com.nv.protocol;

import ua.com.nv.protocol.builder.OutOfPacketSizeException;

public class SimpleTelnetMsg2 {
    int mark;
    private byte[] box = new byte[1024];

    public void appendContent(byte... content) {
        if (mark < box.length && content.length <= (box.length - mark)) {
            for (byte curByte : content) {
                box[mark++] = curByte;
            }
        }
        else {
            throw  new OutOfPacketSizeException("Box size is: "+ box.length+"," +
                    "current mark is in: "+mark+" content length is:"+content.length );
        }

    }

    public byte[] getResponseMsg() {
        return box;
    }

}
