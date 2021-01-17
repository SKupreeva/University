package model.client;

import view.EnterDialog;

public class StartDO {
    public static void main(String[] args) {
        EnterDialog dialog = new EnterDialog();
        dialog.setTitle("Авторизация");
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        //System.exit(0);
    }
}
