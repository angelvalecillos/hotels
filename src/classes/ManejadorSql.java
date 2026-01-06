package classes;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ManejadorSql {
    private final String driver, nombre_db, ip, port, user, pass, jdbc;
    private String clave, usuario, tipo, id, nombre_usuario;
    private final String h2;
    public validaciones val;
    private Calendar calendario = new GregorianCalendar();
    private final boolean test;
    
    // Flag de debug para activar/desactivar los prints
    private boolean debug = true;
    
    // Pool simple manual para evitar crear muchas conexiones
    private static final int MAX_CONNECTIONS = 10;
    private static final List<Connection> connectionPool = new ArrayList<>();
    private static final List<Connection> usedConnections = new ArrayList<>();
    
    public ManejadorSql(String db_name, String ip, String user, String pass, String driver2, String port2, String jdbc2, String h3, validaciones val2) {        
        archivos txt = new archivos();
        String leer = "";

        try {
            leer = txt.leer("ini\\ini.txt", "\n");
        } catch (IOException ex) {
            Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Variables temporales para evitar asignar a final fields en el constructor
        String tempJdbc = jdbc2;
        String tempIp = ip;
        String tempPort = port2;
        String tempDbName = db_name;
        String tempDriver = driver2;
        String tempUser = user;
        String tempPass = pass;
        
        if (!leer.isEmpty()) {
            String[] split = leer.split("\n");
            if (split.length == 7) {
                tempJdbc = split[0].trim();
                tempIp = split[1].trim();
                tempPort = split[2].trim();
                tempDbName = split[3].trim();
                tempDriver = split[4].trim();
                tempUser = split[5].trim();
                tempPass = split[6].trim();
            }
        }
        
        // Asignación a campos finales
        this.ip = tempIp;
        this.nombre_db = tempDbName;
        this.user = tempUser;
        this.pass = tempPass;
        this.driver = tempDriver;
        this.port = tempPort;
        this.jdbc = tempJdbc;
        this.h2 = "0";
        this.val = val2;
        this.test = testConnection();
    }
    
    private boolean testConnection() {
        try (Connection testConn = createConnection()) {
            return testConn != null && !testConn.isClosed();
        } catch (SQLException ex) {
            try {
                this.Log("Error Critico", "Error testeando conexión: " + ex.getMessage(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
    }
    
    private Connection createConnection() {
        try {
            Class.forName(this.driver);
            String connectionString;
            
            if ("1".equals(this.h2)) {
                connectionString = this.jdbc + "://" + this.ip + ";databaseName=" + this.nombre_db + ";user=" + this.user + ";password=" + this.pass + ";";
            } else {
                connectionString = this.jdbc + "://" + this.ip + ":" + this.port + "/" + this.nombre_db;
            }
            
            return DriverManager.getConnection(connectionString, this.user, this.pass);
            
        } catch (ClassNotFoundException | SQLException ex) {
            try {
                this.Log("Error Critico", "Error creando conexión: " + ex.getMessage(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, "Error de conexión: " + ex.getMessage());
            return null;
        }
    }
    
    // Pool simple manual
    private synchronized Connection getConnectionFromPool() throws SQLException {
        if (!connectionPool.isEmpty()) {
            Connection conn = connectionPool.remove(connectionPool.size() - 1);
            usedConnections.add(conn);
            return conn;
        }
        
        if (usedConnections.size() < MAX_CONNECTIONS) {
            Connection conn = createConnection();
            if (conn != null) {
                usedConnections.add(conn);
            }
            return conn;
        }
        
        throw new SQLException("Pool de conexiones agotado");
    }
    
    private synchronized void returnConnectionToPool(Connection connection) {
        if (connection != null) {
            usedConnections.remove(connection);
            try {
                if (!connection.isClosed()) {
                    connectionPool.add(connection);
                }
            } catch (SQLException e) {
                // Ignorar
            }
        }
    }
    
    public boolean prueba() {
        return this.test;
    }
    
    private String toHexadecimal(byte[] digest) {
        if (digest == null) return "";
        
        StringBuilder hash = new StringBuilder();
        for (byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                hash.append("0");
            }
            hash.append(Integer.toHexString(b));
        }
        return hash.toString();
    }
    
    public String getStringMessageDigest(String message, String algorithm) {
        if (message == null || algorithm == null) {
            return "";
        }

        byte[] digest = null;
        byte[] buffer = message.getBytes();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }

        return toHexadecimal(digest);
    }
        
    public static String getPath(String p) {
        if (p == null) return "";
        
        StringBuilder dir = new StringBuilder();
        char d = '\\';
        for (int x = 0; x < p.length(); x++) {
            char l = p.charAt(x);
            if (d == l) {
                dir.append("\\\\");
            } else {
                dir.append(l);
            }
        }
        return dir.toString();
    }
    
    public void Desconectar() {
        // Cerrar todas las conexiones del pool
        for (Connection conn : connectionPool) {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                // Ignorar
            }
        }
        connectionPool.clear();
        
        for (Connection conn : usedConnections) {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                // Ignorar
            }
        }
        usedConnections.clear();
    }
    
    
    // Setter para activar/desactivar prints en runtime
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
    
    // Método genérico para ejecutar consultas que devuelven resultados
    private List<Map<String, Object>> executeQuery(String sql) {
        List<Map<String, Object>> results = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnectionFromPool();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            try {
                this.Log(this.getUsuario(), sql + " " + calendario.getTime(), "BD");
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                results.add(row);
            }
            
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), sql + " " + calendario.getTime() + " ERROR= " + ex.getMessage(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos en orden inverso
            if (rs != null) {
                try { rs.close(); } catch (SQLException ex) { /* Ignorar */ }
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException ex) { /* Ignorar */ }
            }
            if (conn != null) {
                returnConnectionToPool(conn);
            }
        }
        
        return results;
    }
    
    // Método genérico para ejecutar updates/inserts/deletes
    private boolean executeUpdate(String sql) {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = getConnectionFromPool();
            stmt = conn.createStatement();
            int affectedRows = stmt.executeUpdate(sql);
            
            try {
                this.Log(this.getUsuario(), sql + " " + calendario.getTime(), "BD");
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return affectedRows > 0;
            
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), sql + " " + calendario.getTime() + " ERROR= " + ex.getMessage(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException ex) { /* Ignorar */ }
            }
            if (conn != null) {
                returnConnectionToPool(conn);
            }
        }
    }
    
    // Métodos específicos usando los genéricos
    public boolean Update(String tabla, String set, String condition) {
        if (tabla == null || set == null || condition == null) {
            return false;
        }
        String sql = "UPDATE " + tabla + " SET " + set + " WHERE " + condition;
        return executeUpdate(sql);
    }
    
    public boolean Insert(String tabla, String values) {
        if (tabla == null || values == null) {
            return false;
        }
        String sql = "INSERT INTO " + tabla + " VALUES(" + values + ")";
        return executeUpdate(sql);
    }
    
    public boolean Insert(String tabla, String campos, String values) {
        if (tabla == null || campos == null || values == null) {
            return false;
        }
        String sql = "INSERT INTO " + tabla + "(" + campos + ") VALUES(" + values + ")";
        
        return executeUpdate(sql);
    }
    
    public boolean Delete(String tabla, String condition) {
        if (tabla == null || condition == null) {
            return false;
        }
        String sql = "DELETE FROM " + tabla + " WHERE " + condition;
        return executeUpdate(sql);
    }
    
    public List<Map<String, Object>> Select(String tabla, String columns) {
        if (tabla == null || columns == null) {
            return new ArrayList<>();
        }
        String sql = "SELECT " + columns + " FROM " + tabla;
        return executeQuery(sql);
    }
    
    public List<Map<String, Object>> Select(String tabla, String columns, String Where) {
        if (tabla == null || columns == null || Where == null) {
            return new ArrayList<>();
        }
        String sql = "SELECT " + columns + " FROM " + tabla + " WHERE " + Where;
        return executeQuery(sql);
    }
    
    public List<Map<String, Object>> SqlQuery(String data) {
        if (data == null || data.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String sql = data.endsWith(";") ? data : data + ";";
        return executeQuery(sql);
    }
    
    public void SqlCreateTables(String data) {
        if (data == null || data.trim().isEmpty()) {
            return;
        }
        String sql = data.endsWith(";") ? data : data + ";";
        executeUpdate(sql);
    }
    
    public int SelectCount(String tabla, String Where) {
        if (tabla == null || Where == null) {
            return 0;
        }
        String sql = "SELECT COUNT(*) FROM " + tabla + " WHERE " + Where;
        List<Map<String, Object>> results = executeQuery(sql);
        if (!results.isEmpty()) {
            Object count = results.get(0).get("count");
            if (count != null) {
                return ((Number) count).intValue();
            }
        }
        return 0;
    }
    
    public int sqlCount(String count) {
        if (count == null || count.trim().isEmpty()) {
            return 0;
        }
        String sql = count.endsWith(";") ? count : count + ";";
        List<Map<String, Object>> results = executeQuery(sql);
        if (!results.isEmpty()) {
            Object firstValue = results.get(0).values().iterator().next();
            if (firstValue != null) {
                return ((Number) firstValue).intValue();
            }
        }
        return 0;
    }
    
    public boolean guardarFoto(String name, String ruta, String link) {
        if (name == null || ruta == null || link == null) {
            return false;
        }
        
        File file = new File(ruta);
        if (!file.exists()) {
            return false;
        }
        
        Connection conn = null;
        PreparedStatement pstm = null;
        FileInputStream fis = null;
        
        try {
            conn = getConnectionFromPool();
            fis = new FileInputStream(file);
            
            if (SelectCount("imagen", "link='" + link.replace("'", "''") + "'") == 0) {
                String sql = "INSERT INTO imagen (nombre, imagen, link) VALUES (?, ?, ?)";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, name);
                pstm.setBinaryStream(2, fis, (int) file.length());
                pstm.setString(3, link);
                return pstm.executeUpdate() > 0;
            } else {
                String sql = "UPDATE imagen SET nombre=?, imagen=? WHERE link=?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, name);
                pstm.setBinaryStream(2, fis, (int) file.length());
                pstm.setString(3, link);
                return pstm.executeUpdate() > 0;
            }
        } catch (IOException | SQLException e) {
            System.err.println("Error guardando imagen: " + e.getMessage());
            return false;
        } finally {
            if (pstm != null) {
                try { pstm.close(); } catch (SQLException ex) { /* Ignorar */ }
            }
            if (fis != null) {
                try { fis.close(); } catch (IOException ex) { /* Ignorar */ }
            }
            if (conn != null) {
                returnConnectionToPool(conn);
            }
        }
    }

    private Image ConvertirImagen(byte[] bytes) throws IOException {
        if (bytes == null) {
            return null;
        }
        
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes)) {
            Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpeg");
            if (!readers.hasNext()) {
                return null;
            }
            
            ImageReader reader = readers.next();
            try (ImageInputStream iis = ImageIO.createImageInputStream(bis)) {
                reader.setInput(iis, true);
                ImageReadParam param = reader.getDefaultReadParam();
                Image image = reader.read(0, param);
                reader.dispose();
                return image;
            }
        }
    }
    
    public DefaultListModel<imagen> Lista_Imagenes() {
        DefaultListModel<imagen> dml = new DefaultListModel<>();
        String sql = "SELECT nombre, imagen, link FROM imagen";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        
        try {
            conn = getConnectionFromPool();
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            
            while (resultSet.next()) {
                imagen img = new imagen();
                img.setId(resultSet.getString("nombre"));
                img.setName(resultSet.getString("link"));
                try {
                    img.setArchivo(ConvertirImagen(resultSet.getBytes("imagen")));
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
                dml.addElement(img);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException ex) { /* Ignorar */ }
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException ex) { /* Ignorar */ }
            }
            if (conn != null) {
                returnConnectionToPool(conn);
            }
        }
        return dml;
    }
    
    public Image getImagen(String link) throws SQLException {
        if (link == null) {
            return null;
        }
        
        String sql = "SELECT imagen FROM imagen WHERE link = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        
        try {
            conn = getConnectionFromPool();
            ps = conn.prepareStatement(sql);
            ps.setString(1, link);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                is = rs.getBinaryStream(1);
                if (is != null) {
                    return ImageIO.read(is);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (is != null) {
                try { is.close(); } catch (IOException ex) { /* Ignorar */ }
            }
            if (rs != null) {
                try { rs.close(); } catch (SQLException ex) { /* Ignorar */ }
            }
            if (ps != null) {
                try { ps.close(); } catch (SQLException ex) { /* Ignorar */ }
            }
            if (conn != null) {
                returnConnectionToPool(conn);
            }
        }
        return null;
    }
    
    // Getters y Setters
    public String getClave() { return clave; }

    public void setClave(String clave) { this.clave = clave; }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getNombre_usuario() { return nombre_usuario; }

    public void setNombre_usuario(String nombre_usuario) { this.nombre_usuario = nombre_usuario; }
    
    public void Log(String Usr, String Mensaje, String tipo) throws IOException {
        if (Usr == null || Mensaje == null || tipo == null) {
            return;
        }
        
        Calendar Calendario = Calendar.getInstance();
        String ano = Integer.toString(Calendario.get(Calendar.YEAR));
        String mes = Integer.toString(Calendario.get(Calendar.MONTH) + 1);
        String dia = Integer.toString(Calendario.get(Calendar.DATE));
        
        if (mes.length() == 1) mes = "0" + mes;
        if (dia.length() == 1) dia = "0" + dia;

        String directorio = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        String nbFold = directorio + separador + "log" + separador + ano + mes + dia;
        String nbFile = nbFold + separador + Usr + ".xml";

        File fichero2 = new File(nbFold);
        if (!fichero2.exists()) {
            fichero2.mkdirs();
        }
        
        try (FileWriter fichero3 = new FileWriter(nbFile, true);
             PrintWriter pw = new PrintWriter(fichero3)) {
            
            String sistema = dia + "/" + mes + "/" + ano;
            String linea = "<LOG TP='" + tipo + "'><![CDATA[" + sistema + " -> " + Mensaje + "]]></LOG>\n";
            pw.append(linea);
        }
    }
    
    // Método para obtener estadísticas del pool
    public String getPoolStats() {
        return String.format(
            "Pool Stats: Active=%d, Idle=%d, Total=%d",
            usedConnections.size(),
            connectionPool.size(),
            usedConnections.size() + connectionPool.size()
        );
    }
    
    // ============================================================================
// MÉTODOS SEGUROS PARAMETRIZADOS - AGREGAR AL FINAL DE LA CLASE ManejadorSql
// ============================================================================

/**
 * Ejecuta consulta SQL parametrizada para prevenir inyección SQL
 */
public List<Map<String, Object>> executeParametrizedQuery(String sql, Object... params) {
    List<Map<String, Object>> results = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        // print la consulta y parámetros (no intenta sustituir '?' por valores, solo muestra)
        conn = getConnectionFromPool();
        pstmt = conn.prepareStatement(sql);
        
        // Establecer parámetros
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }

        rs = pstmt.executeQuery();
        
        // Log seguro (sin parámetros)
        try {
            this.Log(this.getUsuario(), "Consulta parametrizada ejecutada " + calendario.getTime(), "BD");
        } catch (IOException ex) {
            Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                row.put(metaData.getColumnName(i), rs.getObject(i));

            }
            results.add(row);
        }
        
    } catch (SQLException ex) {
        try {
            this.Log(this.getUsuario(), "Error en consulta parametrizada: " + ex.getMessage(), "BD");
        } catch (IOException ex1) {
            Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex1);
        }
        Logger.getLogger(ManejadorSql.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        if (rs != null) {
            try { rs.close(); } catch (SQLException ex) { /* Ignorar */ }
        }
        if (pstmt != null) {
            try { pstmt.close(); } catch (SQLException ex) { /* Ignorar */ }
        }
        if (conn != null) {
            returnConnectionToPool(conn);
        }
    }
    
    return results;
}


