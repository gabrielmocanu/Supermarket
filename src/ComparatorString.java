import java.util.Comparator;

// Comparator pentru a ordina produsele alfabetic conform strategiei

public class ComparatorString implements  Comparator<Item>
{
        public int compare(Item ob1, Item ob2)
        {
            return ob1.getName().compareTo(ob2.getName());
        }
}
