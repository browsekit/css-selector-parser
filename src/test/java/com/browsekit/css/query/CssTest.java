package com.browsekit.css.query;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class CssTest {
	// src/test/resources/testcases.txt
	private static final String TESTCASES = "testcases.txt";

	@Test
	public void test() throws CssQueryUnknownGrammarException, IOException {
		InputStream testcases = ClassLoader.getSystemResourceAsStream(TESTCASES);
		assertNotNull(testcases);
		BufferedReader br = new BufferedReader(new InputStreamReader(testcases));
		String line = null;
		
		CssSelectorTokenizer csTokenizer = new CssSelectorTokenizer();
		csTokenizer.addDefaultGrammar();
		
		while ((line = br.readLine()) != null) {
			String query = StringUtils.trim(line);
			if (StringUtils.isEmpty(query)) continue;
			if (query.startsWith("//")) continue;
			if (query.matches("^$")) continue;

			assertEquals(query, csTokenizer.parse(query).toString());
		}
	}
}