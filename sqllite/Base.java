import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Base {
  public static void main(String[] args) {
    Connection connection = null;
    try {
      // Cria a conexão com o banco de dados
      connection = DriverManager.getConnection("jdbc:sqlite:base.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // Espera só por 30 segundos para conectar

      // Roda os comandos para o SQLite
      statement.executeUpdate("DROP TABLE IF EXISTS terminalroot");
      statement.executeUpdate("CREATE TABLE terminalroot (id INTEGER, name STRING)");
      statement.executeUpdate("INSERT INTO terminalroot VALUES(1, 'Marcos Oliveira')");
      statement.executeUpdate("INSERT INTO terminalroot VALUES(2, 'James Gosling')");
      ResultSet rs = statement.executeQuery("SELECT * FROM terminalroot");
      while(rs.next()) {
        // Ler os dados inseridos
        System.out.println("NOME DO CARA  : " + rs.getString("name"));
        System.out.println("IDENTIFICAÇÃO : " + rs.getInt("id"));
      }
      // Roda os comandos para o SQLite
      statement.executeUpdate("DROP TABLE IF EXISTS user");
      statement.executeUpdate("CREATE TABLE user (id INTEGER, name STRING, passworld STRING, nome_completo STRING, email VARCHAR(100), telefone VARCHAR)");
      statement.executeUpdate("INSERT INTO user VALUES(1, 'Joao', '111110', 'joao saraiva', 'saraivajandrade@gmail.com', '982343820')");
      statement.executeUpdate("INSERT INTO user VALUES(2, 'bia', '111110', 'binca andrade', 'bianca.andrade@gmail.com', '629283692')");
      statement.executeUpdate("INSERT INTO user VALUES(3, 'thiago', '111110', 'thiago saraiva', 'thpapapopi@gmail.com', '81141529')");
      ResultSet rs2 = statement.executeQuery("SELECT * FROM user");
    } catch(SQLException e) {
      // Se a mensagem de erro for: "out of memory",
      // Provavelmente erro ao criar(permissão) ou caminho do banco de dados
      System.err.println(e.getMessage());
    }

    finally {
      try {
        if(connection != null){
          connection.close();
        }
      } catch(SQLException e) {
        // Falhou também para fechar o arquivo
        System.err.println(e.getMessage());
      }
    }

  }
}