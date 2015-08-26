package com.demo.thrift;

import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.apache.thrift.transport.TSocket;

import com.demo.thrift.support.provider.ThriftServerAddressProvider;

/**
 * 连接池,thrift-client for spring
 */
public class ThriftClientPoolFactory1{

    private GenericObjectPool<TServiceClient> pool;
    
    private final ThriftServerAddressProvider addressProvider;

    protected ThriftClientPoolFactory1(Integer maxActive,Integer maxIdle,ThriftServerAddressProvider addressProvider,TServiceClientFactory<TServiceClient> clientFactory) throws Exception {
        GenericObjectPool.Config poolConfig = new GenericObjectPool.Config();
        poolConfig.maxActive = maxActive;
        if(maxIdle != null){
            poolConfig.maxIdle = maxIdle;
        }
        this.addressProvider = addressProvider;
        pool = new GenericObjectPool<TServiceClient>(new PoolableConnectionFactory(),poolConfig);
    }


    public TServiceClient borrow() throws Exception{
        return pool.borrowObject();
    }

    public void returnObject(TServiceClient socket) throws Exception{
        pool.returnObject(socket);
    }


    class PoolableConnectionFactory extends BasePoolableObjectFactory<TServiceClient> {

        @Override
        public TServiceClient makeObject() throws Exception {
            InetSocketAddress address = addressProvider.selector();
            TSocket tsocket = new TSocket(address.getHostName(),address.getPort());
            return tsocket;
        }

        public void destroyObject(TServiceClient obj) throws Exception {
            obj.close();
        }

        public boolean validateObject(TServiceClient obj) {
            return obj.isOpen();
        }
    }
}
