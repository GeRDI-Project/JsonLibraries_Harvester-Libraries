/**
 * Copyright © 2017 Robin Weiss, Fidan Limani (http://www.gerdi-project.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.gerdiproject.json.datacite.constants;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import de.gerdiproject.json.datacite.abstr.AbstractDate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This static class is a collection of constants that are used by {@linkplain AbstractDate} and its sub-classes.
 *
 * @author Robin Weiss
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataCiteDateConstants
{
    public static final String ISO_8601_TIME_WITHOUT_SECONDS = "(T\\d\\d:\\d\\d)([Z+])";
    public static final String ISO_8601_REPLACE_TIME_WITH_SECONDS = "$1:00$2";
    public static final String PARSE_ERROR = "Could not parse date string '%s'!";

    // DATE RANGE
    public static final String DATE_RANGE_SPLITTER = "/";

    // JSON (DE-)SERIALIZATION
    public static final String VALUE_JSON = "value";
    public static final String DATE_TYPE_JSON = "dateType";
    public static final String DATE_INFO_JSON = "dateInformation";


    // DATE PARSING
    public static final String WORD_BEFORE_NUMBER_REGEX = "^\\D+\\s([^0-9]+[,.-]?\\s?\\d[\\d\\D]+)$";
    public static final String FIRST_NUMBER_REGEX = "^\\D+(\\d[\\d\\D]+)$";
    public static final String FIRST_MATCH = "$1";
    public static final String STANDARD_TIMEZONE = "UTC";
    public static final ZoneId Z_ZONE_ID = ZoneId.of("Z");
    public static final List<SimpleDateFormat> DATE_FORMATS_STARTING_WITH_CHAR = createDateFormatsStartingWithChar(STANDARD_TIMEZONE);
    public static final List<SimpleDateFormat> DATE_FORMATS_STARTING_WITH_NUM = createDateFormatsStartingWithDigit(STANDARD_TIMEZONE);


    /**
     * Creates an unmodifiable list of date formats, each of which is starting with a digit.
     *
     * @param timeZoneName the String representation of the time zone
     *
     * @return a list of date formats
     */
    private static List<SimpleDateFormat> createDateFormatsStartingWithDigit(String timeZoneName)
    {
        List<SimpleDateFormat> dateFormats =
            Collections.unmodifiableList(
                Arrays.asList(
                    new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH),     // 20.11.2016
                    new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH),     // 20/11/2016
                    new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH),     // 20-11-2016
                    new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH),     // 20 11 2016

                    new SimpleDateFormat("MM.yyyy", Locale.ENGLISH),        // 11.2016
                    new SimpleDateFormat("MM/yyyy", Locale.ENGLISH),        // 11/2016
                    new SimpleDateFormat("MM-yyyy", Locale.ENGLISH),        // 11-2016
                    new SimpleDateFormat("MM yyyy", Locale.ENGLISH),        // 11 2016

                    new SimpleDateFormat("d. MMMM, yyyy", Locale.ENGLISH),  // 7. November, 2016
                    new SimpleDateFormat("d. MMMM yyyy", Locale.ENGLISH),   // 7. November 2016
                    new SimpleDateFormat("d MMMM, yyyy", Locale.ENGLISH),   // 7 November, 2016
                    new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH),    // 7 November 2016
                    new SimpleDateFormat("dd-MMMM-yyyy", Locale.ENGLISH),   // 07-November-2016
                    new SimpleDateFormat("d-MMMM-yyyy", Locale.ENGLISH),    // 7-November-2016
                    new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH),    // 07-Nov-2016
                    new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH),     // 7-Nov-2016

                    new SimpleDateFormat("dd. MMM. yyyy", Locale.ENGLISH),  // 20. Nov. 2016
                    new SimpleDateFormat("dd. MMM, yyyy", Locale.ENGLISH),  // 20. Nov, 2016
                    new SimpleDateFormat("dd. MMM yyyy", Locale.ENGLISH),   // 20. Nov 2016
                    new SimpleDateFormat("dd MMM. yyyy", Locale.ENGLISH),   // 20 Nov. 2016
                    new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH),   // 20 Nov, 2016
                    new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH),    // 20 Nov 2016

                    new SimpleDateFormat("yyyy", Locale.ENGLISH)            // 2016
                ));

        // set TimeZone to UTC
        dateFormats.forEach((SimpleDateFormat sdf) -> sdf.setTimeZone(TimeZone.getTimeZone(timeZoneName)));

        return dateFormats;
    }

    /**
     * Creates an unmodifiable list of date formats, each of which is starting with a char.
     * @param timeZoneName the String representation of the time zone
     *
     * @return a list of date formats
     */
    private static List<SimpleDateFormat> createDateFormatsStartingWithChar(String timeZoneName)
    {
        List<SimpleDateFormat> dateFormats =
            Collections.unmodifiableList(
                Arrays.asList(
                    new SimpleDateFormat("MMMM, yyyy", Locale.ENGLISH), // November, 2016
                    new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH),  // November 2016
                    new SimpleDateFormat("MMMM-yyyy", Locale.ENGLISH),  // November-2016

                    new SimpleDateFormat("MMM yyyy", Locale.ENGLISH),   // Nov 2016
                    new SimpleDateFormat("MMM, yyyy", Locale.ENGLISH),  // Nov, 2016
                    new SimpleDateFormat("MMM. yyyy", Locale.ENGLISH),  // Nov. 2016
                    new SimpleDateFormat("MMM-yyyy", Locale.ENGLISH)    // Nov-2016
                ));

        // set TimeZone to UTC
        dateFormats.forEach((SimpleDateFormat sdf) -> sdf.setTimeZone(TimeZone.getTimeZone(timeZoneName)));

        return dateFormats;
    }
}
