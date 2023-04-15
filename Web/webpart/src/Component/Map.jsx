import { GoogleMap, useJsApiLoader, MarkerF, HeatmapLayerF } from '@react-google-maps/api'
import '../Styles/Mainpage.css'
import React, { useState } from 'react';
import usePlacesAutocomplete, { getGeocode, getLatLng } from 'use-places-autocomplete';
import { Combobox, ComboboxInput, ComboboxPopover, ComboboxList, ComboboxOption } from "@reach/combobox";
import "@reach/combobox/styles.css";
const GetMap = () => {
    const { isLoaded } = useJsApiLoader({
        googleMapsApiKey: "AIzaSyDj25GVFY21IxqtFD_KC_dZanHnn67H91w", //process.env.REACT_APP_map_api,
        libraries: ["visualization", "places"],
    });

    if (!isLoaded) return <>Loading....</>
    return (<Gmap />);
}

const Gmap = () => {
    let coords = { lat: 25.6208, lng: 85.1720 };
    const [selected, setSelected] = useState(null);
    const HeatmapDataArray = [
        new window.google.maps.LatLng(25.6218, 85.3720),
        new window.google.maps.LatLng(25.6228, 85.1420),
        new window.google.maps.LatLng(25.6238, 85.2720),
        new window.google.maps.LatLng(25.6248, 85.1730),
        new window.google.maps.LatLng(25.6258, 85.1230),
        new window.google.maps.LatLng(25.6278, 85.1760),
        new window.google.maps.LatLng(25.6215, 85.1790),
        new window.google.maps.LatLng(25.6223, 85.1700),
        new window.google.maps.LatLng(25.6254, 85.1450),
        new window.google.maps.LatLng(25.6276, 85.1650),
        new window.google.maps.LatLng(25.6280, 85.1230),
        new window.google.maps.LatLng(25.6209, 85.1980),
        new window.google.maps.LatLng(25.6206, 85.1050),
        new window.google.maps.LatLng(25.6234, 85.1450)
    ];
    if(selected !=null)
      coords = selected;
    return (
        <>
            <GoogleMap
                zoom={10}
                center={coords}
                mapContainerClassName='left_div'>
                <MarkerF position={coords} />
                <HeatmapLayerF data={HeatmapDataArray} />
                <div className='places-container' >
                    <PlaceAutoComplete setSelected={setSelected} />
                </div>
            </GoogleMap>
        </>
    )
}
const PlaceAutoComplete = ({ setSelected }) => {
    const { ready, value, setValue, suggestions: { status, data }, clearSuggestions } = usePlacesAutocomplete();
    const onSelect = async (address) => {
        setValue(address, false);
        clearSuggestions();
        const results = await getGeocode({ address });
        const{ lat,lng} = getLatLng(results[0]);
        console.log(results);
        setSelected({ lat, lng });
    }
    return (
        <Combobox onSelect={onSelect}>
            <ComboboxInput value={value} onChange={e => setValue(e.target.value)} disabled={!ready} className='combobox-input' placeholder="Search Address" />
            <ComboboxPopover>
                <ComboboxList>
                    {status === "OK" && data.map(({ place_id, description }) => (
                        <ComboboxOption key={place_id} value={description} />
                    ))}
                </ComboboxList>
            </ComboboxPopover>
        </Combobox>
    )
}
export default GetMap;