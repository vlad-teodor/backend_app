**Backend App**

The implementation creates 10000 random locations with no duplicate names.

The PlaceContainer class contains 3 types of storing the places:
1. A hash map (name -> place) for finding the place fast by name
2. A hash map (country name -> counties in country) to a hash map (county name -> cities in county)
to a hash map (city name -> list of places in the city, sorted by price ascending). This helps find
the places in a certain location very fast.
3. A list sorted by price ascending.

The input for creating the list is always random (no implementation of manual input yet).
I did this to test larger inputs faster. There is a predefined list of countries/counties/cities.
Also, Dublin/Ireland is not the same as Dublin/Spain, to allow diversity in the random inputs.

Input for the user is always manual.

There are many cases which are not tested (any kind of wrong input).