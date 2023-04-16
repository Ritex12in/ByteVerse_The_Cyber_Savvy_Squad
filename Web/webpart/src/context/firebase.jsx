import {initializeApp} from 'firebase/app'
import React,{useContext,createContext, useState, useEffect} from 'react'
import {getAuth,signInWithEmailAndPassword,onAuthStateChanged,signOut} from "firebase/auth"
import {getFirestore} from "firebase/firestore"

const firebasecontext = createContext(null);
const firebaseConfig = {
    apiKey: "AIzaSyAWgCLVfRywJkGVQxQyCfFkBByNX-AX5O4",
    authDomain: "garbagelocator-41283.firebaseapp.com",
    projectId: "garbagelocator-41283",
    storageBucket: "garbagelocator-41283.appspot.com",
    messagingSenderId: "192958369223",
    appId: "1:192958369223:web:b735d9ed3c228297f29de2",
    measurementId: "G-6Q9L2QX2H8"
  

  };
  export const useFirebase =() => useContext(firebasecontext);

export const FirebaseProvider = (props) => {
    const firebaseapp = initializeApp(firebaseConfig);
    const firebaseauth = getAuth(firebaseapp);
    const firebasestore = getFirestore(firebaseapp);

    const [user, setUser] = useState(null);
    useEffect(() => {
        onAuthStateChanged(firebaseauth, user => {
            if (user) setUser(user);
            else setUser(null);
        })
    }, [firebaseauth])

    const signin =(email,password) =>
    signInWithEmailAndPassword(firebaseauth,email,password);
    
    const isLoggedin = user?true:false;
    const uid =isLoggedin&&user.uid;
    const Logout = () => {signOut(firebaseauth)};

    return <firebasecontext.Provider value={{uid,firebaseapp,signin,isLoggedin,Logout,firebasestore}}>
        {props.children}
    </firebasecontext.Provider>
};