
create table AC_ACCT (
  ID int8 not null,
  CODE varchar(255) not null,
  DESCRIPTION varchar(255),
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  ACTOR_ID int8,
  primary key (ID)
);

create table AC_ACCT_CHRG (
  ID int8 not null,
  AMOUNT numeric(19, 2) not null,
  CHARGE_TYPE int4 not null,
  DESCRIPTION varchar(255) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  REFERENCE_NO varchar(255) not null,
  SOURCE_NO varchar(255) not null,
  ACCOUNT_ID int8,
  INVOICE_ID int8,
  SESSION_ID int8 not null,
  primary key (ID)
);

create table AC_ACCT_TRSN (
  ID int8 not null,
  AMOUNT numeric(19, 2) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  POSTED_DATE timestamp not null,
  SOURCE_NO varchar(255) not null,
  TRANSACTION_CODE int4 not null,
  ACCOUNT_ID int8,
  CHARGE_CODE_ID int8 not null,
  SESSION_ID int8,
  primary key (ID)
);

create table AC_ACDM_CHRG (
  ID int8 not null,
  CHARGE_CODE_ID int8,
  primary key (ID)
);

create table AC_ACDM_SESN (
  ID int8 not null,
  CODE varchar(255) not null,
  CURRENT_ boolean not null,
  DESCRIPTION varchar(255) not null,
  END_DATE timestamp not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  START_DATE timestamp not null,
  primary key (ID)
);

create table AC_ACTR (
  ID int8 not null,
  ACTOR_TYPE int4,
  ADDRESS varchar(255),
  EMAIL varchar(255),
  FAX varchar(255),
  IDENTITY_NO varchar(255) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  MOBILE varchar(255),
  NAME varchar(255) not null,
  PHONE varchar(255),
  primary key (ID)
);

create table AC_AUDT (
  ID int8 not null,
  CLASS_NAME varchar(255) not null,
  MESSAGE varchar(255) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  OBJECT_ID int8 not null,
  USER_ID int8 not null,
  primary key (ID)
);

create table AC_BANK_CODE (
  ID int8 not null,
  CODE varchar(255) not null,
  IBG_CODE varchar(255) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  NAME varchar(255) not null,
  SWIFT_CODE varchar(255) not null,
  primary key (ID)
);

create table AC_BSRY_RCPT (
  ID int8 not null,
  primary key (ID)
);

create table AC_CHRG_CODE (
  ID int8 not null,
  CHARGE_TYPE int4 not null,
  CODE varchar(255) not null,
  DESCRIPTION varchar(255) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  PRIORITY int4 not null,
  primary key (ID)
);

create table AC_CITY_CODE (
  ID int8 not null,
  CODE varchar(255) not null,
  DESCRIPTION varchar(255),
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  STATE_CODE_ID int8,
  primary key (ID)
);

create table AC_CMPS_CODE (
  ID int8 not null,
  CODE varchar(255) not null,
  DESCRIPTION varchar(255) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  primary key (ID)
);

create table AC_CNFG (
  ID int8 not null,
  DESCRIPTION varchar(255),
  CONFIG_KEY varchar(255) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  CONFIG_VALUE varchar(255),
  CONFIG_VALUE_BYTEA bytea,
  CONFIG_VALUE_DOUBLE float8,
  CONFIG_VALUE_LONG int8,
  primary key (ID)
);

create table AC_CNTY_CODE (
  ID int8 not null,
  CODE varchar(255) not null,
  DESCRIPTION varchar(255),
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  primary key (ID)
);

create table AC_CVRG (
  ID int8 not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  CHARGE_CODE_ID int8,
  SPONSOR_ID int8,
  primary key (ID)
);

create table AC_DSCT_CODE (
  ID int8 not null,
  CODE varchar(255) not null,
  DESCRIPTION varchar(255),
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  primary key (ID)
);

create table AC_ELTC_RCPT (
  ID int8 not null,
  primary key (ID)
);

create table AC_EMAL_QUEU (
  ID int8 not null,
  EMAIL_BCC varchar(255),
  BODY text,
  EMAIL_CC varchar(255),
  CODE varchar(255) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  QUEUE_STATUS int4,
  RETRY_COUNT int4,
  SUBJECT varchar(255),
  EMAIL_TO varchar(255),
  primary key (ID)
);

