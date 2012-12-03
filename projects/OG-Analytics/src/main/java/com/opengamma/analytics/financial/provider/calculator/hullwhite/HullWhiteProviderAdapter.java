/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.provider.calculator.hullwhite;

import com.opengamma.analytics.financial.interestrate.InstrumentDerivative;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivativeVisitor;
import com.opengamma.analytics.financial.interestrate.InstrumentDerivativeVisitorSameMethodAdapter;
import com.opengamma.analytics.financial.provider.description.HullWhiteOneFactorProvider;
import com.opengamma.analytics.financial.provider.description.MulticurveProviderInterface;

/**
 * 
 * @param <RESULT_TYPE> The result-type for the provider.
 */
public class HullWhiteProviderAdapter<RESULT_TYPE> extends InstrumentDerivativeVisitorSameMethodAdapter<HullWhiteOneFactorProvider, RESULT_TYPE> {
  private final InstrumentDerivativeVisitor<MulticurveProviderInterface, RESULT_TYPE> _visitor;

  public HullWhiteProviderAdapter(final InstrumentDerivativeVisitor<MulticurveProviderInterface, RESULT_TYPE> visitor) {
    _visitor = visitor;
  }

  @Override
  public RESULT_TYPE visit(final InstrumentDerivative derivative) {
    return derivative.accept(_visitor);
  }

  @Override
  public RESULT_TYPE visit(final InstrumentDerivative derivative, final HullWhiteOneFactorProvider data) {
    return derivative.accept(_visitor, data.getMulticurveProvider());
  }

}
