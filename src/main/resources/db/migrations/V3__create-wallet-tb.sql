CREATE TABLE IF NOT EXISTS tb_wallet
(
    id             CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    full_name      VARCHAR(255)                   NOT NULL,
    cpf_cnpj       VARCHAR(20) UNIQUE             NOT NULL,
    email          VARCHAR(255) UNIQUE            NOT NULL,
    password       VARCHAR(255)                   NOT NULL,
    balance        DECIMAL(19, 2)       DEFAULT 0 NOT NULL,
    wallet_type_id BIGINT,
    CONSTRAINT fk_wallet_wallet_type FOREIGN KEY (wallet_type_id) REFERENCES tb_wallet_type (id)
);