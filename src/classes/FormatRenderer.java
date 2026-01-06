package classes;

import java.text.Format;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatRenderer extends DefaultTableCellRenderer {
    private static final Logger logger = Logger.getLogger(FormatRenderer.class.getName());
    
    private Format formatter;
    private String defaultText; // ✅ TEXTO POR DEFECTO PARA VALORES NULOS

    /*
     * Use the specified formatter to format the Object
     */
    public FormatRenderer(Format formatter) {
        this(formatter, ""); // ✅ VALOR POR DEFECTO VACÍO
    }
    
    // ✅ NUEVO CONSTRUCTOR CON TEXTO POR DEFECTO
    public FormatRenderer(Format formatter, String defaultText) {
        setFormatter(formatter);
        this.defaultText = defaultText != null ? defaultText : "";
        
        // ✅ MEJORAR APARIENCIA POR DEFECTO
        setHorizontalAlignment(RIGHT); // Alineación derecha para números/fechas
    }
    
    // ✅ SETTER PARA CAMBIAR EL FORMATEADOR DINÁMICAMENTE
    public void setFormatter(Format formatter) {
        if (formatter == null) {
            throw new IllegalArgumentException("Formatter no puede ser nulo");
        }
        this.formatter = formatter;
    }
    
    // ✅ SETTER PARA TEXTO POR DEFECTO
    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText != null ? defaultText : "";
    }

    @Override
    public void setValue(Object value) {
        // Format the Object before setting its value in the renderer
        try {
            if (value != null) {
                value = formatter.format(value);
            } else {
                value = defaultText; // ✅ USAR TEXTO POR DEFECTO PARA VALORES NULOS
            }
        } catch (IllegalArgumentException e) {
            // ✅ MEJOR MANEJO DE ERRORES CON LOGGING
            logger.log(Level.WARNING, "Error formateando valor: " + value, e);
            value = "Formato inválido"; // ✅ TEXTO DE ERROR AMIGABLE
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error inesperado formateando valor: " + value, e);
            value = "Error"; // ✅ TEXTO DE ERROR PARA EXCEPCIONES INESPERADAS
        }

        super.setValue(value);
    }

    /*
     * Use the default date/time formatter for the default locale
     */
    public static FormatRenderer getDateTimeRenderer() {
        return new FormatRenderer(DateFormat.getDateTimeInstance());
    }
    
    public static FormatRenderer getDateTimeRenderer(int dateStyle, int timeStyle) {
        return new FormatRenderer(DateFormat.getDateTimeInstance(dateStyle, timeStyle));
    }
    
    public static FormatRenderer getDateRenderer() {
        return new FormatRenderer(DateFormat.getDateInstance());
    }
    
    public static FormatRenderer getDateRenderer(int style) {
        return new FormatRenderer(DateFormat.getDateInstance(style));
    }

    /*
     * Use the default time formatter for the default locale
     */
    public static FormatRenderer getTimeRenderer() {
        return new FormatRenderer(DateFormat.getTimeInstance());
    }
    
    public static FormatRenderer getTimeRenderer(int style) {
        return new FormatRenderer(DateFormat.getTimeInstance(style));
    }
    
    // ✅ NUEVOS MÉTODOS PARA FORMATEO DE NÚMEROS
    
    public static FormatRenderer getNumberRenderer() {
        return new FormatRenderer(NumberFormat.getNumberInstance());
    }
    
    public static FormatRenderer getIntegerRenderer() {
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(true); // ✅ USAR SEPARADORES DE MILES
        return new FormatRenderer(format);
    }
    
    public static FormatRenderer getCurrencyRenderer() {
        return new FormatRenderer(NumberFormat.getCurrencyInstance());
    }
    
    public static FormatRenderer getCurrencyRenderer(Locale locale) {
        return new FormatRenderer(NumberFormat.getCurrencyInstance(locale));
    }
    
    public static FormatRenderer getPercentRenderer() {
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMinimumFractionDigits(2); // ✅ 2 DECIMALES PARA PORCENTAJES
        return new FormatRenderer(format);
    }
    
    // ✅ MÉTODOS CON TEXTO POR DEFECTO PERSONALIZADO
    
    public static FormatRenderer getDateRendererWithDefault(String defaultText) {
        return new FormatRenderer(DateFormat.getDateInstance(), defaultText);
    }
    
    public static FormatRenderer getNumberRendererWithDefault(String defaultText) {
        return new FormatRenderer(NumberFormat.getNumberInstance(), defaultText);
    }
    
    public static FormatRenderer getCurrencyRendererWithDefault(String defaultText) {
        return new FormatRenderer(NumberFormat.getCurrencyInstance(), defaultText);
    }
    
    // ✅ MÉTODO PARA FORMATEADOR PERSONALIZADO CON CONFIGURACIÓN
    public static FormatRenderer createCustomNumberRenderer(int minFractionDigits, int maxFractionDigits) {
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMinimumFractionDigits(minFractionDigits);
        format.setMaximumFractionDigits(maxFractionDigits);
        return new FormatRenderer(format);
    }
}