/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.fudgemsg.security;

import org.fudgemsg.FudgeMsg;
import org.fudgemsg.MutableFudgeMsg;
import org.fudgemsg.mapping.FudgeBuilder;
import org.fudgemsg.mapping.FudgeBuilderFor;
import org.fudgemsg.mapping.FudgeDeserializer;
import org.fudgemsg.mapping.FudgeSerializer;

import com.opengamma.financial.security.future.EquityFutureSecurity;
import com.opengamma.util.fudgemsg.AbstractFudgeBuilder;
import com.opengamma.util.fudgemsg.ExternalIdBuilder;
import com.opengamma.util.fudgemsg.ZonedDateTimeBuilder;

/**
 * A Fudge builder for {@code EquityFutureSecurity}.
 */
@FudgeBuilderFor(EquityFutureSecurity.class)
public class EquityFutureSecurityBuilder extends AbstractFudgeBuilder implements FudgeBuilder<EquityFutureSecurity> {

  /** Field name. */
  public static final String SETTLEMENT_DATE_KEY = "settlementDate";
  /** Field name. */
  public static final String UNDERLYING_IDENTIFIER_KEY = "underlyingIdentifier";

  @Override
  public MutableFudgeMsg buildMessage(FudgeSerializer serializer, EquityFutureSecurity object) {
    final MutableFudgeMsg msg = serializer.newMessage();
    EquityFutureSecurityBuilder.toFudgeMsg(serializer, object, msg);
    return msg;
  }

  public static void toFudgeMsg(FudgeSerializer serializer, EquityFutureSecurity object, final MutableFudgeMsg msg) {
    FutureSecurityBuilder.toFudgeMsg(serializer, object, msg);
    addToMessage(msg, SETTLEMENT_DATE_KEY, ZonedDateTimeBuilder.toFudgeMsg(serializer, object.getSettlementDate()));
    addToMessage(msg, UNDERLYING_IDENTIFIER_KEY, ExternalIdBuilder.toFudgeMsg(serializer, object.getUnderlyingId()));
  }

  @Override
  public EquityFutureSecurity buildObject(FudgeDeserializer deserializer, FudgeMsg msg) {
    EquityFutureSecurity object = new EquityFutureSecurity();
    EquityFutureSecurityBuilder.fromFudgeMsg(deserializer, msg, object);
    return object;
  }

  public static void fromFudgeMsg(FudgeDeserializer deserializer, FudgeMsg msg, EquityFutureSecurity object) {
    FutureSecurityBuilder.fromFudgeMsg(deserializer, msg, object);
    object.setSettlementDate(ZonedDateTimeBuilder.fromFudgeMsg(deserializer, msg.getMessage(SETTLEMENT_DATE_KEY)));
    object.setUnderlyingId(ExternalIdBuilder.fromFudgeMsg(deserializer, msg.getMessage(UNDERLYING_IDENTIFIER_KEY)));
  }

}
