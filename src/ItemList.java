import java.util.ListIterator;
import java.util.Comparator;

public class ItemList<T extends Item> {
    private int nrElements;
    Node prim = null;
    Node ultim = null;

    Comparator< T > comparator = null;

    public int getNrElements() {
        return nrElements;
    }

    public void setNrElements(int nrElements) {
        this.nrElements = nrElements;
    }

    // Diferiti constructori in functie de ce lista vrem sa avem
    public ItemList(T item, Comparator< T > comparator) {
        prim = new Node(item);
        ultim = prim;
        this.comparator = comparator;
    }

    public ItemList() {
        prim = null;
        ultim = null;
        comparator = null;
    }

    public ItemList(T item) {
        prim = new Node(item);
        ultim = prim;
        comparator = null;
    }

    public ItemList(Comparator<T> comparator) {
        prim = null;
        ultim = null;
        this.comparator = comparator;
    }

    public boolean contains(int id) {
        if (prim != null) {
            Node temp = prim;
            if (temp.item.getId() == id)
                return true;
            while (temp.next != null) {
                temp = temp.next;
                if (temp.item.getId() == id)
                    return true;
            }
            return false;
        } else {
            return false;
        }
    }

    // Metoda de sortare dupa ce am modificat un pret
    public void sortAfterModify() {
        boolean ok;
        int i;
        Node curent;
        Node save = null;

        if (prim == null)
            return;

        do {
            ok = false;
            curent = prim;

            while (curent.next != save) {
                if (curent.item.getPret() > curent.next.item.getPret()) {
                    Node a = curent;
                    Node b = curent.next;

                    a.next = b.next;
                    b.prev = a.prev;

                    if (a.next != null)
                        a.next.prev = a;

                    if (b.prev != null)
                        b.prev.next = b;
                    else
                        prim = b;


                    b.next = a;
                    a.prev = b;
                    ok = true;
                }
                curent = curent.next;

            }
            save = curent;
        }
        while (ok);
    }

    public boolean add(T item) {
        if (this.contains(item.getId())) {
            return false;
        } else {
            addItem(item);
        }
        return true;
    }

    public boolean addItem(T item) {

        if(this.contains(item.getId()))
        {
            Node temp = prim;
            if (temp == null) {
                prim = new Node(item);
                ultim = prim;
                nrElements++;
                return true;
            }

            while (temp.next != null)
                temp = temp.next;

            Node newNode = new Node(item);

            temp.next = newNode;
            newNode.prev = temp;
            nrElements++;
            return true;
        }

        if (comparator != null) {
            Node temp = prim;
            if (temp == null) {
                prim = new Node(item);
                ultim = prim;
                nrElements++;
                return true;
            }

            if (comparator.compare(item, (T) prim.item) < 0) {
                Node newNode = new Node(item);
                newNode.next = prim;
                prim.prev = newNode;
                prim = newNode;
                nrElements++;
                return true;
            }

            if (comparator.compare(item, (T) ultim.item) > 0) {
                Node newNode = new Node(item);
                ultim.next = newNode;
                newNode.prev = ultim;
                ultim = newNode;
                nrElements++;
                return true;
            }

            temp = prim;


            while (comparator.compare(item, (T) temp.next.item) >= 0) {
                temp = temp.next;
            }


            Node newNode = new Node(item);

            newNode.next = temp.next;
            temp.next.prev = newNode;
            temp.next = newNode;
            newNode.prev = temp;
            nrElements++;
        } else {
            Node temp = prim;
            if (temp == null) {
                prim = new Node(item);
                ultim = prim;
                nrElements++;
                return true;
            }

            while (temp.next != null)
                temp = temp.next;

            Node newNode = new Node(item);

            temp.next = newNode;
            newNode.prev = temp;
            nrElements++;
            return true;
        }
        return true;
    }

    public void removeAll() {

        while (nrElements > 0) {
            removeFirst();
        }
    }

