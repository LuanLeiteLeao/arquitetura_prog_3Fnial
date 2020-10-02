package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import mensagem.Mensagem;
import mensagem.PersistenciaMensagem;
import model.Tabela;
import model.TabelaNparaN;

/***
 * A classe e responsavel por gerenciar as conexoes com o banco de dados,
 * utilizando metodos e atributos herdados da classe de conexao.
 * 
 * @author User
 *
 */
public class DAO extends Conexao {
	private PersistenciaMensagem mensagem = new PersistenciaMensagem();

	/***
	 * O metodo construtor pertence a esta classe e tambem e responsavel pelo metodo
	 * construtor presente na classe herdada.
	 */
	public DAO() {
		super();

	}

	/***
	 * O metodo verifica a quantidade de colunas do banco de dados e guarda os
	 * valores presentes em uma lista de Object.
	 * 
	 * @param rs
	 * @return list
	 * @throws SQLException
	 */
	private List<Object> getCamposValores(ResultSet rs) throws SQLException {
		ArrayList<Object> list = new ArrayList<>();
		int columnCount = rs.getMetaData().getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			list.add(rs.getObject(i));

		}
		return list;
	}

	/***
	 * O metodo executa o comando select no banco de dados, o retorno da execucao e
	 * direcionado ao metodo getCamposValores() que retorna os dados, se a tabela
	 * possui ligacoes de muitos para muitos e chamado o metodo listarNparaN() que
	 * executa o comando select no banco para as outras tabelas, todas as
	 * informacoes sao armazenadas em uma lista de tabela, um try catch verifica o
	 * sucesso da execucao apresentando uma mensagem de aviso.
	 * 
	 * @param sqlSelect
	 * @param tabela
	 * @return listaDeTabela
	 */
	private <T extends Tabela<?>> ArrayList<T> select(String sqlSelect, T tabela) {

		ArrayList<T> lista = new ArrayList<>();
		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) this.con.prepareStatement(sqlSelect);

			// executa um select
			ResultSet rs = stmt.executeQuery();

			// itera no ResultSet

			while (rs.next()) {
				T auxTab = (T) tabela.getNovoObjeto();
				auxTab.setCamposTabela(this.getCamposValores(rs));

				ArrayList<TabelaNparaN> NparaN = tabela.getTabelasNparaN();
				if (NparaN != null) {
					List<List<Tabela<?>>> matrix = new ArrayList<>();

					for (TabelaNparaN aux : NparaN) {
						List<Tabela<?>> tabelas = listarNparaN(aux.getTabelaAux(), auxTab, aux.getTabelaMuitos());
						matrix.add(tabelas);
					}

					auxTab.setCamposTabelasNparaN(matrix);

				}

				lista.add(auxTab);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;

	}

	/***
	 * O metodo cria uma string contendo informacoes da tabela especifica para a
	 * execucao de um select simples no banco de dados e envia a tabela e a string
	 * para o metodo select() que ira executar o comando e retornar uma lista com
	 * dados.
	 * 
	 * @param <T>
	 * @param tabela
	 * @return list
	 */
	public <T extends Tabela<?>> ArrayList<T> listar(T tabela) {
		String sqlSelect = "select " + getCamposNomeSelect(tabela) + " from " + tabela.getNomeTabela();
		return this.select(sqlSelect, tabela);
	}

