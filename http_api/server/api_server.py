from flask import Flask, request, jsonify
from flask_cors import CORS
from flask_api import status
from datetime import datetime
import pprint


import biker_routes

api_server = Flask(__name__)
cors = CORS(api_server)

@api_server.route("/", methods=['GET'])
def home():
	return "Server is Running."

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

		# pprinter = pprint.PrettyPrinter(indent=2)
		# pprinter.pprint(route)
		print('Route: ', route)

		return route, status.HTTP_200_OK


if __name__ == "__main__":
	api_server.run()