package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(seller.name, SUM(sale.amount)) "
	        + "FROM Seller seller JOIN seller.sales sale "
	        + "WHERE sale.date BETWEEN :minDate AND :maxDate "
	        + "GROUP BY seller.name")
	List<SaleSummaryDTO> getSummary(LocalDate minDate, LocalDate maxDate);

}
