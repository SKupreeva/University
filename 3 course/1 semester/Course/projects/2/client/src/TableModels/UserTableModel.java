package TableModels;

import AllData.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class UserTableModel implements TableModel {
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private List<User> users;

    public UserTableModel(List<User> users) {
        this.users = users;
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
            return "Логин";
        case 1:
            return "Пароль";
        case 2:
            return "Статус";
        }
        return "";
    }

    public int getRowCount() {
        return users.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return user.getLogin();
        case 1:
            return user.getPassword();
        case 2:
            if(user.getStatus() == 1)
                return "Администратор";
            else
                return "Пользователь";
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
