package org.launchcode.FamilyOrganizer.data;

import org.launchcode.FamilyOrganizer.models.GroceryListItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryListRepository extends CrudRepository<GroceryListItem, Integer> {

//    List<GroceryListItem> findByUserId(int id);
}