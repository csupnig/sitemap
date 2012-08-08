package com.mandt.sitemap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class CompareUtil {
	
	public static final String getCompareString(String filname) {
		InputStream stream = CompareUtil.class.getResourceAsStream(filname);
		try {
			return inputStreamToString(stream);
		} catch (IOException e) {
			return "";
		}
	}
	
	/**
	 * Reads an input stream into a string.
	 * @param is input stream
	 * @return strimg
	 * @throws IOException
	 */
	private static final String inputStreamToString(InputStream is) throws IOException {
		if (is != null) {
            Writer writer = new StringWriter();
 
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {        
            return "";
        }
	}

}
