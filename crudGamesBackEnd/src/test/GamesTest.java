package test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import persistencia.DAO;
import test.model.Games;
import test.model.Generos;
import test.model.Plataformas;

public class GamesTest {
	public static void main(String[] args) {
		Games g = new Games();
		DAO dao = new DAO();
			
		List<Plataformas> pla = new ArrayList<>();
		Plataformas aux = new Plataformas();
		
		g=dao.getTabelByPk(1,g);
		System.out.println(g.getCamposValor());
		
	}
}