package br.com.caelum.jdbc.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.DAOException.DAOException;
import br.com.caelum.jdbc.modelo.Contato;

public class ContatoDao {

	private Connection connection;

	
	public ContatoDao(){
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Contato contato) {
		String sql = " insert into contatos " + "(nome, email, endereco, dataNascimento)" + ("values (?,?,?,?)");
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			
			stmt.execute();
			stmt.close();
			
		}catch(SQLException e){
			
			throw new DAOException();
			
		}
			
	}

	public List<Contato> getLista(){
		try{
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM contatos");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Contato contato = new Contato();
				contato.setId(rs.getLong("ID"));
				contato.setNome(rs.getString("Nome"));
				contato.setEmail(rs.getString("Email"));
				contato.setEndereco(rs.getString("Endereco"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("DataNascimento"));
				contato.setDataNascimento(data);
				
				contatos.add(contato);
				
			}
			rs.close();
			stmt.close();
			return contatos;
		}catch(SQLException e){
			throw new DAOException();
		}
	}
	
	public void altera(Contato contato){
	
		String sql = " update contato set nome=?, email=?," + 
		"endereco=?, dataNascimento=? where id=?";
		
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId() );
			stmt.execute();
			stmt.close();
		}catch(SQLException e){
			throw new DAOException();
		}
	}
	
	public void remove(Contato contato){
		try{
			PreparedStatement stmt = connection.prepareStatement("delete from contato where id=?");
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();
		}catch(SQLException e) {
			throw new DAOException();
		}
	}
}