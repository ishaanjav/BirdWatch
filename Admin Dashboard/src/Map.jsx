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
       {props.isMarkerShown && (
        <>
          <Marker
            position={{ lat: 43.397, lng: -80.644 }}
            icon="https://www.robotwoods.com/dev/misc/bluecircle.png"
          />
        </>
      )}
    </GoogleMap>
  ))
);


function Map(props) {
  return <>
    <div className="flex-1 h-full overflow-y-hidden">
      <MapComponent
        isMarkerShown
        googleMapURL="https://maps.googleapis.com/maps/api/js?key=AIzaSyC9u0a8wUPG0p0pw_Ym_pmV_gkvd1jUfIs&v=3.exp"
        loadingElement={<div style={{ height: `100%` }} />}
        containerElement={<div style={{ height: `100vh` }} />}
        mapElement={<div style={{ height: `100%` }} />}
        center={props.currentCenter}
        />
    </div>
  </>
}

export default Map;