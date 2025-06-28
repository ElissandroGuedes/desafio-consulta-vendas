package com.devsuperior.dsmeta.repositories;
import com.devsuperior.dsmeta.projections.ReportProjection;
import com.devsuperior.dsmeta.projections.SumaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s.seller.name AS sellerName, SUM(s.amount) AS total " +
            "FROM Sale s " +
            "WHERE s.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY s.seller.name")
    List<SumaryProjection>searchSumary(@Param("minDate")LocalDate minDate, @Param("maxDate") LocalDate maxDate);


    @Query("SELECT s.id AS id, s.date AS date, s.amount AS amount, s.seller.name AS sellerName " +
            "FROM Sale s " +
            "WHERE s.date BETWEEN :minDate AND :maxDate " +
            "AND (:name IS NULL OR :name = '' OR LOWER(s.seller.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    Page<ReportProjection> searchReport(@Param("minDate")LocalDate minDate, @Param("maxDate") LocalDate maxDate, @Param("name") String name, Pageable pageable);

}

