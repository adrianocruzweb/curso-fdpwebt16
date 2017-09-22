package br.com.fabricadeprogrmador.fabricaweb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import br.com.fabricadeprogramador.fabricaweb.model.Usuario;

public class UsuarioRepositoryBanco implements UsuarioRepository {

	private Connection conexao = ConexaoFactory.criarConexao();

	@Override
	public void cadastrar(Usuario usuario) {
		String sql = "INSERT INTO usuario (nome, email) VALUES (?,?)";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Usuario> buscarTodos() {
		List<Usuario> lista = new ArrayList<>();
		String sql = "SELECT * FROM usuario ORDER BY nome";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet resultado = ps.executeQuery();
			
			while(resultado.next()) {
				
				Usuario usu = new Usuario();
				
				usu.setId(resultado.getInt("id"));
				usu.setNome(resultado.getString("nome"));
				usu.setEmail(resultado.getString("email"));
				
				lista.add(usu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public void alterar(Usuario usuario) {
		String sql = "UPDATE usuario SET nome = ?, email = ? WHERE id = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setInt(3, usuario.getId());

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void salvar(Usuario usuario) {
		if(usuario.getId() == null) {
			cadastrar(usuario);
		} else {
			alterar(usuario);
		}
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		Usuario usuRetorno = null;
		String sql = "SELECT * FROM usuario WHERE id = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setInt(1, id);
			
			ResultSet resultado = ps.executeQuery();
			
			if(resultado.next()) {
				
				usuRetorno = new Usuario();
				
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setEmail(resultado.getString("email"));
				
				return usuRetorno;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void excluir(int id) {
		String sql = "DELETE FROM usuario WHERE id = ?";

		try {
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setInt(1, id);

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}