from flask import jsonify
import json
import googlemaps

#imports for testing
import pprint

class biker_routes:


	#TODO: DO NOT HARDCODE KEY!!!!
	def __init__(self):
		self.key = 'AIzaSyA0a_wiiuucep3IHoieb3xyr8ZLTKGHh7E'
		self.maps = googlemaps.Client(key=self.key);

		self.pprinter = pprint.PrettyPrinter(indent=2)

		print('New Route Object Created')

	def build_route(self, latitude, longitude):
		nearby_places = self.get_nearby_locations(latitude, longitude)
		directions = self.get_directions(current_location=(latitude, longitude), nearby_places=nearby_places)

		return jsonify(directions)

	def get_nearby_locations(self, latitude, longitude):
		print('Searching for Nearby Locations')

		#TODO: Make distance a changable parameter
		distance = 5000 # meters (5km)
		place_type = 'tourist_attraction'

		#places_nearby() returns a dict of info
		places = self.maps.places_nearby(location={'latitude': latitude, 'longitude':longitude},
									radius=distance,
									type=place_type)

		#TODO: If places_nearby returns a next_page_token, call places_nearby() again to build a full list of places.

		return places



	def get_directions(self, current_location, nearby_places):
			route = []
			nearby_places = nearby_places['results']
			prev_place = None

			for place in nearby_places:
				if(prev_place == None):
					self._get_directions(current_location, place, route)
				else:
					self._get_directions(prev_place, place, route)
				prev_place = self._get_latlng(place)
			return route
		
	def _get_directions(self, starting_location, ending_location, route):

		print('-------------------------------------------------------')
		destination = ending_location['place_id']

		directions = self.maps.directions(origin=starting_location,
										   destination='place_id:' + destination)

		self.extract_steps(directions, route)

	def _get_latlng(self, place):
		geo = place['geometry']
		loc = geo['location']
		latlng = (loc['lat'], loc['lng'])
		return latlng

	def extract_steps(self, directions, route):
		
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
		for d in directions:
			legs = d['legs']
			for leg in legs:
				steps = leg['steps']
				route.append(steps)