package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.SimpleTelnetEnveloper;

public abstract class AbstractCommander implements Commander {
    protected Commands concreteCommand;
    protected SimpleTelnetEnveloper enveloper;


}
