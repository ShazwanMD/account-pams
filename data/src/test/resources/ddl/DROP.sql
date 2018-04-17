-- TODO

DROP TABLE IF EXISTS bat_job_instance CASCADE ;
DROP TABLE IF EXISTS bat_job_execution CASCADE ;
DROP TABLE IF EXISTS bat_job_execution_context CASCADE ;
DROP TABLE IF EXISTS bat_job_execution_params CASCADE ;
DROP TABLE IF EXISTS bat_step_execution CASCADE ;
DROP TABLE IF EXISTS bat_step_execution_context CASCADE ;

 alter table AC_ACCT 
        drop constraint FK_874n8x3rs4gbxyyvcsisq9vwv; 
    alter table AC_ACCT_CHRG 
        drop constraint FK_hmi56t60b9ai51shufk9o63nw; 
    alter table AC_ACCT_CHRG 
        drop constraint FK_1pi4606wcpiir5b1fve6uh8c6; 
    alter table AC_ACCT_CHRG 
        drop constraint FK_bnblgrmw2hvfi9b4xwg6t1fam; 
    alter table AC_ACCT_CHRG 
        drop constraint FK_b64kwriwk4vyls17mcqwj7uka; 
    alter table AC_ACCT_CHRG 
        drop constraint FK_2wqjbqqd99e9jfdg57wsdh6ys; 
    alter table AC_ACCT_CHRG 
        drop constraint FK_6ugb2w9qui63g1rw8p7va6ibm; 
    alter table AC_ACCT_CHRG 
        drop constraint FK_cjojy4m3cr24g4hx7n23fnuge; 
    alter table AC_ACCT_CHRG 
        drop constraint FK_2yev1dn0dhdkc6dmdjcd9b0jt; 
    alter table AC_ACCT_CHRG_TRSN 
        drop constraint FK_f3u86h0lf0prikjung5k7vnjb; 
    alter table AC_ACCT_CHRG_TRSN 
        drop constraint FK_53gtciyn6phq76iywqdlgnees; 
    alter table AC_ACCT_CHRG_TRSN 
        drop constraint FK_yw1t2p3e74wk5frtc0amnf99; 
    alter table AC_ACCT_TRSN 
        drop constraint FK_7scem81vjyp92ew1quqqrwebj; 
    alter table AC_ACCT_TRSN 
        drop constraint FK_tmeqyaoy1umx2f8t50j3m05oe; 
    alter table AC_ACCT_TRSN 
        drop constraint FK_gok5lgsyud20w75frwjala98a; 
    alter table AC_ACCT_WAVR 
        drop constraint FK_7he1r0ld5dv236x5eu7yj4ktm; 
    alter table AC_ACCT_WAVR 
        drop constraint FK_5wf5loa65e073204ius4wpe3i; 
    alter table AC_ADVC_PYMT 
        drop constraint FK_mtp68skukh4by78nxjcu8ckok; 
    alter table AC_ADVC_PYMT 
        drop constraint FK_pp59klowywbpspd2d8rxhyijt; 
    alter table AC_ADVC_PYMT 
        drop constraint FK_9t91j02sig23ethl9u8niayai; 
    alter table AC_BSRY_RCPT 
        drop constraint FK_jc1lhth7un8oggnbjsew0mhs8; 
    alter table AC_CDIT_NOTE 
        drop constraint FK_kn1ige6q5d3um3d1y9l1vpsua; 
    alter table AC_CDIT_NOTE_ITEM 
        drop constraint FK_21e2oam0dhnb0qlcavpy8iheg; 
    alter table AC_CDIT_NOTE_ITEM 
        drop constraint FK_rhnoistpmgs6ikwsj2wrl9f8h; 
    alter table AC_CHRG_CODE 
        drop constraint FK_3j9nre46398unh5i1rwdx2eg; 
    alter table AC_CHRT_CODE 
        drop constraint FK_r6x0t8jgb661ibpbd7wsgibq; 
    alter table AC_CITY_CODE 
        drop constraint FK_mfu391fdemh19u1mhdby3yy4u; 
    alter table AC_CVRG 
        drop constraint FK_n995653hwah8dwqqfmc04aans; 
    alter table AC_CVRG 
        drop constraint FK_pb6bu58vuo9an2pn17qb2trlm; 
    alter table AC_DBIT_NOTE 
        drop constraint FK_b5s7as6etxcj3i06ro0orbovc; 
    alter table AC_DBIT_NOTE_ITEM 
        drop constraint FK_7jvhwik13ryr7liw2lun71t64; 
    alter table AC_DBIT_NOTE_ITEM 
        drop constraint FK_2h8k5x0vsyvlwesr0dkq3gfbo; 
    alter table AC_ELTC_RCPT 
        drop constraint FK_mi3q81i59xmlrn51q9pxlx71k; 
    alter table AC_FEE_SCDL 
        drop constraint FK_9bty3yjbg6enkxnmd3mc8g9ee; 
    alter table AC_FEE_SCDL 
        drop constraint FK_bkb7tplkos1ef7s4spyh0e56r; 
    alter table AC_FEE_SCDL 
        drop constraint FK_qmhucgsah18heo0kwud2htjbv; 
    alter table AC_FEE_SCDL 
        drop constraint FK_ge5avvbfwo8vj96fn826i6jta; 
    alter table AC_FEE_SCDL_ITEM 
        drop constraint FK_6pew9bxlby83rc5bifnwna9uq; 
    alter table AC_FEE_SCDL_ITEM 
        drop constraint FK_ekfmm6poyf27bgvfnvlyk8ad3; 
    alter table AC_GNRL_RCPT 
        drop constraint FK_tn50m44nrh32jegvbalyew9k6; 
    alter table AC_GRDN 
        drop constraint FK_lxn77f6qb53rrs3r81qqwwes7; 
    alter table AC_GROP 
        drop constraint FK_oyukdv0gy0cx7d1v0c9dky5wk; 
    alter table AC_GROP_MMBR 
        drop constraint FK_rfpjc48ul656ba82r4c6jynyu; 
    alter table AC_GROP_MMBR 
        drop constraint FK_770jh71hts8dap5ljwxtvgby0; 
    alter table AC_INVC 
        drop constraint FK_lqa5mht7w78noknfxwfeyopuq; 
    alter table AC_INVC 
        drop constraint FK_sk12qvuxady19d2vh9v75c52p; 
    alter table AC_INVC_ITEM 
        drop constraint FK_tl5yy4kfw6sbodu0jgbgo3ek8; 
    alter table AC_INVC_ITEM 
        drop constraint FK_g7nihxqxxqx22a3mjlwodfl43; 
    alter table AC_KNOF 
        drop constraint FK_omlq6461txohapdwxiqtgd316; 
    alter table AC_KNOF_CHRG 
        drop constraint FK_pmppmcg3vqrq6o0sg3flbrxrg; 
    alter table AC_KNOF_CHRG 
        drop constraint FK_tjix5e3uwv7wf2k0ugru675q6; 
    alter table AC_KNOF_DBT_NOTE 
        drop constraint FK_78y5esdtg1sh9sufj7m9cc03f; 
    alter table AC_KNOF_DBT_NOTE 
        drop constraint FK_sf0p92ff8eegoaf3oupp3q27f; 
    alter table AC_KNOF_INVC 
        drop constraint FK_hg7bkl3ie421se4kfwky76hfo; 
    alter table AC_KNOF_INVC 
        drop constraint FK_frca4d50lonwqyefl7w8qltpx; 
    alter table AC_KNOF_ITEM 
        drop constraint FK_cvb6l8r7mqsg6uwv42fox1sb3; 
    alter table AC_KNOF_ITEM 
        drop constraint FK_ihcbtdkqv3f788ok84ikf68vk; 
    alter table AC_KNOF_ITEM 
        drop constraint FK_9b8dq7a0lt5w6xw5hg7kvga1w; 
    alter table AC_KNOF_ITEM 
        drop constraint FK_lxa2hldcd6061vnf7bri9x5xx; 
    alter table AC_KNOF_ITEM 
        drop constraint FK_gipf8bfloah8esdpss1rf2cf; 
    alter table AC_PCPL_ROLE 
        drop constraint FK_ofdb3qau30ii76rhetdhgtn2r; 
    alter table AC_PRGM_CODE 
        drop constraint FK_985qolx3dscttpk3p5bj85yop; 
    alter table AC_PRGM_CODE 
        drop constraint FK_2wqhk3gvusbsolixlxbl6he2t; 
    alter table AC_PRGM_CODE 
        drop constraint FK_h1crddoet5n2bp5okx5q4x1xq; 
    alter table AC_PRMO_CODE_ITEM 
        drop constraint FK_72n2se3cskvb4lkn9ybw93650; 
    alter table AC_PRMO_CODE_ITEM 
        drop constraint FK_f3ko5d4k5ak8wf94q5wqvn9j4; 
    alter table AC_RCPT 
        drop constraint FK_fnxq30p92pj8aepx0smoaexos; 
    alter table AC_RCPT 
        drop constraint FK_qhr2bjjsvs73ja9rpvjei1pav; 
    alter table AC_RCPT_ACCT_CHRG 
        drop constraint FK_3seqkkr06puuid2q2vcv0pydc; 
    alter table AC_RCPT_ACCT_CHRG 
        drop constraint FK_a2c5yoeldso04qymw9ygot2og; 
    alter table AC_RCPT_CHRG_ITEM 
        drop constraint FK_l4xuhlp8ce1p4j3pmqhr5ona0; 
    alter table AC_RCPT_CHRG_ITEM 
        drop constraint FK_9fwdlu0w4xoxk6f2xvckei4fw; 
    alter table AC_RCPT_DBT 
        drop constraint FK_okdm6xo1wiyjrox1uno8adcnl; 
    alter table AC_RCPT_DBT 
        drop constraint FK_6bj5r5aoalbjtlo64782iu5w8; 
    alter table AC_RCPT_INVC 
        drop constraint FK_rpj1wg5pqyv2jpqmu6pm5g9pc; 
    alter table AC_RCPT_INVC 
        drop constraint FK_su460f3lvgsf3h4079asbbi3s; 
    alter table AC_RCPT_ITEM 
        drop constraint FK_6ob8yk033mimyyh4dbbb8k4v; 
    alter table AC_RCPT_ITEM 
        drop constraint FK_dtmemhqcipfpet4tyyrd9copt; 
    alter table AC_RCPT_ITEM 
        drop constraint FK_7vy8dvpqgshkjf3d47cq69gmo; 
    alter table AC_RCPT_ITEM 
        drop constraint FK_pvm4sa8mdj3fccu5i73wjl5e8; 
    alter table AC_RCPT_ITEM 
        drop constraint FK_369xjsjo5e6svl0ynmlab782g; 
    alter table AC_RFND_PYMT 
        drop constraint FK_6ubqbcujiftaq4ram8xpk5ok; 
    alter table AC_SCTY_CHRG_CODE 
        drop constraint FK_l6nijkvb5dfkpxoknap6yhxt2; 
    alter table AC_SMDL 
        drop constraint FK_l5iijx9gif3hcfpnfhketwebo; 
    alter table AC_SPHP 
        drop constraint FK_9mgc3kpojgfpx0yjnlpxcuenj; 
    alter table AC_SPHP 
        drop constraint FK_g9ton1fnie0166sjm1ly05pun; 
    alter table AC_SPHP 
        drop constraint FK_st6klbsxd7yrnqvdrvy0b53n2; 
    alter table AC_SPSR 
        drop constraint FK_16abykipvmbjv2dwcrni1kg3w; 
    alter table AC_STAF 
        drop constraint FK_2dg6k73feu1j3uxq3jgtaf2n9; 
    alter table AC_STAF 
        drop constraint FK_apwd3tfq9378q7lxj4yjq1mq5; 
    alter table AC_STDN 
        drop constraint FK_94tusf6tsiftgfi48jy9djglt; 
    alter table AC_STDN 
        drop constraint FK_trktpa3g0uybvo8sol6i6dffe; 
    alter table AC_STDN 
        drop constraint FK_qjkb9u444rjhpaq7wk7o5f931; 
    alter table AC_STL 
        drop constraint FK_qqe2ryeyt83dbofknllb88ci0; 
    alter table AC_STL 
        drop constraint FK_p5oh5k8hyr9vkooi9qgjq4b43; 
    alter table AC_STLT 
        drop constraint FK_qg6x86suvsjp6f83rhg7eiiek; 
    alter table AC_STLT_ITEM 
        drop constraint FK_l4h9gkypwx5pxy6sy0vjlj8yd; 
    alter table AC_STLT_ITEM 
        drop constraint FK_tk697i3lhyu99oi68vl3sk1sg; 
    alter table AC_STLT_ITEM 
        drop constraint FK_q96jbbc43hfu81xf9y06k8f40; 
    alter table AC_STTE_CODE 
        drop constraint FK_3ht39tir20as2xgwfo9sg5qc9; 
    alter table AC_USER 
        drop constraint FK_i67hqx2uqvpvtqs2kqh8yth90; 
    alter table AC_USER 
        drop constraint FK_aqtnyxfkopl49r5chh7es5fv3; 
    alter table AC_WAVR_ACCT_CHRG 
        drop constraint FK_2oyu1kp4bdfmnrrtlwfr3vdo8; 
    alter table AC_WAVR_ACCT_CHRG 
        drop constraint FK_o6adful921g0efni2hyg2pj09; 
    alter table AC_WAVR_APLN 
        drop constraint FK_1coqllo22hpahq7m73p6u1g74; 
    alter table AC_WAVR_APLN 
        drop constraint FK_9biewrgydh2os939x0wy4bw6; 
    alter table AC_WAVR_DEBT_NOTE 
        drop constraint FK_j3qyt5k5aq13p04ql7lcto8as; 
    alter table AC_WAVR_DEBT_NOTE 
        drop constraint FK_pf4jdfa5al2eduoux9tyf8als; 
    alter table AC_WAVR_FNCE_APLN 
        drop constraint FK_4c543iqe6mw91kefxwl341hbx; 
    alter table AC_WAVR_FNCE_APLN 
        drop constraint FK_thycctwtmvcgw76c013chacq; 
    alter table AC_WAVR_FNCE_APLN 
        drop constraint FK_mc4ci4whcl49dxctpyg7ur8cy; 
    alter table AC_WAVR_INVC 
        drop constraint FK_riqgcgkm6wmm9o0nngp6pp3fh; 
    alter table AC_WAVR_INVC 
        drop constraint FK_8un6c6x9rxruy12eb1lxuia8l; 
    alter table AC_WAVR_ITEM 
        drop constraint FK_pyb0acjsjy3mrek1d4udx4tf2; 
    alter table AC_WAVR_ITEM 
        drop constraint FK_55j1jgk9gtl8292yqb7y181e2; 
    alter table AC_WAVR_ITEM 
        drop constraint FK_kp3w3n229mv2emob6x16tcc61; 
    alter table AC_WAVR_ITEM 
        drop constraint FK_4pc2uvjst1i4gn8omdye9qjp3; 
    alter table AC_WAVR_ITEM 
        drop constraint FK_3ynd0nedy8f6m8o1wu42iqoke; 
    drop table if exists AC_ACCT cascade; 
    drop table if exists AC_ACCT_CHRG cascade; 
    drop table if exists AC_ACCT_CHRG_TRSN cascade; 
    drop table if exists AC_ACCT_TRSN cascade; 
    drop table if exists AC_ACCT_WAVR cascade; 
    drop table if exists AC_ACDM_SESN cascade; 
    drop table if exists AC_ACTR cascade; 
    drop table if exists AC_ADVC_PYMT cascade; 
    drop table if exists AC_AUDT cascade; 
    drop table if exists AC_BANK_CODE cascade; 
    drop table if exists AC_BSRY_RCPT cascade; 
    drop table if exists AC_CDIT_NOTE cascade; 
    drop table if exists AC_CDIT_NOTE_ITEM cascade; 
    drop table if exists AC_CHRG_CODE cascade; 
    drop table if exists AC_CHRT_CODE cascade; 
    drop table if exists AC_CITY_CODE cascade; 
    drop table if exists AC_CMPS_CODE cascade; 
    drop table if exists AC_CNFG cascade; 
    drop table if exists AC_CNTY_CODE cascade; 
    drop table if exists AC_CVRG cascade; 
    drop table if exists AC_DBIT_NOTE cascade; 
    drop table if exists AC_DBIT_NOTE_ITEM cascade; 
    drop table if exists AC_DSCT_CODE cascade; 
    drop table if exists AC_ELTC_RCPT cascade; 
    drop table if exists AC_EMAL_QUEU cascade; 
    drop table if exists AC_EMIL_TMPT cascade; 
    drop table if exists AC_FCTY_CODE cascade; 
    drop table if exists AC_FEE_SCDL cascade; 
    drop table if exists AC_FEE_SCDL_ITEM cascade; 
    drop table if exists AC_GNRL_RCPT cascade; 
    drop table if exists AC_GRDN cascade; 
    drop table if exists AC_GROP cascade; 
    drop table if exists AC_GROP_MMBR cascade; 
    drop table if exists AC_INVC cascade; 
    drop table if exists AC_INVC_ITEM cascade; 
    drop table if exists AC_KNOF cascade; 
    drop table if exists AC_KNOF_CHRG cascade; 
    drop table if exists AC_KNOF_DBT_NOTE cascade; 
    drop table if exists AC_KNOF_INVC cascade; 
    drop table if exists AC_KNOF_ITEM cascade; 
    drop table if exists AC_MODL cascade; 
    drop table if exists AC_NTLY_CODE cascade; 
    drop table if exists AC_PCPL cascade; 
    drop table if exists AC_PCPL_ROLE cascade; 
    drop table if exists AC_PRGM_CODE cascade; 
    drop table if exists AC_PRGM_LEVEL cascade; 
    drop table if exists AC_PRMO_CODE cascade; 
    drop table if exists AC_PRMO_CODE_ITEM cascade; 
    drop table if exists AC_RCPT cascade; 
    drop table if exists AC_RCPT_ACCT_CHRG cascade; 
    drop table if exists AC_RCPT_CHRG_ITEM cascade; 
    drop table if exists AC_RCPT_DBT cascade; 
    drop table if exists AC_RCPT_INVC cascade; 
    drop table if exists AC_RCPT_ITEM cascade; 
    drop table if exists AC_RFND_PYMT cascade; 
    drop table if exists AC_RFRN_NO cascade; 
    drop table if exists AC_RSCY_CODE cascade; 
    drop table if exists AC_SCTY_CHRG_CODE cascade; 
    drop table if exists AC_SMDL cascade; 
    drop table if exists AC_SPHP cascade; 
    drop table if exists AC_SPSR cascade; 
    drop table if exists AC_STAF cascade; 
    drop table if exists AC_STDN cascade; 
    drop table if exists AC_STDY_CNTR_CODE cascade; 
    drop table if exists AC_STDY_MODE cascade; 
    drop table if exists AC_STL cascade; 
    drop table if exists AC_STLT cascade; 
    drop table if exists AC_STLT_ITEM cascade; 
    drop table if exists AC_STTE_CODE cascade; 
    drop table if exists AC_TAX_CODE cascade; 
    drop table if exists AC_USER cascade; 
    drop table if exists AC_WAVR_ACCT_CHRG cascade; 
    drop table if exists AC_WAVR_APLN cascade; 
    drop table if exists AC_WAVR_DEBT_NOTE cascade; 
    drop table if exists AC_WAVR_FNCE_APLN cascade; 
    drop table if exists AC_WAVR_INVC cascade; 
    drop table if exists AC_WAVR_ITEM cascade; 
    drop table if exists AC_WTCH cascade; 
    drop sequence SEQ_ACCT_CHRG; 
    drop sequence SQ_AC_ACCT; 
    drop sequence SQ_AC_ACCT_TRSN; 
    drop sequence SQ_AC_ACCT_WAVR; 
    drop sequence SQ_AC_ACDM_SESN; 
    drop sequence SQ_AC_ACTR; 
    drop sequence SQ_AC_ADVC_PYMT; 
    drop sequence SQ_AC_AUDT; 
    drop sequence SQ_AC_BANK_CODE; 
    drop sequence SQ_AC_CDIT_NOTE; 
    drop sequence SQ_AC_CDIT_NOTE_ITEM; 
    drop sequence SQ_AC_CHRG_CODE; 
    drop sequence SQ_AC_CHRG_ITEM; 
    drop sequence SQ_AC_CHRT_CODE; 
    drop sequence SQ_AC_CITY_CODE; 
    drop sequence SQ_AC_CMPS_CODE; 
    drop sequence SQ_AC_CNFG; 
    drop sequence SQ_AC_CNTY_CODE; 
    drop sequence SQ_AC_CVRG; 
    drop sequence SQ_AC_DBIT_NOTE; 
    drop sequence SQ_AC_DBIT_NOTE_ITEM; 
    drop sequence SQ_AC_DSCT_CODE; 
    drop sequence SQ_AC_EMAL_QUEU; 
    drop sequence SQ_AC_EMIL_TMPT; 
    drop sequence SQ_AC_FCTY_CODE; 
    drop sequence SQ_AC_FEE_SCDL; 
    drop sequence SQ_AC_FEE_SCDL_ITEM; 
    drop sequence SQ_AC_GRDN; 
    drop sequence SQ_AC_GROP_MMBR; 
    drop sequence SQ_AC_INVC; 
    drop sequence SQ_AC_INVC_ITEM; 
    drop sequence SQ_AC_KNOF; 
    drop sequence SQ_AC_KNOF_CHRG; 
    drop sequence SQ_AC_KNOF_DBT_NOTE; 
    drop sequence SQ_AC_KNOF_INVC; 
    drop sequence SQ_AC_KNOF_ITEM; 
    drop sequence SQ_AC_MODL; 
    drop sequence SQ_AC_NTLY_CODE; 
    drop sequence SQ_AC_PCPL; 
    drop sequence SQ_AC_PCPL_ROLE; 
    drop sequence SQ_AC_PRGM_CODE; 
    drop sequence SQ_AC_PRGM_LEVEL; 
    drop sequence SQ_AC_PRMO_CODE; 
    drop sequence SQ_AC_PRMO_CODE_ITEM; 
    drop sequence SQ_AC_RCPT; 
    drop sequence SQ_AC_RCPT_ACCT_CHRG; 
    drop sequence SQ_AC_RCPT_DBT; 
    drop sequence SQ_AC_RCPT_INVC; 
    drop sequence SQ_AC_RFND_PYMT; 
    drop sequence SQ_AC_RFRN_NO; 
    drop sequence SQ_AC_RSCY_CODE; 
    drop sequence SQ_AC_SCTY_CHRG_CODE; 
    drop sequence SQ_AC_SMDL; 
    drop sequence SQ_AC_SPHP; 
    drop sequence SQ_AC_STDY_CNTR_CODE; 
    drop sequence SQ_AC_STDY_MODE; 
    drop sequence SQ_AC_STL; 
    drop sequence SQ_AC_STLT; 
    drop sequence SQ_AC_STLT_ITEM; 
    drop sequence SQ_AC_STTE_CODE; 
    drop sequence SQ_AC_TAX_CODE; 
    drop sequence SQ_AC_WAVR_ACCT_CHRG; 
    drop sequence SQ_AC_WAVR_APLN; 
    drop sequence SQ_AC_WAVR_DEBT_NOTE; 
    drop sequence SQ_AC_WAVR_FNCE_APLN; 
    drop sequence SQ_AC_WAVR_INVC; 
    drop sequence SQ_AC_WAVR_ITEM; 
    drop sequence SQ_AC_WTCH;