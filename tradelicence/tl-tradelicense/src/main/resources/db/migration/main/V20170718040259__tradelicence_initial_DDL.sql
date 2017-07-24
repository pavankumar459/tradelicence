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

------------------START------------------
CREATE TABLE egtl_mstr_sub_category(
    id bigint NOT NULL,
    tenantId character varying NOT NULL,
    code character varying(256) NOT NULL,
    name character varying(256) NOT NULL,
    businessNatureId character varying(256) NOT NULL,
    categoryId bigint NOT NULL,
    createdBy character varying,
    lastModifiedBy character varying,
    createdTime bigint,
    lastModifiedTime bigint
);

CREATE SEQUENCE seq_egtl_mstr_sub_category;

ALTER TABLE ONLY egtl_mstr_sub_category 
	ALTER COLUMN id SET DEFAULT nextval('seq_egtl_mstr_sub_category');
ALTER TABLE ONLY egtl_mstr_sub_category
    ADD CONSTRAINT pk_egtl_mstr_sub_category PRIMARY KEY (id);
ALTER TABLE ONLY egtl_mstr_sub_category
    ADD CONSTRAINT fk_subcategory_category FOREIGN KEY (categoryId) REFERENCES egtl_mstr_category(id);
ALTER TABLE ONLY egtl_mstr_sub_category
    ADD CONSTRAINT unq_tlsubcategory_code UNIQUE (code);
ALTER TABLE ONLY egtl_mstr_sub_category
    ADD CONSTRAINT unq_tlsubcategory_name UNIQUE (name);
-------------------END-------------------
    
-------------------START-------------------
CREATE TABLE egtl_sub_category_details (
    id bigint NOT NULL,
    subCategoryId bigint NOT NULL,
    feeType character varying(50) NOT NULL,
    uomId bigint NOT NULL,
    ratetype character varying(50) NOT NULL
);

CREATE SEQUENCE seq_egtl_sub_category_details;

ALTER TABLE ONLY egtl_sub_category_details 
	ALTER COLUMN id SET DEFAULT nextval('seq_egtl_sub_category_details');
ALTER TABLE ONLY egtl_sub_category_details
    ADD CONSTRAINT pk_egtl_sub_category_details PRIMARY KEY (id);
ALTER TABLE ONLY egtl_sub_category_details
    ADD CONSTRAINT fk_scdetails_subcategory FOREIGN KEY (subCategoryId) REFERENCES egtl_mstr_sub_category(id);
ALTER TABLE ONLY egtl_sub_category_details
    ADD CONSTRAINT fk_scdetails_uom FOREIGN KEY (uomId) REFERENCES egtl_mstr_uom(id);
-------------------END-------------------