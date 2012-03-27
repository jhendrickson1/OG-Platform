/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.integration.loadsave.portfolio.reader;

import java.io.InputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.financial.tool.ToolContext;
import com.opengamma.integration.loadsave.portfolio.rowparser.RowParser;
import com.opengamma.integration.loadsave.sheet.reader.SheetReader;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.ManageableTrade;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.tuple.ObjectsPair;

/**
 * A simple portfolio reader assumes that the input sheet only contains one asset class, and may also be used as a base
 * class for specific asset class loaders that follow this rule.
 */
public class SingleSheetSimplePortfolioReader extends SingleSheetPortfolioReader {

  private static final Logger s_logger = LoggerFactory.getLogger(SingleSheetSimplePortfolioReader.class);

  /*
   * Load one or more parsers for different types of securities/trades/whatever here
   */
  private RowParser _rowParser;
  /*
   * Specify column order and names here (optional, may be inferred from sheet headers instead)
   */
  private String[] _columns;

  public SingleSheetSimplePortfolioReader(String filename, InputStream portfolioFileStream, RowParser rowParser) {
    super(SheetReader.newSheetReader(filename, portfolioFileStream));
    
    ArgumentChecker.notNull(rowParser, "rowParser");
    
    _columns = getSheet().getColumns();
    _rowParser = rowParser;
  }

  public SingleSheetSimplePortfolioReader(SheetReader sheet, String[] columns, RowParser rowParser) {   
    super(sheet);
    
    ArgumentChecker.notNull(rowParser, "rowParser");

    _columns = getSheet().getColumns();
    _rowParser = rowParser;
  }

  public SingleSheetSimplePortfolioReader(String filename, InputStream portfolioFileStream, String securityClass) {
    super(SheetReader.newSheetReader(filename, portfolioFileStream));
    
    ArgumentChecker.notNull(securityClass, "securityClass");
    
    _columns = getSheet().getColumns();
    _rowParser = RowParser.newRowParser(securityClass);
    if (_rowParser == null) {
      throw new OpenGammaRuntimeException("Could not identify an appropriate row parser for security class " + securityClass);
    }
  }

  public SingleSheetSimplePortfolioReader(SheetReader sheet, String[] columns, String securityClass, ToolContext toolContext) {
    super(sheet);

    ArgumentChecker.notNull(securityClass, "securityClass");

    _columns = getSheet().getColumns();
    _rowParser = RowParser.newRowParser(securityClass);
    if (_rowParser == null) {
      throw new OpenGammaRuntimeException("Could not identify an appropriate row parser for security class " + securityClass);
    }
  }

//  @Override
//  public void writeTo(PortfolioWriter portfolioWriter) {
//    Map<String, String> row;
//
//    // Get the next row from the sheet
//    while ((row = getSheet().loadNextRow()) != null) {
//    
//      // Build the underlying security
//      ManageableSecurity[] security = _rowParser.constructSecurity(row);
//      if (security.length > 0) {
//        // Attempt to write securities and obtain the correct security (either newly written or original)
//        // Write array in reverse order as underlying is at position 0
//        for (int i = security.length - 1; i >= 0; i--) {
//          security[i] = portfolioWriter.writeSecurity(security[i]);        
//        }
//  
//        
//        // Build the position and trade(s) using security[0] (underlying)
//        ManageablePosition position = _rowParser.constructPosition(row, security[0]);
//        
//        ManageableTrade trade = _rowParser.constructTrade(row, security[0], position);
//        if (trade != null) { 
//          position.addTrade(trade);
//        }
//        
//        // Write positions/trade(s) to masters (trades are implicitly written with the position)
//        portfolioWriter.writePosition(position);
//      } else {
//        s_logger.warn("Row parser was unable to construct a security from row " + row);
//      }
//    }
//  }

  @Override
  public ObjectsPair<ManageablePosition, ManageableSecurity[]> readNext() {
    
    Map<String, String> row = getSheet().loadNextRow();    
    if (row == null) {
      return null;
    }
    
    // Build the underlying security
    ManageableSecurity[] securities = _rowParser.constructSecurity(row);
    if (securities != null && securities.length > 0) {
      
      // Build the position and trade(s) using security[0] (underlying)
      ManageablePosition position = _rowParser.constructPosition(row, securities[0]);      
      if (position != null) {
        ManageableTrade trade = _rowParser.constructTrade(row, securities[0], position);
        if (trade != null) { 
          position.addTrade(trade);
        }
      }
      return new ObjectsPair<ManageablePosition, ManageableSecurity[]>(position, securities);
      
    } else {
      s_logger.warn("Row parser was unable to construct a security from row " + row);
      return new ObjectsPair<ManageablePosition, ManageableSecurity[]>(null, null);
    }
    
  }

  public String[] getColumns() {
    return _columns;
  }

  @Override
  public String[] getCurrentPath() {
    return new String[0];
  }

  @Override
  public void close() {
    getSheet().close();
  }

}
