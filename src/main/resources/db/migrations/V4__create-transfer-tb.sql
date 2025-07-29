CREATE TABLE tb_transfer
(
    id                 CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    wallet_sender_id   BIGINT  NULL,
    wallet_receiver_id BIGINT  NULL,
    amount             DECIMAL NULL
);