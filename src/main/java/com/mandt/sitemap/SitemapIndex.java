package com.mandt.sitemap;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Vector;

/**
 * The sitemapindex can hold serveral sitemaps and
 * will render them according to the specification
 * on http://www.sitemaps.org.
 * @author Christopher Supnig | http://supnig.com
 *
 */
public class SitemapIndex {
	/**
	 * XML Header.
	 */
	private static final String DTYPE
		= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	/**
	 * Start element containing the protocol version.
	 */
	private static final String START
	= "<sitemapindex xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">";
	
	/**
	 * Ending element.
	 */
	private static final String END = "</sitemapindex>";
	/**
	 * Sitemap items.
	 */
	private Collection<Sitemap> sitemaps;
	/**
	 * Create a new instance of a SitemapIndex.
	 */
	public SitemapIndex() {
		sitemaps = new Vector<Sitemap>();
	}
	/**
	 * Add a sitemap to the sitemap index.
	 * @param sitemap sitemap item.
	 * @return true if the collection of items changed
	 * with this call.
	 */
	public final boolean add(final Sitemap sitemap) {
		return sitemaps.add(sitemap);
	}
	/**
	 * Add all items to the sitemap index.
	 * @param c collection of sitemap items.
	 * @return true if the collection of items changed
	 * with this call.
	 */
	public final boolean addAll(final Collection<Sitemap> c) {
		return sitemaps.addAll(c);
	}
	/**
	 * Render the sitemap index.
	 * @param writer writer the sitemap index should be rendered to.
	 * @throws IOException in case of IO error.
	 */
	public final void render(final Writer writer) throws IOException {
		writer.write(DTYPE);
		writer.write(START);
		for (Sitemap i : sitemaps) {
			i.renderForIndex(writer);
		}
		writer.write(END);
	}
}
