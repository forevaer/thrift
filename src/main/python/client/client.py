from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TCompactProtocol
from Struct import PersonService
from Struct.ttypes import Person
from Struct.ttypes import Gender
try:
    socket = TSocket.TSocket("localhost",8989)
    socket.setTimeout(500)
    transport = TTransport.TFramedTransport(socket)
    protocol = TCompactProtocol.TCompactProtocol(transport)
    client = PersonService.Client(protocol)

    transport.open()

    person = client.getPersonByName("godme")
    print(person)
    person = Person()
    person.name = "godme"
    person.age = 90
    person.gender = Gender.MALE
    client.addPerson(person)
    person = client.getPersonByName("godme")
    print(person)
except Exception as e:
    print(e)