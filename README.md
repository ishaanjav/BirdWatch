# BirdWatch

A smart system that uses deep learning to identify bird species and provide information, including *endangered level*, to users so as to raise awareness for endangered species and help people learn more. 
- The app crowdsources new bird data by storing labelled images captured by users in Firebase.
  - This crowdsourced, labelled data is used to train newer versions of the model.
- The system then displays locations of endangered species, in a web app for local authorities to monitor and receive alerts.

-----

## Process

- Users of the app simply take a picture of bird species they come across while hiking, visiting farms, or simply being out in nature.
- The app then identifies the top 5 most likely bird species and displays them to the user along with an image.
- The user then selects the most similar result or "None of the above". This information is then sent to Firebase and the user is shown the endangered level of their species.
