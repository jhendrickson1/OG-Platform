/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.fudgemsg;

import org.fudgemsg.FudgeMsg;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeBuilder;
import org.fudgemsg.mapping.FudgeBuilderFor;
import org.fudgemsg.mapping.FudgeDeserializer;
import org.fudgemsg.mapping.FudgeSerializer;

import com.opengamma.financial.analytics.fxforwardcurve.BloombergFXForwardCurveInstrumentProvider;

/**
 * 
 */
@FudgeBuilderFor(BloombergFXForwardCurveInstrumentProvider.class)
public class BloombergFXForwardCurveInstrumentProviderFudgeBuilder implements FudgeBuilder<BloombergFXForwardCurveInstrumentProvider> {
  private static final String DATA_FIELD_NAME = "dataFieldName";
  private static final String POSTFIX = "postfix";
  private static final String PREFIX = "prefix";
  private static final String SPOT_PREFIX = "spotPrefix";

  @Override
  public MutableFudgeMsg buildMessage(final FudgeSerializer serializer, final BloombergFXForwardCurveInstrumentProvider object) {
    final MutableFudgeMsg message = serializer.newMessage();
    message.add(PREFIX, object.getPrefix());
    message.add(POSTFIX, object.getPostfix());
    if (!object.getSpotPrefix().equals(object.getPrefix())) {
      message.add(SPOT_PREFIX, object.getSpotPrefix());
    }
    message.add(DATA_FIELD_NAME, object.getDataFieldName());
    return message;
  }

  @Override
  public BloombergFXForwardCurveInstrumentProvider buildObject(final FudgeDeserializer deserializer, final FudgeMsg message) {
    final String prefix = message.getString(PREFIX);
    final String postfix = message.getString(POSTFIX);
    String spotPrefix = message.getString(SPOT_PREFIX);
    if (spotPrefix == null) {
      spotPrefix = prefix;
    }
    final String dataFieldName = message.getString(DATA_FIELD_NAME);
    return new BloombergFXForwardCurveInstrumentProvider(prefix, postfix, spotPrefix, dataFieldName);
  }

}
