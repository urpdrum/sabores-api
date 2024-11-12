package guia.saboresapi.domain.mapper.avaliacao;


import guia.saboresapi.domain.entity.Avaliacao;
import guia.saboresapi.domain.input.avaliacao.AtualizarAvaliacaoRequest;
import guia.saboresapi.domain.input.avaliacao.CadastrarAvaliacaoRequest;
import guia.saboresapi.domain.output.avaliacao.AvaliacaoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {

    AvaliacaoMapper INSTANCE = Mappers.getMapper(AvaliacaoMapper.class);

    /**
     * @param avaliacaoRequest
     * @return
     */
    @Mapping(target = "avaliacaoId", ignore = true)
    @Mapping(target = "restaurante", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Avaliacao toAvaliacao(CadastrarAvaliacaoRequest avaliacaoRequest);

    /**
     * @param avaliacaoRequest
     * @return
     */
    @Mapping(target = "avaliacaoId", ignore = true)
    @Mapping(target = "restaurante", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Avaliacao toAvaliacao(AtualizarAvaliacaoRequest avaliacaoRequest);

    /**
     * @param avaliacao
     * @return
     */
    @Mapping(target = "restauranteId", source = "restaurante.restauranteId")
    @Mapping(target = "usuarioId", source = "usuario.usuarioId")
    AvaliacaoResponse toAvaliacaoResponse(Avaliacao avaliacao);
}
