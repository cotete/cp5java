package br.com.fiap.controller;

import br.com.fiap.model.factory.ConnectionFactory;
import br.com.fiap.model.dao.FilmeDAO;
import br.com.fiap.model.dto.Filme;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmeController {
    public String inserirFilme(String titulo,String genero,String produtora) throws ClassNotFoundException, SQLException {
        String resultado;
        Connection con = ConnectionFactory.abrirConexao();
        Filme filme = new Filme();
        filme.setTitulo(titulo);
        filme.setGenero(genero);
        filme.setProdutora(produtora);
        FilmeDAO dao = new FilmeDAO(con);
        resultado = dao.inserir(filme);
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }
    public String alterarFilme(int codigo,String titulo,String genero,String produtora) throws ClassNotFoundException, SQLException {
        String resultado;
        Connection con = ConnectionFactory.abrirConexao();
        Filme filme = new Filme();
        filme.setGenero(genero);
        filme.setTitulo(titulo);
        filme.setCodigo(codigo);
        filme.setProdutora(produtora);
        FilmeDAO dao = new FilmeDAO(con);
        resultado = dao.alterar(filme);
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }
    public String excluirFilme(int codigo) throws ClassNotFoundException, SQLException {
        String resultado;
        Connection con = ConnectionFactory.abrirConexao();
        Filme filme = new Filme();
        filme.setCodigo(codigo);
        FilmeDAO dao = new FilmeDAO(con);
        resultado = dao.excluir(filme);
        ConnectionFactory.fecharConexao(con);
        return resultado;
    }
    public String listarFilmes() throws ClassNotFoundException, SQLException {
        String resultado = "";
        Connection con = ConnectionFactory.abrirConexao();
        ArrayList<Filme> listaFilmes = new ArrayList<Filme>();
        FilmeDAO dao = new FilmeDAO(con);
        listaFilmes = dao.listarTodos();
        if (listaFilmes != null){
            for (Filme filme : listaFilmes){
                resultado += "Codigo: "+filme.getCodigo()+"\nTitulo: " +filme.getTitulo()+"\nGenero: "+filme.getGenero()+"\nProdutora: " + filme.getProdutora()+"\n";
            }
            ConnectionFactory.fecharConexao(con);
            return resultado;
        }
        ConnectionFactory.fecharConexao(con);
        return "Não há filmes para listar.";
    }
}
