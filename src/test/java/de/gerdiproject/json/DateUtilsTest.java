/*
 *  Copyright © 2019 Robin Weiss (http://www.gerdi-project.de/)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package de.gerdiproject.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.gerdiproject.json.datacite.constants.DataCiteDateConstants;

/**
 * This class provides test cases for the {@linkplain DateUtils} class.
 * Multiple runs with varying input dates are executed in order to cover
 * a wide range of possible date strings.
 *
 * @author Robin Weiss
 *
 */
@RunWith(Parameterized.class)
public class DateUtilsTest
{
    private final static String DATE_FORMAT = "%3$04d-%2$02d-%1$02d";
    private final static String DATE_FORMAT_MISSING_DAY = "%3$04d-%2$02d-01";
    private final static String DATE_FORMAT_MISSING_MONTH = "%3$04d-01-01";


    private final int day;
    private final int month;
    private final int year;
    private final long unixTimestamp;


    @Parameters(name = "date: {0}")
    public static Object[] getParameters()
    {
        return new Object[] {"03.02.1582", "03.02.1583", "23.10.3333"};
    }


    /**
     * Constructor that uses a date string  in order to generate
     * raw strings to be parsed and the expected outcome thereof.
     *
     * @param dateString a date string in "dd.mm.yyyy"-format
     */
    public DateUtilsTest(String dateString)
    {
        final String[] splitDate = dateString.split("\\.");

        final int dayTemp = Integer.parseInt(splitDate[0]);
        final int monthTemp = Integer.parseInt(splitDate[1]);
        this.year = Integer.parseInt(splitDate[2]);

        // swap day and month for catching wild switcharoos
        if (monthTemp > 12) {
            this.month = dayTemp;
            this.day = monthTemp;
        } else {
            this.month = monthTemp;
            this.day = dayTemp;
        }

        final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(DataCiteDateConstants.STANDARD_TIMEZONE));
        cal.set(year, month - 1, day, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        this.unixTimestamp = cal.getTimeInMillis();
    }


    @Test
    public void testUnixTimestampToInstant()
    {
        final Instant unixTimstampDate = DateUtils.unixTimestampToInstant(unixTimestamp);

        assertEquals(
            String.format("The method unixTimestampToInstant(%d) should return a date with the correct timestamp; ", unixTimestamp),
            unixTimestamp,
            unixTimstampDate.toEpochMilli());
    }


    @Test
    public void testDateParsingNull()
    {
        final String dateToParse = null;

        assertNull("The method parseDate(null) returned the wrong value;",
                   DateUtils.parseDate(dateToParse));
    }


    @Test
    public void testDateParsingEmptyString()
    {
        final String dateToParse = "";

        assertNull("The method parseDate(\"\") returned the wrong value;",
                   DateUtils.parseDate(dateToParse));
    }


    @Test
    public void testDateParsingStringWithoutDate()
    {
        final String dateToParse = "The quick brown fox jumps over the lazy unit tester!";

        assertNull(String.format("The method parseDate(%s) returned the wrong value;", dateToParse),
                   DateUtils.parseDate(dateToParse));
    }


