-- Criação das tabelas
CREATE TABLE forma_pagamento (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50),
    numeroParcelas VARCHAR(50)
);

CREATE TABLE descricao (
    id SERIAL PRIMARY KEY,
    valor DECIMAL(10, 2),
    dataHora TIMESTAMP,
    estabelecimento VARCHAR(100),
    nsu VARCHAR(50),
    codAutorizacao VARCHAR(50),
    status VARCHAR(20)
);

CREATE TABLE transacao (
    id SERIAL PRIMARY KEY,
    cartao VARCHAR(50),
    descricao_id BIGINT,
    forma_pagamento_id BIGINT,
    CONSTRAINT fk_descricao FOREIGN KEY (descricao_id) REFERENCES descricao(id),
    CONSTRAINT fk_forma_pagamento FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento(id)
);
