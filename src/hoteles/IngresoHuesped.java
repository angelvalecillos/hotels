package hoteles;

import classes.ManejadorSql;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.Vector;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jdesktop.swingx.prompt.PromptSupport;

public class IngresoHuesped extends javax.swing.JFrame {
    ManejadorSql sql = new ManejadorSql(null, null, null, null, null, null, null, null, null);
    panel1 pan1 = new panel1();
    panel2 pan2 = new panel2();
    panel3 pan3 = new panel3();
    panel4 pan4 = new panel4();
    String huesped,hab,idguesped; 
    int dia,mes,ano;
    
    public IngresoHuesped() {
        initComponents();
        PromptSupport.setPrompt("Numero de identificacion", cedula);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, cedula);
        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon.jpg"));
        ulin.setText("");
        ulout.setText("");
        this.setIconImage(icon.getImage());
        
        //centrar imagen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        this.setLocation(x, y);
        //fin de centrar
        Dimension paissize = pais.getSize();
        this.cargaPais();
        pais.setPreferredSize(paissize);
        Dimension selectorsize = selector.getSize();
        selector.addTab("Huesped Principal", pan1);
        selector.addTab("Acompañantes", pan2);
        selector.addTab("Habitacion", pan3);
        selector.addTab("Pagos", pan4);
        selector.setSize(selectorsize);
        eliminar.setVisible(false);
        this.setResizable(false);
        obsant.getSize();
        checkout.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    
    
    
    private void initComponents() {

        fondo1 = new org.edisoncor.gui.panel.PanelImage();
        fondo2 = new org.edisoncor.gui.panel.PanelImage();
        txtpasaporte = new javax.swing.JLabel();
        txtnacionalidad = new javax.swing.JLabel();
        pais = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtUltimaVisita = new javax.swing.JLabel();
        ulin = new javax.swing.JLabel();
        ulout = new javax.swing.JLabel();
        txtObservacion = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        obsant = new javax.swing.JTextPane();
        txtCalidad = new javax.swing.JLabel();
        calidad = new javax.swing.JProgressBar();
        selector = new javax.swing.JTabbedPane();
        txtnumero = new javax.swing.JLabel();
        numfac = new javax.swing.JLabel();
        txtCantVisita = new javax.swing.JLabel();
        numvis = new javax.swing.JLabel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        checkin = new javax.swing.JButton();
        checkout = new javax.swing.JButton();
        darcalidad = new javax.swing.JComboBox();
        editar = new javax.swing.JButton();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        agregarcomp = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        panelImage3 = new org.edisoncor.gui.panel.PanelImage();
        R = new javax.swing.JRadioButton();
        C = new javax.swing.JRadioButton();
        cedula = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        fondo1.setBackground(new java.awt.Color(255, 255, 255));
        fondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/blanco.png"))); // NOI18N

        fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/hotel-mirasol-suites.jpg"))); // NOI18N

        txtpasaporte.setText("Cedula/Pasaporte");

        txtnacionalidad.setText("Nacionalidad");

        javax.swing.GroupLayout fondo2Layout = new javax.swing.GroupLayout(fondo2);
        fondo2.setLayout(fondo2Layout);
        fondo2Layout.setHorizontalGroup(
            fondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondo2Layout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addGroup(fondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtpasaporte, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtnacionalidad, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        fondo2Layout.setVerticalGroup(
            fondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondo2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(txtnacionalidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtpasaporte)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pais.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "otro" }));
        pais.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paisItemStateChanged(evt);
            }
        });
        pais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paisMouseClicked(evt);
            }
        });
        pais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                paisKeyTyped(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txtUltimaVisita.setText("Ultima Visita:");

        ulin.setText("in: 01/12/2014");

        ulout.setText("out: 01/12/2014");

        txtObservacion.setText("Observacion:");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jScrollPane1.setViewportView(obsant);

        txtCalidad.setText("Calidad:");

        calidad.setMaximum(5);

        selector.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                selectorStateChanged(evt);
            }
        });

        txtnumero.setText("Numero:");

        numfac.setText("000000");

        txtCantVisita.setText("cant de visitas");

        numvis.setText("0");

        checkin.setText("Check In");
        checkin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkinActionPerformed(evt);
            }
        });

        checkout.setText("Check Out");
        checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutActionPerformed(evt);
            }
        });

        darcalidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CALIDAD", "Pesimo", "Malo", "Regular", "Bueno", "Excelente" }));
        darcalidad.setToolTipText("");
        darcalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darcalidadActionPerformed(evt);
            }
        });

        editar.setText("editar");

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addComponent(checkin)
                        .addGap(18, 18, 18)
                        .addComponent(darcalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addComponent(checkout)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editar)))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkin)
                    .addComponent(darcalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkout)
                    .addComponent(editar)))
        );

        agregarcomp.setText("agregar acompañante");
        agregarcomp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarcompActionPerformed(evt);
            }
        });

        eliminar.setText("eliminar acompañante");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImage2Layout = new javax.swing.GroupLayout(panelImage2);
        panelImage2.setLayout(panelImage2Layout);
        panelImage2Layout.setHorizontalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eliminar)
                    .addComponent(agregarcomp)))
        );
        panelImage2Layout.setVerticalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage2Layout.createSequentialGroup()
                .addComponent(agregarcomp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eliminar))
        );

        R.setText("Reservacion");
        R.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                RStateChanged(evt);
            }
        });
        R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RActionPerformed(evt);
            }
        });

        C.setText("Check In");
        C.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                CStateChanged(evt);
            }
        });
        C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImage3Layout = new javax.swing.GroupLayout(panelImage3);
        panelImage3.setLayout(panelImage3Layout);
        panelImage3Layout.setHorizontalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage3Layout.createSequentialGroup()
                .addComponent(R)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(C))
        );
        panelImage3Layout.setVerticalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage3Layout.createSequentialGroup()
                .addGroup(panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(R)
                    .addComponent(C))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cedulaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout fondo1Layout = new javax.swing.GroupLayout(fondo1);
        fondo1.setLayout(fondo1Layout);
        fondo1Layout.setHorizontalGroup(
            fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(fondo1Layout.createSequentialGroup()
                        .addGroup(fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(fondo1Layout.createSequentialGroup()
                                .addComponent(fondo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pais, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cedula)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondo1Layout.createSequentialGroup()
                                .addComponent(txtCantVisita)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numvis)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtnumero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(numfac)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ulin)
                            .addComponent(txtUltimaVisita)
                            .addGroup(fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ulout, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(fondo1Layout.createSequentialGroup()
                                    .addComponent(txtCalidad)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(calidad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fondo1Layout.createSequentialGroup()
                                .addComponent(txtObservacion)
                                .addGap(0, 118, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)))
                    .addComponent(selector)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondo1Layout.createSequentialGroup()
                        .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(panelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        fondo1Layout.setVerticalGroup(
            fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondo1Layout.createSequentialGroup()
                .addGroup(fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(fondo1Layout.createSequentialGroup()
                        .addComponent(txtObservacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                    .addGroup(fondo1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(fondo1Layout.createSequentialGroup()
                                .addComponent(txtUltimaVisita)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ulin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ulout, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addComponent(fondo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(fondo1Layout.createSequentialGroup()
                                .addComponent(pais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtnumero)
                                .addComponent(numfac))
                            .addGroup(fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCantVisita)
                                .addComponent(numvis))
                            .addComponent(txtCalidad)
                            .addComponent(calidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selector, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelImage2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelImage3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    
    public void send(){
        pan3.numero=hab;
        pan3.loadData(dia+"-"+mes+"-"+ano);
    }
    
    private void checkin(){   
        String item = pais.getSelectedItem().toString().trim();
        // Usar la nueva API: sql.SqlQuery devuelve List<Map<String,Object>>
        List<Map<String,Object>> res = sql.SqlQuery("select id from pais where nombre like '%" + item.replace("'", "''") + "%'");
        if (!res.isEmpty()) {
            Map<String,Object> row = res.get(0);
            // obtener primer valor (id)
            if (!row.isEmpty()) {
                Object val = row.values().iterator().next();
                if (val != null) item = val.toString();
            }
        }
        String text = cedula.getText();
        //modificar
        //boolean guardar = pan1.guardar(item.trim()+"-"+text);
        String anexos = pan2.guardar();
        String[] valores = pan1.guardardespues();
        String x=valores[0];
        String y=valores[1];
        String z=valores[2];
        int cal = darcalidad.getSelectedIndex();
        int con=1;
        if(R.isSelected())con=3;
        else if(C.isSelected())con=2;
        pan3.anoSel = ano;
        pan3.mesSel = mes;
        pan3.diaSel = dia;
        idguesped = pan3.guardarhuesped(x, y, z, cal,con);
        item = item.trim()+"-"+cedula.getText();
        pan1.guardar(item);
        huespedAdd(item,"1");
        int l=anexos.split("xxx").length;
        if (l>0)
        {
            String[] anexo = anexos.split("xxx");
            for(int i=0;i<l;i++)
            {
                huespedAdd(anexo[i], "2");
            }
        }
        pan4.guardarCargos(idguesped);//xsw
        this.setVisible(false);
    }
    
    private void huespedAdd(String valor,String con){
        int SelectCount = sql.SelectCount("huesped_add", " huesped = '"+idguesped+"' and persona ='"+valor+"'");
        if(SelectCount==0)
        {
            sql.Insert("huesped_add", "huesped, persona, con", 
                    "'"+idguesped+"',"
                  + " '"+valor+"',"
                  + " '"+con+"'");
        }
    }
    
    public void clear(){
        cedula.setEditable(true);
        cedula.setFocusable(true);
        pais.setEnabled(true);
        cedula.setText("");
        ulin.setText("");
        ulout.setText("");
        calidad.setValue(0);
        numvis.setText("");
        darcalidad.setSelectedIndex(0);
        selector.setSelectedIndex(0);
        pan1.clear();
        pan2.clear();
        pan3.clear();
        pan4.clear();
    }
    
    private void cargaPais(){
        Vector comboBoxItems=new Vector();
        try {
            List<Map<String,Object>> rows = sql.SqlQuery("SELECT nombre FROM pais ORDER BY id");
            for (Map<String,Object> fila : rows) {
                Object nombreObj = fila.get("nombre");
                if (nombreObj != null) {
                    comboBoxItems.add(nombreObj.toString().trim());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(IngresoHuesped.class.getName()).log(Level.SEVERE, null, ex);
        }
        final DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems);
        pais.setModel(model);
        //pais.setSelectedItem("");
    }
    
    private void getlastvisit(){
        obsant.setText("");
        String dato="",p;
        p=pais.getSelectedItem().toString();
        List<Map<String,Object>> res = sql.SqlQuery("select id from pais where nombre like '%" + p.replace("'", "''") + "%'");
        try {
            if(!res.isEmpty()) {
                Map<String,Object> r0 = res.get(0);
                if(!r0.isEmpty()) {
                    Object v = r0.values().iterator().next();
                    if(v!=null) dato = v.toString().trim()+"-"+cedula.getText();
                }
            }
        } catch (Exception e) {
            // ignorar
        }
        int c = sql.SelectCount("huesped_add", "persona = '"+dato+"'");
        
        numvis.setText(c+"");
        String[] hus=new String[c];
        int n=0;
        String hab = null;
        if(c>0){
            List<Map<String,Object>> rows = sql.SqlQuery("SELECT "
                    + "huesped.fecha_in, huesped.fecha_out, huesped.calidad, "
                    + "huesped.observacion, habitacion.numero, huesped.idguest "
                    + "FROM "
                    + "public.huesped_add, public.huesped, public.habitacion "
                    + "WHERE "
                    + "huesped.idguest = huesped_add.huesped "
                    + "AND "
                    + "habitacion.id = huesped.hab "
                    + "AND huesped_add.persona ='"+dato+"' "
                    + "order by huesped.fecha_out");
            try {
                for (Map<String,Object> fila : rows) {
                    Object fecObj = fila.get("fecha_in");
                    if (fecObj != null) {
                        String fec = fecObj.toString();
                        String[] split = fec.split("-");
                        if (split.length >= 3) {
                            ulin.setText("IN: "+split[2]+"/"+split[1]+"/"+split[0]);
                        }
                    }
                    Object fec2Obj = fila.get("fecha_out");
                    if (fec2Obj != null) {
                        String fec2 = fec2Obj.toString();
                        String[] split2 = fec2.split("-");
                        if (split2.length >= 3) {
                            ulout.setText("OUT: "+split2[2]+"/"+split2[1]+"/"+split2[0]);
                        }
                    }
                    Object calidadObj = fila.get("calidad");
                    int aInt = 0;
                    if (calidadObj instanceof Number) aInt = ((Number) calidadObj).intValue();
                    else if (calidadObj != null) {
                        try { aInt = Integer.parseInt(calidadObj.toString()); } catch (Exception ign) {}
                    }
                    calidad.setValue(aInt);
                    if(aInt==1)
                        calidad.setForeground(Color.red);
                    else if(aInt==2)
                        calidad.setForeground(Color.orange);
                    else if(aInt==3)
                        calidad.setForeground(Color.yellow);
                    else if(aInt==4)
                        calidad.setForeground(Color.GREEN);
                    else 
                        calidad.setForeground(Color.blue);
                    Object habObj = fila.get("numero");
                    if (habObj != null) {
                        String split2 = (fila.get("fecha_out") != null) ? fila.get("fecha_out").toString() : "";
                        String[] sp2 = split2.split("-");
                        String outText = "";
                        if (sp2.length >= 3) outText = sp2[2]+"/"+sp2[1]+"/"+sp2[0];
                        obsant.setText(obsant.getText()+"HAB: "+habObj.toString()+(outText));
                    }
                    Object obsObj = fila.get("observacion");
                    if (obsObj != null) {
                        obsant.setText(obsant.getText()+"\n    "+obsObj.toString()+"\n");
                    }
                }
            } catch (Exception e) {
                // ignorar
            }
        }
            
    }
    
    public void guestout(String txtpais,String txtid) {
        int l=pais.getItemCount();
        String p ="";
        List<Map<String,Object>> res = sql.SqlQuery("select nombre from pais where id ='" + txtpais.trim() + "'");
        try {
            if(!res.isEmpty()){
                Map<String,Object> r0 = res.get(0);
                if(!r0.isEmpty()){
                    Object v = r0.values().iterator().next();
                    if (v != null) p = v.toString().trim();
                }
            }
        } catch (Exception e) {
        }
        
        for(int i=0;i<l;i++){
            if(pais.getItemAt(i).toString().contains(p))
            {
                pais.setSelectedIndex(i);
                i=l;
            }
        }
        cedula.setText(txtid.trim());
        cedula.setEditable(false);
        cedula.setFocusable(false);
        pais.setEnabled(false);
        this.test(txtpais.trim(),txtid.trim(),false);
        this.adicioneales();
    }
    
    private void adicioneales() {
        String date;
        date = ano+"-"+mes+"-"+dia;
        pan1.adicionales(huesped);
        pan2.adicionales(hab, date);
        int adicional = pan3.adicional(huesped);
        selectionBox(adicional);
    }
    
    private void d() {
        String code="";
        Object p = pais.getSelectedItem().toString();
        List<Map<String,Object>> res = sql.SqlQuery("select id from pais where nombre like '%" + p.toString().replace("'", "''") + "%'");
        try {
            if(!res.isEmpty()){
                Map<String,Object> r0 = res.get(0);
                if(!r0.isEmpty()) {
                    Object v = r0.values().iterator().next();
                    if (v != null) code = v.toString();
                }
            }
        } catch (Exception e) {
        }
        String text = cedula.getText();
        test(code.trim(),text,true);
    }
    
    private void test(String p,String id,boolean t) {
        int sc = sql.SelectCount("persona", "identificacion = '"+p+"-"+id+"'");
            if(sc>0){
            try {
                List<Map<String,Object>> rows = sql.Select("persona", "*","identificacion = '"+p+"-"+id+"'");
                for (Map<String,Object> rs : rows) {
                        JPanel existe = new JPanel();
                        existe.setLayout(new GridLayout(2,2));
                        String a1,n1;
                        a1 = rs.get("p_apellido") != null ? rs.get("p_apellido").toString().trim() : "";
                        n1 = rs.get("p_nombre") != null ? rs.get("p_nombre").toString().trim() : "";
                        JLabel mess = new JLabel(a1+" "+n1);
                        JLabel mess2 = new JLabel("desea ingresas estos datos?");
                        existe.add(mess);
                        existe.add(mess2);
                        int input;
                        if(t){
                            input= JOptionPane.showConfirmDialog(null, existe, "!!!Cedula existe!!!"
                                ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        }
                        else
                            input=0;
                        if (input==0)//true
                        {
                            String idStr = (rs.get("identificacion") != null) ? rs.get("identificacion").toString().trim() : "";
                            pan1.id = idStr;
                            String in_pnombre="",in_snombre="",in_papellido="",in_sapellido="",in_correo="",in_dir="",in_sex="",in_ecivil="";
                            Object in_fechanac;
                            in_pnombre = rs.get("p_nombre") != null ? rs.get("p_nombre").toString().trim() : "";
                            in_snombre = rs.get("s_nombre") != null ? rs.get("s_nombre").toString().trim() : "";
                            in_papellido = rs.get("p_apellido") != null ? rs.get("p_apellido").toString().trim() : "";
                            in_sapellido = rs.get("s_apellido") != null ? rs.get("s_apellido").toString().trim() : "";
                            in_fechanac = rs.get("fenac");
                            if(rs.get("correo")!=null)
                                in_correo = rs.get("correo").toString().trim();
                            if(rs.get("direccion")!=null)
                                in_dir = rs.get("direccion").toString().trim();
                            if(rs.get("sexo")!=null)
                                in_sex = rs.get("sexo").toString().trim();
                            if(rs.get("ecivil")!=null)
                                in_ecivil = rs.get("ecivil").toString().trim();
                            pan1.insertValor(in_pnombre,in_snombre,in_papellido,in_sapellido,
                                    in_fechanac,in_correo,in_dir,in_sex,in_ecivil);
                        }
                }
            } catch (Exception ex) {
                Logger.getLogger(IngresoHuesped.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }
    
    private void cedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaKeyTyped
        
    }//GEN-LAST:event_cedulaKeyTyped
    
    private void checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkoutActionPerformed
    
    private void cedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulaKeyReleased
        this.d();
        this.getlastvisit();
    }//GEN-LAST:event_cedulaKeyReleased
    
    private void paisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paisKeyTyped
        
    }//GEN-LAST:event_paisKeyTyped
    
    private void paisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paisMouseClicked
  
    }//GEN-LAST:event_paisMouseClicked
    
    private void paisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paisItemStateChanged
        this.d();
        this.getlastvisit();
    }//GEN-LAST:event_paisItemStateChanged
    
    private void checkinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkinActionPerformed
        selectorload();
        this.checkin();
    }//GEN-LAST:event_checkinActionPerformed
    
    private void agregarcompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarcompActionPerformed
        int rows = pan2.newgest();
        selector.setSelectedIndex(1);
        if (rows>0)
            eliminar.setVisible(true);
        else
            eliminar.setVisible(false);
    }//GEN-LAST:event_agregarcompActionPerformed
    
    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        int rows = pan2.delgest();
        if (rows>0)
            eliminar.setVisible(true);
        else
            eliminar.setVisible(false);
    }//GEN-LAST:event_eliminarActionPerformed
    
    private void darcalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darcalidadActionPerformed
        if(darcalidad.getSelectedIndex()==0)
            checkout.setEnabled(false);
        else
            checkout.setEnabled(true);
    }//GEN-LAST:event_darcalidadActionPerformed
    
    private void RStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_RStateChanged
        
    }//GEN-LAST:event_RStateChanged
    
    private void CStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_CStateChanged
        
    }//GEN-LAST:event_CStateChanged
    
    private void RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RActionPerformed
        if(R.isSelected())
            selectionBox(3);
    }//GEN-LAST:event_RActionPerformed
    
    private void CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CActionPerformed
        if(C.isSelected())
            selectionBox(2);
    }//GEN-LAST:event_CActionPerformed
    
    Object[] costo=null;
    
    private void selectorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_selectorStateChanged
        selectorload();
    }//GEN-LAST:event_selectorStateChanged
    
    private void selectorload(){
        
        int rowcount = pan2.getrowcount();
        pan3.pax(rowcount);
        if(selector.getSelectedIndex()==3){
            if(!Arrays.equals(costo, pan3.costo()))
            {
                costo = pan3.costo();
                pan4.cargos(costo);
                if(huesped!=null)
                    pan4.getbd(huesped);
            }
        }
    }
    
    private void selectionBox(int data) {
        boolean ri=false,ci=false;
        if (data == 2)
            ci=true;
        else 
            ri=true;
        R.setSelected(ri);
        C.setSelected(ci);
    }
    
        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IngresoHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresoHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresoHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresoHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngresoHuesped().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton C;
    private javax.swing.JRadioButton R;
    private javax.swing.JButton agregarcomp;
    private javax.swing.JProgressBar calidad;
    private javax.swing.JTextField cedula;
    private javax.swing.JButton checkin;
    private javax.swing.JButton checkout;
    private javax.swing.JComboBox darcalidad;
    private javax.swing.JButton editar;
    private javax.swing.JButton eliminar;
    private org.edisoncor.gui.panel.PanelImage fondo1;
    private org.edisoncor.gui.panel.PanelImage fondo2;
    private javax.swing.JLabel txtpasaporte;//passaporte
    private javax.swing.JLabel txtnumero;//numero
    private javax.swing.JLabel txtnacionalidad;//nacionalidad
    private javax.swing.JLabel txtUltimaVisita;//ultima visita
    private javax.swing.JLabel txtCantVisita;//cant de visitas
    private javax.swing.JLabel txtObservacion;//observacion
    private javax.swing.JLabel txtCalidad;//calidad
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel numfac;//numero de facturas
    private javax.swing.JLabel numvis;//numvis
    private javax.swing.JTextPane obsant;
    private javax.swing.JComboBox pais;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    private org.edisoncor.gui.panel.PanelImage panelImage3;
    private javax.swing.JTabbedPane selector;
    private javax.swing.JLabel ulin;
    private javax.swing.JLabel ulout;
    // End of variables declaration//GEN-END:variables
}