package application;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import application.exceptions.ContaNaoEncontradaException;
import application.exceptions.SaldoInsuficienteException;
import application.exceptions.ValorInvalidoException;
import application.util.Spause;
import java.util.Locale;
import models.entities.ContaBancaria;
import util.ClearScreen;

public class Banco {
    private static Map<Integer, ContaBancaria> contas = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        sc.useLocale(Locale.US);

        int opcao;
        do {
            ClearScreen.cls();
            mostrarMenuPrincipal();
            opcao = sc.nextInt();
            opcaoMenuPrincipal(opcao);
        } while (opcao != 0);

        sc.close();
    }

    private static void mostrarMenuPrincipal() {
        ClearScreen.cls();
        System.out.println("SISTEMA BANCARIO");
        System.out.println("========================");
        System.out.println("(1) - Criar nova conta");
        System.out.println("(2) - Acessar conta existente");
        System.out.println("(3) - Listar todas as contas");
        System.out.println("(4) - Remover conta");
        System.out.println("(0) - Sair");
        System.out.println("========================");
        System.out.print("Escolha uma opcao: ");
    }

    private static void opcaoMenuPrincipal(int opcao) {
        switch (opcao) {
            case 1:
                criarConta();
                break;
            case 2:
                acessarConta();
                break;
            case 3:
                listarContas();
                break;
            case 4:
                removerConta();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opcao invalida! Tente novamente.");
                Spause.pausa();
                break;
        }
    }

    private static void criarConta() {
        ClearScreen.cls();
        System.out.println("CRIAR NOVA CONTA");
        System.out.println();

        System.out.print("Numero da conta: ");
        int numero = sc.nextInt();
        sc.nextLine();

        if (contas.containsKey(numero)) {
            System.out.println("Numero de conta ja existe! Tente outro.");
            Spause.pausa();
            return;
        }

        System.out.print("Nome do titular: ");
        String nome = sc.nextLine();

        System.out.print("Deseja fazer um deposito inicial? (s/n): ");
        char resp = sc.next().charAt(0);

        ContaBancaria conta;
        if (resp == 's') {
            double depositoInicial;
            do {
                System.out.print("Valor do deposito inicial (minimo R$ 5,00): ");
                depositoInicial = sc.nextDouble();
                if (depositoInicial < 5.0 && depositoInicial > 0) {
                    System.out.println("Deposito minimo e R$ 5,00!");
                } else if (depositoInicial < 0) {
                    System.out.println("Deposito nao pode ser negativo!");
                }
            } while (depositoInicial < 5.0);

            conta = new ContaBancaria(numero, nome, depositoInicial);
        } else {
            conta = new ContaBancaria(numero, nome);
        }

        contas.put(numero, conta);
        System.out.println();
        System.out.println("Conta criada com sucesso!");
        Spause.pausa();
    }

    private static void listarContas() {
        ClearScreen.cls();
        System.out.println("LISTA DE CONTAS");
        System.out.println();
        
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            for (ContaBancaria conta : contas.values()) {
                System.out.println(conta);
                System.out.println("----------------------");
            }
        }
        Spause.pausa();
    }
    
    private static void removerConta() {
        ClearScreen.cls();
        System.out.println("REMOVER CONTA");
        System.out.println();
        
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            Spause.pausa();
            return;
        }
        
        System.out.print("Digite o numero da conta a remover: ");
        int numero = sc.nextInt();
        
        if (!contas.containsKey(numero)) {
            System.out.println("Conta nao encontrada!");
            Spause.pausa();
            return;
        }
        
        ContaBancaria conta = contas.get(numero);
        System.out.println("Conta encontrada: " + conta.getTitular());
        System.out.print("Confirma remocao? (s/n): ");
        char resp = sc.next().charAt(0);
        
        if (resp == 's') {
            contas.remove(numero);
            System.out.println("Conta removida com sucesso!");
        } else {
            System.out.println("Operacao cancelada.");
        }
        Spause.pausa();
    }

    private static void acessarConta() {
        ClearScreen.cls();
        System.out.println("ACESSAR CONTA");
        System.out.println();
        
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            Spause.pausa();
            return;
        }

        System.out.print("Digite o numero da conta: ");
        int numero = sc.nextInt();

        try {
            ContaBancaria conta = buscarContaPorNumero(numero);
            menuConta(conta);
        } catch (ContaNaoEncontradaException e) {
            System.out.println(e.getMessage());
            Spause.pausa();
        }
    }

    private static ContaBancaria buscarContaPorNumero(int numero) {
        ContaBancaria conta = contas.get(numero);
        if (conta == null) {
            throw new ContaNaoEncontradaException("Conta " + numero + " nao encontrada!");
        }
        return conta;
    }

    private static void menuConta(ContaBancaria conta) {
        int opcao;
        do {
            mostrarMenuConta(conta);
            opcao = sc.nextInt();
            try {
                opcaoMenuConta(opcao, conta);
            } catch (SaldoInsuficienteException | ValorInvalidoException e) {
                System.out.println("Erro: " + e.getMessage());
                Spause.pausa();
            } catch (ContaNaoEncontradaException e) {
                System.out.println("Erro: " + e.getMessage());
                Spause.pausa();
                break;
            }
        } while (opcao != 0);
    }

    private static void mostrarMenuConta(ContaBancaria conta) {
        ClearScreen.cls();
        System.out.println("CONTA: " + conta.getNumeroConta() + " - " + conta.getTitular());
        System.out.println("=================================");
        System.out.println("(1) - Visualizar dados da conta");
        System.out.println("(2) - Realizar deposito");
        System.out.println("(3) - Realizar saque");
        System.out.println("(4) - Realizar saque com antecipacao");
        System.out.println("(5) - Realizar transferencia");
        System.out.println("(0) - Voltar ao menu principal");
        System.out.println("=================================");
        System.out.print("Escolha uma opcao: ");
    }

    private static void opcaoMenuConta(int opcao, ContaBancaria conta) {
        switch (opcao) {
            case 1:
                ClearScreen.cls();
                System.out.println();
                System.out.println(conta);
                Spause.pausa();
                break;

            case 2:
                ClearScreen.cls();
                System.out.println();
                System.out.println("DEPOSITO");
                System.out.printf("Saldo atual: R$ %.2f\n", conta.getSaldo());
                System.out.print("Valor do deposito: ");
                double valorDeposito = sc.nextDouble();
                if (valorDeposito < 5.0) {
                    throw new ValorInvalidoException("Valor minimo de deposito e R$ 5,00");
                }
                conta.depositar(valorDeposito);
                System.out.println();
                System.out.println("Deposito realizado com sucesso!");
                System.out.printf("Novo saldo: R$ %.2f\n", conta.getSaldo());
                Spause.pausa();
                break;

            case 3:
                ClearScreen.cls();
                System.out.println();
                System.out.println("SAQUE");
                System.out.printf("Saldo atual: R$ %.2f\n", conta.getSaldo());
                System.out.print("Valor do saque (0 para usar valor padrao R$ 49,90): ");
                double valorSaque = sc.nextDouble();
                if (valorSaque == 0) {
                    valorSaque = 49.90;
                }
                if (valorSaque > conta.getSaldo()) {
                    throw new SaldoInsuficienteException("Saldo insuficiente. Saldo atual: R$ " + conta.getSaldo());
                }
                conta.sacar(valorSaque);
                System.out.printf("Saldo atual: R$ %.2f\n", conta.getSaldo());
                Spause.pausa();
                break;

            case 4:
                ClearScreen.cls();
                System.out.println();
                System.out.println("SAQUE COM ANTECIPACAO");
                System.out.printf("Saldo atual: R$ %.2f\n", conta.getSaldo());
                System.out.print("Valor do saque (0 para usar valor padrao R$ 49,90): ");
                double valorSaqueAnt = sc.nextDouble();
                if (valorSaqueAnt == 0) {
                    valorSaqueAnt = 49.90;
                }
                System.out.print("Dias de antecipacao (taxa de 10%% ao dia): ");
                int dias = sc.nextInt();
                double valorTotal = valorSaqueAnt * (1 + (dias * 0.10));
                if (valorTotal > conta.getSaldo()) {
                    throw new SaldoInsuficienteException(
                        "Saldo insuficiente. Necessario: R$ " + String.format("%.2f", valorTotal)
                    );
                }
                conta.sacar(valorSaqueAnt, dias);
                System.out.printf("Saldo atual: R$ %.2f\n", conta.getSaldo());
                Spause.pausa();
                break;

            case 5:
                ClearScreen.cls();
                System.out.println();
                System.out.println("TRANSFERENCIA");
                System.out.println();
                
                System.out.println("Contas disponiveis para transferencia:");
                for (ContaBancaria c : contas.values()) {
                    if (c.getNumeroConta() != conta.getNumeroConta()) {
                        System.out.printf("Conta %d - %s (Saldo: R$ %.2f)\n", 
                            c.getNumeroConta(), c.getTitular(), c.getSaldo());
                    }
                }
                
                System.out.println();
                System.out.print("Digite o numero da conta destino: ");
                int numDestino = sc.nextInt();
                
                ContaBancaria contaDestino = buscarContaPorNumero(numDestino);
                
                if (contaDestino.getNumeroConta() == conta.getNumeroConta()) {
                    throw new ValorInvalidoException("Nao pode transferir para a mesma conta!");
                }
                
                System.out.println();
                System.out.printf("Transferindo para: %s\n", contaDestino.getTitular());
                System.out.printf("Seu saldo: R$ %.2f\n", conta.getSaldo());
                System.out.print("Valor a transferir: R$ ");
                double valorTransferencia = sc.nextDouble();
                
                if (valorTransferencia > conta.getSaldo()) {
                    throw new SaldoInsuficienteException(
                        "Saldo insuficiente para transferencia. Saldo: R$ " + conta.getSaldo()
                    );
                }
                
                if (valorTransferencia <= 0) {
                    throw new ValorInvalidoException("Valor de transferencia deve ser positivo");
                }
                
                conta.transferir(contaDestino, valorTransferencia);
                
                System.out.println();
                System.out.println("SALDOS ATUALIZADOS");
                System.out.printf("Sua conta: R$ %.2f\n", conta.getSaldo());
                System.out.printf("Conta destino: R$ %.2f\n", contaDestino.getSaldo());
                Spause.pausa();
                break;

            case 0:
                System.out.println("Voltando ao menu principal...");
                break;

            default:
                System.out.println("Opcao invalida! Tente novamente.");
                Spause.pausa();
                break;
        }
    }
}