package org.launchcode.FamilyOrganizer.data;

import org.launchcode.FamilyOrganizer.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
