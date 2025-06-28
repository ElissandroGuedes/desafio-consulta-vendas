package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.dto.SumaryDTO;
import com.devsuperior.dsmeta.projections.ReportProjection;
import com.devsuperior.dsmeta.projections.SumaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
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

	public List<SumaryDTO> searchSumary(String minDateStr, String maxDateStr) {
		LocalDate maxDate = (maxDateStr == null || maxDateStr.isEmpty()) ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(maxDateStr);
		LocalDate minDate = (minDateStr == null || minDateStr.isEmpty()) ? maxDate.minusYears(1L): LocalDate.parse(minDateStr);
		List<SumaryProjection> result = repository.searchSumary(minDate, maxDate);
		return result.stream().map(x -> new SumaryDTO(x)).collect(Collectors.toList());
	}

	public Page<ReportDTO> searchReport(String minDateStr, String maxDateStr, String name, Pageable pageable) {
		LocalDate maxDate = (maxDateStr == null || maxDateStr.isEmpty()) ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(maxDateStr);
		LocalDate minDate = (minDateStr == null || minDateStr.isEmpty()) ? maxDate.minusYears(1L): LocalDate.parse(minDateStr);
		Page<ReportProjection> result = repository.searchReport(minDate, maxDate, name, pageable);
		return result.map(x -> new ReportDTO(x));
	}



}
