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

/**
 * Class of static methods for manipulating threads.
 * 
 * @author bruce
 */
public final class ThreadUtils {
    private ThreadUtils() {}

    /**
     * Sleep and catch the interrupted exception.
     * 
     * @param millis The time to sleep
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
            
        }
    }
    
    /**
     * Sleep and catch the interrupted exception.
     * 
     * @param millis millisecond portion of the sleep time
     * @param nanos Nanosecond portion of the sleep time
     */
    public static void sleep(long millis, int nanos) {
        try {
            Thread.sleep(millis, nanos);
        }
        catch (InterruptedException e) {
            
        }
    }
}