--
-- PostgreSQL database dump
--

-- Dumped from database version 11.5
-- Dumped by pg_dump version 11.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: helmes_hw_markg; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE helmes_hw_markg WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Estonian_Estonia.1257' LC_CTYPE = 'Estonian_Estonia.1257';


ALTER DATABASE helmes_hw_markg OWNER TO postgres;

\connect helmes_hw_markg

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: sectors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sectors (
    sectorid integer NOT NULL,
    sectorname character varying(255) NOT NULL
);


ALTER TABLE public.sectors OWNER TO postgres;

--
-- Name: user_sectors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_sectors (
    userid character varying(64) NOT NULL,
    sectorid integer NOT NULL
);


ALTER TABLE public.user_sectors OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    userid character varying(64) NOT NULL,
    fullname character varying(255) NOT NULL,
    hasagreedtoterms boolean NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Data for Name: sectors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sectors (sectorid, sectorname) FROM stdin;
1	Manufacturing
19	    Construction materials
18	    Electronics and Optics
6	    Food and Beverage
342	        Bakery & confectionery products
43	        Beverages
42	        Fish & fish products 
40	        Meat & meat products
39	        Milk & dairy products 
437	        Other
378	        Sweets & snack food
13	    Furniture
389	        Bathroom/sauna 
385	        Bedroom
390	        Children’s room 
98	        Kitchen 
101	        Living room 
392	        Office
394	        Other (Furniture)
341	        Outdoor 
99	        Project furniture
12	    Machinery
94	        Machinery components
91	        Machinery equipment/tools
224	        Manufacture of machinery 
97	        Maritime
271	            Aluminium and steel workboats 
269	            Boat/Yacht building
230	            Ship repair and conversion
93	        Metal structures
508	        Other
227	        Repair and maintenance service
11	    Metalworking
67	        Construction of metal structures
263	        Houses and buildings
267	        Metal products
542	        Metal works
75	            CNC-machining
62	            Forgings, Fasteners 
69	            Gas, Plasma, Laser cutting
66	            MIG, TIG, Aluminum welding
9	    Plastic and Rubber
54	        Packaging
556	        Plastic goods
559	        Plastic processing technology
55	            Blowing
57	            Moulding
53	            Plastics welding and processing
560	        Plastic profiles
5	    Printing 
148	        Advertising
150	        Book/Periodicals printing
145	        Labelling and packaging printing
7	    Textile and Clothing
44	        Clothing
45	        Textile
8	    Wood
337	        Other (Wood)
51	        Wooden building materials
47	        Wooden houses
3	Other
37	    Creative industries
29	    Energy technology
33	    Environment
2	Service
25	    Business services
35	    Engineering
28	    Information Technology and Telecommunications
581	        Data processing, Web portals, E-marketing
576	        Programming, Consultancy
121	        Software, Hardware
122	        Telecommunications
22	    Tourism
141	    Translation services
21	    Transport and Logistics
111	        Air
114	        Rail
112	        Road
113	        Water
\.


--
-- Data for Name: user_sectors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_sectors (userid, sectorid) FROM stdin;
1B64372E0B4BC72EC0B79EFB69F11B61	1
1B64372E0B4BC72EC0B79EFB69F11B61	19
1B64372E0B4BC72EC0B79EFB69F11B61	6
4240FACCF86932D99A62CED705B55820	93
4240FACCF86932D99A62CED705B55820	11
4240FACCF86932D99A62CED705B55820	263
74FFD1753AE934C0125D3310F0BB12B8	37
74FFD1753AE934C0125D3310F0BB12B8	29
74FFD1753AE934C0125D3310F0BB12B8	33
74FFD1753AE934C0125D3310F0BB12B8	111
74FFD1753AE934C0125D3310F0BB12B8	114
74FFD1753AE934C0125D3310F0BB12B8	112
74FFD1753AE934C0125D3310F0BB12B8	113
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (userid, fullname, hasagreedtoterms) FROM stdin;
1B64372E0B4BC72EC0B79EFB69F11B61	John Smith	t
4240FACCF86932D99A62CED705B55820	Holly Smith	t
74FFD1753AE934C0125D3310F0BB12B8	Jack Bond	t
\.


--
-- Name: sectors pk_sector; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sectors
    ADD CONSTRAINT pk_sector PRIMARY KEY (sectorid);


--
-- Name: users pk_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT pk_user PRIMARY KEY (userid);


--
-- Name: user_sectors pk_user_sectors; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_sectors
    ADD CONSTRAINT pk_user_sectors PRIMARY KEY (userid, sectorid);


--
-- Name: user_sectors fk_user_sectors_sector_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_sectors
    ADD CONSTRAINT fk_user_sectors_sector_id FOREIGN KEY (sectorid) REFERENCES public.sectors(sectorid) ON UPDATE CASCADE;


--
-- Name: user_sectors fk_user_sectors_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_sectors
    ADD CONSTRAINT fk_user_sectors_user_id FOREIGN KEY (userid) REFERENCES public.users(userid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

