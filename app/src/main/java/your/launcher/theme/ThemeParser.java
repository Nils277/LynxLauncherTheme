/*
 * Copyright 2020 Nils Büscher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package your.launcher.theme;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

/**
 * This class parses the themes and retrieves the drawable, icon, color, dimension or boolean resources
 * YOU SHOULD NOT HAVE TO CHANGE ANYTHING IN THIS CLASS.
 * @author Nils Büscher
 */
class ThemeParser {

    //region Public Methods

    /**
     * Parse all themes defined in the themes.xml file
     * @param context The context of the application
     * @return The list of all themes that were parsed
     */
    static ArrayList<Theme> parseThemes(Context context) {
        ArrayList<Theme> themes = new ArrayList<>();

        try {
            XmlPullParser xmlPullParser = context.getResources().getXml(R.xml.themes);

            //Get the event type of the xmlParser
            int eventType = xmlPullParser.getEventType();


            //Load until the end of the xml is reached
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //If we have a start tag
                if(eventType == XmlPullParser.START_TAG) {
                    //change depending on the name of the xml tag
                    if (xmlPullParser.getName().equals("theme")) {
                        //Log.d("TTT","Found: theme!!");
                        Theme theme = new Theme();

                        //get the attributes from the theme
                        //---------------------------------
                        int attributeCount = xmlPullParser.getAttributeCount();
                        for (int i = 0; i < attributeCount; i++) {
                            //when we have found the title
                            if (xmlPullParser.getAttributeName(i).startsWith("title")) {
                                theme.name = getString(xmlPullParser.getAttributeValue(i), context);
                            } else if (xmlPullParser.getAttributeName(i).startsWith("mode")) {
                                theme.isDark = xmlPullParser.getAttributeValue(i).toLowerCase().equals("dark");
                            }
                            else if (xmlPullParser.getAttributeName(i).startsWith("wallpaper")) {
                                theme.hasWallpaper = true;
                            }
                        }
                        eventType = xmlPullParser.next();

                        while (eventType != XmlPullParser.END_TAG) {
                            if (eventType == XmlPullParser.START_TAG) {
                                String type = xmlPullParser.getName();
                                switch (type) {
                                    //an icon
                                    case "icon":
                                        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                                            if (xmlPullParser.getAttributeName(i).startsWith("name")) {
                                                String themeItemName = xmlPullParser.getAttributeValue(i);
                                                eventType = xmlPullParser.next();
                                                if (eventType == XmlPullParser.TEXT) {
                                                    theme.icons.put(themeItemName, xmlPullParser.getText());
                                                    eventType = xmlPullParser.next();
                                                    while (eventType != XmlPullParser.END_TAG) {
                                                        eventType = xmlPullParser.next();
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    //a boolean
                                    case "bool":
                                        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                                            if (xmlPullParser.getAttributeName(i).startsWith("name")) {
                                                String themeItemName = xmlPullParser.getAttributeValue(i);
                                                eventType = xmlPullParser.next();
                                                if (eventType == XmlPullParser.TEXT) {
                                                    theme.booleans.put(themeItemName,getBoolean(xmlPullParser.getText(), context));
                                                    eventType = xmlPullParser.next();
                                                    while (eventType != XmlPullParser.END_TAG) {
                                                        eventType = xmlPullParser.next();
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    case "dimen":
                                        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                                            if (xmlPullParser.getAttributeName(i).startsWith("name")) {
                                                String themeItemName = xmlPullParser.getAttributeValue(i);
                                                eventType = xmlPullParser.next();
                                                if (eventType == XmlPullParser.TEXT) {
                                                    theme.dimens.put(themeItemName, getDimen(xmlPullParser.getText(), context));
                                                    eventType = xmlPullParser.next();
                                                    while (eventType != XmlPullParser.END_TAG) {
                                                        eventType = xmlPullParser.next();
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    case "background":
                                        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                                            if (xmlPullParser.getAttributeName(i).startsWith("name")) {
                                                String themeItemName = xmlPullParser.getAttributeValue(i);
                                                eventType = xmlPullParser.next();
                                                if (eventType == XmlPullParser.TEXT) {
                                                    theme.backgrounds.put(themeItemName, xmlPullParser.getText());
                                                    eventType = xmlPullParser.next();
                                                    while (eventType != XmlPullParser.END_TAG) {
                                                        eventType = xmlPullParser.next();
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    case "color":
                                        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                                            if (xmlPullParser.getAttributeName(i).startsWith("name")) {
                                                String themeItemName = xmlPullParser.getAttributeValue(i);
                                                eventType = xmlPullParser.next();
                                                if (eventType == XmlPullParser.TEXT) {
                                                    String resourceName = xmlPullParser.getText().toLowerCase();
                                                    theme.colors.put(themeItemName, getResourceColor(resourceName, context));
                                                    eventType = xmlPullParser.next();
                                                    while (eventType != XmlPullParser.END_TAG) {
                                                        eventType = xmlPullParser.next();
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    default:
                                        eventType = xmlPullParser.next();
                                        while (eventType != XmlPullParser.END_TAG) {
                                            eventType = xmlPullParser.next();
                                        }
                                }
                            }
                            eventType = xmlPullParser.next();
                        }
                        themes.add(theme);
                    }
                }
                eventType = xmlPullParser.next();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return themes;
    }

    /**
     * Get the background of an item
     * @param item The string for the item
     * @param context The context of the application
     * @param optional When true, this background is allowed to be null
     * @return The background or null of not available and optional
     */
    static Drawable getBackground(String item, Context context, boolean optional) {
        return getBackground(item, context, optional, false);
    }

    /**
     * Get the background of an item
     * @param item The string for the item
     * @param context The context of the application
     * @param optional When true, this background is allowed to be null
     * @param removeMask When true, the layout with the ID "android.R.id.mask" are removed
     * @return The background or null of not available and optional
     */
    static Drawable getBackground(String item, Context context, boolean optional, boolean removeMask) {
        if (item == null) {
            if (optional) {
                return null;
            }
            Log.e("THEME ERROR","Drawable not defined");
            return new ColorDrawable(0xFFFF00FF);
        }

        if (item.startsWith("@drawable/")) {
            String name = item.substring(10);
            int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
            if (id == 0) {
                Log.e("THEME ERROR","Not able to find drawable for target: @drawable/" + name);
                return new ColorDrawable(0xFFFF00FF);
            }
            else {
                Drawable drawable = context.getResources().getDrawable(id);
                if (drawable instanceof LayerDrawable && removeMask) {
                    LayerDrawable layerDrawable = (LayerDrawable)drawable;
                    layerDrawable.setDrawableByLayerId(android.R.id.mask, null);
                }
                return drawable;
            }
        }
        else {
            return new ColorDrawable(getResourceColor(item, context));
        }
    }

    /**
     * Get the color for an item
     * @param color The color name of the item
     * @return The color
     */
    static int getColor(Integer color) {
        if (color == null) {
            Log.e("THEME ERROR", "Color not defined");
            return 0xFFFF00FF;
        }
        return color;
    }

    /**
     * Get an icon for the theme
     * @param item The string of the item to get
     * @param context The context of the application
     * @param defaultIcon The default icons for when the icons should just be colored
     * @return The icon for the theme
     */
    static Drawable getIcon(String item, Context context, Drawable defaultIcon) {
        if (item == null) {
            Log.e("THEME ERROR","Icon not defined");
            return new ColorDrawable(0xFFFF00FF);
        }
        if (item.startsWith("@drawable/")) {
            String name = item.substring(10);
            int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
            if (id == 0) {
                Log.e("THEME ERROR","Not able to find icon for target: @drawable/" + name);
                return new ColorDrawable(0xFFFF00FF);
            }
            else {
                return context.getResources().getDrawable(id);
            }
        }
        else {
            int color = getResourceColor(item, context);
            defaultIcon.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            return defaultIcon;
        }
    }

    //endregion

    //region Private Methods

    /**
     * Get the color from the resources
     * @param name The string for the color resource
     * @param context The context of the application
     * @return The color. A pink color of the color was not found or not correctly defined
     */
    private static int getResourceColor(String name, Context context) {
        int color = 0xFFFF00FF;

        //a color resource
        if (name.startsWith("@color/")) {
            name = name.substring(7);

            int resourceID = context.getResources().getIdentifier(name, "color", context.getPackageName());
            if (resourceID != 0) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        color = context.getResources().getColor(resourceID, null);
                    }
                    else {
                        color = context.getResources().getColor(resourceID);
                    }
                }
                catch (Exception e) {
                    //nothing to do here
                    Log.e("THEME ERROR","Not able to find color for target: @color/" + name);
                }
            }
            else {
                Log.e("THEME ERROR","No resource found for target: @color/" + name);
            }
        }
        //a color defined directly
        else {
            String original = name;
            if (name.startsWith("#")) {
                name = name.substring(1);
            }

            if (name.length() == 3 || name.length() == 4) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < name.length(); i++) {
                    result.append(name.substring(i, i));
                    result.append(name.substring(i, i));
                }
                name = result.toString();
            }

            //when the string does not have an alpha value, add it
            if (name.length() == 6) {
                name = "FF" + name;
            }

            if (name.length() == 8) {
                try {
                    color = (int)(long) Long.valueOf(name,16);
                }
                catch (Exception e) {
                    //invalid color nothing to do here
                    e.printStackTrace();
                    Log.e("THEME ERROR","Not able to parse color for target: " + original);
                }
            }
            else {
                Log.e("THEME ERROR","Invalid color definition for target: " + original);
            }
        }
        return color;
    }

    /**
     * Get a string from the resources
     * @param name The string for the string resource
     * @param context The context of the application
     * @return The string
     */
    private static String getString(String name, Context context) {
        if (name.startsWith("@string/")) {
            name = name.substring(8);
            int stringID = context.getResources().getIdentifier(name, "string", context.getPackageName());
            try {
                return context.getResources().getString(stringID);
            }
            catch (Exception e) {
                //invalid color nothing to do here
                Log.e("THEME ERROR", "Not able to find string for target: @string/" + name);
            }
        }
        else if (name.startsWith("@")) {
            int stringID = Integer.parseInt(name.substring(1));
            try {
                return context.getResources().getString(stringID);
            }
            catch (Exception e) {
                //invalid color nothing to do here
                Log.e("THEME ERROR", "Not able to find string for target: @string/" + name);
            }
        }
        Log.d("TTT","name: " + name);
        return name;
    }

    /**
     * Get a dimension from the resources
     * @param name The string for the dimension resource
     * @param context The context of the application
     * @return The dimension
     */
    private static int getDimen(String name, Context context) {
        if (name.startsWith("@dimen/")) {
            name = name.substring(7);
            int resourceID = context.getResources().getIdentifier(name, "dimen", context.getPackageName());
            try {
                return (int)context.getResources().getDimension(resourceID);
            }
            catch (Exception e) {
                //invalid color nothing to do here
                Log.e("THEME ERROR", "Not able to find boolean for target: @dimen/" + name);
            }
        }
        else {
            int typedValue = 0;
            String val = name;
            String newString = name.substring(0,name.length()-2);

            if (name.endsWith("dp")) {
                typedValue = TypedValue.COMPLEX_UNIT_DIP;
                name = newString;
            }
            else if (name.endsWith("dip")) {
                typedValue = TypedValue.COMPLEX_UNIT_DIP;
                name = name.substring(0,name.length()-3);
            }
            else if (name.endsWith("pt")) {
                typedValue = TypedValue.COMPLEX_UNIT_PT;
                name = newString;
            }
            else if (name.endsWith("sp")) {
                typedValue = TypedValue.COMPLEX_UNIT_SP;
                name = newString;
            }
            else if (name.endsWith("in")) {
                typedValue = TypedValue.COMPLEX_UNIT_IN;
                name = newString;
            }
            else if (name.endsWith("mm")) {
                typedValue = TypedValue.COMPLEX_UNIT_MM;
                name = newString;
            }
            else if (name.endsWith("px")) {
                name = newString;
            }
            try {
                return (int) TypedValue.applyDimension(typedValue, Integer.parseInt(name), context.getResources().getDisplayMetrics());
            }
            catch (Exception e) {
                Log.e("THEME ERROR", "Invalid value definition for target: " + val);
                //nothing to do here
            }
        }
        return 0;
    }

    /**
     * Get a boolean from the resources
     * @param name The string for the boolean resource
     * @param context The context of the application
     * @return The boolean value
     */
    private static boolean getBoolean(String name, Context context) {
        if (name.startsWith("@bool/")) {
            name = name.substring(6);
            int id = context.getResources().getIdentifier(name, "bool", context.getPackageName());
            if (id != 0) {
                try {
                    return context.getResources().getBoolean(id);
                }
                catch (Exception e) {
                    //Nothing to do here
                }
            }
            else {
                Log.e("THEME ERROR", "Not able to find boolean for target: @bool/" + name);
            }
        }
        else {
            return name.toLowerCase().equals("true");
        }
        return false;
    }

}
