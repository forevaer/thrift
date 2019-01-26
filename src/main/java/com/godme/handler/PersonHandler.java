package com.godme.handler;

import com.godme.thrift.MyException;
import com.godme.thrift.Person;
import com.godme.thrift.PersonService;
import org.apache.thrift.TException;

import java.lang.reflect.Member;
import java.util.HashMap;

public class PersonHandler implements PersonService.Iface {
    HashMap<String, Person> people = new HashMap<>();
    @Override
    public Person getPersonByName(String name) throws MyException {
        if(people.size() <= 0){
            Person person = new Person();
            person.setName("队列为空");
            return person;
        }
        if(!people.containsKey(name)){
            Person person = new Person();
            person.setName("用户"+ name + "不存在");
            return person;
        }
        return people.get(name);
    }

    @Override
    public void addPerson(Person person) throws MyException {
        people.put(person.getName(), person);
    }
}
