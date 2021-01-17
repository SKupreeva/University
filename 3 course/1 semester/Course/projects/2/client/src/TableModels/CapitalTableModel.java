package TableModels;

import AllData.CapitalConsumption;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class CapitalTableModel implements TableModel {
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private List<CapitalConsumption> depreciations;

    public CapitalTableModel(List<CapitalConsumption> depreciations) {
        this.depreciations = depreciations;
    }

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 5;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return "Наименование";
        case 1:
            return "Годовой процент";
        case 2:
            return "Годовая цена";
        case 3:
            return "Месячный процент";
        case 4:
            return "Месячная цена";
        }
        return "";
    }

    public int getRowCount() {
        return depreciations.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        CapitalConsumption depreciation = depreciations.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return depreciation.getNmae();
        case 1:
            return depreciation.getYearPercent();
        case 2:
            return depreciation.getYearPrice();
        case 3:
            return depreciation.getMonthPercent();
        case 4:
            return depreciation.getMonthPrice();
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
