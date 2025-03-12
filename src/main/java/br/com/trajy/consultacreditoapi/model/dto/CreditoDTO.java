package br.com.trajy.consultacreditoapi.model.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

import br.com.trajy.architecture.layer.data.struct.common.Identity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(NON_NULL)
@Getter
@Setter
public class CreditoDTO extends Identity<Long> {

    private String numeroCredito;

    private String numeroNfse;

    private LocalDate dataConstituicao;

    @PositiveOrZero
    private BigDecimal valorIssqn;

    private String tipoCredito;

    private boolean simplesNacional;

    @PositiveOrZero
    private BigDecimal aliquota;

    @PositiveOrZero
    private BigDecimal valorFaturado;

    @PositiveOrZero
    private BigDecimal valorDeducao;

    @PositiveOrZero
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
