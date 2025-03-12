package br.com.trajy.consultacreditoapi.assembly;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import br.com.trajy.architecture.layer.assembly.AssemblyMapperAbstract;
import br.com.trajy.consultacreditoapi.model.dto.CreditoDTO;
import br.com.trajy.consultacreditoapi.model.entity.Credito;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = SPRING)
public abstract class CreditoAssembly extends AssemblyMapperAbstract<CreditoDTO, Credito> {

    @Override
    @Mapping(target = "id", ignore = true)
    public abstract CreditoDTO toResource(Credito credito);

}
