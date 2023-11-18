/**
 * @author elzackarias
 */
public class SpotiflixModelo {
    private String tipo = "N/A";
    private String nombre = "N/A";
    private String creador = "N/A";
    private float duracion = 0;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) throws EmptyInputException {
        checkEmptyInput(nombre, "Nombre");
        this.nombre = nombre;
    }
    public String getCreador() {
        return creador;
    }
    public void setCreador(String creador) throws EmptyInputException {
        checkEmptyInput(creador, "Creador");
        this.creador = creador;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public float getDuracion() {
        return duracion;
    }
    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }
    private void checkEmptyInput(String input, String fieldName) throws EmptyInputException {
        if (input.equals(fieldName)) {
            throw new EmptyInputException("Ingresa información en el campo " + fieldName);
        }
    }
}
