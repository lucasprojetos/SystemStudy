package br.com.caelum.jdbc.teste;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaLista {

	public static void main(String[] args) {
	
		
		ContatoDao dao = new ContatoDao();
		
		List <Contato> contatos = dao.getLista();
		
		for (Contato contato : contatos){
			System.out.println("Nome: " +contato.getNome());
			System.out.println("Email: " +contato.getEmail());
			System.out.println("Endere�o: " +contato.getEndereco());
			
			//convers�o da data que vem do banco para imprimir abaixo como uma string(text)
			
			Date date = contato.getDataNascimento().getTime(); 
			 
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String text = df.format(date);
			
			System.out.println("Data de Nascimento: " + text + "\n");

		}

	}

}
