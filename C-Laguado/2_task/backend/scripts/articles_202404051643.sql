
-- public.articles definition
-- Drop table
-- DROP TABLE public.articles;

CREATE TABLE public.articles (
	id serial4 NOT NULL,
	"name" varchar NOT NULL,
	idtype int4 NOT NULL,
	isactive bool NULL DEFAULT true
);

INSERT INTO public.articles ("name",idtype,isactive) VALUES
	 ('Article 1',1,true),
	 ('Article 2',2,false),
	 ('Article 3',1,true),
	 ('Article 4',1,false);
