/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server
 */

package server;

//ma alveg endurskyra ef hann faer a sig skyrt purpose
public class ToolBox {
	private static final int dayInMs = 86400000;

	public static long formatToMidnight(long date) {
		return (date / dayInMs) * dayInMs;
	}


	//copy-paste function, don't hate
	//return km
	public static double distance(double lat1, double lat2, double lon1, double lon2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
			 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
			 * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c;
	}

	public static boolean isClose(double lat1, double lat2, double lng1, double lng2) {
		return distance(lat1, lat2, lng1, lng2) < 150;
	}



}
