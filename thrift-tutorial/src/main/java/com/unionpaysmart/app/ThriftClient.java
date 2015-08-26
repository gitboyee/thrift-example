package com.unionpaysmart.app;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import thrift.tutorial.Calculator;
import thrift.tutorial.Calculator.Client;
import thrift.tutorial.Operation;
import thrift.tutorial.Work;

/**
 * @author yida
 * @since 2015年8月7日 下午5:53:33 
 */
public class ThriftClient {
    
    
    public static void main(String[] args) {
        if (1 != args.length) {
            System.out.println("please enter 'simple' or 'secure' ");
            System.exit(0);
        }
        try {
            TTransport transport =  null;
            if (args[0].contains("simple")) {
                transport = new TSocket("localhost",9090);
                transport.open();
            }else {
                TSSLTransportParameters params = new TSSLTransportParameters();
                params.setTrustStore("", "thrift", "SunX509", "JKS");
                transport = TSSLTransportFactory.getClientSocket("localhost", 9090, 0, params);
            }
            TProtocol protocol = new TBinaryProtocol(transport);
            Calculator.Client client = new Calculator.Client(protocol);
            perform(client);
            transport.close();
        } catch (Exception e) {
        }
    }

    /**
     * @param client
     * @throws TException 
     */
    private static void perform(Client client) throws TException {
        client.ping();
        System.out.println("ping()");
        
        int sum = client.add(1, 2);
        System.out.println("sum = "+sum);
        
        Work work = new Work();
        work.op = Operation.DIVEDE;
        work.num1 = 1;
        work.num2 = 0;
        client.calculate(1, work);
    }

}
