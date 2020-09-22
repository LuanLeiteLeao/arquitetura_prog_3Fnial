package view;

import java.util.Hashtable;

import controle.ICrudControler;
import controle.MaterGame;

public class Views extends ViewGerenic {

	@Override
	protected Hashtable<String, ICrudControler> casoDeUsoValido() {
		Hashtable<String, ICrudControler> modelos=new Hashtable();
		modelos.put("pessoa",new MaterGame());
		
		return modelos;
	}

}
