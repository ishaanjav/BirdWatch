import React, { useState, useEffect } from "react";
import Map from "./Map.jsx";
import SearchBar from "./SearchBar.jsx";
import SightingCard from "./SightingCard.jsx";
import firebase from "firebase";


function App(props) {
  const [currentCenter, setCurrentCenter] = useState({lat: 43, lng: -80});
  const [birdData, setData] = useState({});

  useEffect(() => {
    var firebaseConfig = {
      apiKey: "AIzaSyDk37dWfrZcb_C9LS-sVNuZp6y_koD-xZc",
      authDomain: "birdwatch-ccc8a.firebaseapp.com",
      databaseURL: "https://birdwatch-ccc8a.firebaseio.com",
      projectId: "birdwatch-ccc8a",
      storageBucket: "birdwatch-ccc8a.appspot.com",
      messagingSenderId: "368071425948",
      appId: "1:368071425948:android:bbfc5530eb683cad64d259",
      // measurementId: "G-MEASUREMENT_ID",
    };
    firebase.initializeApp(firebaseConfig);

      let ref = firebase.database().ref('/');
      ref.on('value', snapshot => {
        const state = snapshot.val();
        console.log(state);
        setData(state)
      });
      
  }, []);

    return <>
      <div style={{ maxWidth: "100%" }} className="flex flex-row overflow-x-hidden h-full">
        <Map
          currentCenter={currentCenter}
        />
        <div className="w-75 bg-white h-screen px-3 py-3">
          <SearchBar></SearchBar>
          <SightingCard
            title="Niagra Falls, New York State"
            lat={40}
            lng={20}
            sightings={16}
            notExtinct={12}
            endangered={4}
            setCurrentCenter={setCurrentCenter}
          />
        </div>
      </div>
    </>
}

export default App;