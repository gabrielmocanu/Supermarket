public interface Observer
{
    void updatePrice(Notification notification, float Price);
    void updateAdd(Notification notification);
    void updateRemove(Notification notification);

}
