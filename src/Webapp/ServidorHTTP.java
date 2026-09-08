package Webapp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorHTTP {
    /**
     * retorna um objeto Requisicao a partir de um fluxo de entrada.
     * @throws IOException caso ocorra algum erro ao ler o fluxo.
     */
    protected Requisicao lerRequisicao(InputStream in) throws IOException {
        BufferedReader leitorLinhas = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String linha;
        int contentLength = 0;


        while ((linha = leitorLinhas.readLine()) != null && !linha.isBlank()) {
            sb.append(linha).append("\r\n");


            if (linha.toLowerCase().startsWith("content-length:")) {
                contentLength = Integer.parseInt(linha.split(":")[1].trim());
            }
        }
        sb.append("\r\n");

        
        if (contentLength > 0) {
            char[] corpo = new char[contentLength];
            leitorLinhas.read(corpo, 0, contentLength);
            sb.append(corpo);
        }

        return new Requisicao(sb.toString());
    }

    protected Pagina getPagina(Requisicao req) {
        String urlBase = req.getURL().split("\\?")[0]; 
    
        if (urlBase.equals("/receptor")) {
        return new Receptor(req);
        }
    
        Pagina pag = new PaginaDoArquivo(urlBase);
        return pag.getHtml() != null ? pag : null;
    }
    protected Resposta criarResposta(Pagina pagina) {
        Resposta res = new Resposta();
        if (pagina == null) {
            res.setStatus(404, "Not Found");
            return res;
        }
        res.setStatus(200, "OK");
        res.setBody(pagina.getHtml());
        return res;
    }
    protected void enviarResposta(Resposta res, OutputStream out) throws
            IOException {
        PrintWriter writer = new PrintWriter(out, true);
        writer.print(res.getDocumentoBruto());
        writer.flush();

    }

    public void iniciar(int porta) {
        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Servidor ouvindo na porta " + porta);
            System.out.printf("Acesse esta aplicação pelo endereço %s:%d\n",
                    "http://localhost", porta);
            System.out.println("Para parar o servidor, aperte Ctrl-C.");
            while (true) {

                try (Socket socket = serverSocket.accept();
                     InputStream in = socket.getInputStream();
                     OutputStream out = socket.getOutputStream()) {
                    Requisicao req = lerRequisicao(in);
                    Pagina pag = getPagina(req);
                    Resposta res = criarResposta(pag);
                    enviarResposta(res, out);
                } // fim try
            } // fim while
        } catch (IOException e) { // fim try do server socket
            System.err.println("Ocorreu um erro: " + e.getMessage());
        }
    }




}
