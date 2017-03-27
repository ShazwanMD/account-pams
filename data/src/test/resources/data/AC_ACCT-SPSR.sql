INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'KPM-MYMASTER'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'KPM-MYMASTER'),             ' KEMENTERIAN PELAJARAN TINGGI MYMASTER',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'KPM-MYPHD'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'KPM-MYPHD'),             ' KEMENTERIAN PELAJARAN TINGGI MYMPHD',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'HLP'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'HLP'),             ' HADIAH LATIHAN PERSEKUTUAN',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'MTDC'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'MTDC'),             ' MTDC',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'SLAI'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'SLAI'),             ' SKIM LATIHAN AKADEMIK IPTA',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'SLAB'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'SLAB'),             ' SKIM LATIHAN AKADEMIK BUMIPUTERA',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'MARA'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'MARA'),             ' MAJLIS AMANAH RAKYAT',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'TATIUC'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'TATIUC'),             ' TATI UNIVERSITY COLLEGE',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'RISDA'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'RISDA'),             ' KEMENTERIAN KEMAJUAN LUAR BANDAR DAN WILAYAH',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'KPM-YSSM'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'KPM-YSSM'),             ' KPM YSSM',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'YS'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'YS'),             ' YAYASAN SARAWAK',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'PNS'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'PNS'),             ' PNS',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'EMBASSY'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'EMBASSY'),             ' EMBASSY',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'PELADANG'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'PELADANG'),             ' PELADANG',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'MKM'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'MKM'),             ' MKM',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'MARDI'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'MARDI'),             ' MARDI',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'TAKAFUL'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'TAKAFUL'),             ' TAKAFUL',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'KIAS'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'KIAS'),             ' KIAS',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'YA'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'YA'),             ' YAYASAN ANGKASA',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'MIS'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'MIS'),             ' MIS',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'IDB'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'IDB'),             ' ISLAMIC DEVELOPMENT BANK',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'KPM-OKU'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'KPM-OKU'),             ' KPM OKU',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'MAYBANK'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'MAYBANK'),             ' MAYBANK BERHAD',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'UMK-STAF'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'UMK-STAF'),             ' UMK STAFF',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'UMK-ZAMALAH'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'UMK-ZAMALAH'),             ' UMK ZAMALAH',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'YNS'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'YNS'),             ' YAYASAN NEGERI SEMBILAN',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'YPJ'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'YPJ'),             ' YAYASAN PELAJARAN JOHOR',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'GTA'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'GTA'),             ' GRADUATE TEACHING ASSISTANT',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'GRA'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'GRA'),             ' GRADUATE RESEARCH ASSISTANT',
            1, CURRENT_TIMESTAMP, 1);
INSERT INTO AC_ACCT (ID, ACTOR_ID, CODE, DESCRIPTION, C_ID, C_TS, M_ST)
    values (nextval('SQ_AC_ACCT'),
            (select ID from AC_ACTR where identity_no = 'SIS'),
            (select IDENTITY_NO from AC_ACTR where identity_no = 'SIS'),             ' SIS',
            1, CURRENT_TIMESTAMP, 1);
