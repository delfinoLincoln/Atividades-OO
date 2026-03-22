package sistema_biblioteca.src.models;

public class Livro {
    private int codigo;
    private String titulo;
    private String autor;
    private boolean disponivel;
    private static int contadorCodigos = 0;
    
    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
        this.codigo = contadorCodigos++;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void emprestar() {
        if (disponivel) {
            disponivel = false;
        }
    }

    public void devolver() {
        disponivel = true;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + " | Título: " + titulo + " | Autor: " + autor +
                " | Status: " + (disponivel ? "Disponível" : "Emprestado");
    }
}