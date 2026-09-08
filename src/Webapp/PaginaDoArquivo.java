package Webapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PaginaDoArquivo extends Pagina {
    private String url;

    public String getHtml(){
        if (this.url == null || this.url.isBlank()){
            return null;
        }
        try{
            Path caminho = Path.of(this.url);
            return Files.readString(caminho);
        } catch (IOException e) {
            return null;
        }
    }

    public PaginaDoArquivo(String url){
       this.url = "C:\\progWeb_1\\AplicacaoWebArtesanal\\src\\Webapp"+ (url.equals("/") ? "/index.html" : url);
    }
}
