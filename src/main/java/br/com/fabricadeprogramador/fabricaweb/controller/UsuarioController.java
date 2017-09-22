package br.com.fabricadeprogramador.fabricaweb.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.fabricaweb.helper.JsonHelper;
import br.com.fabricadeprogramador.fabricaweb.model.Usuario;
import br.com.fabricadeprogrmador.fabricaweb.repository.UsuarioRepositoryBanco;

@WebServlet(urlPatterns="/usucontroller")
public class UsuarioController extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	private JsonHelper helper = new JsonHelper(); 
	private UsuarioRepositoryBanco usuarioRepository = new UsuarioRepositoryBanco();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String json;
		try {
			json = helper.gerarJsonLista(usuarioRepository.buscarTodos());
			resp.getWriter().print(json);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		
		Usuario usuario = new Usuario(nome,email);
		
		usuarioRepository.cadastrar(usuario);
		
		try {
			resp.getWriter().println(helper.gerarJson(usuario));
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Usuario usu = new Usuario();
		
		usu.setId(Integer.parseInt(req.getParameter("id")));
		usu.setNome(req.getParameter("nome"));
		usu.setEmail(req.getParameter("email"));
		
	    usuarioRepository.alterar(usu);		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int indice = Integer.parseInt(req.getParameter("id"));
		
		usuarioRepository.excluir(indice);		

		resp.getWriter().print("apagado");		
	}
	
}
