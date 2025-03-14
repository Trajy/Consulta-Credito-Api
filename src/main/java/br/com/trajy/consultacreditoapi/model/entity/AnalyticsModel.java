package br.com.trajy.consultacreditoapi.model.entity;

import br.com.trajy.architecture.layer.data.struct.common.Identity;
import br.com.trajy.architecture.layer.data.struct.model.AuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnalyticsModel extends AuditableEntity<Long> {

    private String searchValue;

    private Boolean hasResult;

    private LocalDateTime searchAt;

}