    @Test
    public void testDateParsingZeroDay()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%d %d %d", 0, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingZeroMonth()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%d %d %d", day, 0, year);

        final String expectedDate;

        // zeroes are ignored, if we have a digit that could be a month, assume we have month and year
        if (day <= 12)
            expectedDate = String.format(DATE_FORMAT_MISSING_DAY, month, day, year);
        else
            expectedDate = String.format(DATE_FORMAT_MISSING_MONTH, day, month, year);

        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingOverNineThousaaaaaand()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%d %d %d", day, month, 10000);

        assertNull("The method parseDate() should return null if the year is too big;",
                   DateUtils.parseDate(dateToParse));
    }


    @Test
    public void testDateParsingDayMonthYear1()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%d.%d.%d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingDayMonthYear2()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%d %d %d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingDayMonthYear3()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%d/%d/%d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingDayMonthYear4()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%d-%d-%d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingDayMonthYear5()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td %1$tb %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingDayMonthYear6()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%02d %02d %04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingDayMonthYear7()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%02d/%02d/%04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingDayMonthYear8()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%02d-%02d-%04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonthDay1()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d.%2$d.%1$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonthDay2()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d %2$d %1$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonthDay3()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d/%2$d/%1$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonthDay4()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d-%2$d-%1$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonthDay5()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d.%2$02d.%1$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonthDay6()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d %2$02d %1$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonthDay7()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d/%2$02d/%1$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonthDay8()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d-%2$02d-%1$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthYear1()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$d.%3$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthYear2()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$d %3$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthYear3()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$d/%3$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthYear4()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$d-%3$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthYear5()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$02d.%3$04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthYear6()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$02d %3$04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthYear7()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$02d/%3$04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthYear8()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$02d-%3$04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonth1()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d.%2$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonth2()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d %2$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonth3()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d/%2$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonth4()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d-%2$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonth5()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d.%2$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonth6()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d %2$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonth7()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d/%2$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYearMonth8()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d-%2$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingYear()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_MONTH, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthName1()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td. %1$tB %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthName2()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td. %1$tB, %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthName3()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td %1$tB, %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthName4()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td %1$tB %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthName5()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td-%1$tB-%1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthName6()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tB, %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthName7()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tB %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingMonthName8()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tB-%1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingShortMonthName1()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td. %1$tb %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingShortMonthName2()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td. %1$tb, %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingShortMonthName3()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td %1$tb, %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingShortMonthName4()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td-%1$tb-%1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingShortMonthName5()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td. %1$tb. %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingShortMonthName6()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tb, %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingShortMonthName7()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tb %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingShortMonthName8()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tb-%1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingShortMonthName9()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tb. %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingContinuousText01()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "Monthly time series starting %1$tB of %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingContinuousText02()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "Annual data for the period %1$tY onwards", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_MONTH, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingContinuousText03()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "Monthly time series starting on the %1$test %1$tB %1$tY, my man", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingContinuousText04()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "Monthly time series starting on the %1$tend %1$tB %1$tY, my man", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingContinuousText05()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "Monthly time series starting on the %1$terd %1$tB %1$tY, my man", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingContinuousText06()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "Monthly time series starting on the %1$teth %1$tB %1$tY, my man", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingContinuousText07()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "bla bla bla, %3$d foo foo %2$d bar %1$d; finito.", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingContinuousText08()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "bla bla bla, %2$d bar %3$d; finito.", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingContinuousText09()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "bla bla bla, %3$d bar %2$d; finito.", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingContinuousText10()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "bla bla bla, %d foo foo %d bar %d; finito.", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingIsoDate1()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d-%2$02d-%1$02dT12:34:56Z", day, month, year);

        final Calendar parsedCalendar = DatatypeConverter.parseDateTime(dateToParse);
        parsedCalendar.setTimeZone(TimeZone.getTimeZone(DataCiteDateConstants.STANDARD_TIMEZONE));
        final Instant expectedDate = parsedCalendar.toInstant();

        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingIsoDate2()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34:56.789Z", day, month, year);
        final Instant expectedDate = ZonedDateTime.parse(dateToParse).toInstant();

        assertDateEquals(expectedDate, dateToParse);
    }

    @Test
    public void testDateParsingIsoDate3()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34Z", day, month, year);
        final Instant expectedDate = ZonedDateTime.parse(dateToParse).toInstant();

        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingIsoDate4()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34:56.789+02", day, month, year);
        final Instant expectedDate = ZonedDateTime.parse(dateToParse, DataCiteDateConstants.ISO8601_FORMATTER).toInstant();

        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingIsoDate5()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34:56.789-02", day, month, year);
        final Instant expectedDate = ZonedDateTime.parse(dateToParse, DataCiteDateConstants.ISO8601_FORMATTER).toInstant();

        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingIsoDate6()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34:56+0230", day, month, year);
        final Instant expectedDate = ZonedDateTime.parse(dateToParse, DataCiteDateConstants.ISO8601_FORMATTER).toInstant();

        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingIsoDate7()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34:56+02:30", day, month, year);
        final Instant expectedDate = Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(dateToParse));

        assertDateEquals(expectedDate, dateToParse);
    }

    @Test
    public void testDateParsingIsoDate8()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34:56.789-02:30", day, month, year);
        final Instant expectedDate = Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(dateToParse));

        assertDateEquals(expectedDate, dateToParse);
    }

    @Test
    public void testDateParsingIsoDate9()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34", day, month, year);
        final Instant expectedDate = Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(dateToParse + 'Z'));

        assertDateEquals(expectedDate, dateToParse);
    }

    @Test
    public void testDateParsingWithHtmlTags1()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "<p><i>%02d.%02d.%04d</i><p>", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingWithHtmlTags2()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "<p><i>%2$02d.%3$04d</i><p>", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    /**
     * This method parses a date string via {@linkplain DateUtils} and compares it to an expected
     * date using an assertion.
     *
     * @param expectedDateString the expected date string in "dd.mm.yyyy" format
     * @param dateToParse a raw string that is to be parsed via {@linkplain DateUtils}
     */
    private void assertDateEquals(final String expectedDateString, final String dateToParse)
    {
        final Calendar parsedCalendar = DatatypeConverter.parseDateTime(expectedDateString);
        parsedCalendar.setTimeZone(TimeZone.getTimeZone(DataCiteDateConstants.STANDARD_TIMEZONE));
        final Instant expectedDate = parsedCalendar.toInstant();
        assertDateEquals(expectedDate, dateToParse);
    }


    /**
     * This method parses a date string via {@linkplain DateUtils} and compares it to an expected
     * date using an assertion.
     *
     * @param expectedDate the expected date as {@linkplain Instant}
     * @param dateToParse a raw string that is to be parsed via {@linkplain DateUtils}
     */
    private void assertDateEquals(final Instant expectedDate, final String dateToParse)
    {
        final Instant parsedInstant = DateUtils.parseDate(dateToParse);

        assertEquals(String.format(Locale.ENGLISH, "The method parseDate(\"%s\") returned the wrong value;", dateToParse),
                     expectedDate,
                     parsedInstant);
    }
}