create table AC_EMIL_TMPT (
  ID int8 not null,
  BCC_ADDRESS varchar(255),
  CC_ADDRESS varchar(255),
  CODE varchar(255) not null,
  DESCRIPTION varchar(255),
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  SUBJECT varchar(255),
  TEMPLATE TEXT,
  TO_ADDRESS varchar(255),
  primary key (ID)
);

create table AC_ENMT_CHRG (
  ID int8 not null,
  primary key (ID)
);

create table AC_ENMT_LATE_CHRG (
  ID int8 not null,
  primary key (ID)
);

create table AC_FCTY_CODE (
  ID int8 not null,
  CODE varchar(255) not null,
  DESCRIPTION varchar(255) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  primary key (ID)
);

create table AC_GNRL_RCPT (
  ID int8 not null,
  primary key (ID)
);

create table AC_GROP (
  ID int8 not null,
  primary key (ID)
);

create table AC_GROP_MMBR (
  ID int8 not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  GROUP_ID int8,
  PRINCIPAL_ID int8,
  primary key (GROUP_ID, PRINCIPAL_ID)
);

create table AC_INVC (
  ID int8 not null,
  AUDIT_NO varchar(255),
  BALANCE_AMOUNT numeric(19, 2),
  CANCEL_COMMENT varchar(255),
  DESCRIPTION varchar(255),
  AV_TS timestamp,
  AV_ID int8,
  CL_ID int8,
  CL_TS timestamp,
  CK_TS timestamp,
  CK_ID int8,
  DT_TS timestamp,
  DT_ID int8,
  EV_TS timestamp,
  EV_ID int8,
  PR_TS timestamp,
  PR_ID int8,
  PS_TS timestamp,
  PS_ID int8,
  RG_TS timestamp,
  RG_ID int8,
  RM_TS timestamp,
  RM_ID int8,
  RQ_TS timestamp,
  RQ_ID int8,
  SL_TS timestamp,
  SL_ID int8,
  FD_ST int4,
  UP_TS timestamp,
  UP_ID int8,
  UV_TS timestamp,
  UV_ID int8,
  VF_TS timestamp,
  VF_ID int8,
  INVOICE_NO varchar(255),
  ISSUED_DATE timestamp,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  PAID boolean,
  REFERENCE_NO varchar(255),
  REMOVE_COMMENT varchar(255),
  SOURCE_NO varchar(255),
  TOTAL_AMOUNT numeric(19, 2),
  ACCOUNT_ID int8,
  SESSION_ID int8,
  primary key (ID)
);

create table AC_INVC_ITEM (
  ID int8 not null,
  AMOUNT numeric(19, 2),
  BALANCE_AMOUNT numeric(19, 2),
  DESCRIPTION varchar(255),
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  CHARGE_CODE_ID int8,
  INVOICE_ID int8,
  primary key (ID)
);

create table AC_MODL (
  ID int8 not null,
  CANONICAL_CODE varchar(255) not null,
  CODE varchar(255) not null,
  DESCRIPTION varchar(255),
  ENABLED boolean,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  ORDINAL int4 not null,
  primary key (ID)
);

create table AC_NTLY_CODE (
  ID int8 not null,
  CODE varchar(255),
  DESCRIPTION varchar(255),
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  primary key (ID)
);

create table AC_PCPL (
  ID int8 not null,
  ENABLED boolean not null,
  LOCKED boolean not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  NAME varchar(255) not null,
  PRINCIPAL_TYPE int4,
  primary key (ID)
);

create table AC_PCPL_ROLE (
  ID int8 not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  ROLE_TYPE int4,
  PRINCIPAL_ID int8,
  primary key (ID)
);

create table AC_PRGM_CODE (
  ID int8 not null,
  CODE varchar(255) not null,
  DESCRIPTION varchar(255) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  primary key (ID)
);

