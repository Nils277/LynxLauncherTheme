# LynxLauncherTheme

This repository contains the Android Studio project for the "Simple Theme" for the Lynx Launcher Application for Android.

The purpose of this template project is to demonstrate, how you can create a new theme for the Lynx Launcher on your own. I hope this project will help you get started. The code is documented with comments to explain what is done. If you have any questions or stumble upon wrong or outdated information feel free to contact me.

### Requirements
Before you start creating a theme you nedd to have the latest version of Android Studio installed on your computer. To learn how to install and use Android Studio please start here:
[Google Developer: Android Studio](https://developer.android.com/studio)

You need some basic knowledge on how to used Android Studio to build and run an Application and have a little knowledge about programming and the file structure of an Android Studio project. Don't be afraid though, you don't need to be an advanced programmer to create your new beautiful theme!

### Setup

1. Download or clone this repository and open it with Android Studio

2. Open the *java* folder and navigate to the folder where the **MainActivity** is located. You will see that the parent folders are named: your, launcher and theme. This is the package name of your app used to identify it in the play store. (It is not the name displayed to the user). Change the names of the folders to the package name you want. E.g. "my", "fabulous" and "theme". To change a name right-click on a folder you want to change e.g. "theme" and select *Refactor* → *Rename*. And change the name. Click *Refactor* in the dialog to continue. Android studion will show you a *Refactoring Preview" at the bottom. Click *Do Refactor* to finish renaming the folder.

3.  This step is **optional** when the refactoring causes errors. Fix any errors that happen. E.g. make sure that the package names in the *AndroidManifest* at the lines 4 and 17 fit the package name you selected.

4. Navigate to *res → values → strings.xml*. Read the comments and change the lines 18, 21 and 24 accordingly.

With this step, the setup of the project is done and you can start the actual theming.

### Create a theme

At first you should take a look at the file *res → xml→ themes.xml*. This file is where you will be changing the most. It contains all the information that will be read by Lynx Launcher the get the colors, drawables and settings of your theme.
At line 24 you can see the header of a **theme** which contains the xml attributes *title*, *mode*, *icon* and *preview*
- **title** is the name of your theme. You only need to change this value when you have more than one theme in your app. This item accepts a **string resource**.
- **mode** is the dark mode of your app. It can either be "light" or "dark". You can support dark and light modes of your theme when you have two themes with the same **title** and different modes. When you don't want to support dark and light mode, the value of this field does not matter.
- **icon** is the name of the drawable that is used as an icon for your theme. This field accepts a. You don't have to change this value for now.
- **preview** is the name of the imge that will be shown as a preview of your theme in Lynx Launcher. You don't have to change this value for now.

Inside the **theme** you will see items called *icon*, *background*, *color*, *boolean* and *value*. Each item stands for a different themable part of the launcher and can be changed to your likings. The **name** attribute is used to tell Lynx Launcher where the item should be used.
You can find a list of all available name attributes here: [Themable Items](TODO). Please read this document carefully to make sure that your theme is working as intended.

**Now you can finally start theming!**
Go through the items and decide which one you want to change, add or remove. Each type of the item can accept different types of resources explained here: [Item types and resources](TODO).
Change the existing drawables or colors and add or remove themable items as you see fit.

Many items in the theme are referring to a color. You can find all colors in the file res → values → colors.xml. You can edit these colors as you see fit or also add new ones.
Drawables can be found in res → drawables*.

**Preview your theme**
You can get a first impression of the visual appearance of your new theme when you run this application on an emulated or real device. The theme of the preview and the main tab should automatically use the new theme items you have defined in the *themes.xml* file.

**Test your theme**
To test the final theme you should run your theme on a device where Lynx Launcher is installed and apply your theme to the launcher.
**CAUTION:** Due to the way Android Studio updates you running application, Lynx Launcher does not get notified when you update your Theme App via Android Studio. Therefore the latest changes might not appear in Lynx Launcher. To reload the new theme, open you Theme App and click "Apply Theme" to apply the latest version.

**Final touches**
Now the you are almost done with you theme and are happy with the results it is time to make the final touches before you can publish your beautiful new theme.
These final touches are a **preview**, a **background image**, an **application icon** and a **theme icon**.

The **background image** or *wallpaper* is used inside the theme app as a background for the items in the preview tab. You can choose any image you like. For now it is NOT supported that your **background image** can also be set as the wallpaper on the phone by applying the theme to Lynx Launcher. This will be added at a later time. Name your background image "background.jpg" and place it into the *app/src/main/res/drawable-nodpi* folder inside your project directory.

To create the **preview**, run your finished theme on a device, apply it to the Lynx Launcher and make a screenshot. Rename the screenshot to "screenshot.jpg" and copy it into the *app/src/main/res/drawable-nodpi* folder inside your project directory.

The last thing you have to add are icons. If you only have one theme defined, it is suggested to use the same icon for both, the theme and your app. But you can still use diffent ones if you like.
The **theme icon** should have a size of 48dp x 48dp and should be named *ic_theme*.
Last but not least, create a **application icon** as described [at this site](https://developer.android.com/studio/write/image-asset-studio).

### Release
Congratulations, you are now done with your own theme. [Create a signed APK or Bundle](https://developer.android.com/studio/publish/preparing#publishing-build) and release it on Google Play.
If you like, you can let me know that you created a theme which is available on Play Store. I plan to create a list of available themes on the Lynx Launcher website and am happy to hear from you.


### Feedback
If you have feedback about things that are unclear, out of date or wrong, feel free to contact me, so i can improve this document and the application.
You can also make pull requests if you updated, improved or fixed any parts of this theme example.

Thanks for reading and cheers!
