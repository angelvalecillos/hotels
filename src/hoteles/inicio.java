package hoteles;

import classes.ManejadorSql;
import classes.archivos;
import classes.codex;
import classes.tablas;
import classes.xml;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class inicio extends javax.swing.JFrame {
    int w,l;
    ManejadorSql sql= new ManejadorSql(null, null, null, null, null, null, null, null, null);
    tablas tablaconfi= new tablas();
    codex code=new codex();
    ingresoHab h=new ingresoHab();
    IngresoHuesped huesped;
    personal per=new personal();
    xml xmls=new xml();
    imprimir impr=new imprimir();
    estadoHabitaciones est= new estadoHabitaciones();
    archivos arch;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(inicio.class.getName());

    public inicio() {
        arch = new archivos();
        huesped = new IngresoHuesped();
    
        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent e) 
            {
                int confirmed = JOptionPane.showConfirmDialog(null, 
                "seguro que desea abandonar?", "Hotel Mirasol",
                JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                if (confirmed == JOptionPane.YES_OPTION) 
                {
                    try{
                    arch = new archivos();
                    arch.eliminar("ini\\user.xml");
                    } catch(Exception ex){}
                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                }
            }
        }
    );
        initComponents();
        //comps
                ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon.jpg"));
        this.setIconImage(icon.getImage());
        
        //centrar imagen
        setLocationRelativeTo(null);
        //fin de centrar
        String fA = codex.getFechaActual();
        String[] fea = fA.split("-");
        String mes = codex.mes(fea[1]);
        String week = code.weekShort();
        String fechahoy=week+", "+fea[0]+" de "+mes+" del "+fea[2];
        this.setTitle(fechahoy);
        spinAno.setOpaque(false);
        spinAno.setValue(Integer.parseInt(fea[2]));
        txt_fecha.setText(mes+" del "+fea[2]);
        txt_fecha.setForeground(Color.BLACK);
        combo_mes.setSelectedIndex(Integer.parseInt(fea[1])-1);
        String ud = code.getUltimoDiaDelMes(fea[1], fea[2]+"");
        String[] split = ud.split("-");
        int uds = Integer.parseInt(split[0]);
        this.loadtable(piso1, uds, "1","");
        this.loadtable(piso2, uds, "2","");
        this.loadtable(piso3, uds, "3","");
        this.roomview(false);
        this.loadtipos();
        permiso();
    }

    
    private void permiso() {
        String[] leerXml = xmls.leerXml();
        int valor = Integer.parseInt(leerXml[2]);
        if(valor==1||valor==4||valor==5)
        {
            bntVisor.setVisible(true);
            bntHabitacion.setVisible(true);
            bntImprimir.setVisible(true);
            bntPersonal.setVisible(true);
        }
        else if(valor==2||valor==3||valor==9)
        {
            bntVisor.setVisible(true);
            bntHabitacion.setVisible(false);
            bntImprimir.setVisible(false);
            bntPersonal.setVisible(false);
        }
        else if(valor==6)
        {
            bntVisor.setVisible(true);
            bntHabitacion.setVisible(false);
            bntImprimir.setVisible(false);
            bntPersonal.setVisible(true);
        }
        else if(valor==7)
        {
            bntVisor.setVisible(true);
            bntHabitacion.setVisible(true);
            bntImprimir.setVisible(true);
            bntPersonal.setVisible(false);
        }
        else if(valor==8)
        {
            bntVisor.setVisible(true);
            bntHabitacion.setVisible(false);
            bntImprimir.setVisible(true);
            bntPersonal.setVisible(false);
        }

    }
    
    private void loadtipos() {
        Dimension size = tipo_hab.getSize();
        tipo_hab.removeAllItems();
        tipo_hab.addItem("Todas");
        // Método 1: Usando Select específico con ORDER BY
        List<Map<String, Object>> resultados = sql.Select("tiphab", "nombre", "1=1 ORDER BY nombre");
    
        try {
            for (Map<String, Object> fila : resultados) {
                Object nombreObj = fila.get("nombre");
                if (nombreObj != null) {
                    tipo_hab.addItem(nombreObj.toString());
                }
            }
            tipo_hab.setSize(size);
        
        } catch (Exception ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new org.edisoncor.gui.panel.PanelImage();
        bntVisor = new javax.swing.JButton();
        bntPersonal = new javax.swing.JButton();
        bntImprimir = new javax.swing.JButton();
        bntHabitacion = new javax.swing.JButton();
        tipo_hab = new javax.swing.JComboBox<>();
        spinAno = new javax.swing.JSpinner();
        combo_mes = new javax.swing.JComboBox<>();
        txt_fecha = new javax.swing.JLabel();
        selector = new javax.swing.JTabbedPane();
        scroll1 = new javax.swing.JScrollPane();
        piso1 = new javax.swing.JTable();
        scroll2 = new javax.swing.JScrollPane();
        piso2 = new javax.swing.JTable();
        scroll3 = new javax.swing.JScrollPane();
        piso3 = new javax.swing.JTable();
        txt_blanco = new javax.swing.JLabel();
        txt_amarillo = new javax.swing.JLabel();
        txt_verde = new javax.swing.JLabel();
        txt_rojo = new javax.swing.JLabel();
        txt_gris = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mirasol-hotel-boutique (2).jpg"))); // NOI18N
        fondo.setPreferredSize(new java.awt.Dimension(1030, 640));

        bntVisor.setText("oculto");
        bntVisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntVisorActionPerformed(evt);
            }
        });

        bntPersonal.setText("personal");
        bntPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPersonalActionPerformed(evt);
            }
        });

        bntImprimir.setText("imprimir");
        bntImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntImprimirActionPerformed(evt);
            }
        });

        bntHabitacion.setText("habitacion");
        bntHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntHabitacionActionPerformed(evt);
            }
        });

        tipo_hab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "todas", "normal", "doble", "swuite" }));
        tipo_hab.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipo_habItemStateChanged(evt);
            }
        });

        spinAno.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinAnoStateChanged(evt);
            }
        });

        combo_mes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        combo_mes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_mesItemStateChanged(evt);
            }
        });

        txt_fecha.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txt_fecha.setText("jLabel1");
        txt_fecha.setToolTipText("");

        selector.setBackground(new java.awt.Color(255, 255, 204));

        piso1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        piso1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                piso1MouseClicked(evt);
            }
        });
        scroll1.setViewportView(piso1);

        selector.addTab("tab1", scroll1);

        piso2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        piso2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                piso2MouseClicked(evt);
            }
        });
        scroll2.setViewportView(piso2);

        selector.addTab("tab2", scroll2);

        piso3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        piso3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                piso3MouseClicked(evt);
            }
        });
        scroll3.setViewportView(piso3);

        selector.addTab("tab3", scroll3);

        txt_blanco.setBackground(new java.awt.Color(255, 255, 255));
        txt_blanco.setText("DISPONIBLE");
        txt_blanco.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txt_blanco.setOpaque(true);

        txt_amarillo.setBackground(new java.awt.Color(255, 255, 102));
        txt_amarillo.setText("RESERVADO");
        txt_amarillo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txt_amarillo.setOpaque(true);

        txt_verde.setBackground(new java.awt.Color(51, 255, 0));
        txt_verde.setText("OCUPADO");
        txt_verde.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txt_verde.setOpaque(true);

        txt_rojo.setBackground(new java.awt.Color(255, 51, 51));
        txt_rojo.setText("BLOQUEADO");
        txt_rojo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txt_rojo.setOpaque(true);
        txt_rojo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_rojoMouseClicked(evt);
            }
        });

        txt_gris.setBackground(new java.awt.Color(204, 204, 204));
        txt_gris.setText("SUCIO");
        txt_gris.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txt_gris.setOpaque(true);
        txt_gris.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_grisMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(combo_mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tipo_hab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(bntHabitacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntImprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntPersonal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bntVisor))
                    .addGroup(fondoLayout.createSequentialGroup()
                        .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(fondoLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(fondoLayout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addComponent(selector, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(fondoLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_rojo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_verde, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_amarillo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_gris, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 70, Short.MAX_VALUE)))
                .addContainerGap())
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntVisor)
                    .addComponent(bntPersonal)
                    .addComponent(bntImprimir)
                    .addComponent(bntHabitacion)
                    .addComponent(tipo_hab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txt_fecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selector, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_blanco)
                    .addComponent(txt_gris)
                    .addComponent(txt_amarillo)
                    .addComponent(txt_verde)
                    .addComponent(txt_rojo))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void spinAnoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinAnoStateChanged
        reorder();
    }//GEN-LAST:event_spinAnoStateChanged

    private void bntPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPersonalActionPerformed
        per.setVisible(true);
        roomview(false);
    }//GEN-LAST:event_bntPersonalActionPerformed

    private void bntHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntHabitacionActionPerformed
        h.setVisible(true);
        h.setResizable(false);
        cleartabla(piso1);
        cleartabla(piso2);
        cleartabla(piso3);
        roomview(false);    }//GEN-LAST:event_bntHabitacionActionPerformed

    private void bntVisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVisorActionPerformed
        cleartabla(piso1);
        cleartabla(piso2);
        cleartabla(piso3);        
        if("visible".equals(bntVisor.getText()))
            this.roomview(true);
        else
            this.roomview(false);
    }//GEN-LAST:event_bntVisorActionPerformed

    private void bntImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntImprimirActionPerformed
        impr.setVisible(true);
        roomview(false);
    }//GEN-LAST:event_bntImprimirActionPerformed

    private void txt_rojoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_rojoMouseClicked
        estadohab();
        roomview(false);
    }//GEN-LAST:event_txt_rojoMouseClicked

    private void txt_grisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_grisMouseClicked
        estadohab();
        roomview(false);
    }//GEN-LAST:event_txt_grisMouseClicked

    private void tipo_habItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipo_habItemStateChanged
        reorder();
    }//GEN-LAST:event_tipo_habItemStateChanged

    private void combo_mesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_mesItemStateChanged
        reorder();
    }//GEN-LAST:event_combo_mesItemStateChanged
