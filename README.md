# Biker

Find an Interesting Bike Route!

(DISCLAIMER: This project is still a work in progress. In it's current state, it serves as a high fidelity prototype.)

## About

Biker is an Android app that creates fun and interesting bicycle rides!

Using the users current location, Biker looks for nearby interesting places and then maps out a route to get there by bike.

Biker was built because as an avid bicyclist I was getting a little bored of taking the same routes all the time.

## Technology used

Biker is written in Java and was developed using Android Studio.

It uses an HTTP server written in Python/Flask to do the heavy lifting. The app itself sends a request to the server, and recieves the locations back. All directions/calculations/etc are all done on the server to reduce strain on the users system.

The HTTP server was hosted on Amazon's ElasticBeanstalk.

Google API's were used for Maps/Locations/Directions/Geolocation/etc

## Screenshots

### Launching the app, it will find your current location and use that as a starting point

![](https://i.imgur.com/XbS0Lbv.jpg)

### Pressing the 'Create Button' will map out a route to nearby interesting locations!

### (NOTE: This image is a Proof of Concept - the final Route would not be this convoluted and repetitive)

![](https://i.imgur.com/MEnJEjt.jpg)

## Author

David Lekei

## License
