package classes;

import javax.swing.AbstractCellEditor;
import javax.swing.table.TableCellEditor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ColorEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private static final Logger logger = Logger.getLogger(ColorEditor.class.getName());
    
    // ✅ CONSTANTES PARA MEJOR MANTENIBILIDAD
    private static final String EDIT = "edit";
    private static final String OK = "ok";
    private static final String CANCEL = "cancel";
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private static final String DIALOG_TITLE = "Seleccionar Color";
    
    private Color currentColor;
    private JButton button;
    private JColorChooser colorChooser;
    private JDialog dialog;
    private Color originalColor; // ✅ GUARDAR COLOR ORIGINAL PARA CANCELAR

    public ColorEditor() {
        initializeComponents();
    }
    
   public void configurarCamposTexto(javax.swing.JTextField campo) {
    // Configurar todos los JTextField
        campo.setOpaque(true);
        campo.setBackground(Color.WHITE);
        campo.setForeground(Color.BLACK);
}
   
    public void configurarCamposPassword(org.edisoncor.gui.passwordField.PasswordField campo) {
    // Configurar todos los JTextField
    
        campo.setOpaque(true);
        campo.setBackground(Color.WHITE);
        campo.setForeground(Color.BLACK);
}
    
    private void initializeComponents() {
        // ✅ INICIALIZAR CON COLOR POR DEFECTO
        currentColor = DEFAULT_COLOR;
        originalColor = DEFAULT_COLOR;
        
        // ✅ CONFIGURAR BOTÓN
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);
        button.setOpaque(true);
        updateButtonAppearance();
        
        // ✅ CONFIGURAR SELECTOR DE COLOR
        colorChooser = new JColorChooser();
        
        // ✅ CREAR DIÁLOGO CON BOTÓN CANCELAR
        createColorDialog();
    }
    
    
    private void createColorDialog() {
        // ✅ CREAR ACTION LISTENER PARA EL DIÁLOGO
        ActionListener okListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor = colorChooser.getColor();
                updateButtonAppearance();
                fireEditingStopped();
            }
        };
        
        ActionListener cancelListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ✅ RESTAURAR COLOR ORIGINAL AL CANCELAR
                currentColor = originalColor;
                updateButtonAppearance();
                fireEditingCanceled();
            }
        };
        
        // ✅ CREAR DIÁLOGO CON AMBOS BOTONES
        dialog = JColorChooser.createDialog(
            button,
            DIALOG_TITLE,
            true, // modal
            colorChooser,
            okListener,
            cancelListener
        );
    }
    
    private void updateButtonAppearance() {
        // ✅ ACTUALIZAR APARIENCIA DEL BOTÓN CON EL COLOR ACTUAL
        if (button != null) {
            button.setBackground(currentColor);
            
            // ✅ CALCULAR COLOR DE TEXTO CONTRASTANTE
            Color textColor = getContrastColor(currentColor);
            button.setForeground(textColor);
            button.setText(getColorHex(currentColor));
            button.setToolTipText("Color: " + getColorHex(currentColor));
        }
    }
    
    private Color getContrastColor(Color color) {
        // ✅ CALCULAR BRILLO PARA DETERMINAR COLOR DE TEXTO
        double brightness = (color.getRed() * 0.299 + color.getGreen() * 0.587 + color.getBlue() * 0.114) / 255;
        return brightness > 0.5 ? Color.BLACK : Color.WHITE;
    }
    
    private String getColorHex(Color color) {
        // ✅ OBTENER REPRESENTACIÓN HEX DEL COLOR
        if (color == null) return "#FFFFFF";
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()).toUpperCase();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
            try {
                // ✅ GUARDAR COLOR ORIGINAL ANTES DE EDITAR
                originalColor = currentColor;
                
                // ✅ CONFIGURAR SELECTOR CON COLOR ACTUAL
                colorChooser.setColor(currentColor);
                
                // ✅ MOSTRAR DIÁLOGO
                dialog.setLocationRelativeTo(button);
                dialog.setVisible(true);
                
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Error mostrando selector de color", ex);
                JOptionPane.showMessageDialog(button, 
                    "Error al abrir selector de color", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    @Override
    public Object getCellEditorValue() {
        // ✅ RETORNAR COLOR ACTUAL CON VALIDACIÓN
        return currentColor != null ? currentColor : DEFAULT_COLOR;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        try {
            // ✅ MANEJO SEGURO DE VALORES NULOS O INVÁLIDOS
            if (value instanceof Color) {
                currentColor = (Color) value;
            } else if (value != null) {
                logger.warning("Valor no es instancia de Color: " + value.getClass().getName());
                currentColor = DEFAULT_COLOR;
            } else {
                currentColor = DEFAULT_COLOR;
            }
            
            // ✅ GUARDAR COLOR ORIGINAL
            originalColor = currentColor;
            
            // ✅ ACTUALIZAR APARIENCIA DEL BOTÓN
            updateButtonAppearance();
            
            // ✅ APLICAR SELECCIÓN VISUAL SI ES NECESARIO
            if (isSelected) {
                button.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            } else {
                button.setBorder(BorderFactory.createEmptyBorder());
            }
            
            return button;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en getTableCellEditorComponent", e);
            currentColor = DEFAULT_COLOR;
            updateButtonAppearance();
            return button;
        }
    }
    
    // ✅ MÉTODO PARA LIMPIAR RECURSOS
    public void dispose() {
        if (dialog != null) {
            dialog.dispose();
            dialog = null;
        }
        if (button != null) {
            for (ActionListener al : button.getActionListeners()) {
                button.removeActionListener(al);
            }
            button = null;
        }
        colorChooser = null;
    }
    
    // ✅ DESTRUCTOR PARA LIMPIAR RECURSOS
    @Override
    protected void finalize() throws Throwable {
        try {
            dispose();
        } finally {
            super.finalize();
        }
    }
}