--INSERT INTO AC_ACCT_CHRG (ID, REFERENCE_NO, SOURCE_NO, DESCRIPTION, AMOUNT, ORDINAL, CHARGE_DATE,
--CHARGE_TYPE, ACCOUNT_ID, SESSION_ID, STUDY_MODE_ID, COHORT_CODE_ID, STUDY_CENTER_ID, GRADUATE_CENTER_TYPE, M_ST) VALUES (
--NEXTVAL('SEQ_ACCT_CHRG'), '20172018-001', 'A17P001', '', 0, 1, CURRENT_TIMESTAMP, 0, 
--(SELECT ID from AC_ACCT where CODE = 'A17P001'), 
--(SELECT ID from AC_ACDM_SESN where CURRENT_ = 'TRUE'), 1, 
--(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0001-CHRT-201720181'), 1, 1, 1);
--
--INSERT INTO AC_ACCT_CHRG (ID, REFERENCE_NO, SOURCE_NO, DESCRIPTION, AMOUNT, ORDINAL, CHARGE_DATE,
--CHARGE_TYPE, ACCOUNT_ID, SESSION_ID, STUDY_MODE_ID, COHORT_CODE_ID, STUDY_CENTER_ID, GRADUATE_CENTER_TYPE,M_ST) VALUES (
--NEXTVAL('SEQ_ACCT_CHRG'), '20172018-002', 'A17P002', '', 0, 2, CURRENT_TIMESTAMP, 0, 
--(SELECT ID from AC_ACCT where CODE = 'A17P002'), 
--(SELECT ID from AC_ACDM_SESN where CURRENT_ = 'TRUE'), 2, 
--(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0001-CHRT-201720181'), 2, 1, 1);
--
--INSERT INTO AC_ACCT_CHRG (ID, REFERENCE_NO, SOURCE_NO, DESCRIPTION, AMOUNT, ORDINAL, CHARGE_DATE,
--CHARGE_TYPE, ACCOUNT_ID, SESSION_ID, STUDY_MODE_ID, COHORT_CODE_ID, STUDY_CENTER_ID, GRADUATE_CENTER_TYPE, M_ST) VALUES (
--NEXTVAL('SEQ_ACCT_CHRG'), '20172018-003', 'A17P003', '', 0, 2, CURRENT_TIMESTAMP, 0, 
--(SELECT ID from AC_ACCT where CODE = 'A17P003'), 
--(SELECT ID from AC_ACDM_SESN where CURRENT_ = 'TRUE'), 1, 
--(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0001-CHRT-201720181'), 3, 1, 1);
--
--INSERT INTO AC_ACCT_CHRG (ID, REFERENCE_NO, SOURCE_NO, DESCRIPTION, AMOUNT, ORDINAL, CHARGE_DATE,
--CHARGE_TYPE, ACCOUNT_ID, SESSION_ID, STUDY_MODE_ID, COHORT_CODE_ID, GRADUATE_CENTER_TYPE, M_ST) VALUES (
--NEXTVAL('SEQ_ACCT_CHRG'), '20172018-004', 'A17P004', '', 0, 1, CURRENT_TIMESTAMP, 0, 
--(SELECT ID from AC_ACCT where CODE = 'A17P004'), 
--(SELECT ID from AC_ACDM_SESN where CURRENT_ = 'TRUE'), 2, 
--(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0002-CHRT-201720181'), 0, 1);
--
--INSERT INTO AC_ACCT_CHRG (ID, REFERENCE_NO, SOURCE_NO, DESCRIPTION, AMOUNT, ORDINAL, CHARGE_DATE,
--CHARGE_TYPE, ACCOUNT_ID, SESSION_ID, STUDY_MODE_ID, COHORT_CODE_ID, GRADUATE_CENTER_TYPE, M_ST) VALUES (
--NEXTVAL('SEQ_ACCT_CHRG'), '20172018-005', 'A17P005', '', 0, 2, CURRENT_TIMESTAMP, 0, 
--(SELECT ID from AC_ACCT where CODE = 'A17P005'), 
--(SELECT ID from AC_ACDM_SESN where CURRENT_ = 'TRUE'), 1, 
--(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0002-CHRT-201720181'), 0, 1);
--
----INSERT INTO AC_ACCT_CHRG (ID, REFERENCE_NO, SOURCE_NO, DESCRIPTION, AMOUNT, ORDINAL, CHARGE_DATE,
----CHARGE_TYPE, ACCOUNT_ID, SESSION_ID, STUDY_MODE_ID, COHORT_CODE_ID, STUDY_CENTER_ID, GRADUATE_CENTER_TYPE, M_ST) VALUES (
----NEXTVAL('SEQ_ACCT_CHRG'), '20172018-006', 'A17P006', '', 0, 1, CURRENT_TIMESTAMP, 0, 
----(SELECT ID from AC_ACCT where CODE = 'A17P006'), 
----(SELECT ID from AC_ACDM_SESN where CURRENT_ = 'TRUE'), 2, 
----(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0003-CHRT-201720181'), 1);
----
----INSERT INTO AC_ACCT_CHRG (ID, REFERENCE_NO, SOURCE_NO, DESCRIPTION, AMOUNT, ORDINAL, CHARGE_DATE,
----CHARGE_TYPE, ACCOUNT_ID, SESSION_ID, STUDY_MODE_ID, COHORT_CODE_ID, M_ST) VALUES (
----NEXTVAL('SEQ_ACCT_CHRG'), '20172018-007', 'A17P007', '', 0, 2, CURRENT_TIMESTAMP, 0, 
----(SELECT ID from AC_ACCT where CODE = 'A17P007'), 
----(SELECT ID from AC_ACDM_SESN where CURRENT_ = 'TRUE'), 2, 
----(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0004-CHRT-201720181'), 1);
----
----INSERT INTO AC_ACCT_CHRG (ID, REFERENCE_NO, SOURCE_NO, DESCRIPTION, AMOUNT, ORDINAL, CHARGE_DATE,
----CHARGE_TYPE, ACCOUNT_ID, SESSION_ID, STUDY_MODE_ID, COHORT_CODE_ID, M_ST) VALUES (
----NEXTVAL('SEQ_ACCT_CHRG'), '20172018-008', 'A17P008', '', 0, 1, CURRENT_TIMESTAMP, 0, 
----(SELECT ID from AC_ACCT where CODE = 'A17P008'), 
----(SELECT ID from AC_ACDM_SESN where CURRENT_ = 'TRUE'), 2, 
----(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0004-CHRT-201720181'), 1);