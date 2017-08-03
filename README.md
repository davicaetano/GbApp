# GbApp
Gb code challenge

Steps:
1. Retrieve and print out the data received from the url above.
2. Parse the data retrieved from the server into a list of Java objects
3. Display your objects in a RecyclerView
  Should display the name, city, state, and end date
4. In addition the object’s name, have your view display the image located at each object’s icon url.

All the steps were done.

Notes:
1. The api is returning null venues. I created a test where the json comes from a file and the first city has a venue with City and State.
2. There is a Button and a TextView on the UI to help with testing. They can be removed.
3. The RecyclerView can be refreshed with "swipe up".
4. I didn't use Dagger because it is a small project. The GbApp does the DI.
5. I used a very simple persistence on memory, but it could implement SQLite.
