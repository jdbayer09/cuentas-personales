package com.jdb.personal.acc.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_DEUDA", nullable = false)
    @JsonBackReference
    private Deuda deuda;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @Column(name = "INTERES")
    private Double interes;

    @Column(name = "MES_PAGO", nullable = false)
    private Integer mesPago;

    @Column(name = "ANO_PAGO", nullable = false)
    private Integer anoPago;

    @Column(name = "PAGADO", nullable = false)
    private Boolean pagado = false;

    @Column(name = "FECHA_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Deuda getDeuda() {
        return deuda;
    }

    public void setDeuda(Deuda idDeuda) {
        this.deuda = idDeuda;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getInteres() {
        return interes;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    public Integer getMesPago() {
        return mesPago;
    }

    public void setMesPago(Integer mesPago) {
        this.mesPago = mesPago;
    }

    public Integer getAnoPago() {
        return anoPago;
    }

    public void setAnoPago(Integer anoPago) {
        this.anoPago = anoPago;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

}