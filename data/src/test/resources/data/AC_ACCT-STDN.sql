-- account for student

INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where email = 'student1@umk.edu.my'),
            (select IDENTITY_NO from AC_ACTR where email = 'student1@umk.edu.my'),
            (select NAME from AC_ACTR where email = 'student1@umk.edu.my'),
            1, CURRENT_TIMESTAMP, 1);

INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where email = 'student2@umk.edu.my'),
            (select IDENTITY_NO from AC_ACTR where email = 'student2@umk.edu.my'),
            (select NAME from AC_ACTR where email = 'student2@umk.edu.my'),
            1, CURRENT_TIMESTAMP, 1);

