package com.htcursos.teste.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.htcursos.teste.connection.ConnectionFactory;
import com.htcursos.teste.model.Cliente;

public class ClienteRepository {

	Connection con;

	public ClienteRepository() {
		con = ConnectionFactory.getConnection();
	}

	public void salvar(Cliente cli) {

		String sql;
		PreparedStatement st;

		if (cli.getId() != null && cli.getId() > 0) {
			// Atualizar
			sql = "UPDATE cliente SET nome=? email=? cpf=? where id=?";
			try {
				st = con.prepareStatement(sql);
				st.setString(0, cli.getNome());
				st.setString(1, cli.getEmail());
				st.setString(2, cli.getCpf());
				st.setInt(3, cli.getId());
				st.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			// Cadastrar
			sql = "INSERT INTO cliente (nome,email,cpf) VALUES(?,?,?)";
			try {
				st = con.prepareStatement(sql);
				st.setString(1, cli.getNome());
				st.setString(2, cli.getEmail());
				st.setString(3, cli.getCpf());
				st.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void remover(Integer id) {

		String sql = "DELETE cliente where id=?";
		PreparedStatement st;
		try {
			st = con.prepareStatement(sql);
			st.setInt(0, id);
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Cliente> buscarTodos() {

		List<Cliente> clientes = new ArrayList<>();

		String sql = "SELECT * FROM cliente";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet result = st.executeQuery();
			while (result.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(result.getInt("id"));
				cliente.setNome(result.getString("nome"));
				cliente.setEmail(result.getString("email"));
				cliente.setCpf(result.getString("cpf"));

				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientes;
	}

}
