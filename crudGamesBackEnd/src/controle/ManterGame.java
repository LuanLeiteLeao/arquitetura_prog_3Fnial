package controle;

import java.util.ArrayList;

import model.Tabela;
import test.Pessoa;
import test.model.Games;

public class ManterGame extends ControleGeric<Pessoa, Integer> {

	@Override
	public Tabela getNovoObjeto() {
		
		return new Games();
	}

		
}
