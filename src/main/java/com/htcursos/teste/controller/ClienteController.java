package com.htcursos.teste.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.htcursos.teste.model.Cliente;
import com.htcursos.teste.repository.ClienteRepository;

@WebServlet("/clientes")
public class ClienteController extends HttpServlet {

	ClienteRepository clienteRepository = new ClienteRepository();
	Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Cliente> clientes = clienteRepository.buscarTodos();
		resp.getWriter().print(gson.toJson(clientes));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				jb.append(line);
			}
		} catch (Exception e) {
			resp.getWriter().print("{ \"error\":\"Erro ao converter entrada de requisição\" }"); 
		}
		
		 Cliente cli = gson.fromJson(jb.toString(), Cliente.class);
		 
		 clienteRepository.salvar(cli);
		 
		 resp.getWriter().print("{ \"msg\":\"Salvo com sucesso\" }");

	}

}
