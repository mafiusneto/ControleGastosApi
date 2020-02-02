package com.rneto.controlegastosapi.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rneto.controlegastosapi.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{
	
	@Query(value = "SELECT c FROM compras c WHERE c.descricao=?1")
	public List<Compra> FindByDescricao(String desc);
	
	/*	 
	@Query(value="SELECT c FROM compras c WHERE c.descricao = :desc ")
	List<Compra> FindByDescricao(@Param("desc")String descricao);
	
	List<Compra> FindByPeriodVenc(LocalDate data1, LocalDate data2);
	/*
	@Query(value="SELECT c FROM compras c WHERE c.descricao = :desc ")
	List<Compra> FindByDescricao(@Param("desc") String desc);
	
	/*
	@Query("SELECT c FROM compras c WHERE c.descricao = ?1 ")
	public List<Compra> FindByDescricao(String desc);
	/*
	@Query("FROM compras as compra WHERE compra.data_vencimento BETWEEN ?1 AND ?2")
	List<Compra> FindByPeriodVenc(LocalDate data1, LocalDate data2);

	/*
	@Query("FROM compras as compra WHERE compra.data_vencimento BETWEEN :from AND :to")
	List<Compra> FindByPeriodVenc(
			@Param("from") Date startDay,
		    @Param("to") Date endDay
	);
	*/
	 
}
