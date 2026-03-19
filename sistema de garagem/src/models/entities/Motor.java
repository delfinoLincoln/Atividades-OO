package models.entities;

public class Motor {
    private String tipo;
    private Integer cavalosPotencia;

    public Motor() {
    }

    public Motor(String tipo, Integer cavalosPotencia) {
        this.tipo = tipo;
        this.cavalosPotencia = cavalosPotencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCavalosPotencia() {
        return cavalosPotencia;
    }

    public void setCavalosPotencia(Integer cavalosPotencia) {
        this.cavalosPotencia = cavalosPotencia;
    }
}