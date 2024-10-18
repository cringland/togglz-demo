package com.ringo.togglz_server.model;

import org.togglz.core.metadata.FeatureMetaData;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ToggleResponse(String name, boolean active, FeatureMetaData metaData) {

}
