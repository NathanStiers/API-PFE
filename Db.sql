DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

--ALTER ROLE smgnrtsxjzzkks CONNECTION LIMIT -1;

------------------ TABLE CATEGORIE ------------------

CREATE TABLE public."Categories"
(
    "Id" integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    "Label" character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "Categories_pkey" PRIMARY KEY ("Id")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Categories" OWNER to vpceiywjjzrzdv;
	
------------------ TABLE PERSONNE_CONTACT ------------------
	
CREATE TABLE public."Contact_persons"
(
	"Code" character varying COLLATE pg_catalog."default" NOT NULL,
    "Name" character varying COLLATE pg_catalog."default" NOT NULL,
    "Surname" character varying COLLATE pg_catalog."default" NOT NULL,
    "Phone" character varying COLLATE pg_catalog."default" NOT NULL,
	"Email" character varying COLLATE pg_catalog."default" NOT NULL,
	"Relationship" character varying COLLATE pg_catalog."default" NOT NULL,
	
	
    CONSTRAINT "Contact_persons_pkey" PRIMARY KEY ("Code")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Contact_persons"
    OWNER to vpceiywjjzrzdv;

------------------ TABLE USERS ------------------

CREATE TABLE public."Users"
(
    "Name" character varying COLLATE pg_catalog."default" NOT NULL,
    "Surname" character varying COLLATE pg_catalog."default" NOT NULL,
    "Code" character varying COLLATE pg_catalog."default" NOT NULL,
	"Birthdate" date NOT NULL,
	"Language" character varying COLLATE pg_catalog."default" NOT NULL,
	"Dominance" character varying COLLATE pg_catalog."default" NOT NULL,
	"Schooling" character varying COLLATE pg_catalog."default" NOT NULL,
	"Schooling_type" character varying COLLATE pg_catalog."default" NULL,
	"Schooling_level" character varying COLLATE pg_catalog."default" NOT NULL,
	"Contact_one" character varying NULL DEFAULT NULL,
	"Contact_two" character varying NULL DEFAULT NULL,	
	"Contact_three" character varying NULL DEFAULT NULL,
	
	
	
    CONSTRAINT "Users_pkey" PRIMARY KEY ("Code"),
	CONSTRAINT "Contact_one" FOREIGN KEY ("Contact_one") 
		REFERENCES public."Contact_persons" ("Code") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	CONSTRAINT "Contact_two" FOREIGN KEY ("Contact_two")
        REFERENCES public."Contact_persons" ("Code") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	CONSTRAINT "Contact_three" FOREIGN KEY ("Contact_three")
        REFERENCES public."Contact_persons" ("Code") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
	
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Users"
    OWNER to vpceiywjjzrzdv;
	
------------------ TABLE BESOINS ------------------
	
CREATE TABLE public."Needs"
(
	"Id" integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    "Child" character varying NOT NULL,
    "Need" character varying COLLATE pg_catalog."default" NOT NULL,
    
	
	
    CONSTRAINT "Needs_pkey" PRIMARY KEY ("Id"),
	CONSTRAINT "Child" FOREIGN KEY ("Child")
        REFERENCES public."Users" ("Code") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Needs"
    OWNER to vpceiywjjzrzdv;
	
------------------ TABLE PROFESSIONALS ------------------
	
CREATE TABLE public."Professionals"
(
	"Code" character varying COLLATE pg_catalog."default" NOT NULL,
    "Name" character varying COLLATE pg_catalog."default" NOT NULL,
    "Surname" character varying COLLATE pg_catalog."default" NOT NULL,
	"Profession" character varying COLLATE pg_catalog."default" NOT NULL,
	"Phone" character varying COLLATE pg_catalog."default" NOT NULL,
	"Email" character varying COLLATE pg_catalog."default" NOT NULL,
	
	CONSTRAINT "Professionals_pkey" PRIMARY KEY ("Code")
	
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Professionals"
    OWNER to vpceiywjjzrzdv;
	
------------------ TABLE USERS_PROFESSIONALS ------------------ 


CREATE TABLE public."Users_professionals"
(
	"Id" integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    "User" character varying NOT NULL,
    "Professional" character varying NOT NULL,
    "Mandate" character varying COLLATE pg_catalog."default" NOT NULL,
	"Mandate_date" date,
	"Initial_ask" character varying COLLATE pg_catalog."default" NOT NULL,
	
	
    CONSTRAINT "Users_professionals_pkey" PRIMARY KEY ("Id"),
	CONSTRAINT "User" FOREIGN KEY ("User")
        REFERENCES public."Users" ("Code") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	CONSTRAINT "Professional" FOREIGN KEY ("Professional")
        REFERENCES public."Professionals" ("Code") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Users_professionals"
    OWNER to vpceiywjjzrzdv;	
	
------------------ TABLE ITEMS ------------------
	
CREATE TABLE public."Items"
(
    "Id" integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    "Category" integer NOT NULL,
    "Image" character varying COLLATE pg_catalog."default" NOT NULL,
    "Name" character varying COLLATE pg_catalog."default" NOT NULL,
	
    CONSTRAINT "Items_pkey" PRIMARY KEY ("Id"),
    CONSTRAINT "Category" FOREIGN KEY ("Category")
        REFERENCES public."Categories" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Items"
    OWNER to vpceiywjjzrzdv;
	
------------------ TABLE SHEETS ------------------

CREATE TABLE public."Sheets"
(
    "Id" integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    "User" character varying COLLATE pg_catalog."default" NOT NULL,
    "Date" date NOT NULL,
    CONSTRAINT "Sheets_pkey" PRIMARY KEY ("Id"),
    CONSTRAINT "User" FOREIGN KEY ("User")
        REFERENCES public."Users" ("Code") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Sheets"
    OWNER to vpceiywjjzrzdv;
	
------------------ TABLE SHEETS_ITEMS ------------------
	
CREATE TABLE public."Sheets_items"
(
    "Id" integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    "Sheet_id" integer NOT NULL,
    "Item_id" integer NOT NULL,
    "Love_it" boolean,
    "Need_help" boolean,
    "Wanna_change" boolean,
	"Favorite" boolean NOT NULL DEFAULT false,
	"Comment" character varying NULL,

    CONSTRAINT "Sheets_items_pkey" PRIMARY KEY ("Id"),
    CONSTRAINT "Item_id" FOREIGN KEY ("Item_id")
        REFERENCES public."Items" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "Sheet_id" FOREIGN KEY ("Sheet_id")
        REFERENCES public."Sheets" ("Id") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."Sheets_items"
    OWNER to vpceiywjjzrzdv;
	
------------------ INSERT ------------------

INSERT INTO public."Categories" ("Label") VALUES 
	('Déplacements'),
	('Habitation'),
	('Loisirs'),
	('Nutrition'),
	('Relations et communication'),
	('Responsabilités'),
	('Soins personnels');
	
INSERT INTO public."Items" ("Category","Image","Name") VALUES 
	(1,'/images/deplacements/bus.jpg','Prendre le bus'),
	(1,'/images/deplacements/marcher.jpg','Marcher'),
	(1,'/images/deplacements/traverser.jpg','Traverser'),
	(1,'/images/deplacements/velo.jpg','Faire du vélo'),
	(1,'/images/deplacements/voiture.jpg','Prendre la voiture'),
	(2,'/images/habitation/allumerlumiere.jpg','Allumer la lumière'),
	(2,'/images/habitation/balayer.jpg','Balayer'),
	(2,'/images/habitation/machinealaver.jpg','Utiliser la machine à laver'),
	(2,'/images/habitation/passerdunepiecealautre.jpg','Passer d''une pièce à l''autre'),
	(2,'/images/habitation/prothese.jpg','Utiliser une prothèse'),
	(2,'/images/habitation/rangersonespace.jpg','Ranger son espace'),
	(2,'/images/habitation/vaisselledef.jpg','Faire la vaisselle'),
	(3,'/images/loisirs/casqueetordi.jpg','Utiliser un casque et un ordinateur'),
	(3,'/images/loisirs/ecrire.jpg','Ecrire'),
	(3,'/images/loisirs/lire.jpg','Lire'),
	(3,'/images/loisirs/television.jpg','Regarder la télévision'),
	(4,'/images/nutrition/boiretasse.jpg','Boire dans une tasse'),
	(4,'/images/nutrition/dresserlatable.jpg','Dresser la table'),
	(4,'/images/nutrition/mangeraurestaurant.jpg','Manger au restaurant'),
	(4,'/images/nutrition/mangeraveclesmains.jpg','Manger avec les mains'),
	(4,'/images/nutrition/prepareramanger.jpg','Preparer à manger'),
	(4,'/images/nutrition/sandwichdef.jpg','Faire un sandwich'),
	(4,'/images/nutrition/serviraliments.jpg','Servir les aliments'),
	(5,'/images/relationscom/parleradesadultes.jpg','Parler à des adultes'),
	(5,'/images/relationscom/parlerencommunaute.jpg','Parler en communauté'),
	(5,'/images/relationscom/telephone.jpg','Utiliser le téléphone'),
	(6,'/images/responsabilites/acheter.jpg','Acheter'),
	(7,'/images/soinspersonnels/brossercheveux.jpg','Brosser les cheveux'),
	(7,'/images/soinspersonnels/chaussures.jpg','Mettre ses chaussures'),
	(7,'/images/soinspersonnels/dormir.jpg','Dormir'),
	(7,'/images/soinspersonnels/douche.jpg','Prendre une douche'),
	(7,'/images/soinspersonnels/habitssales.jpg','Habits sales'),
	(7,'/images/soinspersonnels/lavercheveux.jpg','Laver ses cheveux'),
	(7,'/images/soinspersonnels/laverlesmains.jpg','Laver les mains'),
	(7,'/images/soinspersonnels/prendrevetementgarderobe.jpg','Prendre des vêtements dans la garde robe'),
	(7,'/images/soinspersonnels/sessuyer.jpg','S''essuyer'),
	(7,'/images/soinspersonnels/shabillertirette.jpg','S''habiller avec une tirette'),
	(7,'/images/soinspersonnels/shabiller.jpg','S''habiller'),
	(7,'/images/soinspersonnels/sebrosserlesdents.jpg','Se brosser les dents'),
	(7,'/images/soinspersonnels/selever.jpg','Se lever'),
	(7,'/images/soinspersonnels/semoucher.jpg','Se moucher'),
	(7,'/images/soinspersonnels/secherlescheveux.jpg','Se secher les cheveux'),
	(7,'/images/soinspersonnels/toilettes.jpg','Aller aux toilettes'),
	(7,'/images/soinspersonnels/urgences.jpg','Signaler des urgences');

INSERT INTO public."Contact_persons" ("Code", "Name", "Surname", "Phone", "Email", "Relationship") VALUES 
	('a1e1a1e1a1', 'Uals', 'Bob', '0444444444', 'bob.conta@gmail.com', 'père'),
	('a2e2a2e2a2', 'Pouilly', 'Marie', '0333333333', 'marie.pouilly@gmail.com', 'mère');

INSERT INTO public."Users" ("Code", "Name", "Surname", "Birthdate", "Language", "Dominance", "Schooling", "Schooling_type", "Schooling_level", "Contact_one", "Contact_two", "Contact_three") VALUES
	('a2e2a2e2a2', 'Uals', 'Rita', '2010-04-02', 'français', 'droitier', 'ordinaire', NULL, 'primaire 3', 'a1e1a1e1a1', NULL, NULL),
	('a3e3a3e3a3', 'Vannat', 'Arnaud', '2008-05-07', 'français', 'droitier', 'ordinaire', NULL, 'primaire 5', 'a2e2a2e2a2', NULL, NULL);
	
INSERT INTO public."Professionals" ("Code", "Name", "Surname", "Profession", "Phone", "Email") VALUES
	('a1e1a1e1a1', 'Doc', 'Thor', 'Psychologue', '118218', 'doc.thor@gmail.com');
	
INSERT INTO public."Users_professionals" ("User", "Professional", "Mandate", "Mandate_date", "Initial_ask") VALUES
	('a2e2a2e2a2','a1e1a1e1a1', 'Père', '2019-05-05', 'Mieux le connaître'),
	('a3e3a3e3a3','a1e1a1e1a1', 'Tuteur', '2019-03-05', 'Problème à l''école');
	
INSERT INTO public."Needs" ("Child", "Need") VALUES
	('a2e2a2e2a2','Problèmes moteurs'),
	('a2e2a2e2a2','Malvoyant');
	
INSERT INTO public."Sheets"("User", "Date") VALUES 
	('a2e2a2e2a2', '2019-12-8');
	
INSERT INTO public."Sheets_items"("Sheet_id", "Item_id", "Love_it", "Need_help", "Wanna_change", "Favorite") VALUES 
	('1', '2', '1', '0', '0', '0');
	
