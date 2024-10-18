// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2024 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
// end::copyright[]
package io.openliberty.guides.data;

import java.util.List;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Insert;
import jakarta.data.repository.By;
import jakarta.data.repository.Repository;

@Repository
// tag::CrudRepository[]
public interface Packages extends CrudRepository<Package, Integer> {
// end::CrudRepository[]

    // tag::query-by-method-step1[]
    List<Package> findByLengthGreaterThan(float length);
    // end::query-by-method-step1[]
    
    // tag::query-by-method-step2[]
    List<Package> findByLengthGreaterThanWidthLessThan(float length, float width);
    // end::query-by-method-step2[]

    // tag::query-by-method-step3[]
    List<Package> findByHeightBetween(float minHeight, float maxHeight);
    // end::query-by-method-step3[]

    // tag::repository-annotations[]
    @Find
    List<Package> getPackagesArrivingIn(@By("destination") String destination);

    @Insert
    void add(Package p);
    // end::repository-annotations[]
}
