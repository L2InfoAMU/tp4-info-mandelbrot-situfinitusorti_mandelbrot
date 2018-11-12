package mandelbrot;

import org.junit.jupiter.api.Test;

import static mandelbrot.Complex.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComplexTest {
    private final Complex onePlusI = new Complex(1,1);
    private final Complex minusI = new Complex(0,-1);
    private final Complex minusOne = new Complex(-1,0);
    private final Complex oneMinusI = new Complex(1, -1);
    private final Complex twoI = new Complex(0,2);
    private final Complex two = new Complex(2,0);
    private final double real = -12;
    private final double imaginary = 10;


    @Test
    void testConstructor(){
        assertEquals(0., twoI.real, Helpers.EPSILON);
        assertEquals(2., twoI.imaginary, Helpers.EPSILON);
        assertEquals(1., oneMinusI.real, Helpers.EPSILON);
        assertEquals(-1., oneMinusI.imaginary, Helpers.EPSILON);
        assertEquals(2., two.real, Helpers.EPSILON);
        assertEquals(0., two.imaginary, Helpers.EPSILON);
    }

    @Test
    void testGetReal(){
        assertEquals(2., two.getReal(), Helpers.EPSILON);
        assertEquals(1., oneMinusI.getReal(), Helpers.EPSILON);
        assertEquals(-1., new Complex(-1,1).getReal(), Helpers.EPSILON);
        assertEquals(real, new Complex(real, imaginary).getReal(), Helpers.EPSILON);
    }

    @Test
    void testGetImaginary(){
        assertEquals(2., twoI.getImaginary(), Helpers.EPSILON);
        assertEquals(1., new Complex(-1, 1).getImaginary(), Helpers.EPSILON);
        assertEquals(-1., oneMinusI.getImaginary(), Helpers.EPSILON);
        assertEquals(imaginary, new Complex(real, imaginary).getImaginary(), Helpers.EPSILON);
    }

    @Test
    void testOne(){
        assertEquals(1., ONE.getReal());
        assertEquals(0., ONE.getImaginary());
    }

    @Test
    void testI(){
        assertEquals(0, Complex.I.getReal());
        assertEquals(1, Complex.I.getImaginary());
    }

    @Test
    void testZero(){
        assertEquals(0, Complex.ZERO.getReal());
        assertEquals(0, Complex.ZERO.getImaginary());
    }

    @Test
    void testNegate(){
        assertEquals(minusOne, ONE.negate());
        assertEquals(Complex.I, minusI.negate());
        assertEquals(new Complex(-1, 1), oneMinusI.negate());
        assertEquals(new Complex(real, imaginary), new Complex(-real,-imaginary).negate());
    }

    @Test
    void testReciprocal(){
        assertEquals(ONE, ONE.reciprocal());
        assertEquals(Complex.I, minusI.reciprocal());
        assertEquals(new Complex(0.5,0), two.reciprocal());
        assertEquals(new Complex(0.5,0.5), oneMinusI.reciprocal());
    }

    @Test
    void testReciprocalOfZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ZERO.reciprocal());
    }

    @Test
    void testSubtract(){
        assertEquals(minusOne, Complex.ZERO.subtract(ONE));
        assertEquals(oneMinusI, ONE.subtract(Complex.I));
        assertEquals(new Complex(real-1,imaginary-1),
                new Complex(real, imaginary).subtract(onePlusI));
    }

    @Test
    void testDivide(){
        assertEquals(onePlusI, onePlusI.divide(ONE));
        assertEquals(new Complex(0.5, 0), ONE.divide(two));
        assertEquals(minusI,oneMinusI.divide(onePlusI));
    }

    @Test
    void testDivideByZero(){
        assertThrows(ArithmeticException.class, ()-> ONE.divide(Complex.ZERO));
    }

    @Test
    void testConjugate(){
        assertEquals(Complex.ZERO, Complex.ZERO.conjugate());
        assertEquals(ONE, ONE.conjugate());
        assertEquals(onePlusI, oneMinusI.conjugate());
        assertEquals(new Complex(real, -imaginary), new Complex(real, imaginary).conjugate());
    }

    @Test
    void testRotation(){
        assertEquals(Complex.I, Complex.rotation(Math.PI/2));
        assertEquals(minusI, Complex.rotation(-Math.PI/2));
        assertEquals(ONE, Complex.rotation(0));
        assertEquals(new Complex(Math.sqrt(2)/2., Math.sqrt(2)/2.),
                Complex.rotation(Math.PI/4));
        assertEquals(new Complex(1./2., Math.sqrt(3)/2.),
                Complex.rotation(Math.PI/3));
    }

    @Test
    void testToString(){
        assertEquals("Complex{real=1.0, imaginary=-1.0}", oneMinusI.toString());
        assertEquals("Complex{real="+real+", imaginary="+imaginary+"}", new Complex(real, imaginary).toString());
    }

    @Test
    void testHashCode() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testComplexReal() {
        Complex c1 = real(real);
        Complex c2 = real(-imaginary);
        assertEquals(real,c1.getReal());
        assertEquals(0.0,c1.getImaginary());
        assertEquals(-imaginary,c2.getReal());
        assertEquals(0.0,c2.getImaginary());
    }

    @Test
    void testAdd() {
        Complex c1 = onePlusI.add(oneMinusI);
        Complex c2 = oneMinusI.add(minusI);
        assertEquals(2.0,c1.getReal());
        assertEquals(0,c1.getImaginary());
        assertEquals(1.0,c2.getReal());
        assertEquals(-2.0,c2.getImaginary());
    }

    @Test
    void testsubtract() {
        Complex c1 = onePlusI.subtract(oneMinusI);
        Complex c2 = oneMinusI.subtract(minusI);
        assertEquals(0.0,c1.getReal());
        assertEquals(2,c1.getImaginary());
        assertEquals(1.0,c2.getReal());
        assertEquals(0.0,c2.getImaginary());
    }

    @Test
    void testMultiply() {
        Complex c1 = onePlusI.multiply(oneMinusI);
        Complex c2 = onePlusI.multiply(ZERO);
        Complex c3 = onePlusI.multiply(ONE);
        Complex c4 = onePlusI.multiply(minusOne);
        assertEquals(2.0,c1.getReal());
        assertEquals(0.0,c1.getImaginary());
        assertEquals(0.0,c2.getReal());
        assertEquals(0.0,c2.getImaginary());
        assertEquals(1.0,c3.getReal());
        assertEquals(1.0,c3.getImaginary());
        assertEquals(-1.0,c4.getReal());
        assertEquals(-1.0,c4.getImaginary());
    }


    @Test
    void testSquaredModulus() {
        Complex c1 = new Complex(real,imaginary);
        assertEquals(real * real + imaginary * imaginary,c1.squaredModulus());
    }


    @Test
    void testModulus() {
        Complex c1 = new Complex(real,imaginary);
        assertEquals(Math.sqrt(real * real + imaginary * imaginary),c1.modulus());
    }

    @Test
    void testPow() {
        Complex c1 = onePlusI.pow(3);
        Complex c2 = ONE.pow(5);
        Complex c3 = twoI.pow(4);
        Complex c4 = twoI.pow(0);
        assertEquals(-2,c1.getReal());
        assertEquals(2,c1.getImaginary());
        assertEquals(1,c2.getReal());
        assertEquals(0,c2.getImaginary());
        assertEquals(16,c3.getReal());
        assertEquals(0,c3.getImaginary());
        assertEquals(1,c4.getReal());
        assertEquals(0,c4.getImaginary());
    }


    @Test
    void testScale() {
        Complex c1 = onePlusI.scale(-3);
        Complex c2 = ONE.scale(5);
        Complex c3 = twoI.scale(4);
        assertEquals(-3,c1.getReal());
        assertEquals(-3,c1.getImaginary());
        assertEquals(5,c2.getReal());
        assertEquals(0,c2.getImaginary());
        assertEquals(0,c3.getReal());
        assertEquals(8,c3.getImaginary());
    }


    @Test
    void testEquals() {
        Complex c1 = onePlusI;
        Complex c2 = c1;
        Complex c3 = onePlusI;
        Complex c4 = minusI;
        Complex c5 = I;
        assertEquals(c1,c2);
        assertEquals(c1,c3);
        assertEquals(c2,c3);
        assertNotEquals(c1,c4);
        assertNotEquals(c5,c4);
    }
}