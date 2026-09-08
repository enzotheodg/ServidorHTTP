package Webapp;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Resposta {

    private int codigoStatus;
    private String msgStatus;
    private String contentType;
    private int contentLenght;
    private String body;
    private String date;

    public void setStatus(int codigo, String msg){
        this.codigoStatus = codigo;
        this.msgStatus = msg;
    }
    public void setContentType(String contentType){
        this.contentType = contentType;
    }
    public void setContentLenght(int lenght) {
        this.contentLenght = lenght;
    }

    public void setBody(String body){
        this.body = body;
        this.contentLenght = body.getBytes().length;
    }
    public String getDocumentoBruto(){
        return """
                HTTP/1.1 %d %s
                Date: %s
                Content-Type: %s
                Content-Lenght: %d
                
                %s
                """.formatted(codigoStatus, msgStatus, date, contentType, contentLenght, body);
    }
    public Resposta(){
        date = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }




}
