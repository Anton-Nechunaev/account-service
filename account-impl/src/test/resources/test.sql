DELETE FROM account;
DELETE FROM condition;
DELETE FROM account_details;
DELETE FROM currency;

INSERT INTO account_details(account_details_id,account_number, bik_bank, bank_name, inn_bank, cor_account_bank,
                            kpp_bank, okpo_bank, ogrn_bank, swift_code_bank, update_date,
                            created_date, reason_blocking)
values ('123e4567-e89b-12d3-a456-426614174000', '40817840800000000870',
        '044525970','Active-Bank', '7702070117',
        '30101810400000000970', '784253001','00032538',
        '1025600132195', 'RU', '2024-10-10',
        '2004-10-10','Не заблокирован');

INSERT INTO condition(condition_id, account_name, period, percent, payoff)
values ('01234567-89ab-cdef-0123-456789abcdef','Название счёта', 'THREE_MONTHS',
        9.50, false);

INSERT INTO currency (currency_id, code, currency_type, created_date)
values ('01234567-89ab-cdef-0424-456789abcdef', 'RUB', 'RUB',
        '1993-01-01');

INSERT INTO account (account_id, account_details_id, client_id, account_type, current_balance,
                         open_date, close_date, account_status, is_matter, update_date, condition, currency)
values ('311e4567-e89b-12d3-a456-426614174015', '123e4567-e89b-12d3-a456-426614174000',
    '57663b11-4f13-4927-ad86-6b5097bbaf9f', 'CURRENT', 100,
    '2020-12-12','2025-12-12', 'ACTIVE', false, '2021-12-12',
    '01234567-89ab-cdef-0123-456789abcdef', '01234567-89ab-cdef-0424-456789abcdef');