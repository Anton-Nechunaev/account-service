DELETE FROM account_details;
DELETE FROM condition;
DELETE FROM currency;
DELETE FROM account;
DELETE FROM transaction;

INSERT INTO account_details
(account_details_id, account_number, bik_bank, bank_name, inn_bank, cor_account_bank, kpp_bank, okpo_bank, ogrn_bank, swift_code_bank,
 update_date, created_date)
VALUES ('100e4567-e89b-12d3-a456-426614174015', '3214344rgerg231231ffwefewFWE', '044525970', 'Active-Bank', '7702070117',
        '30101810400000000970', '784253001', '00032538', '1025600132195', 'ACTV RU MM ХХХ', '2023-06-20T14:30:00Z', '2023-06-20T14:30:00Z'),
       ('800e4567-e89b-12d3-a456-426614174015', '12331rgerg231231ffwefewFWE', '044525970', 'Active-Bank', '7702070117',
        '30101810400000000970', '784253001', '00032538', '1025600132195', 'ACTV RU MM ХХХ', '2023-06-20T14:30:00Z', '2023-06-20T14:30:00Z'),
       ('800e4567-e89b-12d3-a456-426614174016', 'gerger12rgerg231231ffwefewFWE', '044525970', 'Active-Bank', '7702070117',
        '30101810400000000970', '784253001', '00032538', '1025600132195', 'ACTV RU MM ХХХ', '2023-06-20T14:30:00Z', '2023-06-20T14:30:00Z');

INSERT INTO condition
(condition_id, account_name, period, percent, payoff)
VALUES ('111e4567-e89b-12d3-a456-426614174015', 'Основной', 'THREE_MONTHS', 1.23, true),
       ('112e4567-e89b-12d3-a456-426614174015', 'Вспомогательный', 'SIX_MONTHS', 2.25, true),
       ('113e4567-e89b-12d3-a456-426614174015', 'Детский', 'NINE_MONTHS', 3.31, true);

INSERT INTO currency
(currency_id, code, currency_type, created_date)
VALUES ('211e4567-e89b-12d3-a456-426614174015', 'RUB', 'RUB', '2023-06-20T14:30:00Z'),
       ('212e4567-e89b-12d3-a456-426614174015', 'RUB', 'RUB', '2023-06-20T14:30:00Z'),
       ('213e4567-e89b-12d3-a456-426614174015', 'RUB', 'RUB', '2023-06-20T14:30:00Z');

INSERT INTO account
(account_id, account_details_id, client_id, account_type, current_balance, open_date, account_status,
 is_matter, update_date, condition, currency)
VALUES ('311e4567-e89b-12d3-a456-426614174015', '100e4567-e89b-12d3-a456-426614174015', '222e4567-e89b-12d3-a456-426614174001','CURRENT', 125000,
        '2023-06-20T14:30:00Z', 'ACTIVE', true, '2023-06-20T14:30:00Z', '111e4567-e89b-12d3-a456-426614174015', '211e4567-e89b-12d3-a456-426614174015'),
       ('312e4567-e89b-12d3-a456-426614174015', '800e4567-e89b-12d3-a456-426614174015', '777e4567-e89b-12d3-a456-426614174001','CURRENT', 300000,
        '2023-06-20T14:30:00Z', 'ACTIVE', true, '2023-06-20T14:30:00Z', '112e4567-e89b-12d3-a456-426614174015', '212e4567-e89b-12d3-a456-426614174015'),
       ('313e4567-e89b-12d3-a456-426614174015', '800e4567-e89b-12d3-a456-426614174016', '888e4567-e89b-12d3-a456-426614174001','CURRENT', 15000,
        '2023-06-20T14:30:00Z', 'ACTIVE', true, '2023-06-20T14:30:00Z', '113e4567-e89b-12d3-a456-426614174015', '213e4567-e89b-12d3-a456-426614174015');