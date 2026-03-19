package models.entities;

public class ContaBancaria {
    private int numeroConta;
    private String titular;
    private double saldo;
    private int contadorDepositos;

    public ContaBancaria() {
    }

    public ContaBancaria(int numeroConta, String titular) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = 0.0;
        this.contadorDepositos = 0;
    }

    public ContaBancaria(int numeroConta, String titular, double depositoInicial) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.contadorDepositos = 0;
        if (depositoInicial >= 5.0) {
            this.saldo = depositoInicial;
            this.contadorDepositos++; 
        } 
        else {
            this.saldo = 0.0;
            System.out.println("Depósito inicial não realizado. Valor mínimo é R$ 5,00.");
        }
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void depositar(double valor) {
        if (valor < 5.0) {
            System.out.println("Depósito não permitido! Valor mínimo é R$ 5,00.");
            return;
        }

        if (contadorDepositos >= 5) {
            double valorComTaxa = valor - 2.99;
            if (valorComTaxa <= 0) {
                System.out.println("Valor insuficiente para cobrir a taxa de R$ 2,99!");
                return;
            }
            saldo += valorComTaxa;
            System.out.printf("Depósito de R$ %.2f realizado com taxa de R$ 2,99. Valor líquido: R$ %.2f\n", valor, valorComTaxa);
        } 
        else {
            saldo += valor;
            System.out.printf("Depósito de R$ %.2f realizado com sucesso!\n", valor);
        }

        contadorDepositos++;
        System.out.printf("Este foi o %dº depósito.\n", contadorDepositos);
    }

    public void sacar(double valor) {
        double valorSaque = valor;

        if (valor <= 0) {
            valorSaque = 49.90;
            System.out.println("Valor não informado. Usando valor padrão de R$ 49,90.");
        }

        double valorComTaxa = valorSaque + 5.0; 

        if (valorComTaxa > saldo) {
            System.out.printf("Saldo insuficiente! Saque de R$ %.2f + taxa R$ 5,00 = R$ %.2f\n", valorSaque, valorComTaxa);
            System.out.printf("Saldo atual: R$ %.2f\n", saldo);
            return;
        }

        saldo -= valorComTaxa;
        System.out.printf("Saque de R$ %.2f realizado com sucesso! (Taxa: R$ 5,00)\n", valorSaque);
    }

    public void sacar(double valor, int diasAntecipacao) {
        double valorSaque = valor;

        if (valor <= 0) {
            valorSaque = 49.90;
            System.out.println("Valor não informado. Usando valor padrão de R$ 49,90.");
        }

        double taxaAntecipacao = valorSaque * 0.10 * diasAntecipacao;
        double valorComTaxas = valorSaque + 5.0 + taxaAntecipacao;

        if (valorComTaxas > saldo) {
            System.out.printf("Saldo insuficiente! \nSaque: R$ %.2f  \nTaxas: R$ %.2f (5,00 + %.2f de antecipação)%n", valorSaque, (5.0 + taxaAntecipacao), taxaAntecipacao);
            System.out.printf("Saldo atual: R$ %.2f\n", saldo);
            return;
        }

        saldo -= valorComTaxas;
        System.out.printf("Saque antecipado de R$ %.2f realizado com sucesso!\n", valorSaque);
        System.out.printf("Taxas: R$ 5,00 (fixa) + R$ %.2f (antecipação de %d dias)\n", taxaAntecipacao, diasAntecipacao);
    }

    public void transferir(ContaBancaria contaDestino, double valor) {
        if (valor <= 0) {
            System.out.println("Valor de transferência deve ser positivo!");
            return;
        }

        double valorComTaxa = valor + 5.0; 

        if (valorComTaxa > saldo) {
            System.out.printf("Saldo insuficiente! Transferência: R$ %.2f + taxa R$ 5,00 = R$ %.2f\n", valor, valorComTaxa);
            System.out.printf("Saldo atual: R$ %.2f\n", saldo);
            return;
        }

        this.saldo -= valorComTaxa;
        contaDestino.saldo += valor;

        System.out.printf("Transferência de R$ %.2f realizada com sucesso para conta %d!\n", valor, contaDestino.getNumeroConta());
        System.out.printf("Taxa de saque: R$ 5,00\n");
    }

    @Override
    public String toString() {
        return 
                "CONTA BANCÁRIA\n" +
                "Número: " + numeroConta + "\n" +
                "Titular: " + titular + "\n" +
                "Saldo: R$ " + String.format("%.2f", saldo) + "\n" +
                "Depósitos realizados: " + contadorDepositos + "\n" +
                "================================";
    }
}