package io.openliberty.guides.data;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface Packages extends CrudRepository<Package, Integer> {
    
}
