INSERT INTO AC_FEE_SCDL (ID, COHORT_CODE_ID, STUDY_MODE_ID, CODE, DESCRIPTION, TOTAL_AMOUNT, M_ST, C_TS,C_ID) VALUES (
nextval('SQ_AC_FEE_SCDL'),(SELECT ID FROM AC_CHRT_CODE WHERE CODE = 'FKP-PHD-0001-CHRT-201720181' ),(SELECT ID FROM AC_STDY_MODE WHERE CODE = '1' ),'YB-FKP-PHD-0001-CHRT-201720181','YURAN PENGAJIAN  PROGRAM SARJANA KEUSAHAWANAN & SARJANA SASTERA FKP SEPENUH MASA',0.00,1,CURRENT_TIMESTAMP,1); 




INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'YURAN TIDAK BERULANG ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Pendaftaran & Kad Matrik','170.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Bench Fee','2000.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'YURAN BERULANG ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ','600.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'2)Yuran Research Methodology ','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan','200.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan','100.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'YURAN SEMESTER 2 ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian','600.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'2)Yuran Research Methodology ','900.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'3)Yuran  Advanced Statistics ','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan','200.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan','100.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'YURAN SEMESTER 3','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ','600.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'2)Yuran  Advanced Statistics ','900.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan','200.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan','100.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'YURAN SEMESTER 4','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ','600.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'2)Yuran  Advanced Statistics ','900.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan','200.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan','100.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'YURAN SEMESTER 5','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ','600.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan','200.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan','100.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'YURAN SEMESTER 6','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ','600.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan','200.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan','100.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar','50.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'YURAN SEMESTER 7','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'YURAN SEMESTER 8','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'YURAN SEMESTER 9','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'YURAN SEMESTER 10','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'1)Yuran Pengajian ','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Yuran Khas Pelajar Antarabangsa','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perkhidmatan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kesihatan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Perpustakaan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Insuran Berkelompok ','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kemudahan Komputer / Peralatan','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Kelab Pelajar','-');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Jumlah ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'YURAN PEPERIKSAAN TESIS ','');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),' PEPERIKSAAN TESIS SARJANA ','750.0');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Konvokesyen','*350');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'Alumni','*100');
INSERT INTO AC_FEE_SCDL_ITEM (ID, SCHEDULE_ID, AMOUNT ) VALUES (nextval('SQ_AC_FEE_SCDL_ITEM'),'JUMLAH KESELURUHAN (MINIMUM)','6020.0');
