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

import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.regex.Matcher;

import de.gerdiproject.harvest.utils.StringUtils;
import de.gerdiproject.json.datacite.Date;
import de.gerdiproject.json.datacite.DateRange;
import de.gerdiproject.json.datacite.abstr.AbstractDate;
import de.gerdiproject.json.datacite.constants.DataCiteDateConstants;
import de.gerdiproject.json.datacite.enums.DateType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This utility class offers static methods for processing {@linkplain Date}s.
 *
 * @author Robin Weiss
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils
{
    /**
     * Creates an instant using the amount of milliseconds that passed
     * from 01/01/1970 00:00:00 until this date.
     *
     * @param epochMilli the amount of milliseconds between 01/01/1970 00:00:00 and this date
     *
     * @return an {@linkplain Instant} that represents the timestamp
     */
    public static Instant unixTimestampToInstant(final long epochMilli)
    {
        return Instant.ofEpochMilli(epochMilli);
    }


    /**
     * Attempts to parse a date from a {@linkplain String}.
     * The string is stripped of HTML tags and trimmed prior to
     * being divided into parts. Each part is then parsed
     * in order to obtain a year, a month and a day.<br>
     *
     * If a date such as 01/02/2000 is parsed, it is assumed that
     * the first number represents the day.<br>
     *
     * If a date such as 2000/02/01 is parsed, it is assumed that
     * the last number is the day.<br>
     *
     * Month names and abbreviations thereof are parsed in English.
     *
     * @param dateString a text containing a date
     *
     * @return an {@linkplain Instant} or null, if nothing was parsed
     */
    public static Instant parseDate(final String dateString)
    {
        // return if string is null
        if (dateString == null)
            return null;

        final String cleanString =  StringUtils.clean(dateString);

        if (cleanString.isEmpty())
            return null;

        Instant parsedDate = null;

        // check if the date is possibly of ISO-8601 standard
        if (isPotentialIso8601String(cleanString))
            parsedDate = parseIso8601String(cleanString);

        // parse non-ISO-8601 date string
        if (parsedDate == null)
            parsedDate = parseNonStandardDateString(cleanString);

        return parsedDate;
    }


    /**
     * Parses an {@linkplain AbstractDate} from a specified date string.
     *
     * @param dateString a raw string that contains a {@linkplain Date} or {@linkplain DateRange}
     * @param type the {@linkplain DateType} of the retrieved date
     *
     * @return a {@linkplain Date}, {@linkplain DateRange}, or null if no date could be parsed
     */
    public static AbstractDate parseAbstractDate(final String dateString, final DateType type)
    {
        // attempt to parse date range
        AbstractDate date = new DateRange(dateString, type);
        boolean isValidDate = date.clean();

        // fallback: attempt to parse single date
        if (!isValidDate) {
            date =  new Date(dateString, type);
            isValidDate = date.clean();
        }

        // return valid date or null
        return isValidDate ? date : null;
    }


    /**
     * @deprecated Use {@linkplain DateRangeUtils#parseDateRange(String)} instead!
     *
     * @param dateString a raw {@linkplain String} that contains a date range
     *
     * @return an {@linkplain Instant} array that contains the start- and end date,
     * or null if no date range could be parsed
     */
    @Deprecated
    public static Instant[] parseDateRange(final String dateString)
    {
        return DateRangeUtils.parseDateRange(dateString);
    }


    /**
     * @deprecated Use {@linkplain DateRangeUtils#parseDateRange(String, String)} instead!
     *
     * @param dateString a raw {@linkplain String} that contains a date range
     * @param separator a sub-string that separates the beginning from the end date
     *
     * @return an {@linkplain Instant} array that contains the start- and end date,
     * or null if no date range could be parsed
     */
    @Deprecated
    public static Instant[] parseDateRange(final String dateString, final String separator)
    {
        return DateRangeUtils.parseDateRange(dateString, separator);
    }

    /**
     * Checks if a date string roughly complies to ISO-8601.
     *
     * @param dateString the date string that is to be checked
     *
     * @return true if the date string starts with a digit, and ends with a digit or a Z
     */
    private static boolean isPotentialIso8601String(final String dateString)
    {
        final int stringLength = dateString.length();
        final char firstChar = dateString.charAt(0);
        final char lastChar = dateString.charAt(stringLength - 1);

        return stringLength > 10
               && Character.isDigit(firstChar)
               && (lastChar == 'Z' || Character.isDigit(lastChar));
    }


    /**
     * Attempts to parse an ISO-8601 compliant date string.
     *
     * @param dateString the date string that is to be parsed
     *
     * @return a parsed {@linkplain Instant} or null, if the string could not be parsed
     */
    private static Instant parseIso8601String(final String dateString)
    {
        Instant parsedValue;

        // try the standard ISO-8601 formatter
        try {
            parsedValue = Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(dateString));

        } catch (final DateTimeException e1) {

            // fallback: use a custom ISO-8601 formatter for handling zones without colons
            try {
                parsedValue = Instant.from(DataCiteDateConstants.ISO8601_FORMATTER.parse(dateString));

            } catch (final DateTimeException e2) {
                parsedValue = null;
            }
        }

        return parsedValue;
    }

    /**
     * Parses a non-ISO8601 date string.
     *
     * @param dateString the date string that is to be parsed
     *
     * @return a parsed {@linkplain Instant} or null, if the string could not be parsed
     */
    private static Instant parseNonStandardDateString(final String dateString) // NOPMD this is as simple as it gets
    {
        final String[] dateSegments = dateString.split(DataCiteDateConstants.DATE_SPLIT_REGEX);

        int day = 1;
        int month = 1;
        int year = 0;
        boolean hasDay = false;
        boolean hasMonth = false;
        boolean hasYear = false;

        // convert each segment of the split string to a day, month, or year
        for (final String s : dateSegments) {
            // skip empty strings
            if (s.isEmpty())
                continue;

            // check if the segment is a number
            if (Character.isDigit(s.charAt(0))) {
                final int num = parseNumberSegment(s);

                // zero values are eligible only as year, which defaults to 0 anyway
                if (num == 0 || num > 9999)
                    continue;

                // if it is higher than 31, it is a year
                if (num > 31) {
                    year = num;
                    hasYear = true;
                }

                // if it is higher than 12, it is a day
                else if (num > 12 || hasMonth || !hasYear && !hasDay) {
                    // if we already have a day, it must be a month, so swap day and month
                    if (hasDay) {
                        month = day;
                        hasMonth = true;
                    }

                    day = num;
                    hasDay = true;
                }

                // otherwise it is a month
                else {
                    month = num;
                    hasMonth = true;
                }

            } else {
                // if the segment is alphabetical text, check if it describes a month
                try {
                    month = DataCiteDateConstants.MONTH_FORMATTER.parse(s).get(ChronoField.MONTH_OF_YEAR);
                    hasMonth = true;
                } catch (final DateTimeParseException e) { // NOPMD
                    // thrown if the string was no month after all, continue parsing
                }
            }
        }

        // abort if the year is missing
        if (!hasYear)
            return null;

        // if we extracted a day, but no month, it was probably a month after all
        if (hasDay && !hasMonth) {
            month = day;
            day = 1;
        }

        // if the month is out of range, only the year is precise enough
        if (month > 12) {
            month = 1;
            day = 1;
        }

        // assemble time
        final ZonedDateTime zdt =
            ZonedDateTime.of(
                year,
                month,
                day,
                0, 0, 0, 0,
                DataCiteDateConstants.Z_ZONE_ID);

        return zdt.toInstant();
    }


    /**
     * Parses a number segment from a date string. Numbers such as
     * 1st, 2nd, 3rd, and 4th are considered.
     *
     * @param dateSegment a part of a date string that starts with a number
     *
     * @return a parsed number or 0, if nothing could be parsed
     */
    private static int parseNumberSegment(final String dateSegment)
    {
        int parsedNumber = 0;

        final int segmentLength = dateSegment.length();

        try {
            // handle day numbers such as: 1st, 2nd, 3rd, 4th, ...
            if (segmentLength > 4 || !Character.isDigit(dateSegment.charAt(segmentLength - 1))) {
                final Matcher matcher = DataCiteDateConstants.NUMBERS_PATTERN.matcher(dateSegment);

                if (matcher.find())
                    parsedNumber = Integer.parseInt(matcher.group(1));
            }
            // handle regular numbers
            else
                parsedNumber = Integer.parseInt(dateSegment);
        } catch (final NumberFormatException e) { // NOPMD number is null by default
        }

        return parsedNumber;
    }
}
