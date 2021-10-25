package com.medolia.demo.jvm.doc;

/**
 * @author lbli@trip.com
 * @date 2021/9/8
 */
public class TableDoc {
    /**
     * Rounding mode to round away from zero.  Always increments the
     * digit prior to a non-zero discarded fraction.  Note that this
     * rounding mode never decreases the magnitude of the calculated
     * value.
     *
     *<p>Example:
     *<table border>
     * <caption><b>Rounding mode UP Examples</b></caption>
     *<tr valign=top><th>Input Number</th>
     *    <th>Input rounded to one digit<br> with {@code UP} rounding
     *<tr align=right><td>5.5</td>  <td>6</td>
     *<tr align=right><td>2.5</td>  <td>3</td>
     *<tr align=right><td>1.6</td>  <td>2</td>
     *<tr align=right><td>1.1</td>  <td>2</td>
     *<tr align=right><td>1.0</td>  <td>1</td>
     *<tr align=right><td>-1.0</td> <td>-1</td>
     *<tr align=right><td>-1.1</td> <td>-2</td>
     *<tr align=right><td>-1.6</td> <td>-2</td>
     *<tr align=right><td>-2.5</td> <td>-3</td>
     *<tr align=right><td>-5.5</td> <td>-6</td>
     *</table>
     */
    private int UP;
}
