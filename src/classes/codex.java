package classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class codex {
    private static final Logger logger = Logger.getLogger(codex.class.getName());
    
    // ✅ CONSTANTES PARA FORMATOS DE FECHA
    private static final String FORMATO_FECHA = "dd-MM-yyyy";
    private static final String FORMATO_HORA = "HH:mm:ss"; // ✅ CAMBIADO A 24H
    
    public static String getFechaActual() {
        try {
            Date ahora = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat(FORMATO_FECHA);
            return formateador.format(ahora);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error obteniendo fecha actual", e);
            return "01-01-2000"; // Fecha por defecto en caso de error
        }
    }

    public static String getHoraActual() {
        try {
            Date ahora = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat(FORMATO_HORA);
            return formateador.format(ahora);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error obteniendo hora actual", e);
            return "00:00:00"; // Hora por defecto en caso de error
        }
    }
    
    public static java.sql.Date sumarFechasDias(java.sql.Date fch, int dias) {
        if (fch == null) {
            throw new IllegalArgumentException("Fecha no puede ser nula");
        }
        
        try {
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(fch.getTime());
            cal.add(Calendar.DATE, dias);
            return new java.sql.Date(cal.getTimeInMillis());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error sumando días a fecha", e);
            return fch; // Retornar fecha original en caso de error
        }
    }
    
    public static java.sql.Date restarFechasDias(java.sql.Date fch, int dias) {
        if (fch == null) {
            throw new IllegalArgumentException("Fecha no puede ser nula");
        }
        
        try {
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(fch.getTime());
            cal.add(Calendar.DATE, -dias);
            return new java.sql.Date(cal.getTimeInMillis());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error restando días a fecha", e);
            return fch; // Retornar fecha original en caso de error
        }
    }
    
    public static void restarFechasDias(Object[] dato, int diaSel) {
        if (dato == null || dato.length == 0) {
            return;
        }
        
        try {
            // Asumiendo que dato[0] contiene una fecha
            if (dato[0] instanceof java.sql.Date) {
                java.sql.Date fecha = (java.sql.Date) dato[0];
                java.sql.Date nuevaFecha = restarFechasDias(fecha, diaSel);
                dato[0] = nuevaFecha;
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error en restarFechasDias con array", e);
        }
    }
    
    public static int diferenciasDeFechas(Date fechaInicial, Date fechaFinal) {
        if (fechaInicial == null || fechaFinal == null) {
            throw new IllegalArgumentException("Fechas no pueden ser nulas");
        }
        
        try {
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            String fechaInicioString = df.format(fechaInicial);
            String fechaFinalString = df.format(fechaFinal);
            
            // ✅ USAR PARSE CON MANEJO DE EXCEPCIONES
            fechaInicial = df.parse(fechaInicioString);
            fechaFinal = df.parse(fechaFinalString);
            
            long fechaInicialMs = fechaInicial.getTime();
            long fechaFinalMs = fechaFinal.getTime();
            long diferencia = fechaFinalMs - fechaInicialMs;
            double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            return ((int) dias);
            
        } catch (ParseException ex) {
            logger.log(Level.SEVERE, "Error calculando diferencia de fechas", ex);
            return 0; // Retornar 0 en caso de error
        }
    }
    
    public static java.sql.Date deStringToDate(String mes, String dia, String ano) {
        // ✅ VALIDACIÓN DE PARÁMETROS
        if (mes == null || dia == null || ano == null) {
            throw new IllegalArgumentException("Parámetros de fecha no pueden ser nulos");
        }
        
        try {
            // ✅ USAR CALENDAR EN LUGAR DE CONSTRUCTOR DEPRECADO
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(ano));
            cal.set(Calendar.MONTH, Integer.parseInt(mes) - 1); // Meses van de 0-11
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dia));
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            
            return new java.sql.Date(cal.getTimeInMillis());
            
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Error convirtiendo string a fecha: " + mes + "-" + dia + "-" + ano, e);
            throw new IllegalArgumentException("Formato de fecha inválido");
        }
    }
    
    public static String mes(String mes) {
        if (mes == null) return "Enero"; // Valor por defecto
        
        // ✅ CONSISTENCIA EN NOMBRES (todos con mayúscula inicial)
        switch (mes) {
            case "01": return "Enero";
            case "02": return "Febrero";
            case "03": return "Marzo";
            case "04": return "Abril";
            case "05": return "Mayo";
            case "06": return "Junio";
            case "07": return "Julio";
            case "08": return "Agosto";
            case "09": return "Septiembre";
            case "10": return "Octubre";
            case "11": return "Noviembre";
            case "12": return "Diciembre";
            default: return "Enero"; // Valor por defecto
        }
    }
    
    public int mesInt(String mes) {
        if (mes == null) return 1; // Valor por defecto
        
        // ✅ CONVERTIR A MINÚSCULAS PARA COMPARACIÓN INSENSIBLE A MAYÚSCULAS
        String mesLower = mes.toLowerCase();
        
        switch (mesLower) {
            case "enero": return 1;
            case "febrero": return 2;
            case "marzo": return 3;
            case "abril": return 4;
            case "mayo": return 5;
            case "junio": return 6;
            case "julio": return 7;
            case "agosto": return 8;
            case "septiembre": return 9;
            case "octubre": return 10;
            case "noviembre": return 11;
            case "diciembre": return 12;
            default: return 1; // Valor por defecto
        }
    }

    public String weekShort() {
        try {
            Calendar c = Calendar.getInstance();
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            
            switch (dayOfWeek) {
                case Calendar.SUNDAY: return "Dom";
                case Calendar.MONDAY: return "Lun";
                case Calendar.TUESDAY: return "Mar";
                case Calendar.WEDNESDAY: return "Mie";
                case Calendar.THURSDAY: return "Jue";
                case Calendar.FRIDAY: return "Vie";
                case Calendar.SATURDAY: return "Sab";
                default: return "Dom";
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error obteniendo día de semana corto", e);
            return "Dom";
        }
    }
    
    public String weeklong() {
        try {
            Calendar c = Calendar.getInstance();
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            
            switch (dayOfWeek) {
                case Calendar.SUNDAY: return "Domingo";
                case Calendar.MONDAY: return "Lunes";
                case Calendar.TUESDAY: return "Martes";
                case Calendar.WEDNESDAY: return "Miércoles";
                case Calendar.THURSDAY: return "Jueves";
                case Calendar.FRIDAY: return "Viernes";
                case Calendar.SATURDAY: return "Sábado";
                default: return "Domingo";
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error obteniendo día de semana largo", e);
            return "Domingo";
        }
    }
    
    public String getUltimoDiaDelMes(String mes, String ano) {
        if (mes == null || ano == null) {
            throw new IllegalArgumentException("Mes y año no pueden ser nulos");
        }
        
        try {
            java.sql.Date dato = deStringToDate(mes, "1", ano);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dato);
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            
            Date lastDayOfMonth = calendar.getTime();
            SimpleDateFormat formateador = new SimpleDateFormat(FORMATO_FECHA);
            return formateador.format(lastDayOfMonth);
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error obteniendo último día del mes: " + mes + "-" + ano, e);
            return "01-" + mes + "-" + ano; // Fecha por defecto
        }
    }
    
public String getPrimerDiaDelMes(String mes, String ano) {
        if (mes == null || ano == null) {
            throw new IllegalArgumentException("Mes y año no pueden ser nulos");
        }
        
        try {
            java.sql.Date dato = deStringToDate(mes, "1", ano);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dato);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            
            Date firstDayOfMonth = calendar.getTime();
            SimpleDateFormat formateador = new SimpleDateFormat(FORMATO_FECHA);
            return formateador.format(firstDayOfMonth);
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error obteniendo primer día del mes: " + mes + "-" + ano, e);
            return "01-" + mes + "-" + ano; // Fecha por defecto
        }
    }
}