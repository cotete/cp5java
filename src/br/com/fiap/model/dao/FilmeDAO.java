package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmeDAO {
    private Connection con; // Atributo para armazenar a conexão com o banco de dados

    // Construtor da classe que recebe uma conexão como parâmetro
    public FilmeDAO(Connection con) {
        this.con = con;
    }

    // Método para obter a conexão
    public Connection getCon() {
        return con;
    }

    // Método para inserir um filme no banco de dados
    public String inserir(Filme filme) {
        // Instrução SQL para inserir os dados do filme
        String sql = "insert into ddd_filme(titulo, genero, produtora) values(?, ?, ?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) { // Prepara a instrução SQL
            // Define os parâmetros da instrução SQL
            ps.setString(1, filme.getTitulo());
            ps.setString(2, filme.getGenero());
            ps.setString(3, filme.getProdutora());

            // Executa a instrução e verifica se houve inserção
            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso.";
            } else {
                return "Falha ao inserir.";
            }
        } catch (SQLException e) {
            return "erro de sql: " + e.getMessage(); // Retorna mensagem de erro em caso de falha
        }
    }

    // Método para alterar um filme no banco de dados
    public String alterar(Filme filme) {
        // Instrução SQL para atualizar os dados do filme
        String sql = "update ddd_filme set titulo = ?, genero = ?, produtora = ? where codigo = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) { // Prepara a instrução SQL
            // Define os parâmetros da instrução SQL
            ps.setString(1, filme.getTitulo());
            ps.setString(2, filme.getGenero());
            ps.setString(3, filme.getProdutora());
            ps.setString(4, String.valueOf(filme.getCodigo()));

            // Executa a instrução e verifica se houve alteração
            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso.";
            } else {
                return "Falha ao alterar.";
            }
        } catch (SQLException e) {
            return "erro de sql: " + e.getMessage(); // Retorna mensagem de erro em caso de falha
        }
    }

    // Método para excluir um filme do banco de dados
    public String excluir(Filme filme) {
        // Instrução SQL para excluir um filme com base no código
        String sql = "delete from ddd_filme where codigo = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)) { // Prepara a instrução SQL
            // Define o parâmetro da instrução SQL
            ps.setString(1, String.valueOf(filme.getCodigo()));

            // Executa a instrução e verifica se houve exclusão
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso.";
            } else {
                return "Falha ao excluir.";
            }
        } catch (SQLException e) {
            return "erro de sql: " + e.getMessage(); // Retorna mensagem de erro em caso de falha
        }
    }

    // Método para listar todos os filmes do banco de dados
    public ArrayList<Filme> listarTodos() {
        // Instrução SQL para selecionar todos os filmes ordenados por código
        String sql = "select * from ddd_filme order by codigo";
        ArrayList<Filme> listaFilme = new ArrayList<Filme>(); // Lista para armazenar os filmes

        try (PreparedStatement ps = getCon().prepareStatement(sql)) { // Prepara a instrução SQL
            ResultSet rs = ps.executeQuery(sql); // Executa a consulta e obtém o resultado
            if (rs != null) {
                // Itera sobre o resultado e adiciona cada filme à lista
                while (rs.next()) {
                    Filme filme = new Filme();
                    filme.setCodigo(Integer.parseInt(rs.getString(1)));
                    filme.setTitulo(rs.getString(2));
                    filme.setGenero(rs.getString(3));
                    filme.setProdutora(rs.getString(4));
                    listaFilme.add(filme);
                }
                return listaFilme; // Retorna a lista de filmes
            } else {
                return null; // Retorna null se não houver resultados
            }
        } catch (SQLException e) {
            System.out.println("erro de sql: " + e.getMessage()); // Exibe mensagem de erro em caso de falha
            return null;
        }
    }
}
