INSERT INTO AC_CNFG (ID, CONFIG_KEY, CONFIG_VALUE, DESCRIPTION, C_ID, C_TS, M_ST)
    VALUES (nextval('SQ_AC_CNFG'), 'account.balance.limit', '200', 'Account Balance Limit', 1, CURRENT_TIMESTAMP, 1);
