/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxtablewithpagination;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhalepr
 */
public class PersonFactory {
  static int id=0;
  static List getAllPersons() {
    ArrayList<Person> list = new ArrayList<>();
    list.add(createPerson("John", 40));
    list.add(createPerson("Daisy", 21));
    list.add(createPerson("Martha", 34));
    list.add(createPerson("Frodo", 16));
    list.add(createPerson("Mickey", 22));
    list.add(createPerson("Minnie", 19));
    list.add(createPerson("Donald", 30));
    list.add(createPerson("Goofy", 32));
    list.add(createPerson("Pokoyo", 12));
    list.add(createPerson("Dora", 16));
    list.add(createPerson("Tintin", 25));
    list.add(createPerson("John", 52));
    list.add(createPerson("Daisy", 29));
    list.add(createPerson("Martha Has a very Long Name", 18));
    System.out.println("Returning person size: " + list.size());
    return list;
  }
  
  static Person createPerson(String _name, int _age)  {
    return new Person(Integer.toString(id++),_name, _age);
  }
}
