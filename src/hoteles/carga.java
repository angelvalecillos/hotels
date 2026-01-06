package hoteles;

import classes.ManejadorSql;
import classes.createTables;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Clase carga
 * ---------------------
 * Esta clase representa una ventana de carga inicial para el sistema
 * de gestión del "Hotel Mirasol Boutique".
 * 
 * Se encarga de:
 *  - Verificar las tablas existentes en la base de datos PostgreSQL.
 *  - Crear las tablas faltantes utilizando la clase createTables.
 *  - Mostrar visualmente el progreso de la carga de datos mediante
 *    una barra de progreso (JProgressBar).
 * 
 * Autor: [Angel Valecillos]
 */
public class carga extends javax.swing.JFrame {

    ManejadorSql sql = new ManejadorSql(null, null, null, null, null, null, null, null, null);
        
    @SuppressWarnings("empty-statement")
    public carga() {
        initComponents();
        
        // Centrar imagen
        setLocationRelativeTo(null);
        
        carga.setValue(0);
        carga.setForeground(Color.yellow);
        carga.setBackground(Color.gray);

        // Iniciar la carga cuando la ventana se abre (ya visible)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                startLoading();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        carga = new javax.swing.JProgressBar();
        info = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HOTEL MIRASOL BOUTIQUE");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/hotel-mirasol-suites.jpg"))); // NOI18N
        panelImage1.addFocusListener(new java.awt.event.FocusAdapter() {
        });

        carga.setStringPainted(true);
        carga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cargaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cargaMouseExited(evt);
            }
        });

        info.setText("                 ");

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(carga, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addComponent(carga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(info))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();

        // IMPORTANTE: NO LLAMAR loading() NI ini() AQUÍ
        // Se ejecutan en windowOpened -> startLoading()
    }

    public void ini()
    {
        boolean ajustable = false;
        IniciarSesion h = null;
        try {
            h = new IniciarSesion();
        } catch (IOException ex) {
            System.getLogger(carga.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        ImageIcon x = new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon.jpg"));
        h.setIconImage(x.getImage());
        h.setVisible(true);
        h.setResizable(ajustable);
        h.setTitle("Hotel Mirasol Boutique");
    }
    
    // Ejecuta la carga en segundo plano y al terminar abre IniciarSesion y cierra esta ventana
    private void startLoading() {
        new SwingWorker<Boolean, Integer>() {

            @Override
            protected Boolean doInBackground() {
                try {
                    Thread.sleep(20);

                    int total = 0, temp = 0, x = 0;
                    String name = "", c = "";

                    // 1) Obtener nombres de tablas en schema public
                    SwingUtilities.invokeLater(() -> info.setText("cargando base de datos ..."));
                    List<Map<String, Object>> rows = sql.SqlQuery(
                        "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'"
                    );
                    for (Map<String, Object> fila : rows) {
                        Object tableNameObj = fila.get("table_name");
                        if (tableNameObj != null) {
                            name = name + tableNameObj.toString().trim() + "###";
                        }
                    }

                    String[] names = name.isEmpty() ? new String[0] : name.split("###");
                    x = names.length;

                    // 2) Tablas requeridas y creación de faltantes
                    String tables = "departamento###" +
                                    "profesion###" +
                                    "permiso###" +
                                    "pais###" +
                                    "tiphab###" +
                                    "tarifario###" +
                                    "tarjetas###" +
                                    "persona###" +
                                    "imagen###" +
                                    "per_tel###" +
                                    "habitacion###" +
                                    "precio###" +
                                    "huesped###" +
                                    "cargoshab###" +
                                    "huesped_add###" +
                                    "empleado###" +
                                    "usuario###";
                    String[] tNames = tables.split("###");
                    int tx = tNames.length;

                    createTables ct = new createTables();

                    for (int j = 0; j < tx; j++) {
                        boolean existe = false;
                        for (int i = 0; i < x; i++) {
                            if (names[i].equals(tNames[j])) {
                                existe = true;
                                break;
                            }
                        }
                        if (!existe) {
                            ct.creater(tNames[j]);
                        }
                    }
                    if (x < tx) {
                        ct.creater("foreign");
                    }

                    // Releer tablas por si se crearon nuevas
                    name = "";
                    rows = sql.SqlQuery(
                        "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'"
                    );
                    for (Map<String, Object> fila : rows) {
                        Object tableNameObj = fila.get("table_name");
                        if (tableNameObj != null) {
                            name = name + tableNameObj.toString().trim() + "###";
                        }
                    }
                    names = name.isEmpty() ? new String[0] : name.split("###");
                    x = names.length;

                    // 3) Contar registros de cada tabla
                    c = "";
                    for (int j = 0; j < x; j++) {
                        List<Map<String, Object>> countRows = sql.SqlQuery(
                            "SELECT count(*) AS count FROM \"" + names[j] + "\""
                        );
                        for (Map<String, Object> fila : countRows) {
                            Object countObj = fila.get("count");
                            if (countObj != null) {
                                c = c + countObj.toString().trim() + "###";
                            }
                        }
                    }

                    String[] status = c.isEmpty() ? new String[0] : c.split("###");
                    for (int i = 0; i < status.length; i++) {
                        if (!status[i].isEmpty()) {
                            temp = Integer.parseInt(status[i]);
                            total = total + temp;
                        }
                    }

                    final int max = total;
                    SwingUtilities.invokeLater(() -> carga.setMaximum(max));

                    // 4) Simular avance de carga según el total de registros
                    int load = 0;
                    for (int j = 0; j < x; j++) {
                        temp = (j < status.length && !status[j].isEmpty())
                                ? Integer.parseInt(status[j])
                                : 0;

                        final String tableNameForInfo = names[j];
                        SwingUtilities.invokeLater(() -> info.setText("cargando base de datos ..." + tableNameForInfo));

                        for (int i = 0; i < temp; i++) {
                            load++;
                            publish(load); // process() se ejecuta en EDT
                            Thread.sleep(1);
                        }
                    }

                    SwingUtilities.invokeLater(() -> info.setText("base de datos cargada"));
                    Thread.sleep(300);
                    return true;

                } catch (Exception ex) {
                    Logger.getLogger(carga.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }

            @Override
            protected void process(List<Integer> chunks) {
                // Actualiza la barra con el último valor publicado
                int val = chunks.get(chunks.size() - 1);
                carga.setValue(val);
            }

            @Override
            protected void done() {
                try {
                    boolean ok = get();
                    if (ok) {
                        ini();      // Abrir IniciarSesion
                    }
                } catch (Exception e) {
                    Logger.getLogger(carga.class.getName()).log(Level.SEVERE, null, e);
                } finally {
                    dispose();     // Cerrar esta ventana siempre al finalizar
                }
            }
        }.execute();
    }
    
    private void cargaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargaMouseEntered
        carga.setForeground(Color.GREEN);
        carga.setBackground(Color.lightGray);
    }//GEN-LAST:event_cargaMouseEntered

    private void cargaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargaMouseExited
        carga.setForeground(Color.YELLOW);
        carga.setBackground(Color.GRAY);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(carga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new carga().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar carga;
    private javax.swing.JLabel info;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    // End of variables declaration//GEN-END:variables
}