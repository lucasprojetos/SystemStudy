package br.com.caelum.jdbc.teste;


import br.com.caelum.jdbc.dao.FuncionarioDao;
import br.com.caelum.jdbc.modelo.Funcionario;

public class InsereFuncionario {

	public static void main(String[] args) {

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Lucas Programando");
		funcionario.setSenha("12345");
		funcionario.setUsuario("LuketiReiDelas");
		
		FuncionarioDao dao = new FuncionarioDao();
		
		dao.adiciona(funcionario);
		
		System.out.println("Gravou o Funcionário");
		
	}

}
