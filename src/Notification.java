enum NotificationType
{
    ADD,REMOVE,MODIFY;
}

public class Notification
{
    private int depId;
    private int prodId;

    NotificationType notificationType;

    public int getDepId() {
        return depId;
    }

    public int getProdId() {
        return prodId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public Notification(int depId, int prodId, NotificationType notificationType)
    {
        this.depId = depId;
        this.prodId = prodId;
        this.notificationType = notificationType;
    }

    public String toString()
    {
        String s;
        s = notificationType + ";" + prodId + ";" + depId;
        return s;
    }
}
