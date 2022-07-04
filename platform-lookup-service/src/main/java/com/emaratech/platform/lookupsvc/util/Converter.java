package com.emaratech.platform.lookupsvc.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * The convertor interface for mapping between db layer data to presentation
 * layer data.
 */
public interface Converter {

    /**
     * This method is used to convert Entity To DTO.
     *
     * @param entityObject the entityObject
     * @param dtoObject the dtoObject
     * @param <T>
     * @return DTO Object
     */
    <T> T mapEntityToDTO(Object entityObject, Class<T> dtoObject);

    /**
     * This method is used to convert DTO to Entity.
     *
     * @param dtoObject source Object which will be converted into Entity
     * @param entityObject target Object
     * @param <T>
     * @return Entity Object
     */
    <T> T mapDTOToEntity(Object dtoObject, Class<T> entityObject);

    /**
     * outClass object must have default constructor with no arguments.
     *
     * @param entityList the entityList
     * @param outCLass the outCLass
     * @param <D> type of objects in result list
     * @param <T> type of entity in entity List
     * @return list of mapped object with type.
     */
    public <D, T> List<D> mapListOfEntityToListOfDto(Collection<T> entityList, Class<D> outCLass);

    public <D, T> Set<D> mapListOfEntityToSetOfDto(Collection<T> entityList, Class<D> outCLass);
}
