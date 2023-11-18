/**
 * @author elzackarias
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Input extends JTextField{
    private String placeholder;
    public Input(String placeholder, int cols){
        super(cols);
        this.placeholder = placeholder;
        setBorder(new LineBorder(new Color(114, 114, 114), 1));
        setBackground(new Color(18, 18, 18));
        setForeground(new Color(167, 167, 167));
        setText(placeholder);
        addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                if (getText().equals(placeholder)){
                    setBorder(new LineBorder(Color.white, 1));
                    setText("");
                    setForeground(Color.WHITE);
                }
            }
            @Override
            public void focusLost(FocusEvent e){
                if (getText().isEmpty()){
                    setText(placeholder);
                    setBorder(new LineBorder(new Color(114, 114, 114), 1));
                    setForeground(Color.GRAY);
                }
            }
        });

    }
}