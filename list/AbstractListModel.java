package list;

public class AbstractListModel extends javax.swing.AbstractListModel{
    
    private String[] strings;

    public AbstractListModel( String[] strings ) {
        this.strings = strings;
    }

    public String[] getStrings() {
        return strings;
    }

    @Override
    public int getSize() {
        return this.strings.length;
    }

    @Override
    public Object getElementAt( int index ) {
        return strings[index];
    }
    
}
