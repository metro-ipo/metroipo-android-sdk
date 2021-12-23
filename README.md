# metroipo-android-sdk  
Official Android Implementation of the Orba One SDK.

## 1. Install as a Gradle Plugin  
The SDK works on API 21+. To fetch with Gradle, make sure you add the Orba One maven repository in your root project's build.gradle file:

```gradle
repositories {
  ...
  mavenCentral()
}
```

Then add the following dependency to your app build.gradle file. You should replace the `+` with your desired version of the SDK.
```gradle
dependencies {
    ...
    implementation 'com.metroipo:metro-ipo-sdk:+'
}
```

Now sync your build gradle to install the sdk.

## 2. Initializing the SDK

The Metro IPO SDK can be initialized by supplying the `domain name` of the website that hosts Metro IPO and the user captured `verification code`. A sample implementation is shown below.

```java
import com.metroipo.sdk.MetroIpoSdk;

String mCode = codeTextField.getText().toString();
MetroIpoSdk sdk = new MetroIpoSdk sdk = new MetroIpoSdk.Builder()
  .setCode(mCode) // VERIFICATION CODE
  .setDomain("https://METROIPO-SERVER") // METRO IPO DOMAIN
  .create();
sdk.start(this);
```

## 3. Handling Callbacks  
The Metro IPO Sdk exposes two callbacks in order to let you know if the user has completed or cancelled the signature capture flow. A third callback (onStart) is also supplied to alert you if the user has successfully began the flow.

```java
sdk.onStart(new MetroIpoSdk.Response() {
    @Override
    public void onSuccess() {
        // Sdk started successfully
    }

    @Override
    public void onFailure(String message) {
        // Code verification failed 
    }
});

sdk.onComplete(new MetroIpoSdk.Callback() {
    @Override
    public void execute() {
        // Signature successfully uploaded
    }
});

sdk.onCancel(new MetroIpoSdk.Callback() {
    @Override
    public void execute() {
        // Signature upload cancelled by the user.
    }
});
```

## 6. Customizing the Theme  
To ensure that the Metro IPO SDK fits in to your app's existing user experience, you can customize various colors by overriding the following in your ``colors.xml`` file.

```metroColorPrimary```: Defines the background color of the Toolbar.\
```metroColorSecondary```: Defines the color of the Back button and the text color of the Title in the Toolbar.\
```metroColorButtonPrimary```: Defines the background color of Primary Buttons and the text color of Secondary Buttons.\
```metroColorButtonPrimaryText```: Defines the text color of Primary Buttons.\
```metroColorButtonPrimaryPressed```: Defines the background color of Primary Buttons when pressed.

## Sample App
A sample app demonstrating the Orba One SDK's implementation has been included. See the [AndroidSample directory](https://github.com/metro-ipo/metroipo-android-sdk/tree/master/AndroidSample) for the Android - Java implementation.

## Support

Please post all issues through [Github](https://github.com/metro-ipo/metroipo-android-sdk/issues). If your query involves sensitive information, you may contact us at dev@orba.io with the subject `ANDROID ISSUE: `.

Copyright 2021 Orba One Technology Holdings Ltd. All rights reserved.
