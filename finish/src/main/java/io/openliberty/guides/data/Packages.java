package io.openliberty.guides.data;

import java.util.List;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Repository;

@Repository
public interface Packages extends CrudRepository<Package, Integer> {

    // tag::query-by-method-step1
    List<Package> findByLengthGreaterThan(float length);
    // end::query-by-method-step1

    // tag::query-by-method-step1
    List<Package> findByLengthGreaterThanWidthLessThan(float length, float width);
    // end::query-by-method-step1
}
