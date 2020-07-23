import requests
import json
import pprint


def testGetBasicRoute():

	winnipeg_lat = 49.916948
	winnipeg_lng = -97.079885
	printer = pprint.PrettyPrinter(indent=3)

	location = {'latitude': winnipeg_lat, 'longitude': winnipeg_lng}
	res = requests.get('http://localhost:5000/getBasicRouteTest', params=location)
	
	places = json.loads(res.text)
	#printer.pprint(places)
	print(places)

	#print('Response: ', res.text)


if __name__ == "__main__":
	print('Running Server Tests')
	testGetBasicRoute()