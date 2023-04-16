import {initializeApp} from 'firebase/app'
import React,{useContext,createContext, useState, useEffect} from 'react'
import {getAuth,signInWithEmailAndPassword,GoogleAuthProvider,signInWithPopup,onAuthStateChanged,signOut} from "firebase/auth"
import {getFirestore} from "firebase/firestore"

const firebasecontext= createContext(null);
const firebaseConfig = {
    apiKey: "AIzaSyD_gM1K3bSabC7XDzuCg8hBGss4-GGok54",//process.env.REACT_APP_firebaseConfig,
    authDomain: "trash-tracker-15722.firebaseapp.com",
    projectId: "trash-tracker-15722",
    storageBucket: "trash-tracker-15722.appspot.com",
    messagingSenderId: "949815088847",
    appId: "1:949815088847:web:a49227c577f9b40576c9ba"
  };
  export const useFirebase =() => useContext(firebasecontext);

  export const FirebaseProvider = (props) => {
    const firebaseapp = initializeApp(firebaseConfig);
    const firebaseauth = getAuth(firebaseapp);
    const googleProvider = new GoogleAuthProvider();
    const firebasestore = getFirestore(firebaseapp);

    const [user,setUser] = useState(null);
    useEffect(() => {
        onAuthStateChanged(firebaseauth,user => {
            if(user) setUser(user);
            else setUser(null);
        })
    },[firebaseauth]);

    const signin =(email,password) =>
    signInWithEmailAndPassword(firebaseauth,email,password);
    
    const signinWithGoogle = () =>
    signInWithPopup(firebaseauth,googleProvider);

    const isLoggedin = user?true:false;
    const Logout = () => {signOut(firebaseauth)};

    return <firebasecontext.Provider value={{firebaseapp,signin,signinWithGoogle,isLoggedin,Logout,firebasestore}}>
        {props.children}
    </firebasecontext.Provider>
  };