import java.util.ListIterator;

public class WishList extends ItemList
{
    ListIterator<Item> iterator;

    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public  WishList(Strategy strategy)
    {
        super();
        this.strategy = strategy;
    }

    public Item executeStrategy()
    {
        return strategy.execute(this);
    }
    public boolean add(Item item)
    {
        return super.add(item);
    }

    public Item findItem(int id)
    {

        iterator = this.listIterator();

        if(iterator.hasNext())
        {
            Item x = iterator.next();
            while(x != null)
            {
                if(x.getId() == id)
                    return x;
                x = iterator.next();
            }
            return x;
        }
        else
            return null;
    }

    public boolean findItemByDepartment(Department dep)
    {
        boolean ok = false;
        iterator = this.listIterator();
        while (iterator.hasNext())
        {
            Item x = iterator.next();
            if(dep.getListProducts().contains(x.getId())) {
                ok = true;
                break;
            }
        }
        return ok;
    }

}
