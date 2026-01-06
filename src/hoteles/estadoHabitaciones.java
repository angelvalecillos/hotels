package hoteles;

import classes.ManejadorSql;
import classes.tablas;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.prompt.PromptSupport;
/**
 *
 * @author angelito
 */
public class estadoHabitaciones extends javax.swing.JFrame {

    /**
     * Creates new form estadoHabitaciones
     */
    ManejadorSql sql =new ManejadorSql(null, null, null, null, null, null, null, null, null);
    tablas tabla = new tablas();
    int[] valores;
    
    public estadoHabitaciones() {
        initComponents();
        PromptSupport.setPrompt("solo se debe usar una opcion sucio o bloqueado y debe existir un motivo espesifico para la opsion", obs);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, obs);
        cargar();
        mostrarbd(null, null);
    }
    
    public void cargar() {
    tabla.delrow(lista);
    int x = 0;
    
    // CORREGIDO: Usar nuevo método SqlQuery
    List<Map<String, Object>> resultados = sql.SqlQuery("SELECT numero from habitacion order by numero");
    
    // CORREGIDO: Procesar resultados con for-each
    for (Map<String, Object> fila : resultados) {
        tabla.addrow(lista);
        lista.setValueAt(false, x, 0);
        
        // CORREGIDO: Obtener número de forma segura
        Object numeroObj = fila.get("numero");
        String numero = (numeroObj != null) ? numeroObj.toString().trim() : "";
        lista.setValueAt(numero, x, 1);
        
        x++;
    }
}    
    
    public void limpiar()
    {
        int x = lista.getRowCount();
        for(int i=0;i<x;i++)
        {
            lista.setValueAt(false, i, 0);
            lista.setValueAt(false, i, 2);
            lista.setValueAt(false, i, 3);
            lista.setValueAt("", i, 4);
            lista.setValueAt("", i, 5);
            lista.setValueAt("", i, 6);
        }
        if(dateinproses.getDate()!=null)
        {
            Date date = dateinproses.getDate();
            String fecha= date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
            mostrarbd(null, fecha);
        }
        else
            mostrarbd(null, null);
    }
    
    private void multiSelect()    {
        //modificar con la primera columna
        int x = lista.getRowCount();
        int y = 0;
        for(int i=0;i<x;i++)
            if((boolean)lista.getValueAt(i, 0))
            {
                if(blo.isSelected())
                lista.setValueAt(true, i, 2);
            else
                lista.setValueAt(false, i, 2);
            if(suc.isSelected())
                lista.setValueAt(true, i, 3);
            else
                lista.setValueAt(false, i, 3);
            lista.setValueAt(in.getDate(), i, 4);
            lista.setValueAt(out.getDate(), i, 5);
            lista.setValueAt(obs.getText(), i, 6);
            }
        checkList();
        
    }
    
    private void checkList()    {
        int r = lista.getRowCount();
        for(int i = 0;i < r;i++)
            if(blo.isSelected())
                suc.setSelected(false);
    }
    
    private int[] modificados()    {
        int rowCount = lista.getRowCount();
        int count=0;
        for(int i=0;i<rowCount;i++)
        {
            if((lista.getValueAt(i, 2)!=null)&&(lista.getValueAt(i, 2)!=null)&&(((boolean)lista.getValueAt(i, 2))||((boolean)lista.getValueAt(i, 3))))
                count++;
        }
        int[] aux=new int[count];
        int w=0;
        if(count>0)
        for(int i=0;i<rowCount;i++)
        {
            if((lista.getValueAt(i, 2)!=null)&&(lista.getValueAt(i, 3)!=null)&&(((boolean)lista.getValueAt(i, 2))||((boolean)lista.getValueAt(i, 3)))) {
                aux[w]=i;
                w++;
            }
        }
        return aux;
    }
    
    private void guardar() {
    //valores anteriores
    int x = valores.length;
    if (x > 0) {
        for (int i = 0; i < x; i++) {
            String num = "";
            
            // CORREGIDO: Primera consulta - buscar número de habitación
            List<Map<String, Object>> resultados = sql.SqlQuery("SELECT habitacion.numero " +
                "FROM public.huesped, public.habitacion " +
                "WHERE habitacion.id = huesped.hab and " +
                "huesped.idguest ='" + valores[i] + "'");
            
            // CORREGIDO: Procesar resultados
            if (!resultados.isEmpty()) {
                Object numeroObj = resultados.get(0).get("numero");
                if (numeroObj != null) {
                    num = numeroObj.toString().trim();
                }
            }
            
            int r = lista.getRowCount();
            for (int j = 0; j < r; j++) {
                String dato = lista.getValueAt(j, 1).toString();
                if (dato.equals(num)) {
                    String con = "";
                    if ((boolean) lista.getValueAt(j, 2)) {
                        con = "1";
                    } else if ((boolean) lista.getValueAt(j, 3)) {
                        con = "4";
                    } else {
                        con = "0";
                    }
                    
                    // CORREGIDO: Usar equals para comparar strings
                    if (!con.equals("0")) {
                        sql.Update("huesped",
                                "fecha_in='" + lista.getValueAt(j, 4) + "', "
                                + "fecha_out='" + lista.getValueAt(j, 5) + "', "
                                + "observacion='" + lista.getValueAt(j, 6) + "', "
                                + "condicion='" + con + "' ",
                                "huesped.idguest ='" + valores[i] + "'");
                    } else {
                        sql.Delete("huesped", "huesped.idguest ='" + valores[i] + "'"); // CORREGIDO: espacio extra eliminado
                    }
                    
                    lista.setValueAt(false, j, 2);
                    lista.setValueAt(false, j, 3);
                    lista.setValueAt("", j, 4);
                    lista.setValueAt("", j, 5);
                    lista.setValueAt("", j, 6);
                }
            }
        }
    }
    
    int[] mod = modificados();
    x = mod.length;
    for (int i = 0; i < x; i++) {
        String num = lista.getValueAt(mod[i], 1).toString();
        String con = "";
        if ((boolean) lista.getValueAt(mod[i], 2)) {
            con = "1";
        } else if ((boolean) lista.getValueAt(mod[i], 3)) {
            con = "4";
        }
        
        // CORREGIDO: Solo insertar si hay una condición válida
        if (!con.isEmpty()) {
            sql.Insert("huesped",
                    "fecha_in, "
                    + "fecha_out, "
                    + "hab, "
                    + "observacion, "
                    + "condicion",
                    "'" + lista.getValueAt(mod[i], 4) + "', "
                    + "'" + lista.getValueAt(mod[i], 5) + "', "
                    + "(select id from habitacion where numero = '" + num + "'), "
                    + "'" + lista.getValueAt(mod[i], 6) + "', "
                    + "'" + con + "'");
        }
    }
    
    if (dateinproses.getDate() != null) {
        Date date = dateinproses.getDate();
        String fecha = date.getDate() + "-" + (date.getMonth() + 1) + "-" + (date.getYear() + 1900);
        mostrarbd(null, fecha);
    } else {
        mostrarbd(null, null);
    }
    
    JOptionPane.showConfirmDialog(null,
            "datos almacenados satisfactorio", "Hotel Mirasol", // CORREGIDO: typo "satisfactorario" → "satisfactorio"
            JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
}
    
    public void mostrarbd(String con, String fecha) {
    // Limpiar la tabla
    for (int i = 0; i < lista.getRowCount(); i++) {
        lista.setValueAt(false, i, 2);
        lista.setValueAt(false, i, 3);
        lista.setValueAt("", i, 4);
        lista.setValueAt("", i, 5);
        lista.setValueAt("", i, 6);
    }
    
    // Construir condición WHERE
    if (con != null) {
        if ("b".equals(con)) {
            con = "condicion = '1'";
        } else {
            con = "condicion = '4'";
        }
    } else {
        con = "(condicion = '1' OR condicion = '4')";
    }
    
    // Construir fecha
    if (fecha == null) {
        fecha = "now()";
    } else {
        fecha = "'" + fecha + "'";
    }
    
    // Obtener conteo
    int SelectCount = sql.SelectCount("huesped, habitacion", 
        "habitacion.id = huesped.hab " +
        "AND " + con + " " +
        "AND huesped.fecha_in <= " + fecha + " " +
        "AND huesped.fecha_out >= " + fecha);
    
    valores = new int[SelectCount];
    
    if (SelectCount > 0) {
        // CORREGIDO: Usar nuevo método SqlQuery
        String query = "SELECT " +
            "habitacion.numero, " +
            "huesped.fecha_in, " +
            "huesped.fecha_out, " +
            "huesped.condicion, " +
            "huesped.observacion, " +
            "huesped.idguest " +
            "FROM " +
            "public.huesped, " +
            "public.habitacion " +
            "WHERE " +
            "habitacion.id = huesped.hab " +
            "AND " + con + " " +
            "AND huesped.fecha_in <= " + fecha + " " +
            "AND huesped.fecha_out >= " + fecha;
        
        List<Map<String, Object>> resultados = sql.SqlQuery(query);
        int y = 0;
        
        // CORREGIDO: Procesar resultados con for-each
        for (Map<String, Object> fila : resultados) {
            int rowCount = lista.getRowCount();
            
            for (int i = 0; i < rowCount; i++) {
                String valor = lista.getValueAt(i, 1).toString();
                
                // CORREGIDO: Obtener datos de forma segura
                Object numeroObj = fila.get("numero");
                String dato = (numeroObj != null) ? numeroObj.toString().trim() : "";
                
                if (valor.equals(dato)) {
                    // CORREGIDO: Obtener idguest de forma segura
                    Object idguestObj = fila.get("idguest");
                    if (idguestObj != null) {
                        valores[y] = ((Number) idguestObj).intValue();
                    }
                    
                    // CORREGIDO: Obtener condición de forma segura
                    Object condicionObj = fila.get("condicion");
                    if (condicionObj != null) {
                        int x = Integer.parseInt(condicionObj.toString());
                        if (x == 1) {
                            lista.setValueAt(true, i, 2);
                            lista.setValueAt(false, i, 3);
                        } else {
                            lista.setValueAt(true, i, 3);
                            lista.setValueAt(false, i, 2);
                        }
                    }
                    
                    // CORREGIDO: Obtener fechas y observación de forma segura
                    Object fechaInObj = fila.get("fecha_in");
                    Object fechaOutObj = fila.get("fecha_out");
                    Object observacionObj = fila.get("observacion");
                    
                    if (fechaInObj != null) {
                        lista.setValueAt(fechaInObj, i, 4);
                    }
                    if (fechaOutObj != null) {
                        lista.setValueAt(fechaOutObj, i, 5);
                    }
                    if (observacionObj != null) {
                        lista.setValueAt(observacionObj.toString(), i, 6);
                    }
                    
                    break; // CORREGIDO: usar break en lugar de i=rowCount
                }
            }
            y++;
            
            // CORREGIDO: Seguridad para evitar desbordamiento
            if (y >= valores.length) {
                break;
            }
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        lista = new javax.swing.JTable();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        suc = new javax.swing.JCheckBox();
        blo = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        obs = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        in = new org.jdesktop.swingx.JXDatePicker();
        out = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        panelImage3 = new org.edisoncor.gui.panel.PanelImage();
        jLabel3 = new javax.swing.JLabel();
        dateinproses = new org.jdesktop.swingx.JXDatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mirasol-hotel-boutique.jpg"))); // NOI18N

        lista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "HAB", "Bloquear", "Sucio", "inicio", "fin", "observacion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista.setColumnSelectionAllowed(true);
        lista.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(lista);
        lista.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (lista.getColumnModel().getColumnCount() > 0) {
            lista.getColumnModel().getColumn(0).setResizable(false);
            lista.getColumnModel().getColumn(0).setPreferredWidth(10);
            lista.getColumnModel().getColumn(1).setResizable(false);
            lista.getColumnModel().getColumn(1).setPreferredWidth(80);
            lista.getColumnModel().getColumn(2).setResizable(false);
            lista.getColumnModel().getColumn(2).setPreferredWidth(80);
            lista.getColumnModel().getColumn(3).setResizable(false);
            lista.getColumnModel().getColumn(3).setPreferredWidth(80);
            lista.getColumnModel().getColumn(4).setResizable(false);
            lista.getColumnModel().getColumn(4).setPreferredWidth(80);
            lista.getColumnModel().getColumn(5).setResizable(false);
            lista.getColumnModel().getColumn(5).setPreferredWidth(80);
            lista.getColumnModel().getColumn(6).setResizable(false);
            lista.getColumnModel().getColumn(6).setPreferredWidth(411);
        }

        panelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/blanco.png"))); // NOI18N

        suc.setText("Sucio");

        blo.setText("Bloquear");

        obs.setColumns(20);
        obs.setLineWrap(true);
        obs.setRows(5);
        jScrollPane2.setViewportView(obs);

        jButton1.setText("APLICAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("observacion");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("HASTA");

        jButton2.setText("GUARDAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImage2Layout = new javax.swing.GroupLayout(panelImage2);
        panelImage2.setLayout(panelImage2Layout);
        panelImage2Layout.setHorizontalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage2Layout.createSequentialGroup()
                        .addComponent(blo)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addGroup(panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelImage2Layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelImage2Layout.createSequentialGroup()
                            .addGroup(panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(suc)
                                .addComponent(in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage2Layout.setVerticalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(blo)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(panelImage2Layout.createSequentialGroup()
                        .addGroup(panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelImage2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(out, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(suc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/blanco.png"))); // NOI18N

        jLabel3.setText("FECHA:");

        dateinproses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateinprosesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImage3Layout = new javax.swing.GroupLayout(panelImage3);
        panelImage3.setLayout(panelImage3Layout);
        panelImage3Layout.setHorizontalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateinproses, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImage3Layout.setVerticalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dateinproses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 297, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        multiSelect();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        guardar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void dateinprosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateinprosesActionPerformed
        Date date = dateinproses.getDate();
        String fecha= date.getDate()+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
        mostrarbd(null, fecha);
    }//GEN-LAST:event_dateinprosesActionPerformed

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
            java.util.logging.Logger.getLogger(estadoHabitaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(estadoHabitaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(estadoHabitaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(estadoHabitaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new estadoHabitaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox blo;
    private org.jdesktop.swingx.JXDatePicker dateinproses;
    private org.jdesktop.swingx.JXDatePicker in;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable lista;
    private javax.swing.JTextArea obs;
    private org.jdesktop.swingx.JXDatePicker out;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    private org.edisoncor.gui.panel.PanelImage panelImage3;
    private javax.swing.JCheckBox suc;
    // End of variables declaration//GEN-END:variables
}
