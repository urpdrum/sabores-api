package guia.saboresapi.domain.mapper.endereco;


import guia.saboresapi.domain.entity.Endereco;
import guia.saboresapi.domain.output.endereco.EnderecoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnderecoResponseMapper {

    EnderecoResponseMapper INSTANCE = Mappers.getMapper(EnderecoResponseMapper.class);

    @Mapping(target = "cidade", source = "localidade")
    Endereco toEndereco(EnderecoResponse enderecoResponse);

}
