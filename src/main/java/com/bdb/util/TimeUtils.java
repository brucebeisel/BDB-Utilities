/* 
 * Copyright (C) 2015 Bruce Beisel
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
package com.bdb.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * Collection of static methods to manipulate time.
 * 
 * @author Bruce
 *
 */
public class TimeUtils {
    private TimeUtils() {
    }

    /**
     * Convert a temporal accessor to an Instant.
     * 
     * @param temporal The temporal to convert
     * 
     * @return The Instant
     */
    public static Instant temporalToInstant(TemporalAccessor temporal) {
        //
        // LocalDateTime, LocalDate and LocalTime must have a time zone conversion before being converted to an Instant
        //
        Instant instant;
        if (temporal instanceof LocalDateTime)
            instant = ((LocalDateTime)temporal).atZone(ZoneId.systemDefault()).toInstant();
        else if (temporal instanceof LocalDate)
            instant = ((LocalDate)temporal).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        else if (temporal instanceof LocalTime)
            instant = ((LocalTime)temporal).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
        else
            instant = Instant.from(temporal); // throws DateTimeException

        return instant;
    }

    /**
     * Convert a local date and time to a java.util.date class.
     * 
     * @param temporal The temporal to convert
     * 
     * @return The converted date
     */
    public static Date localDateTimeToDate(TemporalAccessor temporal) {
        return Date.from(temporalToInstant(temporal));
    }

    /**
     * Convert a TemporalAccess to an Epoch value.
     * 
     * @param temporal
     * @return The Epoch time
     */
    public static long localDateTimeToEpochMillis(TemporalAccessor temporal) {
        return temporalToInstant(temporal).toEpochMilli();
    }
}
