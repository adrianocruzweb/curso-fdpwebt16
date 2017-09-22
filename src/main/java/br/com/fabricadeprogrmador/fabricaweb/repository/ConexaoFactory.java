package br.com.fabricadeprogrmador.fabricaweb.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	
	public static Connection criarConexao(){		
		
		try {
			//Carrega o driver na memória
			Class.forName("org.postgresql.Driver");
			//Cria a conexão
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/usuariodb","postgres","postgres");
			
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return null;
	}

}
