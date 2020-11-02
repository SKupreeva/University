public class Menu {
    public String menu[] = {"Pizza", "Sandwich", "Hamburger", "Coca-Cola", "Fanta", "Sprite"};

    public Iterator getIterator() {
        return new menuIterator();
    }

    private class menuIterator implements Iterator {
        int ind;
        public boolean hasNext() {
            if(ind < menu.length) return true;
            return false;
        }
        public Object next() {
            if(this.hasNext()) return menu[ind++];
            return null;
        }
    }
}
