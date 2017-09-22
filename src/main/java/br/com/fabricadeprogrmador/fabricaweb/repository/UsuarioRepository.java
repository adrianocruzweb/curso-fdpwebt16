package br.com.fabricadeprogrmador.fabricaweb.repository;

import java.util.List;

import br.com.fabricadeprogramador.fabricaweb.model.Usuario;

public interface UsuarioRepository {
	
	public void cadastrar(Usuario usuario) ;

	public List<Usuario> buscarTodos() ;

	public void alterar(Usuario usuario) ;
	
	public void salvar(Usuario usuario) ;

	public Usuario buscarPorId(Integer id) ;
	
	public void excluir(int id) ;

}
