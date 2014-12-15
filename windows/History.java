package windows;

import list.AbstractListModel;
import javax.swing.ImageIcon;

public class History extends javax.swing.JFrame {

    private final Calculator calculator;
    private final AbstractListModel listModel;

    public History( Calculator calculator, AbstractListModel listModel ) {
        this.calculator = calculator;
        this.listModel = listModel;

        this.initComponents();
        this.setIconImage( new ImageIcon( "icons/iconCalculator.png" ).getImage() );
        this.setLocationRelativeTo( calculator );
        this.setVisible( true );
    }

    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        historyList = new javax.swing.JList();
        buttonSelectFromList = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("History");

        historyList.setFont(new java.awt.Font("Tahoma", 0, 12));
        historyList.setModel(this.listModel);
        historyList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        historyList.setToolTipText("");
        scrollPane.setViewportView(historyList);

        buttonSelectFromList.setFont(new java.awt.Font("Tahoma", 0, 16));
        buttonSelectFromList.setText("Select item");
        buttonSelectFromList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectFromListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(346, Short.MAX_VALUE)
                .addComponent(buttonSelectFromList, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSelectFromList, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void buttonSelectFromListActionPerformed(java.awt.event.ActionEvent evt) {
        // Select-button pressed, loads the selected input from a list of history items
        String selectedValue = (String)this.historyList.getSelectedValue();
        this.calculator.calculateStringAndSetText( selectedValue );
        this.dispose();
    }

    private javax.swing.JButton buttonSelectFromList;
    private javax.swing.JList historyList;
    private javax.swing.JScrollPane scrollPane;
}
