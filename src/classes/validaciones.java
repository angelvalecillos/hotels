package classes;

import java.awt.Color;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.InputMap;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class validaciones {
    
    public void permitir_solo_letra(java.awt.event.KeyEvent evt) { //Se valida solo letras
        char car;
        car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_SPACE) && (car != '�' && car != '�') && (car != '�' && car != '�' && car != '�' && car != '�' && car != '�' && car != '�' && car != '�' && car != '�' && car != '�' && car != '�')) {
            evt.consume();
        }
    }

    public void permitir_letras_numeros(java.awt.event.KeyEvent evt) { //Se valida letras/numeros
        char car;
        car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car < '0' || car > '9') && (car != (char) KeyEvent.VK_SPACE)) {

            evt.consume();
        }
    }

    public void permitir_espacio(java.awt.event.KeyEvent evt) { //Se valida el espacio entre caracteres
        char car;
        car = evt.getKeyChar();
        if ((car == (char) KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }

    /**
     *
     * @param evt
     * @param dat
     * @return
     */
    public String timeformat(java.awt.event.KeyEvent evt,JTextField dat)
    {
        String time;
        char car = evt.getKeyChar();
        String data=dat.getText();
        time = dat.getText()+car;
        int tx=time.length();
        if ((car >= '0' && car <= '9')) {
            String t=time;
            int tl = t.length();
            int th,tm;
            if (tl==1)
            {
                th=Integer.parseInt(t);
                if(th>2)
                    data="0"+th+":";
                else
                    data=t;
            }
            if(tl==2)
            {
                th=Integer.parseInt(t);
                if(th==24)
                    data="12:00 am";
                else if(th>24)
                    data="";
                else
                    data=t+":";
            }
            if(tl==4)
            {
                String[] ts=t.split(":");
                th=Integer.parseInt(ts[0]);
                tm=Integer.parseInt(ts[1]);
                if(tm>5)
                {
                    if(th>12)
                    {
                        if(th>21)
                            data=(th-12)+":0"+tm+" pm";
                        else
                            data="0"+(th-12)+":0"+tm+" pm";
                    }
                    else
                        data=ts[0]+":0"+tm+" ";
                }
                else
                    data=ts[0]+":"+tm;
            }
            if(tl==5)
            {
                String[] ts=t.split(":");
                th=Integer.parseInt(ts[0]);
                tm=Integer.parseInt(ts[1]);
                if(tm>59)
                    data=ts[0]+":";
                else
                {
                    if(th>12)
                    {
                         if(th>21)
                            data=(th-12)+":"+tm+" pm";
                        else
                            data="0"+(th-12)+":"+tm+" pm";
                    }
                    else{
                        if(tm==0)
                            data=ts[0]+":00 ";
                        else if(tm<10)
                            data=ts[0]+":0"+tm+" ";
                        else
                            data=ts[0]+":"+tm+" ";
                        
                    }
                        
                }
            }
        }
        if(tx>=6)
        {
            if((car=='p')||(car=='P'))
            {
                data=dat.getText()+"pm";
            }
            else if((car=='a')||(car=='A'))
            {
                data=dat.getText()+"am";
            }
        }
        if((car==':')||(car=='.'))
        {
            if(tx==2)
                data="0"+dat.getText()+":";
            else if(tx==5)
            {
                String[] ts=time.split(":");
                int th=Integer.parseInt(ts[0]);
                int tm=Integer.parseInt(ts[1]);
                if(th>12)
                    {
                        if(th>21)
                            data=(th-12)+":"+tm+" pm";
                        else
                            data="0"+(th-12)+":"+tm+" pm";
                    }
                    else
                        data=ts[0]+":"+tm+" ";
            }
                
        }
        evt.consume();
        return data;
    }

    /**
     *
     * @param dat debe contener una hora completa en formato 24
     * @return es devuelve en formato 12 am o pm
     */
    public String timeformat(JTextField dat)
    {
        String time;
        String data=dat.getText();
        char car=data.charAt(4);
        data=data.substring(0, 4);
        time = data+car;
        int tx=time.length();
        if ((car >= '0' && car <= '9')) {
            String t=time;
            int tl = t.length();
            int th,tm;
            if (tl==1)
            {
                th=Integer.parseInt(t);
                if(th>2)
                    data="0"+th+":";
                else
                    data=t;
            }
            if(tl==2)
            {
                th=Integer.parseInt(t);
                if(th==24)
                    data="12:00 am";
                else if(th>24)
                    data="";
                else
                    data=t+":";
            }
            if(tl==4)
            {
                String[] ts=t.split(":");
                th=Integer.parseInt(ts[0]);
                tm=Integer.parseInt(ts[1]);
                if(tm>5)
                {
                    if(th>12)
                    {
                        if(th>21)
                            data=(th-12)+":0"+tm+" pm";
                        else
                            data="0"+(th-12)+":0"+tm+" pm";
                    }
                    else
                        data=ts[0]+":0"+tm+" ";
                }
                else
                    data=ts[0]+":"+tm;
            }
            if(tl==5)
            {
                String[] ts=t.split(":");
                th=Integer.parseInt(ts[0]);
                tm=Integer.parseInt(ts[1]);
                if(tm>59)
                    data=ts[0]+":";
                else
                {
                    if(th>12)
                    {
                        if(th>21)
                        {
                            if(tm<10)
                                data=(th-12)+":0"+tm+" pm";
                            else
                                data=(th-12)+":"+tm+" pm";
                        }
                            
                        else
                        {
                            if(tm<10)
                                data="0"+(th-12)+":0"+tm+" pm";
                            else
                                data="0"+(th-12)+":"+tm+" pm";
                        }
                    }
                    else{
                        if(tm<10)
                            data=ts[0]+":0"+tm+" am";
                        else
                            data=ts[0]+":"+tm+" am";
                    }
                }
            }
        }
        if(tx>=6)
        {
            if((car=='p')||(car=='P'))
            {
                data=dat.getText()+"pm";
            }
            else if((car=='a')||(car=='A'))
            {
                data=dat.getText()+"am";
            }
        }
        if((car==':')||(car=='.'))
        {
            if(tx==2)
                data="0"+dat.getText()+":";
            else if(tx==5)
            {
                String[] ts=time.split(":");
                int th=Integer.parseInt(ts[0]);
                int tm=Integer.parseInt(ts[1]);
                if(th>12)
                    {
                        if(th>21)
                            data=(th-12)+":"+tm+" pm";
                        else
                            data="0"+(th-12)+":"+tm+" pm";
                    }
                    else
                        data=ts[0]+":"+tm+" ";
            }
                
        }
        return data;
    }
    public String telefono(java.awt.event.KeyEvent evt,JTextField num)
    {
        String phone;
        char car = evt.getKeyChar();
        String data = num.getText();
        phone = data+car;
        if((car>='0'&&car<='9'))
        {
            String d = phone;
            int dl = d.length();
            if(dl==1)
            {
                if(car=='0')
                    data = "0";
                else 
                    data = "";
            }
            else if(dl==2)
            {
                //0
                if(car=='2'||car=='4')
                    data = phone;
            }
            else if(dl==3)
            {
                //0X
                if("04".equals(data)){
                    if(car=='1'||car=='2')
                        data = phone;
                }
                else
                    data = phone;
            }
            else if(dl==4)
            {
                //0XX
                if(data.startsWith("04"))
                {
                    if(car=='2'||car=='4'||car=='6')
                        data=phone;
                }
                else
                    data=phone;
            }
            else if(dl==5)
            {
                //0XXX
                data= data+" "+car;
            }
            else if(dl==7)
            {
                //0XXX X
                data= phone;
            }
            else if(dl==8)
            {
                //0XXX XX
                data= phone;
            }
            else if(dl==9)
            {
                //0XXX XXX
                data= data+"-"+car;
            }
            else if(dl==11)
            {
                //0XXX XXX-X
                data= phone;
            }
            else if(dl==12)
            {
                //0XXX XXX-XX
                data= phone;
            }
            else if(dl==13)
            {
                //0XXX XXX-XXX
                data= phone;
            }
            else
                Toolkit.getDefaultToolkit().beep();
        }
        evt.consume();
        return data;
    }
    
    /**
     *
     * @param x
     * @return
     */
    public boolean validar_email(Object x)
    {
        if(x.getClass().toString().equals("class javax.swing.JTextField"))
        {
            JTextField temp=(JTextField) x;
            String email = temp.getText();
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
            Matcher m = p.matcher(email);
            boolean matchFound = m.matches();
            if(!matchFound)
            {
                temp.setBackground(Color.PINK);                 
                temp.requestFocus();
                return true;
            }
            else
            {
                temp.setBackground(Color.white);
                return false;
            }
        }
        return false;
    }
    
    public String dateformat(java.awt.event.KeyEvent evt,JFormattedTextField dat)
    {
        String date;  
        char car = evt.getKeyChar();
        String data=dat.getText();
        date = dat.getText()+car;
        if ((car >= '0' && car <= '9')) {
        String d=date;
        int dl = d.length();
        int dd,dm,da;
        if(dl==1)
        {
            dd = Integer.parseInt(d);
            if((dd>3)&&(dd<10))
                data="0"+dd+"/";
            else
                data=date;
        }
        else if(dl==2)
        {
            dd = Integer.parseInt(d);
            if(dd>31)
                data="";
            else
                data=dd+"/";
        }
        else if(dl==4)
        {
            String[] ds = d.split("/");
            dm=Integer.parseInt(ds[1]);
            dd=Integer.parseInt(ds[0]);
            if((dm!=1)&&(dm!=0)){
                if(dd<=29)
                    data=ds[0]+"/0"+dm+"/";
                else if(dd==30){
                    if(dm==2)
                        data=ds[0]+"/";
                    else
                        data=ds[0]+"/0"+dm+"/";
                }
                else{
                    if((dm==1)||(dm==3)||(dm==5)||(dm==7)||(dm==8))
                        data=ds[0]+"/0"+dm+"/";
                    else
                        data=ds[0]+"/";
                }
            }
            else
                data=d;
        }
        else if(dl==5)
        {
            String[] ds = d.split("/");
            dm=Integer.parseInt(ds[1]);
            dd=Integer.parseInt(ds[0]);
            if(dm>12)
                data=ds[0]+"/";
            else{
                if(dd<=29)
                    data=d+"/";
                else if(dd==30){
                    if(dm==2)
                        data=ds[0]+"/";
                    else
                        data=d+"/";
                }
                else{
                    if((dm==1)||(dm==3)||(dm==5)||(dm==7)||(dm==8)||(dm==10)||(dm==12))
                        data=d+"/";
                    else
                        data=ds[0]+"/";
                }
            }
        }
        else if(dl>=7)
        {
            String[] ds = d.split("/");
            dm=Integer.parseInt(ds[1]);
            dd=Integer.parseInt(ds[0]);
            da=Integer.parseInt(ds[2]);
            if((1<da)&&(da<2025))
                data=d;
            else
                data=dd+"/"+dm+"/";
        }
    }
        evt.consume();
        return data;
 }
    
    public void permitir_solo_numero(java.awt.event.KeyEvent evt) { //Se valida solo numeros
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }

    }

    public void permitir_decimales(java.awt.event.KeyEvent e, JTextField j) {
        char c = e.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
                && (c != '.')) {
            e.consume();
        }
        if (c == ',' && j.getText().contains(",")) {
            e.consume();
        }
    }

    public void copiar_pegar(JTextField x) {                       //Se valida copiar/pegar de caracteres
        InputMap map4 = x.getInputMap(x.WHEN_FOCUSED);
        map4.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");

    }

    public void copiar_pegar2(JTextArea x) {                       //Se valida copiar/pegar de caracteres
        InputMap map4 = x.getInputMap(x.WHEN_FOCUSED);
        map4.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");

    }

    public void longitud_Field(JTextField x, int l, java.awt.event.KeyEvent evt) { //Se determina la longitud

        if (x.getText().length() >= l) {
            evt.consume();
        }
    }

    public void longitud_Area(JTextArea x, int l, java.awt.event.KeyEvent evt) { //Se determina la longitud
        if (x.getText().length() >= l) {
            evt.consume();
        }
    }
    public boolean existe_Mayuscula(String x)
    {
        return !x.equals(x.toLowerCase());
    }
    public boolean existe_Minuscula(String x)
    {
        return !x.equals(x.toUpperCase());
    }
    
    public boolean existe_especial(String x)
    {
        return !x.matches("[A-Za-z0-9 ]*");
    }
    
    public boolean existe_numero(String x)
    {
        char[] chary = x.toCharArray();
        int l = chary.length;
        for(int i=0;i<l;i++)
            if(chary[i]>='0'&&chary[i]<='9')
                return true;
        return false;
    }
    
    public float stringtofloat(String data)
    {
        float total=0,dec, ent,pox;
        char[] values = data.toCharArray();
        data="";
        int length = values.length;
        for(int x=0;x<length;x++)
        {
            if((values[x]>='0')&&(values[x]<='9'))
                data+=values[x];
            else if(values[x]==','||values[x]=='.')
                data+=','; 
        }
        if("".equals(data))
            return 0;
        else if(data.split(",").length>1)
        {   
            String[] split = data.split(",");
            ent=Integer.parseInt(split[0]);
            dec=Integer.parseInt(split[1]);
            pox = (float) Math.pow(10, split[1].length());
            total = ent+(dec/pox);
        }
        else if(!data.contains(","))
            total = Integer.parseInt(data);
        else {
            length = data.length();
            total = Integer.parseInt(data.substring(0, length-1));
        }
        return total;
    }
    
    public String formatoMoneda(KeyEvent evt, JTextField campo) {
        char car = evt.getKeyChar();

        // permitir backspace y delete (no consumimos para que el campo pueda procesarlo)
        if (car == KeyEvent.VK_BACK_SPACE || car == KeyEvent.VK_DELETE) {
            // no consumir: dejar que el componente procese la borrado
            return campo.getText();
        }

        // permitir números y separadores (coma o punto)
        if (!((car >= '0' && car <= '9') || car == ',' || car == '.')) {
            evt.consume();
            return campo.getText();
        }

        String actual = campo.getText();
        String appended = actual + car;

        // normalizar: usar coma como separador decimal
        appended = appended.replace('.', ',');

        // si hay más de una coma, rechazar
        int firstComma = appended.indexOf(',');
        if (firstComma >= 0) {
            int secondComma = appended.indexOf(',', firstComma + 1);
            if (secondComma >= 0) {
                evt.consume();
                return actual;
            }
            // comprobar longitud de decimales
            String decPart = appended.substring(firstComma + 1);
            if (decPart.length() > 2) {
                evt.consume();
                return actual;
            }
        }

        // Si el primer carácter es ',' no permitir (poner 0,)
        if (appended.startsWith(",")) {
            appended = "0" + appended;
        }

        // evitar múltiples ceros a la izquierda en la parte entera (p. ej. "000")
        String[] parts = appended.split(",");
        String intPart = parts[0];
        if (intPart.length() > 1 && intPart.startsWith("0")) {
            // conservar solo un cero si no hay decimales aún
            intPart = intPart.replaceFirst("^0+(?!$)", "");
            if (intPart.isEmpty()) intPart = "0";
            appended = intPart + (parts.length > 1 ? ("," + parts[1]) : "");
        }

        evt.consume();
        return appended;
    }
    
    
}
