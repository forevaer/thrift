package com.godme.client;

import com.godme.thrift.Gender;
import com.godme.thrift.Person;
import com.godme.thrift.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.io.IOException;

public class clientMain {
    public static void main(String[] args) throws IOException {
        TSocket socket = new TSocket("localhost",8989);
        int timeout = 500;
        TTransport transport = new TFramedTransport(socket,timeout);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);
        try{
            transport.open();
            Person person = client.getPersonByName("godme");
            System.out.println(person.toString());
            Person newPerson = new Person();
            newPerson.setName("godme");
            newPerson.setAge(99);
            newPerson.setGender(Gender.MALE);
            client.addPerson(newPerson);
            person = client.getPersonByName("godme");
            System.out.println("get godme : " + person.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            transport.close();
        }

    }
}
