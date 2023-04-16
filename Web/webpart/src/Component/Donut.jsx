import React from "react";
import Chart from "react-apexcharts";
import { getDoc, doc } from 'firebase/firestore'
import { useEffect } from "react";
import { useFirebase } from "../context/firebase";

function Donut() {
    const firebase = useFirebase();
    const WasteType = [];


    useEffect(() => {
        try {
            const docRef = doc(firebase.firebasestore, 'Constants', "WasteTypeData")
            const snap = getDoc(docRef);
            snap.then(doc => {
                if (doc && doc.exists) {
                    WasteType.push(doc.data().plasticWaste, doc.data().generalWaste);
                    console.log(doc.id, '=>', doc.data());
                    console.log(WasteType);
                }
            })
        }
        catch (err) {
            console.log(err);
        }
    })

    return (
        < div >
            <Chart
                type="donut"
                width='125%'
                height='auto'
                series={WasteType}

                options={{

                    labels: ['Plastic', 'General waste'],
                }}

            />
        </div >


    );
}
export default Donut;