package pontoEletronico.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Atraso implements Serializable {
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	private Date data;
	@Temporal(TemporalType.TIME)
	private Date horaEntrada;
	@Temporal(TemporalType.TIME)
	private Date horaSaida;

	private int atrasoMinutos;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	public int getAtrasoMinutos() {
		return atrasoMinutos;
	}

	public void setAtrasoMinutos(int atrasoMinutos) {
		this.atrasoMinutos = atrasoMinutos;
	}

	public Atraso() {
		this.data = new Date();
	}

	public Atraso(Date horaEntrada, Date horaSaida, int atrasoMinutos) {
		this();
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
		this.atrasoMinutos = atrasoMinutos;
	}

}
