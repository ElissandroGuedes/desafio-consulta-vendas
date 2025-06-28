package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.ReportProjection;

import java.time.LocalDate;

public class ReportDTO {
    private long id;
    private LocalDate date;
    private double amount;
    private String sellerName;


    public ReportDTO() {
    }

    public ReportDTO(long id, LocalDate date, double amount,String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public ReportDTO(ReportProjection projection) {
        id = projection.getId();
        date = projection.getDate();
        amount = projection.getAmount();
        sellerName = projection.getSellerName();
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
