package org.matusikl.mapperinterface;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.matusikl.dto.laptopdto.LaptopGetDto;
import org.matusikl.dto.laptopdto.LaptopPostDto;
import org.matusikl.model.Laptop;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LaptopIMapper {

    LaptopIMapper INSTANCE = Mappers.getMapper( LaptopIMapper.class );

    LaptopGetDto laptopToLaptopGetDto(Laptop laptop);

    Laptop laptopPostDtoToLaptop(LaptopPostDto laptopPostDto);

    List<LaptopGetDto> listLaptopToListLaptopGetDto(List<Laptop> laptopList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLaptopFromLaptopPostDto(LaptopPostDto laptopPostDto, @MappingTarget Laptop laptop);

}
