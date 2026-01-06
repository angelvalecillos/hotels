/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 *
 * @author angelito
 */
public class tarjetaCredito {
    public boolean validarNumero(String cadena)
    {
        int longitud = cadena.length();
        int cifra = 0;
        String cifraCad = "";
        int suma=0;
        for(int i = 0;i<longitud;i+=2)
        {
            cifra = Integer.parseInt(cadena.charAt(i)+"")*2;
            if(cifra>9)
            {
                 cifraCad = cifra+"";
                 cifra = Integer.parseInt(cifraCad.charAt(0)+"")+Integer.parseInt(cifraCad.charAt(1)+"");
            }
            suma+=cifra;
        }
        for(int i = 0;i<longitud;i+=2)
            suma += Integer.parseInt(cadena.charAt(i)+"");
        return true;
    }
}
