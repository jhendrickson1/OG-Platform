/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate.bond.provider;

import static org.testng.AssertJUnit.assertEquals;

import javax.time.calendar.ZonedDateTime;

import org.testng.annotations.Test;

import com.opengamma.analytics.financial.instrument.bond.BillSecurityDefinition;
import com.opengamma.analytics.financial.instrument.bond.BillTransactionDefinition;
import com.opengamma.analytics.financial.interestrate.bond.definition.BillTransaction;
import com.opengamma.analytics.financial.provider.calculator.issuer.ParSpreadMarketQuoteCurveSensitivityIssuerDiscountingCalculator;
import com.opengamma.analytics.financial.provider.calculator.issuer.ParSpreadMarketQuoteIssuerDiscountingCalculator;
import com.opengamma.analytics.financial.provider.calculator.issuer.PresentValueCurveSensitivityIssuerCalculator;
import com.opengamma.analytics.financial.provider.calculator.issuer.PresentValueIssuerCalculator;
import com.opengamma.analytics.financial.provider.description.IssuerProviderDiscount;
import com.opengamma.analytics.financial.provider.description.IssuerProviderDiscountDataSets;
import com.opengamma.analytics.financial.provider.sensitivity.issuer.ParameterSensitivityIssuerCalculator;
import com.opengamma.analytics.financial.provider.sensitivity.issuer.ParameterSensitivityIssuerDiscountInterpolatedFDCalculator;
import com.opengamma.analytics.financial.provider.sensitivity.issuer.SimpleParameterSensitivityIssuerCalculator;
import com.opengamma.analytics.financial.provider.sensitivity.issuer.SimpleParameterSensitivityIssuerDiscountInterpolatedFDCalculator;
import com.opengamma.analytics.financial.provider.sensitivity.multicurve.MulticurveSensitivity;
import com.opengamma.analytics.financial.provider.sensitivity.multicurve.MultipleCurrencyMulticurveSensitivity;
import com.opengamma.analytics.financial.provider.sensitivity.multicurve.MultipleCurrencyParameterSensitivity;
import com.opengamma.analytics.financial.provider.sensitivity.multicurve.SimpleParameterSensitivity;
import com.opengamma.analytics.financial.schedule.ScheduleCalculator;
import com.opengamma.analytics.financial.util.AssertSensivityObjects;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.calendar.MondayToFridayCalendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCountFactory;
import com.opengamma.financial.convention.yield.YieldConvention;
import com.opengamma.financial.convention.yield.YieldConventionFactory;
import com.opengamma.util.money.Currency;
import com.opengamma.util.money.MultipleCurrencyAmount;
import com.opengamma.util.time.DateUtils;

/**
 * Tests related to the pricing of bills transactions by discounting.
 */
public class BillTransactionDiscountingMethodTest {

  private final static IssuerProviderDiscount ISSUER_MULTICURVE = IssuerProviderDiscountDataSets.createIssuerProvider();
  private final static String[] ISSUER_NAMES = IssuerProviderDiscountDataSets.getIssuerNames();

  private final static Currency EUR = Currency.EUR;
  private static final Calendar CALENDAR = new MondayToFridayCalendar("TARGET");
  private final static ZonedDateTime REFERENCE_DATE = DateUtils.getUTCDate(2012, 1, 17);

  private static final DayCount ACT360 = DayCountFactory.INSTANCE.getDayCount("Actual/360");
  private static final int SETTLEMENT_DAYS = 2;
  private static final YieldConvention YIELD_CONVENTION = YieldConventionFactory.INSTANCE.getYieldConvention("INTEREST@MTY");

  // ISIN: BE0312677462
  private final static ZonedDateTime END_DATE = DateUtils.getUTCDate(2012, 3, 15);
  private final static double NOTIONAL = 1000;
  private static final double YIELD = 0.00185; // External source
  //  private static final double PRICE = 0.99971; // External source

