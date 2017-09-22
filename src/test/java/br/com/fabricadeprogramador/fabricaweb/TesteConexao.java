package br.com.fabricadeprogramador.fabricaweb;

import java.sql.Connection;

import br.com.fabricadeprogrmador.fabricaweb.repository.ConexaoFactory;

public class TesteConexao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conexao = ConexaoFactory.criarConexao();
		
		if(conexao == null){
			System.out.println("erro");
		}else{
			System.out.println("ok");
		}

	}

}
