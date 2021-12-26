<h1 align="center"> Smart-City-Traveller </h1>
<p align="center">
<img src="./img/icon.png" alt="smart-city-traveller" width="20%" align = "center">
</p>


[<img alt="watch video" width="120px" src="./img/watchVideo.png" />](https://youtu.be/a3OAVr1kiqc)

- It is an Application that helps a traveler visiting a city to explore and navigate places of their choice in a very Simple way without typing and deciding which place is best and which is not.

- It is developed using `XML`, `Java` and `Foursquare API`.

<img src="./img/used.png" alt="smart-city-traveller" width="400" align = "center">

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

### How to view path on Google Map from your current location to the venue
- click on the red marker wher you want to go and then 
- click on bottom right corner icons to view the route from the current location to the venue

for demo view video 

[<img alt="watch video" width="120px" src="./img/watchVideo.png" />](https://youtu.be/a3OAVr1kiqc)

# Requirements

- Android Studio
- minimum 4GB RAM
- minimum 2GB Storage
- FourSquare API (version 3 `v3`)
- Google Maps API
- Working Internet Connection

### 1. Changing Foursquare Api
- Login with foursquare account and get Authroization code
- Now go to : [app\src\main\res\values\strings.xml](app\src\main\res\values\strings.xml)
- and replace `AUTH_ID` with your own foursquare authroization code.

### 2. Change the Google Maps Api
- Get [Google Map api](https://developers.google.com/maps/documentation/android/signup)
- Now go to : [app\src\release\res\values\google_maps_api.xml](app\src\release\res\values\google_maps_api.xml)
- and replace the "google_maps_key" with your own google map api key.