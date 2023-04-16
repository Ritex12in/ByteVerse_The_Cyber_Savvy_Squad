Drive Link for the video
https://drive.google.com/drive/folders/1dXK2svzG4JYL1Cy74tWuSuOsaOD13Bjt?usp=sharing

To build android app I have uses Android Studio Platform in Kotlin language

I have hide api-key from gitHub so if you want to run you have to insert and api-key
for maps and one for firebase.

dependencies used:

plugins {
    id 'com.google.gms.google-services'
}

this plugin is for google services like map, location

dependency use for user authentication:
implementation 'com.google.firebase:firebase-auth-ktx'

dependency for Firestore where we store user data and other informations
implementation 'com.google.firebase:firebase-firestore-ktx'

dependency for loglin with google:
implementation 'com.google.android.gms:play-services-auth:20.5.0'
implementation platform('com.google.firebase:firebase-bom:31.5.0')

Dependencies to use google map:
    implementation 'com.google.android.gms:play-services-maps:18.1.0'
    implementation 'com.google.maps.android:android-maps-utils:2.3.0'
    implementation 'com.google.maps.android:maps-utils-ktx:3.4.0'
    
    
Dependency to search for places i.e autofill for places:
implementation 'com.google.android.gms:play-services-location:21.0.1'
implementation 'com.google.android.libraries.places:places:3.1.0'


Dependecies to get permision for location
implementation 'com.karumi:dexter:6.2.3'

Dependencies to use circular image view:
implementation 'de.hdodenhof:circleimageview:3.1.0'


Dependencies to use firebse storage where we have stored our media files:
implementation 'com.google.firebase:firebase-storage-ktx'
implementation 'com.firebaseui:firebase-ui-storage:7.2.0'

Dependencies to use line graphs and piecharts:
implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'
    
    


