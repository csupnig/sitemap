package com.mandt.sitemap;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Vector;

/**
 * The sitemap can hold serveral sitemap items and
 * will render them according to the specification
 * on http://www.sitemaps.org.
 * @author Christopher
 *
 */
public class Sitemap {
	/**
	 * XML Header.
	 */
	private static final String DTYPE
		= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	/**
	 * Start element containing the protocol version.
	 */
	private static final String START
	= "<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">";
	/**
	 * Ending element.
	 */
	private static final String END = "</urlset>";
	/**
	 * Sitemap items.
	 */
	private Collection<Item> items;
	/**
	 * Create a new instance of a sitemap.
	 */
	public Sitemap() {
		items = new Vector<Item>();
	}
	/**
	 * Add an item to the sitemap.
	 * @param item sitemap item.
	 * @return true if the collection of items changed
	 * with this call.
	 */
	public final boolean add(final Item item) {
		return items.add(item);
	}
	/**
	 * Add all items to the sitemap.
	 * @param c collection of sitemap items.
	 * @return true if the collection of items changed
	 * with this call.
	 */
	public final boolean addAll(final Collection<Item> c) {
		return items.addAll(c);
	}
	/**
	 * Render the sitemap.
	 * @param writer writer the sitemap should be rendered to.
	 * @throws IOException in case of IO error.
	 */
	public final void render(final Writer writer) throws IOException {
		writer.write(DTYPE);
		writer.write(START);
		for (Item i : items) {
			i.render(writer);
		}
		writer.write(END);
	}
}
