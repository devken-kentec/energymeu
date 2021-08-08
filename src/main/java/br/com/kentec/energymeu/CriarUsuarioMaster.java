package br.com.kentec.energymeu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.kentec.energymeu.domain.Cadastro;
import br.com.kentec.energymeu.repository.CadastroRepository;

@Configuration
@Profile("prod")
public class CriarUsuarioMaster {
	
	@Autowired
	private CadastroRepository cr;
	
	@Bean
	CommandLineRunner executar() {
		return args -> {
			
			long cadastro = cr.count();
			
			if(cadastro == 0) {
				Cadastro cad = new Cadastro();
				cad.setNome("Administrador");
				cad.setLogin("ADM");
				cad.setSenha("123");
				cad.setStatusMatricula("Ativo");
				cad.setTipoUser("Administrador");
				cr.save(cad);
			}
		};
	}
}	
