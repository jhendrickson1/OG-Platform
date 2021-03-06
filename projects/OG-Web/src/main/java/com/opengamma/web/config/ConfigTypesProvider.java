/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.web.config;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

import org.fudgemsg.AnnotationReflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Maps;
import com.opengamma.core.config.Config;

/**
 * Provides all supported configuration types
 */
public final class ConfigTypesProvider {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(ConfigTypesProvider.class);
  /**
   * Singleton instance.
   */
  private static final ConfigTypesProvider s_instance = new ConfigTypesProvider();

  /**
   * Map of config types.
   */
  private final ImmutableSortedMap<String, Class<?>> _configTypeMap;
  /**
   * Map of config descriptions.
   */
  private final ImmutableSortedMap<String, String> _configDescriptionMap;

  //-------------------------------------------------------------------------
  /**
   * Gets the singleton instance.
   * 
   * @return the provider, not null
   */
  public static ConfigTypesProvider getInstance() {
    return s_instance;
  }

  //-------------------------------------------------------------------------
  /**
   * Restricted constructor
   */
  private ConfigTypesProvider() {
    Map<String, Class<?>> result = Maps.newHashMap();
    ImmutableSortedMap.Builder<String, String> descriptions = ImmutableSortedMap.naturalOrder();
    AnnotationReflector reflector = AnnotationReflector.getDefaultReflector();
    Set<Class<?>> configClasses = reflector.getReflector().getTypesAnnotatedWith(Config.class);
    for (Class<?> configClass : configClasses) {
      Annotation annotation = configClass.getAnnotation(Config.class);
      if (annotation instanceof Config) {
        Config configValueAnnotation = (Config) annotation;
        // extract config type
        Class<?> configType = configValueAnnotation.searchType();
        if (configType == Object.class) {
          configType = configClass;
        }
        // extract description
        String description = configValueAnnotation.description();
        if (description.length() == 0) {
          description = configType.getSimpleName();
        }
        // store
        Class<?> old = result.put(configType.getSimpleName(), configType);
        if (old != null) {
          s_logger.warn("Two classes exist with the same name: " + configType.getSimpleName());
        }
        descriptions.put(configType.getSimpleName(), description);
      }
    }
    _configTypeMap = ImmutableSortedMap.copyOf(result);
    _configDescriptionMap = descriptions.build();
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the set of config keys.
   * 
   * @return the types, not null
   */
  public ImmutableSortedSet<String> getConfigTypes() {
    return ImmutableSortedSet.copyOf(_configTypeMap.keySet());
  }

  /**
   * Gets the map of config types by short key.
   * 
   * @return the map, not null
   */
  public ImmutableSortedMap<String, Class<?>> getConfigTypeMap() {
    return _configTypeMap;
  }

  /**
   * Gets the map of config descriptions by short key.
   * 
   * @return the map, not null
   */
  public ImmutableSortedMap<String, String> getDescriptionMap() {
    return _configDescriptionMap;
  }

  /**
   * Gets the description for a type.
   * 
   * @param type  the type, not null
   * @return the description, not null
   */
  public String getDescription(Class<?> type) {
    String key = HashBiMap.create(_configTypeMap).inverse().get(type);
    String description = null;
    if (key != null) {
      description = _configDescriptionMap.get(key);
    }
    return (description != null ? description : type.getSimpleName());
  }

}
