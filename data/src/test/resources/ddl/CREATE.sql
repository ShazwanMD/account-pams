-- TODO

CREATE TABLE AC_PCPL (
  ID              BIGSERIAL    NOT NULL PRIMARY KEY,
  NAME            VARCHAR(100) NOT NULL,
  ENABLED         BOOLEAN      NOT NULL,
  LOCKED          BOOLEAN      NOT NULL,
  PRINCIPAL_TYPE  INT          NOT NULL,
  M_ST            INT          NOT NULL,
  C_ID            INT          NOT NULL,
  C_TS            TIMESTAMP     ,
  D_ID            INT           ,
  M_ID            INT           ,
  D_TS            TIMESTAMP
  --  CONSTRAINT WHATEVER_FK FOREIGN KEY (WHATEVER_ID) REFERENCES WHATEVER_TABLE (WHATEVER_ID)
);

CREATE TABLE AC_PCPL_ROLE (
  ID              BIGSERIAL    NOT NULL PRIMARY KEY,
  PRINCIPAL_ID    BIGSERIAL    NOT NULL,
  ROLE_TYPE       INT          NOT NULL,
  M_TS            TIMESTAMP     ,
  C_ID            INT           ,
  D_ID            INT           ,
  M_ID            INT           ,
  C_TS            TIMESTAMP     ,
  D_TS            TIMESTAMP
);

CREATE TABLE AC_USER (
  ID              BIGSERIAL    NOT NULL PRIMARY KEY,
  REAL_NAME       VARCHAR(100) NOT NULL,
  EMAIL           VARCHAR(100) NOT NULL,
  PASSWORD        VARCHAR(100) NOT NULL,
  M_TS            INT          ,
  C_ID            INT          ,
  C_TS            TIMESTAMP
);

CREATE TABLE AC_GROP (
  ID              BIGSERIAL    NOT NULL PRIMARY KEY
);

CREATE TABLE AC_GROP_MMBR (
  ID              BIGSERIAL    NOT NULL PRIMARY KEY,
  PRINCIPAL_ID    BIGSERIAL    NOT NULL,
  GROUP_ID        BIGSERIAL    NOT NULL,
  M_TS            INT          ,
  M_ID            INT          ,
  C_ID            INT          ,
  D_ID            INT          ,
  C_TS            TIMESTAMP    ,
  D_TS            TIMESTAMP    ,

  CONSTRAINT FOREIGN_FK_1 FOREIGN KEY (PRINCIPAL_ID)  REFERENCES AC_PCPL (ID),
  CONSTRAINT FOREIGN_FK_2 FOREIGN KEY (GROUP_ID)      REFERENCES AC_GROP (ID)
);

GRANT ALL PRIVILEGES ON DATABASE account to account;