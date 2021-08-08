package br.com.kentec.energymeu.service;

import java.util.Optional;

import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import br.com.kentec.energymeu.domain.Cadastro;

import br.com.kentec.energymeu.repository.CadastroRepository;

import java.io.IOException;
import java.io.InputStream;




@Service
public class CadastroService {
	
	private Cadastro cadastroValido;
	
	@Autowired
	private CadastroRepository cr;
	
	
	public Long startServer() {
		return cr.count();
	}
	
	
	public Iterable<Cadastro> findAll(){
		return cr.findAll();
	}
	
	public Optional<Cadastro> findById(Long id){
		return cr.findById(id);
	}
	
	public Cadastro findByLoginSenha(Long login, String senha) {
		
		Cadastro cadastro = cr.findByLoginSenha(login, senha);
		
		if(cadastro.getId().equals(login) && cadastro.getSenha().equals(senha)) {
			
			this.cadastroValido = cadastro;
		}
		
		return this.cadastroValido;
	}
	
	
	public Page<Cadastro> listarCadastro(Integer page, Integer size){
		PageRequest pageRequest = PageRequest.of(page, size);
		return cr.findAll(pageRequest);
	}
	
	public void create(Cadastro cadastro) {
		cr.save(cadastro);
	}
	
	public void update(Cadastro cadastro) {
		Optional<Cadastro> c = cr.findById(cadastro.getId());
		
		if(c.isPresent()) {
			cr.save(cadastro);	
		}
	}
	
	public void updateSenha(Cadastro cadastro) {
		Optional<Cadastro> c = cr.findById(cadastro.getId());
		
		if(c.isPresent()) {
			c.get().setTipoUser(cadastro.getTipoUser());
			c.get().setStatusMatricula(cadastro.getStatusMatricula());
			c.get().setLogin(cadastro.getLogin());
			c.get().setSenha(cadastro.getSenha());
			
			cr.save(c.get());
		}
	}
	
	public void mudarStatusCadastro(Long id) {
		Optional<Cadastro> c = cr.findById(id);
		
		if(c.isPresent()) {
			c.get().setStatusMatricula("Inativo");
			cr.save(c.get());
		}
	}
	
	


	
	public byte[] adicionarFoto(Part arquivo, Long id) {
		
		Optional<Cadastro> cad = cr.findById(id);
		
		return cad.map(c -> {
			try {
				InputStream is = arquivo.getInputStream();
				byte[] bytes = new byte[(int) arquivo.getSize()];
				IOUtils.readFully(is, bytes);
				c.setFoto(bytes);
				cr.save(c);
				is.close();
				return bytes;
			} catch (IOException e) {
				return null;
			}
		}).orElse(null);
	}

}
