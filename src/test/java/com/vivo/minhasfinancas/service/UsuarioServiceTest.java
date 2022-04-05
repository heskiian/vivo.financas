package com.vivo.minhasfinancas.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vivo.minhasfinancas.exception.RegraNegocioException;
import com.vivo.minhasfinancas.model.entity.Usuario;
import com.vivo.minhasfinancas.model.repository.UsuarioRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	public static String EMAIL = "fulano@email.com"; 
	public static String NOME = "Fulano das Flores"; 
	
	@Autowired
	UsuarioService service;
	
	@Autowired
	UsuarioRepository repository;
	
	@Test
	public void deveValidarEmail() {
		Assertions.assertDoesNotThrow(() -> {
			
			UsuarioRepository usuarioRepositoryMock = Mockito.mock(UsuarioRepository.class);
 
			// cenario
			repository.deleteAll();
 
			// acao
			service.validarEmail(EMAIL);
		});
	}
 
	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		Assertions.assertThrows(RegraNegocioException.class, () -> {
			//cenario
			Usuario usuario = Usuario.builder().nome(NOME).email(EMAIL).build();		
			repository.save(usuario);
 
			//acao
			service.validarEmail(EMAIL);
		});
	}
}
