
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
        CHARGE_DATE timestamp,
        CHARGE_TYPE int4,
        CODE varchar(255),
        DESCRIPTION varchar(255) not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        ORDINAL int default 0,
        REFERENCE_NO varchar(255) not null,
        SOURCE_NO varchar(255) not null,
        ACCOUNT_ID int8,
        COHORT_CODE_ID int8,
        INVOICE_ID int8,
        SESSION_ID int8,
        STUDY_MODE_ID int8,
        SECURITY_CHARGE_CODE_ID int8,
        primary key (ID)
    );

    create table AC_ACCT_STL (
        ID int8 not null,
        AMOUNT numeric(19, 2),
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        SOURCE_NO varchar(255),
        ACCOUNT_ID int8,
        SESSION_ID int8,
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
        SESSION_ID int8 not null,
        primary key (ID)
    );

    create table AC_ACCT_WAVR (
        ID int8 not null,
        AMOUNT numeric(19, 2),
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        SOURCE_NO varchar(255),
        ACCOUNT_ID int8,
        SESSION_ID int8,
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
    
    create table AC_ADVC_PYMT (
        ID int8 not null,
        REFERENCE_NO varchar(255) not null,
        DESCRIPTION varchar(255) not null,
        AMOUNT numeric(19, 2),
        BALANCE_AMOUNT numeric(19, 2),
        STATUS boolean,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        RECEIPT_ID int8,
        KNOCKOFF_ID int8,
        ACCOUNT_ID int8,
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

    create table AC_CDIT_NOTE (
        ID int8 not null,
        AUDIT_NO varchar(255),
        CANCEL_COMMENT varchar(255),
        CREDITNOTE_DATE timestamp,
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
        ISSUED_DATE timestamp,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        REFERENCE_NO varchar(255),
        REMOVE_COMMENT varchar(255),
        SOURCE_NO varchar(255),
        TOTAL_AMOUNT numeric(19, 2),
        CHARGE_CODE_ID int8,
        INVOICE_ID int8,
        primary key (ID)
    );

    create table AC_CDIT_NOTE_ITEM (
        ID int8 not null,
        AMOUNT numeric(19, 2),
        CREDITNOTEITEM_DATE timestamp,
        DESCRIPTION varchar(255),
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        CHARGE_CODE_ID int8,
        CREDIT_NOTE_ID int8,
        primary key (ID)
    );

    create table AC_CHRG_CODE (
        ID int8 not null,
        CHARGE_TYPE int4 not null,
        CODE varchar(255) not null,
        DESCRIPTION varchar(255) not null,
        INCLUSIVE boolean,
        ACTIVE boolean,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        PRIORITY int4 not null,
        TAX_CODE_ID int8,
        primary key (ID)
    );

    create table AC_CHRT_CODE (
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
        PROGRAM_CODE_ID int8,
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

    create table AC_DBIT_NOTE (
        ID int8 not null,
        AUDIT_NO varchar(255),
        CANCEL_COMMENT varchar(255),
        DEBITNOTE_DATE timestamp,
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
        ISSUED_DATE timestamp,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        REFERENCE_NO varchar(255),
        REMOVE_COMMENT varchar(255),
        SOURCE_NO varchar(255),
        TOTAL_AMOUNT numeric(19, 2),
        CHARGE_CODE_ID int8,
        INVOICE_ID int8,
        primary key (ID)
    );

    create table AC_DBIT_NOTE_ITEM (
        ID int8 not null,
        AMOUNT numeric(19, 2),
        DEBITNOTEITEM_DATE timestamp,
        DESCRIPTION varchar(255),
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        CHARGE_CODE_ID int8,
        DEBIT_NOTE_ID int8,
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

    create table AC_FEE_SCDL (
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
        TOTAL_AMOUNT numeric(19, 2) not null,
        COHORT_CODE_ID int8 not null,
        RESIDENCY_CODE_ID int8 not null,
        STUDY_MODE_ID int8 not null,
        primary key (ID)
    );

    create table AC_FEE_SCDL_ITEM (
        ID int8 not null,
        AMOUNT numeric(19, 2) not null,
        DESCRIPTION varchar(255) not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        ORDINAL int4 not null,
        CHARGE_CODE_ID int8,
        SCHEDULE_ID int8,
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

    create table AC_KNOF (
        ID int8 not null,
        AMOUNT numeric(19, 2),
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
        ISSUED_DATE timestamp,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        REFERENCE_NO varchar(255),
        REMOVE_COMMENT varchar(255),
        SOURCE_NO varchar(255),
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
        FACULTY_CODE_ID int8,
        primary key (ID)
    );

    create table AC_PRMO_CODE (
        ID int8 not null,
        DESCRIPTION varchar(255) not null,
        EXPIRY_DATE timestamp,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        PROMO_CODE_TYPE int4,
        QUANTITY int4,
        REFERENCE_NO varchar(255) not null,
        VALUE numeric(19, 2) not null,
        primary key (ID)
    );

    create table AC_PRMO_CODE_ITEM (
        ID int8 not null,
        APPLIED boolean,
        CODE varchar(255),
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        SOURCE_NO varchar(255),
        ACCOUNT_ID int8,
        PROMO_CODE_ID int8 not null,
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

    create table AC_RCPT_INVC (
        ID int8 not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        INVOICE_ID int8,
        RECEIPT_ID int8,
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

    create table AC_RFND_PYMT (
        ID int8 not null,
        AMOUNT numeric(19, 2),
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
        ISSUED_DATE timestamp,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        REFERENCE_NO varchar(255),
        REMOVE_COMMENT varchar(255),
        SOURCE_NO varchar(255),
        INVOICE_ID int8,
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

    create table AC_SCTY_CHRG_CODE (
        ID int8 not null,
        ACTIVE boolean,
        AMOUNT numeric(19, 2) not null,
        AMOUNT_DESCRIPTION varchar(255) not null,
        DESCRIPTION varchar(255) not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        OFFENSE varchar(255) not null,
        OFFENSE_DESCRIPTION varchar(255) not null,
        SECTION varchar(255) not null,
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
        AMOUNT numeric(19, 2) not null,
        START_DATE timestamp,
        END_DATE timestamp,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        ACTIVE boolean,
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
        STUDENT_STATUS int4,
        ID int8 not null,
        COHORT_CODE_ID int8,
        RESIDENCY_CODE_ID int8,
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

    create table AC_STDY_MODE (
        ID int8 not null,
        CODE varchar(255) not null,
        DESCRIPTION_EN varchar(255) not null,
        DESCRIPTION_MS varchar(255) not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        PREFIX varchar(255) not null,
        primary key (ID)
    );

    create table AC_STL (
        ID int8 not null,
        AMOUNT numeric(19, 2) not null,
        DESCRIPTION varchar(255) not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        REFERENCE_NO varchar(255) not null,
        SOURCE_NO varchar(255),
        STL_STATUS int4 not null,
        ACCOUNT_ID int8,
        SESSION_ID int8,
        primary key (ID)
    );

    create table AC_STLT (
        ID int8 not null,
        DESCRIPTION varchar(255) not null,
        EXECUTED boolean,
        ISSUED_DATE timestamp,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        REFERENCE_NO varchar(255) not null,
        SOURCE_NO varchar(255),
        SESSION_ID int8,
        primary key (ID)
    );

    create table AC_STLT_ITEM (
        ID int8 not null,
        BALANCE_AMOUNT numeric(19, 2) not null,
        FEE_AMOUNT numeric(19, 2) not null,
        LOAN_AMOUNT numeric(19, 2) not null,
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        NETT_AMOUNT numeric(19, 2) not null,
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

    create table AC_TAX_CODE (
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
        TAX_RATE numeric (19, 2),
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

    create table AC_WAVR_APLN (
        ID int8 not null,
        AUDIT_NO varchar(255),
        BALANCE numeric(19, 2) not null,
        CANCEL_COMMENT varchar(255),
        DESCRIPTION varchar(255),
        EFFECTIVE_BALANCE numeric(19, 2) not null,
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
        GRACED_AMOUNT numeric(19, 2) not null,
        MEMO varchar(255),
        C_TS timestamp,
        C_ID int8,
        D_TS timestamp,
        D_ID int8,
        M_TS timestamp,
        M_ID int8,
        M_ST int4,
        REASON varchar(255),
        REFERENCE_NO varchar(255),
        REMOVE_COMMENT varchar(255),
        SOURCE_NO varchar(255),
        WAIVED_AMOUNT numeric(19, 2) not null,
        ACCOUNT_ID int8,
        SESSION_ID int8,
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
        add constraint UK_ajo4qavo4vyq6jtt2xirmwsix unique (CODE);

    alter table AC_ACCT_CHRG
        add constraint UK_ojhm9q9q2f9cp1fp0qa4791b unique (REFERENCE_NO);

    alter table AC_ACDM_SESN
        add constraint UK_m96040pp8um8tl90wrggobq1m unique (CODE);

    alter table AC_ACTR
        add constraint UK_20dgcebqglh0294filrie2udq unique (IDENTITY_NO);

    alter table AC_BANK_CODE
        add constraint UK_ige90lfk9a8cmikt70bs8q8gf unique (CODE);

    alter table AC_BANK_CODE
        add constraint UK_n47g6j6o605xn376al3ohj5hi unique (IBG_CODE);

    alter table AC_BANK_CODE
        add constraint UK_j0fsw80ly5hte8rvqk5o0rhcs unique (SWIFT_CODE);

    alter table AC_CHRG_CODE
        add constraint UK_en0irx7xftckn12cblkf7jv2y unique (CODE);

    alter table AC_CHRT_CODE
        add constraint UK_jq9ithor1ni7nukp0t3s80laf unique (CODE);

    alter table AC_CITY_CODE
        add constraint UK_rjdfwk18b6gfhakf1va27qkdl unique (CODE);

    alter table AC_CMPS_CODE
        add constraint UK_p84xk90wsbvuyu8p95nogfynu unique (CODE);

    alter table AC_CNTY_CODE
        add constraint UK_6dx6hyt321xsffv7cjvfp7egg unique (CODE);

    alter table AC_DSCT_CODE
        add constraint UK_aoy84lupr60brr3csdv3pv6p7 unique (CODE);

    alter table AC_EMAL_QUEU
        add constraint UK_hvi7wtxspuvvycml836ocwtv unique (CODE);

    alter table AC_EMIL_TMPT
        add constraint UK_esbpeomsubq67hfwj70741v6s unique (CODE);

    alter table AC_FCTY_CODE
        add constraint UK_gb7lc837og3v53wfb0uuyaort unique (CODE);

    alter table AC_FEE_SCDL
        add constraint UK_a32cvvl18fkny9vfelysg3qwv unique (CODE);

    alter table AC_MODL
        add constraint UK_mkldgtkix1t3f5v6t686bcy31 unique (CANONICAL_CODE);

    alter table AC_MODL
        add constraint UK_mwra0o0dp7939j0gnfaoipc5v unique (CODE);

    alter table AC_NTLY_CODE
        add constraint UK_1hsjcgwq82dbgfg4wqn6a3x83 unique (CODE);

    alter table AC_PCPL
        add constraint UK_nursbuo1lo7429txvvbykqxf3 unique (NAME);

    alter table AC_PRGM_CODE
        add constraint UK_l2jdhwoc9lfw54ex525au2dcv unique (CODE);

    alter table AC_RFRN_NO
        add constraint UK_qlemie3464w4yf7j3gt7wnoj6 unique (CODE);

    alter table AC_RSCY_CODE
        add constraint UK_4g6qbgh5rriyhydf0yf8orkbo unique (CODE);

    alter table AC_STDY_CNTR_CODE
        add constraint UK_gg5t5jjhlycdxkmqqt41fd32t unique (CODE);

    alter table AC_STDY_MODE
        add constraint UK_a1jdyqp2pigso8yue4g452peo unique (CODE);

    alter table AC_STL
        add constraint UK_lyasm97ufbbi6v6lns0p25ape unique (REFERENCE_NO);

    alter table AC_STL
        add constraint UK_hasytwc7rx8f2knrfdgmfnesb unique (SOURCE_NO);

    alter table AC_STLT
        add constraint UK_2f100eucl1eod3s40a1pq464l unique (REFERENCE_NO);

    alter table AC_STLT
        add constraint UK_jegtaoei5ldllb2h09f9jqx98 unique (SOURCE_NO);

    alter table AC_STTE_CODE
        add constraint UK_dh4c03mf0vl4odu77jc5wxsn2 unique (CODE);

    alter table AC_TAX_CODE
        add constraint UK_nxhcj19rqmt8iivtq7xfu69p1 unique (CODE);

    alter table AC_USER
        add constraint UK_bb0r87m6t5xpf1bt1ttc6m32 unique (EMAIL);

    alter table AC_ACCT
        add constraint FK_874n8x3rs4gbxyyvcsisq9vwv
        foreign key (ACTOR_ID)
        references AC_ACTR;

    alter table AC_ACCT_CHRG
        add constraint FK_hmi56t60b9ai51shufk9o63nw
        foreign key (ACCOUNT_ID)
        references AC_ACCT;

    alter table AC_ACCT_CHRG
        add constraint FK_1pi4606wcpiir5b1fve6uh8c6
        foreign key (COHORT_CODE_ID)
        references AC_CHRT_CODE;

    alter table AC_ACCT_CHRG
        add constraint FK_bnblgrmw2hvfi9b4xwg6t1fam
        foreign key (INVOICE_ID)
        references AC_INVC;

    alter table AC_ACCT_CHRG
        add constraint FK_2wqjbqqd99e9jfdg57wsdh6ys
        foreign key (SESSION_ID)
        references AC_ACDM_SESN;

    alter table AC_ACCT_CHRG
        add constraint FK_cjojy4m3cr24g4hx7n23fnuge
        foreign key (STUDY_MODE_ID)
        references AC_STDY_MODE;

    alter table AC_ACCT_STL
        add constraint FK_e4ftnj68uuggufmccnc1tm136
        foreign key (ACCOUNT_ID)
        references AC_ACCT;

    alter table AC_ACCT_STL
        add constraint FK_7gs5l65kl1op0p9f9r5g54dxn
        foreign key (SESSION_ID)
        references AC_ACDM_SESN;

    alter table AC_ACCT_TRSN
        add constraint FK_7scem81vjyp92ew1quqqrwebj
        foreign key (ACCOUNT_ID)
        references AC_ACCT;

    alter table AC_ACCT_TRSN
        add constraint FK_tmeqyaoy1umx2f8t50j3m05oe
        foreign key (CHARGE_CODE_ID)
        references AC_CHRG_CODE;

    alter table AC_ACCT_TRSN
        add constraint FK_gok5lgsyud20w75frwjala98a
        foreign key (SESSION_ID)
        references AC_ACDM_SESN;

    alter table AC_ACCT_WAVR
        add constraint FK_7he1r0ld5dv236x5eu7yj4ktm
        foreign key (ACCOUNT_ID)
        references AC_ACCT;

    alter table AC_ACCT_WAVR
        add constraint FK_5wf5loa65e073204ius4wpe3i
        foreign key (SESSION_ID)
        references AC_ACDM_SESN;
    
    alter table AC_ADVC_PYMT 
        add constraint FK_f91f9no80f8qtl6r92n08r32n 
        foreign key (RECEIPTS_ID) 
        references AC_RCPT;

    alter table AC_BSRY_RCPT
        add constraint FK_jc1lhth7un8oggnbjsew0mhs8
        foreign key (ID)
        references AC_RCPT;

    alter table AC_CDIT_NOTE
        add constraint FK_5p75fcqqvmyqo7m945gf6xaj3
        foreign key (CHARGE_CODE_ID)
        references AC_CHRG_CODE;

    alter table AC_CDIT_NOTE
        add constraint FK_kn1ige6q5d3um3d1y9l1vpsua
        foreign key (INVOICE_ID)
        references AC_INVC;

    alter table AC_CDIT_NOTE_ITEM
        add constraint FK_21e2oam0dhnb0qlcavpy8iheg
        foreign key (CHARGE_CODE_ID)
        references AC_CHRG_CODE;

    alter table AC_CDIT_NOTE_ITEM
        add constraint FK_rhnoistpmgs6ikwsj2wrl9f8h
        foreign key (CREDIT_NOTE_ID)
        references AC_CDIT_NOTE;

    alter table AC_CHRG_CODE
        add constraint FK_3j9nre46398unh5i1rwdx2eg
        foreign key (TAX_CODE_ID)
        references AC_TAX_CODE;

    alter table AC_CHRT_CODE
        add constraint FK_r6x0t8jgb661ibpbd7wsgibq
        foreign key (PROGRAM_CODE_ID)
        references AC_PRGM_CODE;

    alter table AC_CITY_CODE
        add constraint FK_mfu391fdemh19u1mhdby3yy4u
        foreign key (STATE_CODE_ID)
        references AC_STTE_CODE;

    alter table AC_CVRG
        add constraint FK_n995653hwah8dwqqfmc04aans
        foreign key (CHARGE_CODE_ID)
        references AC_CHRG_CODE;

    alter table AC_CVRG
        add constraint FK_pb6bu58vuo9an2pn17qb2trlm
        foreign key (SPONSOR_ID)
        references AC_SPSR;

    alter table AC_DBIT_NOTE
        add constraint FK_h8896h8o9jer8nhbs7soqncdu
        foreign key (CHARGE_CODE_ID)
        references AC_CHRG_CODE;

    alter table AC_DBIT_NOTE
        add constraint FK_b5s7as6etxcj3i06ro0orbovc
        foreign key (INVOICE_ID)
        references AC_INVC;

    alter table AC_DBIT_NOTE_ITEM
        add constraint FK_7jvhwik13ryr7liw2lun71t64
        foreign key (CHARGE_CODE_ID)
        references AC_CHRG_CODE;

    alter table AC_DBIT_NOTE_ITEM
        add constraint FK_2h8k5x0vsyvlwesr0dkq3gfbo
        foreign key (DEBIT_NOTE_ID)
        references AC_DBIT_NOTE;

    alter table AC_ELTC_RCPT
        add constraint FK_mi3q81i59xmlrn51q9pxlx71k
        foreign key (ID)
        references AC_RCPT;

    alter table AC_FEE_SCDL
        add constraint FK_9bty3yjbg6enkxnmd3mc8g9ee
        foreign key (COHORT_CODE_ID)
        references AC_CHRT_CODE;

    alter table AC_FEE_SCDL
        add constraint FK_bkb7tplkos1ef7s4spyh0e56r
        foreign key (RESIDENCY_CODE_ID)
        references AC_RSCY_CODE;

    alter table AC_FEE_SCDL
        add constraint FK_ge5avvbfwo8vj96fn826i6jta
        foreign key (STUDY_MODE_ID)
        references AC_STDY_MODE;

    alter table AC_FEE_SCDL_ITEM
        add constraint FK_6pew9bxlby83rc5bifnwna9uq
        foreign key (CHARGE_CODE_ID)
        references AC_CHRG_CODE;

    alter table AC_FEE_SCDL_ITEM
        add constraint FK_ekfmm6poyf27bgvfnvlyk8ad3
        foreign key (SCHEDULE_ID)
        references AC_FEE_SCDL;

    alter table AC_GNRL_RCPT
        add constraint FK_tn50m44nrh32jegvbalyew9k6
        foreign key (ID)
        references AC_RCPT;

    alter table AC_GROP
        add constraint FK_oyukdv0gy0cx7d1v0c9dky5wk
        foreign key (ID)
        references AC_PCPL;

    alter table AC_GROP_MMBR
        add constraint FK_rfpjc48ul656ba82r4c6jynyu
        foreign key (GROUP_ID)
        references AC_GROP;

    alter table AC_GROP_MMBR
        add constraint FK_770jh71hts8dap5ljwxtvgby0
        foreign key (PRINCIPAL_ID)
        references AC_PCPL;

    alter table AC_INVC
        add constraint FK_lqa5mht7w78noknfxwfeyopuq
        foreign key (ACCOUNT_ID)
        references AC_ACCT;

    alter table AC_INVC
        add constraint FK_sk12qvuxady19d2vh9v75c52p
        foreign key (SESSION_ID)
        references AC_ACDM_SESN;

    alter table AC_INVC_ITEM
        add constraint FK_tl5yy4kfw6sbodu0jgbgo3ek8
        foreign key (CHARGE_CODE_ID)
        references AC_CHRG_CODE;

    alter table AC_INVC_ITEM
        add constraint FK_g7nihxqxxqx22a3mjlwodfl43
        foreign key (INVOICE_ID)
        references AC_INVC;

    alter table AC_KNOF
        add constraint FK_f91f9no80f8qtl6r92n08r32n
        foreign key (INVOICE_ID)
        references AC_INVC;

    alter table AC_PCPL_ROLE
        add constraint FK_ofdb3qau30ii76rhetdhgtn2r
        foreign key (PRINCIPAL_ID)
        references AC_PCPL;

    alter table AC_PRGM_CODE
        add constraint FK_985qolx3dscttpk3p5bj85yop
        foreign key (FACULTY_CODE_ID)
        references AC_FCTY_CODE;

    alter table AC_PRMO_CODE_ITEM
        add constraint FK_72n2se3cskvb4lkn9ybw93650
        foreign key (ACCOUNT_ID)
        references AC_ACCT;

    alter table AC_PRMO_CODE_ITEM
        add constraint FK_f3ko5d4k5ak8wf94q5wqvn9j4
        foreign key (PROMO_CODE_ID)
        references AC_PRMO_CODE;

    alter table AC_RCPT
        add constraint FK_fnxq30p92pj8aepx0smoaexos
        foreign key (ACCOUNT_ID)
        references AC_ACCT;

    alter table AC_RCPT_INVC
        add constraint FK_rpj1wg5pqyv2jpqmu6pm5g9pc
        foreign key (INVOICE_ID)
        references AC_INVC;

    alter table AC_RCPT_INVC
        add constraint FK_su460f3lvgsf3h4079asbbi3s
        foreign key (RECEIPT_ID)
        references AC_RCPT;

    alter table AC_RCPT_ITEM
        add constraint FK_dtmemhqcipfpet4tyyrd9copt
        foreign key (CHARGE_CODE_ID)
        references AC_CHRG_CODE;

    alter table AC_RCPT_ITEM
        add constraint FK_pvm4sa8mdj3fccu5i73wjl5e8
        foreign key (INVOICE_ID)
        references AC_INVC;

    alter table AC_RCPT_ITEM
        add constraint FK_369xjsjo5e6svl0ynmlab782g
        foreign key (RECEIPT_ID)
        references AC_RCPT;

    alter table AC_RFND_PYMT
        add constraint FK_i9dv9aqu0m3layaisbx625v83
        foreign key (INVOICE_ID)
        references AC_INVC;

    alter table AC_SMDL
        add constraint FK_l5iijx9gif3hcfpnfhketwebo
        foreign key (MODULE_ID)
        references AC_MODL;

    alter table AC_SPHP
        add constraint FK_st6klbsxd7yrnqvdrvy0b53n2
        foreign key (SPONSOR_ID)
        references AC_SPSR;

    alter table AC_SPHP
        add constraint FK_ng5avp5mj8tim7n76tyny4bst
        foreign key (STUDENT_ID)
        references AC_STDN;

    alter table AC_SPSR
        add constraint FK_16abykipvmbjv2dwcrni1kg3w
        foreign key (ID)
        references AC_ACTR;

    alter table AC_STAF
        add constraint FK_apwd3tfq9378q7lxj4yjq1mq5
        foreign key (ID)
        references AC_ACTR;

    alter table AC_STDN
        add constraint FK_94tusf6tsiftgfi48jy9djglt
        foreign key (COHORT_CODE_ID)
        references AC_CHRT_CODE;

    alter table AC_STDN
        add constraint FK_trktpa3g0uybvo8sol6i6dffe
        foreign key (RESIDENCY_CODE_ID)
        references AC_RSCY_CODE;

    alter table AC_STDN
        add constraint FK_qjkb9u444rjhpaq7wk7o5f931
        foreign key (ID)
        references AC_ACTR;

    alter table AC_STL
        add constraint FK_qqe2ryeyt83dbofknllb88ci0
        foreign key (ACCOUNT_ID)
        references AC_ACCT;

    alter table AC_STL
        add constraint FK_p5oh5k8hyr9vkooi9qgjq4b43
        foreign key (SESSION_ID)
        references AC_ACDM_SESN;

    alter table AC_STLT
        add constraint FK_qg6x86suvsjp6f83rhg7eiiek
        foreign key (SESSION_ID)
        references AC_ACDM_SESN;

    alter table AC_STLT_ITEM
        add constraint FK_l4h9gkypwx5pxy6sy0vjlj8yd
        foreign key (ACCOUNT_ID)
        references AC_ACCT;

    alter table AC_STLT_ITEM
        add constraint FK_tk697i3lhyu99oi68vl3sk1sg
        foreign key (INVOICE_ID)
        references AC_INVC;

    alter table AC_STLT_ITEM
        add constraint FK_q96jbbc43hfu81xf9y06k8f40
        foreign key (SETTLEMENT_ID)
        references AC_STLT;

    alter table AC_STTE_CODE
        add constraint FK_3ht39tir20as2xgwfo9sg5qc9
        foreign key (COUNTRY_CODE_ID)
        references AC_CNTY_CODE;

    alter table AC_USER
        add constraint FK_i67hqx2uqvpvtqs2kqh8yth90
        foreign key (ACTOR_ID)
        references AC_ACTR;

    alter table AC_USER
        add constraint FK_aqtnyxfkopl49r5chh7es5fv3
        foreign key (ID)
        references AC_PCPL;

    alter table AC_WAVR_APLN
        add constraint FK_1coqllo22hpahq7m73p6u1g74
        foreign key (ACCOUNT_ID)
        references AC_ACCT;

    alter table AC_WAVR_APLN
        add constraint FK_9biewrgydh2os939x0wy4bw6
        foreign key (SESSION_ID)
        references AC_ACDM_SESN;

    create sequence SEQ_ACCT_CHRG;

    create sequence SQ_AC_ACCT;

    create sequence SQ_AC_ACCT_STL;

    create sequence SQ_AC_ACCT_TRSN;

    create sequence SQ_AC_ACCT_WAVR;

    create sequence SQ_AC_ACDM_SESN;

    create sequence SQ_AC_ACTR;
    
    create sequence SQ_AC_ADVC_PYMT;

    create sequence SQ_AC_AUDT;

    create sequence SQ_AC_BANK_CODE;

    create sequence SQ_AC_CDIT_NOTE;

    create sequence SQ_AC_CDIT_NOTE_ITEM;

    create sequence SQ_AC_CHRG_CODE;

    create sequence SQ_AC_CHRT_CODE;

    create sequence SQ_AC_CITY_CODE;

    create sequence SQ_AC_CMPS_CODE;

    create sequence SQ_AC_CNFG;

    create sequence SQ_AC_CNTY_CODE;

    create sequence SQ_AC_CVRG;

    create sequence SQ_AC_DBIT_NOTE;

    create sequence SQ_AC_DBIT_NOTE_ITEM;

    create sequence SQ_AC_DSCT_CODE;

    create sequence SQ_AC_EMAL_QUEU;

    create sequence SQ_AC_EMIL_TMPT;

    create sequence SQ_AC_FCTY_CODE;

    create sequence SQ_AC_FEE_SCDL;

    create sequence SQ_AC_FEE_SCDL_ITEM;

    create sequence SQ_AC_GROP_MMBR;

    create sequence SQ_AC_INVC;

    create sequence SQ_AC_INVC_ITEM;

    create sequence SQ_AC_KNOF;

    create sequence SQ_AC_MODL;

    create sequence SQ_AC_NTLY_CODE;

    create sequence SQ_AC_PCPL;

    create sequence SQ_AC_PCPL_ROLE;

    create sequence SQ_AC_PRGM_CODE;

    create sequence SQ_AC_PRMO_CODE;

    create sequence SQ_AC_PRMO_CODE_ITEM;

    create sequence SQ_AC_RCPT;

    create sequence SQ_AC_RCPT_INVC;

    create sequence SQ_AC_RFND_PYMT;

    create sequence SQ_AC_RFRN_NO;

    create sequence SQ_AC_RSCY_CODE;

    create sequence SQ_AC_SCTY_CHRG_CODE;

    create sequence SQ_AC_SMDL;

    create sequence SQ_AC_SPHP;

    create sequence SQ_AC_STDY_CNTR_CODE;

    create sequence SQ_AC_STDY_MODE;

    create sequence SQ_AC_STL;

    create sequence SQ_AC_STLT;

    create sequence SQ_AC_STLT_ITEM;

    create sequence SQ_AC_STTE_CODE;

    create sequence SQ_AC_TAX_CODE;

    create sequence SQ_AC_WAVR_APLN;

    create sequence SQ_AC_WTCH;
