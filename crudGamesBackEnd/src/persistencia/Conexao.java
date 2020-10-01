package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import util.Configuracoes;

/***
 * 
 * @author Kaleb Vieira Evangelista
 * @author Luan Leite Leão
 * 
 *         Esta classe gerencia uma conexao com o banco de dados uma unica vez,
 *         este padrao garante a existencia de apenas uma instancia de uma
 *         classe, mantendo um ponto global de acesso ao seu objeto.
 * 
 */

public class Conexao {

	protected Connection con;
	protected static Conexao dao;

	/***
	 * O metodo valida se possui uma instancia de conexao, se nao possuir ele entao
	 * recebe uma nova instancia de conexao;
	 * 
	 * @return Uma instancia global da conexao.
	 */
	public static Conexao getIntancia() {
		if (dao == null) {
			dao = new Conexao();
		}
		return dao;
	}

	/***
	 * O metodo construtor cria uma conexao com o banco de dados uma unica vez
	 * utilizando as informaçoes de configuraçoes e o drive de banco de dados, e
	 * validado se a conexao foi bem sucedida e se nao for e gerado uma mensagem
	 * descrevendo o problema.
	 * 
	 * @throws RuntimeException
	 */
	protected Conexao() throws RuntimeException {
		Configuracoes conf = Configuracoes.getInstance();
		String driver = conf.getDriveBd();
		try {
			Class.forName(driver);
			// padrao jdbc ://nomeserver:porta/database
			con = DriverManager.getConnection(conf.getUrlConexao(), conf.getUsuarioBd(), conf.getSenhaBd());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			throw new RuntimeException("DAOFuncionarioSQL erro(1):" + e.getMessage());
		}
	}

}