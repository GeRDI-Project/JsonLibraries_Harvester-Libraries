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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.gerdiproject.harvest.utils.StringCleaner;
import de.gerdiproject.json.datacite.constants.DataCiteDateConstants;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
    private static final Calendar CALENDAR = initCalendar();


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
     * in order to obtain a year, a month and a day.
     *
     * Days are prioritized, meaning if a date such as 01/02/2000
     * is parsed, it is assumed that the first number represents the day.
     * Month names and abbreviations thereof are parsed in English.
     *
     * @param dateString a text containing a date
     *
     * @return an {@linkplain Instant} or null, if nothing was parsed
     */
    public static Instant parseDate(String dateString)
    {
        // clean string and split it up into its components
        final String[] dateSegments = StringCleaner
                                      .clean(dateString)
                                      .split(DataCiteDateConstants.DATE_SPLIT_REGEX);

        int day = Integer.MIN_VALUE;
        int month = Integer.MIN_VALUE;
        int year = Integer.MIN_VALUE;
        boolean hasDay = false;
        boolean hasMonth = false;
        boolean hasYear = false;

        // convert each segment of the split string to a day, month, or year
        for (int i = 0; i < dateSegments.length; i++) {
            final String s = dateSegments[i];

            // check if the
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

                if (num > 31) {
                    year = num;
                    hasYear = true;
                } else if (!hasDay) {
                    day = num;
                    hasDay = true;
                } else {
                    if (num > 12) {
                        month = day;
                        day = num;
                    } else
                        month = num;

                    hasMonth = true;
                }
            } else {
                // if the segment is alphabetical text, check if it describes a month
                for (final SimpleDateFormat format : DataCiteDateConstants.MONTH_FORMATS) {
                    try {
                        CALENDAR.setTime(format.parse(s));
                        month = CALENDAR.get(Calendar.MONTH) + 1;
                        hasMonth = true;
                    } catch (ParseException e) {
                        // nothing to do, carry on
                    }
                }
            }

            // abort early if all components were retrieved
            if (hasMonth && hasDay && hasYear)
                break;
        }

        // abort if the year is missing
        if (!hasYear) {
            LOGGER.warn(String.format(DataCiteDateConstants.PARSE_ERROR, dateString));
            return null;
        }

        // if we extracted a day, but no month, it was probably a month after all
        if (hasDay && !hasMonth) {
            month = day;
            hasDay = false;
            hasMonth = true;
        }

        // fill calendar with the parsed data
        CALENDAR.set(Calendar.YEAR, year);
        CALENDAR.set(Calendar.MONTH, hasMonth ? month - 1 : 0);
        CALENDAR.set(Calendar.DAY_OF_MONTH, hasDay ? day : 1);

        // convert and return calendar as Instant
        return CALENDAR.toInstant();
    }


    /**
     * Creates a {@linkplain Calendar} instance that is used as a tool to assemble parsed
     * date information.
     *
     * @return a {@linkplain Calendar} with every value set to zero
     */
    private static Calendar initCalendar()
    {
        final Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(DataCiteDateConstants.STANDARD_TIMEZONE));
        cal.set(0, 0, 0, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }
}
