import java.util.Comparator;

// Comparator pentru a ordona produsele in functie de pret

public class ComparatorItemList implements Comparator<Item> {
    public int compare(Item ob1, Item ob2) {
        if (ob1.getPret() > ob2.getPret())
            return 1;
        else if (ob1.getPret() < ob2.getPret())
            return -1;
        else {
            return ob1.getName().compareTo(ob2.getName());
        }
    }
}
