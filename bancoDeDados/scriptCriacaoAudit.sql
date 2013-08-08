--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.4
-- Started on 2013-08-07 23:29:34 BRT

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 16826)
-- Name: audit; Type: SCHEMA; Schema: -; Owner: gomebroker
--

CREATE SCHEMA audit;


ALTER SCHEMA audit OWNER TO gomebroker;

SET search_path = audit, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 185 (class 1259 OID 17371)
-- Name: ativo_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE ativo_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    codigo character varying(255),
    datacadastro timestamp without time zone,
    datadesativacao timestamp without time zone,
    descricao character varying(255),
    obs character varying(255),
    empresa_id bigint
);


ALTER TABLE audit.ativo_aud OWNER TO gomebroker;

--
-- TOC entry 183 (class 1259 OID 17361)
-- Name: ativocotacoes_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE ativocotacoes_aud (
    ativo_id integer NOT NULL,
    data timestamp without time zone NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint
);


ALTER TABLE audit.ativocotacoes_aud OWNER TO gomebroker;

--
-- TOC entry 184 (class 1259 OID 17366)
-- Name: ativoofertas_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE ativoofertas_aud (
    ativo_id integer NOT NULL,
    data timestamp without time zone NOT NULL,
    valor double precision NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint
);


ALTER TABLE audit.ativoofertas_aud OWNER TO gomebroker;

--
-- TOC entry 186 (class 1259 OID 17379)
-- Name: conta_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE conta_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    conta character varying(255),
    datacadastro timestamp without time zone,
    datadesativacao timestamp without time zone,
    nome character varying(255),
    obs character varying(255),
    titular_id bigint
);


ALTER TABLE audit.conta_aud OWNER TO gomebroker;

--
-- TOC entry 187 (class 1259 OID 17387)
-- Name: corretora_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE corretora_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    datacadastro timestamp without time zone,
    datadesativacao timestamp without time zone,
    descricao character varying(255),
    moduloconexao character varying(255),
    nome character varying(255),
    obs character varying(255),
    parametrosconexao character varying(255)
);


ALTER TABLE audit.corretora_aud OWNER TO gomebroker;

--
-- TOC entry 197 (class 1259 OID 17467)
-- Name: detalhe_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE detalhe_aud (
    verrev bigint NOT NULL,
    datamodificacao bigint NOT NULL,
    usuario character varying(255)
);


ALTER TABLE audit.detalhe_aud OWNER TO gomebroker;

--
-- TOC entry 213 (class 1259 OID 17796)
-- Name: detalhe_aud_id_seq; Type: SEQUENCE; Schema: audit; Owner: gomebroker
--

CREATE SEQUENCE detalhe_aud_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE audit.detalhe_aud_id_seq OWNER TO gomebroker;

--
-- TOC entry 188 (class 1259 OID 17395)
-- Name: empresa_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE empresa_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    cnpj character varying(255),
    datacadastro timestamp without time zone,
    datadesativacao timestamp without time zone,
    nome character varying(255),
    nomecompleto character varying(255),
    obs character varying(255),
    url character varying(255)
);


ALTER TABLE audit.empresa_aud OWNER TO gomebroker;

--
-- TOC entry 189 (class 1259 OID 17403)
-- Name: incidencia_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE incidencia_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    datacadastro timestamp without time zone,
    datavalidade timestamp without time zone,
    datavigencia timestamp without time zone,
    nome character varying(255),
    obs character varying(255),
    sobre character varying(255),
    tipo character varying(255),
    valor double precision
);


ALTER TABLE audit.incidencia_aud OWNER TO gomebroker;

--
-- TOC entry 190 (class 1259 OID 17411)
-- Name: movimento_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE movimento_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    data timestamp without time zone,
    dataexecucao timestamp without time zone,
    descricao character varying(255),
    idexecucaoordemcorretora character varying(255),
    obs character varying(255),
    quantidade double precision,
    tipo character varying(255),
    valor double precision,
    valorincidencia double precision,
    operador_id bigint,
    ordem_id bigint
);


ALTER TABLE audit.movimento_aud OWNER TO gomebroker;

--
-- TOC entry 191 (class 1259 OID 17419)
-- Name: operador_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE operador_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    cota character varying(255),
    datacadastro timestamp without time zone,
    datadesativacao timestamp without time zone,
    nome character varying(255),
    obs character varying(255),
    conta_id bigint,
    usuario_id bigint
);


ALTER TABLE audit.operador_aud OWNER TO gomebroker;

