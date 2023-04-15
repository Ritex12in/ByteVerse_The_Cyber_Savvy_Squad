import { GoogleMap, useJsApiLoader,MarkerF } from '@react-google-maps/api'
import '../Styles/Mainpage.css'


const GetMap=() =>{
    const { isLoaded }= useJsApiLoader({
        googleMapsApiKey: "AIzaSyDj25GVFY21IxqtFD_KC_dZanHnn67H91w", //process.env.REACT_APP_map_api,
    });

    if(!isLoaded) return <>Loading....</>
    return(<Gmap/>);
}

const Gmap= () => {
    let coords = {lat:25.6208, lng: 85.1720};
    return(
        <>
        <GoogleMap
          zoom={10}
          center={coords}
          mapContainerClassName='left_div'>
           <MarkerF position={coords}/> 
          </GoogleMap>
        </>
    )
}
export default GetMap;