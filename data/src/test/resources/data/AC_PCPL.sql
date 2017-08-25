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
                                                                                 
--ADMIN BENDAHARI
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_ADM_BEND', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_ADM'), 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_ADM'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_ADM_BEND'), 1, 1, CURRENT_TIMESTAMP); 
                                                                                 
                                                                                
-- KERANI ADMIN BENDAHARI
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_KRN_ADM_BEND', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_KRN_ADM_BEND'), 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_ADM_BEND'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_KRN_ADM_BEND'), 1, 1, CURRENT_TIMESTAMP);
                                                                                 
-- PEGAWAI ADMIN BENDAHARI                                                                                 
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_PGW_ADM_BEND', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PGW_ADM_BEND'), 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_ADM_BEND'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PGW_ADM_BEND'), 1, 1, CURRENT_TIMESTAMP);                                                                                 
                                                                                                                                                                  
-- HO ADMIN BENDAHARI                                                                               
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_HO_ADM_BEND', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_HO_ADM_BEND'), 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_ADM_BEND'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_HO_ADM_BEND'), 1, 1, CURRENT_TIMESTAMP);      
                                                                                 
-- AHO ADMIN BENDAHARI                                                                                
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_AHO_ADM_BEND', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_AHO_ADM_BEND'), 0, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_ADM_BEND'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_AHO_ADM_BEND'), 1, 1, CURRENT_TIMESTAMP);                                                                                    
                                                                                 
                                                                                 
-- MANAGEMENT
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_MGT', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_MGT'), 5, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_MGT'), 1, 1, CURRENT_TIMESTAMP);	         
                                                                                 
                                                                                 
-- STUDENT
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_STDN', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_STDN'), 4, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_STDN'), 1, 1, CURRENT_TIMESTAMP);	 
                                                                                 
 
                                                                                 
-- SPONSOR
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_SPNSR', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_SPNSR'), 6, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_SPNSR'), 1, 1, CURRENT_TIMESTAMP);	
                                                                                 
                                                                                 
-- PTJ
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_PTJ', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_USR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ'), 1, 1, CURRENT_TIMESTAMP);
                                                                                 
 
-- PTJ (SEC)
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_PTJ_SEC', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_SEC'), 1, 1, CURRENT_TIMESTAMP);                                                                                 
                                                                                 
-- KERANI PTJ SEC
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_KRN_PTJ_SEC', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_KRN_PTJ_SEC'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_SEC'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_KRN_PTJ_SEC'), 1, 1, CURRENT_TIMESTAMP);

-- PEGAWAI PTJ SEC
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_PGW_PTJ_SEC', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PGW_PTJ_SEC'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_SEC'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PGW_PTJ_SEC'), 1, 1, CURRENT_TIMESTAMP);

-- HO PTJ SEC
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_HO_PTJ_SEC', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_HO_PTJ_SEC'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_SEC'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_HO_PTJ_SEC'), 1, 1, CURRENT_TIMESTAMP);

-- AHO PTJ SEC
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_AHO_PTJ_SEC', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_AHO_PTJ_SEC'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_SEC'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_AHO_PTJ_SEC'), 1, 1, CURRENT_TIMESTAMP);                                                                                 
																				                                                                                  

-- PTJ (MGSEB)
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_PTJ_MGSEB', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_MGSEB'), 1, 1, CURRENT_TIMESTAMP);                                                                                 
                                                                                 
-- KERANI PTJ MGSEB
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_KRN_PTJ_MGSEB', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_KRN_PTJ_MGSEB'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_MGSEB'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_KRN_PTJ_MGSEB'), 1, 1, CURRENT_TIMESTAMP);

-- PEGAWAI PTJ MGSEB
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_PGW_PTJ_MGSEB', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PGW_PTJ_MGSEB'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_MGSEB'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PGW_PTJ_MGSEB'), 1, 1, CURRENT_TIMESTAMP);

-- HO PTJ MGSEB
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_HO_PTJ_MGSEB', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_HO_PTJ_MGSEB'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_MGSEB'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_HO_PTJ_MGSEB'), 1, 1, CURRENT_TIMESTAMP);

-- AHO PTJ MGSEB
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_AHO_PTJ_MGSEB', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_AHO_PTJ_MGSEB'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_MGSEB'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_AHO_PTJ_MGSEB'), 1, 1, CURRENT_TIMESTAMP);     
                                                                                 
                                                                                 
-- PTJ (CPS)
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_PTJ_CPS', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_CPS'), 1, 1, CURRENT_TIMESTAMP);                                                                                 
                                                                                 
-- KERANI PTJ CPS
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_KRN_PTJ_CPS', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_KRN_PTJ_CPS'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_CPS'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_KRN_PTJ_CPS'), 1, 1, CURRENT_TIMESTAMP);

-- PEGAWAI PTJ CPS
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_PGW_PTJ_CPS', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PGW_PTJ_CPS'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_CPS'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PGW_PTJ_CPS'), 1, 1, CURRENT_TIMESTAMP);

-- HO PTJ CPS
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_HO_PTJ_CPS', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_HO_PTJ_CPS'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_CPS'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_HO_PTJ_CPS'), 1, 1, CURRENT_TIMESTAMP);

-- AHO PTJ CPS
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_AHO_PTJ_CPS', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_AHO_PTJ_CPS'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_CPS'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_AHO_PTJ_CPS'), 1, 1, CURRENT_TIMESTAMP);                                                                                    
 
-- PTJ (HEP)
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_PTJ_HEP', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_HEP'), 1, 1, CURRENT_TIMESTAMP);                                                                                 
                                                                                 
-- KERANI PTJ HEP
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_KRN_PTJ_HEP', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_KRN_PTJ_HEP'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_HEP'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_KRN_PTJ_HEP'), 1, 1, CURRENT_TIMESTAMP);

-- PEGAWAI PTJ HEP
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_PGW_PTJ_HEP', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PGW_PTJ_HEP'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_HEP'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PGW_PTJ_HEP'), 1, 1, CURRENT_TIMESTAMP);

-- HO PTJ HEP
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_HO_PTJ_HEP', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_HO_PTJ_HEP'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_HEP'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_HO_PTJ_HEP'), 1, 1, CURRENT_TIMESTAMP);

-- AHO PTJ HEP
INSERT INTO AC_PCPL (ID, NAME, ENABLED, LOCKED, PRINCIPAL_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL'), 'GRP_AHO_PTJ_HEP', TRUE, TRUE, 1, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP (ID) VALUES (currval('SQ_AC_PCPL'));
INSERT INTO AC_PCPL_ROLE (ID, PRINCIPAL_ID, ROLE_TYPE, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_PCPL_ROLE'), (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_AHO_PTJ_HEP'), 3, 1, 1, CURRENT_TIMESTAMP);
INSERT INTO AC_GROP_MMBR (ID, GROUP_ID, PRINCIPAL_ID, M_ST, C_ID, C_TS) VALUES (nextval('SQ_AC_GROP_MMBR'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_PTJ_HEP'),
                                                                                 (SELECT ID FROM AC_PCPL WHERE NAME = 'GRP_AHO_PTJ_HEP'), 1, 1, CURRENT_TIMESTAMP);                                                                                    
                                                                                                                                                                  
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
                                                                                 
---------------------------------------------------------
-- GROUP END
---------------------------------------------------------

