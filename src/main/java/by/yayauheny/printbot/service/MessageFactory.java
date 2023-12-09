package by.yayauheny.printbot.service;

public abstract class MessageFactory<T> {

    public abstract String convert(T obj);
}