// trabajar aqui cambiar los valores del option pane y modificar los datos a evaluar 
    private void piso1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_piso1MouseClicked
        clickPiso(piso1);
    }//GEN-LAST:event_piso1MouseClicked

    private void piso2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_piso2MouseClicked
        clickPiso(piso2);
    }//GEN-LAST:event_piso2MouseClicked

    private void piso3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_piso3MouseClicked
        clickPiso(piso3);
    }//GEN-LAST:event_piso3MouseClicked

    /**
     * @param args the command line arguments
     */
    
    private void reorder() {
        int ma = combo_mes.getSelectedIndex()+1;
        int me=ma;
        if(ma<0){
            me=1;
            ma=1;
        }
        String men,man;
        if(me<10)
            men = "0"+me;
        else
            men = ""+me;
        if(ma<10)
            man = "0"+ma;
        else
            man = ""+ma;
        String ud = code.getUltimoDiaDelMes(man,spinAno.getValue().toString());
        String mes =codex.mes(men);
        txt_fecha.setText(mes+" del "+spinAno.getValue());
        String[] split = ud.split("-");
        int uds = Integer.parseInt(split[0]);
        String t="todas";
        if(tipo_hab.getSelectedItem()!=null)
            t = tipo_hab.getSelectedItem().toString();
        if("Todas".equals(t.trim()))
            t="";
        this.loadtable(piso1, uds, "1",t);
        this.loadtable(piso2, uds, "2",t);
        this.loadtable(piso3, uds, "3",t);
        this.roomview(true);
    }
    
    private void loadtable(JTable tabla,int dias,String piso,String tipo) {
        int pisos=0;
        tablaconfi.delrow(tabla);
        int c;
        c = tabla.getColumnCount();
        while(dias>=c)
        {
            tablaconfi.addcol(tabla);
            c = tabla.getColumnCount();
        }
        c = tabla.getColumnCount();
        while(c>dias+2)
        {
              c = tabla.getColumnCount();
            tablaconfi.Removecolum(tabla, c-1);
        }
        
        dias=tabla.getColumnCount()-1;
        Object[][] datos = tablaconfi.nombrespiso(piso,tipo);
        for(int i=0;i<49;i++){
            if(datos[i][0]!=null) {
                pisos=i+1;
            } 
        }
        for(int i=0;i<pisos;i++)
            tablaconfi.addrow(tabla);
        for(int i=0;i<pisos;i++)
        {
            tabla.setValueAt(datos[i][0], i, 0);
        }
        for(int i=0;i<pisos;i++)
            for(int j=1;j<=dias;j++)
                tabla.setValueAt("", i, j);
        for(int i=1;i<=dias;i++){
            tabla.getColumnModel().getColumn(i).setCellRenderer(new CustomRenderer());
        }
        tablaconfi.config(tabla);
        Date dia =new Date();
        int d = dia.getDate();
        if((dia.getMonth()+1)==combo_mes.getSelectedIndex()+1)
            tablaconfi.setcolorheader(tabla, Color.blue, d);
    }

    private void roomview(boolean s) {
        selector.setVisible(s);
        combo_mes.setVisible(s);
        spinAno.setVisible(s);
        txt_fecha.setVisible(s);
        tipo_hab.setVisible(s);
        txt_rojo.setVisible(s);
        txt_verde.setVisible(s);
        txt_amarillo.setVisible(s);
        txt_gris.setVisible(s);
        txt_blanco.setVisible(s);
        if(s){
            bntVisor.setText("oculto");
            this.loadgest(piso1);
            this.loadgest(piso2);
            this.loadgest(piso3);
            huesped.huesped=null;
        }
        else
            bntVisor.setText("visible");
    }
    
    private void loadgest(JTable tabla) {
        int p1c = tabla.getRowCount();
        String test,value,ob = null,in,out,con = null,con1="",con2="";
        int i,o,m;
        String mes,mes1;
        int Index = combo_mes.getSelectedIndex()+1;
        int Index1=1+Index;
        int ano = Integer.parseInt(spinAno.getValue().toString());
        int ano2=ano;
        if(Index==0)
            Index=1;
        if(Index<10)
            mes = "0"+Index;
        else
            mes = ""+Index;
        if(Index1<10)
            mes1 = "0"+Index1;
        else
            mes1 = ""+Index1;
        if(Index1==13)
        {
            mes1="01";
            ano2=ano+1;
        }
        int SelectCount = sql.SelectCount("huesped", "fecha_in between '"
                +ano+"-"+mes+"-1' and '"+ano2+"-"+mes1+"-1'"
                + " or fecha_out between '"
                +ano+"-"+mes+"-1' and '"+ano2+"-"+mes1+"-1'");
        if(SelectCount!=0){
        String SqlQuery = "SELECT fecha_in, "
                + "fecha_out, "
                + "numero, "
                + "condicion, "
                + "observacion, "
                + "idguest, " +
                "(select persona " +
                "from huesped_add " +
                "where idguest=huesped " +
                "and con='1')as \"cedula\"" +
                "from huesped " +
                "inner join habitacion " +
                "on huesped.hab = habitacion.id " +
                "where fecha_in between '"
                +ano+"-"+mes+"-1' and '"+ano2+"-"+mes1+"-1'"
                + " or fecha_out between '"
                +ano+"-"+mes+"-1' and '"+ano2+"-"+mes1+"-1'";
        List<Map<String, Object>> resultados = sql.SqlQuery(SqlQuery);
        try {
            if (!resultados.isEmpty())
            {
                for (Map<String, Object> fila : resultados) {
                for(int x=0;x<p1c;x++)
                {
                   
                    test = tabla.getValueAt(x, 0).toString();
                    value = fila.get("numero") != null ? fila.get("numero").toString() : "";
                    if(test == null ? value == null : test.trim().equals(value.trim()))
                    {
                        in=fila.get("fecha_in") != null ? fila.get("fecha_in").toString() : "";//fecha_in
                        out=fila.get("fecha_out") != null ? fila.get("fecha_out").toString() : "";//fecha_out
                        String[] sx = out.split("-");
                        o=Integer.parseInt(sx[1]);//
                        String[] sp = in.split("-");
                        i=Integer.parseInt(sp[2]);
                        m=Integer.parseInt(sp[1]);
                                             
                        if(Integer.parseInt(mes) > Integer.parseInt(sp[1]))
                        {
                            i=1;
                        }
                        if (Integer.parseInt(mes) == Integer.parseInt(sx[1]))
                        {
                            o=Integer.parseInt(sx[2]);
                            
                        }
                        else
                            o=tabla.getColumnCount();
                        con=fila.get("condicion") != null ? fila.get("condicion").toString() : "";
                        con1="";
                        con2="";
                        String query6 = null,query7 = null;
                        query6=fila.get("idguest") != null ? fila.get("idguest").toString() : "";
                        query7=fila.get("cedula") != null ? fila.get("cedula").toString() : "";
                        ob = fila.get("observacion") != null ? fila.get("observacion").toString() : "";
                        String txtdatos = datos(query7,ob);
                        
                        switch (con) {
                            case "1":
                                //rojo
                                con1="_"+" "+txtdatos;
                                con2="_"+" "+txtdatos;
                                break;
                            case "2":
                                //verde
                                con1=">>"+" "+txtdatos+">>"+query6.trim();
                                con2="*"+" "+txtdatos+">>"+query6.trim();
                                break;
                            case "3":
                                //amarillo
                                con1=">"+" "+txtdatos+">>"+query6.trim();
                                con2=","+" "+txtdatos+">>"+query6.trim();
                                break;
                            case "4":
                                //gris
                                con1="#"+" "+txtdatos;
                                con2="#"+" "+txtdatos;
                                break;
                                    
                        }
                        tabla.setValueAt(con1, x, i);
                        for(int cs=(i+1);cs<o;cs++)
                            tabla.setValueAt(con2, x, cs);
                    }
                }
            }
            }
        } catch (Exception ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }//end of catch//end of catch
        
        }
        
    }
    
    public void imag() {
        Dimension size = this.getSize();
        w = size.width;
        l = size.height;
        size.setSize(w-15, l);
        fondo.setPreferredSize(size);
    }
    
    class CustomRenderer extends DefaultTableCellRenderer  {
    private static final long serialVersionUID = 6703872492730589499L;

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String data=table.getValueAt(row, column).toString().trim();

        if(data.startsWith(">>")){
            cellComponent.setBackground(Color.green);
            cellComponent.setForeground(Color.black);
        }
        else if(data.startsWith("*")){
            cellComponent.setBackground(Color.green);
            cellComponent.setForeground(Color.GREEN);
        }
        else if(data.startsWith(">")){
            cellComponent.setBackground(Color.ORANGE);
            cellComponent.setForeground(Color.BLACK);
        }
        else if(data.startsWith(",")){
            cellComponent.setBackground(Color.ORANGE);
            cellComponent.setForeground(Color.ORANGE);
        }
        else if(data.startsWith("#")){
            cellComponent.setBackground(Color.GRAY);
            cellComponent.setForeground(Color.GRAY);
        }
        else if(data.startsWith("_")){
            cellComponent.setBackground(Color.red);
            cellComponent.setForeground(Color.red);
        }
        
        else
            cellComponent.setBackground(Color.white);
        return cellComponent;
    }
}
    
    private void cleartabla(JTable tabla) {
        int tr=tabla.getRowCount();
        int tc=tabla.getColumnCount();
        for(int i=0;i<tr;i++)
            for(int j=1;j<tc;j++)
                tabla.setValueAt("",i,j);
    }

    private void estadohab() {
        est.limpiar();
        est.mostrarbd(null, null);
        est.setVisible(true);
    }    
    
    private void activegest(JTable tabla) {
        huesped.clear();
        int r = tabla.getSelectedRow();
        int c = tabla.getSelectedColumn();
        tabla.getColumnName(c);
        huesped.dia=Integer.parseInt(tabla.getColumnName(c));
        huesped.mes=combo_mes.getSelectedIndex()+1;
        huesped.ano=Integer.parseInt(spinAno.getValue().toString());
        huesped.hab=tabla.getValueAt(r, 0).toString();
        huesped.send();
        huesped.setVisible(true);
        this.roomview(false);
            
    }
    
    private String datos(String q7,String ob){
        String sqlquery="SELECT " +
                        "p_nombre, " +
                        "p_apellido " +
                        "FROM persona WHERE identificacion = '"+q7+"';";
        String salida="";
        List<Map<String, Object>> resultado=sql.SqlQuery(sqlquery);
        try{
            if(!resultado.isEmpty()){
                Map<String, Object> fila = resultado.get(0);
                salida="ID : "+q7+"\n" +
                        "NOMBRE : "+(fila.get("p_nombre")!= null ? fila.get("p_nombre").toString() : "")+"\n" +
                        "APELLIDO : "+(fila.get("p_apellido")!= null ? fila.get("p_apellido").toString() : "")+"\n" +
                        "OBSERVACION : "+ob+"\n";
                
            }
        }catch (Exception ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salida;
    }
    
    private void clickPiso(javax.swing.JTable piso){
        int c = piso.getSelectedColumn();
        int r = piso.getSelectedRow();
        huesped.dia = Integer.parseInt( piso.getColumnName(c));
        huesped.ano = Integer.parseInt( spinAno.getValue().toString());
        huesped.mes = code.mesInt(combo_mes.getSelectedItem().toString());
        int confirmar;
        String data = piso1.getValueAt(r, c).toString();
        String[] datas = new String[2];
        if("".equals(data)){
            if(c>0)
            this.activegest(piso);
        }
        else if((!data.startsWith(">>"))&&(!data.startsWith("*"))&&(!data.startsWith(">"))&&(!data.startsWith(","))) {//regresa
            JOptionPane.showMessageDialog(null, data);
        }
        else{
            String[] dataq=data.split(">>");
            int datal=data.split(">>").length;
            if(datal==3){
                dataq[0]=dataq[1];
                dataq[1]=dataq[2];
            }
            else if(datal==2){
                if(dataq[0].contains("\\*")){
                String[] split = dataq[0].split("\\*");
                dataq[0]=split[1];
                }
                else
                {
                    dataq[0]=dataq[0].substring(1).trim();
                
                }
                    
            }
            
            confirmar=JOptionPane.showConfirmDialog(null,
                dataq[0], "Modificar Estadia?", JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE);
            if(confirmar==JOptionPane.YES_OPTION)
            {
                datas=dataq[0].split("-");
                datas[0]=datas[0].replace("ID : ", "").trim();
                String s=datas[1];
                if (s == null) datas[1]= "";
                int pos = s.indexOf("NOMBRE");
                if (pos == -1) {
                    datas[1]= s.trim(); // o return "";
                }
                datas[1]= s.substring(0, pos).trim();
                this.activegest(piso);
                huesped.huesped=dataq[1];
                huesped.guestout(datas[0],datas[1]);
            }
        }
    }
        
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new inicio().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntHabitacion;
    private javax.swing.JButton bntImprimir;
    private javax.swing.JButton bntPersonal;
    private javax.swing.JButton bntVisor;
    private javax.swing.JComboBox<String> combo_mes;
    private org.edisoncor.gui.panel.PanelImage fondo;
    private javax.swing.JTable piso1;
    private javax.swing.JTable piso2;
    private javax.swing.JTable piso3;
    private javax.swing.JScrollPane scroll1;
    private javax.swing.JScrollPane scroll2;
    private javax.swing.JScrollPane scroll3;
    private javax.swing.JTabbedPane selector;
    private javax.swing.JSpinner spinAno;
    private javax.swing.JComboBox<String> tipo_hab;
    private javax.swing.JLabel txt_amarillo;
    private javax.swing.JLabel txt_blanco;
    private javax.swing.JLabel txt_fecha;
    private javax.swing.JLabel txt_gris;
    private javax.swing.JLabel txt_rojo;
    private javax.swing.JLabel txt_verde;
    // End of variables declaration//GEN-END:variables
}

