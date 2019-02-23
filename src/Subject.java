public interface Subject {

    void addObserver(Customer c);
    void removeObserver(Customer c);
    void NotifyAdd(Notification notification);
    void NotifyPrice(Notification notification, float price);
    void NotifyRemove(Notification notification);


}
