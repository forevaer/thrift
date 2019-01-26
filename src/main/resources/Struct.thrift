namespace java com.godme.thrift
typedef i16 short
typedef i32 int
typedef i64 long
typedef string String
typedef bool boolean


enum Gender{
    MALE
    FEMALE
}

struct Person{
    1:optional String name;
    2:optional int age;
    3:optional Gender gender;
}

exception MyException{
    1:optional int code;
    2:optional String message;
}

service PersonService{
    Person getPersonByName(1:required String name);
    void addPerson(1:required Person person);
}
