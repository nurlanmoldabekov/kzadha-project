CREATE SEQUENCE public.hibernate_sequence
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE public.t_users
(
    id bigint PRIMARY KEY DEFAULT nextval('hibernate_sequence'::regclass),
    vname character varying(255) NOT NULL,
    vpassword character varying(255) NOT NULL,
    vemail character varying(255) NOT NULL UNIQUE,
    vroles character varying(20) NOT NULL
);
CREATE TABLE public.t_tasks
(
    id bigint PRIMARY KEY DEFAULT nextval('hibernate_sequence'::regclass),
    vdescription character varying(255) NOT NULL,
    dcreatedate TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    dupdatedate TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    vstatus character varying(20) NOT NULL,
    nuserid bigint NOT NULL,
    nexecutoruserid bigint,
    CONSTRAINT tasks_userid FOREIGN KEY (nuserid)
        REFERENCES public.t_users (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT tasks_executoruserid FOREIGN KEY (nexecutoruserid)
        REFERENCES public.t_users (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE public.t_userstotasks
(
    nuserid bigint NOT NULL,
    ntaskid bigint NOT NULL,
    issigned boolean DEFAULT false,
    UNIQUE (nuserid, ntaskid),
    CONSTRAINT userstotasks_userid FOREIGN KEY (nuserid)
        REFERENCES public.t_users (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT userstotasks_taskid FOREIGN KEY (ntaskid)
        REFERENCES public.t_tasks (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE
);


INSERT INTO t_users (vemail, vpassword, vname, vroles)
VALUES ('npofi@inbox.ru','$2a$10$oh4.vfAj05PUgRysQA8geuFxckUeTMVTVT7oWwelSvsHMfstBrhYy','NURLAN',	'ROLE_ADMIN');
