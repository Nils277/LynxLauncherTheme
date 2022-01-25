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
import java.util.*
import kotlin.collections.ArrayList

/**
 * This class parses the themes and retrieves the drawable, icon, color, dimension or boolean resources
 * YOU SHOULD NOT HAVE TO CHANGE ANYTHING IN THIS CLASS.
 * @author Nils Büscher
 */
class ThemeParser {

    //region Public Methods
    //------------------------------------------------

    companion object {

        // Color returns when there was an error (its PINK!!!)
        private val mErrorColor = Color.parseColor("#FFFF00FF")

        /**
         * Parse all themes defined in the themes.xml file and returns all successfully created
         * themes. This method requires a [context] for parsing the xml file
         */
        @JvmStatic
        fun parseThemes(context: Context): ArrayList<Theme> {
            val themes = ArrayList<Theme>()
            try {
                val xmlPullParser: XmlPullParser = context.resources.getXml(R.xml.themes)
                var eventType = xmlPullParser.eventType

                //Load until the end of the xml is reached
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    //If we have a start tag
                    if (eventType == XmlPullParser.START_TAG && xmlPullParser.name == "theme") {
                        val theme = Theme() //create a new theme

                        //get the attributes from the theme
                        parseAttributes(context, xmlPullParser, theme)
                        eventType = xmlPullParser.next()

                        //parse all the data of the theme
                        while (eventType != XmlPullParser.END_TAG) {
                            if (eventType == XmlPullParser.START_TAG) {
                                parseThemeData(xmlPullParser, theme, eventType, context, xmlPullParser.name)
                            }
                            eventType = xmlPullParser.next()
                        }
                        themes.add(theme)
                    }
                    eventType = xmlPullParser.next()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return themes
        }

        /**
         * Get the background of an [item]. When the [optional] parameter is set to true,
         * this method can also return a null value, else it is assured to not be null.
         * When [removeMask] is set to true, all sub-drawables that are masks are removed.
         */
        @JvmStatic
        fun getBackground(item: String?,
                          context: Context,
                          optional: Boolean = true,
                          removeMask: Boolean = false): Drawable? {
            return if (item == null) {
                if (optional) null else ColorDrawable(mErrorColor)
            } else if (item.startsWith("@drawable/")) {
                val name = item.substring(10)
                val id = context.resources.getIdentifier(name, "drawable", context.packageName)
                if (id == 0) {
                    Log.e("THEME ERROR", "Not able to find drawable for target: @drawable/$name")
                    ColorDrawable(mErrorColor)
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

        /** returns the [color] for an item, and the default color if it is null */
        @JvmStatic
        fun getColor(color: Int?) = color ?: mErrorColor

        /**
         * Get an icon for the theme
         * @param item The string of the item to get
         * @param context The context of the application
         * @param defaultIcon The default icons for when the icons should just be colored
         * @return The icon for the theme
         */
        @JvmStatic
        fun getIcon(item: String?, context: Context, defaultIcon: Drawable?): Drawable? {
            if (item == null || defaultIcon == null ) {
                Log.e("THEME ERROR", "Icon not defined")
                return ColorDrawable(mErrorColor)
            }

            return if (item.startsWith("@drawable/")) {
                val name = item.substring(10)
                val id = context.resources.getIdentifier(name, "drawable", context.packageName)
                if (id == 0) {
                    Log.e("THEME ERROR", "Not able to find icon for target: @drawable/$name")
                    ColorDrawable(mErrorColor)
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
        //------------------------------------------------

        /**
         * Parse all the attributes for a [theme] using the given [xmlPullParser].
         * This method required a [context] to parse the data
         */
        @JvmStatic
        private fun parseAttributes(context: Context, xmlPullParser: XmlPullParser, theme: Theme) {
            val attributeCount = xmlPullParser.attributeCount
            for (i in 0 until attributeCount) {
                //when we have found the title
                val attributeName = xmlPullParser.getAttributeName(i)
                when {
                    attributeName.startsWith("title") -> {
                        theme.name = getString(xmlPullParser.getAttributeValue(i), context)
                    }
                    attributeName.startsWith("mode") -> {
                        theme.isDark = xmlPullParser.getAttributeValue(i)
                            .lowercase(Locale.getDefault()) == "dark"
                    }
                    attributeName.startsWith("wallpaper") -> {
                        theme.hasWallpaper = true
                    }
                }
            }
        }

        /**
         * Returns the color from the resources for a given [colorString].
         * This method requires a [context] to parse the color
         */
        @JvmStatic
        private fun getResourceColor(colorString: String, context: Context): Int {
            // a color resource
            if (colorString.startsWith("@color/")) {
                val resourceID = context.resources.getIdentifier(colorString.substring(7),
                    "color", context.packageName)
                if (resourceID != 0) {
                    try {
                        return ContextCompat.getColor(context, resourceID)
                    } catch (e: Exception) {
                        Log.e("THEME ERROR", "Not able to find color for target: $colorString")
                    }
                } else {
                    Log.e("THEME ERROR", "No resource found for target: $colorString")
                }
            } else {  // a string describing a color
                var colorName = colorString
                // when the color string begins with a hash sign, remove it
                if (colorName.startsWith("#")) {
                    colorName = colorString.substring(1)
                }

                // when the short form os the color definition is used, replace it with the long one
                if (colorName.length == 3 || colorName.length == 4) {
                    val result = StringBuilder()
                    for (ch in colorName) {
                        result.append(ch).append(ch)
                    }
                    colorName = result.toString()
                }

                // when the length of th string name is valid
                if (colorName.length == 6 || colorName.length == 8) {
                    try {
                        return Color.parseColor("#$colorName")
                    } catch (e: Exception) {
                        Log.e("THEME ERROR", "Not able to parse color for target: $colorString")
                    }
                } else {
                    Log.e("THEME ERROR", "Invalid color definition for target: $colorString")
                }
            }
            return mErrorColor
        }

        /**
         * Returns a string from the resources for the given [stringName] of the string.
         * This method requires the a [context] to parse the dimension
         */
        @JvmStatic
        private fun getString(stringName: String, context: Context): String {
            var stringID = 0
            if (stringName.startsWith("@string/")) {
                stringID = context.resources.getIdentifier(stringName.substring(8), "string", context.packageName)
            }
            else if (stringName.startsWith("@")) {
                stringID = stringName.substring(1).toInt()
            }

            try {
                return context.resources.getString(stringID)
            }
            catch (e: Exception) {
                //invalid string nothing to do here
                Log.e("THEME ERROR", "Not able to find string for target: $stringName")
            }
            return stringName
        }

        /**
         * Returns the dimension in pixels for the given [dimensionString].
         * This method requires the a [context] to parse the dimension
         */
        @JvmStatic
        private fun getDimen(dimensionString: String, context: Context): Int {
            if (dimensionString.startsWith("@dimen/")) {
                val resourceID = context.resources.getIdentifier(dimensionString.substring(7), "dimen", context.packageName)
                try {
                    return context.resources.getDimension(resourceID).toInt()
                } catch (e: Exception) {
                    Log.e("THEME ERROR", "Not able to find boolean for target: $dimensionString")
                }
            }
            else {
                var valueString = dimensionString.substring(0, dimensionString.length - 2)
                val typedValue = when {
                    dimensionString.endsWith("dp") -> TypedValue.COMPLEX_UNIT_DIP
                    dimensionString.endsWith("pt") -> TypedValue.COMPLEX_UNIT_PT
                    dimensionString.endsWith("sp") -> TypedValue.COMPLEX_UNIT_SP
                    dimensionString.endsWith("in") -> TypedValue.COMPLEX_UNIT_IN
                    dimensionString.endsWith("mm") -> TypedValue.COMPLEX_UNIT_MM
                    dimensionString.endsWith("dip") -> {
                        valueString = valueString.substring(0, valueString.length - 1)
                        TypedValue.COMPLEX_UNIT_DIP
                    }
                    else -> TypedValue.COMPLEX_UNIT_PX
                }

                try {
                    return TypedValue.applyDimension(typedValue, valueString.toFloat(),
                        context.resources.displayMetrics).toInt()
                } catch (e: Exception) {
                    Log.e("THEME ERROR", "Invalid value definition for target: $valueString")
                }
            }
            return 0
        }

        /**
         * Returns a boolean from the resources using the given [booleanString].
         * This method requires a [context] to perform its operations
         */
        @JvmStatic
        private fun getBoolean(booleanString: String, context: Context): Boolean {
            if (booleanString.startsWith("@bool/")) {
                val id = context.resources.getIdentifier(booleanString.substring(6), "bool", context.packageName)
                if (id != 0) {
                    try {
                        return context.resources.getBoolean(id)
                    } catch (ignored: Exception) {}
                } else {
                    Log.e("THEME ERROR", "Not able to find boolean for target: $booleanString")
                }
            } else {
                return booleanString.lowercase(Locale.getDefault()) == "true"
            }
            return false
        }

        /** Add a color filter with a given [color] the the [drawable] */
        @Suppress("DEPRECATION")
        @JvmStatic
        private fun setColorFilter(drawable : Drawable, @ColorInt color : Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
            }
            else {
                drawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
            }
        }

        /**
         * Parse one data sample for the given [theme] using the given [xmlPullParser].
         * The given [event] holds the current type of data in the XML.
         * This method requires a [context] to perform its operations
         */
        @JvmStatic
        private fun parseThemeData(xmlPullParser : XmlPullParser, theme : Theme,
                                    event : Int, context : Context, type : String) : Int {
            var eventType = event
            var valid = false

            val attributeCount = xmlPullParser.attributeCount
            for (i in 0 until attributeCount) {
                if (xmlPullParser.getAttributeName(i).startsWith("name")) {
                    val themeItemName = xmlPullParser.getAttributeValue(i)
                    eventType = xmlPullParser.next()
                    if (eventType == XmlPullParser.TEXT) {
                        valid = addToTheme(type, themeItemName, xmlPullParser.text, theme, context)

                        do{ eventType = xmlPullParser.next()
                        } while (eventType != XmlPullParser.END_TAG)
                    }
                }
            }

            if (!valid) {
                do{ eventType = xmlPullParser.next()
                } while (eventType != XmlPullParser.END_TAG)
            }
            return eventType
        }

        /**
         * Add the retrieved data to the theme.
         * [type] is the type of the resource, [name] the name of the resource and
         * [data] describes the data as a string.
         * The retrieved colors, drawables etc. will be added to the given [theme].
         * This method requires a [context] for its operations.
         */
        @JvmStatic
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