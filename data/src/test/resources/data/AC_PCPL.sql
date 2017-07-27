-- USR, PGW, KRN BDR
-- USER, PEGAWAI, KERANI, BENDAHARI
-- abc123 = 6367c48dd193d56ea7b0baad25b19455e529f5ee

INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'root', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_AC_PCPL'), 'Root', 'root@umk.edu.my', 'abc123');

INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'admin', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_AC_PCPL'), 'PAMS Admin', 'admin@umk.edu.my', 'abc123');

INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'system', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_AC_PCPL'), 'PAMS System', 'system@umk.edu.my', 'abc123');

INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'bursary', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_AC_PCPL'), 'PAMS Bursary', 'bursary@umk.edu.my', 'abc123');

INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'cps', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_AC_PCPL'), 'PAMS CPS', 'cps@umk.edu.my', 'abc123');

INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'student1', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_AC_PCPL'), 'PAMS Student1', 'student1@umk.edu.my', 'abc123');

INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'student2', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_AC_PCPL'), 'PAMS Student2', 'student2@umk.edu.my', 'abc123');

INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'jpa', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_AC_PCPL'), 'jpa', 'sponsorship@jpa.gov.my', 'abc123');

INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'ptptn', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_AC_PCPL'), 'ptptn', 'sponsorship@ptptn.gov.my', 'abc123');

INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'security', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_AC_PCPL'), 'Security', 'security@umk.edu.my', 'abc123');

INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'studentaffair', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_AC_PCPL'), 'Student Affair', 'studentaffair@umk.edu.my', 'abc123');

INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'mgseb', TRUE, TRUE, 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_USER (ID, REAL_NAME, EMAIL, PASSWORD)
VALUES (currval('SQ_AC_PCPL'), 'MGSEB', 'mgseb@umk.edu.my', 'abc123');

---------------------------------------------------------
-- GROUP START
---------------------------------------------------------

-- user@ROOT
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_USR', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_USR'), 1, 1, 1, CURRENT_TIMESTAMP);

-- ADMIN
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_ADM', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_ADM'), 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_ADM'),
                                                                                (SELECT ID FROM AC_PCPL WHERE NAME = 'root'), 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_ADM'),
                                                                                (SELECT ID FROM AC_PCPL WHERE NAME = 'bursary'), 1, 1, CURRENT_TIMESTAMP);



INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_ADM'), 1, 1, CURRENT_TIMESTAMP);

---------------------------------------------------------
-- GROUP END
---------------------------------------------------------

