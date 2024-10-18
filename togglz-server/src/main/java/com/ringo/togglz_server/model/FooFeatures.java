package com.ringo.togglz_server.model;

import org.togglz.core.Feature;
import org.togglz.core.activation.ReleaseDateActivationStrategy;
import org.togglz.core.annotation.ActivationParameter;
import org.togglz.core.annotation.DefaultActivationStrategy;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;

public enum FooFeatures implements Feature {

    @Label("Feature one")
    FEATURE_ONE,

    @EnabledByDefault
    @Label("Feature two")
    FEATURE_TWO,

    @Label("Previous Date Example")
    @EnabledByDefault
    @DefaultActivationStrategy(id = ReleaseDateActivationStrategy.ID,
            parameters = @ActivationParameter(name="date", value = "2024-10-15")
    )
    PREV_DATE_SAMPLE,

    @Label("Future Date Example")
    @EnabledByDefault
    @DefaultActivationStrategy(id = ReleaseDateActivationStrategy.ID,
            parameters = @ActivationParameter(name="date", value = "2024-10-19")
    )
    FUTURE_DATE_SAMPLE;
}
