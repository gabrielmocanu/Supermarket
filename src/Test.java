public class Test
{
    public static void main(String args[])
    {
        InitStore init = new InitStore();
        Store store = init.getStore("store.txt");
        store.ListCustomer = init.getCustomers("customers.txt");
        init.getEvents("events.txt");
    }

}
