/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.Image;
/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 * CLASE PARA ALMACENAR LOS DATOS DE LA TABLA IMAGEN
 */
public class imagen {

    private String id,name;
    private Image archivo;

    public Image getArchivo() {
        return archivo;
    }

    public void setArchivo(Image archivo) {
        this.archivo = archivo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    

    @Override
    public String toString() {
        return this.id+" : "+this.name;
    }

}