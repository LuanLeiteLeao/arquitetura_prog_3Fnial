package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

/***
 * Esta classe generica e abstrata possui metodos e atributos que devem estar
 * presentes em outras classes de tabelas.
 * 
 * @author User
 *
 * @param <TipoPK>
 */
public abstract class Tabela<TipoPK> {
	private TipoPK pk;
	private Date criacao;
	private Date modificacao;

	/***
	 * Retorna o atributo criacao do tipo Date.
	 * 
	 * @return criacao
	 */
	public Date getCriacao() {
		return criacao;
	}

	/***
	 * Define valor do atributo criacao.
	 * 
	 * @param simpleDateFormat
	 */
	public void setCriacao(Date simpleDateFormat) {
		this.criacao = simpleDateFormat;
	}

	/***
	 * Retorna o atributo modificacao do tipo Date.
	 * 
	 * @return modificacao
	 */
	public Date getModificacao() {
		return modificacao;
	}

	/***
	 * Dita qual novo valor o atributo modificacao ira armazenar.
	 * 
	 * @param criacao
	 */
	public void setModificacao(Date modificacao) {
		this.modificacao = modificacao;
	}

	/***
	 * Descobre a data local e armazena em uma variavel do tipo Date.
	 * 
	 * @return date
	 */
	public Date dataHoje() {
		// default time zone
		ZoneId defaultZoneId = ZoneId.systemDefault();

		// creating the instance of LocalDate using the day, month, year info
		LocalDate localDate = LocalDate.now();

		// local date + atStartOfDay() + default time zone + toInstant() = Date
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		return date;
	}

	/***
	 * Retorna o atributo pk do TipoPK.
	 * 
	 * @return pk
	 */
	public TipoPK getPk() {
		return pk;
	}

	/***
	 * Define valor do atributo pk.
	 * 
	 * @param pk
	 */
	public void setPk(TipoPK pk) {
		this.pk = pk;
	}

	/***
	 * Retorna o nome da tabela.
	 * 
	 * @return String
	 */
	public abstract String getNomeTabela();

	/***
	 * Define valores dos campos da tabela.
	 * 
	 * @param list
	 */
	public abstract void setCamposTabela(List<Object> list);

	/***
	 * Retorna um novo objeto do tipo tabela.
	 * 
	 * @return tabela
	 */
	public abstract Tabela getNovoObjeto();

	/***
	 * Retorna o nome do atributo pk;
	 * 
	 * @return string
	 */
	public abstract String getNomePk();

	/***
	 * Retorna os valores dos campos a tabela.
	 * 
	 * @return list
	 */
	public abstract List<Object> getCamposValor();

	/***
	 * Retorna os nomes dos campos a tabela.
	 * 
	 * @return list
	 */
	public abstract List<String> getCamposNome();

	/***
	 * Retorna se pk e serial.
	 * 
	 * @return boolean
	 */
	public abstract Boolean isPkSerial();

	/***
	 * Retorna as relacoes muitos para muitos da tabela.
	 * 
	 * @return list
	 */
	public abstract ArrayList<TabelaNparaN> getTabelasNparaN();

	/***
	 * Define valores para os campos das relacoes muitos para muitos da tabela.
	 * 
	 * @param list
	 */

	public abstract void setCamposTabelasNparaN(List<List<Tabela<?>>> list);

}