package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.DAOException.DAOException;
import br.com.caelum.jdbc.modelo.Funcionario;

public class FuncionarioDao {


	private Connection connection;

	
	public FuncionarioDao(){
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Funcionario funcionario) {
		String sql = " insert into funcionarios " + "(nome, usuario, senha)" + ("values (?,?,?)");
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());
		
			stmt.execute();
			stmt.close();
			
		}catch(SQLException e){
			
			throw new DAOException();
			
		}
			
	}

	public List<Funcionario> getLista(){
		try{
			List<Funcionario> contatos = new ArrayList<Funcionario>();
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM funcionarios");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getLong("ID"));
				funcionario.setNome(rs.getString("Nome"));
				funcionario.setUsuario(rs.getString("Usuario"));
				funcionario.setSenha(rs.getString("Senha"));
				
				contatos.add(funcionario);
				
			}
			rs.close();
			stmt.close();
			return contatos;
		}catch(SQLException e){
			throw new DAOException();
		}
	}
	
	public void altera(Funcionario funcionario){
	
		String sql = " update funcionario set nome=?, usuario=?," + 
		"senha=?, where id=?";
		
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());
			stmt.setLong(5, funcionario.getId() );
			
			stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new DAOException();
		}
	}
	
	public void remove(Funcionario funcionario){
		try{
			PreparedStatement stmt = connection.prepareStatement("delete from funcionarios where id=?");
			stmt.setLong(1, funcionario.getId());
			stmt.execute();
			stmt.close();
		}catch(SQLException e) {
			throw new DAOException();
		}
	}
}
