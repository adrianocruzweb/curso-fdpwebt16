package br.com.fabricadeprogramador.fabricaweb;

import java.util.List;

import br.com.fabricadeprogramador.fabricaweb.model.Usuario;
import br.com.fabricadeprogrmador.fabricaweb.repository.UsuarioRepositoryBanco;

public class TesteUsuarioRepository {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UsuarioRepositoryBanco repository = new UsuarioRepositoryBanco();
		
		List<Usuario> lista;
		
		lista = repository.buscarTodos();
		
		for (Usuario usuario2 : lista) {
			System.out.println(usuario2.toString());
		}	
	}
}
