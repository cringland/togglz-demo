package com.ringo.togglz_client;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.togglz.core.context.FeatureContext;
import org.togglz.core.util.NamedFeature;

import com.ringo.togglz_client.model.ToggleResponse;

@RestController
@RequestMapping("/toggle")
public class ToggleController {
    @GetMapping("/all")
    public ResponseEntity<List<ToggleResponse>> allToggles(@RequestParam(required = false) final boolean metadata) {
        final var manager = FeatureContext.getFeatureManager();
        return ResponseEntity.ok(manager.getFeatures().stream().map(feature ->
                new ToggleResponse(
                        feature.name(),
                        feature.isActive(),
                        metadata ? manager.getMetaData(feature) : null
                )
        ).toList());
    }

    @GetMapping("/{toggle}")
    public ResponseEntity<Boolean> toggle(@PathVariable("toggle") final String toggleName) {
        final var manager = FeatureContext.getFeatureManager();
        final var featureState = manager.getFeatureState(new NamedFeature(toggleName));
        if (featureState == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        final var feature = featureState.getFeature();
        return ResponseEntity.ok(feature.isActive());
    }
}
