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
		print('New Route Object Created')

	def build_route(self, latitude, longitude):
		#response = {'startingLocation': 'Winnipeg', 'from': 'biker_routes'}

		#Testing

		nearby_places = self.get_nearby_locations(latitude, longitude)
		directions = self.get_directions(current_location=(latitude, longitude), nearby_places=nearby_places)
		#end testing


		return jsonify(nearby_places)

	def get_nearby_locations(self, latitude, longitude):
		print('Searching for Nearby Locations')

		distance = 5000 # which is 5km
		place_type = 'tourist_attraction'

		#places_nearby() returns a dict of info
		places = self.maps.places_nearby(location={'latitude': latitude, 'longitude':longitude},
									radius=distance,
									type=place_type)

		#TODO: If places_nearby returns a next_page_token, call places_nearby() again
		#to build a full list of places.
		#if(places.contains('next_page_token')):
			#call again with token
		return places

	def get_directions(self, current_location, nearby_places):
			#places = json.loads(nearby_places);
			#print('Places decoded: \n\n', nearby_places)
			directions = []
			for place in nearby_places:
				route = self._get_directions(current_location, place)
			#self.directions(origin=current_location, )

	def _get_directions(self, starting_location, ending_location):

		destination = ending_location['place_id']

		directions = self.maps.directions(origin=starting_location,
										   destination='place_id:' + ending_location,
										   key=self.key)

		#maps.directions() returns a LIST of Routes
		print('**DIRECTIONS**\n', directions);
		pass