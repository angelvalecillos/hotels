package classes;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ColorRenderer extends JLabel implements TableCellRenderer {
    private static final Logger logger = Logger.getLogger(ColorRenderer.class.getName());
    
    // ✅ CONSTANTES PARA CONFIGURACIÓN
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static final Color ERROR_COLOR = Color.LIGHT_GRAY;
    private static final int BORDER_TOP = 2;
    private static final int BORDER_LEFT = 5;
    private static final int BORDER_BOTTOM = 2;
    private static final int BORDER_RIGHT = 5;
    
    // ✅ BORDERS COMO CONSTANTES (no se recrean)
    private final Border selectedBorder;
    private final Border unselectedBorder;
    private final boolean isBordered;

    public ColorRenderer(boolean isBordered) {
        this.isBordered = isBordered;
        
        // ✅ INICIALIZAR BORDERS UNA SOLA VEZ
        this.selectedBorder = BorderFactory.createMatteBorder(
            BORDER_TOP, BORDER_LEFT, BORDER_BOTTOM, BORDER_RIGHT, 
            Color.BLUE // Color fijo para selección
        );
        
        this.unselectedBorder = BorderFactory.createMatteBorder(
            BORDER_TOP, BORDER_LEFT, BORDER_BOTTOM, BORDER_RIGHT, 
            Color.GRAY // Color fijo para no seleccionado
        );
        
        setOpaque(true); // MUST do this for background to show up.
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object color,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        
        try {
            Color newColor = safelyConvertToColor(color);
            setBackground(newColor);
            
            if (isBordered) {
                setBorder(isSelected ? selectedBorder : unselectedBorder);
            } else {
                setBorder(null); // ✅ LIMPIAR BORDER SI NO ES BORDEADO
            }
            
            // ✅ TOOLTIP MEJORADO CON FORMATO HEX Y RGB
            setToolTipText(createColorTooltip(newColor));
            
            // ✅ TEXTO OPCIONAL CON CONTRASTE (puede ser útil para debugging)
            setText(""); // Limpiar texto por defecto
            
            return this;
            
        } catch (Exception e) {
            logger.log(Level.WARNING, 
                "Error en renderizado de color en fila " + row + ", columna " + column + ": " + e.getMessage(), 
                e);
            
            // ✅ COMPORTAMIENTO SEGURO EN CASO DE ERROR
            setBackground(ERROR_COLOR);
            setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            setToolTipText("Error: Valor de color inválido");
            setText("!");
            
            return this;
        }
    }
    
    /**
     * ✅ CONVERTIR SEGURAMENTE EL OBJETO A COLOR
     */
    private Color safelyConvertToColor(Object colorObj) {
        if (colorObj instanceof Color) {
            return (Color) colorObj;
        }
        
        if (colorObj == null) {
            return DEFAULT_COLOR;
        }
        
        // ✅ INTENTAR CONVERTIR DESDE STRING (ej: "#FF0000" o "255,0,0")
        if (colorObj instanceof String) {
            try {
                return parseColorFromString((String) colorObj);
            } catch (Exception e) {
                logger.warning("No se pudo convertir string a color: " + colorObj);
            }
        }
        
        logger.warning("Tipo de color no soportado: " + colorObj.getClass().getName());
        return DEFAULT_COLOR;
    }
    
    /**
     * ✅ PARSER SIMPLE PARA STRINGS DE COLOR
     */
    private Color parseColorFromString(String colorStr) {
        if (colorStr == null || colorStr.trim().isEmpty()) {
            return DEFAULT_COLOR;
        }
        
        colorStr = colorStr.trim();
        
        // Formato hexadecimal: #RRGGBB o #RGB
        if (colorStr.startsWith("#")) {
            try {
                return Color.decode(colorStr);
            } catch (NumberFormatException e) {
                // Intentar formato corto #RGB
                if (colorStr.length() == 4) {
                    String fullHex = "#" + colorStr.charAt(1) + colorStr.charAt(1) +
                                    colorStr.charAt(2) + colorStr.charAt(2) +
                                    colorStr.charAt(3) + colorStr.charAt(3);
                    return Color.decode(fullHex);
                }
                throw e;
            }
        }
        
        // Formato RGB: "255,0,0"
        if (colorStr.contains(",")) {
            String[] parts = colorStr.split(",");
            if (parts.length == 3) {
                int r = Integer.parseInt(parts[0].trim());
                int g = Integer.parseInt(parts[1].trim());
                int b = Integer.parseInt(parts[2].trim());
                return new Color(r, g, b);
            }
        }
        
        throw new IllegalArgumentException("Formato de color no reconocido: " + colorStr);
    }
    
    /**
     * ✅ CREAR TOOLTIP INFORMATIVO
     */
    private String createColorTooltip(Color color) {
        if (color == null) {
            return "Color: No definido";
        }
        
        String hex = String.format("#%02X%02X%02X", 
            color.getRed(), color.getGreen(), color.getBlue());
        
        return String.format(
            "Color: %s | RGB: (%d, %d, %d) | Alpha: %d",
            hex, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()
        );
    }
    
    // ✅ MÉTODO PARA CONFIGURACIÓN RÁPIDA
    public static ColorRenderer createDefaultRenderer() {
        return new ColorRenderer(true);
    }
    
    public static ColorRenderer createBorderlessRenderer() {
        return new ColorRenderer(false);
    }
}