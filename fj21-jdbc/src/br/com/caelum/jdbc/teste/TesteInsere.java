package br.com.caelum.jdbc.teste;

import java.util.Calendar;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

public class TesteInsere {

	public static void main(String[] args) {
		
		Contato contato = new Contato();
		contato.setNome("Lucas Santana");
		contato.setEmail("lucassice@gmail.com");
		contato.setEndereco("Rua abreu lima");
		contato.setDataNascimento(Calendar.getInstance());
		
		ContatoDao dao = new ContatoDao();

		dao.adiciona(contato);
		
		System.out.println("Gravado com Sucesso");
		
		

	}

}
