public class orderDecorated extends orderDecorator{
    public orderDecorated(order decorated) {
        super(decorated);
    }
    public void print() {
        decorated.print();
        setID();
    }
    private void setID(){
        System.out.println("ID: 1.");
    }
}
