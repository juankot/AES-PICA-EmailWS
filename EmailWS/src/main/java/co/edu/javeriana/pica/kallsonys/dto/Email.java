package co.edu.javeriana.pica.kallsonys.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class Email {

    @NotBlank(message = "El/los destinatarios son obligatorios")
    @NotNull(message = "El/los destinatarios son obligatorios")
    private String to;

    private String cc;

    private String cco;

    @NotBlank(message = "El asunto es obligatorio")
    @NotNull(message = "El asunto es obligatorio")
    private String about;

    @NotBlank(message = "El cuerpo del mensaje es obligatorio")
    @NotNull(message = "El cuerpo del mensaje es obligatorio")
    private String body;

    private boolean isHTML;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCco() {
        return cco;
    }

    public void setCco(String cco) {
        this.cco = cco;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isHTML() {
        return isHTML;
    }

    public void setHTML(boolean HTML) {
        isHTML = HTML;
    }
}