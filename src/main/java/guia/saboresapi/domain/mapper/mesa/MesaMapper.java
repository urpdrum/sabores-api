package guia.saboresapi.domain.mapper.mesa;


import guia.saboresapi.domain.entity.Mesa;
import guia.saboresapi.domain.input.mesa.AtualizarMesaRequest;
import guia.saboresapi.domain.input.mesa.CadastrarMesaRequest;
import guia.saboresapi.domain.output.mesa.MesaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MesaMapper {
  MesaMapper INSTANCE = Mappers.getMapper(MesaMapper.class);

  @Mapping(target = "mesaId", ignore = true)
  @Mapping(target = "restaurante.restauranteId", source = "restauranteId")
  Mesa toMesa(CadastrarMesaRequest cadastrarMesaRequest);

  @Mapping(target = "mesaId", ignore = true)
  Mesa toMesa(AtualizarMesaRequest atualizarMesaRequest);

  @Mapping(source = "mesaId", target = "id")
  @Mapping(source = "quantidadeAssentos", target = "capacidade")
  @Mapping(target = "restauranteId", source = "restaurante.restauranteId")
  MesaResponse toMesaResponse(Mesa mesa);
}