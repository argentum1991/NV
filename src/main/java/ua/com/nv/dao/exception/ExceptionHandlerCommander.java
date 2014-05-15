package ua.com.nv.dao.exception;

import ua.com.nv.protocol.commander.AbstractCommander;


public class ExceptionHandlerCommander<T extends Exception> extends AbstractCommander {
    @Override
    public void processRequest(String request) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public void processRequest(T exception) {
        enveloper.addMsgContent(exception.getMessage());
    }

}
