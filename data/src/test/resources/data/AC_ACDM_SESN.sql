-- ACADEMIC SESSION

DELETE FROM ac_acdm_sesn;
INSERT INTO AC_ACDM_SESN (ID, CODE, DESCRIPTION, START_DATE, END_DATE, CURRENT_, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_AC_ACDM_SESN'), '201720181', 'SESSION 201720181', '01-SEP-2017', '01-SEP-2018', true, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_ACDM_SESN (ID, CODE, DESCRIPTION, START_DATE, END_DATE, CURRENT_, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_AC_ACDM_SESN'), '201720182', 'SESSION 201720182', '01-SEP-2017', '01-SEP-2018', false, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_ACDM_SESN (ID, CODE, DESCRIPTION, START_DATE, END_DATE, CURRENT_, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_AC_ACDM_SESN'), '201720183', 'SESSION 201720182', '01-SEP-2017', '01-SEP-2018', false, 1, 1, CURRENT_TIMESTAMP);
