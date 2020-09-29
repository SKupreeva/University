package lab_2.FObject.fixed;

import lab_2.FObject.FOblect;

public class fixedObject extends FOblect {
    protected String mater;

    @Override
    protected void getName() {
        name = "Some Name";
    }

    @Override
    public boolean equals(Object o){
        if(o==null)
            return false;
        if(this==o)
            return true;
        if(o instanceof fixedObject){
            fixedObject temp=(fixedObject)o;
            return this.mater.equals(temp.mater) && this.name.equals(temp.name) &&
                    this.cost==temp.cost && this.id==temp.id;
        }
        else
            return false;
    }

    @Override
    public String toString() {
        String s = new String();
        s = "Dish - "+ name + "\nID - " + id + "\nCost - " + cost + "\n\n";
        return s;
    }

    @Override
    public int hashCode() {
        return mater != null ? mater.hashCode() : 0;
    }
}
