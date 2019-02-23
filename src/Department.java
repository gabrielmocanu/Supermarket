import java.util.*;

public abstract class Department implements Subject
{
    private String name;
    private Integer id;
    private ArrayList<Customer> customersObserver; // Lista pentru Observeri
    private ArrayList<Customer> customersBuyer; // Lista pentru cei care au cumparat( varianta grafica )
    private ItemList listProducts;

    public String getName()
    {
        return name;
    }

    public Integer getId()
    {
        return id;
    }

    public ArrayList<Customer> getCustomersObserver()
    {
        return customersObserver;
    }

    public ArrayList<Customer> getCustomerBuyer()
    {
        return customersBuyer;
    }

    public ItemList getListProducts()
    {
        return listProducts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCustomersBuyer(ArrayList<Customer> customersBuyer) {
        this.customersBuyer = customersBuyer;
    }

    public void setCustomersObserver(ArrayList<Customer> customersObserver) {
        this.customersObserver = customersObserver;
    }

    public void setListProducts(ItemList listProducts) {
        this.listProducts = listProducts;
    }

    public void addObserver(Customer c)
    {
        if(!customersObserver.contains(c))
        {
            customersObserver.add(c);
        }
    }

    public void removeObserver(Customer c)
    {
        customersObserver.remove(c);
    }

    public ArrayList<Customer> getObservers()
    {
        return customersObserver;
    }

    public boolean contains(int id)
    {
        if(listProducts.contains(id))
            return true;
        else
            return false;
    }


    public void NotifyPrice(Notification notification, float price)
    {
        for(int i = 0; i < customersObserver.size();i++)
        {
            customersObserver.get(i).updatePrice(notification,price);
        }
    }

    public void NotifyAdd(Notification notification)
    {
        for(int i = 0; i < customersObserver.size();i++)
        {
            System.out.println("DA");
            customersObserver.get(i).updateAdd(notification);
        }
    }

    public void NotifyRemove(Notification notification)
    {
        for(int i = 0; i < customersObserver.size();i++)
        {
            customersObserver.get(i).updateRemove(notification);
        }
    }

    public void enter(Customer c)
    {
        if(!customersBuyer.contains(c))
        {
            customersBuyer.add(c);
        }
    }

    public void exit(Customer c)
    {
        customersBuyer.remove(c);
    }

    public ArrayList<Customer> getCustomers()
    {
        return customersBuyer;
    }

    public void addItem(Item item)
    {
        listProducts.add(item);
    }

    public ItemList getItems()
    {
        return listProducts;
    }

    abstract void accept(Visitor visitor);
}
