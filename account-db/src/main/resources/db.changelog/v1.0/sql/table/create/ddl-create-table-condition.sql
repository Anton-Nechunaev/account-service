CREATE TABLE ${schemaName}.${tableNameCondition}
(
    -- Идентификатор условий счёта
    condition_id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    -- Название счёта
    account_name VARCHAR(50)   NOT NULL,
    -- Период действия счёта
    period PERIOD NOT NULL,
    -- Процентная ставка (например, 5.25)
    percent      DECIMAL(3, 2) NOT NULL,
    -- Выплата процентов (true/false)
    payoff       BOOLEAN       NOT NULL
);

-- Комментарии к таблице
COMMENT ON TABLE ${schemaName}.${tableNameCondition} IS 'Таблица условий для банковского счёта';

-- Комментарии к столбцам
COMMENT ON COLUMN ${schemaName}.${tableNameCondition}.condition_id IS 'Уникальный идентификатор условий счёта';
COMMENT ON COLUMN ${schemaName}.${tableNameCondition}.account_name IS 'Название счёта';
COMMENT ON COLUMN ${schemaName}.${tableNameCondition}.period IS 'Период действия счёта';
COMMENT ON COLUMN ${schemaName}.${tableNameCondition}.percent IS 'Процентная ставка, начисляемая на счёт';
COMMENT ON COLUMN ${schemaName}.${tableNameCondition}.payoff IS 'Признак, указывающий, производится ли выплата процентов (true/false)';