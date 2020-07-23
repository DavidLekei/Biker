from flask import jsonify
import json
import googlemaps

#imports for testing
import pprint

class biker_api:


	#TODO: DO NOT HARDCODE KEY!!!!
	def __init__(self):
		self.key = 'AIzaSyA0a_wiiuucep3IHoieb3xyr8ZLTKGHh7E'
		self.maps = googlemaps.Client(key=self.key);
		self.pprinter = pprint.PrettyPrinter(indent=2)

	#@RETURN: A JSONObject with the fields:
	#			html_attributions: []
	#			next_page_token: String
	#			results: Array of JSON Objects
	#			status: HTTP Status Code (200 OK)
	def get_nearby_locations(self, latitude, longitude):
		print('Searching for Nearby Locations')

		#TODO: Make distance a changable parameter
		distance = 5000 # meters (5km)
		place_type = 'tourist_attraction'

		#places_nearby() returns a dict of info
		places = self.maps.places_nearby(location={'latitude': latitude, 'longitude':longitude},
									radius=distance,
									type=place_type)

		locations = self._build_locations(places)
		for loc in locations:
			print(loc, '\n\n\n')


		#TODO: If places_nearby returns a next_page_token, call places_nearby() again to build a full list of places.

		return locations

	def _build_locations(self, places):
		print('Building Location Dict')

		list_of_locations = []

		results = places['results']
		for res in results:
			location = {}

			#Extract data from JSON
			name = res['name']
			geo = res['geometry']
			loc = geo['location']
			place_id = res['place_id']
			types = res['types']
			address = res['vicinity']

			#Add Data to Dictionary
			location["name"] = name
			location["location"] = loc
			location["place_id"] = place_id
			location["types"] = types
			location["address"] = address

			#Add new Dictionary to Array of Dict's
			list_of_locations.append(location)

		return list_of_locations

	def get_directions(self, starting_location, ending_location):

		print('-------------------------------------------------------')
		# start_lat = starting_location[0]
		# start_lng = starting_location[1]

		# dest_lat = ending_location[0]
		# dest_lng = ending_location[1]

		directions = self.maps.directions(origin=starting_location,
										   destination=ending_location,
										   mode='bicycling')

		route = self.extract_steps(directions)
		return route

	def extract_steps(self, directions):
		
		"""
		The following loop goes through the list of Directions. As of now, that list
		should only contain a single element, as we are requesting directions from 
		Point A to Point B.

		In the future, we can maybe change the request to include multiple destinations.
		Ex) Directions from Point A to B, then to C, then to D, etc
		In that case, the directions list will have more than one element.

		The loop then extracts the 'legs' JSON Array, which again, should only actually
		contain a single element, which is another List of Steps

		The List of Steps then gets added to the route List variable.


		"""
		route = []
		for d in directions:
			legs = d['legs']
			for leg in legs:
				steps = leg['steps']
				route.append(steps)
		return route