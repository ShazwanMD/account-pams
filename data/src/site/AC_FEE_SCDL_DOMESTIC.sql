INSERT INTO AC_FEE_SCDL (ID,RESIDENCY_CODE_ID, COHORT_CODE_ID, STUDY_MODE_ID, CODE, DESCRIPTION, TOTAL_AMOUNT, M_ST, C_TS,C_ID) VALUES (
nextval('SQ_AC_FEE_SCDL'),(SELECT ID from AC_RSCY_CODE where code ='B' ),(SELECT ID FROM AC_CHRT_CODE WHERE CODE = 'FKP-PHD-0001-CHRT-201720181' ),(SELECT ID FROM AC_STDY_MODE WHERE CODE = '1' ),'YB-FKP-PHD-0001-CHRT-201720181','YURAN PENGAJIAN  PROGRAM SARJANA KEUSAHAWANAN & SARJANA SASTERA FKP SEPENUH MASA',0.00,1,CURRENT_TIMESTAMP,1); 




INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Pendaftaran & Kad Matrik',1,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79321'),'170.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Bench Fee',1,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79336'),'2000.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ',1,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79329'),'600.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'2)Yuran Research Methodology ',1,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79333'),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa',1,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79332'),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan',1,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79324'),'200.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan',1,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79325'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan',1,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79326'),'100.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ',1,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79327'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan',1,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79328'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar',1,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79335'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ',1,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'3270.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian',2,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79329'),'600.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'2)Yuran Research Methodology ',2,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79333'),'900.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'3)Yuran  Advanced Statistics ',2,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79334'),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa',2,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79332'),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan',2,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79324'),'200.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan',2,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79325'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan',2,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79326'),'100.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ',2,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79327'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan',2,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79328'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar',2,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79335'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ',2,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'2000.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ',3,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79329'),'600.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'2)Yuran  Advanced Statistics ',3,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79334'),'900.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa',3,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79332'),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan',3,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79324'),'200.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan',3,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79325'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan',3,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79326'),'100.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ',3,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79327'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan',3,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79328'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar',3,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79335'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ',3,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'2000.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ',4,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79329'),'600.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'2)Yuran  Advanced Statistics ',4,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79334'),'900.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa',4,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79332'),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan',4,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79324'),'200.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan',4,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79325'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan',4,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79326'),'100.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ',4,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79327'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan',4,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79328'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar',4,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79335'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ',4,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'2000.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ',5,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79329'),'600.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa',5,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79332'),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan',5,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79324'),'200.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan',5,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79325'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan',5,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79326'),'100.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ',5,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79327'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan',5,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79328'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar',5,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79335'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ',5,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'1100.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ',6,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79329'),'600.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa',6,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79332'),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan',6,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79324'),'200.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan',6,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79325'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan',6,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79326'),'100.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ',6,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79327'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan',6,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79328'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar',6,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = 'TABPPS-PCA-00-H79335'),'50.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ',6,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'1100.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ',7,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa',7,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan',7,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan',7,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan',7,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ',7,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan',7,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar',7,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ',7,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ',8,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa',8,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan',8,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan',8,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan',8,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ',8,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan',8,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar',8,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ',8,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ',9,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa',9,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan',9,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan',9,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan',9,currval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRG_CODE WHERE CODE = ''),'0.0',CURRENT_TIMESTAMP,1,1);

INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) 
 VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ',9,currval('SQ_AC