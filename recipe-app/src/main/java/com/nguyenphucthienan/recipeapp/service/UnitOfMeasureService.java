package com.nguyenphucthienan.recipeapp.service;

import com.nguyenphucthienan.recipeapp.command.UnitOfMeasureCommand;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> getAllUnitOfMeasures();
}
