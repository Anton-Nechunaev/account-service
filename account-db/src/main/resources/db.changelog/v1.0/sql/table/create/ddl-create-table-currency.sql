CREATE TABLE ${schemaName}.${tableNameCurrency}
(
    -- Идентификатор валюты
    currency_id   UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    -- Код валюты
    code          CODE          NOT NULL,
    -- Тип валюты
    currency_type CURRENCY_TYPE NOT NULL,
    -- Дата создания записи
    created_date  TIMESTAMP     NOT NULL
);

-- Комментарии к таблице
COMMENT ON TABLE ${schemaName}.${tableNameCurrency} IS 'Таблица с информацией о валютах';

-- Комментарии к столбцам
COMMENT ON COLUMN ${schemaName}.${tableNameCurrency}.currency_id IS 'Уникальный идентификатор валюты';
COMMENT ON COLUMN ${schemaName}.${tableNameCurrency}.code IS 'Код валюты (например, USD, EUR, RUB)';
COMMENT ON COLUMN ${schemaName}.${tableNameCurrency}.currency_type IS 'Тип валюты (например, национальная или иностранная)';
COMMENT ON COLUMN ${schemaName}.${tableNameCurrency}.created_date IS 'Дата создания записи о валюте';