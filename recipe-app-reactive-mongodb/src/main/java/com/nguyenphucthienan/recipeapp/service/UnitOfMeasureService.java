package com.nguyenphucthienan.recipeapp.service;

import com.nguyenphucthienan.recipeapp.command.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

public interface UnitOfMeasureService {

    Flux<UnitOfMeasureCommand> getAllUnitOfMeasures();
}
