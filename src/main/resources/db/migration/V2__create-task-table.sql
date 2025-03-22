CREATE TABLE task (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    priority VARCHAR(255) NOT NULL,
    deadline TIMESTAMP NOT NULL,
    status BOOLEAN NOT NULL,
    userId BIGINT,
    CONSTRAINT fk_user FOREIGN KEY (userId) REFERENCES users(id)
);
