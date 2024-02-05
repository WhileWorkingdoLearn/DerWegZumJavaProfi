package org.der.weg.zum.java.profi.Refactoring;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class IsNumberExampleTest {

    @Test
    public void testValidNumberInput(){
        assertTrue(IsNumberExample.isNumber("12345"));

    }

    @Test
    public void testInvalidInput(){
        assertFalse(IsNumberExample.isNumber("ABC"));
    }

    @Test
    public void testNumberInputLength0(){
        assertFalse(IsNumberExample.isNumber(""));
    }

    @Test
    public void testNumberInputLength1(){
        assertTrue(IsNumberExample.isNumber("1"));
    }

    @Test
    public void testNullInput(){
        NullPointerException npe = assertThrows(NullPointerException.class,() -> IsNumberExample.isNumber(null));
        assertFalse(StringUtils.isBlank(npe.getMessage()));
    }

    @Test
    public void testNumberPositive(){
        assertTrue(IsNumberExample.isNumber("+4711"),"plus sign should be accepted");
    }

    @Test
    public void testNumberNegative(){
        assertTrue(IsNumberExample.isNumber("-4711"),"minus sign should be accepted");
    }


}