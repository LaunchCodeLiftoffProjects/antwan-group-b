package org.launchcode.FamilyOrganizer.data;

import org.launchcode.FamilyOrganizer.models.GroceryListItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroceryListRepository extends CrudRepository<GroceryListItem, Integer> {

    List<GroceryListItem> findByUserId(int id);
}