create table AC_RCPT (
  ID int8 not null,
  AUDIT_NO varchar(255),
  CANCEL_COMMENT varchar(255),
  DESCRIPTION varchar(255),
  AV_TS timestamp,
  AV_ID int8,
  CL_ID int8,
  CL_TS timestamp,
  CK_TS timestamp,
  CK_ID int8,
  DT_TS timestamp,
  DT_ID int8,
  EV_TS timestamp,
  EV_ID int8,
  PR_TS timestamp,
  PR_ID int8,
  PS_TS timestamp,
  PS_ID int8,
  RG_TS timestamp,
  RG_ID int8,
  RM_TS timestamp,
  RM_ID int8,
  RQ_TS timestamp,
  RQ_ID int8,
  SL_TS timestamp,
  SL_ID int8,
  FD_ST int4,
  UP_TS timestamp,
  UP_ID int8,
  UV_TS timestamp,
  UV_ID int8,
  VF_TS timestamp,
  VF_ID int8,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  PAYMENT_METHOD int4,
  RECEIPT_NO varchar(255),
  RECEIPT_TYPE int4,
  RECEIVED_DATE timestamp,
  REFERENCE_NO varchar(255),
  REMOVE_COMMENT varchar(255),
  SOURCE_NO varchar(255),
  TOTAL_AMOUNT numeric(19, 2),
  TOTAL_APPLIED numeric(19, 2),
  TOTAL_RECEIVED numeric(19, 2),
  ACCOUNT_ID int8,
  primary key (ID)
);

create table AC_RCPT_ITEM (
  ID int8 not null,
  ADJUSTED_AMOUNT numeric(19, 2),
  APPLIED_AMOUNT numeric(19, 2) not null,
  DESCRIPTION varchar(255),
  DUE_AMOUNT numeric(19, 2) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  PRICE numeric(19, 2),
  TOTAL_AMOUNT numeric(19, 2),
  UNIT int4,
  CHARGE_CODE_ID int8,
  INVOICE_ID int8,
  RECEIPT_ID int8,
  primary key (ID)
);

create table AC_RFRN_NO (
  ID int8 not null,
  CODE varchar(255) not null,
  CURRENT_VALUE int4,
  DESCRIPTION varchar(255) not null,
  INCREMENT_VALUE int4,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  PREFIX varchar(255),
  REFERENCE_FORMAT varchar(255),
  SEQUENCE_FORMAT varchar(255),
  primary key (ID)
);

create table AC_RSCY_CODE (
  ID int8 not null,
  CODE varchar(255),
  DESCRIPTION varchar(255),
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  primary key (ID)
);

create table AC_SCTY_CHRG (
  ID int8 not null,
  CHARGE_CODE_ID int8,
  primary key (ID)
);

create table AC_SMDL (
  ID int8 not null,
  CODE varchar(255),
  DESCRIPTION varchar(255),
  ENABLED boolean,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  ORDINAL int4,
  MODULE_ID int8,
  primary key (ID)
);

create table AC_SPHP (
  ID int8 not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  SPONSOR_ID int8,
  STUDENT_ID int8,
  primary key (ID)
);

create table AC_SPSR (
  SPONSOR_TYPE int4,
  ID int8 not null,
  primary key (ID)
);

create table AC_STAF (
  ID int8 not null,
  primary key (ID)
);

create table AC_STDN (
  ID int8 not null,
  primary key (ID)
);

create table AC_STDN_AFFR_CHRG (
  ID int8 not null,
  CHARGE_CODE_ID int8,
  primary key (ID)
);

create table AC_STDY_CNTR_CODE (
  ID int8 not null,
  CODE varchar(255) not null,
  DESCRIPTION varchar(255) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  primary key (ID)
);

create table AC_STLT (
  ID int8 not null,
  DESCRIPTION varchar(255) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  REFERENCE_NO varchar(255) not null,
  SESSION_ID int8,
  SPONSOR_ID int8,
  primary key (ID)
);

create table AC_STLT_ITEM (
  ID int8 not null,
  BALANCE_AMOUNT numeric(19, 2) not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  SETTLEMENT_STATUS int4 not null,
  ACCOUNT_ID int8,
  INVOICE_ID int8,
  SETTLEMENT_ID int8,
  primary key (ID)
);

