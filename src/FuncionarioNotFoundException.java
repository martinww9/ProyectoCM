

public class FuncionarioNotFoundException extends Exception {
    public FuncionarioNotFoundException(String message) {
        super("Funcionario no encontado");
    }
}