package view;

import java.util.Hashtable;

import controle.ICrudControler;
import controle.ManterGame;

public class Views extends ViewGerenic {

	@Override
	protected Hashtable<String, ICrudControler> casoDeUsoValido() {
		Hashtable<String, ICrudControler> modelos=new Hashtable();
		modelos.put("ManterGame",new ManterGame());
		
		return modelos;
	}

}
