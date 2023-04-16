import { GoogleMap, useJsApiLoader, MarkerF, HeatmapLayerF } from '@react-google-maps/api'
import '../Styles/Mainpage.css'
import React, { useEffect, useMemo, useState } from 'react';
import usePlacesAutocomplete, { getGeocode, getLatLng } from 'use-places-autocomplete';
import { Combobox, ComboboxInput, ComboboxPopover, ComboboxList, ComboboxOption } from "@reach/combobox";
import "@reach/combobox/styles.css";
import { useFirebase } from '../context/firebase';
import { collection, getDocs } from 'firebase/firestore';
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
    const firebase = useFirebase();
    const HeatmapDataArray = [];
    useEffect(() => {
        const collectionRef = getDocs(collection(firebase.firebasestore, "Garbage"))
        collectionRef.then(
            querySnapshot => {
                querySnapshot.forEach((doc) => {
                    const data = doc.data();
                    const lat = data.latitude;
                    const lng = data.longitude;
                    const weight = data.amount;
                    const location = new window.google.maps.LatLng(lat, lng);
                    HeatmapDataArray.push({ location, weight });
                })
            })
    }, [HeatmapDataArray, firebase.firebasestore]);
    if (selected != null)
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
        const { lat, lng } = getLatLng(results[0]);
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