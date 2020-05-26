package com.nguyenphucthienan.recipeapp.service;

import com.nguyenphucthienan.recipeapp.command.UnitOfMeasureCommand;
import com.nguyenphucthienan.recipeapp.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.nguyenphucthienan.recipeapp.domain.UnitOfMeasure;
import com.nguyenphucthienan.recipeapp.repository.reactive.UnitOfMeasureReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    public UnitOfMeasureServiceImplTest() {
        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureReactiveRepository,
                unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void testGetAllUnitOfMeasures() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();

        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId("1");
        unitOfMeasures.add(unitOfMeasure1);

        UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId("2");
        unitOfMeasures.add(unitOfMeasure2);

        when(unitOfMeasureReactiveRepository.findAll()).thenReturn(Flux.just(unitOfMeasure1, unitOfMeasure2));

        List<UnitOfMeasureCommand> commands = unitOfMeasureService.getAllUnitOfMeasures().collectList().block();

        assertEquals(2, Objects.requireNonNull(commands).size());
        verify(unitOfMeasureReactiveRepository, times(1)).findAll();
    }
}
