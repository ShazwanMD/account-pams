-- account for student

INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
VALUES (nextval('SQ_AC_ACCT'),
        (SELECT ID
         FROM AC_ACTR
         WHERE email = 'student1@umk.edu.my'),
        (SELECT IDENTITY_NO
         FROM AC_ACTR
         WHERE email = 'student1@umk.edu.my'),
        (SELECT NAME
         FROM AC_ACTR
         WHERE email = 'student1@umk.edu.my'),
        1, CURRENT_TIMESTAMP, 1);

INSERT INTO ac_acct_trsn (id, account_id, charge_code_id, session_id, posted_date, source_no, transaction_code, amount, c_id, c_ts, m_st)
VALUES (nextval('SQ_AC_ACCT_TRSN'),
  currval('SQ_AC_ACCT'),
  (SELECT ID
   FROM AC_CHRG_CODE
   WHERE CODE = 'TMGSEB-MBA-00-H79321'),
  (SELECT ID
   FROM AC_ACDM_SESN
   WHERE CODE = '201720181'),
  CURRENT_DATE,
  'INVC1234',
  0,
  1000.00,
  1, CURRENT_TIMESTAMP, 1
);

INSERT INTO ac_acct_trsn (id, account_id, charge_code_id, session_id, posted_date, source_no, transaction_code, amount,  c_id, c_ts, m_st)
VALUES (nextval('SQ_AC_ACCT_TRSN'),
  currval('SQ_AC_ACCT'),
  (SELECT ID
   FROM AC_CHRG_CODE
   WHERE CODE = 'TMGSEB-MBA-00-H79322'),
  (SELECT ID
   FROM AC_ACDM_SESN
   WHERE CODE = '201720181'),
  CURRENT_DATE,
  'INVC1238',
  0,
  1000.00,
  1, CURRENT_TIMESTAMP, 1
);


INSERT INTO ac_acct_trsn (id, account_id, charge_code_id, session_id, posted_date, source_no, transaction_code, amount,  c_id, c_ts, m_st)
VALUES (nextval('SQ_AC_ACCT_TRSN'),
  currval('SQ_AC_ACCT'),
  (SELECT ID
   FROM AC_CHRG_CODE
   WHERE CODE = 'TMGSEB-MBA-00-H79322'),
  (SELECT ID
   FROM AC_ACDM_SESN
   WHERE CODE = '201720181'),
  CURRENT_DATE,
  'INVC1238',
  0,
  1000.00,
  1, CURRENT_TIMESTAMP, 1
);


INSERT INTO ac_acct_trsn (id, account_id, charge_code_id, session_id, posted_date, source_no, transaction_code, amount,  c_id, c_ts, m_st)
VALUES (nextval('SQ_AC_ACCT_TRSN'),
  currval('SQ_AC_ACCT'),
  (SELECT ID
   FROM AC_CHRG_CODE
   WHERE CODE = 'TMGSEB-MBA-00-H79322'),
  (SELECT ID
   FROM AC_ACDM_SESN
   WHERE CODE = '201720181'),
  CURRENT_DATE,
  'INVC1238',
  0,
  1000.00,
  1, CURRENT_TIMESTAMP, 1
);


INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
VALUES (nextval('SQ_AC_ACCT'),
        (SELECT ID
         FROM AC_ACTR
         WHERE email = 'student2@umk.edu.my'),
        (SELECT IDENTITY_NO
         FROM AC_ACTR
         WHERE email = 'student2@umk.edu.my'),
        (SELECT NAME
         FROM AC_ACTR
         WHERE email = 'student2@umk.edu.my'),
        1, CURRENT_TIMESTAMP, 1);

