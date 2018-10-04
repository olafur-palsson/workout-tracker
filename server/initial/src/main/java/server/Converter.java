/**
 * Author: Olafur Palsson
 * HImail: olp6@gmail.com
 * Actual: olafur.palsson
 * Heiti verkefnis: server
 */

package server;

import java.util.*;

public class Converter {

	public static Map<Integer, ?> arrayListToMap(ArrayList<?> arrayList) {
		Map<Integer, Object> map = new HashMap<>();
		for(int i = 0; i < arrayList.size(); i++)
			map.put(i, arrayList.get(i));
		return map;
	}

	//assume format yyyy-mm-dd as the input for simplcity of use
	public static Long yyyymmdd_toLong(String dateString) {
		String[] yearMonthDay = dateString.split("-");
		Calendar c = new GregorianCalendar();
		c.set(
			 Integer.parseInt(yearMonthDay[0]),
			 Integer.parseInt(yearMonthDay[1]) - 1,
			 Integer.parseInt(yearMonthDay[2])
		);
		return c.getTime().getTime();
	}
}
