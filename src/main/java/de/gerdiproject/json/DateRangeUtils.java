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

import java.time.Instant;

import de.gerdiproject.json.datacite.DateRange;
import de.gerdiproject.json.datacite.constants.DataCiteDateConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This utility class offers static methods for processing {@linkplain DateRange}s.
 *
 * @author Robin Weiss
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateRangeUtils
{
    /**
     * Parses a date range using a set of common separators defined in {@linkplain DataCiteDateConstants}.
     *
     * @param dateString a raw {@linkplain String} that contains a date range
     *
     * @return an {@linkplain Instant} array that contains the start- and end date,
     * or null if no date range could be parsed
     */
    public static Instant[] parseDateRange(final String dateString)
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
    public static Instant[] parseDateRange(final String dateString, final String separator)
    {
        final Instant[] dates = new Instant[2];
        boolean isDateRange = false;

        // check if the string contains the separator
        if (dateString.contains(separator)) {
            String dateStringFrom = null;
            String dateStringUntil = null;

            // edge case: date range with open start-date
            if (dateString.startsWith(separator))
                dateStringUntil = dateString.substring(separator.length());

            // edge case: date range with open end-date
            else if (dateString.endsWith(separator))
                dateStringFrom = dateString.substring(0, dateString.length() - separator.length());

            // regular case: the separator is somewhere between two dates
            else {
                final String[] dateRangeElements = dateString.split(separator);

                // check if there is just one separator
                if (dateRangeElements.length == 2) {

                    dateStringFrom = dateRangeElements[0];
                    dateStringUntil = dateRangeElements[1];
                }

                // edge case: the range-separator is the same symbol as the day/month/year-separator
                else if (dateRangeElements.length > 2 && dateRangeElements.length % 2 == 0) {
                    // determine the middle of the string for a proper separation
                    final int separatorLength = separator.length();
                    int halfLength = 0;
                    final int len = dateRangeElements.length / 2;

                    for (int i = 0; i < len; i++)
                        halfLength += dateRangeElements[i].length() + separatorLength;

                    dateStringFrom = dateString.substring(0, halfLength - 1);
                    dateStringUntil = dateString.substring(halfLength);
                }
            }

            // continue only if at least one possible date was found
            if (dateStringFrom != null  || dateStringUntil != null) {

                // edge case: not a date range, because the two date strings are too radically different
                isDateRange = dateStringFrom == null
                              || dateStringUntil == null
                              || Math.abs(dateStringFrom.length() - dateStringUntil.length()) <= 2;

                if (dateStringFrom != null) {
                    dates[0] = DateUtils.parseDate(dateStringFrom);
                    isDateRange &= dates[0] != null;
                }

                if (dateStringUntil != null) {
                    dates[1] = DateUtils.parseDate(dateStringUntil);
                    isDateRange &= dates[1] != null;
                }
            }
        }

        // return null if array is empty
        return isDateRange ? dates : null;
    }
}
