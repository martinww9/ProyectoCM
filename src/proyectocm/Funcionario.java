package proyectocm;
//test ricardo
public class Funcionario {
    private String nombre;
    private String cargo;
    private String ID;

    public Funcionario(String nombre, String cargo, String ID) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.ID = ID;
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
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
}