package models.entities;

public class Carro {
    private String marca;
    private String modelo;
    private String placa;

    private Motor motor;

    private Condutor condutor;

    public Carro() {
    }

    public Carro(String marca, String modelo, String placa, Motor motor, Condutor condutor) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.motor = motor;
        this.condutor = condutor;
    }

    public Carro(String marca, String modelo, String placa, Motor motor) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.motor = motor;
    }

    public Carro(String marca, String modelo, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }
}