DROP SEQUENCE SQ_AC_BANK_CODE;
CREATE SEQUENCE SQ_AC_BANK_CODE START WITH 1;

INSERT INTO AC_BANK_CODE (ID, CODE,IBG_CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (nextval('SQ_AC_BANK_CODE'), 'PHBMMYKL','IBG', 'AFFIN BANK BERHAD', CURRENT_TIMESTAMP, 1, 'PHBMMYKL');
INSERT INTO AC_BANK_CODE (ID, CODE,IBG_CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (nextval('SQ_AC_BANK_CODE'), 'AIBBMYKL', 'AFFIN ISLAMIC BANK BERHAD', CURRENT_TIMESTAMP, 1, 'AIBBMYKL') ;

INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'AIBBMYKL', 'AFFIN ISLAMIC BANK BERHAD', CURRENT_TIMESTAMP, 1, 'AIBBMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'MFBBMYKL', 'ALLIANCE BANK MALAYSIA BERHAD', CURRENT_TIMESTAMP, 1, 'MFBBMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'ALSRMYK1', 'ALLIANCE ISLAMIC BANK MALAYSIA BERHAD', CURRENT_TIMESTAMP, 1, 'ALSRMYK1') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'RJHIMYKL', 'AL-RAJHI BANK (M) BERHAD', CURRENT_TIMESTAMP, 1, 'RJHIMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'ARBKMYKL', 'AMBANK (M) BERHAD', CURRENT_TIMESTAMP, 1, 'ARBKMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'AISLMYKL', 'AMISLAMIC BANK BERHAD', CURRENT_TIMESTAMP, 1, 'AISLMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'BIMBMYKL', 'BANK ISLAM MALAYSIA BERHAD', CURRENT_TIMESTAMP, 1, 'BIMBMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'BKRMMYKL', 'BANK KERJASAMA RAKYAT (M) BERHAD', CURRENT_TIMESTAMP, 1, 'BKRMMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'BMMBMYKL', 'BANK MUAMALAT MALAYSIA BERHAD', CURRENT_TIMESTAMP, 1, 'BMMBMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'BOFAMY2X', 'BANK OF AMERICA (M) BERHAD', CURRENT_TIMESTAMP, 1, 'BOFAMY2X') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'BKCHMYKL', 'BANK OF CHINA (MALAYSIA) BERHAD', CURRENT_TIMESTAMP, 1, 'BKCHMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'BOTKMYKX', 'BANK OF TOKYO-MITSUBISHI UFJ (M) BERHAD', CURRENT_TIMESTAMP, 1, 'BOTKMYKX') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'AGOBMYK1', 'BANK PERTANIAN MALAYSIA BERHAD (AGROBANK)', CURRENT_TIMESTAMP, 1, 'AGOBMYK1') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'BSNAMYK1', 'BANK SIMPANAN NASIONAL BERHAD', CURRENT_TIMESTAMP, 1, 'BSNAMYK1') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'CIBBMYKL', 'CIMB BANK BERHAD', CURRENT_TIMESTAMP, 1, 'CIBBMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'CTBBMYKL', 'CIMB ISLAMIC BANK BERHAD', CURRENT_TIMESTAMP, 1, 'CTBBMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'CITIMYKL', 'CITIBANK BERHAD', CURRENT_TIMESTAMP, 1, 'CITIMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'DEUTMYKL', 'DEUTSCHE BANK (M) BERHAD', CURRENT_TIMESTAMP, 1, 'DEUTMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'HLBBMYKL', 'HONG LEONG BANK BERHAD', CURRENT_TIMESTAMP, 1, 'HLBBMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'HLIBMYKL', 'HONG LEONG ISLAMIC BANK BERHAD', CURRENT_TIMESTAMP, 1, 'HLIBMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'HMABMYKL', 'HSBC AMANAH MALAYSIA BERHAD', CURRENT_TIMESTAMP, 1, 'HMABMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'HBMBMYKL', 'HSBC BANK MALAYSIA BERHAD', CURRENT_TIMESTAMP, 1, 'HBMBMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'CHASMYKX', 'J.P MORGAN CHASE BANK BERHAD', CURRENT_TIMESTAMP, 1, 'CHASMYKX') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'KFHOMYKL', 'KUWAIT FINANCE HOUSE (M) BERHAD', CURRENT_TIMESTAMP, 1, 'KFHOMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'MBBEMYKL', 'MALAYAN BANKING BERHAD', CURRENT_TIMESTAMP, 1, 'MBBEMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'MBISMYKL', 'MAYBANK ISLAMIC BERHAD', CURRENT_TIMESTAMP, 1, 'MBISMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'OABBMYKL', 'OCBC AL-AMIN BANK BERHAD', CURRENT_TIMESTAMP, 1, 'OABBMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'OCBCMYKL', 'OCBC BANK (M) BERHAD', CURRENT_TIMESTAMP, 1, 'OCBCMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'PBBEMYKL', 'PUBLIC BANK BERHAD', CURRENT_TIMESTAMP, 1, 'PBBEMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'PIBEMYK1', 'PUBLIC ISLAMIC BANK BERHAD', CURRENT_TIMESTAMP, 1, 'PIBEMYK1') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'RHBBMYKL', 'RHB BANK BERHAD', CURRENT_TIMESTAMP, 1, 'RHBBMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'RHBAMYKL', 'RHB ISLAMIC BANK BERHAD', CURRENT_TIMESTAMP, 1, 'RHBAMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'SCBLMYKX', 'STANDARD CHARTERED BANK (M) BERHAD', CURRENT_TIMESTAMP, 1, 'SCBLMYKX') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'SCSRMYK1', 'STANDARD CHARTERED SAADIQ BERHAD', CURRENT_TIMESTAMP, 1, 'SCSRMYK1') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'SMBCMYKL', 'SUMITOMO MITSUI BANKING CORPORATION (M) BERHAD', CURRENT_TIMESTAMP, 1, 'SMBCMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'ABNAMYKL', 'THE ROYAL BANK OF SCOTLAND BERHAD', CURRENT_TIMESTAMP, 1, 'ABNAMYKL') ;
INSERT INTO AC_BANK_CODE (ID, CODE, NAME, C_TS, M_ST, SWIFT_CODE) VALUES (SQ_AC_BANK_CODE.NEXTVAL, 'UOVBMYKL', 'UNITED OVERSEAS BANK (M) BERHAD', CURRENT_TIMESTAMP, 1, 'UOVBMYKL') ;