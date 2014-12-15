package windows;

import java.awt.Desktop;
import java.awt.Window;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class About extends javax.swing.JFrame {

    public About() {
        // Location not relative
        this.initComponents();
        this.setLocationRelativeTo( null );
        this.initLabels();
        this.setVisible( true );
    }

    public About( Window window ) {
        // Location relative to Window
        this.initComponents();
        this.setLocationRelativeTo( window );
        this.initLabels();
        this.setVisible( true );
    }

    private void initComponents() {

        labelVersion = new javax.swing.JLabel();
        labelAuthor = new javax.swing.JLabel();
        labelHeader = new javax.swing.JLabel();
        buttonWebsite = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About");
        setName("aboutFrame");
        setResizable(false);

        labelVersion.setText("Version: X.XX ");

        labelAuthor.setText("Author: XXXX");

        labelHeader.setFont(new java.awt.Font("Tahoma", 0, 24));
        labelHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHeader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconCalculator.png")));
        labelHeader.setText("String Calculator");

        buttonWebsite.setText("Website");
        buttonWebsite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonWebsiteActionPerformed(evt);
            }
        });

        buttonClose.setText("Close");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAuthor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelVersion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelHeader, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                                .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelVersion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAuthor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }

    private void buttonWebsiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonWebsiteActionPerformed
        try {
            // Open authors website in browser
            Desktop.getDesktop().browse( new URL( Calculator.WEBSITE ).toURI() );
        } catch ( MalformedURLException ex ) {
            this.buttonWebsite.setEnabled( false );
            Error error = new Error( "Malformed URL detected.", this );
        } catch ( URISyntaxException ex ) {
            this.buttonWebsite.setEnabled( false );
            Error error = new Error( "URI Syntax was incorrect.", this );
        } catch ( IOException ex ) {
            this.buttonWebsite.setEnabled( false );
            Error error = new Error( "IOException while opening page.", this );
        }
    }

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void initLabels() {
        this.labelVersion.setText( "Version: " + Calculator.VERSION );
        this.labelAuthor.setText( "Author: " + Calculator.AUTHOR );
    }

    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonWebsite;
    private javax.swing.JLabel labelAuthor;
    private javax.swing.JLabel labelHeader;
    private javax.swing.JLabel labelVersion;
}
