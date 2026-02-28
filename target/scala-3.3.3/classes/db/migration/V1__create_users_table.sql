-- Migration for creating the users table
CREATE TABLE IF NOT EXISTS users (
    id         VARCHAR(255) PRIMARY KEY,
    name       VARCHAR(255) NOT NULL, -- Логин или полное имя
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    phone      VARCHAR(50),
    firstname  VARCHAR(100),
    lastname   VARCHAR(100),
    post_id    INT, -- Identifier of the position
    depart_id  INT, -- Identifier of the department
    role_id    INT  -- Identifier of the role (e.g., admin, user, etc.)
);

-- Index for faster lookups by email and by department and role
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_depart_role ON users(depart_id, role_id);

