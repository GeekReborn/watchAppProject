var MapDistanceUtils = {
	getDistance(latA, lngA, latB, lngB) {
		var radLat1 = latA * Math.PI / 180.0;
		var radLat2 = latB * Math.PI / 180.0;
		var radLng1 = lngA * Math.PI / 180.0;
		var radLng2 = lngB * Math.PI / 180.0;
		var radLatTotal = radLat1 - radLat2;
		var radLngTotal = radLng1 - radLng2;
		var distance = 2 * Math.asin(
				Math.sqrt(Math.pow(Math.sin(radLatTotal / 2), 2)
				+ Math.cos(radLat1)
					* Math.cos(radLat2)
					* Math.pow(Math.sin(radLngTotal / 2), 2)));
		distance = distance * 6378.137 ;	// EARTH_RADIUS;
		distance = Math.round(distance * 10000) / 10000;	// 保留4位小数
		return distance;
	}
}

module.exports = MapDistanceUtils