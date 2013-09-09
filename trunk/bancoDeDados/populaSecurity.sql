--senha: senha
insert into usuario values (1, 'goiaba', '4e9394b4d2876b8741b10a2fb46589b60f1a1c121e9bc4c280fae85af75b75ae8609d49f0e4215f3b682306dc7f262b171ffc181f886f764d638210d6ff7ba28', 'Bruno Godoy Martins Corrêa', 'brunogmc@gmail.com', '', now(), current_timestamp + 365 * interval '1 day');
insert into sec_papel values (1, 'role1', '', '', now(), current_timestamp + 365 * interval '1 day');
insert into sec_recurso values (1, 'faces resources', '', 'public_starts_with_url','/javax.faces.resource/', now(), current_timestamp + 365 * interval '1 day');
insert into sec_recurso values (2, 'login page', '', 'public_url','/login.jsf', now(), current_timestamp + 365 * interval '1 day');
insert into sec_recurso values (3, 'main page', '', 'private_url','/index.jsf', now(), current_timestamp + 365 * interval '1 day');
insert into sec_recurso values (4, 'css resources', '', 'public_starts_with_url','/css/', now(), current_timestamp + 365 * interval '1 day');
insert into sec_recurso values (5, 'RD Recursos', 'Recuperação/Remoção de Recursos do Sistema', 'private_url', '/pages/security/recurso/list.jsf', '2013-08-08 20:15:26.872291', '2014-07-31 19:03:33.830351');
insert into sec_recurso values (6, 'CU Recursos', 'Criação/Edição de Recursos do Sistema', 'private_url', '/pages/security/recurso/edit.jsf', '2013-08-08 23:30:57.659858', '2014-07-31 19:03:33.830351');


insert into sec_relusuariopapel values (1, 1, 1, true, now(), current_timestamp + 365 * interval '1 day');
insert into sec_relpapelrecurso values (1, 1, 1);
insert into sec_relpapelrecurso values (2, 1, 2);
insert into sec_relpapelrecurso values (3, 1, 3);
insert into sec_relpapelrecurso values (4, 1, 4);
insert into sec_relpapelrecurso values (5, 1, 5);
insert into sec_relpapelrecurso values (6, 1, 6);
