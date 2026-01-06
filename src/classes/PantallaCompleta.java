package classes;

/**
 *
 * @author angel
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

/**
 * Clase utilitaria para manejar ventanas en pantalla completa
 * con adaptación automática a diferentes resoluciones
 */
public class PantallaCompleta {
    
    private JFrame frame;
    private JPanel panelOriginal;
    private boolean esPantallaCompleta;
    
    public PantallaCompleta(JFrame frame) {
        this.frame = frame;
        this.panelOriginal = (JPanel) frame.getContentPane();
        this.esPantallaCompleta = false;
    }
    
    /**
     * Activa la pantalla completa
     */
    public void activarPantallaCompleta() {
        if (esPantallaCompleta) return;
        
        // Guardar el estado original
        guardarEstadoOriginal();
        
        // Configurar pantalla completa
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Configurar layout responsivo
        configurarLayoutResponsivo();
        
        esPantallaCompleta = true;
    }
    
    /**
     * Desactiva la pantalla completa
     */
    public void desactivarPantallaCompleta() {
        if (!esPantallaCompleta) return;
        
        frame.setUndecorated(false);
        frame.setExtendedState(JFrame.NORMAL);
        frame.setContentPane(panelOriginal);
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        esPantallaCompleta = false;
    }
    
    /**
     * Alterna entre pantalla completa y normal
     */
    public void togglePantallaCompleta() {
        if (esPantallaCompleta) {
            desactivarPantallaCompleta();
        } else {
            activarPantallaCompleta();
        }
    }
    
    /**
     * Configura el layout para ser responsivo
     */
    private void configurarLayoutResponsivo() {
        JPanel panelResponsivo = new JPanel(new BorderLayout());
        panelResponsivo.setBackground(Color.DARK_GRAY);
        
        // Panel central que se expande pero mantiene proporciones
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setOpaque(false);
        
        // Contenedor para los componentes originales
        JPanel contenedorComponentes = new JPanel();
        contenedorComponentes.setLayout(new BoxLayout(contenedorComponentes, BoxLayout.Y_AXIS));
        contenedorComponentes.setOpaque(false);
        
        // Mover componentes al contenedor
        Component[] componentes = panelOriginal.getComponents();
        for (Component comp : componentes) {
            contenedorComponentes.add(comp);
        }
        
        panelCentral.add(contenedorComponentes);
        panelResponsivo.add(panelCentral, BorderLayout.CENTER);
        
        frame.setContentPane(panelResponsivo);
        
        // Listener para redimensionamiento
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarComponentesAlTamaño();
            }
        });
    }
    
    /**
     * Ajusta los componentes según el tamaño actual
     */
    private void ajustarComponentesAlTamaño() {
        Dimension screenSize = frame.getSize();
        
        // Aquí puedes agregar lógica específica para ajustar tus componentes
        // según el tamaño de la pantalla
        if (screenSize.width < 1024) {
            // Configuración para pantallas pequeñas
            ajustarParaPantallasPequenas();
        } else {
            // Configuración para pantallas grandes
            ajustarParaPantallasGrandes();
        }
    }
    
    private void ajustarParaPantallasPequenas() {
        // Ejemplo: reducir fuentes y tamaños
        ajustarTamanioFuentes(0.8f);
    }
    
    private void ajustarParaPantallasGrandes() {
        // Ejemplo: aumentar fuentes y tamaños
        ajustarTamanioFuentes(1.2f);
    }
    
    private void ajustarTamanioFuentes(float factor) {
        // Recorrer todos los componentes y ajustar fuentes
        Component[] components = getAllComponents(frame.getContentPane());
        for (Component comp : components) {
            if (comp instanceof JComponent) {
                Font font = comp.getFont();
                if (font != null) {
                    Font nuevaFont = font.deriveFont(font.getSize() * factor);
                    comp.setFont(nuevaFont);
                }
            }
        }
    }
    
    private Component[] getAllComponents(Container container) {
        Component[] components = container.getComponents();
        for (Component comp : components) {
            if (comp instanceof Container) {
                Component[] subComponents = getAllComponents((Container) comp);
                Component[] newArray = new Component[components.length + subComponents.length];
                System.arraycopy(components, 0, newArray, 0, components.length);
                System.arraycopy(subComponents, 0, newArray, components.length, subComponents.length);
                components = newArray;
            }
        }
        return components;
    }
    
    private void guardarEstadoOriginal() {
        // Puedes expandir esto para guardar más estados si es necesario
    }
    
    /**
     * Verifica si está en pantalla completa
     */
    public boolean isPantallaCompleta() {
        return esPantallaCompleta;
    }
    
    /**
     * Configura un atajo de teclado para alternar pantalla completa (F11)
     */
    public void configurarAtajoTeclado() {
        KeyStroke f11KeyStroke = KeyStroke.getKeyStroke("F11");
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = frame.getRootPane().getActionMap();
        
        inputMap.put(f11KeyStroke, "togglePantallaCompleta");
        actionMap.put("togglePantallaCompleta", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePantallaCompleta();
            }
        });
    }
}