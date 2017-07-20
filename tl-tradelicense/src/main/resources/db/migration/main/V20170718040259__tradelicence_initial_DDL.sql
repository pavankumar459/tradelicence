------------------START------------------
CREATE TABLE egtl_mstr_category(
    id bigint NOT NULL,
    tenantId character varying NOT NULL,
    code character varying(256) NOT NULL,
    name character varying(256) NOT NULL,
    createdBy character varying,
    lastModifiedBy character varying,
    createdTime bigint,
    lastModifiedTime bigint
);

CREATE SEQUENCE seq_egtl_mstr_category;

ALTER TABLE ONLY egtl_mstr_category 
	ALTER COLUMN id SET DEFAULT nextval('seq_egtl_mstr_category');
ALTER TABLE ONLY egtl_mstr_category
    ADD CONSTRAINT pk_egtl_mstr_category PRIMARY KEY (id);
ALTER TABLE ONLY egtl_mstr_category
    ADD CONSTRAINT unq_tlcategory_code UNIQUE (code);
ALTER TABLE ONLY egtl_mstr_category
    ADD CONSTRAINT unq_tlcategory_name UNIQUE (name);
-------------------END-------------------

------------------START------------------
CREATE TABLE egtl_mstr_uom(
    id bigint NOT NULL,
    tenantId character varying NOT NULL,
    code character varying(50) NOT NULL,
    name character varying(50) NOT NULL,
    active boolean DEFAULT true,
    createdBy character varying,
    lastModifiedBy character varying,
    createdTime bigint,
    lastModifiedTime bigint
);

CREATE SEQUENCE seq_egtl_mstr_uom;

ALTER TABLE ONLY egtl_mstr_uom 
	ALTER COLUMN id SET DEFAULT nextval('seq_egtl_mstr_uom');
ALTER TABLE ONLY egtl_mstr_uom
    ADD CONSTRAINT pk_egtl_mstr_uom PRIMARY KEY (id);
ALTER TABLE ONLY egtl_mstr_uom
    ADD CONSTRAINT unq_tluom_code UNIQUE (code);
ALTER TABLE ONLY egtl_mstr_uom
    ADD CONSTRAINT unq_tluom_name UNIQUE (name);
-------------------END-------------------

