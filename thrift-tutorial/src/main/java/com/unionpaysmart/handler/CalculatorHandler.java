package com.unionpaysmart.handler;

import java.util.HashMap;

import org.apache.thrift.TException;

import thrift.shared.SharedStruct;
import thrift.tutorial.Calculator;
import thrift.tutorial.InvalidOperation;
import thrift.tutorial.Work;

/**
 * @author yida
 * @since 2015年8月10日 上午11:07:43 
 */
public class CalculatorHandler implements Calculator.Iface{
    
    private HashMap<Integer,SharedStruct> log;

    public SharedStruct getStruct(int key) {
        System.out.println("getStruct(" + key + ")");
        return log.get(key);
      }

    /* (non-Javadoc)
     * @see thrift.tutorial.Calculator.Iface#ping()
     */
    @Override
    public void ping() throws TException {
        System.out.println("server receive ping request");
        
    }

    /* (non-Javadoc)
     * @see thrift.tutorial.Calculator.Iface#add(int, int)
     */
    @Override
    public int add(int num1, int num2) throws TException {
        return num1+num2;
    }

    /* (non-Javadoc)
     * @see thrift.tutorial.Calculator.Iface#calculate(int, thrift.tutorial.Work)
     */
    @Override
    public int calculate(int logid, Work work) throws InvalidOperation, TException {
        System.out.println("calculate(" + logid + ", {" + work.op + "," + work.num1 + "," + work.num2 + "})");
        int val = 0;
        switch (work.op) {
        case ADD:
          val = work.num1 + work.num2;
          break;
        case SUBTRACT:
          val = work.num1 - work.num2;
          break;
        case MULTYPLY:
          val = work.num1 * work.num2;
          break;
        case DIVEDE:
          if (work.num2 == 0) {
            InvalidOperation io = new InvalidOperation();
            io.whatOp = work.op.getValue();
            io.why = "Cannot divide by 0";
            throw io;
          }
          val = work.num1 / work.num2;
          break;
        default:
          InvalidOperation io = new InvalidOperation();
          io.whatOp = work.op.getValue();
          io.why = "Unknown operation";
          throw io;
        }

        SharedStruct entry = new SharedStruct();
        entry.key = logid;
        entry.value = Integer.toString(val);
        log.put(logid, entry);

        return val;
    }

    /* (non-Javadoc)
     * @see thrift.tutorial.Calculator.Iface#zip()
     */
    @Override
    public void zip() throws TException {
        System.out.println("...zip()...");
        
    }

}
