public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(10);

        // 1. Cadastro do Acervo
        Livro l1 = new Livro("L01", "Java para Iniciantes");
        Livro l2 = new Livro("L02", "Estruturas de Dados em Java");
        Livro l3 = new Livro("L03", "Engenharia de Software");
        Revista r1 = new Revista("R01", "Mundo Java Edição 100");
        Revista r2 = new Revista("R02", "Tech Monthly");

        biblioteca.adicionarItem(l1);
        biblioteca.adicionarItem(l2);
        biblioteca.adicionarItem(l3);
        biblioteca.adicionarItem(r1);
        biblioteca.adicionarItem(r2);

        // 2. Cadastro de Usuários
        Aluno aluno = new Aluno("Francisco Assis"); // Limite: 3
        Professor professor = new Professor("Prof. Carlos"); // Limite: 5

        biblioteca.listarAcervo();

        System.out.println("\n--- SIMULAÇÃO DE EMPRÉSTIMOS ---");

        // Empréstimos bem-sucedidos do aluno até atingir o limite (3 itens)
        biblioteca.emprestar(aluno, l1);
        biblioteca.emprestar(aluno, l2);
        biblioteca.emprestar(aluno, r1);

        // Tentativa de empréstimo recusada por limite atingido
        System.out.println("\n-- Tentando emprestar além do limite --");
        biblioteca.emprestar(aluno, r2);

        // Empréstimo para o professor
        System.out.println("\n-- Empréstimo para Professor --");
        biblioteca.emprestar(professor, l3);

        biblioteca.listarAcervo();
    }
}