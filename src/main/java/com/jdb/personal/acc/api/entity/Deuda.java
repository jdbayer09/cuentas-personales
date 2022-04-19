package com.jdb.personal.acc.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Column(name = "CREATED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    //@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date created;

    @Column(name = "OCULTO", nullable = false)
    private Boolean oculto = false;

    @OneToMany(mappedBy = "deuda", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Pago> pagos;

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getOculto() {
        return oculto;
    }

    public void setOculto(Boolean oculto) {
        this.oculto = oculto;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    @PrePersist
    private void prePersist() {
        this.created = new Date();
    }

    /*Variables Visuales */

    public boolean getPagada() {
        for (Pago p: this.pagos) {
            if (!p.getPagado())
                return false;
        }
        return true;
    }

    public Double getDebe() {
        double debe = 0.0;

        for (Pago p: this.pagos) {
            if (!p.getPagado())
                debe += p.getValor();
        }

        return debe;
    }

    public Double getPago() {
        double pago = 0.0;

        for (Pago p: this.pagos) {
            if (p.getPagado())
                pago += p.getValor();
        }

        return pago;
    }

    public Integer getCuotasPagas() {
        int cuotas = 0;

        for (Pago p: this.pagos) {
            if (p.getPagado())
                cuotas ++;
        }

        return cuotas;
    }
}