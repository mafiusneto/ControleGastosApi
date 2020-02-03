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
	
	List<Compra> findByDataVencimentoBetween(LocalDate DataVencimento1, LocalDate DataVencimento2);	 
}
