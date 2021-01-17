package TableModels;

import javax.swing.table.TableModel;
import AllData.Asset;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class AssetTableModel  implements TableModel {
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private List<Asset> assets;

    public AssetTableModel(List<Asset> assets) {
        this.assets = assets;
    }

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 3;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return "Наименование";
        case 1:
            return "Стоимость OC (руб.)";
        case 2:
            return "Срок полезного использования OC (лет)";
        }
        return "";
    }

    public int getRowCount() {
        return assets.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Asset asset = assets.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return asset.getName();
        case 1:
            return asset.getPrice();
        case 2:
            return asset.getTermUse();
        }
        return "";
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }
}
