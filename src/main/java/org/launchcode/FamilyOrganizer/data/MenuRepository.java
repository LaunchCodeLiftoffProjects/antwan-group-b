package org.launchcode.FamilyOrganizer.data;

import org.launchcode.FamilyOrganizer.models.AbstractEntity;
import org.launchcode.FamilyOrganizer.models.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuRepository<M extends AbstractEntity, I extends Number> extends CrudRepository <Menu, Integer>{

    List<Menu> findByUserId(int id);

}

