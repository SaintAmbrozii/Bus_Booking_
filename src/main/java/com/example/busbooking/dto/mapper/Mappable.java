package com.example.busbooking.dto.mapper;

import java.util.List;

public interface Mappable<E,D>{

    E fromDto(D dto);

    D toDto(E entity);

    List<D> toDto(List<E> entities);

    List<E> toEntity(List<D> dtos);
}

