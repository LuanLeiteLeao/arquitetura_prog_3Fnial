package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

import model.DAOGeneric;
import test.model.Plataformas;

public class Test {

	public static void main(String[] args) {
		Test t = new Test();
		Plataformas plataforma = new Plataformas();
		DAOGeneric dao = new DAOGeneric();

		t.inserirPlataforma(plataforma, dao);
//		t.listarPlataforma(plataforma, dao);
	}

	private void listarPlataforma(Plataformas plataforma, DAOGeneric dao) {
		ArrayList<Plataformas> list = dao.listar(plataforma);
		for (Plataformas plataformas : list) {
			System.out.println(plataformas.getPk() + "  " + plataformas.getNome() + "  " + plataformas.getModificacao()
					+ "  " + plataformas.getCriacao());
		}
	}

	private void inserirPlataforma(Plataformas plataforma, DAOGeneric dao) {
		plataforma.setNome("Xbox");
		plataforma.setCriacao(plataforma.dataHoje());
		plataforma.setModificacao(plataforma.dataHoje());

		dao.inserir(plataforma);
	}

}
