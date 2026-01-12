-- User table
CREATE TABLE IF NOT EXISTS EXPT_USER (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email_address VARCHAR(100) NOT NULL
);

-- Category table (standalone table, referenced by expenses)
CREATE TABLE IF NOT EXISTS EXPT_CATEGORY (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(500)
);

-- User Address table (One-to-Many relationship with User)
-- One user can have many addresses
CREATE TABLE IF NOT EXISTS EXPT_USER_ADDRESS (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255),
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100),
    zip_code VARCHAR(20),
    country VARCHAR(100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES EXPT_USER(id) ON DELETE CASCADE
);

-- Expense table
-- One-to-Many relationship with User (one user can have many expenses)
-- Many-to-One relationship with Category (many expenses can belong to one category)
CREATE TABLE IF NOT EXISTS EXPT_EXPENSE (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    amount DECIMAL(19, 2) NOT NULL,
    description VARCHAR(500),
    expense_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES EXPT_USER(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES EXPT_CATEGORY(id) ON DELETE RESTRICT
);

-- Create indexes for better query performance
CREATE INDEX IF NOT EXISTS IDX_EXPENSE_USER_ID ON EXPT_EXPENSE(user_id);
CREATE INDEX IF NOT EXISTS IDX_EXPENSE_CATEGORY_ID ON EXPT_EXPENSE(category_id);
CREATE INDEX IF NOT EXISTS IDX_EXPENSE_DATE ON EXPT_EXPENSE(expense_date);
CREATE INDEX IF NOT EXISTS IDX_USER_ADDRESS_USER_ID ON EXPT_USER_ADDRESS(user_id);