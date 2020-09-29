package lab_2;

public class Person {
    public String Surname;
    public int Age;
    public String Gender;

    public Person() {
        System.out.println("    Congrats!\n You'v created a new person!\n\n");
    }

    public Person(String surname, int age, String gender) {
        Surname = surname;
        Age = age;
        Gender = gender;
    }

    public void setSurname(String surname){
        this.Surname = surname;
    }

    public void setAge(Integer age){
        this.Age = age;
    }

    public void setGender(String gender){
        this.Gender = gender;
    }

    public Integer getAge() {
        return Age;
    }

    public String getGender() {
        return Gender;
    }

    public String getSurname() {
        return Surname;
    }

    public void showPerson() {
        System.out.println("\nInfo about person:");
        System.out.println("Surname - " + Surname + ".");
        System.out.println("Age - " + Age + ".");
        System.out.println("Gender - " + Gender + ".\n");
    }
}
