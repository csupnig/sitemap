package com.mandt.sitemap;

import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

/**
 * The sitemap can hold serveral sitemap items and
 * will render them according to the specification
 * on http://www.sitemaps.org.
 * @author Christopher Supnig | http://supnig.com
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
	 * Location of the Sitemap.
	 */
	private URI location;
	
	/**
	 * Date format.
	 */
	private static final DateFormat DATE_FORMAT
		= new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Last modification.
	 */
	private Date lastMod;
	
	/**
	 * Create a new instance of a sitemap.
	 */
	public Sitemap() {
		items = new Vector<Item>();
	}
	
	/**
	 * Create a new instance of a sitemap.
	 * @param location URI to the sitemap.
	 * The location is mandatory if this sitemap will be part
	 * of a sitemap index.
	 */
	public Sitemap(URI location) {
		this();
		this.location = location;
	}
	
	/**
	 * Create a new instance of a sitemap.
	 * @param location url to the sitemap as string.
	 * The location is mandatory if this sitemap will be part
	 * of a sitemap index.
	 * @throws URISyntaxException in case the url is not properly formated.
	 */
	public Sitemap(String location) throws URISyntaxException {
		this(new URI(location));
	}
	
	/**
	 * Set the location of the sitemap.The location is mandatory if this sitemap will be part
	 * of a sitemap index.
	 * @param location URI to the sitemap.
	 */
	public final void setLocation(URI location) {
		this.location = location;
	}
	
	/**
	 * Set the location of the sitemap.
	 * The location is mandatory if this sitemap will be part
	 * of a sitemap index.
	 * @param location url to the sitemap as string.
	 * @throws URISyntaxException in case the url is not properly formated.
	 */
	public final void setLocation(String location) throws URISyntaxException {
		setLocation(new URI(location));
	}
	
	/**
	 * Get the location of the sitemap.
	 * @return returns the URI of the sitemap.
	 */
	public final URI getLocation() {
		return this.location;
	}
	
	/**
	 * Sets the last modification date for this sitemap instance.
	 * If this will not be set, the lastmod element will not be
	 * rendered in a sitemap index.
	 * @param lastMod last date this sitemap was modified.
	 */
	public final void setLastModification(final Date lastMod) {
		this.lastMod = lastMod;
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
	
	/**
	 * Render the sitemap item for a sitemap index.
	 * @param writer wirter the sitemap item should be rendered to.
	 * @throws IOException in case of IO error.
	 */
	public final void renderForIndex(final Writer writer) throws IOException {
		writer.write("<sitemap>");
		URI location = getLocation();
		if (location == null) {
			throw new IllegalArgumentException("The sitemap hase to have a location,"
					+ " when it is being rendered in a sitemap index.");
		}
		writer.write("<loc>" + getLocation() + "</loc>");
		if (this.lastMod != null) {
			writer.write("<lastmod>" + DATE_FORMAT.format(this.lastMod) + "</lastmod>");
		}
		writer.write("</sitemap>");
	}
}
