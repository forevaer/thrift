from Struct import PersonService
from Struct import ttypes
class PersonHandler():
    def __init__(self):
        self.people = {}
    def getPersonByName(self, name):
        if len(self.people) <= 0:
            person = ttypes.Person()
            person.name = "队列为空"
            return person
        if not self.people[name]:
            person = ttypes.Person()
            person.name = "用户["+ name + "]不存在"
            return person
        return self.people[name]

    def addPerson(self, person):
        self.people[person.name] = person