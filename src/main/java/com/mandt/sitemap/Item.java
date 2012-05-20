package com.mandt.sitemap;

import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a sitemap item.
 * @author Christopher
 *
 */
public class Item {
	/**
	 * default priority.
	 */
	private static final float DEFAULT_PRIO = 0.5f;
	/**
	 * Minimum priority.
	 */
	private static final float MIN_PRIO = 0.0f;
	/**
	 * Maximum priority.
	 */
	private static final float MAX_PRIO = 1.0f;
	/**
	 * Start element.
	 */
	private static final String START = "<url>";
	/**
	 * End element.
	 */
	private static final String END = "</url>";
	/**
	 * Date format.
	 */
	private static final DateFormat DATE_FORMAT
		= new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * Location.
	 */
	private URI location;
	/**
	 * Last modification.
	 */
	private Date lastMod;
	/**
	 * Modification frequency.
	 */
	private ModificationFrequency mFreq;
	/**
	 * Priority.
	 */
	private float prio = DEFAULT_PRIO;
	/**
	 * Create a new Item.
	 * @param loc location of the item.
	 * @throws URISyntaxException If the given string
	 * violates RFC 2396.
	 */
	public Item(final String loc) throws URISyntaxException {
		this(new URI(loc));
	}
	/**
	 * Create a new Item.
	 * @param loc location of the item.
	 */
	public Item(final URI loc) {
		location = loc;
	}
	/**
	 * Set the date when the item was last modified.
	 * @param mod date when the item was last modified.
	 */
	public final void setLastmod(final Date mod) {
		lastMod = mod;
	}
	/**
	 * Set the modification frequency.
	 * @param modFreq modification frequency.
	 */
	public final void setModificationFrequency(
			final ModificationFrequency modFreq) {
		mFreq = modFreq;
	}
	/**
	 * The priority of this URL relative to other
	 * URLs on your site. Valid values range from 0.0 to 1.0.
	 * This value does not affect how your pages are compared
	 * to pages on other sites—it only lets the search engines
	 * know which pages you deem most important for the crawlers.
	 * The default priority of a page is 0.5.
	 * @param p priority as float between 0.0 and 1.0
	 */
	public final void setPrority(final float p) {
		if (p > MAX_PRIO) {
			prio = MAX_PRIO;
		} else if (p < MIN_PRIO) {
			prio = MIN_PRIO;
		} else {
			prio = p;
		}
	}
	/**
	 * Render the item.
	 * @param writer writer the item should be rendered to.
	 * @throws IOException in case of I/O error.
	 */
	public final void render(final Writer writer) throws IOException {
		writer.write(START);
		writer.write("<loc>" + location + "</loc>");
		if (lastMod != null) {
			writer.write("<lastmod>" + DATE_FORMAT.format(lastMod)
					+ "</lastmod>");
		}
		if (mFreq != null) {
			writer.write("<changefreq>" + mFreq + "</changefreq>");
		}
		if (prio != DEFAULT_PRIO) {
			writer.write("<priority>" + prio + "</priority>");
		}
		writer.write(END);
	}
}
