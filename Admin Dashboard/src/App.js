import React, { useState, useEffect } from "react";
import Map from "./Map.jsx";
import SearchBar from "./SearchBar.jsx";
import SightingCard from "./SightingCard.jsx";
import firebase from "firebase";


function App(props) {
  const [currentCenter, setCurrentCenter] = useState({lat: 43, lng: -80});
  const [birdData, setBirdData] = useState({});
  const [locations, setLocations] = useState([])

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
        console.log("state", state);
        setBirdData(state);
        var locs = []
        for (var timestamp in state) {
          locs.push({lat: state[timestamp].lat, lng: state[timestamp].lng});
        }
        setLocations(locs);
      });

  }, []);

  useEffect(() => {
    console.log("locations", locations);
  }, [locations]);

    return <>
      <div style={{ maxWidth: "100%" }} className="flex flex-row overflow-x-hidden h-full">
        <Map
          currentCenter={currentCenter}
          locations = {locations}
        />
        {/* get from firebase via passed in state */}
          {birdData.map((value, key) => {
            return <SightingCard
            title="Plano, TX, USA"
            lat={value.lat}
            lng={value.lng}
            sightings={Object.keys(value.sightings).length}
            notExtinct={value.notExtinct}
            endangered={value.endangered}
            setCurrentCenter={setCurrentCenter}
          />
          })}
          {/* placeholders */}
{/* 
        <div className="w-75 bg-white h-screen px-3 py-3">
          <SearchBar></SearchBar>
          <SightingCard
            title="Lincoln, Ontario, Canada"
            lat={43.397}
            lng={-80.644}
            sightings={16}
            notExtinct={12}
            endangered={4}
            setCurrentCenter={setCurrentCenter}
          />

          <SightingCard
            title="Beamsville, Ontario, Canada"
            lat={43.1385}
            lng={-79.4845}
            sightings={10}
            notExtinct={9}
            endangered={1}
            setCurrentCenter={setCurrentCenter}
          />
          <SightingCard
            title="Plano East, TX, USA"
            lat={33.0334}
            lng={-96.67525}
            sightings={10}
            notExtinct={5}
            endangered={0}
            setCurrentCenter={setCurrentCenter}
          />
          <SightingCard
            title="Plano, TX, USA"
            lat={33.006740}
            lng={-96.650440}
            sightings={10}
            notExtinct={5}
            endangered={1}
            setCurrentCenter={setCurrentCenter}
          /> */}
          
        </div>
      </div>
    </>
}

export default App;