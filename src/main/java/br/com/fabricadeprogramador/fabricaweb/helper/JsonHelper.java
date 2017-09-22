package br.com.fabricadeprogramador.fabricaweb.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.fabricadeprogramador.fabricaweb.model.Cliente;
import br.com.fabricadeprogramador.fabricaweb.model.Usuario;

public class JsonHelper {
	public String gerarJsonLista(List<?> lista) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		StringBuffer json = new StringBuffer("[");
		
		for (int i = 0; i < lista.size(); i++) {
			json.append(gerarJson(lista.get(i)));
			if(i < lista.size()-1){
				json.append(",");
			}			
		}
		json.append("]");
		
		return json.toString();		
	}
	
	public String gerarJson(Object o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class clazz = o.getClass();
		
		Method[] metodos = clazz.getDeclaredMethods();
		
		StringBuffer json = new StringBuffer("{");
		
		int contGeter = 0;
		for (int i = 0; i < metodos.length; i++) {
			if(metodos[i].getName().indexOf("get")!=-1){
				contGeter++;
				
				String propriedade = metodos[i].getName().substring(3);			
				
				Object valor = metodos[i].invoke(o); 
				
				json.append("\""+propriedade.toLowerCase()+"\"");
				json.append(":");
				json.append("\""+valor+"\"");
				
				
				
				json.append(",");
			}			
			
						
		}
		json.deleteCharAt(json.lastIndexOf(","));		
		json.append("}");
		return json.toString();
	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Usuario u = new Usuario();
		u.setNome("zé");
		u.setEmail("ze@gmail");	
		
		Usuario u2 = new Usuario();
		u2.setNome("jão");
		u2.setEmail("jão@gmail");
		
		List<Object> listaUsuarios = new ArrayList<Object>();
		listaUsuarios.add(u);
		listaUsuarios.add(u2);
		
		Cliente c = new Cliente();
		c.setCpf("90920");
		
		Cliente c2 = new Cliente();
		c2.setCpf("27726");		
		
		//List<Object> listaClientes = Arrays.asList(c,c2);
		
		
		List<Object> listaMista = Arrays.asList(u,u2,c,c2);
		
		
		JsonHelper helper = new JsonHelper();

		//System.out.println(helper.gerarJsonLista(listaClientes));
	
		System.out.println(helper.gerarJsonLista(listaMista));
		
		System.out.println(helper.gerarJson(c));		
		
	}

}
