package windows;

import java.awt.Dimension;
import list.AbstractListModel;
import java.util.ArrayDeque;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.ImageIcon;

/**
 * Calculates the give string with JS ScriptEngine
 *
 * @author Ooppa
 */
public class Calculator extends javax.swing.JFrame {

    public static final String AUTHOR = "Ooppa";
    public static final double VERSION = 1.4;
    public static final String WEBSITE = "http://www.ooppa.fi";

    // All blocked characters since we don't want anything extra in our .eval()
    private static final char[] charBlacklist = new char[] { ',', ';', '\\',
        '/', '\'', '=', '&', '@', '!', '<', '>', '[', ']', '{', '}', '?', ':' };

    private final ScriptEngineManager scriptEngineManager;
    private final ScriptEngine scriptEngine;
    private final ArrayDeque<String> history;

    public Calculator() {
        this.history = new ArrayDeque<>();
        this.scriptEngineManager = new ScriptEngineManager();
        this.scriptEngine = scriptEngineManager.getEngineByName( "JavaScript" );

        this.initComponents();
        this.setIconImage( new ImageIcon( "icons/iconCalculator.png" ).getImage() );
        this.setLocationRelativeTo( null ); // In the middle of the screen
        this.setVisible( true );
    }

    private void initComponents() {
        inputField = new javax.swing.JTextField();
        calculateButton = new javax.swing.JButton();
        answerLabel = new javax.swing.JLabel();
        millisecondsLabel = new javax.swing.JLabel();
        copyResultButton = new javax.swing.JButton();
        mainMenuBar = new javax.swing.JMenuBar();
        menuSectionFile = new javax.swing.JMenu();
        menuItemAbout = new javax.swing.JMenuItem();
        menuItemExit = new javax.swing.JMenuItem();
        menuSectionEdit = new javax.swing.JMenu();
        menuItemAlwaysOnTop = new javax.swing.JCheckBoxMenuItem();
        menuItemShowTime = new javax.swing.JCheckBoxMenuItem();
        menuSectionHistory = new javax.swing.JMenu();
        menuItemPrevious = new javax.swing.JMenuItem();
        menuItemHistoryList = new javax.swing.JMenuItem();
        menuItemLockHistory = new javax.swing.JCheckBoxMenuItem();
        menuItemClear = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("String Calculator");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(780, 170));
        setName("Window");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        inputField.setFont(new java.awt.Font("Tahoma", 0, 18));
        inputField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputFieldKeyPressed(evt);
            }
        });

        calculateButton.setFont(new java.awt.Font("Tahoma", 0, 14));
        calculateButton.setText("Calculate");
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateButtonActionPerformed(evt);
            }
        });

        answerLabel.setFont(new java.awt.Font("Tahoma", 0, 18));
        answerLabel.setText("=");

        millisecondsLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
        millisecondsLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        millisecondsLabel.setText("~ms");

        copyResultButton.setFont(new java.awt.Font("Tahoma", 0, 5));
        copyResultButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrow-up.png")));
        copyResultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyResultButtonActionPerformed(evt);
            }
        });

        mainMenuBar.setFont(new java.awt.Font("Segoe UI", 0, 16));

        menuSectionFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconFile.png")));
        menuSectionFile.setText("File");
        menuSectionFile.setFont(new java.awt.Font("Segoe UI", 0, 18));

        menuItemAbout.setFont(new java.awt.Font("Segoe UI", 0, 18));
        menuItemAbout.setText("About");
        menuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAboutActionPerformed(evt);
            }
        });
        menuSectionFile.add(menuItemAbout);

        menuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        menuItemExit.setFont(new java.awt.Font("Segoe UI", 0, 18));
        menuItemExit.setText("Exit");
        menuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExitActionPerformed(evt);
            }
        });
        menuSectionFile.add(menuItemExit);

        mainMenuBar.add(menuSectionFile);

        menuSectionEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconEdit.png")));
        menuSectionEdit.setText("Edit");
        menuSectionEdit.setFocusable(false);
        menuSectionEdit.setFont(new java.awt.Font("Segoe UI", 0, 18));

        menuItemAlwaysOnTop.setFont(new java.awt.Font("Segoe UI", 0, 18));
        menuItemAlwaysOnTop.setText("Always on top");
        menuItemAlwaysOnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAlwaysOnTopActionPerformed(evt);
            }
        });
        menuSectionEdit.add(menuItemAlwaysOnTop);

        menuItemShowTime.setFont(new java.awt.Font("Segoe UI", 0, 18));
        menuItemShowTime.setSelected(true);
        menuItemShowTime.setText("Show time");
        menuItemShowTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemShowTimeActionPerformed(evt);
            }
        });
        menuSectionEdit.add(menuItemShowTime);

        mainMenuBar.add(menuSectionEdit);

        menuSectionHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconHistory.png")));
        menuSectionHistory.setText("History");
        menuSectionHistory.setFont(new java.awt.Font("Segoe UI", 0, 18));

        menuItemPrevious.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        menuItemPrevious.setFont(new java.awt.Font("Segoe UI", 0, 18));
        menuItemPrevious.setText("Previous");
        menuItemPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemPreviousActionPerformed(evt);
            }
        });
        menuSectionHistory.add(menuItemPrevious);

        menuItemHistoryList.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        menuItemHistoryList.setFont(new java.awt.Font("Segoe UI", 0, 18));
        menuItemHistoryList.setText("History list");
        menuItemHistoryList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemHistoryListActionPerformed(evt);
            }
        });
        menuSectionHistory.add(menuItemHistoryList);

        menuItemLockHistory.setFont(new java.awt.Font("Segoe UI", 0, 18));
        menuItemLockHistory.setText("Lock history");
        menuItemLockHistory.setToolTipText("Locks history so no more items are added to it or can be removed from it");
        menuItemLockHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemLockHistoryActionPerformed(evt);
            }
        });
        menuSectionHistory.add(menuItemLockHistory);

        menuItemClear.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menuItemClear.setFont(new java.awt.Font("Segoe UI", 0, 18));
        menuItemClear.setText("Clear history");
        menuItemClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemClearActionPerformed(evt);
            }
        });
        menuSectionHistory.add(menuItemClear);

        mainMenuBar.add(menuSectionHistory);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(inputField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(answerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(millisecondsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(copyResultButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calculateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(millisecondsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(copyResultButton, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(calculateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(answerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        pack();
    }

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.calculate();

        // Push to history if field is not empty and history is not locked
        if ( !this.inputField.getText().isEmpty() && !this.menuItemLockHistory.isSelected() ) {
            this.history.push( this.inputField.getText() );
        }
    }

    private void inputFieldKeyPressed(java.awt.event.KeyEvent evt) {
        // Button ENTER pressed, performs the same as Calculate button
        if ( evt.getExtendedKeyCode() == 10 ) {
            calculateButtonActionPerformed( null );
        }
    }

    private void menuItemClearActionPerformed(java.awt.event.ActionEvent evt) {
        this.inputField.setText( "" );
        this.history.clear();
    }

    private void menuItemExitActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose(); // Ends the program
    }

    private void menuItemShowTimeActionPerformed(java.awt.event.ActionEvent evt) {
        // Hides or shows the time it took to perform the calculation
        if ( this.menuItemShowTime.isSelected() ) {
            this.millisecondsLabel.setVisible( true );
        } else {
            this.millisecondsLabel.setVisible( false );
        }
    }

    private void menuItemAlwaysOnTopActionPerformed(java.awt.event.ActionEvent evt) {
        if ( this.menuItemAlwaysOnTop.isSelected() ) {
            this.setAlwaysOnTop( true );
        } else {
            this.setAlwaysOnTop( false );
        }

    }

    private void menuItemPreviousActionPerformed(java.awt.event.ActionEvent evt) {
        this.historyPrevious();
        this.calculate();
    }

    private void menuItemLockHistoryActionPerformed(java.awt.event.ActionEvent evt) {
        // Locks history so no more items are added to it or can be removed from it
        if ( this.menuItemLockHistory.isSelected() ) {
            this.menuSectionHistory.setIcon( new javax.swing.ImageIcon(
                    getClass().getResource( "/icons/iconHistoryLocked.png" )
            ) );
        } else {
            this.menuSectionHistory.setIcon( new javax.swing.ImageIcon(
                    getClass().getResource( "/icons/iconHistory.png" )
            ) );
        }

    }

    private void menuItemHistoryListActionPerformed(java.awt.event.ActionEvent evt) {
        // Displays history as a list
        String[] array = new String[this.history.size()];
        ArrayDeque<String> copyOfHistory = this.history.clone();
        int index = 0;

        while ( !copyOfHistory.isEmpty() ) {
            array[index] = copyOfHistory.pop();
            index++;
        }

        if ( array.length == 0 ) {
            // Opens error message location-relative to the this window
            Error error = new Error( "History was empty.", this );
        } else {
            AbstractListModel listModel = new AbstractListModel( array );
            History historyList = new History( this, listModel );
        }
    }

    private void menuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {
        // Opens the about page relative to this window
        About about = new About( this );
    }

    private void formComponentResized(java.awt.event.ComponentEvent evt) {
        int answerFontSize = getSmallerIntFromDimension( answerLabel.getSize() );
        int msFontSize = getSmallerIntFromDimension( millisecondsLabel.getSize() );

        answerLabel.setFont( new java.awt.Font( "Tahoma", 0, Math.min( 50, msFontSize / 2 ) ) );
        millisecondsLabel.setFont( new java.awt.Font( "Tahoma", 0, Math.min( 30, msFontSize / 2 ) ) );
    }

    private void copyResultButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Copy results from answer to the input-field
        String result = answerLabel.getText();
        if ( !result.equals( "= ERROR" ) && !result.equals( "= null" ) ) {
            inputField.setText( result.substring( 2, result.length() ) );
        }
    }

    private void calculate() {
        // Calculates the string and measures how long it took in milliseconds
        long calculationTimeInMs = System.currentTimeMillis();
        String calculation = inputField.getText();

        // Parse all blacklisted characters out.
        for ( char replaceable : charBlacklist ) {
            // \u0000 is a null char literal
            calculation = calculation.replace( replaceable, '\u0000' );
        }

        try {
            answerLabel.setText( "= " + scriptEngine.eval( calculation ) );
        } catch ( ScriptException ex ) {
            answerLabel.setText( "= ERROR" );
        }

        millisecondsLabel.setText( ( System.currentTimeMillis() - calculationTimeInMs ) + "ms" );
    }

    public void calculateStringAndSetText( String selected ) {
        // Sets the parameter to field and calculates it
        // Used by history to set the text
        this.inputField.setText( selected );
        this.calculate();
    }

    private void historyPrevious() {
        // Pops the last item from history
        if ( !this.history.isEmpty() && !this.menuItemLockHistory.isSelected() ) {
            this.inputField.setText( this.history.pop() );
        }
    }

    private int getSmallerIntFromDimension( Dimension dimension ) {
        return Math.min( dimension.height, dimension.width );
    }

    public static void main( String args[] ) {
        java.awt.EventQueue.invokeLater( () -> {
            new Calculator();
        } );
    }

    private javax.swing.JLabel answerLabel;
    private javax.swing.JButton calculateButton;
    private javax.swing.JButton copyResultButton;
    private javax.swing.JTextField inputField;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenuItem menuItemAbout;
    private javax.swing.JCheckBoxMenuItem menuItemAlwaysOnTop;
    private javax.swing.JMenuItem menuItemClear;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JMenuItem menuItemHistoryList;
    private javax.swing.JCheckBoxMenuItem menuItemLockHistory;
    private javax.swing.JMenuItem menuItemPrevious;
    private javax.swing.JCheckBoxMenuItem menuItemShowTime;
    private javax.swing.JMenu menuSectionEdit;
    private javax.swing.JMenu menuSectionFile;
    private javax.swing.JMenu menuSectionHistory;
    private javax.swing.JLabel millisecondsLabel;
}
