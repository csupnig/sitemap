package com.mandt.sitemap;
/**
 * Enumeration of possible modification frequencies.
 * @author Christopher Supnig | http://supnig.com
 *
 */
public enum ModificationFrequency {
	/**
	 * The value "always" should be used to describe
	 * documents that change each time they are accessed.
	 */
	always,
	/**
	 * the value "hourly" should be used to describe
	 * documents that change every hour.
	 */
	hourly,
	/**
	 * the value "hourly" should be used to describe
	 * documents that change every day.
	 */
	daily,
	/**
	 * the value "hourly" should be used to describe
	 * documents that change every week.
	 */
	weekly,
	/**
	 * the value "hourly" should be used to describe
	 * documents that change every month.
	 */
	monthly,
	/**
	 * the value "hourly" should be used to describe
	 * documents that change every year.
	 */
	yearly,
	/**
	 * The value "never" should be used to describe archived URLs
	 * and items that never change.
	 */
	never
}
