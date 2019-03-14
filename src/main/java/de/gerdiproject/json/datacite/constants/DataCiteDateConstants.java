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

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.regex.Pattern;

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
    public static final String PARSE_ERROR = "Could not parse date string '%s'!";

    // DATE RANGE
    public static final String DATE_RANGE_SPLITTER = "/";

    // JSON (DE-)SERIALIZATION
    public static final String VALUE_JSON = "value";
    public static final String DATE_TYPE_JSON = "dateType";
    public static final String DATE_INFO_JSON = "dateInformation";

    // DATE PARSING
    public static final String DATE_SPLIT_REGEX = "[\\-/,;. \\\\]+";
    public static final DateTimeFormatter MONTH_FORMATTER = initMonthFormatter();
    public static final DateTimeFormatter ISO8601_FORMATTER = initIso8601Formatter();
    public static final Pattern NUMBERS_PATTERN = Pattern.compile("(\\d{1,})");
    public static final String STANDARD_TIMEZONE = "UTC";
    public static final ZoneId Z_ZONE_ID = ZoneId.of("Z");


    private static DateTimeFormatter initIso8601Formatter()
    {
        final DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();

        // ignore case when parsing
        builder.parseCaseInsensitive();
        builder.parseLenient();
        builder.appendPattern("yyyy-MM-dd'T'HH:mm[:ss[.SSS]][X]");

        return builder
               .toFormatter()
               .withZone(ZoneId.of(DataCiteDateConstants.STANDARD_TIMEZONE));

    }


    private static DateTimeFormatter initMonthFormatter()
    {
        final DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();

        builder.appendPattern("[MMMM][MMM]");
        builder.parseCaseInsensitive();
        builder.parseLenient();

        return builder.toFormatter()
               .withZone(ZoneId.of(DataCiteDateConstants.STANDARD_TIMEZONE))
               .withLocale(Locale.ENGLISH);
    }
}