--
-- TOC entry 193 (class 1259 OID 17435)
-- Name: ordem_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE ordem_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    data timestamp without time zone,
    datacorretora timestamp without time zone,
    datavalidade timestamp without time zone,
    idcorretora character varying(255),
    mercado character varying(255),
    obs character varying(255),
    operacao character varying(255),
    parametros character varying(255),
    quantidade double precision,
    situacao character varying(255),
    tipo character varying(255),
    ultimamensagemcorretora character varying(255),
    valor double precision,
    ativo_id bigint,
    operador_id bigint,
    portfolio_id bigint
);


ALTER TABLE audit.ordem_aud OWNER TO gomebroker;

--
-- TOC entry 192 (class 1259 OID 17427)
-- Name: ordemsolicitacoes_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE ordemsolicitacoes_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    data timestamp without time zone,
    mensagemcorretora character varying(255),
    resposta character varying(255),
    tiposolicitacao character varying(255),
    ordem_id bigint,
    usuario_id bigint
);


ALTER TABLE audit.ordemsolicitacoes_aud OWNER TO gomebroker;

--
-- TOC entry 194 (class 1259 OID 17443)
-- Name: portfolio_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE portfolio_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    datacadastro timestamp without time zone,
    datadesativacao timestamp without time zone,
    nome character varying(255),
    obs character varying(255),
    operador_id bigint
);


ALTER TABLE audit.portfolio_aud OWNER TO gomebroker;

--
-- TOC entry 198 (class 1259 OID 17472)
-- Name: relportifolioativo_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE relportifolioativo_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    datacadastro timestamp without time zone,
    datadesativacao timestamp without time zone,
    descricao character varying(255),
    obs character varying(255),
    participacao character varying(255),
    ativo_id bigint,
    porifolio_id bigint
);


ALTER TABLE audit.relportifolioativo_aud OWNER TO gomebroker;

--
-- TOC entry 199 (class 1259 OID 17480)
-- Name: sec_itemmenu_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE sec_itemmenu_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    datacadastro timestamp without time zone,
    datadesativacao timestamp without time zone,
    descricaokey character varying(255),
    ordem integer,
    separador boolean,
    submenu boolean,
    url character varying(255),
    itemmenu_id_pai bigint
);


ALTER TABLE audit.sec_itemmenu_aud OWNER TO gomebroker;

--
-- TOC entry 200 (class 1259 OID 17488)
-- Name: sec_papel_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE sec_papel_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    datacadastro timestamp without time zone,
    datadesativacao timestamp without time zone,
    descricao character varying(255),
    nome character varying(255),
    obs character varying(255)
);


ALTER TABLE audit.sec_papel_aud OWNER TO gomebroker;

--
-- TOC entry 201 (class 1259 OID 17496)
-- Name: sec_recurso_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE sec_recurso_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    datacadastro timestamp without time zone,
    datadesativacao timestamp without time zone,
    descricao character varying(255),
    nome character varying(255),
    tipo character varying(255),
    valor character varying(255)
);


ALTER TABLE audit.sec_recurso_aud OWNER TO gomebroker;

--
-- TOC entry 202 (class 1259 OID 17504)
-- Name: sec_relpapelitemmenu_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE sec_relpapelitemmenu_aud (
    verrev bigint NOT NULL,
    itemmenu_id bigint NOT NULL,
    papel_id bigint NOT NULL,
    revtype smallint
);


ALTER TABLE audit.sec_relpapelitemmenu_aud OWNER TO gomebroker;

--
-- TOC entry 203 (class 1259 OID 17509)
-- Name: sec_relpapelrecurso_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE sec_relpapelrecurso_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    papel_id bigint,
    recurso_id bigint
);


ALTER TABLE audit.sec_relpapelrecurso_aud OWNER TO gomebroker;

--
-- TOC entry 204 (class 1259 OID 17514)
-- Name: sec_relusuariopapel_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE sec_relusuariopapel_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    datacadastro timestamp without time zone,
    datadesativacao timestamp without time zone,
    padrao boolean,
    papel_id bigint,
    usuario_id bigint
);


ALTER TABLE audit.sec_relusuariopapel_aud OWNER TO gomebroker;

--
-- TOC entry 195 (class 1259 OID 17451)
-- Name: titular_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE titular_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    assinatura character varying(255),
    datacadastro timestamp without time zone,
    datadesativacao timestamp without time zone,
    login character varying(255),
    obs character varying(255),
    parametros character varying(255),
    senha character varying(255),
    corretora_id bigint,
    usuario_id bigint
);


