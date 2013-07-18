package br.com.gome.gomebroker.business;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.gome.gomebroker.domain.Usuario;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

@RunWith(DemoiselleRunner.class)
public class UsuarioBCTest {

//	@Inject
//	private UsuarioBC usuarioBC;
	
	@Test
	public void insert() {

		Usuario usuario = new Usuario();
		usuario.setDataCadastro(new Timestamp(new Date().getTime()));
		usuario.setDataDesativacao(null);
		usuario.setEmail("brunogmc@gmail.com");
		usuario.setNome("brunocorrea");
		usuario.setNomeCompleto("Bruno Godoy Martins Correa");
		usuario.setObs(null);
		usuario.setSenha("senha");
		
//		usuarioBC.inserir(usuario);
//		
//		List<Usuario> usuarios = usuarioBC.findAll();
		
//		assertNotNull(usuarios);
//		assertEquals(1, usuarios.size());
//		assertSame(usuario, usuarios.get(0));
		
	}
	
}
