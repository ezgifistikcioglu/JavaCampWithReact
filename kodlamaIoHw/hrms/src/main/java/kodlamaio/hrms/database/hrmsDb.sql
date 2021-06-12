-- This script was generated by a beta version of the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE public.advertisements
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    job_position_id integer NOT NULL,
    city_id integer NOT NULL,
    employer_id integer NOT NULL,
    job_description character varying(100) NOT NULL,
    min_salary double precision,
    max_salary double precision,
    is_advertisement_open boolean NOT NULL,
    is_advertisement_active boolean NOT NULL,
    is_advertisement_deleted boolean NOT NULL,
    number_of_open_position integer NOT NULL,
    application_deadline timestamp with time zone NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.cities
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    city_name character varying(80) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.cvs
(
    cv_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    cover_letter character varying(255),
    job_seeker_id integer NOT NULL,
    photo character varying(255),
    PRIMARY KEY (cv_id)
);

CREATE TABLE public.educations
(
    education_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    school_name character varying(100) NOT NULL,
    school_department_name character varying(100) NOT NULL,
    school_start_date date NOT NULL,
    school_graduation_date date,
    created_at timestamp with time zone NOT NULL,
    cv_id integer NOT NULL,
    PRIMARY KEY (education_id)
);

CREATE TABLE public.email_verifications
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_id integer NOT NULL,
    authentication character varying(200) NOT NULL,
    email character varying(100) NOT NULL,
    is_approved boolean NOT NULL,
    created_at timestamp with time zone NOT NULL,
    expiration_date timestamp with time zone NOT NULL,
    activation_date timestamp with time zone,
    PRIMARY KEY (id)
);

CREATE TABLE public.employers
(
    employer_id integer NOT NULL,
    company_name character varying(100) NOT NULL,
    telephone_no character varying(15) NOT NULL,
    web_address character varying(100) NOT NULL,
    PRIMARY KEY (employer_id)
);

CREATE TABLE public.experiences
(
    experience_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    cv_id integer NOT NULL,
    position_id integer NOT NULL,
    business_name character varying(100) NOT NULL,
    business_start_date date NOT NULL,
    business_leaving_date date,
    is_still_working boolean NOT NULL,
    created_at timestamp with time zone NOT NULL,
    PRIMARY KEY (experience_id)
);

CREATE TABLE public.hrms_employees
(
    employees_id integer NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    id integer NOT NULL,
    approval_date timestamp without time zone,
    created_at timestamp without time zone,
    is_approved boolean,
    user_id integer NOT NULL,
    PRIMARY KEY (employees_id)
);

CREATE TABLE public.hrms_employees_verification
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_id integer NOT NULL,
    is_approved boolean NOT NULL,
    created_at timestamp with time zone,
    approval_date time with time zone,
    PRIMARY KEY (id)
);

CREATE TABLE public.job_positions
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    job_name character varying(100) NOT NULL,
    created_date timestamp with time zone,
    is_active_position boolean NOT NULL,
    is_deleted_position boolean NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.job_seekers
(
    job_seeker_id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    firstname character varying(30) NOT NULL,
    lastname character varying(50) NOT NULL,
    tc_no character(11) NOT NULL,
    birth_year integer NOT NULL,
    PRIMARY KEY (job_seeker_id)
);

CREATE TABLE public.languages
(
    language_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    created_at timestamp with time zone NOT NULL,
    cv_id integer NOT NULL,
    language_level_number integer NOT NULL,
    language_name character varying(255) NOT NULL,
    PRIMARY KEY (language_id)
);

CREATE TABLE public.mernis_verifications
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_id integer NOT NULL,
    is_approved boolean NOT NULL,
    created_at timestamp with time zone,
    approval_date time with time zone,
    PRIMARY KEY (id)
);

CREATE TABLE public.photos
(
    id integer NOT NULL,
    job_seeker_id integer NOT NULL,
    photo_url character varying(255),
    PRIMARY KEY (id)
);

CREATE TABLE public.programming_skills
(
    skill_id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    created_at timestamp with time zone NOT NULL,
    cv_id integer NOT NULL,
    programming_name character varying(255) NOT NULL,
    programming_skill_level integer NOT NULL,
    PRIMARY KEY (skill_id)
);

CREATE TABLE public.social_medias
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    created_at timestamp with time zone NOT NULL,
    cv_id integer NOT NULL,
    social_media_name character varying(255) NOT NULL,
    social_media_url character varying(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.users
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    email character varying(150) NOT NULL,
    password character varying(100) NOT NULL,
    confirm_password character varying(100) NOT NULL,
    created_date timestamp with time zone,
    is_user_deleted boolean NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.advertisements
    ADD FOREIGN KEY (city_id)
    REFERENCES public.cities (id)
    NOT VALID;


ALTER TABLE public.advertisements
    ADD FOREIGN KEY (employer_id)
    REFERENCES public.employers (employer_id)
    NOT VALID;


ALTER TABLE public.advertisements
    ADD FOREIGN KEY (employer_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.advertisements
    ADD FOREIGN KEY (job_position_id)
    REFERENCES public.job_positions (id)
    NOT VALID;


ALTER TABLE public.cvs
    ADD FOREIGN KEY (job_seeker_id)
    REFERENCES public.job_seekers (job_seeker_id)
    NOT VALID;


ALTER TABLE public.cvs
    ADD FOREIGN KEY (job_seeker_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.educations
    ADD FOREIGN KEY (cv_id)
    REFERENCES public.cvs (cv_id)
    NOT VALID;


ALTER TABLE public.email_verifications
    ADD FOREIGN KEY (user_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.employers
    ADD FOREIGN KEY (employer_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.experiences
    ADD FOREIGN KEY (cv_id)
    REFERENCES public.cvs (cv_id)
    NOT VALID;


ALTER TABLE public.experiences
    ADD FOREIGN KEY (position_id)
    REFERENCES public.job_positions (id)
    NOT VALID;


ALTER TABLE public.hrms_employees
    ADD FOREIGN KEY (employees_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.hrms_employees
    ADD FOREIGN KEY (user_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.hrms_employees_verification
    ADD FOREIGN KEY (user_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.job_seekers
    ADD FOREIGN KEY (job_seeker_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.languages
    ADD FOREIGN KEY (cv_id)
    REFERENCES public.cvs (cv_id)
    NOT VALID;


ALTER TABLE public.languages
    ADD FOREIGN KEY (cv_id)
    REFERENCES public.cvs (cv_id)
    NOT VALID;


ALTER TABLE public.mernis_verifications
    ADD FOREIGN KEY (user_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.photos
    ADD FOREIGN KEY (job_seeker_id)
    REFERENCES public.job_seekers (job_seeker_id)
    NOT VALID;


ALTER TABLE public.programming_skills
    ADD FOREIGN KEY (cv_id)
    REFERENCES public.cvs (cv_id)
    NOT VALID;


ALTER TABLE public.programming_skills
    ADD FOREIGN KEY (cv_id)
    REFERENCES public.cvs (cv_id)
    NOT VALID;


ALTER TABLE public.social_medias
    ADD FOREIGN KEY (cv_id)
    REFERENCES public.cvs (cv_id)
    NOT VALID;


ALTER TABLE public.social_medias
    ADD FOREIGN KEY (cv_id)
    REFERENCES public.cvs (cv_id)
    NOT VALID;

END;