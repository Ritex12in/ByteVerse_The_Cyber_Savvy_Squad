import React from "react";
import Chart from "react-apexcharts";
import { getDoc, doc } from 'firebase/firestore'
import { useEffect } from "react";
import firebasestore from "../db";

function Donut() {
    const WasteType = [];


    useEffect(() => {
        try {
            const docRef = doc(firebasestore, 'Constants', "WasteTypeData")
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
                width={400}
                height={450}
                series={WasteType}

                options={{

                    labels: ['Plastic', 'General waste'],
                }}

            />
        </div >


    );
}
export default Donut;