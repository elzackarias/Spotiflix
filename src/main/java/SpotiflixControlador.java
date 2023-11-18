/**
 * @author elzackarias
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class SpotiflixControlador implements ActionListener{
    private SpotiflixVista vista;
    private SpotiflixModelo modelo;
    private SpotiflixArchivo archivo;

    public void setVista(SpotiflixVista vista) {
        this.vista = vista;
    }
    public void setModelo(SpotiflixModelo modelo) {
        this.modelo = modelo;
    }
    public void setArchivo(SpotiflixArchivo archivo) {
        this.archivo = archivo;
    }     
      
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource() == vista.btnAgregar){
                    if(!vista.rbtnCancion.isSelected() && !vista.rbtnPelicula.isSelected()){
                        throw new EmptyInputException("Selecciona el tipo de elemento que deseas agregar");
                    }
                modelo.setTipo(vista.rbtnCancion.isSelected() ? vista.rbtnCancion.getText() : vista.rbtnPelicula.getText());
                modelo.setNombre(vista.txtNombre.getText());
                modelo.setCreador(vista.txtCreador.getText());
                modelo.setDuracion(Float.parseFloat(vista.txtDuracion.getText()));

                archivo.escribirEnArchivo(modelo.getTipo(), modelo.getNombre(), modelo.getCreador(), modelo.getDuracion());
                vista.successAdd();
                
            }else{
                vista.visualizarPlaylist(archivo);
            }
        }catch(NumberFormatException err){
            vista.error("Solo se aceptan numeros en el campo Duracion");
        }catch(IOException err){
            vista.error("No se ha encontrado el archivo de la playlist para almacenar el elemento");
        }catch(EmptyInputException | EmptyPlaylistException err){
            vista.error(err.getMessage());
        }
    }

}