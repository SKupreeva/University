package MyForms;

import ClientWork.SocketStream;
import AllData.CapitalConsumption;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import TableModels.CapitalTableModel;

public class MainUserFrame extends javax.swing.JFrame {
    
    private TableModel model;
    
    public MainUserFrame() {
        try {
            String sqlString = "SELECT * FROM depreciation";
            setDepreciationModel (sqlString);
        } catch (IOException ex) {
            Logger.getLogger(MainUserFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
    }

    public void setDepreciationModel (String sqlString) throws IOException{
        ArrayList<CapitalConsumption> depreciations = new ArrayList<CapitalConsumption>();
        SocketStream server = new SocketStream();
        server.sendInt(9);
        server.sendString(sqlString);
        int count = server.getInt();
        for(int i = 0; i < count; i++){
            String name = server.getString();
            float yearPercent = (float)Double.parseDouble(server.getString());
            float yearPrice = (float)Double.parseDouble(server.getString());
            float monthPercent = (float)Double.parseDouble(server.getString());
            float monthPrice = (float)Double.parseDouble(server.getString());
            depreciations.add(new CapitalConsumption(name, yearPercent, yearPrice, monthPercent, monthPrice));
        }
	model = new CapitalTableModel(depreciations);
    }
    
    public void ChangeTable(){
        getContentPane().removeAll();
        initComponents();
    }
    public void turnTo (int i){
        jTabbedPane1.setSelectedIndex(i);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("окно бухгалтера");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Сортировка по");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "сбросить", "наименованию", "годовому проценту", "годовой цене", "месячному проценту", "месячной цене" }));

        jButton3.setText("Сортировать");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jCheckBox1.setText("Обратный порядок");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Сортировка", jPanel1);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "сбросить", "годовому проценту", "годовой цене", "месячному проценту", "месячной цене" }));

        jLabel2.setText("от");

        jLabel3.setText("до");

        jButton4.setText("Поиск");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox2, 0, 146, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Фильтрация", jPanel2);

        jLabel4.setText("Введите наименование:");

        jButton5.setText("Сбросить");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jButton6.setText("Поиск");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Поиск", jPanel3);

        jButton1.setText("Подробнее");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Выйти");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton7.setText("Создать отчет");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });

        jButton8.setText("Построить диаграмму");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton7)
                            .addComponent(jButton8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        SocketStream server = new SocketStream();
        server.sendString("END");
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        this.setVisible(false);
        SigningInFrame SigningIn = new SigningInFrame();
        SigningIn.setVisible(true);
        SigningIn.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if(jTable1.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Необходимо выбрать запись!",
                        "Ошибка!",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String name = (String)jTable1.getValueAt(jTable1.getSelectedRow(), 0);
        ViewAssetFrame view = new ViewAssetFrame(name);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        return;
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        try {
            String sqlString = "SELECT * FROM depreciation ORDER BY ";
            String desc = "";
            if(jCheckBox1.isSelected()){
                desc = " DESC";
            }
            switch(jComboBox1.getSelectedIndex()){
                case 0:
                    sqlString = "SELECT * FROM depreciation";
                    break;
                case 1:
                    sqlString += "name"+desc;
                    break;
                case 2:
                    sqlString += "year_proz"+desc;
                    break;
                case 3:
                    sqlString += "year_price"+desc;
                    break;
                case 4:
                    sqlString += "month_proz"+desc;
                    break;
                case 5:
                    sqlString += "month_price"+desc;
                    break;
            }
            setDepreciationModel (sqlString);
            ChangeTable();
            turnTo (0);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        String min = jTextField1.getText();
        String max = jTextField2.getText();
        String sqlSting = "";
        String var = "";
        int choise = jComboBox2.getSelectedIndex();
        switch(choise){
            case 0:
                sqlSting = "SELECT * FROM depreciation";
                System.out.println(123);
                break;
            case 1:
                var = "year_proz";
                break;
            case 2:
                var = "year_price";
                break;
            case 3:
                var = "month_proz";
                break;
            case 4:
                var = "month_price";
                break;
        }
        
        if(choise > 0){
            if(min.length() == 0 || max.length() == 0){
                
                JOptionPane.showMessageDialog(null, "Не все поля заполнены!",
                        "Ошибка!",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if(!checkString(min) || !checkString(max) || Double.parseDouble(min) > Double.parseDouble(max)){
                JOptionPane.showMessageDialog(null, "Введены неверные данные!",
                        "Ошибка!",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            sqlSting = "SELECT * FROM depreciation WHERE "+var+">="+min+" AND "+var+"<="+max;
        }
        try {
            setDepreciationModel (sqlSting);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChangeTable();
        turnTo (1);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        String name = jTextField3.getText();
        if(name.length() == 0){
            JOptionPane.showMessageDialog(null, "Необходимо ввести данные для поиска!",
                        "!!!",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String sqlString = "SELECT * FROM depreciation WHERE name='"+name+"'";
        try {
            setDepreciationModel (sqlString);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChangeTable();
        turnTo (2);
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        String sqlString = "SELECT * FROM depreciation";
        try {
            setDepreciationModel (sqlString);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ChangeTable();
        turnTo (2);
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        int count = jTable1.getRowCount();
        if(count == 0){
            JOptionPane.showMessageDialog(null, "Отсутствуют данные для отчета!",
                        "Ошибка!",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream("otchet.txt"));
            String header = "Наименование\t\tГодовой процент\t\tГодовая стоимость\t\tМесячный процент\t\tМесячная стоимость";
            writer.println(header);
            for (int i = 0; i < count; i++){
                String name = (String)jTable1.getValueAt(i, 0);
                float dd1 = (float)jTable1.getValueAt(i, 1);
                float dd2 = (float)jTable1.getValueAt(i, 2);
                float dd3 = (float)jTable1.getValueAt(i, 3);
                float dd4 = (float)jTable1.getValueAt(i, 4);
                String textStr = name+"\t\t\t"+dd1+"\t\t\t"+dd2+"\t\t\t"+dd3+"\t\t\t\t"+dd4;
                writer.println(textStr);
            }
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Отчет составлен.",
                        "Создание отчета",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        int count = jTable1.getRowCount();
        if(count == 0){
            JOptionPane.showMessageDialog(null, "Отсутствуют данные!",
                        "Ошибка!",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        DefaultPieDataset pie = new DefaultPieDataset();
        for (int i = 0; i < count; i++){
            String name = (String)jTable1.getValueAt(i, 0);
            float dd2 = (float)jTable1.getValueAt(i, 2);
            pie.setValue(name, new Float (dd2));
        }
        JFreeChart chart = ChartFactory.createPieChart("Диаграмма годовой стоимости амортизации", pie, true, true, true);
        PiePlot P =(PiePlot)chart.getPlot();
        ChartFrame frame = new ChartFrame("Амортизация", chart);
        frame.setVisible(true);
        frame.setSize(600, 500);
    }//GEN-LAST:event_jButton8MouseClicked
    
    public boolean checkString(String string) {
         if (string == null || string.length() == 0) return false;
 
         int i = 0;
         if (string.charAt(0) == '-') {
            if (string.length() == 1) {
               return false;
            }
            i = 1;
         }
         int punktAmt = 0;
         char c;
         for (; i < string.length(); i++) {
             c = string.charAt(i);
             if (!((c >= '0' && c <= '9')||c=='.')) {
                 return false;
             }
             if(c=='.'){
                 punktAmt++;
             }
         }
         if(punktAmt > 1)
             return false;
         return true;
     }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUserFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
