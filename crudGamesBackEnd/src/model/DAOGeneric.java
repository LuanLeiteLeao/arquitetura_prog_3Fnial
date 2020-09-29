package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import mensagem.Mensagem;
import mensagem.PersistenciaMensagem;

public class DAOGeneric extends DAOConexao {
	private PersistenciaMensagem mensagem = new PersistenciaMensagem();

//	ao invez de herdar fazer referencia ao objeto
	public DAOGeneric() {
		super();

	}

	private List<Object> getCamposValores(ResultSet rs) throws SQLException {
		ArrayList<Object> list = new ArrayList<>();
		int columnCount = rs.getMetaData().getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			list.add(rs.getObject(i));
			
		}
		return list;
	}

	private <T extends Tabela<?>> ArrayList<T> select(String sqlSelect,T tabela) {
		
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
				if(NparaN!=null) {
					List<List<Tabela<?>>> matrix = new ArrayList<>();
					
					for (TabelaNparaN aux : NparaN) {
						 List<Tabela<?>> tabelas = listarNparaN( aux.getTabelaAux(), auxTab, aux.getTabelaMuitos());	
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

	public <T extends Tabela<?>> ArrayList<T> listar(T tabela) {
		String sqlSelect = "select "+getCamposNomeSelect(tabela) +" from " + tabela.getNomeTabela();
		return this.select(sqlSelect, tabela);
	}

	public <T extends Tabela<?>> ArrayList<T> listarNparaN(T tabelaAuxiliar, T tabelaUm, T tabelaMuitos) {
		//para que essa metodo funcione e importante que tando 
		//q id da tabela tenha o mesmo nome quando ele for usando como chave estranfeira
		String sqlSelect = "SELECT "+getCamposNomeSelect(tabelaMuitos) + " FROM " + tabelaAuxiliar.getNomeTabela() + " INNER JOIN "
				+ tabelaMuitos.getNomeTabela() + " ON " + tabelaAuxiliar.getNomeTabela() + "." + tabelaMuitos.getNomePk() + " = "
				+ tabelaMuitos.getNomeTabela() + "." + tabelaMuitos.getNomePk()+ " where " +tabelaUm.getNomePk()+"="+tabelaUm.getPk();;
		System.out.println(sqlSelect);
		
		
		return this.select(sqlSelect, tabelaMuitos);

	}
	
	public <T extends Tabela<?>> void   inserirNparaN(List<T> tabelaDaRelacao,TabelaNparaN tabela) {
//		INSERT INTO tabela (a,b,c) VALUES (1,2,3),(4,5,6);
	String sqlInsert = "INSERT INTO "
			+ tabela.getTabelaAux().getNomeTabela()+" "
			+"("+ getCamposNomeSelect(tabela.getTabelaAux())+")"
			+ " VALUES "
			+ prepararVariosCamposNparaN(tabelaDaRelacao);
	
		System.out.println(sqlInsert);
	}
	

	private <T extends Tabela<?>>  String prepararVariosCamposNparaN(List<T> tabelaDaRelacao) {
		List<String> campos = new ArrayList();
		
		for (T tabela : tabelaDaRelacao) {
//			campos.add("("+tabelaUm.getPk()+","+tabela.getPk()+")");
			campos.add("(?, ?)");
		}
		
		return String.join(",", campos);
	}

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

	public <T extends Tabela<?>> String getCamposNomeSelect(T lista) {
		List<String> listcampos = lista.getCamposNome();
		
		String campos = new String();
		for (int i=0;i<listcampos.size(); i++) {
			campos=campos.concat(listcampos.get(i)+",");
		}
		campos=campos.substring(0,campos.length()-1);
		return campos;
	}
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
