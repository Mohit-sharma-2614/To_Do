This is a Kotlin Multiplatform project targeting Android.


A simple Demo App to showcase the ability of KMP + CMP in real life projects. The App is totally built for Android only but Used the Common Main Package to do the work which is the Shared module for code b/w different platforms.


This is a simple use case of KMP + CMP
Here we define a `Task` model that we want to store in Firebase.
Firebase is by default supported Natively on Android and IOS but, when we want to use it in a common b/w different platform this can not
be done, and specially if we want to share the data model in `commonMain` and UI then it become even more challenging, this is where
the GitLive library comes in action which wraps the native library for IOS and Android and gives us a wrapper over it which we can use
In our Project in `commonMain`. 
Still we have to initialize the firebase as we do in Native code bases.