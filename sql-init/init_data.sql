-- This runs automatically ONLY if the database is empty (first time startup)
-- 1. Create the table that JAVA expects
CREATE TABLE IF NOT EXISTS book_library (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    author_name VARCHAR(255),  -- Snake case to match @Column
    created_by VARCHAR(255)    -- Snake case to match @Column
);

-- 2. Insert data into book_library (NOT "book")
INSERT INTO book_library (title, author_name, created_by)
VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', 'System');

INSERT INTO book_library (title, author_name, created_by)
VALUES ('Clean Code', 'Robert C. Martin', 'System');