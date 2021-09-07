package org.launchcode.FamilyOrganizer.data;

import org.launchcode.FamilyOrganizer.models.GroceryList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryListRepository extends CrudRepository<GroceryList, Integer> {

    List<GroceryList> findByUserId(int id);
}