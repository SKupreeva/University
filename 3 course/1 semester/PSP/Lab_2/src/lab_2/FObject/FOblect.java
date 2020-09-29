package lab_2.FObject;

abstract public class FOblect{
    public String name;
    protected int id;
    protected int cost;

    abstract protected void getName();
    abstract public boolean equals(Object o);
    abstract public int hashCode();
    abstract public String toString();
}
