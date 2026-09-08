package Webapp;

import java.util.HashMap;
import java.util.Map;

public class Receptor extends Pagina {
    private Requisicao req;

    public Receptor(Requisicao req) {
        this.req = req;
    }

    protected Map<String, String> getParametrosQuery() {
        Map<String, String> parametros = new HashMap<>();
        String url = req.getURL();
        
        if (url.contains("?")) {
            String queryString = url.substring(url.indexOf("?") + 1);
            String[] pares = queryString.split("&");
            
            for (String par : pares) {
                String[] chaveValor = par.split("=");
                if (chaveValor.length == 2) {
                    parametros.put(chaveValor[0], chaveValor[1]);
                }
            }
        }
        return parametros;
    }
    @Override
    public String getHtml() {
        Map<String, String> params =req.getMetodo().equals("GET")? getParametrosQuery(): getParametrosPOST();
        StringBuilder html = new StringBuilder();
        
        html.append("<html><head><meta charset=\"utf-8\"></head><body>");
        html.append("<h1>Parâmetros Recebidos </h1><ul>");
        
        for (Map.Entry<String, String> entry : params.entrySet()) {
            html.append("<li><b>").append(entry.getKey()).append(":</b> ")
                .append(entry.getValue()).append("</li>");
        }
        
        return html.append("</ul></body></html>").toString();
    }
    protected Map<String, String> getParametrosPOST() {
        Map<String, String> parametros = new HashMap<>();

        String documento = req.getDocumentoBruto();
        String[] partes = documento.split("\r\n\r\n");

        if (partes.length > 1 && !partes[1].isBlank()) {
            String corpo = partes[1];
            String[] pares = corpo.split("&");
            for (String par : pares) {
                String[] chaveValor = par.split("=");
                if (chaveValor.length == 2) {
                    parametros.put(chaveValor[0], chaveValor[1]);
                }
            }
        }
        return parametros;
    }
}
