package dm.bl.creditservice.mapper;

import dm.bl.creditservice.entity.CreditApplicationEntity;

import dm.bl.model.CreditApplicationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreditApplicationMapper {

    @Mappings({
            @Mapping(target = "id", expression = "java(entity.getId().toString())"),
            @Mapping(target = "termInMonths", source = "term"),
            @Mapping(target = "status", expression = "java(entity.getStatus().name())"),
            @Mapping(target = "createdAt", expression = "java(toOffsetDateTime(entity.getCreationAt()))")
    })
    CreditApplicationResponse toCreditApplicationResponse(CreditApplicationEntity entity);

    default OffsetDateTime toOffsetDateTime(LocalDate localDate) {
        return localDate != null ? localDate.atStartOfDay().atOffset(ZoneOffset.UTC) : null;
    }
}