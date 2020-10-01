package util;

import java.io.IOException;
import java.util.Properties;

/***
 * Esta classe e responsavel por armazenar informacoes para a conexao com o
 * banco de dados.
 * 
 * @author User
 *
 */
public class Configuracoes {
	public static final String DRIVER_BD = "DRIVER_BD";
	public static final String SENHA_BD = "SENHA_BD";
	public static final String USUARIO_BD = "USUARIO_BD";
	public static final String URLCONEXAO = "URLCONEXAO";
	private String urlConexao = "";
	private String usuarioBd = "";
	private String senhaBd = "";
	private String driveBd = "";
	private static Configuracoes conf;

	/***
	 * O metodo valida se possui uma instancia de configuracoes, se nao possuir ele
	 * entao recebe uma nova instancia de configuracoes;
	 * 
	 * @return conf
	 */
	public static Configuracoes getInstance() {
		if (conf == null) {
			conf = new Configuracoes();
			conf.init();
		}
		return conf;
	}

	/***
	 * Retorna o atributo urlConexao do tipo String.
	 * 
	 * @return urlConexao
	 */
	public String getUrlConexao() {
		return urlConexao;
	}

	/***
	 * Retorna o atributo usuarioBd do tipo String.
	 * 
	 * @return usuarioBd
	 */
	public String getUsuarioBd() {
		return usuarioBd;
	}

	/***
	 * Retorna o atributo senhaBd do tipo String.
	 * 
	 * @return senhaBd
	 */
	public String getSenhaBd() {
		return senhaBd;
	}

	/***
	 * Retorna o atributo driveBd do tipo String.
	 * 
	 * @return driveBd
	 */
	public String getDriveBd() {
		return driveBd;
	}

	private Configuracoes() {

	}

	/***
	 * O metodo construtor recebe dados do documento "config.properties" e os
	 * armazena em atributos.
	 */
	private void init() {
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
			urlConexao = prop.getProperty(URLCONEXAO, "jdbc:postgresql://localhost:5433/lan_manager");
			usuarioBd = prop.getProperty(USUARIO_BD, "postgres");
			senhaBd = prop.getProperty(SENHA_BD, "");
			driveBd = prop.getProperty(DRIVER_BD, "org.postgresql.Driver");

		} catch (IOException e) {
			e.printStackTrace();
		} // carrega propriedades do arquivo jdbc.properties,
	}
}
