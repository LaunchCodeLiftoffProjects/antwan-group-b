package org.launchcode.FamilyOrganizer.data;

import org.launchcode.FamilyOrganizer.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
