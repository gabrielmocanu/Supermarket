import java.util.*;

public final class Store {

    String name;
    ArrayList<Customer> ListCustomer;
    public ArrayList<Department> ListDepartment;
    private static Store store = null;

    public static Store getInstance(String name) {
        if (store == null)
            store = new Store(name);
        return store;
    }

    public static Store getInstance() {
        return store;
    }

    private Store(String name) {
        this.name = name;
        ListCustomer = new ArrayList<Customer>();
        ListDepartment = new ArrayList<Department>();
    }

    public void enter(Customer c) {
        ListCustomer.add(c);
    }

    public void exit(Customer c) {
        ListCustomer.remove(c);
    }

    public ShoppingCart getShoppingCart(float sum) {
        return new ShoppingCart(sum);
    }

    public ArrayList<Customer> getCustomers() {
        return ListCustomer;
    }

    public ArrayList<Department> getDepartments() {
        return ListDepartment;
    }

    public void addDepartment(Department d) {
        ListDepartment.add(d);
    }

    public Department getDepartment(Integer i) {
        for (Department dep : ListDepartment)
            if (dep.getId() == i) return dep;
        return null;
    }

    public Customer findCustomer(String name) {
        for (int i = 0; i < ListCustomer.size(); i++) {
            if (ListCustomer.get(i).getName().equals(name))
                return ListCustomer.get(i);
        }
        return null;
    }

    public Item findItem(int id) {
        for (int k = 0; k < store.ListDepartment.size(); k++) {
            for (int j = 0; j < store.ListDepartment.get(k).getListProducts().getNrElements(); j++) {
                if (store.ListDepartment.get(k).getListProducts().getItem(j).getId() == id)
                    return store.ListDepartment.get(k).getListProducts().getItem(j);
            }
        }
        return null;
    }

    public Item findItemByName(String name) {
        for (int k = 0; k < store.ListDepartment.size(); k++) {
            for (int j = 0; j < store.ListDepartment.get(k).getListProducts().getNrElements(); j++) {
                if (store.ListDepartment.get(k).getListProducts().getItem(j).getName().equals(name))
                    return store.ListDepartment.get(k).getListProducts().getItem(j);
            }
        }
        return null;
    }

    public Department findDeparment(int id) {
        for (int i = 0; i < store.ListDepartment.size(); i++) {
            if (store.ListDepartment.get(i).getId() == id)
                return store.ListDepartment.get(i);
        }
        return null;
    }

    public Department findDeparmentByItem(int id) {
        for (int k = 0; k < store.ListDepartment.size(); k++) {
            for (int j = 0; j < store.ListDepartment.get(k).getListProducts().getNrElements(); j++) {
                if (store.ListDepartment.get(k).getListProducts().getItem(j).getId() == id)
                    return store.ListDepartment.get(k);
            }
        }
        return null;
    }

}
