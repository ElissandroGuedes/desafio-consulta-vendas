package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SumaryProjection;

public class SumaryDTO {

    private String sellerName;
    private double total;

    public SumaryDTO() {
    }

    public SumaryDTO(String sellerName, double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SumaryDTO(SumaryProjection projection) {
        sellerName = projection.getSellerName();
        total = projection.getTotal();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