create table AC_STTE_CODE (
  ID int8 not null,
  CODE varchar(255) not null,
  DESCRIPTION varchar(255),
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  COUNTRY_CODE_ID int8,
  primary key (ID)
);

create table AC_USER (
  EMAIL varchar(255) not null,
  PASSWORD varchar(255),
  REAL_NAME varchar(255) not null,
  ID int8 not null,
  ACTOR_ID int8,
  primary key (ID)
);

create table AC_WTCH (
  ID int8 not null,
  C_TS timestamp,
  C_ID int8,
  D_TS timestamp,
  D_ID int8,
  M_TS timestamp,
  M_ID int8,
  M_ST int4,
  OBJECT_CLASS varchar(255) not null,
  OBJECT_ID int8 not null,
  USER_ID int8 not null,
  primary key (ID)
);

alter table AC_ACCT
  add constraint uc_AC_ACCT_1 unique (CODE);

alter table AC_ACCT
  add constraint FKE6230610F3FA5886
foreign key (ACTOR_ID)
references AC_ACTR;

alter table AC_ACCT_CHRG
  add constraint uc_AC_ACCT_CHRG_1 unique (REFERENCE_NO);

alter table AC_ACCT_CHRG
  add constraint FKD5788A2962AC940F
foreign key (ACCOUNT_ID)
references AC_ACCT;

alter table AC_ACCT_CHRG
  add constraint FKD5788A297580D95D
foreign key (INVOICE_ID)
references AC_INVC;

alter table AC_ACCT_CHRG
  add constraint FKD5788A29706DBE68
foreign key (SESSION_ID)
references AC_ACDM_SESN;

alter table AC_ACCT_TRSN
  add constraint FKD5806A2862AC940F
foreign key (ACCOUNT_ID)
references AC_ACCT;

alter table AC_ACCT_TRSN
  add constraint FKD5806A2865B1C0CE
foreign key (CHARGE_CODE_ID)
references AC_CHRG_CODE;

alter table AC_ACCT_TRSN
  add constraint FKD5806A28706DBE68
foreign key (SESSION_ID)
references AC_ACDM_SESN;

alter table AC_ACDM_CHRG
  add constraint FKFE6CD91165B1C0CE
foreign key (CHARGE_CODE_ID)
references AC_CHRG_CODE;

alter table AC_ACDM_CHRG
  add constraint FKFE6CD911ACE4F811
foreign key (ID)
references AC_ACCT_CHRG;

alter table AC_ACDM_SESN
  add constraint uc_AC_ACDM_SESN_1 unique (CODE);

alter table AC_ACTR
  add constraint uc_AC_ACTR_1 unique (IDENTITY_NO);

alter table AC_BANK_CODE
  add constraint uc_AC_BANK_CODE_1 unique (CODE);

alter table AC_BANK_CODE
  add constraint uc_AC_BANK_CODE_2 unique (IBG_CODE);

alter table AC_BANK_CODE
  add constraint uc_AC_BANK_CODE_3 unique (SWIFT_CODE);

alter table AC_BSRY_RCPT
  add constraint FK9DC399FB023D496
foreign key (ID)
references AC_RCPT;

alter table AC_CHRG_CODE
  add constraint uc_AC_CHRG_CODE_1 unique (CODE);

alter table AC_CITY_CODE
  add constraint uc_AC_CITY_CODE_1 unique (CODE);

alter table AC_CITY_CODE
  add constraint FKF91AB864761B73B6
foreign key (STATE_CODE_ID)
references AC_STTE_CODE;

alter table AC_CMPS_CODE
  add constraint uc_AC_CMPS_CODE_1 unique (CODE);

alter table AC_CNTY_CODE
  add constraint uc_AC_CNTY_CODE_1 unique (CODE);

alter table AC_CVRG
  add constraint FKE62437E565B1C0CE
foreign key (CHARGE_CODE_ID)
references AC_CHRG_CODE;

alter table AC_CVRG
  add constraint FKE62437E5624354E6
foreign key (SPONSOR_ID)
references AC_SPSR;

alter table AC_DSCT_CODE
  add constraint uc_AC_DSCT_CODE_1 unique (CODE);

