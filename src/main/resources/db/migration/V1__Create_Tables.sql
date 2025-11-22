/*
Criado por: Cleber Cisne Catão
Projeto: Desafio
Data de criação: 19/11/2025
*/
-- Tabelas (ajustado para Postgres 17)
CREATE TABLE usuario (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT true
);

CREATE TABLE modulo (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) UNIQUE NOT NULL,
    descricao TEXT,
    ativo BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE solicitacao_acesso (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    protocolo VARCHAR(50) UNIQUE NOT NULL,
    usuario_id UUID REFERENCES usuario(id),
    departamento_solicitante VARCHAR(100) NOT NULL,
    justificativa TEXT,
    urgente BOOLEAN NOT NULL,
    data_solicitacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_expiracao TIMESTAMP,
    status VARCHAR(20) NOT NULL, -- ATIVO, NEGADO, CANCELADO
    motivo_negacao TEXT
);

CREATE TABLE solicitacao_item (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    solicitacao_id UUID REFERENCES solicitacao_acesso(id) ON DELETE CASCADE,
    modulo_id UUID REFERENCES modulo(id),
    status_item VARCHAR(20) NOT NULL, -- ATIVO, NEGADO
    motivo TEXT
);

CREATE TABLE acesso_usuario (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id UUID REFERENCES usuario(id),
    modulo_id UUID REFERENCES modulo(id),
    data_concessao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_expiracao TIMESTAMP NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT true
);
CREATE TABLE historico_alteracao (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    solicitacao_id UUID REFERENCES solicitacao_acesso(id),
    descricao TEXT,
    data_alteracao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);