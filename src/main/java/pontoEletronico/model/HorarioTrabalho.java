package pontoEletronico.model;

import javax.persistence.Entity;

import com.google.protobuf.TextFormat.ParseException;

import javax.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class HorarioTrabalho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIME)
	private Date inicio;

	@Temporal(TemporalType.TIME)
	private Date fim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public HorarioTrabalho(Date inicio, Date fim) {
		this.inicio = inicio;
		this.fim = fim;
	}

	public HorarioTrabalho() {
	}

}
