INSERT INTO AC_FEE_SCDL (id, cohort_code_id, study_mode_id, code, description, total_amount, m_st, c_id, c_ts)
VALUES (nextval('SQ_AC_FEE_SCDL'),
        (SELECT ID
         FROM ac_chrt_code
         WHERE code = 'FIAT-PHD-0001-CHRT-201720181'),
        (SELECT ID FROM AC_STDY_MODE where code = '1'),
        'YB-FIAT-PHD-0001-CHRT-201720181',
        'YURAN BERULANG COHORT FIAT-PHD-0001-CHRT-201720181', 2442.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-MBA-00-H79321'), 1000.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-MBA-00-H79330'), 1442.00,
        1, 1, CURRENT_TIMESTAMP);


        INSERT INTO AC_FEE_SCDL (id, cohort_code_id, STUDY_MODE_ID, code, description, total_amount, m_st, c_id, c_ts)
VALUES (nextval('SQ_AC_FEE_SCDL'),
        (SELECT ID
         FROM ac_chrt_code
         WHERE code = 'FIAT-PHD-0001-CHRT-201720181'),
                 (SELECT ID FROM AC_STDY_MODE where code = '2'),
        'YB2-FIAT-PHD-0001-CHRT-201720181', 'YURAN BERULANG COHORT FIAT-PHD-0001-CHRT-201720181', 2442.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-MBA-00-H79321'), 1000.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-MBA-00-H79330'), 1442.00,
        1, 1, CURRENT_TIMESTAMP);



