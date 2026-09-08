package Webapp;

public class Requisicao {
    private String documento;
    private String url;
    private String metodo;

    public Requisicao(String documento) {
        this.documento = documento;

        String[] linhas = documento.split("\n");
        String primeiraLinha = linhas[0];
        linhas =  primeiraLinha.split(" ");
        metodo = linhas[0];
        url = linhas[1];
    }

    public String getMetodo() {
        return metodo;
    }

    public String getURL() {
        return url;
    }

    public String getDocumentoBruto() { return documento;
    }
}
