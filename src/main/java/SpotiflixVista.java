/**
 * @author elzackarias
 */
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.*;
public class SpotiflixVista extends JFrame{
    private JLabel lbTipo, lbLogo;
    protected JRadioButton rbtnCancion, rbtnPelicula;
    protected JTextField txtNombre, txtCreador, txtDuracion;
    protected JButton btnAgregar, btnVisualizar;
    private SpotiflixControlador controlador;
    public SpotiflixVista(SpotiflixControlador controlador){
        super("Spotiflix");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(500, 310);
        this.controlador = controlador;
        initComponents();
    }
    private void initComponents() {
        //Radiobuttons para escoger el tipo de elemento a agregar
        lbTipo = new JLabel("Tipo:");
        lbTipo.setFont(new Font("Arial", Font.BOLD, 14)); //Letra por default
        lbTipo.setForeground(Color.WHITE);
        lbLogo = new JLabel("    \uD83C\uDFBE Spotiflix");
        lbLogo.setFont(new Font("Arial", Font.BOLD, 20)); //Letra por default
        lbLogo.setForeground(new Color(30, 215, 96)); //Color por default
        lbLogo.setOpaque(true); //Se pone en true para que se pueda cambiar el color de fondo
        lbLogo.setBackground(Color.WHITE); //Color por default
        lbLogo.setBorder(BorderFactory.createLineBorder(Color.white, 4)); //Color por default
        rbtnCancion = new JRadioButton("Canción");
        rbtnCancion.setForeground(Color.WHITE);
        rbtnCancion.setFont(new Font("Arial", Font.PLAIN, 14));
        rbtnPelicula = new JRadioButton("Película");
        rbtnPelicula.setForeground(Color.WHITE);
        rbtnPelicula.setFont(new Font("Arial", Font.PLAIN, 14));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbtnCancion);
        buttonGroup.add(rbtnPelicula);
        //Formulario para agregar un elemento
        txtNombre = new Input(" Ingrese nombre", 20);
        txtNombre.setFont(new Font("Arial", Font.BOLD, 14));
        txtCreador = new Input(" Ingrese nombre del creador", 20);
        txtCreador.setFont(new Font("Arial", Font.BOLD, 14));
        txtDuracion = new Input(" Duración", 20);
        txtDuracion.setFont(new Font("Arial", Font.BOLD, 14));
        btnAgregar = new Button("Agregar", true);
        btnAgregar.addActionListener(controlador); //Se le asigna un oyente (el controlador)
        btnVisualizar = new Button("Ver Playlist", false);
        btnVisualizar.addActionListener(controlador); //Se le asigna un oyente (el controlador)
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes entre los componentes
        gbc.gridx = 0;
        gbc.gridy = 0;
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.white, 4)); //Color por default
        topPanel.add(lbTipo,BorderLayout.NORTH);
        topPanel.add(rbtnCancion, BorderLayout.WEST);
        topPanel.add(rbtnPelicula,BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 90));
        add(lbLogo, BorderLayout.PAGE_START);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.add(topPanel, gbc);
        gbc.gridy++;
        mainPanel.add(txtNombre, gbc);
        gbc.gridy++;
        mainPanel.add(txtCreador, gbc);
        gbc.gridy++;
        mainPanel.add(txtDuracion, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(btnAgregar, gbc);
        gbc.gridy++;
        mainPanel.add(btnVisualizar, gbc);
        setBackground(Color.BLACK);
        this.add(mainPanel);
    }
    public void successAdd(){
        JOptionPane.showMessageDialog(null, "Se agregó el elemento correctamente!!", "Yeii", JOptionPane.INFORMATION_MESSAGE);
    }
    public void error(String message){
        JOptionPane.showMessageDialog(null, message, "Opsss!", JOptionPane.ERROR_MESSAGE);
    }
    public void visualizarPlaylist(SpotiflixArchivo archivo) throws EmptyPlaylistException, IOException {
        JDialog dialog = new JDialog();
        dialog.setResizable(false);
        dialog.setTitle("Tu Playlist \uD83C\uDFAC\uD83C\uDFA7");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(this);
        String[] header = {"ID", "Tipo", "Título", "Creador", "Duración"};
        ArrayList<String> lineas = archivo.leerArchivo();
        if(lineas.isEmpty()){
            throw new EmptyPlaylistException();
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(header, 0);
        for (String linea : lineas) {
            String[] campos = linea.split("&&");
            modeloTabla.addRow(campos);
        }
        JTable tabla = new JTable(modeloTabla);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(60);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14)); //Letra por default
        JScrollPane scrollPane = new JScrollPane(tabla);
        dialog.add(scrollPane);
        dialog.pack();
        dialog.setVisible(true);
    }
}