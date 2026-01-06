package hoteles;

import classes.ManejadorSql;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import classes.InstallDB;


/**
 *
 * @author Usuario
 */
public class Hoteles {
    
        
    public void start() {
        
        ManejadorSql sql = new ManejadorSql(null, null, null, null, null, null, null, null, null);
                boolean prueba = sql.prueba();
        if(prueba)
        {
                this.ini();
        }
        else
        {
            InstallDB c=new InstallDB();
            c.setVisible(true);
            c.setResizable(false);
       }}
    
    public void ini()
    {
        boolean ajustable;
        ajustable = false;
        carga h = null;
        h=new carga();
        ImageIcon x = new javax.swing.ImageIcon(getClass().getResource("/imagenes/icon.jpg"));
        h.setIconImage(x.getImage());
        h.setVisible(true);
        h.setResizable(ajustable);
        h.setTitle("Hotel Mirasol Boutique");
        
    }
    
}
