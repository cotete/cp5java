package br.com.fiap.controller;

import br.com.fiap.model.factory.ConnectionFactory;
import br.com.fiap.model.dao.FilmeDAO;
import br.com.fiap.model.dto.Filme;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmeController {

    // Método para inserir um novo filme no banco de dados
    public String inserirFilme(String titulo, String genero, String produtora) throws ClassNotFoundException, SQLException {
        String resultado;  // Variável para armazenar o resultado da operação
        Connection con = ConnectionFactory.abrirConexao();  // Abre uma conexão com o banco de dados
        Filme filme = new Filme();  // Cria um novo objeto Filme
        filme.setTitulo(titulo);  // Define o título do filme
        filme.setGenero(genero);  // Define o gênero do filme
        filme.setProdutora(produtora);  // Define a produtora do filme
        FilmeDAO dao = new FilmeDAO(con);  // Cria um DAO (Data Access Object) para manipulação do banco de dados
        resultado = dao.inserir(filme);  // Chama o método para inserir o filme no banco de dados e armazena o resultado
        ConnectionFactory.fecharConexao(con);  // Fecha a conexão com o banco de dados
        return resultado;  // Retorna o resultado da operação
    }

    // Método para alterar um filme existente no banco de dados
    public String alterarFilme(int codigo, String titulo, String genero, String produtora) throws ClassNotFoundException, SQLException {
        String resultado;  // Variável para armazenar o resultado da operação
        Connection con = ConnectionFactory.abrirConexao();  // Abre uma conexão com o banco de dados
        Filme filme = new Filme();  // Cria um novo objeto Filme
        filme.setCodigo(codigo);  // Define o código do filme (para identificar qual filme alterar)
        filme.setTitulo(titulo);  // Define o novo título do filme
        filme.setGenero(genero);  // Define o novo gênero do filme
        filme.setProdutora(produtora);  // Define a nova produtora do filme
        FilmeDAO dao = new FilmeDAO(con);  // Cria um DAO para manipulação do banco de dados
        resultado = dao.alterar(filme);  // Chama o método para alterar o filme no banco de dados e armazena o resultado
        ConnectionFactory.fecharConexao(con);  // Fecha a conexão com o banco de dados
        return resultado;  // Retorna o resultado da operação
    }

    // Método para excluir um filme do banco de dados
    public String excluirFilme(int codigo) throws ClassNotFoundException, SQLException {
        String resultado;  // Variável para armazenar o resultado da operação
        Connection con = ConnectionFactory.abrirConexao();  // Abre uma conexão com o banco de dados
        Filme filme = new Filme();  // Cria um novo objeto Filme
        filme.setCodigo(codigo);  // Define o código do filme (para identificar qual filme excluir)
        FilmeDAO dao = new FilmeDAO(con);  // Cria um DAO para manipulação do banco de dados
        resultado = dao.excluir(filme);  // Chama o método para excluir o filme no banco de dados e armazena o resultado
        ConnectionFactory.fecharConexao(con);  // Fecha a conexão com o banco de dados
        return resultado;  // Retorna o resultado da operação
    }

    // Método para listar todos os filmes do banco de dados
    public String listarFilmes() throws ClassNotFoundException, SQLException {
        String resultado = "";  // Variável para armazenar o resultado da operação
        Connection con = ConnectionFactory.abrirConexao();  // Abre uma conexão com o banco de dados
        ArrayList<Filme> listaFilmes = new ArrayList<Filme>();  // Cria uma lista para armazenar os filmes
        FilmeDAO dao = new FilmeDAO(con);  // Cria um DAO para manipulação do banco de dados
        listaFilmes = dao.listarTodos();  // Chama o método para listar todos os filmes e armazena-os na lista

        // Verifica se a lista de filmes não está vazia
        if (listaFilmes != null) {
            // Itera sobre a lista de filmes e concatena as informações de cada filme na variável 'resultado'
            for (Filme filme : listaFilmes) {
                resultado += "Codigo: " + filme.getCodigo() + "\nTitulo: " + filme.getTitulo() + "\nGenero: " + filme.getGenero() + "\nProdutora: " + filme.getProdutora() + "\n";
            }
            ConnectionFactory.fecharConexao(con);  // Fecha a conexão com o banco de dados
            return resultado;  // Retorna a lista de filmes formatada como uma string
        }

        ConnectionFactory.fecharConexao(con);  // Fecha a conexão com o banco de dados
        return "Não há filmes para listar.";  // Retorna uma mensagem caso não haja filmes para listar
    }
}
