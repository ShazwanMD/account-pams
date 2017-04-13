INSERT INTO AC_FEE_SCDL (id, cohort_code_id, code, description, total_amount, m_st, c_id, c_ts)
VALUES (nextval('SQ_AC_FEE_SCDL'),
        (SELECT ID
         FROM ac_chrt_code
         WHERE code = 'FIAT/PHD/0001/CHRT/0001'),
        'YB/FIAT/PHD/0001/CHRT/0001', 'YURAN BERULANG COHORT FIAT/PHD/0001/CHRT/0001', 2442.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-MBA-00-H79321'), 1000.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-MBA-00-H79330'), 1442.00,
        1, 1, CURRENT_TIMESTAMP);


