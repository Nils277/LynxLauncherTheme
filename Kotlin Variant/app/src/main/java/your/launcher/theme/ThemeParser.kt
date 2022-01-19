/*
 * Copyright 2022 Nils Büscher
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
package your.launcher.theme

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.util.Log
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import org.xmlpull.v1.XmlPullParser
import java.lang.Exception
import java.lang.StringBuilder
import java.util.*

/**
 * This class parses the themes and retrieves the drawable, icon, color, dimension or boolean resources
 * YOU SHOULD NOT HAVE TO CHANGE ANYTHING IN THIS CLASS.
 * @author Nils Büscher
 */
class ThemeParser {

    //region Public Methods

    companion object {
        /**
         * Parse all themes defined in the themes.xml file
         * @param context The context of the application
         * @return The list of all themes that were parsed
         */
        fun parseThemes(context: Context): ArrayList<Theme> {
            val themes = ArrayList<Theme>()
            try {
                val xmlPullParser: XmlPullParser = context.resources.getXml(R.xml.themes)

                //Get the event type of the xmlParser
                var eventType = xmlPullParser.eventType

                //Load until the end of the xml is reached
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    //If we have a start tag
                    if (eventType == XmlPullParser.START_TAG) {

                        //change depending on the name of the xml tag
                        if (xmlPullParser.name == "theme") {
                            val theme = Theme()

                            //get the attributes from the theme
                            //---------------------------------
                            val attributeCount = xmlPullParser.attributeCount
                            for (i in 0 until attributeCount) {
                                //when we have found the title
                                when {
                                    xmlPullParser.getAttributeName(i).startsWith("title") -> {
                                        theme.name = getString(xmlPullParser.getAttributeValue(i), context)
                                    }
                                    xmlPullParser.getAttributeName(i).startsWith("mode") -> {
                                        theme.isDark = xmlPullParser.getAttributeValue(i)
                                            .lowercase(Locale.getDefault()) == "dark"
                                    }
                                    xmlPullParser.getAttributeName(i).startsWith("wallpaper") -> {
                                        theme.hasWallpaper = true
                                    }
                                }
                            }
                            eventType = xmlPullParser.next()
                            while (eventType != XmlPullParser.END_TAG) {
                                if (eventType == XmlPullParser.START_TAG) {
                                    parseThemeData(xmlPullParser, theme, eventType, context, xmlPullParser.name)
                                }
                                eventType = xmlPullParser.next()
                            }
                            themes.add(theme)
                        }
                    }
                    eventType = xmlPullParser.next()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return themes
        }


        /**
         * Get the background of an item
         * @param item The string for the item
         * @param context The context of the application
         * @param optional When true, this background is allowed to be null
         * @return The background or null of not available and optional
         */
        fun getBackground(item: String?, context: Context, optional: Boolean): Drawable? {
            return getBackground(item, context, optional, false)
        }

        /**
         * Get the background of an item
         * @param item The string for the item
         * @param context The context of the application
         * @param optional When true, this background is allowed to be null
         * @param removeMask When true, the layout with the ID "android.R.id.mask" are removed
         * @return The background or null of not available and optional
         */
        fun getBackground(item: String?, context: Context, optional: Boolean, removeMask: Boolean): Drawable? {
            if (item == null) {
                if (optional) {
                    return null
                }
                Log.e("THEME ERROR", "Drawable not defined")
                return ColorDrawable(-0xff01)
            }
            return if (item.startsWith("@drawable/")) {
                val name = item.substring(10)
                val id = context.resources.getIdentifier(name, "drawable", context.packageName)
                if (id == 0) {
                    Log.e("THEME ERROR", "Not able to find drawable for target: @drawable/$name")
                    ColorDrawable(-0xff01)
                } else {
                    val drawable = ContextCompat.getDrawable(context, id)
                    if (drawable is LayerDrawable && removeMask) {
                        drawable.setDrawableByLayerId(android.R.id.mask, null)
                    }
                    drawable
                }
            } else {
                ColorDrawable(getResourceColor(item, context))
            }
        }

        /**
         * Get the color for an item
         * @param color The color name of the item
         * @return The color
         */
        fun getColor(color: Int?): Int {
            if (color == null) {
                return Color.parseColor("#FFFF00FF")
            }
            return color
        }

        /**
         * Get an icon for the theme
         * @param item The string of the item to get
         * @param context The context of the application
         * @param defaultIcon The default icons for when the icons should just be colored
         * @return The icon for the theme
         */
        fun getIcon(item: String?, context: Context, defaultIcon: Drawable?): Drawable? {
            if (item == null || defaultIcon == null ) {
                Log.e("THEME ERROR", "Icon not defined")
                return ColorDrawable(-0xff01)
            }

            return if (item.startsWith("@drawable/")) {
                val name = item.substring(10)
                val id = context.resources.getIdentifier(name, "drawable", context.packageName)
                if (id == 0) {
                    Log.e("THEME ERROR", "Not able to find icon for target: @drawable/$name")
                    ColorDrawable(-0xff01)
                }
                else {
                    ContextCompat.getDrawable(context, id)
                }
            }
            else {
                setColorFilter(defaultIcon, getResourceColor(item, context))
                defaultIcon
            }
        }

        //endregion

        //region Private Methods
        /**
         * Get the color from the resources
         * @param colorString The name for the color resource
         * @param context The context of the application
         * @return The color. A pink color of the color was not found or not correctly defined
         */
        private fun getResourceColor(colorString: String, context: Context): Int {
            var name = colorString
            var color = -0xff01

            //a color resource
            if (name.startsWith("@color/")) {
                name = name.substring(7)
                val resourceID = context.resources.getIdentifier(name, "color", context.packageName)
                if (resourceID != 0) {
                    try {
                        color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            context.resources.getColor(resourceID, null)
                        }
                        else {
                            ContextCompat.getColor(context, resourceID)
                        }
                    }
                    catch (e: Exception) {
                        Log.e("THEME ERROR", "Not able to find color for target: @color/$name")
                    }
                }
                else {
                    Log.e("THEME ERROR", "No resource found for target: @color/$name")
                }
            }
            else {
                val original = name
                if (name.startsWith("#")) {
                    name = name.substring(1)
                }
                if (name.length == 3 || name.length == 4) {
                    val result = StringBuilder()
                    for (i in name.indices) {
                        result.append(name.substring(i, i))
                        result.append(name.substring(i, i))
                    }
                    name = result.toString()
                }

                //when the string does not have an alpha value, add it
                if (name.length == 6) {
                    name = "FF$name"
                }
                if (name.length == 8) {
                    try {
                        color = (java.lang.Long.valueOf(name, 16) as Long).toInt()
                    }
                    catch (e: Exception) {
                        //invalid color nothing to do here
                        e.printStackTrace()
                        Log.e("THEME ERROR", "Not able to parse color for target: $original")
                    }
                }
                else {
                    Log.e("THEME ERROR", "Invalid color definition for target: $original")
                }
            }
            return color
        }

        /**
         * Get a string from the resources
         * @param string The name for the string resource
         * @param context The context of the application
         * @return The string
         */
        private fun getString(string: String, context: Context): String {
            var name = string
            if (name.startsWith("@string/")) {
                name = name.substring(8)
                val stringID = context.resources.getIdentifier(name, "string", context.packageName)
                try {
                    return context.resources.getString(stringID)
                }
                catch (e: Exception) {
                    //invalid color nothing to do here
                    Log.e("THEME ERROR", "Not able to find string for target: @string/$name")
                }
            }
            else if (name.startsWith("@")) {
                val stringID = name.substring(1).toInt()
                try {
                    return context.resources.getString(stringID)
                } catch (e: Exception) {
                    //invalid color nothing to do here
                    Log.e("THEME ERROR", "Not able to find string for target: @string/$name")
                }
            }
            return name
        }

        /**
         * Get a dimension from the resources
         * @param dimensionString The string for the dimension resource
         * @param context The context of the application
         * @return The dimension
         */
        private fun getDimen(dimensionString: String, context: Context): Int {
            var name = dimensionString
            if (name.startsWith("@dimen/")) {
                name = name.substring(7)
                val resourceID = context.resources.getIdentifier(name, "dimen", context.packageName)
                try {
                    return context.resources.getDimension(resourceID).toInt()
                }
                catch (e: Exception) {
                    Log.e("THEME ERROR", "Not able to find boolean for target: @dimen/$name")
                }
            }
            else {
                var typedValue = 0
                val value = name
                val newString = name.substring(0, name.length - 2)
                when {
                    name.endsWith("dp") -> {
                        typedValue = TypedValue.COMPLEX_UNIT_DIP
                        name = newString
                    }
                    name.endsWith("dip") -> {
                        typedValue = TypedValue.COMPLEX_UNIT_DIP
                        name = name.substring(0, name.length - 3)
                    }
                    name.endsWith("pt") -> {
                        typedValue = TypedValue.COMPLEX_UNIT_PT
                        name = newString
                    }
                    name.endsWith("sp") -> {
                        typedValue = TypedValue.COMPLEX_UNIT_SP
                        name = newString
                    }
                    name.endsWith("in") -> {
                        typedValue = TypedValue.COMPLEX_UNIT_IN
                        name = newString
                    }
                    name.endsWith("mm") -> {
                        typedValue = TypedValue.COMPLEX_UNIT_MM
                        name = newString
                    }
                    name.endsWith("px") -> {
                        name = newString
                    }
                }
                try {
                    return TypedValue.applyDimension(typedValue, name.toInt().toFloat(),
                        context.resources.displayMetrics).toInt()
                }
                catch (e: Exception) {
                    Log.e("THEME ERROR", "Invalid value definition for target: $value")
                }
            }
            return 0
        }

        /**
         * Get a boolean from the resources
         * @param booleanString The string for the boolean resource
         * @param context The context of the application
         * @return The boolean value
         */
        private fun getBoolean(booleanString: String, context: Context): Boolean {
            var name = booleanString

            if (name.startsWith("@bool/")) {
                name = name.substring(6)
                val id = context.resources.getIdentifier(name, "bool", context.packageName)
                if (id != 0) {
                    try {
                        return context.resources.getBoolean(id)
                    }
                    catch (ignored: Exception) {}
                }
                else {
                    Log.e("THEME ERROR", "Not able to find boolean for target: @bool/$name")
                }
            }
            else {
                return name.lowercase(Locale.getDefault()) == "true"
            }
            return false
        }

        /**
         * Set a color filter for the drawable
         * @param drawable The drawable to set the color filter for
         * @param color The color to set
         */
        @Suppress("DEPRECATION")
        private fun setColorFilter(drawable : Drawable, @ColorInt color : Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                drawable.colorFilter = BlendModeColorFilter(color, BlendMode.MULTIPLY)
            }
            else {
                drawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
            }
        }

