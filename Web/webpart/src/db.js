import { getFirestore } from 'firebase/firestore'
import { initializeApp } from "firebase/app";


const firebaseConfig = {
    apiKey: "AIzaSyD_gM1K3bSabC7XDzuCg8hBGss4-GGok54",
    authDomain: "trash-tracker-15722.firebaseapp.com",
    projectId: "trash-tracker-15722",
    storageBucket: "trash-tracker-15722.appspot.com",
    messagingSenderId: "949815088847",
    appId: "1:949815088847:web:a49227c577f9b40576c9ba"
};




const app = initializeApp(firebaseConfig);
const firebasestore = getFirestore(app);
export default firebasestore
