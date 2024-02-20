package com.example.springwebfluxpostgres.contributor;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CustomInfoContributor implements InfoContributor {

    // What ever the property we set here will be displayed in the /actuator/info endpoint
    private final Environment environment;

    public CustomInfoContributor(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void contribute(Info.Builder builder) {
        // I added the below code in property file, and it does work
        //builder.withDetail("port",environment.getProperty("local.server.port"));
        builder.withDetail("profile",environment.getDefaultProfiles());
    }
}
