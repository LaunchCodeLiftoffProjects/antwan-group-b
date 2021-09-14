package org.launchcode.FamilyOrganizer.data;

import org.launchcode.FamilyOrganizer.models.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuRepository extends CrudRepository <Menu, Integer>{

    List<Menu> findByUserId(int id);

}
