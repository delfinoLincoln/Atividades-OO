package controller;

import models.entities.Carro;
import models.entities.Condutor;
import models.entities.Motor;
import view.GaragemView;
import view.util.ClearScreen;
import view.util.Spause;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import controller.exceptions.GaragemException;

public class GaragemController {
    private GaragemView view;
    private List<Carro> carros;
    private Set<Motor> motoresDisponiveis;
    private Carro carroSelecionado;

    public GaragemController() {
        this.view = new GaragemView();
        this.carros = new ArrayList<>();
        this.motoresDisponiveis = new HashSet<>();
    }

    public void iniciar() {
        int opcao = -1;

        do {
            try {
                opcao = view.mostrarMenuPrincipal();

                switch (opcao) {
                    case 1:
                        ClearScreen.cls();
                        criarCarro();
                        Spause.pausa();
                        ClearScreen.cls();
                        break;

                    case 2:
                        ClearScreen.cls();
                        listarCarros();
                        Spause.pausa();
                        ClearScreen.cls();
                        break;

                    case 3:
                        ClearScreen.cls();
                        selecionarCarro();
                        if (carroSelecionado != null) {
                            menuCarroSelecionado();
                        }
                        break;

                    case 4:
                        ClearScreen.cls();
                        gerenciarMotores();
                        Spause.pausa();
                        ClearScreen.cls();
                        break;

                    case 5:
                        ClearScreen.cls();
                        listarMotoresDisponiveis();
                        Spause.pausa();
                        ClearScreen.cls();
                        break;

                    case 0:
                        ClearScreen.cls();
                        System.out.println("Saindo do sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                        Spause.pausa();
                        ClearScreen.cls();
                        break;
                }

            } catch (GaragemException e) {
                System.out.println("Erro: " + e.getMessage());
                Spause.pausa();
                ClearScreen.cls();
            }
        } while (opcao != 0);
    }

    private void criarCarro() {
        Carro novoCarro = view.criarCarro(motoresDisponiveis);
        carros.add(novoCarro);
        System.out.println("Carro criado com sucesso!");
    }

    private void listarCarros() {
        if (carros.isEmpty()) {
            System.out.println("Nenhum carro cadastrado.");
            return;
        }

        System.out.println("=== CARROS CADASTRADOS ===");
        for (int i = 0; i < carros.size(); i++) {
            Carro c = carros.get(i);
            System.out.println((i + 1) + " - " + c.getMarca() + " " + c.getModelo() + " (Placa: " + c.getPlaca() + ")");
        }
    }

    private void selecionarCarro() {
        if (carros.isEmpty()) {
            System.out.println("Nenhum carro cadastrado.");
            carroSelecionado = null;
            return;
        }

        listarCarros();
        System.out.print("Escolha o número do carro: ");
        int escolha = view.getScanner().nextInt();

        if (escolha > 0 && escolha <= carros.size()) {
            carroSelecionado = carros.get(escolha - 1);
            System.out.println("Carro selecionado: " + carroSelecionado.getMarca() + " " + carroSelecionado.getModelo());
        } else {
            System.out.println("Opção inválida!");
            carroSelecionado = null;
        }
    }

    private void menuCarroSelecionado() {
        int opcao;
        do {
            opcao = view.mostrarMenuCarro(carroSelecionado);

            switch (opcao) {
                case 1:
                    ClearScreen.cls();
                    view.exibirDadosCarro(carroSelecionado);
                    Spause.pausa();
                    ClearScreen.cls();
                    break;

                case 2:
                    ClearScreen.cls();
                    Condutor novoCondutor = view.criarCondutor();
                    carroSelecionado.setCondutor(novoCondutor);
                    System.out.println("Condutor alterado com sucesso!");
                    Spause.pausa();
                    ClearScreen.cls();
                    break;

                case 3:
                    ClearScreen.cls();
                    trocarMotor();
                    Spause.pausa();
                    ClearScreen.cls();
                    break;

                case 4:
                    ClearScreen.cls();
                    System.out.println("Voltando ao menu principal...");
                    break;
            }
        } while (opcao != 4);
    }

    private void trocarMotor() {
        if (motoresDisponiveis.isEmpty()) {
            System.out.println("Nenhum motor disponível. Cadastre um motor primeiro.");
            return;
        }

        System.out.println("=== MOTORES DISPONÍVEIS ===");
        List<Motor> listaMotores = new ArrayList<>(motoresDisponiveis);
        for (int i = 0; i < listaMotores.size(); i++) {
            Motor m = listaMotores.get(i);
            System.out.println((i + 1) + " - " + m.getTipo() + " (" + m.getCavalosPotencia() + " cv)");
        }

        System.out.print(" Escolha o motor: ");
        int escolha = view.getScanner().nextInt();

        if (escolha > 0 && escolha <= listaMotores.size()) {
            carroSelecionado.setMotor(listaMotores.get(escolha - 1));
            System.out.println("Motor trocado com sucesso!");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private void gerenciarMotores() {
        System.out.println("=== GERENCIAR MOTORES ===");
        System.out.println("1 - Adicionar novo motor");
        System.out.println("2 - Listar motores");
        System.out.print("Escolha: ");

        int opcao = view.getScanner().nextInt();

        if (opcao == 1) {
            Motor novoMotor = view.criarMotor();
            if (motoresDisponiveis.add(novoMotor)) {
                System.out.println("Motor adicionado com sucesso!");
            } else {
                System.out.println("Motor já existe na lista!");
            }
        } else if (opcao == 2) {
            listarMotoresDisponiveis();
        }
    }

    private void listarMotoresDisponiveis() {
        if (motoresDisponiveis.isEmpty()) {
            System.out.println("Nenhum motor disponível.");
            return;
        }

        System.out.println("=== MOTORES DISPONÍVEIS ===");
        for (Motor m : motoresDisponiveis) {
            System.out.println("- " + m.getTipo() + " (" + m.getCavalosPotencia() + " cv)");
        }
    }
}