alter table AC_ELTC_RCPT
  add constraint FK33C32561B023D496
foreign key (ID)
references AC_RCPT;

alter table AC_EMAL_QUEU
  add constraint uc_AC_EMAL_QUEU_1 unique (CODE);

alter table AC_EMIL_TMPT
  add constraint uc_AC_EMIL_TMPT_1 unique (CODE);

alter table AC_ENMT_CHRG
  add constraint FKAE35844CACE4F811
foreign key (ID)
references AC_ACCT_CHRG;

alter table AC_ENMT_LATE_CHRG
  add constraint FK774A03A1ACE4F811
foreign key (ID)
references AC_ACCT_CHRG;

alter table AC_FCTY_CODE
  add constraint uc_AC_FCTY_CODE_1 unique (CODE);

alter table AC_GNRL_RCPT
  add constraint FKD19D8436B023D496
foreign key (ID)
references AC_RCPT;

alter table AC_GROP
  add constraint FKE625FA0914AF69B5
foreign key (ID)
references AC_PCPL;

alter table AC_GROP_MMBR
  add constraint FKD940C6665F83F46
foreign key (GROUP_ID)
references AC_GROP;

alter table AC_GROP_MMBR
  add constraint FKD940C6664AF14766
foreign key (PRINCIPAL_ID)
references AC_PCPL;

alter table AC_INVC
  add constraint FKE626D48F62AC940F
foreign key (ACCOUNT_ID)
references AC_ACCT;

alter table AC_INVC
  add constraint FKE626D48F706DBE68
foreign key (SESSION_ID)
references AC_ACDM_SESN;

alter table AC_INVC_ITEM
  add constraint FKBE5FD8C365B1C0CE
foreign key (CHARGE_CODE_ID)
references AC_CHRG_CODE;

alter table AC_INVC_ITEM
  add constraint FKBE5FD8C37580D95D
foreign key (INVOICE_ID)
references AC_INVC;

alter table AC_MODL
  add constraint uc_AC_MODL_1 unique (CANONICAL_CODE);

alter table AC_MODL
  add constraint uc_AC_MODL_2 unique (CODE);

alter table AC_NTLY_CODE
  add constraint uc_AC_NTLY_CODE_1 unique (CODE);

alter table AC_PCPL
  add constraint uc_AC_PCPL_1 unique (NAME);

alter table AC_PCPL_ROLE
  add constraint FK278C33A94AF14766
foreign key (PRINCIPAL_ID)
references AC_PCPL;

alter table AC_PRGM_CODE
  add constraint uc_AC_PRGM_CODE_1 unique (CODE);

alter table AC_RCPT
  add constraint FKE62AC1F262AC940F
foreign key (ACCOUNT_ID)
references AC_ACCT;

alter table AC_RCPT_ITEM
  add constraint FK5DB7D14065B1C0CE
foreign key (CHARGE_CODE_ID)
references AC_CHRG_CODE;

alter table AC_RCPT_ITEM
  add constraint FK5DB7D1407580D95D
foreign key (INVOICE_ID)
references AC_INVC;

alter table AC_RCPT_ITEM
  add constraint FK5DB7D140BC4EFBFD
foreign key (RECEIPT_ID)
references AC_RCPT;

alter table AC_RFRN_NO
  add constraint uc_AC_RFRN_NO_1 unique (CODE);

alter table AC_RSCY_CODE
  add constraint uc_AC_RSCY_CODE_1 unique (CODE);

alter table AC_SCTY_CHRG
  add constraint FKCE1A82A765B1C0CE
foreign key (CHARGE_CODE_ID)
references AC_CHRG_CODE;

alter table AC_SCTY_CHRG
  add constraint FKCE1A82A7ACE4F811
foreign key (ID)
references AC_ACCT_CHRG;

alter table AC_SMDL
  add constraint FKE62B5A5F1AED9EFD
foreign key (MODULE_ID)
references AC_MODL;

alter table AC_SPHP
  add constraint FKE62B6622624354E6
foreign key (SPONSOR_ID)
references AC_SPSR;

