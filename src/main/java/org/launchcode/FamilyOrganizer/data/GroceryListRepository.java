package org.launchcode.FamilyOrganizer.data;

import org.launchcode.FamilyOrganizer.models.GroceryList;
import org.springframework.data.repository.CrudRepository;

public interface GroceryListRepository extends CrudRepository<GroceryList, Integer> {
}
