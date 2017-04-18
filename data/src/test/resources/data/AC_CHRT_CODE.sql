INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/PHD/0001'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/PHD/0001'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/PHD/0001'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/PHD/0002'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/PHD/0002'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/PHD/0002'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/PHD/0003'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/PHD/0003'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/PHD/0003'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/PHD/0004'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/PHD/0004'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/PHD/0004'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/MASTER/0001'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/MASTER/0001'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/MASTER/0001'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/MASTER/0002'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/MASTER/0002'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/MASTER/0002'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/MASTER/0003'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/MASTER/0003'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/MASTER/0003'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/MASTER/0004'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/MASTER/0004'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FIAT/MASTER/0004'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0001'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0001'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0001'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0002'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0002'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0002'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0003'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0003'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0003'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0004'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0004'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0004'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0005'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0005'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0005'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0006'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0006'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/PHD/0006'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0001'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0001'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0001'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0002'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0002'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0002'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0003'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0003'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0003'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0004'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0004'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0004'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0005'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0005'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0005'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0006'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0006'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FSB/MASTER/0006'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0001'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0001'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0001'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0002'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0002'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0002'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0003'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0003'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0003'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0004'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0004'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0004'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0005'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0005'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0005'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0006'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0006'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/PHD/0006'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0001'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0001'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0001'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0002'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0002'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0002'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0003'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0003'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0003'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0004'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0004'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0004'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0005'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0005'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0005'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0006'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0006'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FPV/MASTER/0006'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0001'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0001'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0001'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0002'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0002'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0002'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0003'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0003'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0003'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0004'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0004'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0004'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0005'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0005'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0005'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0006'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0006'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0006'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0007'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0007'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/PHD/0007'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0001'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0001'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0001'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0002'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0002'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0002'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0003'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0003'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0003'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0004'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0004'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0004'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0005'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0005'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0005'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0006'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0006'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0006'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0007'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0007'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'PBI/MASTER/0007'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0001'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0001'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0001'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0002'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0002'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0002'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0003'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0003'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0003'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0004'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0004'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0004'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0005'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0005'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/PHD/0005'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0001'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0001'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0001'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0002'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0002'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0002'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0003'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0003'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0003'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0004'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0004'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0004'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0005'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0005'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FTKW/MASTER/0005'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0001'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0001'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0001'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0002'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0002'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0002'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0003'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0003'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0003'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0004'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0004'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0004'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0005'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0005'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0005'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0006'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0006'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0006'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0007'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0007'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0007'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0008'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0008'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/PHD/0008'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0001'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0001'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0001'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0002'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0002'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0002'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0003'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0003'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0003'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0004'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0004'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0004'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0005'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0005'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0005'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0006'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0006'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0006'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0007'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0007'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0007'),
    CURRENT_TIMESTAMP, 0, 1);
INSERT INTO AC_CHRT_CODE (ID, PROGRAM_CODE_ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)    VALUES (nextval('SQ_AC_CHRT_CODE'),    (SELECT ID
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0008'),
   (SELECT CODE  || '/CHRT/201720181'
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0008'),
   (SELECT 'COHORT' || DESCRIPTION
    FROM AC_PRGM_CODE PC
    WHERE PC.CODE = 'FKP/MASTER/0008'),
    CURRENT_TIMESTAMP, 0, 1);
