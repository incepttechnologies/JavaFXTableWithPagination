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
public class Person {
  String id;
  String name;
  int age;

  public Person() {
  }

  public Person(String id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }
  

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{" + "id=" + id + ", name=" + name + ", age=" + age + '}';
  }
  
  
  
}
