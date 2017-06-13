DROP SEQUENCE SQ_AC_TAX_CODE;
CREATE SEQUENCE SQ_AC_TAX_CODE START WITH 1;

insert into AC_TAX_CODE (ID, CODE, TAX_RATE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_TAX_CODE'), 'SR', 0.06, 'Standard-rated supplies with GST charged.', 1, 0, TIMESTAMP '2015-09-21 15:57:23');
insert into AC_TAX_CODE (ID, CODE, TAX_RATE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_TAX_CODE'), 'ZRL', 0.00, 'Local supply of goods or services which are subject to zero rated supplies.', 1, 0, TIMESTAMP '2015-09-21 15:57:23');
insert into AC_TAX_CODE (ID, CODE, TAX_RATE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_TAX_CODE'), 'ZRE', 0.00, 'Exportation of goods or services which are subject to zero rated supplies.', 1, 0, TIMESTAMP '2015-09-21 15:57:23');
insert into AC_TAX_CODE (ID, CODE, TAX_RATE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_TAX_CODE'), 'DS', 0.06, 'Deemed supplies (e.g. transfer or disposal of business assets without consideration).', 1, 0, TIMESTAMP '2015-09-21 15:57:23');
insert into AC_TAX_CODE (ID, CODE, TAX_RATE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_TAX_CODE'), 'OS', 0.00, 'Out-of-scope supplies.', 1, 0, TIMESTAMP '2015-09-21 15:57:23');
insert into AC_TAX_CODE (ID, CODE, TAX_RATE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_TAX_CODE'), 'ES', 0.00, 'Exempt supplies under GST.', 1, 0, TIMESTAMP '2015-09-21 15:57:23');
insert into AC_TAX_CODE (ID, CODE, TAX_RATE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_TAX_CODE'), 'ES43', 0.00, 'Incidental Exempt Supplies', 1, 0, TIMESTAMP '2015-09-21 15:57:23');
insert into AC_TAX_CODE (ID, CODE, TAX_RATE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_TAX_CODE'), 'RS', 0.00, 'Relief supply under GST.', 1, 0, TIMESTAMP '2015-09-21 15:57:23');
insert into AC_TAX_CODE (ID, CODE, TAX_RATE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_TAX_CODE'), 'GS', 0.00, 'Disregarded supplies.', 1, 0, TIMESTAMP '2015-09-21 15:57:23');
insert into AC_TAX_CODE (ID, CODE, TAX_RATE, DESCRIPTION, M_ST, C_ID, C_TS) values (nextval('SQ_AC_TAX_CODE'), 'AJS', 0.06, 'Any adjustment made to Output Tax', 1, 0, TIMESTAMP '2015-09-21 15:57:23');

