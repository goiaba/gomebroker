
CREATE SEQUENCE core.itemmenu_id_seq;

CREATE TABLE core.itemMenu (
                id INTEGER NOT NULL DEFAULT nextval('core.itemmenu_id_seq'),
                itemMenu_id_pai INTEGER NOT NULL,
                descricaoKey VARCHAR NOT NULL,
                url VARCHAR NOT NULL,
                separador BOOLEAN NOT NULL,
                submenu BOOLEAN NOT NULL,
                ordem INTEGER NOT NULL,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                dataDesativacao TIMESTAMP DEFAULT NULL::timestamp without time zone NOT NULL,
                CONSTRAINT itemmenu_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN core.itemMenu.id IS 'Código do item de menu';
COMMENT ON COLUMN core.itemMenu.itemMenu_id_pai IS 'Código do item de menu pai';
COMMENT ON COLUMN core.itemMenu.descricaoKey IS 'Chave de internacionalização da descrição do item de menu (será usada no código java para retonar a descrição na linguagem escolhida pelo usuário).';
COMMENT ON COLUMN core.itemMenu.url IS 'URL para a qual o item de menu apontará.';
COMMENT ON COLUMN core.itemMenu.separador IS 'Indicador de separador (ao invés de item de menu)';
COMMENT ON COLUMN core.itemMenu.submenu IS 'Indicador de submenu (ao invés de item de menu).';
COMMENT ON COLUMN core.itemMenu.ordem IS 'Ordenador dos itens pai de menu. Ou seja, será utilizado para ordenar, horizontalmente, os submenus pais que serão exibidos para o usuário, de acordo com o perfil em uso.';
COMMENT ON COLUMN core.itemMenu.dataCadastro IS 'Data de cadastro do item de menu';
COMMENT ON COLUMN core.itemMenu.dataDesativacao IS 'Data de desativação do item de menu';


ALTER SEQUENCE core.itemmenu_id_seq OWNED BY core.itemMenu.id;

CREATE SEQUENCE core.recurso_id_seq;

CREATE TABLE core.Recurso (
                id INTEGER NOT NULL DEFAULT nextval('core.recurso_id_seq'),
                nome VARCHAR(255) NOT NULL,
                descricao VARCHAR(255) DEFAULT ''::character varying NOT NULL,
                valor VARCHAR(1000) NOT NULL,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                dataDesativacao TIMESTAMP DEFAULT NULL::timestamp without time zone NOT NULL,
                CONSTRAINT recurso_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN core.Recurso.nome IS 'Nome do Recurso';
COMMENT ON COLUMN core.Recurso.descricao IS 'Descrição do Recurso';
COMMENT ON COLUMN core.Recurso.valor IS 'Valor do recurso (url)';
COMMENT ON COLUMN core.Recurso.dataCadastro IS 'Data de cadastro do Recurso';
COMMENT ON COLUMN core.Recurso.dataDesativacao IS 'Data de desativação do Recurso';


ALTER SEQUENCE core.recurso_id_seq OWNED BY core.Recurso.id;

CREATE UNIQUE INDEX recurso_idx
 ON core.Recurso
 ( nome );

CREATE TABLE core.papel (
                id INTEGER NOT NULL,
                nome VARCHAR(255) NOT NULL,
                descricao VARCHAR(255) DEFAULT ''::character varying NOT NULL,
                obs TEXT DEFAULT ''::text NOT NULL,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                dataDesativacao TIMESTAMP DEFAULT NULL::timestamp without time zone NOT NULL,
                CONSTRAINT papel_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN core.papel.id IS 'Código do papel';
COMMENT ON COLUMN core.papel.nome IS 'Nome do Papel';
COMMENT ON COLUMN core.papel.descricao IS 'Descrição do Papel';
COMMENT ON COLUMN core.papel.obs IS 'Observação para o Papel';
COMMENT ON COLUMN core.papel.dataCadastro IS 'Data de cadastro do Papel';
COMMENT ON COLUMN core.papel.dataDesativacao IS 'Data de desativação do Papel';


CREATE UNIQUE INDEX papel_idx
 ON core.papel
 ( nome );

CREATE SEQUENCE core.relpapelrecurso_id_seq;

CREATE TABLE core.relPapelRecurso (
                id INTEGER NOT NULL DEFAULT nextval('core.relpapelrecurso_id_seq'),
                papel_id INTEGER NOT NULL,
                recurso_id INTEGER NOT NULL,
                CONSTRAINT relpapelrecurso_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN core.relPapelRecurso.id IS 'Código da relação entre papel e seus recursos';
COMMENT ON COLUMN core.relPapelRecurso.papel_id IS 'Código do papel';
COMMENT ON COLUMN core.relPapelRecurso.recurso_id IS 'Código do recurso';


ALTER SEQUENCE core.relpapelrecurso_id_seq OWNED BY core.relPapelRecurso.id;

CREATE UNIQUE INDEX relpapelrecurso_idx
 ON core.relPapelRecurso
 ( papel_id, recurso_id );

CREATE SEQUENCE core.usuario_id_seq;

CREATE TABLE core.usuario (
                id INTEGER NOT NULL DEFAULT nextval('core.usuario_id_seq'),
                nome VARCHAR(50) NOT NULL,
                senha VARCHAR(255) DEFAULT ''::character varying,
                nomeCompleto VARCHAR(255) DEFAULT ''::character varying,
                email VARCHAR(255) DEFAULT ''::character varying,
                obs TEXT DEFAULT ''::text,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                dataDesativacao TIMESTAMP DEFAULT NULL::timestamp without time zone,
                CONSTRAINT usuario_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.usuario IS 'Usuário do sistema';
COMMENT ON COLUMN core.usuario.id IS 'Código do Usuário';
COMMENT ON COLUMN core.usuario.nome IS 'Nome do Usuário';
COMMENT ON COLUMN core.usuario.senha IS 'Senha do Usuário';
COMMENT ON COLUMN core.usuario.nomeCompleto IS 'Nome Completo do Usuário';
COMMENT ON COLUMN core.usuario.email IS 'Endereço de e-mail do Usuário';
COMMENT ON COLUMN core.usuario.obs IS 'Observação para o Usuário';
COMMENT ON COLUMN core.usuario.dataCadastro IS 'Data de cadastro do Usuário';
COMMENT ON COLUMN core.usuario.dataDesativacao IS 'Data de desativação do Usuário';


ALTER SEQUENCE core.usuario_id_seq OWNED BY core.usuario.id;

CREATE SEQUENCE core.perfil_id_seq;

CREATE TABLE core.perfil (
                id INTEGER NOT NULL DEFAULT nextval('core.perfil_id_seq'),
                nome VARCHAR(255) NOT NULL,
                descricao VARCHAR(255) DEFAULT ''::character varying,
                obs TEXT DEFAULT ''::text,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                dataDesativacao TIMESTAMP DEFAULT NULL::timestamp without time zone,
                CONSTRAINT perfil_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.perfil IS 'Perfis para Usuários';
COMMENT ON COLUMN core.perfil.id IS 'Código do Perfil';
COMMENT ON COLUMN core.perfil.nome IS 'Nome do Perfil';
COMMENT ON COLUMN core.perfil.descricao IS 'Descrição do Perfil';
COMMENT ON COLUMN core.perfil.obs IS 'Observação para o Perfil';
COMMENT ON COLUMN core.perfil.dataCadastro IS 'Data de cadastro do Perfil';
COMMENT ON COLUMN core.perfil.dataDesativacao IS 'Data de desativação do Perfil';


ALTER SEQUENCE core.perfil_id_seq OWNED BY core.perfil.id;

CREATE UNIQUE INDEX perfil_idx
 ON core.perfil
 ( nome );

CREATE TABLE core.relPerfilPapel (
                id INTEGER NOT NULL,
                papel_id INTEGER NOT NULL,
                perfil_id INTEGER NOT NULL,
                CONSTRAINT relperfilpapel_pk PRIMARY KEY (id)
);
COMMENT ON COLUMN core.relPerfilPapel.papel_id IS 'Código do papel';
COMMENT ON COLUMN core.relPerfilPapel.perfil_id IS 'Código do Perfil';


CREATE UNIQUE INDEX relperfilpapel_idx
 ON core.relPerfilPapel
 ( papel_id, perfil_id );

CREATE SEQUENCE core.relusuarioperfil_id_seq;

CREATE TABLE core.relUsuarioPerfil (
                id INTEGER NOT NULL DEFAULT nextval('core.relusuarioperfil_id_seq'),
                usuario_id INTEGER NOT NULL,
                perfil_id INTEGER NOT NULL,
                dataVigencia TIMESTAMP DEFAULT now() NOT NULL,
                dataValidade TIMESTAMP DEFAULT '9999-01-01 00:00:00'::timestamp without time zone NOT NULL,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                padrao BOOLEAN DEFAULT false NOT NULL,
                CONSTRAINT relusuarioperfil_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.relUsuarioPerfil IS 'Perfil do Usuário para utilização do sismtema.';
COMMENT ON COLUMN core.relUsuarioPerfil.usuario_id IS 'Código do Usuário';
COMMENT ON COLUMN core.relUsuarioPerfil.perfil_id IS 'Código do Perfil';
COMMENT ON COLUMN core.relUsuarioPerfil.dataVigencia IS 'Data do início da vigência do Perfil';
COMMENT ON COLUMN core.relUsuarioPerfil.dataValidade IS 'Data do fim da vigência do Perfil';
COMMENT ON COLUMN core.relUsuarioPerfil.dataCadastro IS 'Data de Cadastro do Perfil para o Usuário';
COMMENT ON COLUMN core.relUsuarioPerfil.padrao IS 'Marcador de perfil padrão do usuário';


ALTER SEQUENCE core.relusuarioperfil_id_seq OWNED BY core.relUsuarioPerfil.id;

CREATE UNIQUE INDEX relusuarioperfil_idx
 ON core.relUsuarioPerfil
 ( usuario_id, perfil_id );

CREATE SEQUENCE core.incidencia_id_seq;

CREATE TABLE core.incidencia (
                id INTEGER NOT NULL DEFAULT nextval('core.incidencia_id_seq'),
                nome VARCHAR(255) NOT NULL,
                dataVigencia TIMESTAMP DEFAULT now() NOT NULL,
                dataValidade TIMESTAMP DEFAULT '9999-01-01 00:00:00'::timestamp without time zone NOT NULL,
                sobre VARCHAR(255) NOT NULL,
                tipo VARCHAR(255) NOT NULL,
                valor DOUBLE PRECISION DEFAULT 0 NOT NULL,
                obs TEXT DEFAULT ''::text,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                CONSTRAINT incidencia_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.incidencia IS 'Incidentes sobre as operações (Impostos, Emolumentos, etc)';
COMMENT ON COLUMN core.incidencia.id IS 'Código da Incidencia';
COMMENT ON COLUMN core.incidencia.nome IS 'Nome da Incidência';
COMMENT ON COLUMN core.incidencia.dataVigencia IS 'Data do início da vigência da Incidência';
COMMENT ON COLUMN core.incidencia.dataValidade IS 'Data do fim da vigência da Incidência';
COMMENT ON COLUMN core.incidencia.sobre IS 'Sobre o quê a Incidência é aplicada (execução de ordem, etc)';
COMMENT ON COLUMN core.incidencia.tipo IS 'Tipo de Incidência (percentual ou absoluta)';
COMMENT ON COLUMN core.incidencia.valor IS 'Valor da Incidência';
COMMENT ON COLUMN core.incidencia.obs IS 'Observação para a Incidência';
COMMENT ON COLUMN core.incidencia.dataCadastro IS 'Data de Cadastro da Incidência';


ALTER SEQUENCE core.incidencia_id_seq OWNED BY core.incidencia.id;

CREATE SEQUENCE core.empresa_id_seq;

CREATE TABLE core.empresa (
                id INTEGER NOT NULL DEFAULT nextval('core.empresa_id_seq'),
                nome VARCHAR(255) NOT NULL,
                nomeCompleto VARCHAR(255) DEFAULT ''::character varying,
                cnpj VARCHAR(255) DEFAULT ''::character varying,
                url VARCHAR(255) DEFAULT ''::character varying,
                obs TEXT DEFAULT ''::text,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                dataDesativacao TIMESTAMP DEFAULT NULL::timestamp without time zone,
                CONSTRAINT empresa_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.empresa IS 'Empresas dos Ativos';
COMMENT ON COLUMN core.empresa.id IS 'Código da Empresa';
COMMENT ON COLUMN core.empresa.nome IS 'Nome da Empresa';
COMMENT ON COLUMN core.empresa.nomeCompleto IS 'Nome Completo da Empresa';
COMMENT ON COLUMN core.empresa.cnpj IS 'CNPJ da Empresa';
COMMENT ON COLUMN core.empresa.url IS 'URL para página da Empresa';
COMMENT ON COLUMN core.empresa.obs IS 'Observações da Empresa';
COMMENT ON COLUMN core.empresa.dataCadastro IS 'Data de cadastro da Empresa';
COMMENT ON COLUMN core.empresa.dataDesativacao IS 'Data de desativação da Empresa';


ALTER SEQUENCE core.empresa_id_seq OWNED BY core.empresa.id;

CREATE UNIQUE INDEX empresa_indice_id
 ON core.empresa USING BTREE
 ( nome );

CREATE SEQUENCE core.corretora_id_seq;

CREATE TABLE core.corretora (
                id INTEGER NOT NULL DEFAULT nextval('core.corretora_id_seq'),
                nome VARCHAR(255) NOT NULL,
                descricao VARCHAR(255) DEFAULT ''::character varying,
                obs TEXT DEFAULT ''::text,
                moduloConexao VARCHAR(255) DEFAULT ''::character varying,
                parametrosConexao TEXT DEFAULT ''::text,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                dataDesativacao TIMESTAMP DEFAULT NULL::timestamp without time zone,
                CONSTRAINT corretora_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.corretora IS 'Corretoras';
COMMENT ON COLUMN core.corretora.id IS 'Código da Corretora';
COMMENT ON COLUMN core.corretora.nome IS 'Nome da Corretora';
COMMENT ON COLUMN core.corretora.descricao IS 'Descrição da Corretora';
COMMENT ON COLUMN core.corretora.obs IS 'Observações para Corretora';
COMMENT ON COLUMN core.corretora.moduloConexao IS 'Módulo de conexão com a Corretora';
COMMENT ON COLUMN core.corretora.parametrosConexao IS 'Parâmetros para o Módulo de conexão com a Corretora';
COMMENT ON COLUMN core.corretora.dataCadastro IS 'Data de cadastro da Corretora';
COMMENT ON COLUMN core.corretora.dataDesativacao IS 'Data de desativação da Corretora';


ALTER SEQUENCE core.corretora_id_seq OWNED BY core.corretora.id;

CREATE UNIQUE INDEX corretora_indice_id
 ON core.corretora USING BTREE
 ( nome );

CREATE SEQUENCE core.titular_id_seq;

CREATE TABLE core.titular (
                id INTEGER NOT NULL DEFAULT nextval('core.titular_id_seq'),
                usuario_id INTEGER NOT NULL,
                corretora_id INTEGER NOT NULL,
                login VARCHAR(255) DEFAULT ''::character varying,
                senha VARCHAR(255) DEFAULT ''::character varying,
                assinatura VARCHAR(255) DEFAULT ''::character varying,
                parametros VARCHAR(255) DEFAULT ''::character varying,
                obs TEXT DEFAULT ''::text,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                dataDesativacao TIMESTAMP DEFAULT NULL::timestamp without time zone,
                CONSTRAINT titular_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.titular IS 'Usuário Titular numa Corretora';
COMMENT ON COLUMN core.titular.id IS 'Código do Titular';
COMMENT ON COLUMN core.titular.usuario_id IS 'Código do Usuário';
COMMENT ON COLUMN core.titular.corretora_id IS 'Código da Corretora';
COMMENT ON COLUMN core.titular.login IS 'Login do Titular';
COMMENT ON COLUMN core.titular.senha IS 'Senha do Titular';
COMMENT ON COLUMN core.titular.assinatura IS 'Assinatura Eletrônica do Titular';
COMMENT ON COLUMN core.titular.parametros IS 'Parâmetros do Titular';
COMMENT ON COLUMN core.titular.obs IS 'Observação para o Titular';
COMMENT ON COLUMN core.titular.dataCadastro IS 'Data de cadastro do Titular';
COMMENT ON COLUMN core.titular.dataDesativacao IS 'Data de desativação do Titular';


ALTER SEQUENCE core.titular_id_seq OWNED BY core.titular.id;

CREATE UNIQUE INDEX titular_indice_id
 ON core.titular USING BTREE
 ( usuario_id, corretora_id );

CREATE SEQUENCE core.conta_id_seq;

CREATE TABLE core.conta (
                id INTEGER NOT NULL DEFAULT nextval('core.conta_id_seq'),
                titular_id INTEGER NOT NULL,
                nome VARCHAR(255) NOT NULL,
                conta VARCHAR(255) NOT NULL,
                obs TEXT DEFAULT ''::text,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                dataDesativacao TIMESTAMP DEFAULT NULL::timestamp without time zone,
                CONSTRAINT conta_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.conta IS 'Conta do Titular na Corretora';
COMMENT ON COLUMN core.conta.id IS 'Código da Conta';
COMMENT ON COLUMN core.conta.titular_id IS 'Código do Titular';
COMMENT ON COLUMN core.conta.nome IS 'Nome da Conta';
COMMENT ON COLUMN core.conta.conta IS 'Número/Código da Conta';
COMMENT ON COLUMN core.conta.obs IS 'Observação para Conta';
COMMENT ON COLUMN core.conta.dataCadastro IS 'Data de Cadastro da Conta';
COMMENT ON COLUMN core.conta.dataDesativacao IS 'Data de desativação da Conta';


ALTER SEQUENCE core.conta_id_seq OWNED BY core.conta.id;

CREATE UNIQUE INDEX conta_indice_id
 ON core.conta USING BTREE
 ( titular_id, nome );

CREATE SEQUENCE core.operador_id_seq;

CREATE TABLE core.operador (
                id INTEGER NOT NULL DEFAULT nextval('core.operador_id_seq'),
                usuario_id INTEGER NOT NULL,
                conta_id INTEGER NOT NULL,
                nome VARCHAR(255) NOT NULL,
                obs TEXT DEFAULT ''::text,
                cota VARCHAR(255) DEFAULT ''::character varying,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                dataDesativacao TIMESTAMP DEFAULT NULL::timestamp without time zone,
                CONSTRAINT operador_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.operador IS 'Usuário designado como Operador de uma Conta de um Titular';
COMMENT ON COLUMN core.operador.id IS 'Código do Operador';
COMMENT ON COLUMN core.operador.usuario_id IS 'Código do Usuário';
COMMENT ON COLUMN core.operador.conta_id IS 'Código da Conta do Titular na Corretora';
COMMENT ON COLUMN core.operador.nome IS 'Nome do Operador (Nome para identificar facilmente o Usuário na Conta do Titular)';
COMMENT ON COLUMN core.operador.obs IS 'Observações para o Operador';
COMMENT ON COLUMN core.operador.cota IS 'Dados da Cota do Operador na Conta do Titular';
COMMENT ON COLUMN core.operador.dataCadastro IS 'Data de cadastro do Operador';
COMMENT ON COLUMN core.operador.dataDesativacao IS 'Data de desativação do Operador';


ALTER SEQUENCE core.operador_id_seq OWNED BY core.operador.id;

CREATE UNIQUE INDEX operador_indice_id
 ON core.operador USING BTREE
 ( usuario_id, conta_id );

CREATE SEQUENCE core.portfolio_id_seq;

CREATE TABLE core.portfolio (
                id INTEGER NOT NULL DEFAULT nextval('core.portfolio_id_seq'),
                operador_id INTEGER NOT NULL,
                nome VARCHAR(255) NOT NULL,
                obs TEXT DEFAULT ''::text,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                dataDesativacao TIMESTAMP DEFAULT NULL::timestamp without time zone,
                CONSTRAINT portfolio_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.portfolio IS 'Portfólios de Ativos para acompanhamento pelos Operadores (pode ser utilizado como Carteira)';
COMMENT ON COLUMN core.portfolio.id IS 'Codigo do Portfólio';
COMMENT ON COLUMN core.portfolio.operador_id IS 'Codigo do Operador';
COMMENT ON COLUMN core.portfolio.nome IS 'Nome do Portfólio';
COMMENT ON COLUMN core.portfolio.obs IS 'Observações para Portfólio';
COMMENT ON COLUMN core.portfolio.dataCadastro IS 'Data de cadastro do Portfólio';
COMMENT ON COLUMN core.portfolio.dataDesativacao IS 'Data de desativação do Portfólio';


ALTER SEQUENCE core.portfolio_id_seq OWNED BY core.portfolio.id;

CREATE UNIQUE INDEX portfolio_indice_id
 ON core.portfolio USING BTREE
 ( operador_id, nome );

CREATE SEQUENCE core.ativo_id_seq;

CREATE TABLE core.ativo (
                id INTEGER NOT NULL DEFAULT nextval('core.ativo_id_seq'),
                empresa_id INTEGER NOT NULL,
                codigo VARCHAR(15) NOT NULL,
                descricao VARCHAR(255) DEFAULT ''::character varying,
                obs TEXT DEFAULT ''::text,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                dataDesativacao TIMESTAMP DEFAULT NULL::timestamp without time zone,
                CONSTRAINT ativo_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.ativo IS 'Ativos a serem movimentados';
COMMENT ON COLUMN core.ativo.id IS 'Código do Ativo';
COMMENT ON COLUMN core.ativo.empresa_id IS 'Código da Empresa do Ativo';
COMMENT ON COLUMN core.ativo.codigo IS 'Código do ativo, utilizado para envio da Ordem.';
COMMENT ON COLUMN core.ativo.descricao IS 'Descrição do Ativo';
COMMENT ON COLUMN core.ativo.obs IS 'Observação para o Ativo';
COMMENT ON COLUMN core.ativo.dataCadastro IS 'Data de Cadastro do Ativo';
COMMENT ON COLUMN core.ativo.dataDesativacao IS 'Data de Desativação do Ativo';


ALTER SEQUENCE core.ativo_id_seq OWNED BY core.ativo.id;

CREATE SEQUENCE core.relportifolioativo_id_seq;

CREATE TABLE core.relPortifolioAtivo (
                id INTEGER NOT NULL DEFAULT nextval('core.relportifolioativo_id_seq'),
                porifolio_id INTEGER NOT NULL,
                ativo_id INTEGER NOT NULL,
                descricao VARCHAR(255) DEFAULT ''::character varying,
                participacao VARCHAR(255) DEFAULT ''::character varying,
                obs TEXT DEFAULT ''::text,
                dataCadastro TIMESTAMP DEFAULT now() NOT NULL,
                dataDesativacao TIMESTAMP DEFAULT NULL::timestamp without time zone,
                CONSTRAINT relportifolioativo_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.relPortifolioAtivo IS 'Ativos dos Portfólios.';
COMMENT ON COLUMN core.relPortifolioAtivo.porifolio_id IS 'Codigo do Portfolio';
COMMENT ON COLUMN core.relPortifolioAtivo.ativo_id IS 'Código do Ativo';
COMMENT ON COLUMN core.relPortifolioAtivo.descricao IS 'Descrição do Ativo para o Portfólio';
COMMENT ON COLUMN core.relPortifolioAtivo.participacao IS 'Dados da Participação do Ativo no Portfólio';
COMMENT ON COLUMN core.relPortifolioAtivo.obs IS 'Observação do Ativo para o Portfólio';
COMMENT ON COLUMN core.relPortifolioAtivo.dataCadastro IS 'Data de Cadastro do Ativo no Portfolio';
COMMENT ON COLUMN core.relPortifolioAtivo.dataDesativacao IS 'Data de desativação do Ativo no Portfolio';


ALTER SEQUENCE core.relportifolioativo_id_seq OWNED BY core.relPortifolioAtivo.id;

CREATE UNIQUE INDEX relportifolioativo_idx
 ON core.relPortifolioAtivo
 ( porifolio_id, ativo_id );

CREATE SEQUENCE core.ordem_id_seq;

CREATE TABLE core.ordem (
                id INTEGER NOT NULL DEFAULT nextval('core.ordem_id_seq'),
                operador_id INTEGER NOT NULL,
                data TIMESTAMP DEFAULT now() NOT NULL,
                ativo_id INTEGER NOT NULL,
                portfolio_id INTEGER NOT NULL,
                tipo VARCHAR(2) NOT NULL,
                mercado VARCHAR(2) NOT NULL,
                operacao VARCHAR(2) NOT NULL,
                quantidade DOUBLE PRECISION DEFAULT 0 NOT NULL,
                valor DOUBLE PRECISION DEFAULT 0 NOT NULL,
                dataValidade TIMESTAMP NOT NULL,
                parametros TEXT DEFAULT ''::text,
                dataCorretora TIMESTAMP DEFAULT NULL::timestamp without time zone,
                idCorretora VARCHAR(255) DEFAULT ''::character varying,
                obs TEXT DEFAULT ''::text,
                situacao VARCHAR(3) DEFAULT 'INS'::character varying NOT NULL,
                ultimamensagemCorretora VARCHAR(255) DEFAULT ''::character varying,
                CONSTRAINT ordem_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.ordem IS 'Ordens enviadas para Corretoras pelos Operadores';
COMMENT ON COLUMN core.ordem.id IS 'Codigo da Ordem';
COMMENT ON COLUMN core.ordem.operador_id IS 'Código do Operador';
COMMENT ON COLUMN core.ordem.data IS 'Data de inserção da Ordem';
COMMENT ON COLUMN core.ordem.ativo_id IS 'Código do Ativo';
COMMENT ON COLUMN core.ordem.portfolio_id IS 'Código do Portfólio (Opcional)';
COMMENT ON COLUMN core.ordem.tipo IS 'Tipo da Ordem (C = Compra, V = Venda)';
COMMENT ON COLUMN core.ordem.mercado IS 'Tipo de Mercado (V = à Vista, O = Opções)';
COMMENT ON COLUMN core.ordem.operacao IS 'Tipo de Operação (F = Final, D = DayTrade)';
COMMENT ON COLUMN core.ordem.quantidade IS 'Quantidade enviada na Ordem';
COMMENT ON COLUMN core.ordem.valor IS 'Valor unitário enviado na Ordem';
COMMENT ON COLUMN core.ordem.dataValidade IS 'Data de validade da Ordem';
COMMENT ON COLUMN core.ordem.parametros IS 'Parâmetros da Ordem (Stop, Start, etc)';
COMMENT ON COLUMN core.ordem.dataCorretora IS 'Data da Ordem na Corretora';
COMMENT ON COLUMN core.ordem.idCorretora IS 'Codigo da Ordem na Corretora';
COMMENT ON COLUMN core.ordem.obs IS 'Observação para a Ordem';
COMMENT ON COLUMN core.ordem.situacao IS 'Situação da Ordem (INS=Inserida no banco de dados / SOL=Solictada á Corretora / PEN=Pendente na Corretora / EXE=Executada / EXP=Executada Parcialmente / CAN=Cancelada / CAP=Cancelada Parcialmente / EXC = Excluída da Corretora / ERR=Erro)';
COMMENT ON COLUMN core.ordem.ultimamensagemCorretora IS 'Última mensagem retornada pela Corretora sobre a Ordem';


ALTER SEQUENCE core.ordem_id_seq OWNED BY core.ordem.id;

CREATE SEQUENCE core.ordemsolicitacoes_id_seq;

CREATE TABLE core.ordemSolicitacoes (
                id INTEGER NOT NULL DEFAULT nextval('core.ordemsolicitacoes_id_seq'),
                ordem_id INTEGER NOT NULL,
                usuario_id INTEGER NOT NULL,
                data TIMESTAMP NOT NULL,
                tipoSolicitacao VARCHAR(50) NOT NULL,
                resposta VARCHAR(255) DEFAULT ''::character varying,
                mensagemCorretora VARCHAR(255) DEFAULT ''::character varying,
                CONSTRAINT ordemsolicitacoes_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.ordemSolicitacoes IS 'Histórico das Ordens enviadas (solicitadas, pendentes, canceladas, etc)';
COMMENT ON COLUMN core.ordemSolicitacoes.ordem_id IS 'Código da Ordem';
COMMENT ON COLUMN core.ordemSolicitacoes.usuario_id IS 'Código do Usuário da Solicitação (pode não ser o mesmo operador)';
COMMENT ON COLUMN core.ordemSolicitacoes.data IS 'Data da Solicitacao';
COMMENT ON COLUMN core.ordemSolicitacoes.tipoSolicitacao IS 'Tipo de Solicitacao (Envio, Cancelamento, Sincronização)';
COMMENT ON COLUMN core.ordemSolicitacoes.resposta IS 'Resposta da Solicitacao';
COMMENT ON COLUMN core.ordemSolicitacoes.mensagemCorretora IS 'Mensagem da Corretora sobre a Solicitacao';


ALTER SEQUENCE core.ordemsolicitacoes_id_seq OWNED BY core.ordemSolicitacoes.id;

CREATE UNIQUE INDEX ordemsolicitacoes_idx
 ON core.ordemSolicitacoes
 ( ordem_id, data );

CREATE SEQUENCE core.movimento_id_seq;

CREATE TABLE core.movimento (
                id INTEGER NOT NULL DEFAULT nextval('core.movimento_id_seq'),
                operador_id INTEGER NOT NULL,
                ordem_id INTEGER NOT NULL,
                data TIMESTAMP DEFAULT now() NOT NULL,
                tipo VARCHAR(10) NOT NULL,
                descricao VARCHAR(255) NOT NULL,
                quantidade DOUBLE PRECISION DEFAULT 0 NOT NULL,
                valor DOUBLE PRECISION DEFAULT 0 NOT NULL,
                dataExecucao TIMESTAMP DEFAULT now(),
                valorIncidencia DOUBLE PRECISION DEFAULT 0,
                obs TEXT DEFAULT ''::text,
                idExecucaoOrdemCorretora VARCHAR(255) DEFAULT ''::character varying,
                CONSTRAINT movimento_pk PRIMARY KEY (id)
);
COMMENT ON TABLE core.movimento IS 'Movimentos realizados nas Contas dos Titulares nas Corretoras (execução de ordens, saques, depósitos, ganhos de juros, dividendos e participações, etc)';
COMMENT ON COLUMN core.movimento.id IS 'Codigo do Movimento';
COMMENT ON COLUMN core.movimento.operador_id IS 'Código do Operador';
COMMENT ON COLUMN core.movimento.ordem_id IS 'Código da Ordem (para Movimentos tipo Ordem)';
COMMENT ON COLUMN core.movimento.data IS 'Data de inserção do Movimento';
COMMENT ON COLUMN core.movimento.tipo IS 'Tipo do Movimento (depósito, saque, ordem de compra, ordem de venda, ordem de aluguel, etc...)';
COMMENT ON COLUMN core.movimento.descricao IS 'Descrição do Movimento';
COMMENT ON COLUMN core.movimento.quantidade IS 'Quantidade executada no Movimento';
COMMENT ON COLUMN core.movimento.valor IS 'Valor unitário executado no Movimento';
COMMENT ON COLUMN core.movimento.dataExecucao IS 'Data da liquidação/execução/finalização do Movimento';
COMMENT ON COLUMN core.movimento.valorIncidencia IS 'Valor absoluto total da Incidência sobre Movimento (corretagem, taxas, impostos, emolumentos, etc...)';
COMMENT ON COLUMN core.movimento.obs IS 'Observação pro Movimento';
COMMENT ON COLUMN core.movimento.idExecucaoOrdemCorretora IS 'Codigo do Movimento (Execução da Ordem) na Corretora';


ALTER SEQUENCE core.movimento_id_seq OWNED BY core.movimento.id;

CREATE TABLE core.ativoOfertas (
                ativo_id INTEGER NOT NULL,
                data TIMESTAMP NOT NULL,
                valor DOUBLE PRECISION DEFAULT 0 NOT NULL,
                quantidade INTEGER DEFAULT 0 NOT NULL,
                CONSTRAINT ativoofertas_pk PRIMARY KEY (ativo_id, data, valor)
);
COMMENT ON TABLE core.ativoOfertas IS 'Ofertas dos Ativos (book de ofertaS)';
COMMENT ON COLUMN core.ativoOfertas.ativo_id IS 'Codigo do Ativo';
COMMENT ON COLUMN core.ativoOfertas.data IS 'Data da Cotação com precisão de millisegundos';
COMMENT ON COLUMN core.ativoOfertas.valor IS 'Valor da Oferta';
COMMENT ON COLUMN core.ativoOfertas.quantidade IS 'Quantidade da Oferta';


CREATE TABLE core.ativoCotacoes (
                ativo_id INTEGER NOT NULL,
                data TIMESTAMP NOT NULL,
                intraDiario BIT DEFAULT '1' NOT NULL,
                abertura DOUBLE PRECISION DEFAULT 0,
                maximo DOUBLE PRECISION DEFAULT 0,
                minimo DOUBLE PRECISION DEFAULT 0,
                medio DOUBLE PRECISION DEFAULT 0,
                ultimo DOUBLE PRECISION DEFAULT 0,
                variacao DOUBLE PRECISION DEFAULT 0,
                melhorOfertaCompra DOUBLE PRECISION DEFAULT 0,
                melhorOfertaVenda DOUBLE PRECISION DEFAULT 0,
                qtdNegocios INTEGER DEFAULT 0,
                qtdPapeis INTEGER DEFAULT 0,
                volume DOUBLE PRECISION DEFAULT 0,
                CONSTRAINT ativocotacoes_pk PRIMARY KEY (ativo_id, data)
);
COMMENT ON TABLE core.ativoCotacoes IS 'Cotações dos Ativos';
COMMENT ON COLUMN core.ativoCotacoes.ativo_id IS 'Codigo do Ativo';
COMMENT ON COLUMN core.ativoCotacoes.data IS 'Data da Cotação com precisão de millisegundos';
COMMENT ON COLUMN core.ativoCotacoes.abertura IS 'Valor de abertura';
COMMENT ON COLUMN core.ativoCotacoes.maximo IS 'Valor máximo negociado';
COMMENT ON COLUMN core.ativoCotacoes.minimo IS 'Valor minimo negociado';
COMMENT ON COLUMN core.ativoCotacoes.medio IS 'Valor médio do dia';
COMMENT ON COLUMN core.ativoCotacoes.ultimo IS 'Último valor negociado';
COMMENT ON COLUMN core.ativoCotacoes.variacao IS 'Variacao do valor';
COMMENT ON COLUMN core.ativoCotacoes.melhorOfertaCompra IS 'Valor da melhor oferta de compra';
COMMENT ON COLUMN core.ativoCotacoes.melhorOfertaVenda IS 'Valor da melhor oferta de venda';
COMMENT ON COLUMN core.ativoCotacoes.qtdNegocios IS 'Qtd de negocios';
COMMENT ON COLUMN core.ativoCotacoes.qtdPapeis IS 'Qtd de papeis movimentados';
COMMENT ON COLUMN core.ativoCotacoes.volume IS 'Volume das negociacoes';


ALTER TABLE core.itemMenu ADD CONSTRAINT itemmenu_itemmenu_fk
FOREIGN KEY (itemMenu_id_pai)
REFERENCES core.itemMenu (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE core.relPapelRecurso ADD CONSTRAINT recurso_relpapelrecurso_fk
FOREIGN KEY (recurso_id)
REFERENCES core.Recurso (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE core.relPerfilPapel ADD CONSTRAINT ppel_relperfilpapel_fk
FOREIGN KEY (papel_id)
REFERENCES core.papel (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE core.relPapelRecurso ADD CONSTRAINT papel_relpapelrecurso_fk
FOREIGN KEY (papel_id)
REFERENCES core.papel (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE core.operador ADD CONSTRAINT operador_usuario_fk
FOREIGN KEY (usuario_id)
REFERENCES core.usuario (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.ordemSolicitacoes ADD CONSTRAINT ordemsolicitacoes_usuario_fk
FOREIGN KEY (usuario_id)
REFERENCES core.usuario (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.relUsuarioPerfil ADD CONSTRAINT relusuarioperfil_usuario_fk
FOREIGN KEY (usuario_id)
REFERENCES core.usuario (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.titular ADD CONSTRAINT titular_usuario_fk
FOREIGN KEY (usuario_id)
REFERENCES core.usuario (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.relUsuarioPerfil ADD CONSTRAINT relusuarioperfil_perfil_fk
FOREIGN KEY (perfil_id)
REFERENCES core.perfil (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.relPerfilPapel ADD CONSTRAINT perfil_relperfilpapel_fk
FOREIGN KEY (perfil_id)
REFERENCES core.perfil (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE core.ativo ADD CONSTRAINT ativo_empresa_fk
FOREIGN KEY (empresa_id)
REFERENCES core.empresa (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.titular ADD CONSTRAINT titular_corretora_fk
FOREIGN KEY (corretora_id)
REFERENCES core.corretora (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.conta ADD CONSTRAINT conta_titular_fk
FOREIGN KEY (titular_id)
REFERENCES core.titular (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.operador ADD CONSTRAINT operador_conta_fk
FOREIGN KEY (conta_id)
REFERENCES core.conta (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.movimento ADD CONSTRAINT movimento_operador_fk
FOREIGN KEY (operador_id)
REFERENCES core.operador (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.ordem ADD CONSTRAINT ordem_operador_fk
FOREIGN KEY (operador_id)
REFERENCES core.operador (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.portfolio ADD CONSTRAINT portfolio_operador_fk
FOREIGN KEY (operador_id)
REFERENCES core.operador (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.ordem ADD CONSTRAINT ordem_portfolio_fk
FOREIGN KEY (portfolio_id)
REFERENCES core.portfolio (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.relPortifolioAtivo ADD CONSTRAINT relportifolioativo_portfolio_fk
FOREIGN KEY (porifolio_id)
REFERENCES core.portfolio (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.ativoCotacoes ADD CONSTRAINT ativocotacoes_ativo_fk
FOREIGN KEY (ativo_id)
REFERENCES core.ativo (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.ativoOfertas ADD CONSTRAINT ativoofertas_ativo_fk
FOREIGN KEY (ativo_id)
REFERENCES core.ativo (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.ordem ADD CONSTRAINT ordem_ativo_fk
FOREIGN KEY (ativo_id)
REFERENCES core.ativo (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.relPortifolioAtivo ADD CONSTRAINT relportifolioativo_ativo_fk
FOREIGN KEY (ativo_id)
REFERENCES core.ativo (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.movimento ADD CONSTRAINT movimento_ordem_fk
FOREIGN KEY (ordem_id)
REFERENCES core.ordem (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE core.ordemSolicitacoes ADD CONSTRAINT ordemsolicitacoes_ordem_fk
FOREIGN KEY (ordem_id)
REFERENCES core.ordem (id)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;
