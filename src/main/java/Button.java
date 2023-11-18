import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Button extends JButton {

    public Button(String texto,boolean type) {
        super(texto);
        init(type);
    }

    private void init(boolean type) {
        // Establecer el color inicial del botón
        Color bgColor = (type) ? new Color(30, 215, 96) : new Color(255, 255, 255);
        Color txtBgColor = (type) ? new Color(255, 255, 255) : new Color(97, 100, 103);

        Color hoverColor = (type) ? new Color(31, 223, 100) : new Color(97, 100, 103);
        Color pressColor = (type) ? new Color(22, 156, 70) : new Color(0,0,0);
        setBackground(bgColor);
        setForeground(txtBgColor);
        setFont(new Font("Arial", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Establecer el UI (Interfaz de Usuario) personalizado para el botón
        setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(c.getBackground());
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
                super.paint(g, c);
            }
        });

        // Listener para cambiar el color cuando se pasa el ratón por encima
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(hoverColor); // Cambiar el color cuando se pasa el ratón por encima
                if(!type) {
                    setForeground(new Color(255,255,255));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(bgColor); // Restaurar el color cuando se sale el ratón
                setForeground(txtBgColor);
            }
        });

        // Listener para cambiar el color cuando se presiona el botón
        addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                ButtonModel model = getModel();
                if (model.isPressed()) {
                    setBackground(pressColor); // Cambiar el color cuando se presiona el botón
                    if(!type) {
                        setForeground(new Color(255,255,255));
                    }
                } else {
                    setBackground(bgColor); // Restaurar el color cuando se suelta el botón
                    setForeground(txtBgColor);
                }
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Expansión horizontal completa
        setPreferredSize(new Dimension(245, getPreferredSize().height)); // Tamaño inicial del botón

        setMargin(new Insets(5, 5, 5, 5)); // Márgenes internos
    }

    // Otros métodos personalizados si son necesarios...
}
