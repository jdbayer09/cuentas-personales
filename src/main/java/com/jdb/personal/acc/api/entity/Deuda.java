package com.jdb.personal.acc.api.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deudas")
public class Deuda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @Column(name = "CUOTAS", nullable = false)
    private Integer cuotas;

    @Column(name = "INTERES")
    private Float interes;

    @Column(name = "CREATED", nullable = false)
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public Float getInteres() {
        return interes;
    }

    public void setInteres(Float interes) {
        this.interes = interes;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


    @PrePersist
    private void prePersist() {
        this.created = new Date();
    }

    /*Variables Visuales */

    public boolean getPagada() {
        return false;
    }

    public Double getDebe() {
        return 0.0;
    }

    public Double getPago() {
        return 0.0;
    }

    public Integer getCuotasPagas() {
        return 0;
    }
}