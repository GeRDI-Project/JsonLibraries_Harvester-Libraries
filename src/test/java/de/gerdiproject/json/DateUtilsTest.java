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
    public void testDateParsing01()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%d.%d.%d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing02()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%d %d %d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing03()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%d/%d/%d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing04()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%d-%d-%d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing05()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td %1$tb %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing06()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%02d %02d %04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing07()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%02d/%02d/%04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing08()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%02d-%02d-%04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing09()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d.%1$d.%2$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing10()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d %1$d %2$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing11()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d/%1$d/%2$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing12()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d-%1$d-%2$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing13()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d.%1$02d.%2$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing14()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d %1$02d %2$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing15()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d/%1$02d/%2$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing16()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d-%1$02d-%2$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing17()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "<p><i>%02d.%02d.%04d</i><p>", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing18()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "bla bla bla, %d foo foo %d bar %d; finito.", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing19()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "bla bla bla, %3$d foo foo %1$d bar %2$d; finito.", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing20()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$d.%3$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing21()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$d %3$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing22()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$d/%3$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing23()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$d-%3$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing24()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$02d.%3$04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing25()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$02d %3$04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing26()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$02d/%3$04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing27()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%2$02d-%3$04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing28()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d.%2$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing29()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d %2$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing30()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d/%2$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing31()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$d-%2$d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing32()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d.%2$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing33()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d %2$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing34()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d/%2$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing35()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d-%2$02d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing36()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "<p><i>%2$02d.%3$04d</i><p>", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing37()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "bla bla bla, %2$d bar %3$d; finito.", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing38()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "bla bla bla, %3$d bar %2$d; finito.", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing39()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d", day, month, year);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_MONTH, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing41()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td. %1$tB %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing42()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td. %1$tB, %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing43()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td %1$tB, %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing44()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td %1$tB %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing45()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td-%1$tB-%1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing46()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td. %1$tb %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing47()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td. %1$tb, %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing48()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td %1$tb, %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing49()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td-%1$tb-%1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing50()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$td. %1$tb. %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing51()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tB, %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing52()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tB %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing53()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tB-%1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing54()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tb, %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing55()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tb %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing56()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tb-%1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing57()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%1$tb. %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing58()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "Monthly time series starting %1$tB of %1$tY", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_DAY, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing59()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "Annual data for the period %1$tY onwards", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT_MISSING_MONTH, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing60()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "Monthly time series starting on the %1$test %1$tB %1$tY, my man", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing61()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "Monthly time series starting on the %1$tend %1$tB %1$tY, my man", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing62()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "Monthly time series starting on the %1$terd %1$tB %1$tY, my man", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsing63()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "Monthly time series starting on the %1$teth %1$tB %1$tY, my man", unixTimestamp);
        final String expectedDate = String.format(Locale.ENGLISH, DATE_FORMAT, day, month, year);
        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingIso()
    {
        final String dateToParse = String.format(Locale.ENGLISH, "%3$04d-%2$02d-%1$02dT12:34:56Z", day, month, year);

        final Calendar parsedCalendar = DatatypeConverter.parseDateTime(dateToParse);
        parsedCalendar.setTimeZone(TimeZone.getTimeZone(DataCiteDateConstants.STANDARD_TIMEZONE));
        final Instant expectedDate = parsedCalendar.toInstant();

        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingIsoWithMillis()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34:56.789Z", day, month, year);
        final Instant expectedDate = ZonedDateTime.parse(dateToParse).toInstant();

        assertDateEquals(expectedDate, dateToParse);
    }

    @Test
    public void testDateParsingIsoWithoutSeconds()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34Z", day, month, year);
        final Instant expectedDate = ZonedDateTime.parse(dateToParse).toInstant();

        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingIsoWithTimezone()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34:56.789+02", day, month, year);
        final Instant expectedDate = ZonedDateTime.parse(dateToParse, DataCiteDateConstants.ISO8601_FORMATTER).toInstant();

        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingIsoWithTimezone3()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34:56.789-02", day, month, year);
        final Instant expectedDate = ZonedDateTime.parse(dateToParse, DataCiteDateConstants.ISO8601_FORMATTER).toInstant();

        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingIsoWithTimezone2()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34:56+0230", day, month, year);
        final Instant expectedDate = ZonedDateTime.parse(dateToParse, DataCiteDateConstants.ISO8601_FORMATTER).toInstant();

        assertDateEquals(expectedDate, dateToParse);
    }


    @Test
    public void testDateParsingIsoWithTimezone4()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34:56+02:30", day, month, year);
        final Instant expectedDate = Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(dateToParse));

        assertDateEquals(expectedDate, dateToParse);
    }

    @Test
    public void testDateParsingIsoWithTimezone5()
    {
        final String dateToParse = String.format("%3$04d-%2$02d-%1$02dT12:34:56.789-02:30", day, month, year);
        final Instant expectedDate = Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(dateToParse));

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

        final Instant parsedInstant = DateUtils.parseDate(dateToParse);

        assertEquals(String.format(Locale.ENGLISH, "The method parseDate(\"%s\") returned the wrong value;", dateToParse),
                     expectedDate,
                     parsedInstant);
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
