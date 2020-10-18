package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;

import model.Tabela;
import model.TabelaNparaN;
import persistencia.DAO;
import test.model.Games;
import test.model.GamesHasPlataformas;
import test.model.Plataformas;

public class Test {

	public static void main(String[] args) {
		Games game = new Games();
		GamesHasPlataformas gamePlataforma = new GamesHasPlataformas();
		Plataformas plataforma = new Plataformas();
		DAO dao = new DAO();

//		t.inserirPlataforma(plataforma, dao);
//		t.listarPlataforma(plataforma, dao);

//Listar games has paltaforma		
// ArrayList<GamesHasPlataformas> list = dao.listar(new GamesHasPlataformas());
// for (GamesHasPlataformas gamesHasPlataformas : list) {
//	System.out.println(gamesHasPlataformas.getGamesId()+" , "+gamesHasPlataformas.getPlataformasId());
//}

//		 Games g = new Games();
//		 g.setPk(2);
//		
//		ArrayList<Tabela<Integer>> lista = dao.listarNparaN(new GamesHasPlataformas(), g,new Plataformas());
//		
//		for (Tabela<Integer> tabela : lista) {
//			System.out.println(tabela.toString());
//		}

//		ArrayList<Games> lista = dao.listar(new Games());
//		
//		for (Games tabela : lista) {
//			System.out.println(tabela.getPk()+"\n"+tabela.getPlataformas()+"\n"+tabela.getGeneros());
//		}
//		
		game.setPk(1);
		plataforma.setPk(1);

		List<List<Tabela<?>>> matrix = new ArrayList<>();
		List<Tabela<?>> npn = (List<Tabela<?>>) new TabelaNparaN(game,gamePlataforma,plataforma);
		matrix.add(npn);
		game.setCamposTabelasNparaN(matrix);
		
		Plataformas p = new Plataformas();
		p.setPk(4);
		p.setNome("Xbox");
		p.setCriacao(p.dataHoje());
		p.setModificacao(p.dataHoje());

		dao.inserir(p);

	}

	private void listarPlataforma(Plataformas plataforma, DAO dao) {
		ArrayList<Plataformas> list = dao.listar(plataforma);
		for (Plataformas plataformas : list) {
			System.out.println(plataformas.getPk() + "  " + plataformas.getNome() + "  " + plataformas.getModificacao()
					+ "  " + plataformas.getCriacao());
		}
	}

	private void inserirPlataforma(Plataformas plataforma, DAO dao) {
		plataforma.setNome("Xbox");
		plataforma.setCriacao(plataforma.dataHoje());
		plataforma.setModificacao(plataforma.dataHoje());
		dao.inserir(plataforma);
	}

}