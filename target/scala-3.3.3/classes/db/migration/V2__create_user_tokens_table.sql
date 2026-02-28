-- Migration for storing user tokens (e.g., for authentication)
CREATE TABLE IF NOT EXISTS user_tokens (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(255) REFERENCES users(id) ON DELETE CASCADE,
    token_hash TEXT NOT NULL, -- Храним хеш токена для безопасности
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP NOT NULL,
    is_revoked BOOLEAN DEFAULT FALSE -- Для принудительного разлогина
);

-- Index для быстрого поиска по user_id
CREATE INDEX idx_user_tokens_user_id ON user_tokens(user_id);