    public Node getNode(int index) {
        Node temp = prim;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public T getItem(int index) {
        if(getNode(index) != null)
            return (T) this.getNode(index).item;
        else
            return null;
    }

    public T getItemId(int Id) {
        for (int i = 0; i < nrElements; i++) {
            if (this.getItem(i).getId() == Id)
                return this.getItem(i);
        }
        return null;
    }

    public T getItemName(String name) {
        for (int i = 0; i < nrElements; i++) {
            if (this.getItem(i).getName().equals(name))
                return this.getItem(i);
        }
        return null;
    }

    public int indexOf(T item_search) {
        int i = 0;
        Node temp = prim;
        while (temp != null) {
            if (temp.item.getId() == item_search.getId())
                return i;
            else {
                i++;
                temp = temp.next;
            }
        }
        return -1;
    }

    public int indexOf(Node node_search) {
        int i = 0;
        Node temp = prim;
        while (temp != null) {
            if (temp.equals(node_search))
                return i;
            else {
                i++;
                temp = temp.next;
            }
        }
        return -1;
    }

    public boolean contains(Node node_search) {
        if (this.indexOf(node_search) != -1)
            return true;
        else
            return false;
    }

    public boolean contains(T item_search) {
        if (this.indexOf(item_search) != -1)
            return true;
        else
            return false;
    }

    public T remove(int index) {
        if (index == 0) {
            return this.removeFirst();
        }
        if (index == nrElements - 1) {
            nrElements--;
            return this.removeLast();
        }

        Node temp = prim;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        temp.next.prev = temp;
        nrElements--;

        return (T) temp.item;
    }


    public T removeFirst() {
        if (nrElements == 1) {
            T item = (T) prim.item;
            prim = null;
            ultim = null;
            nrElements--;
            return item;
        } else {
            nrElements--;
            T item = (T) prim.item;
            prim.next.prev = null;
            prim = prim.next;
            return item;
        }

    }

    public T removeLast() {
        Node temp = prim;
        if (ultim.prev != null)
            ultim = ultim.prev;
        while (temp.next != null) {
            temp = temp.next;
        }
        T item = (T) temp.item;

        temp.prev.next = null;
        temp.prev = null;

        return item;
    }

    public boolean remove(T item) {
        if (item == null)
            return false;


        int index = indexOf(item);
        if (index == -1)
            return false;
        else {
            remove(index);
            return true;
        }
    }

    public boolean isEmtpy() {
        if (nrElements == 0)
            return true;
        else
            return false;
    }

    public Float getTotalPrice() {
        float sum = 0;
        Node temp = prim;
        while (temp != null) {
            sum = sum + temp.item.getPret();
            temp = temp.next;
        }
        return sum;
    }

    private static class Node< T extends Item> {
        T item;
        Node<T> next;
        Node<T> prev;

        public Node(T item, Node<T> next, Node<T > prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;

        }

        public Node(T item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    public ListIterator<T > listIterator() {
        return new ItemIterator();
    }

    public ListIterator<T > listIterator(int index) {
        return new ItemIterator(index);
    }

    public class ItemIterator implements ListIterator<T > {
        private int index;
        private Node curNode = new Node(null);
        private Node visited;

        public ItemIterator() {
            curNode.next = prim;
            curNode.item = null;
            curNode.prev = null;
            visited = null;
            index = 0;
        }

        public ItemIterator(int index) {
            curNode = prim;
            for (int i = 0; i < index - 1; i++) {
                curNode = curNode.next;
            }
            visited = null;
            this.index = index;
        }

        public boolean hasNext() {
            if (curNode.next != null)
                return true;
            else
                return false;
        }

        public boolean hasPrevious() {
            if (curNode.prev != null)
                return true;
            else
                return false;
        }

        public int previousIndex() {
            return index - 1;
        }

        public int nextIndex() {
            return index;
        }


        public T next() {
            if (!hasNext()) {

                return null;
            }
            T item = (T) curNode.next.item;
            curNode = curNode.next;
            index++;
            visited = curNode;
            return item;
        }

        public T previous() {
            if (!hasPrevious())
                return null;
            T item = (T) curNode.prev.item;
            curNode = curNode.prev;
            index--;
            visited = curNode;
            return item;
        }

        public void set(T item) {
            if (visited != null)
                visited.item = item;
        }
        public void remove() {
            if (visited != null) {
                visited.prev.next = visited.next;
                visited.next.prev = visited.prev;
                nrElements--;
                if (curNode == visited)
                    curNode = visited.next;
                else
                    index--;

                visited = null;
            }
        }

        public void add(T item) {
            Node nou = new Node(item);
            curNode.prev.next = nou;
            curNode.prev = nou;
            nou.prev = curNode.prev;
            nou.next = curNode;
            nrElements++;
            index++;
            visited = null;
        }
    }
    public String toString() {

        String s = "[";
        Node temp = prim;
        while (temp != null && temp.next != null) {
            s = s + temp.item + ", ";
            temp = temp.next;
        }
        if (temp != null)
            s = s + temp.item;
        s = s + "]";

        return s;
    }

}


