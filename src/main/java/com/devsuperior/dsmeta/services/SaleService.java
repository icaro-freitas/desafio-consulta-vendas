package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SaleSummaryDTO> getSummary(String minDate, String maxDate) {
		LocalDate finalDate;

		if (!maxDate.equals("")) {
			finalDate = LocalDate.parse(maxDate);
		} else {
			finalDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}

		LocalDate initialDate;

		if (!minDate.equals("")) {
			initialDate = LocalDate.parse(minDate);
		} else {
			initialDate = finalDate.minusYears(1L);
		}

		return repository.getSummary(initialDate, finalDate);
	}

	public Page<SaleReportDTO> getReport(Pageable pageable, String minDate, String maxDate, String name) {
		LocalDate finalDate;

		if (!maxDate.equals("")) {
			finalDate = LocalDate.parse(maxDate);
		} else {
			finalDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}

		LocalDate initialDate;

		if (!minDate.equals("")) {
			initialDate = LocalDate.parse(minDate);
		} else {
			initialDate = finalDate.minusYears(1L);
		}

		return repository.getReport(pageable, initialDate, finalDate, name);
	}
}
