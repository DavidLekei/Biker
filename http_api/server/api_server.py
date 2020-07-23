from flask import Flask, request, jsonify, json
from flask_cors import CORS
from flask_api import status
from datetime import datetime
import pprint
import ast


import biker_routes

api_server = Flask(__name__)
cors = CORS(api_server)

@api_server.route("/", methods=['GET'])
def home():
	return "Server is Running."

@api_server.route("/getBasicRouteTest", methods=['GET'])
def get_basic_route_test():
	with open("google_directions.json") as test_file:
		data = json.load(test_file)

	response = jsonify(data)
	print_response_info(response)
	return response, status.HTTP_200_OK

@api_server.route("/getBasicRoute", methods=['GET'])
def get_basic_route():
	latitude = request.args.get('latitude')
	longitude = request.args.get('longitude')

	if(latitude == None or longitude == None):
		return "ERROR: Missing Latitude/Longitude Parameter", status.HTTP_400_BAD_REQUEST
	else:
		print('Recieved request with Latitude: ' + latitude + ' / Longitude : ' + longitude)
		routes = biker_routes.biker_routes()		
		route = routes.build_route(latitude, longitude)

		print_response_info(route)
		return route, status.HTTP_200_OK

@api_server.route("/getNearbyPlaces", methods=['GET'])
def get_nearby_places():
	latitude = request.args.get('latitude')
	longitude = request.args.get('longitude')

	if(latitude == None or longitude == None):
		return "ERROR: Missing Latitude/Longitude Parameter", status.HTTP_400_BAD_REQUEST
	else:
		routes = biker_routes.biker_routes()
		nearby_places = routes.get_nearby_locations(latitude, longitude)
		return jsonify(nearby_places), status.HTTP_200_OK

@api_server.route("/getNearbyPlacesTest", methods=['GET'])
def get_nearby_places_test():
	with open('nearby_places.json') as file:
		data = json.load(file)

	response = jsonify(data)
	return response, status.HTTP_200_OK




def print_response_info(response):
	print('Response Length: ', response.headers.get('Content-Length', type=int))
	print('Response Type/Charset: ', response.headers.get('Content-Type', type=str))
	print('Response Encoding: ', response.headers.get('Content-Encoding', type=str))
	print('Response Charset: ', response.charset)


if __name__ == "__main__":
	api_server.run()