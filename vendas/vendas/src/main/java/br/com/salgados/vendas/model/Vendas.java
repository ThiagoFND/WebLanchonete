package br.com.salgados.vendas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendas")
public class Vendas {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "dataPreenchimento")
	private String data;

	@Column(name = "dinheiro", nullable = false)
	private String dinheiro;

	@Column(name = "pix", nullable = false)
	private String pix;

	@Column(name = "cartao", nullable = false)
	private String cartao;

	public Vendas() {
		super();
	}

	public Vendas(String id, String data, String dinheiro, String pix, String cartao) {
		super();
		this.id = id;
		this.data = data;
		this.dinheiro = dinheiro;
		this.pix = pix;
		this.cartao = cartao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(String dinheiro) {
		this.dinheiro = dinheiro;
	}

	public String getPix() {
		return pix;
	}

	public void setPix(String pix) {
		this.pix = pix;
	}

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartao == null) ? 0 : cartao.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((dinheiro == null) ? 0 : dinheiro.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pix == null) ? 0 : pix.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendas other = (Vendas) obj;
		if (cartao == null) {
			if (other.cartao != null)
				return false;
		} else if (!cartao.equals(other.cartao))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (dinheiro == null) {
			if (other.dinheiro != null)
				return false;
		} else if (!dinheiro.equals(other.dinheiro))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pix == null) {
			if (other.pix != null)
				return false;
		} else if (!pix.equals(other.pix))
			return false;
		return true;
	}

}
