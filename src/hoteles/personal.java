package hoteles;

import classes.tablas;
import classes.ManejadorSql;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JTable;
/**
 *
 * @author angelito
 */
public class personal extends javax.swing.JFrame {
    /**
     * Creates new form personal
     */
    tablas tabla = new tablas();
    ManejadorSql sql = new ManejadorSql(null, null, null, null, null, null, null, null, null);
    ingresoPersonal ingreso = new ingresoPersonal();
    int tableNumber=0;
    String tablename="";
    
    public personal() {
        //confirmar cerrar
    addWindowListener(new WindowAdapter() 
    {
        public void windowClosing(WindowEvent e) 
        {
            dispose();
        }
    }
    );
//confrimar cerrar
        initComponents();
        //centrar imagen
        setLocationRelativeTo(null);
        this.setResizable(false);
        //fin de centrar

        panel.setVisible(false);
        imag();
        panelEmplea2.setVisible(false);
        nuevoingreso.setVisible(false);
        modificarEmpleado.setVisible(false);
        datos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        check1.setSelected(true);
        check2.setSelected(true);
        check3.setSelected(true);
        check4.setSelected(true);
        check5.setSelected(true);
        check6.setSelected(true);
        check7.setSelected(true);
        check8.setSelected(true);
        check9.setSelected(true);
        check10.setSelected(true);
        Dimension siz = new Dimension(600, 360);
        panel.setMaximumSize(siz);
    }
    
    public void imag() {
        Dimension size = this.getSize();
        int w = size.width;
        int l = size.height;
        size.setSize(w, l);
        panelImage1.setPreferredSize(size);
    }
    
    private void cols(int column) {
        int columnCount = datos.getColumnCount();
        if(columnCount<column)
        {
            while(columnCount<column)
            {
                tabla.addcol(datos);
                columnCount = datos.getColumnCount();
            }
            columnCount = datos.getColumnCount();
        }
        if(columnCount>column)
        {
            while(columnCount>column)
            {
                tabla.Removecolum(datos, columnCount-1);
                columnCount = datos.getColumnCount();
            }
        }
    }
    
    private void campos() {
    int h = 0;
    int Row = datos.getSelectedRow();
    String value = datos.getValueAt(Row, 1).toString();
    tabla.delrow(datos);
    cols(3);
    tabla.colname(datos, 0, "num");
    tabla.setWidth(datos, 0, 30);
    tabla.colname(datos, 1, "nombre");
    tabla.setWidth(datos, 1, 100);
    tabla.colname(datos, 2, "tipo");
    tabla.setWidth(datos, 2, 100);
    tablename = value;
    
    // Corregido: usar SqlQuery que devuelve List<Map<String, Object>>
    List<Map<String, Object>> resultados = sql.SqlQuery("SELECT column_name, data_type FROM information_schema.columns WHERE table_name = '" + value + "'");
    
    for (Map<String, Object> fila : resultados) {
        h++;
        tabla.addrow(datos);
        int rowCount = datos.getRowCount();
        datos.setValueAt(rowCount, rowCount - 1, 0);
        
        if (fila.get("column_name") != null) {
            datos.setValueAt(fila.get("column_name").toString(), rowCount - 1, 1);
        }
        
        if (fila.get("data_type") != null) {
            datos.setValueAt(fila.get("data_type").toString(), rowCount - 1, 2);
        }
    }
    
    datos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    int w = 230;
    pansize(w, h);
}
    
    private void data() {
    tableNumber = 0;
    int[] Row = datos.getSelectedRows();
    int rowlenth = Row.length;
    int h = 0;
    String[] value = new String[rowlenth];
    String valor = "";
    
    for (int i = 0; i < rowlenth; i++)
        value[i] = datos.getValueAt(Row[i], 1).toString();
    
    tabla.delrow(datos);
    cols(rowlenth + 1);
    tabla.colname(datos, 0, "num");
    tabla.setWidth(datos, 0, 30);
    
    for (int i = 1; i <= rowlenth; i++) {
        tabla.colname(datos, i, value[i - 1]);
        tabla.setWidth(datos, i, 100);
        valor = valor + ", " + value[i - 1];
    }
    
    valor = valor.substring(1);
    
    // Corregido: usar SqlQuery que devuelve List<Map<String, Object>>
    List<Map<String, Object>> resultados = sql.SqlQuery("select " + valor + " from " + tablename);
    
    for (Map<String, Object> fila : resultados) {
        h++;
        tabla.addrow(datos);
        int rowCount = datos.getRowCount();
        datos.setValueAt(rowCount, rowCount - 1, 0); // Agregar número de fila
        
        for (int i = 1; i <= rowlenth; i++) {
            String campo = value[i - 1];
            if (fila.get(campo) != null) {
                datos.setValueAt(fila.get(campo).toString(), rowCount - 1, i);
            }
        }
    }
    
    datos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    int w = (rowlenth * 100) + 30;
    pansize(w, h);
}
    
