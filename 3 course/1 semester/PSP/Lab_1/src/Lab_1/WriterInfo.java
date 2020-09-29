package Lab_1;

public class WriterInfo {

    WriterInfo() {
        Person p1 = new Person("Nilson", 23, "male");
        Person p2 = new Person("Anderson", 44, "male");
        Person p3 = new Person("Ting", 27, "female");
        p1.showPerson();
        p2.showPerson();
        p3.showPerson();
        averageAge(p1, p2, p3);
        sumMale(p1, p2, p3);
    }

    public void averageAge(Person p1, Person p2, Person p3){
        int s = 0, a = 0;
        s = p1.Age + p2.Age + p3.Age;
        a = s/3;
        System.out.println("Average age is " + a + ".");
    }

    public void sumMale(Person p1, Person p2, Person p3){
        int sum = 0;
        if(p1.Gender.equals("male"))
            sum++;
        if(p2.Gender.equals("male"))
            sum++;
        if(p3.Gender.equals("male"))
            sum++;
        System.out.println("Male number is " + sum + ".");
    }
}