        /**
         * Parse one data sample for the theme
         * @param xmlPullParser The reader for the xml
         * @param theme The theme to load the data for
         * @param event The current type of input event
         * @param context The context of the application
         */
        private fun parseThemeData(xmlPullParser : XmlPullParser, theme : Theme,
                                    event : Int, context : Context, type : String) : Int {
            var i = 0
            var eventType = event
            val attributeCount = xmlPullParser.attributeCount
            var valid = false

            while (i in 0 until attributeCount) {
                if (xmlPullParser.getAttributeName(i).startsWith("name")) {
                    val themeItemName = xmlPullParser.getAttributeValue(i)
                    eventType = xmlPullParser.next()
                    if (eventType == XmlPullParser.TEXT) {
                        valid = addToTheme(type, themeItemName, xmlPullParser.text, theme, context)
                        eventType = xmlPullParser.next()

                        while (eventType != XmlPullParser.END_TAG) {
                            eventType = xmlPullParser.next()
                        }
                    }
                }
                i++
            }
            if (!valid) {
                eventType = xmlPullParser.next()
                while (eventType != XmlPullParser.END_TAG) {
                    eventType = xmlPullParser.next()
                }
            }

            return eventType
        }

        /**
         * Add the retrieved data to the theme
         * @param type The type of the data
         * @param name The name to save the data for
         * @param data The string representing the data
         * @param theme The theme to add the data into
         * @param context the context of the application
         */
        private fun addToTheme(type : String, name : String, data : String,
                               theme : Theme, context: Context) : Boolean {
            when (type) {
                "icon" -> { theme.icons.put(name, data) }
                "bool" -> { theme.booleans.put(name,getBoolean(data, context)); }
                "dimen" -> { theme.dimens.put(name, getDimen(data, context)) }
                "background" -> { theme.backgrounds.put(name, data) }
                "color" -> { theme.colors.put(name, getResourceColor(data, context)) }
                else -> { return false }
            }
            return true
        }

        //endregion
    }
}