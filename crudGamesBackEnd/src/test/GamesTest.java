package test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.DAOGeneric;
import test.model.Games;
import test.model.Generos;
import test.model.Plataformas;

public class GamesTest {
	public static void main(String[] args) {
		Games g = new Games();
		DAOGeneric dao = new DAOGeneric();
			
		List<Plataformas> pla = new ArrayList<>();
		Plataformas aux = new Plataformas();
		aux.setPk(1);
		Plataformas aux2 = new Plataformas();
		aux2.setPk(2);
		pla.add(aux);
		pla.add(aux2);
	
		g.setNome("GTA IV");
		g.setDescricao("Jogao da por@");
		g.setDataLancamento(g.dataHoje());
		g.setIsJogoDoAno(true);
		g.setCriacao(g.dataHoje());
		g.setModificacao(g.dataHoje());
		g.setPlataformas(pla);
		dao.inserir(g);
		
	}
}
