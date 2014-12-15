package windows;

import java.awt.Window;

public class Error extends javax.swing.JFrame {

    public Error( Window window ) {
        this.initComponents();
        this.setLocationRelativeTo( window );
        this.setVisible( true );
        this.errorMessage.setText( "Error occured!" );
    }

    public Error( String errorMessage, Window window ) {
        this( window );
        this.errorMessage.setText( "Error: " + errorMessage );
    }

    private void initComponents() {

        okButton = new javax.swing.JButton();
        errorMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Error");
        setName("errorFrame");
        setResizable(false);

        okButton.setFont(new java.awt.Font("Tahoma", 0, 15));
        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        errorMessage.setFont(new java.awt.Font("Tahoma", 0, 14));
        errorMessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconError.png")));
        errorMessage.setText("Error: no error");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(errorMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose(); // Pressing OK destroys the error message window
    }

    private javax.swing.JLabel errorMessage;
    private javax.swing.JButton okButton;
}