alter table AC_SPHP
  add constraint FKE62B66224F717CC6
foreign key (STUDENT_ID)
references AC_STDN;

alter table AC_SPSR
  add constraint FKE62B6779565BDDFC
foreign key (ID)
references AC_ACTR;

alter table AC_STAF
  add constraint FKE62B7443565BDDFC
foreign key (ID)
references AC_ACTR;

alter table AC_STDN
  add constraint FKE62B74A8565BDDFC
foreign key (ID)
references AC_ACTR;

alter table AC_STDN_AFFR_CHRG
  add constraint FK7FEF0E5165B1C0CE
foreign key (CHARGE_CODE_ID)
references AC_CHRG_CODE;

alter table AC_STDN_AFFR_CHRG
  add constraint FK7FEF0E51ACE4F811
foreign key (ID)
references AC_ACCT_CHRG;

alter table AC_STDY_CNTR_CODE
  add constraint uc_AC_STDY_CNTR_CODE_1 unique (CODE);

alter table AC_STLT
  add constraint uc_AC_STLT_1 unique (REFERENCE_NO);

alter table AC_STLT
  add constraint FKE62B75A6706DBE68
foreign key (SESSION_ID)
references AC_ACDM_SESN;

alter table AC_STLT
  add constraint FKE62B75A6624354E6
foreign key (SPONSOR_ID)
references AC_SPSR;

alter table AC_STLT_ITEM
  add constraint FK4574E0C62AC940F
foreign key (ACCOUNT_ID)
references AC_ACCT;

alter table AC_STLT_ITEM
  add constraint FK4574E0C7580D95D
foreign key (INVOICE_ID)
references AC_INVC;

alter table AC_STLT_ITEM
  add constraint FK4574E0C49AD1DB9
foreign key (SETTLEMENT_ID)
references AC_STLT;

alter table AC_STTE_CODE
  add constraint uc_AC_STTE_CODE_1 unique (CODE);

alter table AC_STTE_CODE
  add constraint FK91EDA99D66497D80
foreign key (COUNTRY_CODE_ID)
references AC_CNTY_CODE;

alter table AC_USER
  add constraint uc_AC_USER_1 unique (EMAIL);

alter table AC_USER
  add constraint FKE62C59C8F3FA5886
foreign key (ACTOR_ID)
references AC_ACTR;

alter table AC_USER
  add constraint FKE62C59C814AF69B5
foreign key (ID)
references AC_PCPL;

create sequence SEQ_ACCT_CHRG;

create sequence SQ_AC_ACCT;

create sequence SQ_AC_ACCT_TRSN;

create sequence SQ_AC_ACDM_SESN;

create sequence SQ_AC_ACTR;

create sequence SQ_AC_AUDT;

create sequence SQ_AC_BANK_CODE;

create sequence SQ_AC_CHRG_CODE;

create sequence SQ_AC_CITY_CODE;

create sequence SQ_AC_CMPS_CODE;

create sequence SQ_AC_CNFG;

create sequence SQ_AC_CNTY_CODE;

create sequence SQ_AC_CVRG;

create sequence SQ_AC_DSCT_CODE;

create sequence SQ_AC_EMAL_QUEU;

create sequence SQ_AC_EMIL_TMPT;

create sequence SQ_AC_FCTY_CODE;

create sequence SQ_AC_GROP_MMBR;

create sequence SQ_AC_INVC;

create sequence SQ_AC_INVC_ITEM;

create sequence SQ_AC_MODL;

create sequence SQ_AC_NTLY_CODE;

create sequence SQ_AC_PCPL;

create sequence SQ_AC_PCPL_ROLE;

create sequence SQ_AC_PRGM_CODE;

create sequence SQ_AC_RCPT;

create sequence SQ_AC_RFRN_NO;

create sequence SQ_AC_RSCY_CODE;

create sequence SQ_AC_SMDL;

create sequence SQ_AC_SPHP;

create sequence SQ_AC_STDY_CNTR_CODE;

create sequence SQ_AC_STLT;

create sequence SQ_AC_STLT_ITEM;

create sequence SQ_AC_STTE_CODE;

create sequence SQ_AC_WTCH;
