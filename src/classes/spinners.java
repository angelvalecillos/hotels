/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.util.Calendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author angelito
 */
public class spinners {
    public void time(JSpinner data)
    {
        SpinnerDateModel model = new SpinnerDateModel();
        model.setCalendarField(Calendar.MINUTE);
        data.setModel(model);
        data.setEditor(new JSpinner.DateEditor(data, "h:mm a"));
    }
    public void datebefore(JSpinner data)
    {
        SpinnerDateModel model = new SpinnerDateModel();
        model.setCalendarField(Calendar.DAY_OF_MONTH);
        Calendar date = Calendar.getInstance();
        model.setEnd(date.getTime());
        data.setModel(model);
        data.setEditor(new JSpinner.DateEditor(data, "dd/MM/yyyy"));
    }
    public void dateafter(JSpinner data)
    {
        SpinnerDateModel model = new SpinnerDateModel();
        model.setCalendarField(Calendar.DAY_OF_MONTH);
        Calendar date = Calendar.getInstance();
        model.setStart(date.getTime());
        data.setModel(model);
        data.setEditor(new JSpinner.DateEditor(data, "dd/MM/yyyy"));
        
    }
    public void numlimit(JSpinner data ,float inicio,float minim,float max, float step)
    {
        SpinnerNumberModel model = new SpinnerNumberModel(inicio, minim, max, step);
        data.setModel(model);
    }
    public void numlimit(JSpinner data ,int inicio,int minim,int max, int step)
    {
        SpinnerNumberModel model = new SpinnerNumberModel(inicio, minim, max, step);
        data.setModel(model);
    }
    
}