//	kaleb fazer
	public <T extends Tabela<?>> T getTabelByPk(T tabela) {
		String sqlSelect = "select " + getCamposNomeSelect(tabela) + " from " + tabela.getNomeTabela();
		return this.select(sqlSelect, tabela).get(0);
	}
	
	
	/***
	 * O metodo cria uma string contendo informacoes de tres tabelas especificas
	 * para a execucao de um select com relacao muitos para muitos no banco de dados
	 * e envia as tabelas e a string para o metodo select() que ira executar o
	 * comando e retornar uma lista com dados.
	 * 
	 * @param <T>
	 * @param tabelaAuxiliar
	 * @param tabelaUm
	 * @param tabelaMuitos
	 * @return list
	 */
	public <T extends Tabela<?>> ArrayList<T> listarNparaN(T tabelaAuxiliar, T tabelaUm, T tabelaMuitos) {
		// para que essa metodo funcione e importante que tando
		// q id da tabela tenha o mesmo nome quando ele for usando como chave
		// estranfeira
		String sqlSelect = "SELECT " + getCamposNomeSelect(tabelaMuitos) + " FROM " + tabelaAuxiliar.getNomeTabela()
				+ " INNER JOIN " + tabelaMuitos.getNomeTabela() + " ON " + tabelaAuxiliar.getNomeTabela() + "."
				+ tabelaMuitos.getNomePk() + " = " + tabelaMuitos.getNomeTabela() + "." + tabelaMuitos.getNomePk()
				+ " where " + tabelaUm.getNomePk() + "=" + tabelaUm.getPk();
		;
		System.out.println(sqlSelect);

		return this.select(sqlSelect, tabelaMuitos);

	}

	/***
	 * O metodo recebe uma tabela e a tabela da relacao, em seguida cria uma string
	 * para execucao de um insert no banco de dados com as informacoes das tabelas.
	 * 
	 * @param <T>
	 * @param tabelaDaRelacao
	 * @param tabela
	 */
	public <T extends Tabela<?>> void inserirNparaN(List<T> tabelaDaRelacao, TabelaNparaN tabela) {
//		INSERT INTO tabela (a,b,c) VALUES (1,2,3),(4,5,6);
		String sqlInsert = "INSERT INTO " + tabela.getTabelaAux().getNomeTabela() + " " + "("
				+ getCamposNomeSelect(tabela.getTabelaAux()) + ")" + " VALUES "
				+ prepararVariosCamposNparaN(tabelaDaRelacao);

		System.out.println(sqlInsert);
	}

	/***
	 * O metodo cria uma string contendo a preparacao de campos da tabela recebida.
	 * 
	 * @param <T>
	 * @param tabelaDaRelacao
	 * @return campos
	 */
	private <T extends Tabela<?>> String prepararVariosCamposNparaN(List<T> tabelaDaRelacao) {
		List<String> campos = new ArrayList();

		for (T tabela : tabelaDaRelacao) {
//			campos.add("("+tabelaUm.getPk()+","+tabela.getPk()+")");
			campos.add("(?, ?)");
		}

		return String.join(",", campos);
	}

	/***
	 * O metodo valida a pk da tabela e se for nulo retorna uma mensagem, se n�o for
	 * nulo ele cria uma string contendo informacoes para a execucao de um update no
	 * banco de dados. Em seguida executa o update, um try catch verifica o sucesso
	 * da execucao apresentando uma mensagem de aviso.
	 * 
	 * @param <T>
	 * @param tabela
	 * @return string
	 */
	public <T extends Tabela<?>> String atualizar(T tabela) {
		PreparedStatement stmt;
		if (tabela.getPk() == null) {
			System.out.println("Erro ao atualizar ID nulo");
			return "ID nulo";
		}

		String sql = "UPDATE  " + tabela.getNomeTabela() + " SET " + prepararNomeCampoAtualizar(tabela) + " WHERE "
				+ tabela.getNomePk() + "=" + tabela.getPk();
		try {
			stmt = (PreparedStatement) this.con.prepareStatement(sql);
			System.out.println(sql);
			// executa um select

			stmt.execute();
			System.out.println("Deu Bom , objeto update");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Deu Ruim , objeto update");
			e.printStackTrace();
		}
		return "";

	}

	/***
	 * O metodo utiliza a tabela recebida para criar uma string com os nomes de
	 * campos, se o numero de valores dos campos for diferente do numero de nomes
	 * dos campos � retornado uma mensagem de falha na preparacao.
	 * 
	 * @param <T>
	 * @param tabela
	 * @return formato
	 */

	public <T extends Tabela<?>> String prepararNomeCampoAtualizar(T tabela) {
		List<String> camposNome = tabela.getCamposNome();
		List<Object> camposValor = tabela.getCamposValor();
		int indice = 0;
		String formato = "";
		if (camposNome.size() != camposValor.size()) {
			return "os metodos getCamposNome() e tabela.getCamposValor() não foram preenchidos da maneira correta pois não contem o mesmo tamanho";
		}

		if (tabela.isPkSerial()) {
			indice = 1;
		}
//		(? ? ? ?)
//		setObjet()
		for (; indice < camposNome.size(); indice++) {
			formato += camposNome.get(indice) + "=" + "'" + camposValor.get(indice) + "'" + " ,";
		}

		formato = formato.substring(0, formato.length() - 1) + "";
		System.out.println(formato);
		return formato;
	}

	/***
	 * O metodo utiliza a lista recebida para criar uma string contendo os nomes dos
	 * campos de forma a ser utilizada para a execucao de um select no banco de
	 * dados.
	 * 
	 * @param <T>
	 * @param lista
	 * @return campos
	 */
	public <T extends Tabela<?>> String getCamposNomeSelect(T lista) {
		List<String> listcampos = lista.getCamposNome();

		String campos = new String();
		for (int i = 0; i < listcampos.size(); i++) {
			campos = campos.concat(listcampos.get(i) + ",");
		}
		campos = campos.substring(0, campos.length() - 1);
		return campos;
	}

	/***
	 * O metodo utiliza a tabela recebida para criar uma string contendo a pk e o
	 * nome da tabela e em seguida executar um delete no banco de dados, um try
	 * catch verifica o sucesso da execucao apresentando uma mensagem de aviso.
	 * 
	 * @param <T>
	 * @param tabela
	 * @return mensagem
	 */
	public <T extends Tabela<?>> Mensagem remover(T tabela) {
		String sqlDelet = "delete from " + tabela.getNomeTabela() + " where " + tabela.getNomePk() + " = "
				+ tabela.getPk();
		System.out.println(sqlDelet);
		try {
			PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement(sqlDelet);
			stmt.execute();

			return mensagem.getMensagemSucesso("112");

		} catch (SQLException e) {

			return mensagem.getMensagemErro("101", e.getMessage());
		}
	}

	/***
	 * O metodo utiliza a lista recebida para criar uma string contendo os nomes dos
	 * campos de forma a ser utilizada para a execucao de um select no banco de
	 * dados.
	 * 
	 * @param <T>
	 * @param tabela
	 * @return formato
	 */
	public <T extends Tabela<?>> String prepararCamposNome(T tabela) {
		List<String> camposNome = tabela.getCamposNome();

		if (tabela.isPkSerial()) {
			camposNome.remove(0);
		}

		String formato = "(";

		for (String campo : camposNome) {

			formato += campo + ",";
		}
		formato = formato.substring(0, formato.length() - 1) + ")";
		System.out.println(formato);
		return formato;
	}

	/***
	 * O metodo utiliza a lista recebida para criar uma string contendo os campos
	 * dos valores de forma a ser utilizada para a execucao de um select no banco de
	 * dados.
	 * 
	 * @param <T>
	 * @param tabela
	 * @return formato
	 */
	public <T extends Tabela<?>> String prepararCamposValor(T tabela) {
		List<Object> camposNome = tabela.getCamposValor();

		if (tabela.isPkSerial()) {
			camposNome.remove(0);
		}

		String formato = "(";

		for (Object campo : camposNome)
			formato += "" + "?" + "" + ",";

		formato = formato.substring(0, formato.length() - 1) + ")";
		System.out.println(formato);
		return formato;
	}

	/***
	 * O metodo utiliza a lista recebida para criar uma string contendo os nomes e
	 * valores dos campos, em seguida executa um insert no no banco de dados. O try
	 * catch verifica o sucesso da execucao e apresenta uma mensagem de acordo.
	 * 
	 * @param <T>
	 * @param tabela
	 */

	public <T extends Tabela<?>> void inserir(T tabela) {
		String sql = "insert into " + tabela.getNomeTabela() + " " + prepararCamposNome(tabela) + " values"
				+ prepararCamposValor(tabela);

		System.out.println(sql);

		try {
			// prepared statement para inserção
			PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement(sql);

			PrepararStatementCampoValor(stmt, tabela);

			// executa
			stmt.execute();

			System.out.println("Salvo Com Sucesso");

		} catch (SQLException e) {
			System.out.println("Deu Ruim");
			System.out.println(e.getMessage());

		}

	}

	/***
	 * Prepara um statement com a tabela e o statement recebidos.
	 * 
	 * @param <T>
	 * @param stmt
	 * @param tabela
	 */

	private <T extends Tabela<?>> void PrepararStatementCampoValor(PreparedStatement stmt, T tabela) {
		int index = 1;
		List<Object> composValor = tabela.getCamposValor();
		if (tabela.isPkSerial()) {
			composValor.remove(0);
		}

		for (Object iterable_element : composValor) {
			try {
				stmt.setObject(index, iterable_element);
				index++;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
