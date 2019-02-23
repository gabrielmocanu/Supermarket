import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelCustomer extends AbstractTableModel {
    private static final int COLUMN_NAME = 0;
    private static final int COLUMN_ID = 1;
    private static final int COLUMN_PRICE = 2;
    private static final int COLUMN_DEP_ID = 3;
    private static final long serialVersionUID = 2;

    private String[] columnNames = {"Name" , "ID", "PRICE", "DepID"};


    private ItemList listItems;

    public TableModelCustomer(ItemList a)
    {
        listItems = a;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount()
    {
        return listItems.getNrElements();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (listItems.getNrElements() == 0) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item item = listItems.getItem(rowIndex);
        Object returnValue = null;

        if(item == null)
            return returnValue;

        switch (columnIndex) {
            case COLUMN_NAME:
                returnValue = item.getName();
                break;
            case COLUMN_ID:
                returnValue = item.getId();
                break;
            case COLUMN_PRICE:
                returnValue = item.getPret();
                break;
            case COLUMN_DEP_ID:
                returnValue = item.getDepId();
                break;
            default:
                throw new IllegalArgumentException("Invalid column index");
        }

        return returnValue;
    }

}
