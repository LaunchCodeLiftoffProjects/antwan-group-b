package org.launchcode.FamilyOrganizer.data;

import org.launchcode.FamilyOrganizer.models.EventDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventDetails, Integer>{



}
