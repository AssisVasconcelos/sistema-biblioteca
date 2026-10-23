-- DDL: Criação das Tabelas na ordem correta de dependências

CREATE TABLE item (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    titulo VARCHAR(150) NOT NULL,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('livro', 'revista')),
    autor VARCHAR(100),
    edicao VARCHAR(20),
    disponivel BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE usuario (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('aluno', 'professor')),
    limite_itens INT NOT NULL
);

CREATE TABLE emprestimo (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id INT NOT NULL REFERENCES item(id),
    usuario_id INT NOT NULL REFERENCES usuario(id),
    data_retirada DATE NOT NULL DEFAULT CURRENT_DATE,
    data_devolucao_prevista DATE NOT NULL,
    data_devolucao DATE, -- Nulo enquanto estiver em aberto
    valor_multa NUMERIC(10, 2) DEFAULT 0.00
);

-- DML: Inserção de Dados de Teste

-- Inserindo 4 itens
INSERT INTO item (codigo, titulo, tipo, autor, edicao, disponivel) VALUES
('L01', 'Java: Como Programar', 'livro', 'Deitel & Deitel', '10ª', FALSE),
('L02', 'Banco de Dados Relacional', 'livro', 'Elmasri & Navathe', '7ª', TRUE),
('R01', 'Saber Eletrônica', 'revista', 'Editora Saber', 'Nº 450', TRUE),
('R02', 'Byte Magazine', 'revista', 'Tech Media', 'Edição Especial', TRUE);

-- Inserindo 2 usuários
INSERT INTO usuario (nome, tipo, limite_itens) VALUES
('Francisco Assis', 'aluno', 3),
('Prof. Roberto', 'professor', 5);

-- Inserindo 2 empréstimos (1 em aberto e 1 já devolvido com multa)
INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa) VALUES
(1, 1, '2026-10-10', '2026-10-24', NULL, 0.00),                       -- Em aberto (Livro L01)
(3, 2, '2026-09-01', '2026-09-08', '2026-09-12', 4.00);                 -- Devolvido com atraso (Revista R01)

SELECT 
    codigo, 
    titulo, 
    tipo, 
    CASE 
        WHEN disponivel THEN 'Disponível' 
        ELSE 'Emprestado' 
    END AS status_disponibilidade
FROM item;

SELECT 
    u.nome AS usuario, 
    i.titulo AS item, 
    e.data_retirada, 
    e.data_devolucao_prevista
FROM emprestimo e
INNER JOIN usuario u ON e.usuario_id = u.id
INNER JOIN item i ON e.item_id = i.id
WHERE e.data_devolucao IS NULL;

SELECT 
    u.nome AS usuario, 
    COALESCE(SUM(e.valor_multa), 0.00) AS total_multas
FROM usuario u
LEFT JOIN emprestimo e ON u.id = e.usuario_id
GROUP BY u.id, u.nome;

SELECT 
    i.codigo, 
    i.titulo, 
    i.tipo
FROM item i
LEFT JOIN emprestimo e ON i.id = e.item_id
WHERE e.id IS NULL;