from flask import Flask, request, jsonify
from flask_cors import CORS
from flask_api import status
from datetime import datetime

api_server = Flask(__name__)
cors = CORS(api_server)

@api_server.route("/", methods=['GET'])
def home():
	return "Server is Running."

@api_server.route("/getBasicRoute", methods=['GET'])
def getBasicRoute():
	#location = request.args.get('location')
	latitude = request.args.get('latitude')
	longitude = request.args.get('longitude')
	# if(location == None):
	# 	return "ERROR: No Location Parameter", status.HTTP_400_BAD_REQUEST
	# else:
	route = { 'startingLocation': 'Winnipeg' }
	return jsonify(route), status.HTTP_200_OK


if __name__ == "__main__":
	api_server.run()