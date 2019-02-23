import java.util.ArrayList;

public class SoftwareDepartment extends Department
{

    private SoftwareDepartment(SoftwareBuilder builder)
    {
        this.setName(builder.name);
        this.setId(builder.id);
        this.setCustomersBuyer(builder.customersBuyer);
        this.setCustomersObserver(builder.customersObserver);
        this.setListProducts(builder.listProducts);
    }

    public static class SoftwareBuilder
    {
        String name;
        Integer id;
        ArrayList<Customer> customersObserver;
        ArrayList<Customer> customersBuyer;
        ItemList listProducts;

        public SoftwareBuilder(String name)
        {
            this.name = name;
        }

        public SoftwareBuilder id(int id)
        {
            this.id = id;
            return this;
        }

        public SoftwareBuilder customersObserver()
        {
            customersObserver = new ArrayList<Customer>();
            return this;
        }

        public SoftwareBuilder customersBuyer()
        {
            customersBuyer = new ArrayList<Customer>();
            return this;
        }

        public SoftwareBuilder listProducts()
        {
            listProducts = new ItemList(new ComparatorItemList());
            return this;
        }

        public SoftwareDepartment build()
        {
            return new SoftwareDepartment(this);
        }

    }
    // Metode pentru a notifica observatorii acestui departament in functie de operatie
    public void NotifyPrice(Notification notification, float price)
    {

        for(int i = 0; i < getCustomersObserver().size();i++)
        {
            getCustomersObserver().get(i).updatePrice(notification,price);
        }

    }

    public void NotifyAdd(Notification notification)
    {
        for(int i = 0; i < getCustomersObserver().size();i++)
        {
            getCustomersObserver().get(i).updateAdd(notification);
        }
    }

    public void NotifyRemove(Notification notification)
    {
        for(int i = 0; i < getCustomersObserver().size();i++)
        {
            getCustomersObserver().get(i).updateRemove(notification);
        }
    }
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }
}
