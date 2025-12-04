-- This runs automatically ONLY if the database is empty (first time startup)
CREATE TABLE IF NOT EXISTS book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255)
);

INSERT INTO book (title, author) VALUES ('The Great Gatsby', 'F. Scott Fitzgerald');
INSERT INTO book (title, author) VALUES ('Clean Code', 'Robert C. Martin');