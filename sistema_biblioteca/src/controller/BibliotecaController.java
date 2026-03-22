package sistema_biblioteca.src.controller;

import sistema_biblioteca.src.controller.exceptions.BibliotecaException;
import sistema_biblioteca.src.models.Aluno;
import sistema_biblioteca.src.models.Biblioteca;
import sistema_biblioteca.src.models.Livro;
import sistema_biblioteca.src.view.BibliotecaView;
import sistema_biblioteca.src.view.util.Spause;
import util.ClearScreen;

public class BibliotecaController {
    private BibliotecaView view;
    private Biblioteca biblioteca;

    public BibliotecaController() {
        this.view = new BibliotecaView();
        this.biblioteca = new Biblioteca();
    }

    public void iniciar() {
        int opcao = -1;

        do {
            try {
                ClearScreen.cls();
                opcao = view.mostrarMenu();

                switch (opcao) {
                    case 1:
                        ClearScreen.cls();
                        cadastrarLivro();
                        break;

                    case 2:
                        ClearScreen.cls();
                        cadastrarAluno();
                        break;

                    case 3:
                        ClearScreen.cls();
                        listarLivros();
                        break;

                    case 4:
                        ClearScreen.cls();
                        listarAlunos();
                        break;

                    case 5:
                        ClearScreen.cls();
                        realizarEmprestimo();
                        break;

                    case 6:
                        ClearScreen.cls();
                        realizarDevolucao();
                        break;

                    case 7:
                        ClearScreen.cls();
                        buscarLivro();
                        break;

                    case 8:
                        ClearScreen.cls();
                        buscarAluno();
                        break;

                    case 9:
                        ClearScreen.cls();
                        menuRelatorios();
                        break;

                    case 0:
                        ClearScreen.cls();
                        System.out.println("Saindo...");
                        break;

                    default:
                        throw new BibliotecaException("Erro inesperado!");
                }

            } catch (BibliotecaException e) {
                System.out.println("**Erro: " + e.getMessage() + "**");
                Spause.pausa();
                view.getScanner().nextLine();
            }
        } while (opcao != 0);
    }

    private void cadastrarLivro() {
        ClearScreen.cls();
        Livro livro = view.cadastrarLivro();

        if (biblioteca.cadastrarLivro(livro)) {
            System.out.println(("Livro cadastrado com sucesso! Código: " + livro.getCodigo()));
        } else {
            System.out.println(("Livro já cadastrado!"));
        }
        Spause.pausa();
    }

    private void cadastrarAluno() {
        ClearScreen.cls();
        Aluno aluno = view.cadastrarAluno();

        if (biblioteca.cadastrarAluno(aluno)) {
            System.out.println(("Aluno cadastrado com sucesso!"));
        } else {
            System.out.println(("Aluno já cadastrado!"));
        }
        Spause.pausa();
    }

    private void listarLivros() {
        ClearScreen.cls();
        view.listarLivros(biblioteca.getLivros());
        Spause.pausa();
    }

    private void listarAlunos() {
        ClearScreen.cls();
        view.listarAlunos(biblioteca.getAlunos());
        Spause.pausa();
    }

    private void realizarEmprestimo() {
        ClearScreen.cls();

        String matricula = view.solicitarMatriculaAluno();
        Aluno aluno = biblioteca.buscarAlunoPorMatricula(matricula);

        if (aluno == null) {
            System.out.println(("Aluno não encontrado!"));
            Spause.pausa();
            return;
        }

        if (!aluno.podeEmprestar()) {
            System.out.println(("Aluno já atingiu o limite de 3 empréstimos!"));
            Spause.pausa();
            return;
        }

        int codigo = view.solicitarCodigoLivro();
        Livro livro = biblioteca.buscarLivroPorCodigo(codigo);

        if (livro == null) {
            System.out.println(("Livro não encontrado!"));
            Spause.pausa();
            return;
        }

        if (!livro.isDisponivel()) {
            System.out.println(("Livro não está disponível para empréstimo!"));
            Spause.pausa();
            return;
        }

        if (aluno.emprestarLivro(livro)) {
            System.out.println(("Empréstimo realizado com sucesso!"));
            System.out.println(("Aluno: " + aluno.getNome()));
            System.out.println(("Livro: " + livro.getTitulo()));
        } else {
            System.out.println("Falha ao realizar empréstimo!");
        }
        Spause.pausa();
    }

    private void realizarDevolucao() {
        ClearScreen.cls();

        String matricula = view.solicitarMatriculaAluno();
        Aluno aluno = biblioteca.buscarAlunoPorMatricula(matricula);

        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            Spause.pausa();
            return;
        }

        if (aluno.getLivrosEmprestados().isEmpty()) {
            System.out.println("Aluno não possui livros emprestados!");
            Spause.pausa();
            return;
        }

        System.out.println("Livros emprestados pelo aluno:");
        for (Livro livro : aluno.getLivrosEmprestados()) {
            System.out.println("   " + livro);
        }

        int codigo = view.solicitarCodigoLivro();
        Livro livro = biblioteca.buscarLivroPorCodigo(codigo);

        if (livro == null) {
            System.out.println("Livro não encontrado!");
            Spause.pausa();
            return;
        }

        if (aluno.devolverLivro(livro)) {
            System.out.println("Devolução realizada com sucesso!");
        } else {
            System.out.println("Este livro não está emprestado para este aluno!");
        }
        Spause.pausa();
    }

    private void buscarLivro() {
        ClearScreen.cls();
        int codigo = view.solicitarCodigoLivro();
        Livro livro = biblioteca.buscarLivroPorCodigo(codigo);
        view.exibirLivro(livro);
        Spause.pausa();
    }

    private void buscarAluno() {
        ClearScreen.cls();
        String matricula = view.solicitarMatriculaAluno();
        Aluno aluno = biblioteca.buscarAlunoPorMatricula(matricula);
        view.exibirAluno(aluno);
        Spause.pausa();
    }

    private void menuRelatorios() {
        int opcao;
        do {
            ClearScreen.cls();
            opcao = view.mostrarMenuRelatorios();

            switch (opcao) {
                case 1:
                    ClearScreen.cls();
                    view.exibirTotalLivros(Biblioteca.getTotalLivrosCadastrados());
                    Spause.pausa();
                    break;

                case 2:
                    ClearScreen.cls();
                    view.exibirQuantidadeEmprestados(biblioteca.getQuantidadeLivrosEmprestados());
                    Spause.pausa();
                    break;

                case 3:
                    ClearScreen.cls();
                    biblioteca.listarLivrosDisponiveis();
                    Spause.pausa();
                    break;

                case 4:
                    ClearScreen.cls();
                    biblioteca.listarAlunosComEmprestimos();
                    Spause.pausa();
                    break;

                case 0:
                    break;

                default:
                    throw new BibliotecaException("Erro inesperado!");
            }
        } while (opcao != 0);
    }
}