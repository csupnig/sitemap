package com.mandt.sitemap;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import org.junit.Test;

public class SitemapTest {
	
	private static final long BIRTHDAY = 498844800000L;
	/**
	 * Testing the sitemap with some elements.
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	@Test
    public void testSitemap() throws IOException, URISyntaxException {
    	
		StringWriter writer = new StringWriter();
		Sitemap sitemap = new Sitemap();
		
		Item item1 = new Item(new URI("http://www.supnig.com"));
		item1.setLastmod(new Date(BIRTHDAY));
		item1.setModificationFrequency(ModificationFrequency.daily);
		item1.setPrority(0.5f);
		sitemap.add(item1);
		
		Item item2 = new Item(new URI("http://www.google.com"));
		item2.setLastmod(new Date(BIRTHDAY));
		item2.setModificationFrequency(ModificationFrequency.hourly);
		//The following should result in 1.0
		item2.setPrority(2.0f);
		sitemap.add(item2);
		
		Item item3 = new Item(new URI("http://www.github.com"));
		sitemap.add(item3);
		
		sitemap.render(writer);
		
		assertEquals(writer.toString(), CompareUtil.getCompareString("simple.xml"));
		
    }
	
	/**
	 * Testing the sitemap index with two sitemaps.
	 * @throws URISyntaxException 
	 * @throws IOException 
	 */
	@Test
	public void testIndex() throws URISyntaxException, IOException {
		StringWriter writer = new StringWriter();
		
		SitemapIndex index = new SitemapIndex();
		
		Sitemap sitemap1 = new Sitemap("http://www.testing.com/sitemap");
		Item item1 = new Item(new URI("http://www.supnig.com"));
		item1.setLastmod(new Date(BIRTHDAY));
		item1.setModificationFrequency(ModificationFrequency.daily);
		item1.setPrority(0.5f);
		sitemap1.add(item1);
		sitemap1.setLastModification(new Date(BIRTHDAY));
		index.add(sitemap1);
		
		Sitemap sitemap2 = new Sitemap();
		sitemap2.setLocation("http://www.testing2.com/sitemap");
		Item item2 = new Item(new URI("http://www.google.com"));
		item2.setLastmod(new Date(BIRTHDAY));
		item2.setModificationFrequency(ModificationFrequency.hourly);
		//The following should result in 1.0
		item2.setPrority(2.0f);
		sitemap2.add(item2);
		index.add(sitemap2);
		
		index.render(writer);
		
		assertEquals(writer.toString(), CompareUtil.getCompareString("index.xml"));
	}
	/**
	 * Test for exception when rendering without location.
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Test
	public void testIndexWithMissingSitemapLocation() throws URISyntaxException, IOException {
		StringWriter writer = new StringWriter();
		
		SitemapIndex index = new SitemapIndex();
		Sitemap sitemap1 = new Sitemap();
		Item item1 = new Item(new URI("http://www.supnig.com"));
		item1.setLastmod(new Date(BIRTHDAY));
		item1.setModificationFrequency(ModificationFrequency.daily);
		item1.setPrority(0.5f);
		sitemap1.add(item1);
		index.add(sitemap1);
		
		Exception thrown = null;
		
		try {
			index.render(writer);
		} catch(IllegalArgumentException e) {
			thrown = e;
		} catch(Exception e) {
			thrown = e;
		}
		
		assertEquals(thrown != null && thrown instanceof IllegalArgumentException, true);
		
	}
}