ALTER TABLE audit.titular_aud OWNER TO gomebroker;

--
-- TOC entry 196 (class 1259 OID 17459)
-- Name: usuario_aud; Type: TABLE; Schema: audit; Owner: gomebroker; Tablespace: 
--

CREATE TABLE usuario_aud (
    id bigint NOT NULL,
    verrev bigint NOT NULL,
    revtype smallint,
    datacadastro timestamp without time zone,
    datadesativacao timestamp without time zone,
    email character varying(255),
    nome character varying(255),
    nomecompleto character varying(255),
    obs character varying(255),
    senha character varying(255)
);


ALTER TABLE audit.usuario_aud OWNER TO gomebroker;

--
-- TOC entry 2177 (class 2606 OID 17378)
-- Name: ativo_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY ativo_aud
    ADD CONSTRAINT ativo_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2173 (class 2606 OID 17365)
-- Name: ativocotacoes_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY ativocotacoes_aud
    ADD CONSTRAINT ativocotacoes_aud_pkey PRIMARY KEY (ativo_id, data, verrev);


--
-- TOC entry 2175 (class 2606 OID 17370)
-- Name: ativoofertas_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY ativoofertas_aud
    ADD CONSTRAINT ativoofertas_aud_pkey PRIMARY KEY (ativo_id, data, valor, verrev);


--
-- TOC entry 2179 (class 2606 OID 17386)
-- Name: conta_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY conta_aud
    ADD CONSTRAINT conta_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2181 (class 2606 OID 17394)
-- Name: corretora_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY corretora_aud
    ADD CONSTRAINT corretora_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2201 (class 2606 OID 17471)
-- Name: detalhe_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY detalhe_aud
    ADD CONSTRAINT detalhe_aud_pkey PRIMARY KEY (verrev);


--
-- TOC entry 2183 (class 2606 OID 17402)
-- Name: empresa_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY empresa_aud
    ADD CONSTRAINT empresa_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2185 (class 2606 OID 17410)
-- Name: incidencia_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY incidencia_aud
    ADD CONSTRAINT incidencia_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2187 (class 2606 OID 17418)
-- Name: movimento_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY movimento_aud
    ADD CONSTRAINT movimento_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2189 (class 2606 OID 17426)
-- Name: operador_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY operador_aud
    ADD CONSTRAINT operador_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2193 (class 2606 OID 17442)
-- Name: ordem_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY ordem_aud
    ADD CONSTRAINT ordem_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2191 (class 2606 OID 17434)
-- Name: ordemsolicitacoes_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY ordemsolicitacoes_aud
    ADD CONSTRAINT ordemsolicitacoes_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2195 (class 2606 OID 17450)
-- Name: portfolio_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY portfolio_aud
    ADD CONSTRAINT portfolio_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2203 (class 2606 OID 17479)
-- Name: relportifolioativo_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY relportifolioativo_aud
    ADD CONSTRAINT relportifolioativo_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2205 (class 2606 OID 17487)
-- Name: sec_itemmenu_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY sec_itemmenu_aud
    ADD CONSTRAINT sec_itemmenu_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2207 (class 2606 OID 17495)
-- Name: sec_papel_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY sec_papel_aud
    ADD CONSTRAINT sec_papel_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2209 (class 2606 OID 17503)
-- Name: sec_recurso_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY sec_recurso_aud
    ADD CONSTRAINT sec_recurso_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2211 (class 2606 OID 17508)
-- Name: sec_relpapelitemmenu_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY sec_relpapelitemmenu_aud
    ADD CONSTRAINT sec_relpapelitemmenu_aud_pkey PRIMARY KEY (verrev, itemmenu_id, papel_id);


--
-- TOC entry 2213 (class 2606 OID 17513)
-- Name: sec_relpapelrecurso_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY sec_relpapelrecurso_aud
    ADD CONSTRAINT sec_relpapelrecurso_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2215 (class 2606 OID 17518)
-- Name: sec_relusuariopapel_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY sec_relusuariopapel_aud
    ADD CONSTRAINT sec_relusuariopapel_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2197 (class 2606 OID 17458)
-- Name: titular_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY titular_aud
    ADD CONSTRAINT titular_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2199 (class 2606 OID 17466)
