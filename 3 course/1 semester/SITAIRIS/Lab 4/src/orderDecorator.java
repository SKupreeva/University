abstract class orderDecorator implements order {
    protected order decorated;

    public orderDecorator(order decorated){
        this.decorated = decorated;
    }
    public void print(){
        decorated.print();
    }
}
