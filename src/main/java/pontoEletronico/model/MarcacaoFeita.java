package pontoEletronico.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MarcacaoFeita {

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
	
	public MarcacaoFeita(Date entrada, Date saida) {
        this.inicio = entrada;
        this.fim = saida;
    }
	
	public MarcacaoFeita() {
        
    }
	
	

}
