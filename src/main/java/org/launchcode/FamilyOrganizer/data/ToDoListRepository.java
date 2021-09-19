package org.launchcode.FamilyOrganizer.data;

import org.launchcode.FamilyOrganizer.models.ToDoList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListRepository extends CrudRepository<ToDoList, Integer> {

    List<ToDoList> findByUserId(int id);


}
