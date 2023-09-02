package proyectocm;

public class Funcionario {
    private String nombre;
    private String cargo;
    private String id;

    public Funcionario(String nombre, String cargo, String id) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }
    
}