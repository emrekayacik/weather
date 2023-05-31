DROP TABLE IF EXISTS weather_saved;
DROP TABLE IF EXISTS user_def;

CREATE TABLE IF NOT EXISTS user_def
(
    id bigint NOT NULL,
    created_date timestamp(6) without time zone,
    id_created_user bigint,
    modified_date timestamp(6) without time zone,
    id_modified_user bigint,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    phone_number character varying(50) COLLATE pg_catalog."default" NOT NULL,
    role character varying(255) COLLATE pg_catalog."default",
    surname character varying(100) COLLATE pg_catalog."default",
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_def_pkey PRIMARY KEY (id),
    CONSTRAINT uk_73ig7ey55utc9wx7l6h7h5l1r UNIQUE (email),
    CONSTRAINT uk_jjuj2hefw34jelwmaxo482chj UNIQUE (username),
    CONSTRAINT uk_pnkah3d387387mgt4wmcu41h7 UNIQUE (phone_number),
    CONSTRAINT user_def_role_check CHECK (role::text = ANY (ARRAY['USER'::character varying, 'ADMIN'::character varying]::text[]))
    );

CREATE TABLE IF NOT EXISTS public.weather_saved
(
    id bigint NOT NULL,
    created_date timestamp(6) without time zone,
    id_created_user bigint,
    modified_date timestamp(6) without time zone,
    id_modified_user bigint,
    city_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT weather_saved_pkey PRIMARY KEY (id),
    CONSTRAINT uq_user_city_name UNIQUE (city_name, user_id),
    CONSTRAINT fkfy55rcwi5a824s375goxgt7ve FOREIGN KEY (user_id)
    REFERENCES public.user_def (id) MATCH SIMPLE
                              ON UPDATE NO ACTION
                              ON DELETE NO ACTION
    )
;


CREATE SEQUENCE IF NOT EXISTS SQ_USER start with 1 increment by 1;
CREATE SEQUENCE IF NOT EXISTS SQ_WEATHER_SAVED start with 1 increment by 1;