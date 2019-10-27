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

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: sectors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sectors (
    sectorid integer NOT NULL,
    sectorname character varying(255) NOT NULL,
    childsectors integer[]
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

COPY public.sectors (sectorid, sectorname, childsectors) FROM stdin;
113	Water	{}
112	Road	{}
114	Rail	{}
111	Air	{}
21	Transport and Logistics	{111,112,113,114}
141	Translation services	{}
22	Tourism	{}
122	Telecommunications	{}
121	Software, Hardware	{}
576	Programming, Consultancy	{}
581	Data processing, Web portals, E-marketing	{}
28	Information Technology and Telecommunications	{121,122,576,581}
35	Engineering	{}
25	Business services	{}
2	Service	{21,22,25,28,35,141}
33	Environment	{}
29	Energy technology	{}
37	Creative industries	{}
3	Other	{29,33,37}
47	Wooden houses	{}
51	Wooden building materials	{}
337	Other (Wood)	{}
8	Wood	{47,51,337}
45	Textile	{}
44	Clothing	{}
7	Textile and Clothing	{44,45}
145	Labelling and packaging printing	{}
150	Book/Periodicals printing	{}
148	Advertising	{}
5	Printing	{145,148,150}
560	Plastic profiles	{}
53	Plastics welding and processing	{}
57	Moulding	{}
55	Blowing	{}
559	Plastic processing technology	{53,55,57}
556	Plastic goods	{}
54	Packaging	{}
9	Plastic and Rubber	{54,556,559,560}
66	MIG, TIG, Aluminum welding	{}
69	Gas, Plasma, Laser cutting	{}
62	Forgings, Fasteners	{}
75	CNC-machining	{}
542	Metal works	{62,66,69,75}
267	Metal products	{}
263	Houses and buildings	{}
67	Construction of metal structures	{}
11	Metalworking	{67,263,267,542}
227	Repair and maintenance service	{}
508	Other	{}
93	Metal structures	{}
230	Ship repair and conversion	{}
269	Boat/Yacht building	{}
271	Aluminium and steel workboats	{}
97	Maritime	{230,269,271}
224	Manufacture of machinery	{}
91	Machinery equipment/tools	{}
94	Machinery components	{}
12	Machinery	{91,93,94,97,224,227,508}
99	Project furniture	{}
341	Outdoor	{}
394	Other (Furniture)	{}
392	Office	{}
101	Living room	{}
98	Kitchen	{}
390	Children's room	{}
385	Bedroom	{}
389	Bathroom/sauna	{}
13	Furniture	{98,99,101,341,385,389,390,392,394}
378	Sweets & snack food	{}
437	Other	{}
39	Milk & dairy products	{}
40	Meat & meat products	{}
42	Fish & fish products	{}
43	Beverages	{}
342	Bakery & confectionery products	{}
6	Food and Beverage	{39,40,42,43,342,378,437}
18	Electronics and Optics	{}
19	Construction materials	{}
1	Manufacturing	{5,6,7,8,9,11,12,13,18,19}
-1	Main sectors	{1,2,3}
\.


--
-- Data for Name: user_sectors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_sectors (userid, sectorid) FROM stdin;
78887DE633A0575894DDE90220CFA24A	7
78887DE633A0575894DDE90220CFA24A	8
17BC71378DAD73C1272F278AD56DA088	342
17BC71378DAD73C1272F278AD56DA088	378
17BC71378DAD73C1272F278AD56DA088	66
17BC71378DAD73C1272F278AD56DA088	69
17BC71378DAD73C1272F278AD56DA088	75
901A56CD444DAFB4347C86012A6199D6	145
901A56CD444DAFB4347C86012A6199D6	148
901A56CD444DAFB4347C86012A6199D6	150
901A56CD444DAFB4347C86012A6199D6	39
901A56CD444DAFB4347C86012A6199D6	40
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (userid, fullname, hasagreedtoterms) FROM stdin;
78887DE633A0575894DDE90220CFA24A	John Smith	t
17BC71378DAD73C1272F278AD56DA088	Jane Smith	t
901A56CD444DAFB4347C86012A6199D6	Mike Stevens	t
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

