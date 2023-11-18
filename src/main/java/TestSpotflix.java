/**
 * @author elzackarias
 */
import java.io.*;
public class TestSpotflix {
    public static void main(String[] args) {
        String path = obtenerRutaArchivo();
        SpotiflixControlador controlador = inicializarControlador(path);
        SpotiflixVista vista = new SpotiflixVista(controlador);
        configurarControladorVista(controlador, vista);
        mostrarVista(vista);
    }
    private static String obtenerRutaArchivo() {
        return System.getProperty("user.dir") + File.separator + "data.dat";
    }
    private static SpotiflixControlador inicializarControlador(String path) {
        SpotiflixModelo modelo = new SpotiflixModelo();
        SpotiflixControlador controlador = new SpotiflixControlador();
        controlador.setModelo(modelo);
        try {
            File archivoFile = new File(path);
            if (!archivoFile.exists()) {
                archivoFile.createNewFile();
            }
            SpotiflixArchivo file = new SpotiflixArchivo();
            controlador.setArchivo(file);
        } catch (IOException e) {
            SpotiflixVista vista = new SpotiflixVista(controlador);
            vista.error("Ops!! Algo salió mal con el archivo");
        }
        return controlador;
    }
    private static void configurarControladorVista(SpotiflixControlador controlador, SpotiflixVista vista) {
        controlador.setVista(vista);
    }
    private static void mostrarVista(SpotiflixVista vista) {
        vista.setVisible(true);
    }
}