/**
 * Método seguro para validar usuario (REEMPLAZA EL CÓDIGO VULNERABLE)
 */
public List<Map<String, Object>> validarUsuarioSeguro(String usuario, String claveHash) {
    String sql = "SELECT * FROM usuario WHERE usuario = ? AND clave = ?";
    return executeParametrizedQuery(sql, usuario, claveHash);
}



/**
 * Valida y limpia el pool de conexiones
 */
public void validarYLimpiarPool() {
    synchronized (ManejadorSql.class) {
        // Limpiar conexiones usadas
        usedConnections.removeIf(conn -> {
            try {
                return conn == null || conn.isClosed() || !conn.isValid(2);
            } catch (SQLException e) {
                return true;
            }
        });
        
        // Limpiar pool libre
        connectionPool.removeIf(conn -> {
            try {
                return conn == null || conn.isClosed() || !conn.isValid(2);
            } catch (SQLException e) {
                return true;
            }
        });
        
        System.out.println("Pool limpiado - Activas: " + usedConnections.size() + 
                          ", Libres: " + connectionPool.size());
    }
}
 
/**
 * Método seguro para conteos parametrizados
 */
public int sqlCountParametrized(String countQuery, Object... params) {
    if (countQuery == null || countQuery.trim().isEmpty()) {
        return 0;
    }
    
    String sql = countQuery.endsWith(";") ? countQuery : countQuery + ";";
    List<Map<String, Object>> results = executeParametrizedQuery(sql, params);
    
    if (!results.isEmpty()) {
        Object firstValue = results.get(0).values().iterator().next();
        if (firstValue != null) {
            return ((Number) firstValue).intValue();
        }
    }
    return 0;
}



}