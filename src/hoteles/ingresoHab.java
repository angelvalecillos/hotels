package hoteles;

import classes.tablas;
import classes.ManejadorSql;
import classes.validaciones;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;

public class ingresoHab extends javax.swing.JFrame {

    ingresoTipohab t =new ingresoTipohab();
    ManejadorSql sql = new ManejadorSql(null, null, null, null, null, null, null, null, null);
    tablas code = new tablas();
    validaciones val = new validaciones();
    String tip;
    Dimension obsize;
    int campoid=0;
    
    public ingresoHab() {
        //window close
        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                clearall();
                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            }
        }
        );
        //window close
        initComponents();
        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon.jpg"));
        this.setIconImage(icon.getImage());
        
        this.setTitle("ingreso de habitacion");
        carga.setEnabled(false);
        cargat.setEnabled(true);
        
        //centrar imagen
        setLocationRelativeTo(null);
        //fin de centrar
        
        nota.setText("");
        nota.setForeground(Color.red);
        this.tipoloader();
        this.numloader(null,null);
        obsize = observaciones.getSize();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        observaciones = new javax.swing.JTextPane();
        numero = new javax.swing.JTextField();
        piso = new javax.swing.JTextField();
        carga = new javax.swing.JButton();
        cargat = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        nota = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("piso");

        jLabel2.setText("numero");

        jLabel3.setText("tipo");

        jLabel4.setText("observaciones:");

        tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "seleccion" }));
        tipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoItemStateChanged(evt);
            }
        });
        tipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tipoMouseEntered(evt);
            }
        });

        observaciones.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        observaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.N_RESIZE_CURSOR));
        observaciones.setMaximumSize(new java.awt.Dimension(6, 20));
        observaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                observacionesMouseEntered(evt);
            }
        });
        observaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            
            public void keyTyped(java.awt.event.KeyEvent evt) {
                observacionesKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(observaciones);

        numero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                numeroMouseEntered(evt);
            }
        });
        numero.addKeyListener(new java.awt.event.KeyAdapter() {
            
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroKeyTyped(evt);
            }
        });

        piso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pisoMouseEntered(evt);
            }
        });
        piso.addKeyListener(new java.awt.event.KeyAdapter() {
            
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pisoKeyTyped(evt);
            }
        });

        carga.setText("guardar");
        carga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cargaMouseEntered(evt);
            }
        });
        carga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargaActionPerformed(evt);
            }
        });

        cargat.setText("nuevo tipo");
        cargat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cargatMouseEntered(evt);
            }
        });
        cargat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargatActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "numero"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                listaMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(lista);
        if (lista.getColumnModel().getColumnCount() > 0) {
            lista.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel5.setText("NOTA:");

        nota.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tipo, 0, 92, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(numero, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                            .addComponent(piso))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(carga)
                                    .addComponent(cargat))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nota)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cargat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carga))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(piso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nota)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tipoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipoMouseEntered
        int pi,ob,num;
        nota.setText("tipos disponbles");
        String ti;
        pi = piso.getText().length();
        ti = tipo.getSelectedItem().toString();
        ob = observaciones.getText().length();
        num = numero.getText().length();
        if((pi!=0)&&(ob>2)&&(num!=0)&&(ti!="seleccion"))
        {
            carga.setEnabled(true);
        }
        else
        {
            carga.setEnabled(false);
        }
    }//GEN-LAST:event_tipoMouseEntered

    private void cargatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargatActionPerformed
        String text = cargat.getText();
        if("nuevo tipo".equals(text))
        {
            t.setVisible(true);
            t.setResizable(false);
        }
        else
        {
            boolean Delete = sql.Delete("habitacion", "numero ='"+numero.getText()+"'");
            if(Delete)
            {
                nota.setText("habitacion eliminada");
                this.clearall();
                carga.setText("guardar");
                cargat.setText("nuevo tipo");
            }
            else
                nota.setText("intente de nuevo");
        }
        tipoloader();
        this.numloader(null,null);
    }//GEN-LAST:event_cargatActionPerformed

    private void clearall()    {
        String a="";
        nota.setText(a);
        numero.setText(a);
        piso.setText(a);
        observaciones.setText(a);
        tipo.setSelectedItem("seleccion");
    }
    
    private void tipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoItemStateChanged
        int pi,ob,num;
        String ti="seleccion";
        pi = piso.getText().length();
        if(null!=tipo.getSelectedItem())
            ti = tipo.getSelectedItem().toString();
        ob = observaciones.getText().length();
        num = numero.getText().length();
        if(ti!="seleccion")
        {
            tip = this.seltipo(null);
            this.numloader("tipo = '"+tip+"'", "numero");
            
        }
        else
            this.numloader(null, null);
        if((pi!=0)&&(ob>2)&&(num!=0)&&(ti!="seleccion"))
        {
            carga.setEnabled(true);
        }
        else
        {
            carga.setEnabled(false);
        }
        
    }//GEN-LAST:event_tipoItemStateChanged

    private void cargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargaActionPerformed
        int sc = sql.SelectCount("habitacion", "id = '"+campoid+"'");
        if(sc==0)
        {
            String campos = "numero,tipo,piso,observaciones",
                    valor = "'"+numero.getText()+"','"+tip+"','"+piso.getText()+"','"+observaciones.getText()+"'";
            boolean Insert = sql.Insert("habitacion", campos, valor);
            if(Insert)
                nota.setText("habitacion guardada");
            else
                nota.setText("intente de nuevo");
        }
        else
        {
            boolean Update = sql.Update("habitacion", "numero='"+numero.getText()+"', tipo='"+this.seltipo(null)
                    + "', piso='"+piso.getText()
                    + "', observaciones='"+observaciones.getText()
                    + "'", "id='"+ campoid
                            +"'");
            if(Update)
            {
                carga.setText("guardar");
                cargat.setText("nuevo tipo");
                nota.setText("informacion cambiada");
            }
            else
                nota.setText("intente de nuevo");
            
        }
        this.numloader(null,null);
    }//GEN-LAST:event_cargaActionPerformed

    private void listaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMouseClicked
        int sr = lista.getSelectedRow();
        String valor = lista.getValueAt(sr, 0).toString();
        numero.setText(valor);
        this.dataloader(valor);
        carga.setText("editar");
        cargat.setText("eliminar");
    }//GEN-LAST:event_listaMouseClicked

    private void numeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numeroKeyTyped
        int pi,ob,num;
        String ti;
        pi = piso.getText().length();
        ti = tipo.getSelectedItem().toString();
        ob = observaciones.getText().length();
        num = numero.getText().length();
        val.longitud_Field(numero, 10, evt);
        if((pi!=0)&&(ob>2)&&(num!=0)&&(ti!="seleccion"))
        {
            carga.setEnabled(true);
        }
        else
        {
            carga.setEnabled(false);
        }
    }//GEN-LAST:event_numeroKeyTyped

    private void pisoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pisoKeyTyped
        int pi,ob,num;
        String ti;
        pi = piso.getText().length();
        ti = tipo.getSelectedItem().toString();
        ob = observaciones.getText().length();
        num = numero.getText().length();
        val.permitir_espacio(evt);
        val.longitud_Field(piso, 1, evt);
        val.permitir_solo_numero(evt);
        if((pi!=0)&&(ob>2)&&(num!=0)&&(ti!="seleccion"))
        {
            carga.setEnabled(true);
        }
        else
        {
            carga.setEnabled(false);
        }
    }//GEN-LAST:event_pisoKeyTyped

    private void observacionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_observacionesKeyTyped
        int pi,ob,num;
        String ti;
        pi = piso.getText().length();
        ti = tipo.getSelectedItem().toString();
        ob = observaciones.getText().length();
        num = numero.getText().length();
        if((pi!=0)&&(ob>2)&&(num!=0)&&(ti!="seleccion"))
        {
            carga.setEnabled(true);
        }
        else
        {
            carga.setEnabled(false);
        }
    }//GEN-LAST:event_observacionesKeyTyped

    private void numeroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numeroMouseEntered
        nota.setText("acepta letras y numeros");
    }//GEN-LAST:event_numeroMouseEntered

    private void pisoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pisoMouseEntered
        nota.setText("pisos del 1, 2 y 3");
    }//GEN-LAST:event_pisoMouseEntered

    private void observacionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_observacionesMouseEntered
        nota.setText("datos dicionales");
    }//GEN-LAST:event_observacionesMouseEntered

    private void listaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMouseEntered
        nota.setText("habitaciones existentes");
    }//GEN-LAST:event_listaMouseEntered

    private void cargaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargaMouseEntered
        if(!carga.isEnabled())
            nota.setText("todos los campos deben estar llenos");
        else
            nota.setText("");
    }//GEN-LAST:event_cargaMouseEntered

    private void cargatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargatMouseEntered
        nota.setText("");
    }//GEN-LAST:event_cargatMouseEntered

    private void dataloader(String valor) {
    String tipy = null;
    
    // CORREGIDO: Usar nuevo método Select
    List<Map<String, Object>> resultados = sql.Select("habitacion", "*", "numero = '" + valor + "'");
    
    // CORREGIDO: Procesar resultados con for-each
    for (Map<String, Object> fila : resultados) {
        // CORREGIDO: Obtener ID de forma segura
        Object idObj = fila.get("id");
        if (idObj != null) {
            campoid = ((Number) idObj).intValue();
        }
        
        // CORREGIDO: Obtener tipo de forma segura
        Object tipoObj = fila.get("tipo");
        if (tipoObj != null) {
            tipy = tipoObj.toString();
        }
        
        // CORREGIDO: Obtener piso de forma segura
        Object pisoObj = fila.get("piso");
        if (pisoObj != null) {
            piso.setText(pisoObj.toString());
        } else {
            piso.setText("");
        }
        
        // CORREGIDO: Obtener observaciones de forma segura
        Object obsObj = fila.get("observaciones");
        if (obsObj != null) {
            observaciones.setText(obsObj.toString());
            observaciones.setSize(obsize);
        } else {
            observaciones.setText("");
        }
    }
    
    // CORREGIDO: Establecer tipo seleccionado
    tipo.setSelectedItem(this.seltipo(tipy));
}
    
    private void numloader(String where, String order) {        
    // Construir consulta optimizada
    String query = "SELECT numero FROM habitacion";
    if (where != null) query += " WHERE " + where;
    query += " ORDER BY " + (order != null ? order : "numero");
    
    // Limpiar y cargar lista
    code.delrow(lista);
    List<Map<String, Object>> resultados = sql.SqlQuery(query);
    
    for (Map<String, Object> fila : resultados) {
        code.addrow(lista);
        int rc = lista.getRowCount();
        Object numero = fila.get("numero");
        lista.setValueAt(numero != null ? numero : "", rc - 1, 0);
    }
}
    
    private void tipoloader() {
    Object first = tipo.getItemAt(0);
    tipo.removeAllItems();
    tipo.addItem(first);
    
    List<Map<String, Object>> resultados = sql.SqlQuery("SELECT nombre FROM tiphab ORDER BY nombre");
    
    for (Map<String, Object> fila : resultados) {
        Object nombre = fila.get("nombre");
        if (nombre != null) {
            tipo.addItem(nombre);
        }
    }
}
    
    private String seltipo(String tipy) {
    if (tipy == null) {
        // Convertir nombre a ID
        String si = tipo.getSelectedItem().toString();
        List<Map<String, Object>> resultados = sql.Select("tiphab", "id", "nombre = '" + si + "'");
        return resultados.isEmpty() ? si : resultados.get(0).get("id").toString();
    } else {
        // Convertir ID a nombre
        List<Map<String, Object>> resultados = sql.Select("tiphab", "nombre", "id = '" + tipy + "'");
        return resultados.isEmpty() ? tipy : resultados.get(0).get("nombre").toString();
    }
}
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
            java.util.logging.Logger.getLogger(ingresoHab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ingresoHab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ingresoHab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ingresoHab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ingresoHab().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton carga;
    private javax.swing.JButton cargat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable lista;
    private javax.swing.JLabel nota;
    private javax.swing.JTextField numero;
    private javax.swing.JTextPane observaciones;
    private javax.swing.JTextField piso;
    private javax.swing.JComboBox tipo;
    // End of variables declaration//GEN-END:variables
}
