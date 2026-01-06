package hoteles;

import classes.tablas;
import classes.ManejadorSql;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class panel2 extends javax.swing.JPanel {

            ManejadorSql sql = new ManejadorSql(null, null, null, null, null, null, null, null, null);
            tablas tabla =new tablas();
    public panel2() {
        initComponents();
        tabla.combobox(tablaacomp, 0, this.pais());
        tabla.datepicker(tablaacomp, 6);
    }

    
    
    public int getrowcount()
    {
         return tablaacomp.getRowCount();
    }

    private String[] pais() {
    // Corregido: usar SelectCount para obtener el número de registros
    int x = sql.SelectCount("pais", "1=1");
    
    // Corregido: usar SqlQuery que devuelve List<Map<String, Object>>
    List<Map<String, Object>> resultados = sql.SqlQuery("SELECT id, nombre FROM pais");
    
    String[] data = new String[resultados.size()];
    int i = 0;
    
    for (Map<String, Object> fila : resultados) {
        String nombre = fila.get("nombre") != null ? fila.get("nombre").toString().trim() : "";
        String id = fila.get("id") != null ? fila.get("id").toString().trim() : "";
        data[i] = nombre + "             -" + id;
        i++;
    }
    
    return data;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tablaacomp = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(604, 324));

        tablaacomp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "pais", "Cedula/Pasaporte", "P Nombre", "S Nombre", "P Apellido", "S Apellido", "F. Nacimiento"
            }
        ));
        tablaacomp.setPreferredSize(new java.awt.Dimension(604, 324));
        tablaacomp.getTableHeader().setReorderingAllowed(false);
        tablaacomp.addContainerListener(new java.awt.event.ContainerAdapter() {
            
        });
        tablaacomp.addKeyListener(new java.awt.event.KeyAdapter() {
            
        });
        
        jScrollPane2.setViewportView(tablaacomp);
        if (tablaacomp.getColumnModel().getColumnCount() > 0) {
            tablaacomp.getColumnModel().getColumn(0).setResizable(false);
            tablaacomp.getColumnModel().getColumn(0).setPreferredWidth(60);
            tablaacomp.getColumnModel().getColumn(1).setResizable(false);
            tablaacomp.getColumnModel().getColumn(1).setPreferredWidth(60);
            tablaacomp.getColumnModel().getColumn(2).setResizable(false);
            tablaacomp.getColumnModel().getColumn(3).setResizable(false);
            tablaacomp.getColumnModel().getColumn(4).setResizable(false);
            tablaacomp.getColumnModel().getColumn(5).setResizable(false);
            tablaacomp.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public String guardar()    {
        boolean guard = false;
        String ids="",aux = "";
        int rowCount = tablaacomp.getRowCount();
        for(int r=rowCount-1;r>=0;r--)
        {
            Object[] data = new Object[7];
            for(int i=0; i<7;i++)
            {
                data[i]=tablaacomp.getValueAt(r, i);
                if(data[i]==null)
                {
                    guard =false;
                    boolean mensaje = this.mensaje("DATOS VACIOS", "la fila "+(i+1)+" esta sin informacion desea eliminar la celda");
                    if(mensaje){
                        tabla.delrow(tablaacomp, r);
                    }
                    break;
                }
                else guard = true;
            }
            if(guard)
            aux = this.guardaradd(r);
            ids +="xxx"+aux;
        }
        if(ids.length()>3)
            ids=ids.substring(3);
        return ids;
    }
        
    public String guardaradd(int r)    {
        String cedula,pnom,snom,pape,sape,fech;
        String[] c = tablaacomp.getValueAt(r, 0).toString().split("-");
        cedula = c[1].trim()+"-"+tablaacomp.getValueAt(r, 1).toString().trim();
        pnom = tablaacomp.getValueAt(r, 2).toString().trim();
        snom = tablaacomp.getValueAt(r, 3).toString().trim();
        pape = tablaacomp.getValueAt(r, 4).toString().trim();
        sape = tablaacomp.getValueAt(r, 5).toString().trim();
        fech = tablaacomp.getValueAt(r, 6).toString().trim();
        int SelectCount = sql.SelectCount("persona", "identificacion='"+cedula+"'");
        if(SelectCount==0)
        {
            sql.Insert("persona", "identificacion,"
                    + "p_nombre,"
                    + "s_nombre,"
                    + "p_apellido,"
                    + "s_apellido,"
                    + "fenac", 
                            "'"+cedula+"',"
                            + "'"+pnom+"',"
                            + "'"+snom+"',"
                            + "'"+pape+"',"
                            + "'"+sape+"',"
                            + "'"+fech+"'");
        }
        else if(SelectCount==1)
        {
            sql.Update("persona", "p_nombre='"+pnom+"',"
                    + "s_nombre='"+snom+"',"
                    + "p_apellido='"+pape+"',"
                    + "s_apellido='"+sape+"',"
                    + "fenac='"+fech+"'", "identificacion='"+cedula+"'");
        }
        return cedula;
    }
    
    private String selectPais(String code)    {
        code="-"+code.trim();
        String[] test = this.pais();
        int x=test.length;
        for(int i=0;i<x;i++)
        {
            if(test[i].endsWith(code))
                code = test[i];
        }
            
        return code;
    }
    
    public void clear()    {
        tabla.delrow(tablaacomp);
    }
    
    public void adicionales(String snumero, String fecha) {
    int r = 0;

    // 1. Obtenemos el conteo
    int SelectCount = sql.SelectCount("huesped inner join habitacion on huesped.hab = habitacion.id "
            + "inner join huesped_add on huesped.idguest = huesped_add.huesped "
            + "inner join persona on huesped_add.persona = persona.identificacion ",
            "fecha_in >= '" + fecha + "' and '" + fecha + "'<=fecha_out "
            + "and numero='" + snumero + "' and huesped_add.con = '2'");

    // 2. Solo procedemos si el conteo es mayor a 0
    if (SelectCount > 0) {
        String[] paiss = new String[SelectCount];

        // 3. Ejecutamos la consulta para obtener los datos
        List<Map<String, Object>> resultados = sql.SqlQuery("select identificacion,p_nombre,s_nombre,p_apellido,s_apellido,fenac "
                + "from huesped "
                + "inner join habitacion on huesped.hab = habitacion.id "
                + "inner join huesped_add on huesped.idguest = huesped_add.huesped "
                + "inner join persona on huesped_add.persona = persona.identificacion "
                + "where fecha_in >= '" + fecha + "' "
                + "and '" + fecha + "'<=fecha_out "
                + "and numero='" + snumero + "' "
                + "and con = '2'"); 

        // 4. Llenamos la tabla con los resultados
        for (Map<String, Object> fila : resultados) {
            tabla.addrow(tablaacomp);
            r = tablaacomp.getRowCount();

            if (fila.get("identificacion") != null) {
                String identificacion = fila.get("identificacion").toString().trim();
                if (identificacion.contains("-")) {
                    String[] va = identificacion.split("-");
                    // Validamos que el índice no exceda el tamaño del arreglo paiss
                    if (r - 1 < paiss.length) {
                        paiss[r - 1] = va[0];
                    }
                    tablaacomp.setValueAt(va[1], r - 1, 1);
                }
            }

            if (fila.get("p_nombre") != null) tablaacomp.setValueAt(fila.get("p_nombre").toString(), r - 1, 2);
            if (fila.get("s_nombre") != null) tablaacomp.setValueAt(fila.get("s_nombre").toString(), r - 1, 3);
            if (fila.get("p_apellido") != null) tablaacomp.setValueAt(fila.get("p_apellido").toString(), r - 1, 4);
            if (fila.get("s_apellido") != null) tablaacomp.setValueAt(fila.get("s_apellido").toString(), r - 1, 5);
            if (fila.get("fenac") != null) tablaacomp.setValueAt(fila.get("fenac"), r - 1, 6);
        }

        // 5. Asignamos los países (solo si se cargaron filas)
        for (int i = 0; i < r; i++) {
            if (paiss[i] != null) {
                tablaacomp.setValueAt(this.selectPais(paiss[i]), i, 0);
            }
        }
    } else {
        System.out.println("No se encontraron registros para cargar.");
    }
}
    
    public int newgest()    {
        tabla.addrow(tablaacomp);
        return tablaacomp.getRowCount();
    }
    
    public int delgest()    {
        if(tablaacomp.getSelectedRow()!=(-1))
        {
            int row = tablaacomp.getSelectedRow();
            if(this.mensaje("Eliminar", "desea elimnar la celda seleccionada"))
                tabla.delrow(tablaacomp, row);            
        }
        return tablaacomp.getRowCount();
    }
    
    private boolean mensaje(String titulo,String mensaje)    {
        int confirmar;
        confirmar=JOptionPane.showConfirmDialog(null,
                mensaje,
                titulo, 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if(confirmar==0)
            return true;
        else
            return false;
                
    }
    
    public void cargarAcompanantesExistente(String idHuesped) {
    try {
        // Limpiar tabla primero
        tabla.delrow(tablaacomp);
        
        // ✅ CORREGIDO: Conversión explícita de tipos
        String query = "SELECT p.identificacion, p.p_nombre, p.s_nombre, p.p_apellido, p.s_apellido, p.fenac " +
                      "FROM huesped_add ha " +
                      "INNER JOIN persona p ON ha.persona = p.identificacion " +
                      "WHERE ha.huesped::text = ? AND ha.con = '2' " +  // ✅ CAST explícito
                      "ORDER BY p.identificacion";
        
        List<Map<String, Object>> resultados = sql.executeParametrizedQuery(query, idHuesped);
        
        
        for (Map<String, Object> fila : resultados) {
            int rowIndex = tablaacomp.getRowCount();
            tabla.addrow(tablaacomp);
            
            // Obtener identificacion y separar país-cédula
            String identificacion = getSafeString(fila, "identificacion");
            if (identificacion != null && identificacion.contains("-")) {
                String[] partes = identificacion.split("-");
                if (partes.length >= 2) {
                    String codigoPais = partes[0].trim();
                    String numeroDoc = partes[1].trim();
                    
                    // Buscar y configurar el país en la columna 0
                    String paisCompleto = selectPais(codigoPais);
                    tablaacomp.setValueAt(paisCompleto, rowIndex, 0);
                    
                    // Configurar cédula en columna 1
                    tablaacomp.setValueAt(numeroDoc, rowIndex, 1);
                }
            }
            
            // Configurar nombres y apellidos (con verificación de null)
            tablaacomp.setValueAt(getSafeString(fila, "p_nombre"), rowIndex, 2);
            tablaacomp.setValueAt(getSafeString(fila, "s_nombre"), rowIndex, 3);
            tablaacomp.setValueAt(getSafeString(fila, "p_apellido"), rowIndex, 4);
            tablaacomp.setValueAt(getSafeString(fila, "s_apellido"), rowIndex, 5);
            
            // Configurar fecha de nacimiento
            Object fenacObj = fila.get("fenac");
            if (fenacObj != null) {
                tablaacomp.setValueAt(fenacObj.toString(), rowIndex, 6);
            }
        }
        
        
    } catch (Exception e) {
        System.err.println("Error cargando acompañantes existentes: " + e.getMessage());
        e.printStackTrace();
    }
}
    // AGREGA ESTE MÉTODO AUXILIAR SI NO EXISTE
    
    private String getSafeString(Map<String, Object> map, String key) {
        Object value = map.get(key);
    return value != null ? value.toString().trim() : "";
}
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaacomp;
    // End of variables declaration//GEN-END:variables
}
