package vttp.batchb.ssf.practice_test.models;

public class Login {
    private String name;
    private int age;
    
    //getter setter
    public String getName() {    return name;}
    public void setName(String name) {    this.name = name;}
    
    public int getAge() {    return age;}
    public void setAge(int age) {    this.age = age;}

    @Override
    public String toString() {
        return "Name: %s, age: %d".formatted(name,age);
    }
    
}
