import Webapp.ServidorHTTP;

public class Main {
    public static void main(String[] args) {
        ServidorHTTP servidor = new ServidorHTTP();
        servidor.iniciar(8080);
    }
}