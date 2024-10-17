package br.com.fiap.model.dao;

import br.com.fiap.model.dto.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private Connection con;
    private Player player;

    public PlayerDAO(Connection con){
        this.con=con;
    }

    public Connection getCon() {
        return con;
    }

    public String inserir(Object object){
        player = (Player) object;
        String sql = "insert into ddd_player(id_player, nome, nivel, pontuacao) values(?,?,?,?)";
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setInt(1, player.getId_player());
            ps.setString(2, player.getNome());
            ps.setString(3, player.getNivel());
            ps.setString(4, player.getPontuacao());
            if (ps.executeUpdate()>0){
                return "inserido com sucesso";
            }else {
                return "erro ao inserir";

            }
        }catch (SQLException e ){
            return "erro sql: " + e.getMessage();
        }
    }

    public String alternar(Object object){
        player = (Player) object;
        String sql = "update ddd_player set nome = ? ,nivel = ?, pontuacao = ? where id_player = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setString(1, player.getNome());
            ps.setString(2, player.getNivel());
            ps.setString(3, player.getPontuacao());
            ps.setInt(4, player.getId_player());
            if (ps.executeUpdate()>0){
                return "alterado com sucesso";
            }else {
                return "erro ao alterar";

            }
        }catch (SQLException e ){
            return "erro de sql: " + e.getMessage();
        }
    }
    public String excluir(Object object){
        player = (Player) object;
        String sql = "delete from ddd_player where id_player = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setInt(1, player.getId_player());
            if (ps.executeUpdate()>0){
                return "excluido com sucesso";
            }else {
                return "erro ao excluir";

            }
        }catch (SQLException e ){
            return "erro de sql: " + e.getMessage();
        }
    }

    public String listarUm(Object object) throws ClassNotFoundException, SQLException{
        player = (Player) object;
        String sql = "select * from ddd_player where id_player = ?";
        try (PreparedStatement ps = getCon().prepareStatement(sql)){
            ps.setInt(1, player.getId_player());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return "Id_number: " + player.getId_player() + "\nNome: " + rs.getNString("nome") + "\nNivel: " + rs.getNString("nivel") + "\nPontuação: " + rs.getNString("pontuacao");
            }else {
                return "erro ao excluir";

            }
        }catch (SQLException e ){
            return "erro de sql: " + e.getMessage();
        }


    }

    public List<Player> listarTodos() throws SQLException {
        List<Player> jogadores = new ArrayList<>();
        String sql = "SELECT * FROM ddd_player";

        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Player player = new Player();
                player.setId_player(rs.getInt("id_player"));
                player.setNome(rs.getNString("nome"));
                player.setNivel(rs.getNString("nivel"));
                player.setPontuacao(rs.getNString("pontuacao"));
                // Adicionar o objeto Player à lista
                jogadores.add(player);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar jogadores: " + e.getMessage());
        }
        return jogadores;  // Retorna a lista de jogadores
    }

    public List<Player> rankearJogadores() throws SQLException {
        List<Player> todosOsJogadores = new ArrayList<>();

        // Consulta SQL para selecionar todos os jogadores do banco
        String sql = "SELECT * FROM ddd_player";

        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            // Iterar sobre os resultados e criar objetos Player
            while (rs.next()) {
                Player player = new Player();
                player.setId_player(rs.getInt("id_player"));
                player.setNome(rs.getString("nome"));
                player.setNivel(rs.getString("nivel"));
                player.setPontuacao(rs.getString("pontuacao"));

                // Adicionar o jogador à lista
                todosOsJogadores.add(player);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar jogadores: " + e.getMessage());
        }

        // Classificar a lista de jogadores pela pontuação (em ordem decrescente)
        todosOsJogadores.sort((p1, p2) -> Integer.compare(Integer.parseInt(p2.getPontuacao()), Integer.parseInt(p1.getPontuacao())));

        // Retornar a lista ordenada
        return todosOsJogadores;
    }


//    public List<Player> rankearJogadores(List<Player> todosOsJogadores) {
//        todosOsJogadores.sort((p1, p2) -> Integer.compare(Integer.parseInt(p2.getPontuacao()), Integer.parseInt(p1.getPontuacao())));
//        return todosOsJogadores;
//    }


    public String verInformacoesCompletas(Player player) throws SQLException {
        String sql = "SELECT * FROM ddd_player WHERE id_player = ?";

        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            // Configurar o parâmetro da consulta com o ID do jogador
            ps.setInt(1, player.getId_player());

            ResultSet rs = ps.executeQuery();

            // Verificar se o jogador existe no banco de dados
            if (rs.next()) {
                // Obter os dados do jogador
                String nome = rs.getString("nome");
                String nivel = rs.getString("nivel");
                String pontuacao = rs.getString("pontuacao");

                // Retornar as informações formatadas
                return "Player: " + nome + " | ID: " + player.getId_player() + " | Nível: " + nivel + " | Pontuação: " + pontuacao;
            } else {
                return "Jogador com ID " + player.getId_player() + " não encontrado.";
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar informações do jogador: " + e.getMessage());
        }
    }



    public String verDesafioDiario(Player player) throws SQLException {
        String sql = "SELECT nome FROM ddd_player WHERE id_player = ?";

        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            // Configura o parâmetro da consulta com o ID do jogador
            ps.setInt(1, player.getId_player());

            ResultSet rs = ps.executeQuery();

            // Verifica se o jogador foi encontrado no banco de dados
            if (rs.next()) {
                String nome = rs.getString("nome");
                // Retorna o desafio diário com o nome do jogador
                return "Desafio Diário para " + nome + ": Ganhe 500 pontos no modo Survival!";
            } else {
                return "Jogador com ID " + player.getId_player() + " não encontrado.";
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar o desafio diário: " + e.getMessage());
        }
    }



    public String verInsignias(Player player) throws SQLException {
        String sql = "SELECT nome FROM ddd_player WHERE id_player = ?";

        try (PreparedStatement ps = getCon().prepareStatement(sql)) {
            // Configura o parâmetro da consulta com o ID do jogador
            ps.setInt(1, player.getId_player());

            ResultSet rs = ps.executeQuery();

            // Verifica se o jogador foi encontrado no banco de dados
            if (rs.next()) {
                String nome = rs.getString("nome");
                // Retorna as insígnias com o nome do jogador
                return "Insígnias de " + nome + ": [Iniciante, Sobrevivente, Campeão]";
            } else {
                return "Jogador com ID " + player.getId_player() + " não encontrado.";
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar as insígnias do jogador: " + e.getMessage());
        }
    }




}
