INSERT INTO AC_RFRN_NO (ID, CODE, CURRENT_VALUE, DESCRIPTION, INCREMENT_VALUE, PREFIX, REFERENCE_FORMAT, SEQUENCE_FORMAT, M_ST, C_ID, C_TS )
VALUES (nextval('SQ_AC_RFRN_NO'), 'AcAccountCharge.referenceNo', 1, 'Account Charge Reference No Format', 1, 'CHRG', '{#academicSession.getCode()}/{#j}', '000', 1, 0, CURRENT_TIMESTAMP );

INSERT INTO AC_RFRN_NO (ID, CODE, CURRENT_VALUE, DESCRIPTION, INCREMENT_VALUE, PREFIX, REFERENCE_FORMAT, SEQUENCE_FORMAT, M_ST, C_ID, C_TS )
VALUES (nextval('SQ_AC_RFRN_NO'), 'AcSettlement.referenceNo', 1, 'Settlement Reference No Format', 1, 'STLT', '{#academicSession.getCode()}/{#sponsor.getCode()}/{#j}', '000', 1, 0, CURRENT_TIMESTAMP );

INSERT INTO AC_RFRN_NO (ID, CODE, CURRENT_VALUE, DESCRIPTION, INCREMENT_VALUE, PREFIX, REFERENCE_FORMAT, SEQUENCE_FORMAT, M_ST, C_ID, C_TS )
VALUES (nextval('SQ_AC_RFRN_NO'), 'AcInvoice.referenceNo', 1, 'Invoice Reference No Format', 1, 'INVC', '{#academicSession.getCode()}/{#j}', '000', 1, 0, CURRENT_TIMESTAMP );

INSERT INTO AC_RFRN_NO (ID, CODE, CURRENT_VALUE, DESCRIPTION, INCREMENT_VALUE, PREFIX, REFERENCE_FORMAT, SEQUENCE_FORMAT, M_ST, C_ID, C_TS )
VALUES (nextval('SQ_AC_RFRN_NO'), 'AcInvoice.invoiceNo', 1, 'Invoice Reference No Format', 1, 'INVC', '{#academicSession.getCode()}/{#j}', '000', 1, 0, CURRENT_TIMESTAMP );

INSERT INTO AC_RFRN_NO (ID, CODE, CURRENT_VALUE, DESCRIPTION, INCREMENT_VALUE, PREFIX, REFERENCE_FORMAT, SEQUENCE_FORMAT, M_ST, C_ID, C_TS )
VALUES (nextval('SQ_AC_RFRN_NO'), 'AcReceipt.referenceNo', 1, 'Receipt Reference No Format', 1, 'RCPT', '{#academicSession.getCode()}/{#j}', '000', 1, 0, CURRENT_TIMESTAMP );

INSERT INTO AC_RFRN_NO (ID, CODE, CURRENT_VALUE, DESCRIPTION, INCREMENT_VALUE, PREFIX, REFERENCE_FORMAT, SEQUENCE_FORMAT, M_ST, C_ID, C_TS )
VALUES (nextval('SQ_AC_RFRN_NO'), 'AcReceipt.receiptNo', 1, 'Receipt Reference No Format', 1, 'RCPT', '{#academicSession.getCode()}/{#j}', '000', 1, 0, CURRENT_TIMESTAMP );

