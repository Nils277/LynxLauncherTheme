## Drawable
A drawable can either be an xml file, a vector graphic imported into Android Studio or a bitmap image. To learn more about drawables on 
Android, please read the [documantation about drawables](https://developer.android.com/guide/topics/resources/drawable-resource).

In your *themes.xml* a drawable resource is defined in the same way as a reference to a drawable in the rest of Android Studio. 
It always starts with **@drawable/** + **DRAWABLE_NAME**. Where **DRAWABLE_NAME** is the name of your drawable.

## Color
A color can either be a value defined in the *res→values→colors.xml* or be directly written in the *themes.xml* file with **#** + **COLOR**. 
To use a color from *res→values→colors.xml*, you have to write **@color/** + **COLOR_NAME**. To see how you can define a **COLOR** value 
directly in the *themes.xml*, read the [documentation about colors](https://developer.android.com/guide/topics/resources/more-resources#Color).

## Boolean
A boolean value can either be a value defined in *res→values→booleans.xml* or be directly written in the *themes.xml* file. To write the 
value directly into the *themes.xml* file, simply write **true** or **false**. To use a value *res→values→booleans.xml*, write **@bool/** + **BOOLEAN_NAME**. 
For more information about booleans read [the documentation](https://developer.android.com/guide/topics/resources/more-resources#Bool).

## Dimension
A dimension depicts a size or distance in the UI. This size can be defined with different units like pixel, DPI, Inches or others. You can either use a 
dimension value defined in *res→values→dimens.xml* by writing **@dimen/** + **DIMENSION_NAME** or directly write the value with a unit into the *themes.xml* file. 
You can find the supported units, their explanation and mor info in the [documentation about dimension resources](https://developer.android.com/guide/topics/resources/more-resources#Dimension).

## Strings
Strings can either be written directly in the *themes.xml* or taken from the *res→values→strings.xml* file. To use a value from *res→values→strings.xml*, 
write **@string/** + **STRING_NAME*. You can find more information about strings in [the documuntation](https://developer.android.com/guide/topics/resources/string-resource).