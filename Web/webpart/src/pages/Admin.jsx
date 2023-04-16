import React,{useState} from "react";
import LocationforAdmin from "./getLocation";
import { useFirebase } from "../context/firebase";
import {collection,addDoc} from "firebase/firestore"


const Admin= () => {
    const firebase = useFirebase();
    const [latitude,setLatitude]=useState("");
    const [longitude,setLongitude]=useState("");
    const Handlesubmit= async(e)=>{
        e.preventDefault();
        await addDoc(collection(firebase.firebasestore,'Admin',firebase.uid,'Position'),{
            latitude: parseFloat(latitude),
            longitude: parseFloat(longitude),
           });        
    }
    return(
        <>
        <LocationforAdmin/>
        <div>

        </div>
        <h1>Enter the coordinates for dustbin to be placed</h1>
        <form onSubmit={Handlesubmit} className="adm">
            <label  htmlFor="name">Latitude:</label>
            <input  name="latitude"
            onChange={e=>setLatitude(e.target.value)}
            value={latitude} placeholder="Enter Lat"/>
            <label htmlFor="name">Longitude:</label>
            <input  name="longitude" 
             onChange={e=>setLongitude(e.target.value)}
             value={longitude} placeholder="Enter Long"/>
             
            <button type="submit">submit</button>
        </form>
        </>
    )
}
export default Admin;