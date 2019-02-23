import java.util.ListIterator;

public class ShoppingCart extends ItemList implements Visitor
{
    private float buget; // reprezinta bugetul pe care un client il are disponibil
    private float pretProduse; // reprezinta pretul produselor din cos
    ListIterator<Item> iterator;

    public float getBuget() {
        return buget;
    }

    public float getPretProduse() {
        return pretProduse;
    }

    public void setBuget(float buget) {
        this.buget = buget;
    }

    public void setPretProduse(float pretProduse) {
        this.pretProduse = pretProduse;
    }

    public ShoppingCart(float buget)
    {
        super(new ComparatorItemList());
        setBuget(buget);
        setPretProduse(0);
    }

    public boolean add(Item item)
    {
        if(buget - item.getPret() > 0)
        {
            buget = buget - item.getPret();
            pretProduse = pretProduse + item.getPret();
            super.addItem(item);
        }
        return false;
    }

    public boolean remove(Item item)
    {
        if(super.remove(item)) {
            pretProduse = pretProduse - item.getPret();
            if(pretProduse < 0)
                pretProduse = 0;
            buget = buget + item.getPret();
            return true;
        }
        else
            return false;
    }

    // metoda pentru a sterge toate produsele
    public void removeAll()
    {
        pretProduse = 0;
        super.removeAll();
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

    // Modificam pretul tuturor produselor scazand cu 10%
    public void visit(BookDepartment bookDepartment)
    {
        iterator = this.listIterator();
        while (iterator.hasNext()) {
            Item x = iterator.next();
            if(bookDepartment.contains(x.getId()))
                x.setPret((float) (9.0/10.0*x.getPret()));
        }

        pretProduse = (float) (9.0/10.0*pretProduse);
        this.sortAfterModify();
    }

    // Adaugam la buget 10% din totatul produselor
    public void visit(MusicDepartment musicDepartment)
    {
        float adaos= 0;
        iterator = this.listIterator();
        while (iterator.hasNext()) {
            Item x = iterator.next();
            if(musicDepartment.contains(x.getId()))
                adaos = (float) (adaos + 1.0/10.0*x.getPret());
        }

        buget = buget + adaos;

    }

    // Aplicam o reducere de 20% daca trebuie, iar apoi sortam lista de produse
    public void visit(SoftwareDepartment softwareDepartment)
    {
        Item cheaper = softwareDepartment.getListProducts().getItem(0);
        if(buget < cheaper.getPret())
        {
            iterator = this.listIterator();
            while (iterator.hasNext()) {
                Item x = iterator.next();
                if(softwareDepartment.contains(x.getId()))
                    x.setPret((float) (8.0/10.0*x.getPret()));
            }
            pretProduse = (float) (8.0/10.0*pretProduse);
        }
        this.sortAfterModify();
    }

    // Aplicam o reducere de 15%, iar apoi adaugam 5% pentru tot bugetul din totalul produselor
    public void visit(VideoDepartment videoDepartment)
    {
        float sum = 0;
        float adaos = 0;
        iterator  = this.listIterator();
        while(iterator.hasNext())
        {
            Item x = iterator.next();
            if(videoDepartment.contains(x.getId()))
                sum = sum + x.getPret();
        }

        if(sum > videoDepartment.getListProducts().getItem(videoDepartment.getListProducts().getNrElements()-1).getPret())
        {
            iterator  = this.listIterator();
            while(iterator.hasNext())
            {
                Item x = iterator.next();
                if(videoDepartment.contains(x.getId()))
                    x.setPret((float) (85.0/100.0*x.getPret()));
            }
            pretProduse = (float) (85.0/100.0*pretProduse);
        }

        iterator = videoDepartment.getListProducts().listIterator();
        while(iterator.hasNext())
        {
            Item x = iterator.next();
            if(videoDepartment.contains(x.getId()))
                adaos = adaos + x.getPret();
        }

        adaos = (float) (5.0/100.0*adaos);
        buget = buget + adaos;

        this.sortAfterModify();
    }
}
