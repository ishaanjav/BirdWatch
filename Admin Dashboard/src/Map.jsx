import React from "react";


import {
  withScriptjs,
  withGoogleMap,
  GoogleMap,
  Marker,
} from "react-google-maps";

const MapComponent = withScriptjs(
  withGoogleMap((props) => (
    <GoogleMap defaultZoom={6} center={props.center}>
          {/* get from firebase */}
          {props.markers.map((value, key) => {
            return <Marker
            key={key}
            position={{ lat: value[0], lng: value[1] }}
            icon="https://www.robotwoods.com/dev/misc/bluecircle.png"
          />
          })}
          {/* placeholders */}
          {/* <Marker
            position={{ lat: 43.397, lng: -80.644 }}
            icon="https://www.robotwoods.com/dev/misc/bluecircle.png"
          />
          <Marker
            position={{ lat: 43.1385, lng: -79.4845 }}
            icon="https://www.robotwoods.com/dev/misc/bluecircle.png"
          />
          <Marker
            position={{ lat: 33.0334, lng: -96.6752 }}
            icon="https://www.robotwoods.com/dev/misc/bluecircle.png"
          />
          <Marker
            position={{ lat: 33.006740, lng: -96.650440 }}
            icon="https://www.robotwoods.com/dev/misc/bluecircle.png"
          /> */}
    </GoogleMap>
  ))
);


function Map(props) {
  return <>
    <div className="flex-1 h-full overflow-y-hidden">
      <MapComponent
        googleMapURL="https://maps.googleapis.com/maps/api/js?key=AIzaSyC9u0a8wUPG0p0pw_Ym_pmV_gkvd1jUfIs&v=3.exp"
        loadingElement={<div style={{ height: `100%` }} />}
        containerElement={<div style={{ height: `100vh` }} />}
        mapElement={<div style={{ height: `100%` }} />}
        center={props.currentCenter}
        markers = {props.locations}
        />
    </div>
  </>
}

export default Map;