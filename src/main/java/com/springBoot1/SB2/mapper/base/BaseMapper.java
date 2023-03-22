package com.springBoot1.SB2.mapper.base;

import org.mapstruct.Condition;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<Entity,ShowDTO,ShowAllDTO,CreateDTO,UpdateDTO> {
    public ShowDTO mapToShow(Entity entity);
    public List<ShowAllDTO> mapToShowAll(List<Entity> entities);
    public Entity unMapCreated(CreateDTO createDTO);
    public Entity unMapUpdated(@MappingTarget Entity entity,UpdateDTO  updateDTO);
}
