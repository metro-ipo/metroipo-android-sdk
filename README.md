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

Then add the following dependency to your app build.gradle file.
```gradle
dependencies {
    ...
    implementation 'com.metroipo:metro-ipo-sdk:0.0.5'
}
```

Now sync your build gradle to install the sdk.

## 2. Initializing the SDK

The Metro IPO SDK can be initialized by supplying the `DOMAIN NAME` of the website that hosts the Metro IPO admin dashboard. After this, starting the SDK requires the user entered `VERIFICATION CODE`. A sample implementation is shown below.

```java
import com.metroipo.sdk.MetroIpoSdk;

// Initialize the SDK
MetroIpoSdk sdk = new MetroIpoSdk.Builder()
  .setDomain("METROIPO-ADMIN-SERVER") //  // Domain/Hostname should be added without "https://" or trailing slash e.g admin.metroipo.com
  .create();

// Pass verification code to SDK
String mCode = codeTextField.getText().toString(); // VERIFICATION CODE
sdk.start(mCode,this);
```

## 3. Handling Callbacks  
The Metro IPO Sdk exposes two callbacks in order to let you know if the user has completed or cancelled the signature capture flow. A third callback (onStart) is also supplied to alert you if the user has successfully began the flow.

```java
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
```

## 6. Customizing the Theme 
### Colors
To ensure that the Metro IPO SDK fits in to your app's existing user experience, you can customize various colors by overriding the following in your ``colors.xml`` file.

```metroColorPrimary```: Defines the background color of the Toolbar.\
```metroColorSecondary```: Defines the color of the Back button and the text color of the Title in the Toolbar.\
```metroColorButtonPrimary```: Defines the background color of Primary Buttons and the text color of Secondary Buttons.\
```metroColorButtonPrimaryText```: Defines the text color of Primary Buttons.\
```metroColorButtonPrimaryPressed```: Defines the background color of Primary Buttons when pressed.

### Buttons
You can also override the corner radius of buttons by specifying the new button with in your ``dimens.xml`` file using `metro_sdk_button_corner_radius`.

### Branding
You are also able to customize a number of images, **using drawables**, on the signature pad view: 

```metro_sdk_logo_center```: Adds an image to the center of the navigation bar.\
```metro_sdk_logo_right```: Adds an image to the right of the navigation bar.\
```metro_sdk_logo_bottom```: Adds an image to the between the clear and submit buttons.\
```metro_sdk_nav_background```: Adds a background image to the navigation bar. A color can also be specified.

## Sample App
A sample app demonstrating the Orba One SDK's implementation has been included. See the [AndroidSample directory](https://github.com/metro-ipo/metroipo-android-sdk/tree/master/AndroidSample) for the Android - Java implementation.

## Support

Please post all issues through [Github](https://github.com/metro-ipo/metroipo-android-sdk/issues). If your query involves sensitive information, you may contact us at dev@orba.io with the subject `ANDROID ISSUE: `.

Copyright 2021 Orba One Technology Holdings Ltd. All rights reserved.
