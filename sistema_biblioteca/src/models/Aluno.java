package sistema_biblioteca.src.models;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String matricula;
    private List<Livro> livrosEmprestados;

    private static final int LIMITE_EMPRESTIMOS = 3;

    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.livrosEmprestados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public boolean podeEmprestar() {
        return livrosEmprestados.size() < LIMITE_EMPRESTIMOS;
    }

    public int getQuantidadeEmprestimos() {
        return livrosEmprestados.size();
    }

    public boolean emprestarLivro(Livro livro) {
        if (!podeEmprestar()) {
            return false;
        }

        if (!livro.isDisponivel()) {
            return false;
        }

        livro.emprestar();
        livrosEmprestados.add(livro);
        return true;
    }

    public boolean devolverLivro(Livro livro) {
        if (livrosEmprestados.contains(livro)) {
            livro.devolver();
            livrosEmprestados.remove(livro);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Matrícula: " + matricula + " | Nome: " + nome +
                " | Livros emprestados: " + livrosEmprestados.size() + "/" + LIMITE_EMPRESTIMOS;
    }
}
