/* 
 * Copyright (C) 2016 Bruce Beisel
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

import java.util.*;
import java.io.*;

/**
 * Group of static methods for manipulating strings.
 */
public final class StringUtils {
    /**
     * Default delimiters of space and tabs
     */
    public static final String DEFAULT_DELIMITERS = " \t";
    /**
     * Default quotes characters.
     */
    public static final String DEFAULT_QUOTE_CHARS = "'\"";

    /**
     * Break a string into tokens based on delimiters and quote characters.
     * 
     * @param input The string to be tokenized
     * @param delimiters The delimiters used to tokenize the input
     * @param quoteChars Characters that are treated as quotations
     * @return  The list of tokens. It is empty if there are no tokens
     */
    public static List<String> tokenize(String input, String delimiters, String quoteChars) {
	ArrayList<String> list = new ArrayList<>();

	//
	// Set up the string tokenizer to only recognize words as tokens (As
	// opposed to numbers and words)
	//
	StreamTokenizer st = new StreamTokenizer(new StringReader(input));
	st.resetSyntax();
	st.wordChars(' ', '~');

	//
	// Register with the tokenizer all the white space characters
	//
	for (int i = 0; i < delimiters.length(); i++)
	    st.whitespaceChars(delimiters.charAt(i), delimiters.charAt(i));

	//
	// Register with the tokenizer all the quote characters
	//
	for (int i = 0; i < quoteChars.length(); i++)
	    st.quoteChar(quoteChars.charAt(i));
	
	try {
	    int tt;
	    //
	    // Loop through the string, extract tokens and add them to the List to
	    // be returned.
	    while ((tt = st.nextToken()) != StreamTokenizer.TT_EOF) {
		if (tt == StreamTokenizer.TT_WORD || quoteChars.indexOf(tt) != -1)
		    list.add(st.sval);
		else
		    System.err.println("StringUtils.tokenizer(): Unexpected token type: " + tt);
	    }
	}
	catch (IOException e) {
	    System.err.println("StringUtils.tokenizer(): Expected exception: " + e);
	}

	return list;
    }

    /**
     * Break a string into tokens using default delimiters (space, and tab) and default quotes (' and ")
     * 
     * @param input The string to tokenize
     * @return The list of tokens
     */
    public static List<String> tokenize(String input) {
	return tokenize(input, DEFAULT_DELIMITERS, DEFAULT_QUOTE_CHARS);
    }
}
