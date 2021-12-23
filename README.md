<h1 align="center"> Smart-City-Traveller </h1>
<p align="center">
<img src="./img/icon.png" alt="smart-city-traveller" width="20%" align = "center">
</p>

It is an Application that helps a traveler visiting a city to explore and navigate places of their choice in a very Simple way without typing and deciding which place is best and which is not.


It is an android app developed using Java and XML and Foursquare API.
- `XML` is used to make layout of the project and 
- `Java` is used is handle the backend of the app and 
- `Foursquare API` is used to provide location based experiences with diverse information about venues(Like venue name, address, latitude, longitude).

[Video Link](https://youtu.be/a3OAVr1kiqc)

## Layout

![layout](./img/layout.png)

## Screenshots of the app

### Home Screen

<img src="./img/homeScreen.jpg" alt="home" width="30%" >

### Input City Name

<img src="./img/inputCity1.jpg" alt="inputCityName" width="30%" > <img src="./img/inputCity2.jpg" alt="inputCityName" width="30%" > <img src="./img/inputCity3.jpg" alt="inputCityName" width="30%" >

### Choose Category

<img src="./img/chooseCategory.jpg" alt="chooseCategory" width="30%" >

### Venue List

<img src="./img/displayVenues.jpg" alt="venueList" width="30%" >

### Google Map

<img src="./img/googleMap.jpg" alt="googleMap" width="30%" >



# Requirements

### 1. Changing Foursquare Api
- Login with foursquare account and get the credentials.
- Now go to : `app\src\main\res\values\strings.xml`
- and replace `CLIENT_ID` and `CLIENT_SECRET` with your own foursquare credentials.

### 2. Change the Google Maps Api
- Get [Google Map api](https://developers.google.com/maps/documentation/android/signup)
- Now go to : `app\src\release\res\values\google_maps_api.xml`
- and replace the "google_maps_key" with your own google map api key.