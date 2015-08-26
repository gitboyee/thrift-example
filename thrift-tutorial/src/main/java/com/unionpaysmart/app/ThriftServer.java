package com.unionpaysmart.app;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import thrift.tutorial.Calculator;
import thrift.tutorial.Calculator.Iface;
import thrift.tutorial.Calculator.Processor;

import com.unionpaysmart.handler.CalculatorHandler;


/**
 * @author yida
 * @since 2015年8月7日 下午5:53:44 
 */
public class ThriftServer {
    
    public static CalculatorHandler handler;
    public static Calculator.Processor<Iface> processor;
    
    public static void main(String[] args) {
        handler = new CalculatorHandler();
        processor = new Calculator.Processor<Calculator.Iface>(handler);
        simple(processor);
    }

    /**
     * @param processor2
     */
    protected static void simple(Processor<Iface> processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
            System.out.println("starting server............");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
    

}
