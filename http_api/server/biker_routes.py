from flask import jsonify
import json
import googlemaps

#imports for testing
import pprint

class biker_routes:


	def __init__(self):
		self.maps = googlemaps.Client(key='AIzaSyA0a_wiiuucep3IHoieb3xyr8ZLTKGHh7E');
		print('New Route Object Created')

	def buildRoute(self, latitude, longitude):
		#response = {'startingLocation': 'Winnipeg', 'from': 'biker_routes'}

		#Testing

		nearby_places = self.getNearbyLocations(latitude, longitude)
		
		#end testing


		return jsonify(nearby_places)

	def getNearbyLocations(self, latitude, longitude):
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