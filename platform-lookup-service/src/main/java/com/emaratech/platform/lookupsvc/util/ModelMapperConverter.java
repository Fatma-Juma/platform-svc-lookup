package com.emaratech.platform.lookupsvc.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * The implementation of mapping converter.
 */
@Component
public class ModelMapperConverter implements Converter {
    /**
     * The model mapper instance.
     */
    private final ModelMapper modelMapper;

    /**
     * The default constructor.
     */
    public ModelMapperConverter() {
        modelMapper = new ModelMapper();
    }

    /** {@inheritDoc} */
    @Override
    public <T> T mapEntityToDTO(Object entityObject, Class<T> dtoObject) {
        return modelMapper.map(entityObject, dtoObject);
    }

    /** {@inheritDoc} */
    @Override
    public <T> T mapDTOToEntity(Object dtoObject, Class<T> entityObject) {
        return modelMapper.map(dtoObject, entityObject);
    }

    /** {@inheritDoc} */
    @Override
    public <D, T> List<D> mapListOfEntityToListOfDto(Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> mapEntityToDTO(entity, outCLass))
                .collect(Collectors.toList());
    }

    @Override
    public <D, T> Set<D> mapListOfEntityToSetOfDto(Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> mapEntityToDTO(entity, outCLass))
                .collect(Collectors.toSet());
    }

}