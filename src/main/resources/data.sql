// Criado por: Cleber Cisne Catão
// Projeto: Desafio Java
// Data: 19/11/2025

-- Usuários (substituir senha pelo hash gerado na aplicação)
INSERT INTO usuario (id, nome, email, senha_hash, departamento, data_cadastro) VALUES
('11111111-1111-1111-1111-111111111111','Usuário TI','ti@example.com','$2a$12$EXEMPLOHASH...', 'TI', now()),
('22222222-2222-2222-2222-222222222222','Usuário Financeiro','fin@example.com','$2a$12$EXEMPLOHASH...', 'FINANCEIRO', now()),
('33333333-3333-3333-3333-333333333333','Usuário RH','rh@example.com','$2a$12$EXEMPLOHASH...', 'RH', now()),
('44444444-4444-4444-4444-444444444444','Usuário Operações','ops@example.com','$2a$12$EXEMPLOHASH...', 'OPERACOES', now());

-- Módulos
INSERT INTO modulo (id, nome, descricao, ativo) VALUES
('m1','Portal do Colaborador','Portal do Colaborador', true),
('m2','Relatórios Gerenciais','Relatórios Gerenciais', true),
('m3','Gestão Financeira','Gestão Financeira', true),
('m4','Aprovador Financeiro','Aprovador Financeiro', true),
('m5','Solicitante Financeiro','Solicitante Financeiro', true),
('m6','Administrador RH','Administrador RH', true),
('m7','Colaborador RH','Colaborador RH', true),
('m8','Gestão de Estoque','Gestão de Estoque', true),
('m9','Compras','Compras', true),
('m10','Auditoria','Auditoria', true);
