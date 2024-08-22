package io.openliberty.guides.data;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Startup
@Singleton
public class DbInit {
    @Inject
    Packages packages;

    @PostConstruct
    public void init() {
        //Liberty Dev mode restarts the app without restarting the JVM, which results in 
        //the Db not being cleared from memory, so only add the packages if nothing exists.
        if (packages.findAll().count() == 0) {
            packages.insert(new Package(1, 10f, 20f, 10f));
            packages.insert(new Package(2, 30f, 10f, 10f));
            packages.insert(new Package(3, 5f, 10f, 5f));
            packages.insert(new Package(4, 5f, 15f, 15f));
        }
    }
}
