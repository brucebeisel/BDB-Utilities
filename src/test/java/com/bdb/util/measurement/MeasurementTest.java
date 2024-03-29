/* 
 * Copyright (C) 2016 Bruce Beisel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.bdb.util.measurement;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class MeasurementTest
{
    @Test
    public void testMeasurement()
    {
        TestDistance m = new TestDistance(10.0, TestDistance.Unit.FEET);
        
        assertEquals(m.get(TestDistance.Unit.FEET), 10.0, 0.01);
        
        Measurement m2 = new TestDistance(m);
        
        if (m.compareTo(m2) != 0)
            Assert.fail("Copy constructor failed");
    }

    @Test
    public void testGet()
    {
    	double expected = 25.4;
        TestDistance d = new TestDistance(1.0, TestDistance.Unit.INCHES);
        double value = d.get(TestDistance.Unit.MILLIMETERS);
        Assert.assertEquals(expected, value, 0.0);
    }


    @Test
    public void testToString()
    {
        TestDistance d = new TestDistance(1.5, TestDistance.Unit.MILLIMETERS);
        TestDistance.setDefaultUnit(TestDistance.Unit.MILLIMETERS);
        String s = d.toString();
        Assert.assertEquals(s, "1.5");
    }

    @Test
    public void defaultUnit()
    {
        TestDistance.setDefaultUnit(TestDistance.Unit.MILLIMETERS);
        assertEquals(TestDistance.getDefaultUnit(), TestDistance.Unit.MILLIMETERS);
        TestDistance.setDefaultUnit(TestDistance.Unit.METERS);
        assertEquals(TestDistance.getDefaultUnit(), TestDistance.Unit.METERS);
        TestDistance.setDefaultUnit(TestDistance.Unit.MILLIMETERS);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void defaultUnitFailure()
    {
        Measurement.setDefaultUnit(String.class, TestDistance.Unit.INCHES);
    }
    
    @Test
    public void add()
    {
        TestDistance.setDefaultUnit(TestDistance.Unit.INCHES);
        TestDistance d1 = new TestDistance(1.0);
        TestDistance d2 = new TestDistance(2.0);
        
        TestDistance d3 = d1.add(d2);
        
        TestDistance d4 = new TestDistance(3.0);
        
        System.out.println(d3.equals(d4));
        
        System.out.println("d3 = " + d3 + "  d4 = " + d4);
        
        Assert.assertEquals(d4.toString(), d3.toString());
    }
    
    @Test
    public void subtract() {
        TestDistance d1 = new TestDistance(5.0);
        TestDistance d2 = new TestDistance(2.0);
        
        TestDistance d3 = d1.subtract(d2);
        
        TestDistance d4 = new TestDistance(3.0);
        
        System.out.println(d3.equals(d4));
        
        System.out.println("d3 = " + d3 + "  d4 = " + d4);
        
        Assert.assertEquals(d4, d3);
    }

    @Test
    public void testEquals() {
        TestDistance d1 = new TestDistance(5.00, TestDistance.Unit.MILLIMETERS);
        TestDistance d2 = new TestDistance(5.004, TestDistance.Unit.MILLIMETERS);
        Assert.assertTrue(d1.equals(d2));

        TestDistance d3 = new TestDistance(5.005, TestDistance.Unit.MILLIMETERS);
        Assert.assertFalse(d1.equals(d3));

    }
}
