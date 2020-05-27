package com.nguyenphucthienan.springrestdocs.web.mapper;

import com.nguyenphucthienan.springrestdocs.domain.Beer;
import com.nguyenphucthienan.springrestdocs.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto dto);
}
