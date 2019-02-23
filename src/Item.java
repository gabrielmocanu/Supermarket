public class Item
{
    private String name;
    private Integer id;
    private float pret;
    private Integer depId;

    public String getName() {
        return name;
    }

    public float getPret() {
        return pret;
    }

    public Integer getDepId() {
        return depId;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public Item(String name, Integer id, float pret, Integer depId)
    {
        setName(name);
        setId(id);
        setPret(pret);
        setDepId(depId);
    }

    public String toString()
    {
        String s = "";
        String aux = String.format("%.2f",this.pret);
        s = this.name + ";" + this.id + ";" + aux;
        return s;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof Item))
            return false;
        if(((Item) o).pret == pret && ((Item) o).id == id && ((Item) o).name.equals(name) && ((Item) o).depId ==depId)
            return true;
        else
            return false;
    }
}
