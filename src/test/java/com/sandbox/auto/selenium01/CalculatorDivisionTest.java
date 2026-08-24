package com.sandbox.auto.selenium01;

import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;

public class CalculatorDivisionTest extends CalculatorBaseTest {
    @Test(dataProviderClass = CalculatorDataProvider.class, dataProvider = "Zero Division Data")
    public void divisionByZeroTest(long a, long b) {
        assertThrows(NumberFormatException.class, () -> calculator.div(a, b));
    }
}
