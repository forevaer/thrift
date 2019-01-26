from thrift.transport import TSocket
from thrift.server import TServer
from thrift.transport import TTransport
from thrift.protocol import TCompactProtocol
from Struct import PersonService
from handler import PersonHandler
try:
    personServiceHandler = PersonHandler.PersonHandler()
    processor = PersonService.Processor(personServiceHandler)

    serverSocket = TSocket.TServerSocket(port=8989)
    transportFactory = TTransport.TFramedTransportFactory()
    protocolFactory = TCompactProtocol.TCompactProtocolFactory()


    server = TServer.TSimpleServer(processor, serverSocket, transportFactory, protocolFactory)
    server.serve()
except Exception as e:
    print(e)