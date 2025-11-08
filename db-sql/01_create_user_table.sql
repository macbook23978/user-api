-- Creazione tabella users_mac_
CREATE TABLE users_mac_ (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    email VARCHAR(150) UNIQUE NOT NULL,
    address TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Funzione per aggiornare updated_at
CREATE OR REPLACE FUNCTION set_updated_at_mac_()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger
CREATE TRIGGER trg_set_updated_at_mac_
BEFORE UPDATE ON users_mac_
FOR EACH ROW
EXECUTE FUNCTION set_updated_at_mac_();

