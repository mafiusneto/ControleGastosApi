package com.rneto.controlegastosapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "compras")
@Data
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Column(name="data_compra")
	private LocalDate dataCompra; 

	@NotNull
	@Column(name="data_vencimento")
	private LocalDate dataVencimento;

	@NotNull
	@Size(min=3, max=50)
	private String descricao;

	@Size(max=50)
	private String observacao;

	@NotNull
	private BigDecimal valor;

	@NotNull
	@Column(name="parcela_atual")
	private Integer parcelaAtual;

	@NotNull
	@Column(name="parcela_total")
	private Integer parcelaTotal;

	@Column(name="valor_pago")
	private BigDecimal valorPago;
	
	@Column(name="data_pagamento")
	private LocalDate dataPagamento;
	
	@NotNull
	@ManyToOne @JoinColumn(name="codigo_grupo")
	private Grupo grupo;
	 
}
