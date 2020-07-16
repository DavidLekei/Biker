import requests


def testGetBasicRoute():
	location = {'latitude': '55.5555', 'longitude': '11.1111'}
	res = requests.get('http://localhost:5000/getBasicRoute', params=location)
	print('Response: ', res.text)


if __name__ == "__main__":
	print('Running Server Tests')
	testGetBasicRoute()