package com.nguyenphucthienan.recipeapp.service;

import com.nguyenphucthienan.recipeapp.command.UnitOfMeasureCommand;
import com.nguyenphucthienan.recipeapp.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.nguyenphucthienan.recipeapp.repository.reactive.UnitOfMeasureReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureReactiveRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureReactiveRepository unitOfMeasureRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Flux<UnitOfMeasureCommand> getAllUnitOfMeasures() {
        return unitOfMeasureRepository
                .findAll()
                .map(unitOfMeasureToUnitOfMeasureCommand::convert);
    }
}
