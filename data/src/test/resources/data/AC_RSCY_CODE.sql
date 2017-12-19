DROP SEQUENCE SQ_AC_RSCY_CODE ;
CREATE SEQUENCE SQ_AC_RSCY_CODE START WITH 1;

insert into AC_RSCY_CODE (ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_RSCY_CODE'), '-1', 'NOT SPECIFIED', 1, 0, TIMESTAMP '2015-09-28 08:08:26');
insert into AC_RSCY_CODE (ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_RSCY_CODE'), '-2', 'NOT APPLICABLE', 1, 0, TIMESTAMP '2015-09-28 08:08:26');
insert into AC_RSCY_CODE (ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_RSCY_CODE'), '1', 'CITIZEN', 1, 0, TIMESTAMP '2015-09-28 08:08:26');
insert into AC_RSCY_CODE (ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_RSCY_CODE'), '2', 'NOT A CITIZEN', 1, 0, TIMESTAMP '2015-09-28 08:08:26');
insert into AC_RSCY_CODE (ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_RSCY_CODE'), '3', 'WARGANEGARA (AMJ)', 1, 0, TIMESTAMP '2015-09-28 08:08:26');
insert into AC_RSCY_CODE (ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_RSCY_CODE'), 'P', 'PERMANENT RESIDENT (PR)', 1, 0, TIMESTAMP '2015-09-28 08:08:26');
insert into AC_RSCY_CODE (ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_RSCY_CODE'), '4', 'TEMPORARY RESIDENT', 1, 0, TIMESTAMP '2015-09-28 08:08:26');
insert into AC_RSCY_CODE (ID, CODE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_RSCY_CODE'), '9', 'MAKLUMAT TIDAK DIPEROLEHI', 1, 0, TIMESTAMP '2015-09-28 08:08:26');
