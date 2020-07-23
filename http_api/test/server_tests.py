import requests
import json
import pprint

class server_test:

	def __init__(self):
		self.lat = 49.916948
		self.lng = -97.079885
		self.printer = pprint.PrettyPrinter(indent=2)
		self.loc = {'latitude':self.lat, 'longitude':self.lng}


	def test_get_nearby_places(self):
		print('Testing getNearbyPlaces Endpoint')
		response = requests.get('http://localhost:5000/getNearbyPlaces', params=self.loc)
		nearby_places = json.loads(response.text)
		self.printer.pprint(nearby_places)

	def testGetBasicRoute(self):
		res = requests.get('http://localhost:5000/getBasicRouteTest', params=self.loc)
		
		places = json.loads(res.text)
		printer.pprint(places)

	def test_get_directions(self):
		print('Testing getDirections endpoint')
		params = {'start_lat':self.lat, 'start_lng':self.lng, 'end_lat':49.944054, 'end_lng':-97.077415}
		response = requests.get('http://localhost:5000/getDirections', params=params)
		directions = json.loads(response.text)
		self.printer.pprint(directions)



if __name__ == "__main__":
	print('Running Server Tests')
	test = server_test()
	#test.test_get_nearby_places()
	#test.testGetBasicRoute()
	test.test_get_directions()