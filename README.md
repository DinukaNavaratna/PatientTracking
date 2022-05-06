# PatientTracking

This is a simple Android mobile application for monitoring a patient. This app contains few simple features such as FCM push notifications, keeping notes, adding reminders, and saving patient details.<br>
Please note that this is a simple Android app created within 2/3 days maximum according to the requirements I was given. So, the application can be improved alot more and you are welcome to make improvements and create a PR.

## Testing

1. Create a Firebase project and replace the '<a href="https://github.com/DinukaNavaratna/PatientTracking/blob/main/app/google-services.json">app/google-services.json</a>' file with your own project file taken from Firebase project you created.
2. A postman request collection has been included in '<a href="https://github.com/DinukaNavaratna/PatientTracking/blob/main/app/src/test/postman/">app/src/test/postman/</a>' for testing the FCM push notifications. Replace the existing 'Device_Token' & 'Firebase_Auth_Key' in Postman collection variabled with your own details/credentials.
3. You can copy the FCM Token of the device by hitting the back button from the 'Home' page of the application. Firebase Auth Key can be taken from the firebase project you created.
