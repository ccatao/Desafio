/*
 Criado por: Cleber Cisne Catão
 Projeto: Desafio
 Data de criação: 19/11/2025
*/

-- Usuários (senhas: senhaTeste123 hashed com BCrypt)
INSERT INTO usuario (id, nome, email, senha_hash, departamento, ativo) VALUES
(gen_random_uuid(), 'João TI', 'ti@example.com', '$2a$12$exemploHashParaSenhaTeste123', 'TI', true),
(gen_random_uuid(), 'Maria Fin', 'fin@example.com', '$2a$12$exemploHashParaSenhaTeste123', 'Financeiro', true),
(gen_random_uuid(), 'Pedro RH', 'rh@example.com', '$2a$12$exemploHashParaSenhaTeste123', 'RH', true),
(gen_random_uuid(), 'Ana Ops', 'ops@example.com', '$2a$12$exemploHashParaSenhaTeste123', 'Operações', true);

-- Módulos (com nomes para UUID lookup em código)
INSERT INTO modulo (id, nome, descricao, ativo) VALUES
(gen_random_uuid(), 'Portal do Colaborador', 'Acesso geral ao portal', true),
(gen_random_uuid(), 'Relatórios Gerenciais', 'Geração de relatórios', true),
(gen_random_uuid(), 'Gestão Financeira', 'Gestão financeira avançada', true),
(gen_random_uuid(), 'Aprovador Financeiro', 'Aprovação de finanças', true),
(gen_random_uuid(), 'Solicitante Financeiro', 'Solicitação de finanças', true),
(gen_random_uuid(), 'Administrador RH', 'Administração RH', true),
(gen_random_uuid(), 'Colaborador RH', 'Colaboração RH', true),
(gen_random_uuid(), 'Gestão de Estoque', 'Controle de estoque', true),
(gen_random_uuid(), 'Compras', 'Gestão de compras', true),
(gen_random_uuid(), 'Auditoria', 'Ferramentas de auditoria', true);