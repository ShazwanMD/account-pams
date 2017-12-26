-- student 1
INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), 'A17P001', 'MUHAMMAD AMMAR FAIZ BIN AZMAN', 'ammar@umk.edu.my', '097111771', '0123450000', NULL, 1,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_STDN (ID, COHORT_CODE_ID, RESIDENCY_CODE_ID) VALUES (
currval('SQ_AC_ACTR'),
(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0001-CHRT-201720181'),
(SELECT ID from AC_RSCY_CODE where code = 'P'));

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'ammar@umk.edu.my';

-- student 2
INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), 'A17P002', 'MUHAMMAD NAZIR BIN HAZZAN', 'nazir.h@umk.edu.my', '097111772', '0123450001', NULL, 1,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_STDN (ID, COHORT_CODE_ID, RESIDENCY_CODE_ID) VALUES (
currval('SQ_AC_ACTR'),
(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0001-CHRT-201720181'),
(SELECT ID from AC_RSCY_CODE where code = 'P'));

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'nazir.h@umk.edu.my';

-- student 3
INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), 'A17P003', 'NUR NASIBAH BINTI KAMIL', 'nasibahk@umk.edu.my', '097111773', '0123450002', NULL, 1,
                          CURRENT_TIMESTAMP, 1, 1);
                          
INSERT INTO AC_STDN (ID, COHORT_CODE_ID, RESIDENCY_CODE_ID) VALUES (
currval('SQ_AC_ACTR'),
(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0001-CHRT-201720181'),
(SELECT ID from AC_RSCY_CODE where code = '2'));

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'nasibahk@umk.edu.my';

-- student 4
INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), 'A17P004', 'FATIMAH AZZAHERA BINTI SABRIY', 'fatimahzahera@umk.edu.my', '097111774', '0123450003', NULL, 1,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_STDN (ID, COHORT_CODE_ID, RESIDENCY_CODE_ID) VALUES (
currval('SQ_AC_ACTR'),
(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0002-CHRT-201720181'),
(SELECT ID from AC_RSCY_CODE where code = '2'));

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'fatimahzahera@umk.edu.my';

-- student 5
INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), 'A17P005', 'AZZAH BINTI YOUSOF', 'azzahyousof@umk.edu.my', '097111775', '0123450004', NULL, 1,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_STDN (ID, COHORT_CODE_ID, RESIDENCY_CODE_ID) VALUES (
currval('SQ_AC_ACTR'),
(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0002-CHRT-201720181'),
(SELECT ID from AC_RSCY_CODE where code = 'P'));

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'azzahyousof@umk.edu.my';


-- student 6
INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), 'A17P006', 'AMINAH BINTI WAHAB', 'aminahwahab@umk.edu.my', '097111775', '0123450004', NULL, 1,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_STDN (ID, COHORT_CODE_ID, RESIDENCY_CODE_ID) VALUES (
currval('SQ_AC_ACTR'),
(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0003-CHRT-201720181'),
(SELECT ID from AC_RSCY_CODE where code = '2'));

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'aminahwahab@umk.edu.my';


-- student 7
INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), 'A17P007', 'DAMIYA MAISARAH BINTI ZAINI', 'damiazaini@umk.edu.my', '097111775', '0123450004', NULL, 1,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_STDN (ID, COHORT_CODE_ID, RESIDENCY_CODE_ID) VALUES (
currval('SQ_AC_ACTR'),
(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0004-CHRT-201720181'),
(SELECT ID from AC_RSCY_CODE where code = 'P'));

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'damiazaini@umk.edu.my';

-- student 8
INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), 'A17P008', 'NUR ASYIKIN BINTI JAMMAL', 'asyikinjamal@umk.edu.my', '097111775', '0123450004', NULL, 1,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_STDN (ID, COHORT_CODE_ID, RESIDENCY_CODE_ID) VALUES (
currval('SQ_AC_ACTR'),
(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0004-CHRT-201720181'),
(SELECT ID from AC_RSCY_CODE where code = '2'));

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'asyikinjamal@umk.edu.my';

---- sponsor
---- self
----INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
----VALUES
----  (nextval('SQ_AC_ACTR'), 'SELF', 'SENDIRI', 'admin@umk.edu.my', NULL, NULL, NULL, 2,
----                          CURRENT_TIMESTAMP, 1, 1);
----
----INSERT INTO AC_SPSR (ID, SPONSOR_TYPE) VALUES (currval('SQ_AC_ACTR'), 1);
------ jpa
----INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
----VALUES
----  (nextval('SQ_AC_ACTR'), 'JPA', 'Jabatan Perkhidmatan Awam', 'sponsorship@jpa.gov.my', NULL, NULL, NULL, 2,
----                          CURRENT_TIMESTAMP, 1, 1);
----
----INSERT INTO AC_SPSR (ID, SPONSOR_TYPE) VALUES (currval('SQ_AC_ACTR'), 1);
----
----UPDATE AC_USER
----SET actor_id = currval('SQ_AC_ACTR')
----WHERE email = 'sponsorship@jpa.gov.my';
----
------ ptptn
----INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
----VALUES
----  (nextval('SQ_AC_ACTR'), 'PTPTPN', 'Pinjaman Pendidikan', 'sponsorship@jpa.gov.my', NULL, NULL, NULL, 2,
----                          CURRENT_TIMESTAMP, 1, 1);
----
----INSERT INTO AC_SPSR (ID, SPONSOR_TYPE) VALUES (currval('SQ_AC_ACTR'), 2);
----
----UPDATE AC_USER
----SET actor_id = currval('SQ_AC_ACTR')
----WHERE email = 'sponsorship@jpa.gov.my';
--
--