  private final static ZonedDateTime SETTLE_DATE = ScheduleCalculator.getAdjustedDate(REFERENCE_DATE, SETTLEMENT_DAYS, CALENDAR);
  private final static BillSecurityDefinition BILL_SEC_DEFINITION = new BillSecurityDefinition(EUR, END_DATE, NOTIONAL, SETTLEMENT_DAYS, CALENDAR, YIELD_CONVENTION, ACT360, ISSUER_NAMES[1]);
  private final static double QUANTITY = 123456.7;
  private final static BillTransactionDefinition BILL_TRA_DEFINITION = BillTransactionDefinition.fromYield(BILL_SEC_DEFINITION, QUANTITY, SETTLE_DATE, YIELD);
  public static final String NOT_USED = "Not used";
  public static final String[] NOT_USED_A = {NOT_USED, NOT_USED, NOT_USED};
  private final static BillTransaction BILL_TRA = BILL_TRA_DEFINITION.toDerivative(REFERENCE_DATE, NOT_USED_A);

  private final static BillSecurityDiscountingMethod METHOD_SECURITY = BillSecurityDiscountingMethod.getInstance();
  private final static BillTransactionDiscountingMethod METHOD_TRANSACTION = BillTransactionDiscountingMethod.getInstance();

  private static final double SHIFT_FD = 1.0E-6;

  private final static PresentValueIssuerCalculator PVIC = PresentValueIssuerCalculator.getInstance();
  private final static PresentValueCurveSensitivityIssuerCalculator PVCSIC = PresentValueCurveSensitivityIssuerCalculator.getInstance();
  private static final ParameterSensitivityIssuerCalculator PS_PVI_C = new ParameterSensitivityIssuerCalculator(PVCSIC);
  private static final ParameterSensitivityIssuerDiscountInterpolatedFDCalculator PS_PVI_FDC = new ParameterSensitivityIssuerDiscountInterpolatedFDCalculator(PVIC, SHIFT_FD);

  private final static ParSpreadMarketQuoteIssuerDiscountingCalculator PSMQIDC = ParSpreadMarketQuoteIssuerDiscountingCalculator.getInstance();
  private final static ParSpreadMarketQuoteCurveSensitivityIssuerDiscountingCalculator PSMQCSIDC = ParSpreadMarketQuoteCurveSensitivityIssuerDiscountingCalculator.getInstance();
  private static final SimpleParameterSensitivityIssuerCalculator PS_PSMQ_C = new SimpleParameterSensitivityIssuerCalculator(PSMQCSIDC);
  private static final SimpleParameterSensitivityIssuerDiscountInterpolatedFDCalculator PS_PSMQ_FDC = new SimpleParameterSensitivityIssuerDiscountInterpolatedFDCalculator(PSMQIDC, SHIFT_FD);

  private static final double TOLERANCE_PV = 1.0E-2;
  private static final double TOLERANCE_PV_DELTA = 1.0E+2; //Testing note: Sensitivity is for a movement of 1. 1E+2 = 1 cent for a 1 bp move.
  private static final double TOLERANCE_SPREAD = 1.0E-8;
  private static final double TOLERANCE_SPREAD_DELTA = 1.0E-6;

  @Test
  /**
   * Tests the present value against explicit computation.
   */
  public void presentValue() {
    MultipleCurrencyAmount pvTransactionComputed = METHOD_TRANSACTION.presentValue(BILL_TRA, ISSUER_MULTICURVE);
    MultipleCurrencyAmount pvSecurity = METHOD_SECURITY.presentValue(BILL_TRA.getBillPurchased(), ISSUER_MULTICURVE);
    pvSecurity = pvSecurity.multipliedBy(QUANTITY);
    double pvSettle = BILL_TRA_DEFINITION.getSettlementAmount() * ISSUER_MULTICURVE.getMulticurveProvider().getDiscountFactor(EUR, BILL_TRA.getBillPurchased().getSettlementTime());
    assertEquals("Bill Security: discounting method - present value", pvSecurity.getAmount(EUR) + pvSettle, pvTransactionComputed.getAmount(EUR), TOLERANCE_PV);
  }

  @Test
  /**
   * Tests the present value: Method vs Calculator
   */
  public void presentValueMethodVsCalculator() {
    MultipleCurrencyAmount pvMethod = METHOD_TRANSACTION.presentValue(BILL_TRA, ISSUER_MULTICURVE);
    MultipleCurrencyAmount pvCalculator = PVIC.visit(BILL_TRA, ISSUER_MULTICURVE);
    assertEquals("Bill Security: discounting method - present value", pvMethod.getAmount(EUR), pvCalculator.getAmount(EUR), TOLERANCE_PV);
  }

