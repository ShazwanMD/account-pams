INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), 'A17P001', 'STUDENT ONE', 'student1@umk.edu.my', NULL, NULL, NULL, 1,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_STDN (ID, COHORT_CODE_ID) VALUES (currval('SQ_AC_ACTR'),
(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0001-CHRT-201720181'));

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'student1@umk.edu.my';

INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), 'A17P002', 'STUDENT TWO', 'student2@umk.edu.my', NULL, NULL, NULL, 1,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_STDN (ID, COHORT_CODE_ID) VALUES (currval('SQ_AC_ACTR'),
(SELECT ID from AC_CHRT_CODE where code = 'FIAT-PHD-0001-CHRT-201720181'));

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'student2@umk.edu.my';

-- sponsor
-- self
INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), 'SELF', 'SENDIRI', 'admin@umk.edu.my', NULL, NULL, NULL, 2,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_SPSR (ID, SPONSOR_TYPE) VALUES (currval('SQ_AC_ACTR'), 1);
-- jpa
INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), 'JPA', 'Jabatan Perkhidmatan Awam', 'sponsorship@jpa.gov.my', NULL, NULL, NULL, 2,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_SPSR (ID, SPONSOR_TYPE) VALUES (currval('SQ_AC_ACTR'), 1);

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'sponsorship@jpa.gov.my';

-- ptptn
INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), 'PTPTPN', 'Pinjaman Pendidikan', 'sponsorship@jpa.gov.my', NULL, NULL, NULL, 2,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_SPSR (ID, SPONSOR_TYPE) VALUES (currval('SQ_AC_ACTR'), 2);

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'sponsorship@jpa.gov.my';


