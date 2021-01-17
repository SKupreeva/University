package MyForms;

import TableModels.CapitalTableModel;
import TableModels.AssetTableModel;
import TableModels.UserTableModel;
import AllData.Asset;
import AllData.User;
import AllData.CapitalConsumption;
import ClientWork.SocketStream;
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

public class MainAdminFrame extends javax.swing.JFrame {
    private TableModel asset;
    private TableModel capital;
    private TableModel users;
    
    public MainAdminFrame() {
        try {
            setAssetModel ();
            setUserModel ();
            String sql = "SELECT * FROM depreciation";
            setCapitalConsumptionModel (sql);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
    }

    
    public void setAssetModel () throws IOException{
        ArrayList<Asset> assets = new ArrayList<Asset>();
        SocketStream server = new SocketStream();
        server.sendInt(3);
        int count = server.getInt();
        for(int i = 0; i < count; i++){
            String name = server.getString();
            int price = server.getInt();
            int term = server.getInt();
            assets.add(new Asset(name, price, term));
        }
	asset = new AssetTableModel(assets);
    }
    public void TableChange(){
        getContentPane().removeAll();
        initComponents();
    }
    public void turnTo (int i){
        jTabbedPane1.setSelectedIndex(i);
    }

    
    public void setUserModel () throws IOException{
        ArrayList<User> users = new ArrayList<User>();
        SocketStream server = new SocketStream();
        server.sendInt(7);
        int count = server.getInt();
        for(int i = 0; i < count; i++){
            String login = server.getString();
            String password = server.getString();
            int status = server.getInt();
            users.add(new User(login, password, status));
        }
	this.users = new UserTableModel(users);
    }

    
    public void setCapitalConsumptionModel (String sqlString) throws IOException{
        ArrayList<CapitalConsumption> capitalCons = new ArrayList<CapitalConsumption>();
        SocketStream server = new SocketStream();
        server.sendInt(9);
        server.sendString(sqlString);
        int count = server.getInt();
        System.out.println(count);
        for(int i = 0; i < count; i++){
            String name = server.getString();
            float yearPercent = (float)Double.parseDouble(server.getString());
            float yearPrice = (float)Double.parseDouble(server.getString());
            float monthPercent = (float)Double.parseDouble(server.getString());
            float monthPrice = (float)Double.parseDouble(server.getString());
            capitalCons.add(new CapitalConsumption(name, yearPercent, yearPrice, monthPercent, monthPrice));
        }
	capital = new CapitalTableModel(capitalCons);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton8 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Администратор");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable1.setModel(asset);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Удалить");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText("Изменить");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setText("Добавить");
        jButton4.setToolTipText("");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton13.setText("Подробнее");
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Основные фонды", jPanel1);

        jTable3.setModel(capital);
        jScrollPane3.setViewportView(jTable3);

        jTabbedPane2.setName(""); // NOI18N

        jLabel1.setText("Сортировка по:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "сбросить", "наименованию", "годовому проценту", "годовой цене", "месячному проценту", "месячной цене" }));

        jCheckBox1.setText("Обратный порядок");

        jButton8.setText("Сортировать");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8))
                    .addComponent(jCheckBox1))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Сортировка", jPanel4);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Сбросить", "Годовой процент", "Годовая цена", "Месячный процент", "Месячная цена" }));

        jLabel2.setText("от");

        jLabel3.setText("до");

        jButton9.setText("Поиск");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, 0, 154, Short.MAX_VALUE)
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addGap(23, 23, 23))
        );

        jTabbedPane2.addTab("Фильтрация", jPanel5);

        jLabel4.setText("Введите наименование:");

        jButton10.setText("Сбросить");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
        });

        jButton11.setText("Поиск");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(29, 29, 29)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Поиск", jPanel6);

        jButton12.setText("Подробнее");
        jButton12.setActionCommand("");
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton12MouseClicked(evt);
            }
        });

        jButton14.setText("Создать отчет");
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton14MouseClicked(evt);
            }
        });

        jButton15.setText("Построить диаграмму");
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(jButton15)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton12)
                            .addComponent(jButton15))
                        .addGap(18, 18, 18)
                        .addComponent(jButton14)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Амортизация", jPanel2);

        jTable2.setModel(users);
        jTable2.setGridColor(new java.awt.Color(0, 0, 0));
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        jButton5.setText("Удалить");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jButton6.setText("Изменить статус");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jButton7.setText("Добавить");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(203, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(87, 87, 87)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(156, 156, 156))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83))
        );

        jTabbedPane1.addTab("Пользователи", jPanel3);

        jButton1.setText("Выход");
        jButton1.setActionCommand("");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        SocketStream server = new SocketStream();
        server.sendString("END");
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        this.setVisible(false);
        SigningInFrame SigningIn = new SigningInFrame();
        SigningIn.setVisible(true);
        SigningIn.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton1MouseClicked
  
    
    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        AddNewAssetFrame asset = new AddNewAssetFrame();
        asset.setVisible(true);
        asset.setLocationRelativeTo(null);
        asset.admin = this;
    }//GEN-LAST:event_jButton4MouseClicked

    
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if(jTable1.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Необходимо выбрать запись!",
                        "Ошибка удаления",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String name = (String)jTable1.getValueAt(jTable1.getSelectedRow(), 0);
        int price = (int)jTable1.getValueAt(jTable1.getSelectedRow(), 1);
        int term = (int)jTable1.getValueAt(jTable1.getSelectedRow(), 2);
        
        SocketStream server = new SocketStream();
        String sqlString = "SELECT id FROM fixed_assets WHERE `name`='"+name+"' AND `price`='"+price+"' AND `term_of_use`='"+term+"'";
        server.sendInt(10);
        server.sendString(sqlString);
        
        sqlString = "DELETE FROM fixed_assets WHERE `name`='"+name+"' AND `price`='"+price+"' AND `term_of_use`='"+term+"'";
        server.sendInt(5);
        server.sendString(sqlString);
        JOptionPane.showMessageDialog(null, "Данные удалены.",
                        "Удаление",JOptionPane.INFORMATION_MESSAGE);
        try {
            this.setAssetModel ();
            sqlString = "SELECT * FROM depreciation";
            this.setCapitalConsumptionModel (sqlString);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.TableChange();
    }//GEN-LAST:event_jButton2MouseClicked

    
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        if(jTable1.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Необходимо выбрать запись!",
                        "Ошибка редактирования",JOptionPane.INFORMATION_MESSAGE);
            return;
            
        }
        String name = (String)jTable1.getValueAt(jTable1.getSelectedRow(), 0);
        int price = (int)jTable1.getValueAt(jTable1.getSelectedRow(), 1);
        int term = (int)jTable1.getValueAt(jTable1.getSelectedRow(), 2);
        ChangeAssetDataFrame change = new ChangeAssetDataFrame(name, price, term, this);
        change.setVisible(true);
        change.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_jButton3MouseClicked

    
    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        AddNewUserFrame user = new AddNewUserFrame();
        user.setVisible(true);
        user.setLocationRelativeTo(null);
        user.admin = this;
    }//GEN-LAST:event_jButton7MouseClicked


    
    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        if(jTable2.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Необходимо выбрать запись!",
                        "Ошибка удаления",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String login = (String)jTable2.getValueAt(jTable2.getSelectedRow(), 0);
        String password = (String)jTable2.getValueAt(jTable2.getSelectedRow(), 1);
        String str = (String)jTable2.getValueAt(jTable2.getSelectedRow(), 2);
        int status;
        if(str.equals("Администратор"))
            status = 1;
        else
            status = 0;
        String sqlString = "DELETE FROM users WHERE `login`='"+login+"' AND `password`='"+password+"' AND `status`='"+status+"'";
        SocketStream server = new SocketStream();
        server.sendInt(5);
        server.sendString(sqlString);
        JOptionPane.showMessageDialog(null, "Данные удалены.",
                        "Удаление",JOptionPane.INFORMATION_MESSAGE);
        try {
            this.setUserModel ();
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.TableChange();
        this.turnTo(2);
    }//GEN-LAST:event_jButton5MouseClicked


    
    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        if(jTable2.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Необходимо выбрать запись!",
                        "Ошибка редактирования",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String login = (String)jTable2.getValueAt(jTable2.getSelectedRow(), 0);
        String str = (String)jTable2.getValueAt(jTable2.getSelectedRow(), 2);
        String sqlString;
        if(str.equals("Администратор"))
            sqlString = "UPDATE users SET `status`='0' WHERE login='"+login+"'";
        else
            sqlString = "UPDATE users SET `status`='1' WHERE login='"+login+"'";
        SocketStream server = new SocketStream();
        server.sendInt(5);
        server.sendString(sqlString);
        JOptionPane.showMessageDialog(null, "Статус изменен.",
                        "Редактирование",JOptionPane.INFORMATION_MESSAGE);
        try {
            this.setUserModel ();
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.TableChange();
        this.turnTo(2);
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        if(jTable3.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Необходимо выбрать запись!",
                        "Ошибка!",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String name = (String)jTable3.getValueAt(jTable3.getSelectedRow(), 0);
        ViewAssetFrame view = new ViewAssetFrame(name);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        return;
    }//GEN-LAST:event_jButton12MouseClicked

    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseClicked
        if(jTable1.getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Необходимо выбрать запись!",
                        "Ошибка!",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String name = (String)jTable1.getValueAt(jTable1.getSelectedRow(), 0);
        int price = (int)jTable1.getValueAt(jTable1.getSelectedRow(), 1);
        int term = (int)jTable1.getValueAt(jTable1.getSelectedRow(), 2);
        ViewCapitalConsumptionFrame view = new ViewCapitalConsumptionFrame(name, price, term);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        return;
    }//GEN-LAST:event_jButton13MouseClicked
    ///////////////////////////сортировка///////////////////////////
    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
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
            setCapitalConsumptionModel (sqlString);
            TableChange();
            turnTo (1);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8MouseClicked
   
    
    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        String min = jTextField1.getText();
        String max = jTextField2.getText();
        String sqlString = "";
        String var = "";
        int choise = jComboBox2.getSelectedIndex();
        switch(choise){
            case 0:
                sqlString = "SELECT * FROM depreciation";
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
            sqlString = "SELECT * FROM depreciation WHERE "+var+">="+min+" AND "+var+"<="+max;
        }
        try {
            setCapitalConsumptionModel (sqlString);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableChange();
        turnTo (1);
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_jButton9MouseClicked

    
    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        String name = jTextField3.getText();
        if(name.length() == 0){
            JOptionPane.showMessageDialog(null, "Необходимо ввести данные для поиска!",
                        "!!!",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String sqlString = "SELECT * FROM depreciation WHERE name='"+name+"'";
        try {
            setCapitalConsumptionModel (sqlString);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableChange();
        turnTo (1);
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        String sqlString = "SELECT * FROM depreciation";
        try {
            setCapitalConsumptionModel (sqlString);
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableChange();
        turnTo (1);
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jButton10MouseClicked

    
    private void jButton14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseClicked
        int count = jTable3.getRowCount();
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
                String name = (String)jTable3.getValueAt(i, 0);
                float d1 = (float)jTable3.getValueAt(i, 1);
                float d2 = (float)jTable3.getValueAt(i, 2);
                float d3 = (float)jTable3.getValueAt(i, 3);
                float d4 = (float)jTable3.getValueAt(i, 4);
                String text = name+"\t\t\t"+d1+"\t\t\t"+d2+"\t\t\t"+d3+"\t\t\t\t"+d4;
                writer.println(text);
            }
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(MainAdminFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Отчет составлен.",
                        "Создание отчета",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton14MouseClicked

    
    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseClicked
        int count = jTable3.getRowCount();
        if(count == 0){
            JOptionPane.showMessageDialog(null, "Отсутствуют данные!",
                        "Ошибка!",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        DefaultPieDataset pie = new DefaultPieDataset();
        for (int i = 0; i < count; i++){
            String name = (String)jTable3.getValueAt(i, 0);
            float dd = (float)jTable3.getValueAt(i, 2);
            pie.setValue(name, new Float (dd));
        }
        JFreeChart chart = ChartFactory.createPieChart("Диаграмма годовой стоимости амортизации", pie, true, true, true);
        PiePlot P =(PiePlot)chart.getPlot();
        ChartFrame frame = new ChartFrame("Амортизация", chart);
        frame.setVisible(true);
        frame.setSize(600, 500);
    }//GEN-LAST:event_jButton15MouseClicked
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
                new MainAdminFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
