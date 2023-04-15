import {initializeApp} from 'firebase/app'
import React,{useContext,createContext} from 'react'

const firebasecontext= createContext(null);
const firebaseConfig = {
    apiKey: process.env.REACT_APP_firebaseConfig,
    authDomain: "trash-tracker-15722.firebaseapp.com",
    projectId: "trash-tracker-15722",
    storageBucket: "trash-tracker-15722.appspot.com",
    messagingSenderId: "949815088847",
    appId: "1:949815088847:web:a49227c577f9b40576c9ba"
  };
  export const useFirebase =() => useContext(firebasecontext);

  export const FirebaseProvider = (props) => {
    const firebaseapp = initializeApp(firebaseConfig);
    return <firebasecontext.Provider value={{}}>
        {props.children}
    </firebasecontext.Provider>
  };