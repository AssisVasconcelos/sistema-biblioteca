public class Biblioteca {
    private ItemBiblioteca[] acervo;
    private int totalItens;

    public Biblioteca(int capacidade) {
        this.acervo = new ItemBiblioteca[capacidade];
        this.totalItens = 0;
    }

    public void adicionarItem(ItemBiblioteca item) {
        if (totalItens < acervo.length) {
            acervo[totalItens] = item;
            totalItens++;
        } else {
            System.out.println("Acervo cheio! Não foi possível adicionar " + item.getTitulo());
        }
    }

    public boolean emprestar(Usuario usuario, ItemBiblioteca item) {
        if (!usuario.podeEmprestar()) {
            System.out.println("❌ Empréstimo recusado para " + usuario.getNome() + ": Limite de itens atingido (" + usuario.getLimiteItens() + ").");
            return false;
        }

        if (!item.isDisponivel()) {
            System.out.println("❌ Empréstimo recusado: O item '" + item.getTitulo() + "' já está emprestado.");
            return false;
        }

        item.emprestar();
        usuario.incrementarEmprestimo();
        System.out.println("✅ Empréstimo realizado: '" + item.getTitulo() + "' para " + usuario.getNome() + " (Prazo: " + item.getPrazoDevolucaoDias() + " dias).");
        return true;
    }

    public void devolver(Usuario usuario, ItemBiblioteca item) {
        item.devolver();
        usuario.decrementarEmprestimo();
        System.out.println("🔄 Item '" + item.getTitulo() + "' devolvido por " + usuario.getNome() + ".");
    }

    public void listarAcervo() {
        System.out.println("\n--- ACERVO DA BIBLIOTECA ---");
        for (int i = 0; i < totalItens; i++) {
            // Polimorfismo puro: chama getPrazoDevolucaoDias() e getMultaPorDiaAtraso() sem verificar o tipo com if/instanceof
            ItemBiblioteca item = acervo[i];
            String status = item.isDisponivel() ? "Disponível" : "Emprestado";
            System.out.println("[" + item.getCodigo() + "] " + item.getTitulo() + " | Status: " + status + 
                               " | Prazo: " + item.getPrazoDevolucaoDias() + " dias | Multa/dia: R$ " + 
                               String.format("%.2f", item.getMultaPorDiaAtraso()));
        }
    }
}