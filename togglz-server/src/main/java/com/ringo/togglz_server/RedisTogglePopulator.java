package com.ringo.togglz_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.togglz.core.context.FeatureContext;
import org.togglz.core.repository.StateRepository;

@Component
public class RedisTogglePopulator implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger log = LoggerFactory.getLogger(RedisTogglePopulator.class);

    private final StateRepository repo;

    public RedisTogglePopulator(final StateRepository repo) {
        this.repo = repo;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        //Togglz will use the default config before storing anything in Redis.
        //In order for this to be shared across services without duplicating the toggle config,
        //We need to force togglz to set the default config in Redis
        final var manager = FeatureContext.getFeatureManager();
        final var features = manager.getFeatures();
        features.stream().filter(feature -> repo.getFeatureState(feature) == null)
                .forEach(feature -> {
                    final var featureState = manager.getFeatureState(feature);
                    log.info("New Toggle Detected, adding {}", feature.name());
                    repo.setFeatureState(featureState);
                });
    }
}