    private void check(JCheckBox object, int valor) {
        if(object.isSelected())
        {
            tabla.setWidth(datos, valor, 100);
        }
        else
        {
            tabla.setWidth(datos, valor, 0);
        }
    }
    
    private void verPersonal() {
    int h = 0;
    tabla.delrow(datos);
    cols(11);
    tabla.colname(datos, 0, "num");
    tabla.setWidth(datos, 0, 30);
    String[] x = {"Cedula", "Apellido", "Nombre", "Usuario", "Cargo", "Ingreso", "Egreso", "Condicion", "Salario", "Profesion"};
    for (int i = 1; i < 11; i++) {
        tabla.colname(datos, i, x[i - 1]);
        tabla.setWidth(datos, i, 100);
    }
    
    String valor = "select "
            + "persona.identificacion, " // 1
            + "persona.p_apellido, " // 2
            + "persona.p_nombre, " // 3
            + "usuario.usuario, " // 4
            + "empleado.cargo, " // 5
            + "empleado.f_ingreso, " // 6
            + "empleado.f_egreso, " // 7
            + "empleado.condicion, " // 8
            + "empleado.salario, " // 9
            + "profesion.nombre " // 10
            + "from empleado "
            + "inner join usuario "
            + "on empleado.id = usuario.empleado "
            + "inner join persona "
            + "on empleado.persona = persona.identificacion "
            + "inner join profesion "
            + "on profesion.id = empleado.profesion "
            + "order by persona.identificacion";
    
    // Corregido: usar SqlQuery que devuelve List<Map<String, Object>>
    List<Map<String, Object>> resultados = sql.SqlQuery(valor);
    
    for (Map<String, Object> fila : resultados) {
        h++;
        tabla.addrow(datos);
        int rowCount = datos.getRowCount();
        datos.setValueAt(rowCount, rowCount - 1, 0);
        
        // Mapeo de campos por nombre
        String[] campos = {
            "identificacion", "p_apellido", "p_nombre", "usuario", 
            "cargo", "f_ingreso", "f_egreso", "condicion", "salario", "nombre"
        };
        
        for (int i = 1; i < 11; i++) {
            String campo = campos[i - 1];
            if (fila.get(campo) != null) {
                datos.setValueAt(fila.get(campo).toString().trim(), rowCount - 1, i);
            }
        }
    }
    
    int w = 10 * 100;
    pansize(w, h);
}
   
    private void pansize(int width,int height) {
        height=height*16;
        //
        if(height>300)
            height=300;
        if(width>680)
            width=680;
        Dimension pansizer = new Dimension();
        pansizer.setSize(width+2, height+44);
        panel.setSize(pansizer);
        //
    }
    
    private void checked() {
                int count=0;
        if(check1.isSelected())
            count++;
        if(check2.isSelected())
            count++;
        if(check3.isSelected())
            count++;
        if(check4.isSelected())
            count++;
        if(check5.isSelected())
            count++;
        if(check6.isSelected())
            count++;
        if(check7.isSelected())
            count++;
        if(check8.isSelected())
            count++;
        if(check9.isSelected())
            count++;
        if(check10.isSelected())
            count++;
        int h = datos.getRowCount();
        int w = (count*85)+180;
        pansize(w, h);
    }
    
    private void showComplite() {
        if((datos.getSelectedRowCount()!=0)&&(datos.getSelectedColumnCount()!=0))
        {
            int selectedColumn = datos.getSelectedColumn();
            int selectedRow = datos.getSelectedRow();
            visor.setText(datos.getValueAt(selectedRow, selectedColumn).toString());
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

        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        listaEmpleados = new javax.swing.JButton();
        buscar = new javax.swing.JButton();
        visor = new javax.swing.JTextField();
        panel = new javax.swing.JScrollPane();
        datos = new javax.swing.JTable();
        panelEmpleados = new org.edisoncor.gui.panel.PanelImage();
        panelEmplea2 = new org.edisoncor.gui.panel.PanelImage();
        check1 = new javax.swing.JCheckBox();
        check2 = new javax.swing.JCheckBox();
        check3 = new javax.swing.JCheckBox();
        check4 = new javax.swing.JCheckBox();
        check5 = new javax.swing.JCheckBox();
        check6 = new javax.swing.JCheckBox();
        check7 = new javax.swing.JCheckBox();
        check8 = new javax.swing.JCheckBox();
        check9 = new javax.swing.JCheckBox();
        check10 = new javax.swing.JCheckBox();
        nuevoingreso = new javax.swing.JButton();
        modificarEmpleado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mirasol4.jpg"))); // NOI18N
        panelImage1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            
        });

