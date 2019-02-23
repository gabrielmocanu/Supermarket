import java.util.ArrayList;

public class Customer implements Observer
{
    private String name;
    private ShoppingCart cart;
    private WishList wishList;
    private ArrayList<Notification> notifications;

    public String getName() {
        return name;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public WishList getWishList() {
        return wishList;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public Customer(String name, float buget, Strategy strategy)
    {
        setName(name);
        cart = new ShoppingCart(buget);
        wishList = new WishList(strategy);
        notifications = new ArrayList<Notification>();
    }


    // Metoda pentru a update pretul produselor

    public void updatePrice(Notification notification, float price)
    {
        notifications.add(notification);
        if(cart.findItem(notification.getProdId()) != null)
        {
            cart.findItem(notification.getProdId()).setPret(price);
            this.cart.setPretProduse(0);
            for(int i = 0; i < this.cart.getNrElements(); i++)
            {
                this.cart.setPretProduse(this.cart.getPretProduse() + this.cart.getItem(i).getPret());
            }
            cart.sortAfterModify();
        }
        if(wishList.findItem(notification.getProdId()) != null)
            wishList.findItem(notification.getProdId()).setPret(price);
    }

    public void updateAdd(Notification notification)
    {
        notifications.add(notification);
    }

    public void updateRemove(Notification notification)
    {
        notifications.add(notification);
        cart.remove(cart.findItem(notification.getProdId()));
        wishList.remove(wishList.findItem(notification.getProdId()));
    }

    public String toString()
    {
        return name;
    }


}
