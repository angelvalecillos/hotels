package classes;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.List;
import java.util.Map;
import org.jdesktop.swingx.table.DatePickerCellEditor;

public class tablas {
    ManejadorSql sql =new ManejadorSql(null, null, null, null, null, null, null, null, null);
    int meses;
    int anos;
    
    public void addcol(JTable tabla)
    {
        DefaultTableModel modelo= (DefaultTableModel) tabla.getModel();
        int cc = modelo.getColumnCount();
        modelo.addColumn(cc);
        
    }
    public void setcolorheader(JTable tabla, Color color, int column)
    {
        DefaultTableCellRenderer headerRender = new DateCellRenderer();
        headerRender.setBackground(color);
        tabla.getColumnModel().getColumn(column).setHeaderRenderer(headerRender);
    }
    public void colname(JTable tabla, int column, String text)
    {
        JTableHeader th = tabla.getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        TableColumn tc = tcm.getColumn(column);
        tc.setHeaderValue( text );
        th.repaint();
    }
    public void Removecolum(JTable table, int col_index)
    {
        TableColumn tcol = table.getColumnModel().getColumn(col_index);
        table.removeColumn(tcol);
  }
    
    public void removeAllColumns(JTable table) {
    TableColumnModel columnModel = table.getColumnModel();
    
    // Eliminar desde la última columna hasta la primera
    for (int i = columnModel.getColumnCount() - 1; i >= 0; i--) {
        TableColumn column = columnModel.getColumn(i);
        table.removeColumn(column);
    }
}
    
    public void addrow(JTable tabla)
    {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.addRow(new Object[] {});
    }
    public void delrow(JTable tabla)
    {
        int cd;
        cd = tabla.getRowCount();
        if(cd>0)
        {
            do
            {
                cd = tabla.getRowCount();
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.removeRow(0);
            }while(cd!=1);
        }
    }
    public void delrow(JTable tabla,int row)
    {
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.removeRow(row);
    }
    public void dateformat(JTable tabla, int column)
    {
        TableColumnModel m = tabla.getColumnModel();
        m.getColumn(column).setCellRenderer( FormatRenderer.getDateRenderer());
    }
    public void timeformat(JTable tabla, int column)
    {
        TableColumnModel m = tabla.getColumnModel();
        m.getColumn(column).setCellRenderer(FormatRenderer.getTimeRenderer());
    }
    public void percent(JTable tabla, int column)
    {
        TableColumnModel m = tabla.getColumnModel();
        m.getColumn(column).setCellRenderer(NumberRenderer.getPercentRenderer());
    }
    public void money(JTable tabla, int column)
    {
        TableColumnModel m = tabla.getColumnModel();
        m.getColumn(column).setCellRenderer(NumberRenderer.getCurrencyRenderer());
    }
    
    public void config(JTable tabla)
    {
        tabla.getColumnModel().getColumn(0).setWidth(50);
        tabla.getColumnModel().getColumn(0).setResizable(false);
        int x= tabla.getColumnCount();
        for(int y=1;y<x;y++)
        {
            tabla.getColumnModel().getColumn(y).setWidth(28);
            tabla.getColumnModel().getColumn(y).setResizable(false);
        }
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setDefaultRenderer(Color.class,new ColorRenderer(true));
        tabla.setDefaultEditor(Color.class,new ColorEditor());
    }
    public void setWidth(JTable tabla,int column,int size)
    {
        tabla.getColumnModel().getColumn(column).setPreferredWidth(size);
        tabla.getColumnModel().getColumn(column).setResizable(false);
        tabla.getTableHeader().setReorderingAllowed(false);
    }
    public Object[][] nombrespiso(String nom, String tip) {
    int i = 0;
    Object[][] data = new Object[50][2];
    
    // CORREGIDO: Usar el nuevo método que devuelve List<Map>
    List<Map<String, Object>> resultados = sql.Select("habitacion", "numero,(Select nombre from tiphab where id = tipo) as nombre_tipo", "piso = '" + nom + "' order by numero");
    
    // CORREGIDO: Procesar los resultados con for-each
    for (Map<String, Object> fila : resultados) {
        String numero = (String) fila.get("numero");
        String nombreTipo = (String) fila.get("nombre_tipo"); // Usar el alias
        
        // CORREGIDO: Usar equals() en lugar de == para comparar strings
        if (tip.isEmpty()) {                    
            data[i][0] = numero != null ? numero.trim() : "";
            data[i][1] = nombreTipo != null ? nombreTipo.trim() : "";
            i++;
        } else if (nombreTipo != null && nombreTipo.trim().equals(tip.trim())) {
            data[i][0] = numero != null ? numero.trim() : "";
            data[i][1] = nombreTipo != null ? nombreTipo.trim() : "";
            i++;
        }
        
        // Seguridad: evitar desbordamiento del array
        if (i >= 50) break;
    }
    
    return data;
}
    public void combobox(JTable tabla, int col,String[] values)
    {
        TableColumn column = tabla.getColumnModel().getColumn(col);
        JComboBox combo = new JComboBox();
        int le=values.length;
        for(int i=0;i<le;i++)
            combo.addItem(values[i]);
        column.setCellEditor(new DefaultCellEditor(combo));
    }
    public void label(JTable tabla, int col)
    {
        TableColumn column = tabla.getColumnModel().getColumn(col);
        JTextField text = new JTextField();
        column.setCellEditor(new DefaultCellEditor(text));
    }
    public void datepicker(JTable tabla, int col)
    {
        TableColumn Column = tabla.getColumnModel().getColumn(col);
        Column.setCellRenderer(new DateCellRenderer());
        Column.setCellEditor(new DatePickerCellEditor(new SimpleDateFormat("dd-MM-yyyy")));
    }
    public class DateCellRenderer extends DefaultTableCellRenderer
    {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean 
                                isSelected, boolean hasFocus, int row, int column)
        {
            super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
            if ( value instanceof Date )
            {
                String strDate = new SimpleDateFormat("dd/MM/yyyy").format((Date)value);
                this.setText( strDate );
            }

            return this;
        }
    }
}
