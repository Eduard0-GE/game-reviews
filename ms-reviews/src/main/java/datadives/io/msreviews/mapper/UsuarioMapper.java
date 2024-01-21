package datadives.io.msreviews.mapper;

import datadives.io.msreviews.model.Usuario;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "Spring")
public interface UsuarioMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario updateUsuarioFromUsuario(Usuario dto, @MappingTarget Usuario user);
}
