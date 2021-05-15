BEGIN;

CREATE TABLE public.users
(
    id integer,
    email character varying(150) NOT NULL,
    password character varying(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.candidates
(
    candidates_id integer NOT NULL,
    firstname character varying(30) NOT NULL,
    lastname character varying(50) NOT NULL,
    tc_no char(11),
    birth_year date,
    PRIMARY KEY (candidates_id)
);

CREATE TABLE public.system_employees
(
    employees_id integer,
    firstname character varying(30) NOT NULL,
    lastname character varying(50) NOT NULL,
    PRIMARY KEY (employees_id)
);

CREATE TABLE public.employers
(
    employer_id integer,
    company_name character varying(100) NOT NULL,
    telephone_no char(11) NOT NULL,
    web_address character varying(150) NOT NULL,
    PRIMARY KEY (employer_id)
);

CREATE TABLE public.job_position_names
(
    id integer,
    job_name character varying(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.login
(
    id integer,
    is_valid boolean,
    PRIMARY KEY (id)
);

CREATE TABLE public.HRMS_employees_verification
(
    id integer,
    employer_id integer UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.mernis_verification
(
    id integer,
    PRIMARY KEY (id)
);

CREATE TABLE public.base_verification
(
    id integer,
    email character varying(150) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.users
    ADD FOREIGN KEY (id)
        REFERENCES public.employers (employer_id)
    NOT VALID;


ALTER TABLE public.users
    ADD FOREIGN KEY (id)
        REFERENCES public.candidates (candidates_id)
    NOT VALID;


ALTER TABLE public.users
    ADD FOREIGN KEY (id)
        REFERENCES public.system_employees (employees_id)
    NOT VALID;


ALTER TABLE public.candidates
    ADD FOREIGN KEY (candidates_id)
        REFERENCES public.mernis_verification (id)
    NOT VALID;


ALTER TABLE public.base_verification
    ADD FOREIGN KEY (id)
        REFERENCES public.mernis_verification (id)
    NOT VALID;


ALTER TABLE public.login
    ADD FOREIGN KEY (id)
        REFERENCES public.base_verification (id)
    NOT VALID;


ALTER TABLE public.employers
    ADD FOREIGN KEY (employer_id)
        REFERENCES public.base_verification (id)
    NOT VALID;


ALTER TABLE public.employers
    ADD FOREIGN KEY (employer_id)
        REFERENCES public.HRMS_employees_verification (employer_id)
    NOT VALID;

END;