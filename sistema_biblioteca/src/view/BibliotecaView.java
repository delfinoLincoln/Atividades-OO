package sistema_biblioteca.src.view;

import java.util.Map;
import java.util.Scanner;

import sistema_biblioteca.src.models.Livro;
import sistema_biblioteca.src.models.Aluno;

public class BibliotecaView {
    private Scanner scanner;

    public BibliotecaView() {
        scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public int mostrarMenu() {
        System.out.println("=== SISTEMA DE BIBLIOTECA ===");
        System.out.println("(1) - Cadastrar livro");
        System.out.println("(2) - Cadastrar aluno");
        System.out.println("(3) - Listar todos os livros");
        System.out.println("(4) - Listar alunos");
        System.out.println("(5) - Realizar empréstimo");
        System.out.println("(6) - Realizar devolução");
        System.out.println("(7) - Buscar livro por código");
        System.out.println("(8) - Buscar aluno por matrícula");
        System.out.println("(9) - Relatórios");
        System.out.println("(0) - Sair");
        System.out.print("**Escolha uma das opções acima**: ");
        return scanner.nextInt();
    }

    public int mostrarMenuRelatorios() {
        System.out.println("=== RELATÓRIOS ===");
        System.out.println("(1) - Total de livros cadastrados");
        System.out.println("(2) - Quantidade de livros emprestados");
        System.out.println("(3) - Listar livros disponíveis");
        System.out.println("(4) - Listar alunos com empréstimos");
        System.out.println("(0) - Voltar");
        System.out.print("Escolha: ");
        return scanner.nextInt();
    }

    public Livro cadastrarLivro() {
        System.out.println("\n--- CADASTRO DE LIVRO ---");
        System.out.print("Título: ");
        scanner.nextLine();
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        return new Livro(titulo, autor);
    }

    public Aluno cadastrarAluno() {
        System.out.println("--- CADASTRO DE ALUNO ---");
        System.out.print("Nome: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.next();

        return new Aluno(nome, matricula);
    }

    public int solicitarCodigoLivro() {
        System.out.print("Digite o código do livro: ");
        return scanner.nextInt();
    }

    public String solicitarMatriculaAluno() {
        System.out.print("Digite a matrícula do aluno: ");
        return scanner.next();
    }

    public void exibirLivro(Livro livro) {
        if (livro != null) {
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    public void exibirAluno(Aluno aluno) {
        if (aluno != null) {
            System.out.println(aluno);
            if (!aluno.getLivrosEmprestados().isEmpty()) {
                System.out.println("Livros emprestados:");
                for (Livro livro : aluno.getLivrosEmprestados()) {
                    System.out.println("   -> " + livro.getTitulo() + " (Código: " + livro.getCodigo() + ")");
                }
            }
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }

    public void listarLivros(Map<Integer, Livro> livros) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        System.out.println("=== LISTA DE LIVROS ===");
        for (Livro livro : livros.values()) {
            System.out.println(livro);
        }
    }

    public void listarAlunos(Map<String, Aluno> alunos) {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("=== LISTA DE ALUNOS ===");
        for (Aluno aluno : alunos.values()) {
            System.out.println(aluno);
        }
    }

    public void exibirTotalLivros(int total) {
        System.out.println("Total de livros cadastrados: " + total);
    }

    public void exibirQuantidadeEmprestados(int quantidade) {
        System.out.println("Quantidade de livros emprestados: " + quantidade);
    }
}