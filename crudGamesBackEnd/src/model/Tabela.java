package model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

public abstract class Tabela<TipoPK> {
	private TipoPK pk;
	private Date criacao;
	private Date modificacao;

	public Date getCriacao() {
		return criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	public Date getModificacao() {
		return modificacao;
	}

	public void setModificacao(Date modificacao) {
		this.modificacao = modificacao;
	}

	public Date dataHoje() {
		// default time zone
		ZoneId defaultZoneId = ZoneId.systemDefault();

		// creating the instance of LocalDate using the day, month, year info
		LocalDate localDate = LocalDate.now();

		// local date + atStartOfDay() + default time zone + toInstant() = Date
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		return date;
	}

	public TipoPK getPk() {
		return pk;
	}

	public void setPk(TipoPK pk) {
		this.pk = pk;
	}

	public abstract String getNomeTabela();

	public abstract void setCamposTabela(List<Object> list);

	public abstract Tabela getNovoObjeto();

	public abstract String getNomePk();

	public abstract List<Object> getCamposValor();

	public abstract List<String> getCamposNome();

	public abstract Boolean isPkSerial();

	public abstract ArrayList<TabelaNparaN> getTabelasNparaN();

	public  abstract void setCamposTabelasNparaN(List<List<Tabela<?>>> list);

}