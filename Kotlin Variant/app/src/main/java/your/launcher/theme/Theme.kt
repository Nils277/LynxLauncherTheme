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

import androidx.collection.SimpleArrayMap

/**
 * This class is a simple container for the entries stored in a theme
 * YOU SHOULD NOT HAVE TO CHANGE ANYTHING IN THIS CLASS.
 * @author Nils Büscher
 */
class Theme {

    // The name of the theme
    var name: String? = null

    // Holds whether this theme is a dark version
    var isDark = false

    // Holds whether the theme has a wallpaper
    var hasWallpaper = false

    // The list of backgrounds
    val backgrounds = SimpleArrayMap<String, String>()

    // The list of icons
    val icons = SimpleArrayMap<String, String>()

    // The list of dimension
    val dimens = SimpleArrayMap<String, Int>()

    // The list of colors
    val colors = SimpleArrayMap<String, Int>()

    // The list of booleans
    val booleans = SimpleArrayMap<String, Boolean>()

}