        listaEmpleados.setText("lista de enpleados");
        listaEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            
        });
        listaEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaEmpleadosActionPerformed(evt);
            }
        });

        buscar.setText("buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImage2Layout = new javax.swing.GroupLayout(panelImage2);
        panelImage2.setLayout(panelImage2Layout);
        panelImage2Layout.setHorizontalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage2Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(listaEmpleados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(visor, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelImage2Layout.setVerticalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listaEmpleados)
                    .addComponent(buscar)
                    .addComponent(visor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.setMinimumSize(new java.awt.Dimension(0, 0));

        datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        datos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            
        });
        datos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datosMouseClicked(evt);
            }
        });
        datos.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                datosCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        datos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                datosKeyReleased(evt);
            }
            
        });
        panel.setViewportView(datos);
        if (datos.getColumnModel().getColumnCount() > 0) {
            datos.getColumnModel().getColumn(0).setResizable(false);
        }

        panelEmplea2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelEmplea2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/blanco.png"))); // NOI18N

        check1.setBackground(new java.awt.Color(255, 255, 255));
        check1.setText("Cedula");
        check1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check1ActionPerformed(evt);
            }
        });

        check2.setBackground(new java.awt.Color(255, 255, 255));
        check2.setText("Apellido");
        check2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check2ActionPerformed(evt);
            }
        });

        check3.setBackground(new java.awt.Color(255, 255, 255));
        check3.setText("Nombre");
        check3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check3ActionPerformed(evt);
            }
        });

        check4.setBackground(new java.awt.Color(255, 255, 255));
        check4.setText("Usuario");
        check4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check4ActionPerformed(evt);
            }
        });

        check5.setBackground(new java.awt.Color(255, 255, 255));
        check5.setText("Cargo");
        check5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check5ActionPerformed(evt);
            }
        });

        check6.setBackground(new java.awt.Color(255, 255, 255));
        check6.setText("Fecha de Ingreso");
        check6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check6ActionPerformed(evt);
            }
        });

        check7.setBackground(new java.awt.Color(255, 255, 255));
        check7.setText("Fesha de Egreso");
        check7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check7ActionPerformed(evt);
            }
        });

        check8.setBackground(new java.awt.Color(255, 255, 255));
        check8.setText("condicion");
        check8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check8ActionPerformed(evt);
            }
        });

        check9.setBackground(new java.awt.Color(255, 255, 255));
        check9.setText("salario");
        check9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check9ActionPerformed(evt);
            }
        });

        check10.setBackground(new java.awt.Color(255, 255, 255));
        check10.setText("profesion");
        check10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEmplea2Layout = new javax.swing.GroupLayout(panelEmplea2);
        panelEmplea2.setLayout(panelEmplea2Layout);
        panelEmplea2Layout.setHorizontalGroup(
            panelEmplea2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmplea2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmplea2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(check6)
                    .addComponent(check1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEmplea2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(check2)
                    .addComponent(check7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEmplea2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(check3)
                    .addComponent(check8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEmplea2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(check9)
                    .addComponent(check4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEmplea2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(check5)
                    .addComponent(check10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEmplea2Layout.setVerticalGroup(
            panelEmplea2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmplea2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmplea2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check1)
                    .addComponent(check2)
                    .addComponent(check3)
                    .addComponent(check4)
                    .addComponent(check5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEmplea2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(check9)
                    .addComponent(check10)
                    .addComponent(check7)
                    .addComponent(check8)
                    .addComponent(check6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nuevoingreso.setText("NUEVO INGRESO");
        nuevoingreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoingresoActionPerformed(evt);
            }
        });

        modificarEmpleado.setText("MODIFICAR EMPLEADO");
        modificarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarEmpleadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEmpleadosLayout = new javax.swing.GroupLayout(panelEmpleados);
        panelEmpleados.setLayout(panelEmpleadosLayout);
        panelEmpleadosLayout.setHorizontalGroup(
            panelEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpleadosLayout.createSequentialGroup()
                .addComponent(panelEmplea2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(panelEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nuevoingreso, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(modificarEmpleado, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        panelEmpleadosLayout.setVerticalGroup(
            panelEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEmplea2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(panelEmpleadosLayout.createSequentialGroup()
                .addComponent(nuevoingreso)
                .addGap(18, 18, 18)
                .addComponent(modificarEmpleado))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void listaEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaEmpleadosActionPerformed
        tableNumber = 5;
        if(!panel.isVisible())
            panel.setVisible(true);
        panelEmplea2.setVisible(true);
        nuevoingreso.setVisible(true);
        modificarEmpleado.setVisible(true);
        verPersonal();
    }//GEN-LAST:event_listaEmpleadosActionPerformed
    
    private void datosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datosMouseClicked
        if(tableNumber==1)
        {
            campos();
            tableNumber=2;
        }
        showComplite();
    }//GEN-LAST:event_datosMouseClicked
    
    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        if(((datos.isColumnSelected(0)) ||(datos.isColumnSelected(1))||(datos.isColumnSelected(2))) && (tableNumber==2)) 
        {
            data();
            tableNumber=3;
        }
    }//GEN-LAST:event_buscarActionPerformed
    
    private void check1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check1ActionPerformed
        check(check1, 1);
        checked();
    }//GEN-LAST:event_check1ActionPerformed
    
    private void datosCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_datosCaretPositionChanged
        
    }//GEN-LAST:event_datosCaretPositionChanged
    
    private void datosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datosKeyReleased
        int value = evt.getKeyCode();
        if((value>=37)&&(value<=40))
        {
            showComplite();
        }
    }//GEN-LAST:event_datosKeyReleased
    
    private void check2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check2ActionPerformed
        check(check2, 2);
        checked();
    }//GEN-LAST:event_check2ActionPerformed
    
    private void check3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check3ActionPerformed
        check(check3, 3);
        checked();
    }//GEN-LAST:event_check3ActionPerformed
    
    private void check4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check4ActionPerformed
        check(check4, 4);
        checked();
    }//GEN-LAST:event_check4ActionPerformed
    
    private void check5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check5ActionPerformed
        check(check5, 5);
        checked();
    }//GEN-LAST:event_check5ActionPerformed
    
    private void check6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check6ActionPerformed
        check(check6, 6);
        checked();
    }//GEN-LAST:event_check6ActionPerformed
    
    private void check7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check7ActionPerformed
        check(check7, 7);
        checked();
    }//GEN-LAST:event_check7ActionPerformed
    
    private void check8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check8ActionPerformed
        check(check8, 8);
        checked();
    }//GEN-LAST:event_check8ActionPerformed
    
    private void check9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check9ActionPerformed
        check(check9, 9);
        checked();
    }//GEN-LAST:event_check9ActionPerformed
    
    private void check10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check10ActionPerformed
        check(check10, 10);
        checked();
    }//GEN-LAST:event_check10ActionPerformed
    
    private void nuevoingresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoingresoActionPerformed
        ingreso.setVisible(true);
        panel.setVisible(false);
        panelEmplea2.setVisible(false);
        nuevoingreso.setVisible(false);
        modificarEmpleado.setVisible(false);
        ingreso.clear();
        ingreso.in_out(true);
    }//GEN-LAST:event_nuevoingresoActionPerformed
    
    private void modificarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarEmpleadoActionPerformed
        if(datos.getSelectedColumn()>=0&&datos.getSelectedRow()>=0)
        {
            int selectedRow = datos.getSelectedRow();
            String id = datos.getValueAt(selectedRow, WIDTH).toString();
            ingreso.setVisible(true);
            ingreso.verbasedatos(id);
            panel.setVisible(false);
            panelEmplea2.setVisible(false);
            nuevoingreso.setVisible(false);
            modificarEmpleado.setVisible(false);
            ingreso.in_out(false);
        }
        
    }//GEN-LAST:event_modificarEmpleadoActionPerformed
    
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
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(personal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(personal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(personal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(personal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new personal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JCheckBox check1;
    private javax.swing.JCheckBox check10;
    private javax.swing.JCheckBox check2;
    private javax.swing.JCheckBox check3;
    private javax.swing.JCheckBox check4;
    private javax.swing.JCheckBox check5;
    private javax.swing.JCheckBox check6;
    private javax.swing.JCheckBox check7;
    private javax.swing.JCheckBox check8;
    private javax.swing.JCheckBox check9;
    private javax.swing.JTable datos;
    private javax.swing.JButton listaEmpleados;
    private javax.swing.JButton modificarEmpleado;
    private javax.swing.JButton nuevoingreso;
    private javax.swing.JScrollPane panel;
    private org.edisoncor.gui.panel.PanelImage panelEmplea2;
    private org.edisoncor.gui.panel.PanelImage panelEmpleados;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    private javax.swing.JTextField visor;
    // End of variables declaration//GEN-END:variables
}
