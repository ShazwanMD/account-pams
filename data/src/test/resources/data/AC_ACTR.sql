INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), '760607145591', 'STUDENT ONE', 'student1@umk.edu.my', NULL, NULL, NULL, 1,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_STDN (ID) VALUES (currval('SQ_AC_ACTR'));

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'student1@umk.edu.my';

INSERT INTO AC_ACTR (ID, IDENTITY_NO, NAME, EMAIL, PHONE, MOBILE, FAX, ACTOR_TYPE, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_AC_ACTR'), '860607145592', 'STUDENT TWO', 'student2@umk.edu.my', NULL, NULL, NULL, 1,
                          CURRENT_TIMESTAMP, 1, 1);

INSERT INTO AC_STDN (ID) VALUES (currval('SQ_AC_ACTR'));

UPDATE AC_USER
SET actor_id = currval('SQ_AC_ACTR')
WHERE email = 'student2@umk.edu.my';