-- Name: usuario_aud_pkey; Type: CONSTRAINT; Schema: audit; Owner: gomebroker; Tablespace: 
--

ALTER TABLE ONLY usuario_aud
    ADD CONSTRAINT usuario_aud_pkey PRIMARY KEY (id, verrev);


--
-- TOC entry 2220 (class 2606 OID 17664)
-- Name: fk21c4bfd4e5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY corretora_aud
    ADD CONSTRAINT fk21c4bfd4e5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2234 (class 2606 OID 17734)
-- Name: fk28fc7c1ee5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY sec_relpapelitemmenu_aud
    ADD CONSTRAINT fk28fc7c1ee5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2230 (class 2606 OID 17714)
-- Name: fk328263e6e5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY relportifolioativo_aud
    ADD CONSTRAINT fk328263e6e5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2225 (class 2606 OID 17689)
-- Name: fk34e9b5e0e5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY ordemsolicitacoes_aud
    ADD CONSTRAINT fk34e9b5e0e5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2236 (class 2606 OID 17744)
-- Name: fk37008e94e5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY sec_relusuariopapel_aud
    ADD CONSTRAINT fk37008e94e5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2218 (class 2606 OID 17654)
-- Name: fk397b0a00e5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY ativo_aud
    ADD CONSTRAINT fk397b0a00e5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2216 (class 2606 OID 17644)
-- Name: fk53e29713e5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY ativocotacoes_aud
    ADD CONSTRAINT fk53e29713e5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2235 (class 2606 OID 17739)
-- Name: fk5562e3ffe5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY sec_relpapelrecurso_aud
    ADD CONSTRAINT fk5562e3ffe5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2219 (class 2606 OID 17659)
-- Name: fk5eee35a0e5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY conta_aud
    ADD CONSTRAINT fk5eee35a0e5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2226 (class 2606 OID 17694)
-- Name: fk5fd9087ae5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY ordem_aud
    ADD CONSTRAINT fk5fd9087ae5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2227 (class 2606 OID 17699)
-- Name: fk6947ef9e5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY portfolio_aud
    ADD CONSTRAINT fk6947ef9e5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2233 (class 2606 OID 17729)
-- Name: fk69e6e4ace5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY sec_recurso_aud
    ADD CONSTRAINT fk69e6e4ace5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2232 (class 2606 OID 17724)
-- Name: fk7b1851e9e5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY sec_papel_aud
    ADD CONSTRAINT fk7b1851e9e5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2223 (class 2606 OID 17679)
-- Name: fk8b1b67cde5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY movimento_aud
    ADD CONSTRAINT fk8b1b67cde5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2229 (class 2606 OID 17709)
-- Name: fk948181dfe5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY usuario_aud
    ADD CONSTRAINT fk948181dfe5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2217 (class 2606 OID 17649)
-- Name: fk954e8e04e5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY ativoofertas_aud
    ADD CONSTRAINT fk954e8e04e5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2231 (class 2606 OID 17719)
-- Name: fka4f89111e5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY sec_itemmenu_aud
    ADD CONSTRAINT fka4f89111e5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2222 (class 2606 OID 17674)
-- Name: fkac35ab0ae5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY incidencia_aud
    ADD CONSTRAINT fkac35ab0ae5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2228 (class 2606 OID 17704)
-- Name: fkb32f9458e5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY titular_aud
    ADD CONSTRAINT fkb32f9458e5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2224 (class 2606 OID 17684)
-- Name: fkb5710485e5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY operador_aud
    ADD CONSTRAINT fkb5710485e5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2221 (class 2606 OID 17669)
-- Name: fkedb5a9bae5ed6604; Type: FK CONSTRAINT; Schema: audit; Owner: gomebroker
--

ALTER TABLE ONLY empresa_aud
    ADD CONSTRAINT fkedb5a9bae5ed6604 FOREIGN KEY (verrev) REFERENCES detalhe_aud(verrev);


--
-- TOC entry 2241 (class 0 OID 0)
-- Dependencies: 6
-- Name: audit; Type: ACL; Schema: -; Owner: gomebroker
--

REVOKE ALL ON SCHEMA audit FROM PUBLIC;
REVOKE ALL ON SCHEMA audit FROM gomebroker;
GRANT ALL ON SCHEMA audit TO gomebroker;
GRANT ALL ON SCHEMA audit TO PUBLIC;


-- Completed on 2013-08-07 23:29:34 BRT

--
-- PostgreSQL database dump complete
--

