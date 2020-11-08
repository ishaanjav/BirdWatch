import React, { useState } from "react";
import Map from "./Map.jsx";
import SearchBar from "./SearchBar.jsx";
import SightingCard from "./SightingCard.jsx";

function App(props) {
  // fucking states
  const [currentCenter, setCurrentCenter] = useState({lat: 43, lng: -80});
  const [birdData, setData] = useState({});



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