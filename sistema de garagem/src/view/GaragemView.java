package view;

import models.entities.Carro;
import models.entities.Motor;
import models.entities.Condutor;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class GaragemView {
    private Scanner sc;

    public GaragemView() {
        sc = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return sc;
    }

    public int mostrarMenuPrincipal() {
        System.out.println("=== SISTEMA DE GARAGEM ===");
        System.out.println("(1) - Criar novo carro");
        System.out.println("(2) - Listar carros");
        System.out.println("(3) - Selecionar carro");
        System.out.println("(4) - Gerenciar motores");
        System.out.println("(5) - Listar motores disponíveis");
        System.out.println("(0) - Sair");
        System.out.println();
        System.out.print("Escolha: ");
        return sc.nextInt();
    }

    public int mostrarMenuCarro(Carro carro) {
        System.out.println("=== CARRO SELECIONADO: " + carro.getMarca() + " " + carro.getModelo() + " ===");
        System.out.println("(1) - Exibir dados completos");
        System.out.println("(2) - Trocar condutor");
        System.out.println("(3) - Trocar motor");
        System.out.println("(4) - Voltar");
        System.out.print("Escolha: ");
        return sc.nextInt();
    }

    public Motor criarMotor() {
        System.out.println("--- CADASTRO DE MOTOR ---");
        System.out.print("Tipo (gasolina/diesel/eletrico): ");
        String tipo = sc.next();
        System.out.print("Potencia (cv): ");
        int potencia = sc.nextInt();

        return new Motor(tipo, potencia);
    }

    public Condutor criarCondutor() {
        System.out.println(" --- CADASTRO DE CONDUTOR ---");
        System.out.print("Nome: ");
        sc.nextLine();
        String nome = sc.nextLine();
        System.out.print("Numero da CNH: ");
        String cnh = sc.next();

        return new Condutor(nome, cnh);
    }

    public Carro criarCarro(Set<Motor> motoresDisponiveis) {
        System.out.println("--- CADASTRO DE CARRO ---");
        System.out.print("Marca: ");
        String marca = sc.next();
        System.out.print("Modelo: ");
        String modelo = sc.next();
        System.out.print("Placa: ");
        String placa = sc.next();

        Motor motor = null;
        if (!motoresDisponiveis.isEmpty()) {
            System.out.print("Usar motor existente? (s/n): ");
            String usarExistente = sc.next();

            if (usarExistente.equalsIgnoreCase("s")) {
                List<Motor> listaMotores = new ArrayList<>(motoresDisponiveis);
                System.out.println("Motores disponíveis:");
                for (int i = 0; i < listaMotores.size(); i++) {
                    Motor m = listaMotores.get(i);
                    System.out.println((i + 1) + " - " + m.getTipo() + " (" + m.getCavalosPotencia() + " cv)");
                }
                System.out.print("Escolha: ");
                int escolha = sc.nextInt();
                if (escolha > 0 && escolha <= listaMotores.size()) {
                    motor = listaMotores.get(escolha - 1);
                }
            }
        }

        if (motor == null) {
            System.out.print("Deseja cadastrar novo motor? (s/n): ");
            String temMotor = sc.next();
            if (temMotor.equalsIgnoreCase("s")) {
                motor = criarMotor();
                motoresDisponiveis.add(motor);
            }
        }

        System.out.print("Deseja cadastrar condutor? (s/n): ");
        String temCondutor = sc.next();

        Condutor condutor = null;
        if (temCondutor.equalsIgnoreCase("s")) {
            condutor = criarCondutor();
        }

        if (motor != null && condutor != null) {
            return new Carro(marca, modelo, placa, motor, condutor);
        } else if (motor != null) {
            return new Carro(marca, modelo, placa, motor);
        } else {
            return new Carro(marca, modelo, placa);
        }
    }

    public void exibirDadosCarro(Carro carro) {
        System.out.println(" === DADOS DO CARRO ===");
        System.out.println("Marca: " + carro.getMarca());
        System.out.println("Modelo: " + carro.getModelo());
        System.out.println("Placa: " + carro.getPlaca());

        if (carro.getMotor() != null) {
            System.out.println("--- MOTOR ---");
            System.out.println("Tipo: " + carro.getMotor().getTipo());
            System.out.println("Potencia: " + carro.getMotor().getCavalosPotencia() + " cv");
        } else {
            System.out.println("Motor: Nao cadastrado");
        }

        if (carro.getCondutor() != null) {
            System.out.println("--- CONDUTOR ---");
            System.out.println("Nome: " + carro.getCondutor().getNome());
            System.out.println("CNH: " + carro.getCondutor().getCnh());
        } else {
            System.out.println("Condutor: Não cadastrado");
        }
    }
}