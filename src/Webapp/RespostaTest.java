package Webapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RespostaTest {

    @Test
    void getDocumentoBruto() {
        Resposta res = new Resposta();
        res.setStatus(200,"OK");
        res.setContentLenght(28);
        res.setContentType("text/html");
        res.setBody("<html><body>0i</body></html>");


        String bruto = """
                HTTP/1.1 200 OK
                Content-Type: text/html
                Content-Lenght: 28
                
                <html><body>0i</body></html>
                """;

        assertEquals(bruto, res.getDocumentoBruto());
    }
}