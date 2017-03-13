INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where email = 'student1@gmail.com'),
            'AC-' || currval('SQ_AC_ACCT'), 'student1',
            1, CURRENT_TIMESTAMP, 1);

INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
values (nextval('SQ_AC_ACCT'),
        (select ID from AC_ACTR where email = 'student2@gmail.com'),
        'AC-' || currval('SQ_AC_ACCT'), 'student2',
        1, CURRENT_TIMESTAMP, 1);