  @Test
  /**
   * Tests present value curve sensitivity.
   */
  public void presentValueCurveSensitivity() {
    MultipleCurrencyParameterSensitivity pvpsDepositExact = PS_PVI_C.calculateSensitivity(BILL_TRA, ISSUER_MULTICURVE, ISSUER_MULTICURVE.getMulticurveProvider().getAllNames());
    MultipleCurrencyParameterSensitivity pvpsDepositFD = PS_PVI_FDC.calculateSensitivity(BILL_TRA, ISSUER_MULTICURVE);
    AssertSensivityObjects.assertEquals("DepositCounterpartDiscountingMethod: presentValueCurveSensitivity ", pvpsDepositExact, pvpsDepositFD, TOLERANCE_PV_DELTA);
  }

  @Test
  public void presentValueCurveSensitivityMethodVsCalculator() {
    MultipleCurrencyMulticurveSensitivity pvcsMethod = METHOD_TRANSACTION.presentValueCurveSensitivity(BILL_TRA, ISSUER_MULTICURVE);
    MultipleCurrencyMulticurveSensitivity pvcsCalculator = PVCSIC.visit(BILL_TRA, ISSUER_MULTICURVE);
    AssertSensivityObjects.assertEquals("Bill Security: discounting method - curve sensitivity", pvcsMethod, pvcsCalculator, TOLERANCE_PV_DELTA);
  }

  @Test
  /**
   * Tests the par spread.
   */
  public void parSpread() {
    double spread = METHOD_TRANSACTION.parSpread(BILL_TRA, ISSUER_MULTICURVE);
    BillTransactionDefinition bill0Definition = BillTransactionDefinition.fromYield(BILL_SEC_DEFINITION, QUANTITY, SETTLE_DATE, YIELD + spread);
    BillTransaction bill0 = bill0Definition.toDerivative(REFERENCE_DATE, NOT_USED_A);
    MultipleCurrencyAmount pv0 = METHOD_TRANSACTION.presentValue(bill0, ISSUER_MULTICURVE);
    assertEquals("Bill Security: discounting method - par spread", 0, pv0.getAmount(EUR), TOLERANCE_PV);
  }

  @Test
  /**
   * Tests the par spread (Method vs Calculator).
   */
  public void parSpreadMethodVsCalculator() {
    double spreadMethod = METHOD_TRANSACTION.parSpread(BILL_TRA, ISSUER_MULTICURVE);
    double spreadCalculator = PSMQIDC.visit(BILL_TRA, ISSUER_MULTICURVE);
    assertEquals("Bill Security: discounting method - par spread", spreadMethod, spreadCalculator, TOLERANCE_SPREAD);
  }

  @Test
  /**
   * Tests parSpread curve sensitivity.
   */
  public void parSpreadCurveSensitivity() {
    SimpleParameterSensitivity pspsDepositExact = PS_PSMQ_C.calculateSensitivity(BILL_TRA, ISSUER_MULTICURVE, ISSUER_MULTICURVE.getAllNames());
    SimpleParameterSensitivity pspsDepositFD = PS_PSMQ_FDC.calculateSensitivity(BILL_TRA, ISSUER_MULTICURVE);
    AssertSensivityObjects.assertEquals("DepositCounterpartDiscountingMethod: presentValueCurveSensitivity ", pspsDepositExact, pspsDepositFD, TOLERANCE_PV_DELTA);
  }

  @Test
  /**
   * Tests the par spread curve sensitivity  (Method vs Calculator).
   */
  public void parSpreadCurveSensitivityMethodVsCalculator() {
    MulticurveSensitivity pscsMethod = METHOD_TRANSACTION.parSpreadCurveSensitivity(BILL_TRA, ISSUER_MULTICURVE);
    MulticurveSensitivity pscsCalculator = PSMQCSIDC.visit(BILL_TRA, ISSUER_MULTICURVE);
    AssertSensivityObjects.assertEquals("parSpread: curve sensitivity - fwd", pscsMethod, pscsCalculator, TOLERANCE_SPREAD_DELTA);
  }

}
