import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    private final int COLUMN_NAME = 0;
    private final int COLUMN_ID = 1;
    private final int COLUMN_PRICE = 2;
    private static final long serialVersionUID = 4;

    private String[] columnNames = {"Name" , "ID", "PRICE"};

    private ItemList listItems;

    public TableModel(ItemList a)
    {
        listItems = a;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() { return listItems.getNrElements(); }

    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    public Class<?> getColumnClass(int columnIndex) {
        if (listItems.getNrElements() == 0) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Item item = listItems.getItem(rowIndex);
        Object returnValue = null;

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
            default:
                throw new IllegalArgumentException("Invalid column index");
        }

        return returnValue;
    }

}
