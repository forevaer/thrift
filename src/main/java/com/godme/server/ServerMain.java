package com.godme.server;

import com.godme.handler.PersonHandler;
import com.godme.thrift.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.*;


public class ServerMain {
    public static void main(String[] args) throws  TTransportException {
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8989);
        THsHaServer.Args serverArgs = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(5);

        PersonService.Processor<PersonHandler> processor = new PersonService.Processor<>(new PersonHandler());
        TFramedTransport.Factory transFactory = new TFramedTransport.Factory();
        TCompactProtocol.Factory protocolFactory = new TCompactProtocol.Factory();
        TProcessorFactory processorFactory = new TProcessorFactory(processor);

        serverArgs.transportFactory(transFactory);
        serverArgs.protocolFactory(protocolFactory);
        serverArgs.processorFactory(processorFactory);

        THsHaServer server = new THsHaServer(serverArgs);

        server.serve();
    }
}
