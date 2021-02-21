/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopclass.breadapp.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="services")
public class Service1 {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    
    private long Id;
    
    private long CustomerId;
    
    private String Category;
    
    private String status;
    
    private Integer EstimatedHours;
    
    private Integer SpentHours;
    
    private LocalDate NextServiceDate;
   
    private LocalDate created; 
     
    private LocalDate updated;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(long CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEstimatedHours() {
        return EstimatedHours;
    }

    public void setEstimatedHours(Integer EstimatedHours) {
        this.EstimatedHours = EstimatedHours;
    }

    public Integer getSpentHours() {
        return SpentHours;
    }

    public void setSpentHours(Integer SpentHours) {
        this.SpentHours = SpentHours;
    }

    public LocalDate getNextServiceDate() {
        return NextServiceDate;
    }

    public void setNextServiceDate(LocalDate NextServiceDate) {
        this.NextServiceDate = NextServiceDate;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }
     


    @Override
    public String toString() {
        return "Customer [id=" + Id + ", category=" + Category + ", serviceStatus=" + status + ", estimatedHours=" + EstimatedHours + ", spentHours="
                + SpentHours + ", nextServiceDate=" + NextServiceDate + ", created=" + created + ", updated=" + updated + "]";
    }
    
}
