package models.entities;

import java.util.ArrayList;
import java.util.List;

public class Condutor {
    private String nome;
    private String cnh;

    private List<Carro> carros = new ArrayList<>();

    public Condutor(String nome, String cnh) {
        this.nome = nome;
        this.cnh = cnh;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    
}
