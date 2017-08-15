--FIAT-PHD-0001-CHRT-201720181, M, Full Time 
INSERT INTO AC_FEE_SCDL (id, RESIDENCY_CODE_ID, cohort_code_id, study_mode_id, code, description, total_amount, m_st, c_id, c_ts)
VALUES (nextval('SQ_AC_FEE_SCDL'),
         (SELECT ID from AC_RSCY_CODE where code = 'M'),
        (SELECT ID
         FROM ac_chrt_code
         WHERE code = 'FIAT-PHD-0001-CHRT-201720181'),

        (SELECT ID FROM AC_STDY_MODE where code = '1'),
        'Y1-M-FIAT-PHD-0001-CHRT-201720181',
        'YURAN BAGI COHORT FIAT-PHD-0001-CHRT-201720181 SEPENUH MASA PERMASTAUTIN TETAP', 12370.00,
        1, 1, CURRENT_TIMESTAMP);

--Semester 1--
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79321'), 1, 'my description 1', 170.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79336'), 1, 'my description 2', 2000.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 1, 'my description 2', 1000.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79324'), 1, 'my description 2', 200.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79325'), 1, 'my description 2', 50.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79326'), 1, 'my description 2', 100.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79326'), 1, 'my description 2', 50.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79327'), 1, 'my description 2', 50.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79328'), 1, 'my description 2', 50.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79335'), 1, 'my description 2', 50.00,
        1, 1, CURRENT_TIMESTAMP);
        
--Semester 2--

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 2, 'my description 2', 1000.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79333'), 2, 'my description 2', 900.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 2, 'my description 2', 500.00,
        1, 1, CURRENT_TIMESTAMP);

--Semester 3--

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 3, 'my description 2', 1000.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79333'), 3, 'my description 2', 900.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 3, 'my description 2', 500.00,
        1, 1, CURRENT_TIMESTAMP);
        
--Semester 4--

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 4, 'my description 2', 1000.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79333'), 4, 'my description 2', 900.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 4, 'my description 2', 500.00,
        1, 1, CURRENT_TIMESTAMP);
        
--Semester 5--

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 5, 'my description 2', 1000.00,
        1, 1, CURRENT_TIMESTAMP);
                       
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 5, 'my description 2', 500.00,
        1, 1, CURRENT_TIMESTAMP);

--FIAT-PHD-0001-CHRT-201720181, X, Full Time 
INSERT INTO AC_FEE_SCDL (id, RESIDENCY_CODE_ID, cohort_code_id, study_mode_id, code, description, total_amount, m_st, c_id, c_ts)
VALUES (nextval('SQ_AC_FEE_SCDL'),
         (SELECT ID from AC_RSCY_CODE where code = 'X'),
        (SELECT ID
         FROM ac_chrt_code
         WHERE code = 'FIAT-PHD-0001-CHRT-201720181'),

        (SELECT ID FROM AC_STDY_MODE where code = '1'),
        'Y1-X-FIAT-PHD-0001-CHRT-201720181',
        'YURAN BAGI COHORT FIAT-PHD-0001-CHRT-201720181 SEPENUH MASA BUKAN WARGANEGARA', 20870.00,
        1, 1, CURRENT_TIMESTAMP);

--Semester 1--
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79321'), 1, 'my description 1', 170.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79336'), 1, 'my description 2', 2000.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 1, 'my description 2', 1500.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79333'), 1, 'my description 2', 1800.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79332'), 1, 'my description 2', 1800.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79324'), 1, 'my description 2', 200.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79325'), 1, 'my description 2', 100.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79326'), 1, 'my description 2', 200.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79328'), 1, 'my description 2', 100.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79335'), 1, 'my description 2', 100.00,
        1, 1, CURRENT_TIMESTAMP);

--Semester 2--
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 2, 'my description 2', 1500.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79334'), 2, 'my description 2', 900.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 2, 'my description 2', 1700.00,
        1, 1, CURRENT_TIMESTAMP);
        
--Semester 3--
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 3, 'my description 2', 1500.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 3, 'my description 2', 1700.00,
        1, 1, CURRENT_TIMESTAMP);
                
--Semester 4--
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 4, 'my description 2', 1500.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 4, 'my description 2', 1700.00,
        1, 1, CURRENT_TIMESTAMP);
        
--Semester 5--
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 5, 'my description 2', 1500.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 5, 'my description 2', 1700.00,
        1, 1, CURRENT_TIMESTAMP);
        
