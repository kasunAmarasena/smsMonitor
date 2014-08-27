/*
 * Copyright (C) 2014 Kasun Amarasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.viewer;

import com.control.controller.ProcessRunner;
import com.control.controller.Utility;
import com.control.helper.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.model.Query;
import java.awt.Color;
import java.awt.Font;
import static java.lang.Thread.sleep;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import javax.comm.CommPortIdentifier;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

/**
 *
 * @author Kasun Amarasena
 */
public class MainUI extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");
    private ProcessRunner processRunner;
    private String comport;
    private boolean isConnected = false;
    private int pageCount; //To load the to the table    
    private int rowCount; //number of data records in the database
    private List<Integer> newRows = new ArrayList<>();

    /**
     * Creates Main UI of the SMS monitor application
     */
    public MainUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        MessageDialogBox.setParentComponent(this);
        datePanel1.setMinDate();
        datePanel2.setCurrentDate();

        tableModel = (DefaultTableModel) jTable.getModel();

        loadData(1, 15);
        pageCount = 1;
        rowCount = Query.rowCount();
        if (rowCount > 15) {
            olderButton.setEnabled(true);
        }
    }

    /**
     * Load data to JTable from Database (index starting from 1) ex: Load first
     * 15 rows -> loadData(1,15)
     *
     * @param from int starting row
     * @param to int finishing row
     */
    private void loadData(int from, int to) {
        from--;
        ResultSet r;
        try {
            r = Query.select();
            for (int i = 0; r.next() && i < to; i++) {
                if (from <= i) {
                    String[] vector = new String[4];
                    vector[0] = Helper.formatPhoneNo(r.getString(1));
                    vector[1] = r.getString(4);
                    Date d = r.getDate(2);
                    vector[2] = d.toString();
                    vector[3] = timeFormat.format(r.getTime(3));
                    tableModel.addRow(vector);
                }
                if (to == 15) {
                    Timestamp stamp = r.getTimestamp(5);
                    if (isNew(stamp)) {
                        newRows.add(i);
                    }
                }
            }
            if (to == 15) {
                flashRow();
            }

        } catch (SQLException ex) {
            Logger.printError(this.getClass().getName(), "loadData", ex.toString()); //logger            
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        datePanel1 = new com.viewer.DatePanel();
        datePanel2 = new com.viewer.DatePanel();
        jLabel1 = new javax.swing.JLabel();
        filterButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        olderButton = new javax.swing.JButton();
        newerButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        connectMenuItem = new javax.swing.JMenuItem();
        disconnectMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SMS Monitor 1.0v");
        setBackground(new java.awt.Color(153, 204, 255));
        setMinimumSize(new java.awt.Dimension(748, 560));
        setResizable(false);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), null));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(450, 410));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " From", " Message", "         Date", "        Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.setMinimumSize(new java.awt.Dimension(610, 200));
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);
        if (jTable.getColumnModel().getColumnCount() > 0) {
            jTable.getColumnModel().getColumn(0).setMinWidth(120);
            jTable.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTable.getColumnModel().getColumn(0).setMaxWidth(150);
            jTable.getColumnModel().getColumn(2).setMinWidth(90);
            jTable.getColumnModel().getColumn(2).setPreferredWidth(90);
            jTable.getColumnModel().getColumn(2).setMaxWidth(90);
            jTable.getColumnModel().getColumn(3).setMinWidth(95);
            jTable.getColumnModel().getColumn(3).setPreferredWidth(95);
            jTable.getColumnModel().getColumn(3).setMaxWidth(95);
        }

        jTextArea.setEditable(false);
        jTextArea.setColumns(20);
        jTextArea.setRows(4);
        jScrollPane2.setViewportView(jTextArea);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search/Filter messages", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 0)));

        jLabel1.setText("   To");

        filterButton.setText("Filter");
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(datePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(filterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datePanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(filterButton)
                                    .addComponent(resetButton))
                                .addComponent(datePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        statusLabel.setFont(new java.awt.Font("Source Code Pro", 1, 12)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(255, 0, 0));
        statusLabel.setText("STATUS: DISCONNECTED");

        deleteButton.setText("Delete Messages");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        olderButton.setText("Older >>");
        olderButton.setToolTipText("");
        olderButton.setEnabled(false);
        olderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                olderButtonActionPerformed(evt);
            }
        });

        newerButton.setText("<< Newer");
        newerButton.setEnabled(false);
        newerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newerButtonActionPerformed(evt);
            }
        });

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        connectMenuItem.setText("Connect");
        connectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(connectMenuItem);

        disconnectMenuItem.setText("Disconnect");
        disconnectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(disconnectMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(newerButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(olderButton))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(statusLabel)
                        .addGap(8, 8, 8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusLabel)
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(olderButton)
                            .addComponent(newerButton))
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        if (processRunner != null) {
            processRunner.stop();
        }
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        deleteButton.setEnabled(true);
        int row = jTable.getSelectedRow();
        String msg = (String) tableModel.getValueAt(row, 1);
        jTextArea.setText(msg);
    }//GEN-LAST:event_jTableMouseClicked

    private void connectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectMenuItemActionPerformed
        if (isConnected()) {
            MessageDialogBox.showMessage("You have already connected to " + getComport() + ". To connect to a different \nport, disconnect the current connection and try again.", "");
            return;
        }
        Object input = JOptionPane.showInputDialog(this, "Select COM port", "Connect", JOptionPane.PLAIN_MESSAGE, null, Utility.getAvailableComports().toArray(), "");
        if (input != null) {
            setComport((String) input);
            processRunner = new ProcessRunner(getComport());
            processRunner.start();
            statusLabel.setFont(new Font("Source Code Pro", Font.BOLD, 12));
            statusLabel.setText("STATUS: CONNECTED   ");
            statusLabel.setForeground(new Color(76, 201, 46));

            isConnected = true;
            listen(true); //start database listener
        }
    }//GEN-LAST:event_connectMenuItemActionPerformed

    private void disconnectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectMenuItemActionPerformed
        if (processRunner != null) {
            processRunner.stop();
            statusLabel.setText("STATUS: DISCONNECTED");
            statusLabel.setForeground(Color.red);
            isConnected = false;
            listen(false); //stop the database listener
        }
    }//GEN-LAST:event_disconnectMenuItemActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        deleteButton.setEnabled(false);
        int[] rows = jTable.getSelectedRows();
        if (MessageDialogBox.isConfirmed()) {
            for (int i : rows) {
                String num = Helper.reFormatPhoneNo((String) tableModel.getValueAt(i, 0));
                Date date = Helper.stringToDate((String) tableModel.getValueAt(i, 2));
                Time time = Helper.stringToTime((String) tableModel.getValueAt(i, 3));  
                //System.out.println("phone:"+ num+"  date: "+date+ "  time: "+time); //testing...............
                try {
                    Query.deleteRecord(num, date, time);
                    //tableModel.removeRow(i);
                } catch (SQLException ex) {                    
                    Logger.printError(this.getClass().getName(), "deleteButtonActionPerformed", ex.toString()); //logger
                }
            }
            removeAllRows();
            loadData(pageCount * 15 - 14, pageCount * 15);
            rowCount = Query.rowCount();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        removeAllRows();
        pageCount = 1;
        loadData(pageCount * 15 - 14, pageCount * 15);
        if (rowCount > pageCount * 15) {
            olderButton.setEnabled(true);
        }

    }//GEN-LAST:event_resetButtonActionPerformed

    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterButtonActionPerformed
        Date from = datePanel1.getDate();
        Date to = datePanel2.getDate();
        removeAllRows();
        if (from.after(to)) {            
            return;
        }
        ResultSet r;
        try {
            r = Query.select(from, to);
            for (int i = 0; r.next(); i++) {
                String[] vector = new String[4];
                vector[0] = Helper.formatPhoneNo(r.getString(1));
                vector[1] = r.getString(4);
                Date d = r.getDate(2);
                vector[2] = d.toString();
                vector[3] = timeFormat.format(r.getTime(3));
                tableModel.addRow(vector);
            }
        } catch (SQLException ex) {
            Logger.printError(this.getClass().getName(), "filterButtonActionPerformed", ex.toString());
        }
        newerButton.setEnabled(false);
        olderButton.setEnabled(false);
    }//GEN-LAST:event_filterButtonActionPerformed

    private void newerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newerButtonActionPerformed
        removeAllRows();
        pageCount--;
        loadData(pageCount * 15 - 14, pageCount * 15);
        if (pageCount == 1) {
            newerButton.setEnabled(false);
        }
        if (rowCount > pageCount * 15) {
            olderButton.setEnabled(true);
        }


    }//GEN-LAST:event_newerButtonActionPerformed

    private void olderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_olderButtonActionPerformed
        removeAllRows();
        pageCount++;
        loadData(pageCount * 15 - 14, pageCount * 15);
        newerButton.setEnabled(true);
        if (rowCount < pageCount * 15) {
            olderButton.setEnabled(false);
        }
        newRows.clear();
    }//GEN-LAST:event_olderButtonActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        MessageDialogBox.aboutPage();
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {

                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    //javax.swing.UIManager.setLookAndFeel();
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem connectMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private com.viewer.DatePanel datePanel1;
    private com.viewer.DatePanel datePanel2;
    private javax.swing.JButton deleteButton;
    private javax.swing.JMenuItem disconnectMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton filterButton;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton newerButton;
    private javax.swing.JButton olderButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * @return String name of the comport
     */
    public String getComport() {
        return comport;
    }

    /**
     * @param comport String name of the comport to set
     */
    public void setComport(String comport) {
        this.comport = comport;
    }

    /**
     * @return boolean true if a connection is already established, false
     * otherwise
     */
    public boolean isConnected() {
        return isConnected;
    }

    private void removeAllRows() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }

    /**
     * Listens to database change and update JTable only if pageCount == 1
     */
    private void listen(boolean flag) {
        DatabaseChangeListener listener = null;
        if (flag) {
            listener = new DatabaseChangeListener();
            listener.start();
        } else if (listener != null) {
            listener.interrupt();
        }
    }

    /**
     *
     * @param stamp
     * @return true if currentStamp is later than given stamp
     */
    private boolean isNew(Timestamp stamp) {
        java.util.Date now = Calendar.getInstance().getTime();
        Timestamp currentStamp = new Timestamp(now.getTime());
        int min = currentStamp.getMinutes();
        currentStamp.setMinutes(min - 2);
        return currentStamp.before(stamp);
    }

    /**
     * Flash the rows containing the newly received messages for 30 seconds
     */
    private void flashRow() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                colorRows(Color.YELLOW);
                jTable.repaint();
                while (true) {
                    try {
                        Thread.sleep(30000);
                        break;
                    } catch (InterruptedException ex) {
                        Logger.printError(this.getClass().getName(), "flashRow", ex.toString()); //logger
                        return;
                    }
                }
                reColorRows();
                jTable.repaint();
            }
        }).start();
    }

    /**
     * This is Simple DatabaseChangeListner Implementation which is Thread that
     * continuously read data from LISTENER table which is triggered by the
     * insertion of new records to INFO table. LISTENER table has one column of
     * boolean datatype. *
     */
    private class DatabaseChangeListener extends Thread {

        @Override
        public void run() {
            while (true) {
                ResultSet r;
                try {
                    r = Query.selectFromListener();
                    if (r.next()) {
                        //Load data to the JTable only if first page
                        if (pageCount == 1) {
                            newRows.clear();
                            removeAllRows();
                            loadData(1, 15);
                            Query.emptyListenerTable();
                        } else {
                            //
                        }
                    }
                    try {
                        sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.printInfo(this.getClass().getName(), "DatabaseChangeListener.run", "DatabaseListener stopped:" + ex); //logger
                    }
                } catch (SQLException ex) {
                    Logger.printError(this.getClass().getName(), "DatabaseChangeListener.run", ex.toString()); //logger            
                }
            }
        }
    }

    private void colorRows(Color color) {

        Enumeration<TableColumn> enumCols = jTable.getColumnModel().getColumns();
        while (enumCols.hasMoreElements()) {
            enumCols.nextElement().setCellRenderer(new MyTableCellRenderer(newRows, color));
        }
    }

    private void reColorRows() {

        Enumeration<TableColumn> enumCols = jTable.getColumnModel().getColumns();
        while (enumCols.hasMoreElements()) {
            enumCols.nextElement().setCellRenderer(new DefaultTableCellRenderer());
        }

    }

}
