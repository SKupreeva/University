package lab_2.Order;

import lab_2.FObject.FOblect;
import lab_2.FObject.fixed.Milkshake;
import lab_2.FObject.fixed.fixedObject;
import lab_2.Inter;
import lab_2.Person;

import java.util.ArrayList;
import java.util.List;


public class Order implements Inter {
    static int oId=0;
    List<String> clients;
    Milkshake m = new Milkshake();
    Person[] P = new Person[4];

    public Order(){
        clients = new ArrayList<>();
        oId=oId+1;
        m.getName();
        P[0] = new Person();
        P[0].Surname = "Anderson";
        P[0].Age = 44;
        P[0].Gender = "male";
        P[1] = new Person("Nilson", 23, "male");
        P[2] = new Person();
        P[2].setSurname("Ting");
        P[2].setAge(27);
        P[2].setGender("female");
        P[3] = new Person();
        P[3].Surname = "Argent";
        P[3].Age = 19;
        P[3].Gender = "female";
        for(int i = 0; i < 4; i++) {
            P[i].showPerson();
            clients.add(P[i].Surname);
        }
        averageAge(P);
        sumMale(P);
    }

    @Override
    public void showOrder() {
        System.out.println("    Your order:");
        int a = (int) (Math.random() * 3);
        System.out.println("----------------------------------");
        System.out.println("|Order №|       Dish      |Number|");
        System.out.println("----------------------------------");
        System.out.printf("|%7d|%17s|%6d|\n", oId, m.name, m.n);
        System.out.println("----------------------------------");
    }

    public static void averageAge(Person p[]){
        int s = 0;
        float a = 0;
        for(int i = 0; i < 4; i++)
            s = s + p[i].Age;
        a = s/4 + s%4;
        System.out.println("Average age is " + a + ".");
    }

    public static void sumMale(Person p[]){
        int sum = 0;
        for(int i = 0; i < 4; i++){
            if(p[i].Gender.equals("male"))
                sum++;
        }
        System.out.println("Male number is " + sum + ".\n\n");
    }
}