--FIAT-PHD-0001-CHRT-201720181, M, PART Time 
INSERT INTO AC_FEE_SCDL (id, RESIDENCY_CODE_ID, cohort_code_id, study_mode_id, code, description, total_amount, m_st, c_id, c_ts)
VALUES (nextval('SQ_AC_FEE_SCDL'),
         (SELECT ID from AC_RSCY_CODE where code = 'M'),
        (SELECT ID
         FROM ac_chrt_code
         WHERE code = 'FIAT-PHD-0001-CHRT-201720181'),

        (SELECT ID FROM AC_STDY_MODE where code = '2'),
        'Y2-M-FIAT-PHD-0001-CHRT-201720181',
        'YURAN BAGI COHORT FIAT-PHD-0001-CHRT-201720181 SEPARUH MASA PERMASTAUTIN TETAP', 9870.00,
        1, 1, CURRENT_TIMESTAMP);

--Semester 1--
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79321'), 1, 'my description 1', 170.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79336'), 1, 'my description 2', 2000.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 1, 'my description 2', 500.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79324'), 1, 'my description 2', 200.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79325'), 1, 'my description 2', 50.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79326'), 1, 'my description 2', 100.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79326'), 1, 'my description 2', 50.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79327'), 1, 'my description 2', 50.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79328'), 1, 'my description 2', 50.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79335'), 1, 'my description 2', 50.00,
        1, 1, CURRENT_TIMESTAMP);
                
--Semester 2--

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 2, 'my description 2', 500.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79333'), 2, 'my description 2', 900.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 2, 'my description 2', 500.00,
        1, 1, CURRENT_TIMESTAMP);

--Semester 3--

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 3, 'my description 2', 500.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79333'), 3, 'my description 2', 900.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 3, 'my description 2', 500.00,
        1, 1, CURRENT_TIMESTAMP);
        
--Semester 4--

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 4, 'my description 2', 500.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79333'), 4, 'my description 2', 900.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 4, 'my description 2', 500.00,
        1, 1, CURRENT_TIMESTAMP);
        
--Semester 5--

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 5, 'my description 2', 500.00,
        1, 1, CURRENT_TIMESTAMP);
                       
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 5, 'my description 2', 500.00,
        1, 1, CURRENT_TIMESTAMP);

--FIAT-PHD-0001-CHRT-201720181, X, Part Time 
INSERT INTO AC_FEE_SCDL (id, RESIDENCY_CODE_ID, cohort_code_id, study_mode_id, code, description, total_amount, m_st, c_id, c_ts)
VALUES (nextval('SQ_AC_FEE_SCDL'),
         (SELECT ID from AC_RSCY_CODE where code = 'X'),
        (SELECT ID
         FROM ac_chrt_code
         WHERE code = 'FIAT-PHD-0001-CHRT-201720181'),

        (SELECT ID FROM AC_STDY_MODE where code = '2'),
        'Y2-X-FIAT-PHD-0001-CHRT-201720181',
        'YURAN BAGI COHORT FIAT-PHD-0001-CHRT-201720181 SEPARUH MASA BUKAN WARGANEGARA', 17120.00,
        1, 1, CURRENT_TIMESTAMP);

--Semester 1--
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79321'), 1, 'my description 1', 170.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79336'), 1, 'my description 2', 2000.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 1, 'my description 2', 750.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79333'), 1, 'my description 2', 1800.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79332'), 1, 'my description 2', 1800.00,
        1, 1, CURRENT_TIMESTAMP);

INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79324'), 1, 'my description 2', 200.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79325'), 1, 'my description 2', 100.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79326'), 1, 'my description 2', 200.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79328'), 1, 'my description 2', 100.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79335'), 1, 'my description 2', 100.00,
        1, 1, CURRENT_TIMESTAMP);

--Semester 2--
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 2, 'my description 2', 750.00,
        1, 1, CURRENT_TIMESTAMP);
        
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79334'), 2, 'my description 2', 900.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 2, 'my description 2', 1700.00,
        1, 1, CURRENT_TIMESTAMP);
        
--Semester 3--
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 3, 'my description 2', 750.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 3, 'my description 2', 1700.00,
        1, 1, CURRENT_TIMESTAMP);
                
--Semester 4--
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 4, 'my description 2', 750.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 4, 'my description 2', 1700.00,
        1, 1, CURRENT_TIMESTAMP);
        
--Semester 5--
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TMGSEB-DBA-00-H79321'), 5, 'my description 2', 750.00,
        1, 1, CURRENT_TIMESTAMP);
                
INSERT INTO ac_fee_scdl_item(id, schedule_id, charge_code_id, ordinal, description, amount, m_st, c_id, c_ts)
values (nextval('SQ_AC_FEE_SCDL_ITEM'), currval('SQ_AC_FEE_SCDL'),
        (select ID FROM ac_chrg_code where code = 'TABPPS-PCA-00-H79999'), 5, 'my description 2', 1700.00,
        1, 1, CURRENT_TIMESTAMP);