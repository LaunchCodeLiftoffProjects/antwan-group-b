package org.launchcode.FamilyOrganizer.data;

import org.launchcode.FamilyOrganizer.models.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository <Menu, Integer>{

    Menu findByUser(int user);
}
