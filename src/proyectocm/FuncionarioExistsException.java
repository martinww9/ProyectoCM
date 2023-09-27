package proyectocm;

public class FuncionarioExistsException extends Exception {
    public FuncionarioExistsException(String message) {
        super("Funcionario ya existe");
    }
}