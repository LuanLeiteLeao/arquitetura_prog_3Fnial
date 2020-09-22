package controle;

import java.util.ArrayList;

import model.Tabela;
import test.Pessoa;

public class MaterGame extends ControleGeric<Pessoa, Integer> {

	@Override
	public Tabela getNovoObjeto() {
		
		return new Pessoa();
	}

		
}
