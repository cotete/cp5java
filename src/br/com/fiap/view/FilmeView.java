package br.com.fiap.view;

import br.com.fiap.controller.FilmeController;

import javax.swing.*;
import java.sql.SQLException;

public class FilmeView {
    public static void main(String[] args) {
        String titulo,genero,produtora;
        int opcao,codigo;
        String[] escolha = {"Inserir","Alterar","Excluir","Listar"};
        FilmeController filmeController = new FilmeController();
        do {
            try {
                opcao = JOptionPane.showOptionDialog(null,"Escolha como você quer manipular o filme","Escolha",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,escolha,escolha[0]);
                codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do filme"));
                switch (opcao){
                    case 0:
                        titulo = JOptionPane.showInputDialog("Insira o titulo do filme.");
                        genero = JOptionPane.showInputDialog("Insira o genero do filme.");
                        produtora = JOptionPane.showInputDialog("Insira a produtora do filme.");
                        System.out.println(filmeController.inserirFilme(titulo,genero,produtora));
                        break;
                    case 1:
                        titulo = JOptionPane.showInputDialog("Insira o titulo do filme.");
                        genero = JOptionPane.showInputDialog("Insira o genero do filme.");
                        produtora = JOptionPane.showInputDialog("Insira a produtora do filme.");
                        System.out.println(filmeController.alterarFilme(codigo,titulo,genero,produtora));
                        break;
                    case 2:
                        System.out.println(filmeController.excluirFilme(codigo));
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null,filmeController.listarFilmes());
                    default:
                        System.out.println("Opção invalida!");
                }
            } catch (SQLException e) {
                System.out.println("Erro de sql: " + e.getMessage());;
            } catch (ClassNotFoundException e) {
                System.out.println("Erro: " + e.getMessage());;
            }
        }while (JOptionPane.showConfirmDialog(null,"Deseja Continuar?") ==0);
        JOptionPane.showMessageDialog(null,"Adeus :3");
    }
}
