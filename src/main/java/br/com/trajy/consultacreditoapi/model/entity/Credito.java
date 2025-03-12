package br.com.trajy.consultacreditoapi.model.entity;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import br.com.trajy.architecture.layer.data.struct.common.Identity;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Credito extends Identity<Long> {

    @Column(length = 50, nullable = false)
    private String numeroCredito;

    @Column(length = 50, nullable = false)
    private String numeroNfse;

    @Column(nullable = false)
    private LocalDate dataConstituicao;

    @PositiveOrZero
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal valorIssqn;

    @Size(max = 50, message = "O campo tipo Credito pode conter no maximo {max} caracteres")
    private String tipoCredito;

    @Column(nullable = false)
    private boolean simplesNacional;

    @PositiveOrZero
    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal aliquota;

    @PositiveOrZero
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal valorFaturado;

    @PositiveOrZero
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal valorDeducao;

    @PositiveOrZero
    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal baseCalculo;

    @Override
    public boolean equals(Object that) {
        return reflectionEquals(this, that);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this, JSON_STYLE);
    }

}

