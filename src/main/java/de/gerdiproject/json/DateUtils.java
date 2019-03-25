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

import de.gerdiproject.harvest.utils.StringCleaner;
import de.gerdiproject.json.datacite.Date;
import de.gerdiproject.json.datacite.DateRange;
import de.gerdiproject.json.datacite.abstr.AbstractDate;
import de.gerdiproject.json.datacite.constants.DataCiteDateConstants;
import de.gerdiproject.json.datacite.enums.DateType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This utility class offers static methods for processing dates.
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
    public static Instant unixTimestampToInstant(long epochMilli)
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
    public static Instant parseDate(String dateString)
    {
        // return if string is null
        if (dateString == null)
            return null;

        final String cleanString =  StringCleaner.clean(dateString);
        final int stringLength = cleanString.length();

        // return if string is empty
        if (stringLength == 0)
            return null;

        // assume the date is an ISO-8601 if it starts and ends with a digit or a Z
        if (stringLength > 10 && Character.isDigit(cleanString.charAt(0))
            && (Character.isDigit(cleanString.charAt(stringLength - 1))
                || cleanString.charAt(stringLength - 1) == 'Z')) {

            // use the standard ISO-8601 formatter
            try {
                return Instant.from(DateTimeFormatter.ISO_DATE_TIME.parse(cleanString));

            } catch (DateTimeException ignore) { } // NOPMD exception is to be expected

            // fallback: use a custom ISO-8601 formatter for handling zones without colons
            try {
                return Instant.from(DataCiteDateConstants.ISO8601_FORMATTER.parse(cleanString));

            } catch (DateTimeException ignore) { } // NOPMD exception is to be expected

            // if all ISO-formatting failed, proceed as usual
        }

        // split string up into its components
        final String[] dateSegments = cleanString.split(DataCiteDateConstants.DATE_SPLIT_REGEX);

        int day = 1;
        int month = 1;
        int year = 0;
        boolean hasDay = false;
        boolean hasMonth = false;
        boolean hasYear = false;

        // convert each segment of the split string to a day, month, or year
        for (final String s : dateSegments) {

            // check if the segment is a number
            if (Character.isDigit(s.charAt(0))) {
                final int num;

                try {
                    // handle 1st, 2nd, 3rd, 4th, ...
                    if (s.length() > 4 || !Character.isDigit(s.charAt(s.length() - 1))) {
                        final Matcher matcher = DataCiteDateConstants.NUMBERS_PATTERN.matcher(s);

                        if (matcher.find())
                            num = Integer.parseInt(matcher.group(1));
                        else
                            continue;
                    } else
                        num = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    // skip this number
                    continue;
                }

                // zero values are eligible only as year, which defaults to 0 anyway
                if (num == 0 || num > 9999)
                    continue;

                // if it is higher than 31, it is a year
                if (num > 31) {
                    year = num;
                    hasYear = true;
                }

                // if it is higher than 12, it is a day
                else if (num > 12) {
                    // if we already have a day, it must be a month, so swap day and month
                    if (hasDay) {
                        month = day;
                        hasMonth = true;
                    }

                    day = num;
                    hasDay = true;
                }
                // if we already have a month or if we have nothing yet, it is a day
                else if (hasMonth || !hasYear && !hasDay) {
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
                } catch (DateTimeParseException e) { // NOPMD
                    // thrown if the string was no month after all, continue parsing
                }
            }

            // abort early if all components were retrieved
            if (hasMonth && hasDay && hasYear)
                break;
        }

        // abort if the year is missing
        if (!hasYear)
            return null;

        // if we extracted a day, but no month, it was probably a month after all
        if (hasDay && !hasMonth) {
            // if it is definitely a day, but we have no month, it was probably not a day after all
            if (day > 12)
                day = 1;
            else {
                month = day;
                day = 1;
            }
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
     * Parses an {@linkplain AbstractDate} from a specified date string.
     *
     * @param dateString a raw string that contains a {@linkplain Date} or {@linkplain DateRange}
     * @param type the {@linkplain DateType} of the retrieved date
     *
     * @return a {@linkplain Date}, {@linkplain DateRange}, or null if no date could be parsed
     */
    public static AbstractDate parseAbstractDate(String dateString, DateType type)
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
     * Parses a date range using a set of common separators defined in {@linkplain DataCiteDateConstants}.
     *
     * @param dateString a raw {@linkplain String} that contains a date range
     *
     * @return an {@linkplain Instant} array that contains the start- and end date,
     * or null if no date range could be parsed
     */
    public static Instant[] parseDateRange(String dateString)
    {
        Instant[] dates = null;

        // check if string represents a range, using any of the common separators
        for (final String separator : DataCiteDateConstants.DATE_RANGE_SEPARATORS) {
            dates = parseDateRange(dateString, separator);

            if (dates != null)
                break;
        }

        return dates;
    }


    /**
     * Parses a date range considering a specified separator.
     *
     * @param dateString a raw {@linkplain String} that contains a date range
     * @param separator a sub-string that separates the beginning from the end date
     *
     * @return an {@linkplain Instant} array that contains the start- and end date,
     * or null if no date range could be parsed
     */
    public static Instant[] parseDateRange(String dateString, String separator)
    {
        final Instant[] dates = new Instant[2];

        // check if the string contains the separator
        if (dateString.contains(separator)) {
            final String[] dateRangeElements = dateString.split(separator);

            // check if there is really just one separator
            if (dateRangeElements.length == 2) {
                dates[0] = parseDate(dateRangeElements[0]);
                dates[1] = parseDate(dateRangeElements[1]);
            }

            // edge case: the range-separator is the same symbol as the day/month/year-separator
            else if (dateRangeElements.length > 2 && dateRangeElements.length % 2 == 0) {
                // determine the middle of the string for a proper separation
                final int separatorLength = separator.length();
                int halfLength = 0;

                for (int i = 0, len = dateRangeElements.length / 2; i < len; i++)
                    halfLength += dateRangeElements[i].length() + separatorLength;

                dates[0] = parseDate(dateString.substring(0, halfLength));
                dates[1] = parseDate(dateString.substring(halfLength));

            }
        }

        // return null if array is empty
        return dates[0] != null || dates[1] != null ? dates : null;
    }
}
