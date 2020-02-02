package com.rneto.controlegastosapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.OneToMany; // @OneToMany(mappedBy = "grupo")
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "grupos")
@Data
public class Grupo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Size(min =3, max = 50)
	private String nome;
	
	//@OneToMany(mappedBy="grupo")	public Set<Compra> compra; 
}
