package lab_2.FObject.fixed;

public class Milkshake extends fixedObject{
    static int k=1;
    public int n;

    public Milkshake(){
        k=k+1;
        System.out.println("    Congrats!\n You'v created a milkshake!\n    Number - "+k+"\n\n");
        n = k;
    }

    @Override
    public void getName() {
        name="Milkshake";
    }
}
