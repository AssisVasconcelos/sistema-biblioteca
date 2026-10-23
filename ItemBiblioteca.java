public abstract class ItemBiblioteca {
    private String codigo;
    private String titulo;
    private boolean disponivel;

    public ItemBiblioteca(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.disponivel = true;
    }

    public abstract int getPrazoDevolucaoDias();
    public abstract double getMultaPorDiaAtraso();

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    // Apenas a classe Biblioteca ou os métodos de negócio alteram a disponibilidade
    void emprestar() {
        this.disponivel = false;
    }

    void devolver() {
        this.disponivel = true;
    }
}