from flask import jsonify

import googlemaps

class biker_routes:


	def __init__(self):
		self.maps = googlemaps.Client(key='AIzaSyA0a_wiiuucep3IHoieb3xyr8ZLTKGHh7E');
		print('New Route Object Created')

	def buildRoute(self, latitude, longitude):
		response = {'startingLocation': 'Winnipeg', 'from': 'biker_routes'}

		#Testing

		self.getNearbyLocations(latitude, longitude)

		#end testing


		return jsonify(response)

	def getNearbyLocations(self, latitude, longitude):
		print('Searching for Nearby Locations')
		places = self.maps.places_nearby(location={'latitude': latitude, 'longitude':longitude},
									radius=20,
									open_now=True)
		print('Google Maps returned places:\n', places)