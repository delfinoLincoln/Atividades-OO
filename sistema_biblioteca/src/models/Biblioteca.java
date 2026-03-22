package sistema_biblioteca.src.models;

import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
    private Map<Integer, Livro> livros;
    private Map<String, Aluno> alunos;
    private static int totalLivrosCadastrados = 0;
    
    public Biblioteca() {
        this.livros = new HashMap<>();
        this.alunos = new HashMap<>();
    }
    
    public boolean cadastrarLivro(Livro livro) {
        if (livros.containsKey(livro.getCodigo())) {
            return false;
        }
        livros.put(livro.getCodigo(), livro);
        totalLivrosCadastrados++;
        return true;
    }
    
    public boolean cadastrarAluno(Aluno aluno) {
        if (alunos.containsKey(aluno.getMatricula())) {
            return false;
        }
        alunos.put(aluno.getMatricula(), aluno);
        return true;
    }
    
    public Livro buscarLivroPorCodigo(int codigo) {
        return livros.get(codigo);
    }
    
    public Aluno buscarAlunoPorMatricula(String matricula) {
        return alunos.get(matricula);
    }
    
    public Map<Integer, Livro> getLivros() {
        return livros;
    }
    
    public Map<String, Aluno> getAlunos() {
        return alunos;
    }
    
    public static int getTotalLivrosCadastrados() {
        return totalLivrosCadastrados;
    }
    
    public int getQuantidadeLivrosEmprestados() {
        int contador = 0;
        for (Livro livro : livros.values()) {
            if (!livro.isDisponivel()) {
                contador++;
            }
        }
        return contador;
    }
    
    public void listarLivrosDisponiveis() {
        for (Livro livro : livros.values()) {
            if (livro.isDisponivel()) {
                System.out.println(livro);
            }
        }
    }
    
    public void listarAlunosComEmprestimos() {
        for (Aluno aluno : alunos.values()) {
            if (aluno.getQuantidadeEmprestimos() > 0) {
                System.out.println(aluno);
                for (Livro livro : aluno.getLivrosEmprestados()) {
                    System.out.println("   -> " + livro);
                }
            }
        }
    }
}