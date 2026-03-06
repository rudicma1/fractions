package pro1;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {

    @org.junit.jupiter.api.Test
    void simpleFraction() {
        Main.Fraction r = Main.calculate("3/17");
        assertEquals("3/17", r.toString());
    }

    @org.junit.jupiter.api.Test
    void reduction() {
        Main.Fraction r = Main.calculate("30/45");
        assertEquals("2/3", r.toString());
    }

    @org.junit.jupiter.api.Test
    void percent() {
        Main.Fraction r = Main.calculate("50%");
        assertEquals("1/2", r.toString());
    }

    @org.junit.jupiter.api.Test
    void addFractions() {
        Main.Fraction r = Main.calculate("1/2 + 1/4");
        assertEquals("3/4", r.toString());
    }

    @org.junit.jupiter.api.Test
    void addPercents() {
        Main.Fraction r = Main.calculate("25% + 25%");
        assertEquals("1/2", r.toString());
    }

    @org.junit.jupiter.api.Test
    void mixedExpression() {
        Main.Fraction r = Main.calculate("1/2 + 25%");
        assertEquals("3/4", r.toString());
    }
}