/**
 * @author elzackarias
 */
import java.io.*;
import java.util.ArrayList;
public class SpotiflixArchivo {
    String ruta = System.getProperty("user.dir") + File.separator + "data.dat";
    public ArrayList<String> leerArchivo() throws IOException{
        ArrayList<String> lineas = new ArrayList<>();
        String linea;
        try (BufferedReader leer = new BufferedReader(new FileReader(ruta))) {
            while ((linea = leer.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }
    public void escribirEnArchivo(String tipo, String nombre, String creador, float duracion) throws IOException{
        int index;
        index = index();
        String elemento = index + "&&" + tipo +  "&&"  + nombre + "&&" + creador + "&&" + duracion+ "\n";
        try (BufferedWriter escribir = new BufferedWriter(new FileWriter(ruta, true))) {
            escribir.write(elemento);
        }
    }
    private int index() throws IOException{
        ArrayList<String> lineas = leerArchivo();
        String c;
        String[] cadena;
        int i;
        if(lineas.isEmpty()){
            return 1;
        }
        c = lineas.get(lineas.size()-1 );
        cadena = c.split("&&");
        i = Integer.parseInt(cadena[0]);
        return ++i;  
    }
}