package com.francisco.modelo;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

//JAX-B
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Pedido implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private Calendar dataPedido;
	private Calendar dataPagamento;
	private BigDecimal valorTotal;
	
	@XmlElementWrapper(name="itens")
	@XmlElement(name="item")
	private final Set<Item> itens = new LinkedHashSet<>();
	
	
	Pedido() {
	}

	public Pedido(Integer codigo, Calendar dataPedido, Calendar dataPagamento, BigDecimal valorTotal) {
		this.codigo = codigo;
		this.dataPedido = dataPedido;
		this.dataPagamento = dataPagamento;
		this.valorTotal = valorTotal;
	}

	public void adicionaItem(Item item) {
		this.itens.add(item);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public Calendar getDataPedido() {
		return dataPedido;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public Set<Item> getItens() {
		return Collections.unmodifiableSet(this.itens);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pedido pedido = (Pedido) o;
		return Objects.equals(codigo, pedido.codigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", dataPedido=" + dataPedido + ", dataPagamento=" + dataPagamento
				+ ", valorTotal=" + valorTotal + ", itens=" + itens + "]";
	}
}
