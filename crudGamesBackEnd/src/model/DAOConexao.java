package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import util.Configuracoes;

//teste kaleb
//test Luan
public class DAOConexao {

	protected Connection con;
	protected static DAOConexao dao;

	public static DAOConexao getIntancia() {
		if (dao == null) {
			dao = new DAOConexao();
		}
		return dao;
	}

	protected DAOConexao() throws RuntimeException {
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