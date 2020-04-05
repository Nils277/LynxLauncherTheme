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

import androidx.collection.SimpleArrayMap;

/**
 * This class is a simple container for the entries stored in a theme
 * YOU SHOULD NOT HAVE TO CHANGE ANYTHING IN THIS CLASS.
 * @author Nils Büscher
 */
class Theme {
    // The name of the theme
    String name;

    // Holds whether this theme is a dark version
    boolean isDark;

    // The list of backgrounds
    final SimpleArrayMap<String, String> backgrounds = new SimpleArrayMap<>();

    // The list of icons
    final SimpleArrayMap<String, String> icons = new SimpleArrayMap<>();

    // The list of dimension
    final SimpleArrayMap<String, Integer> dimens = new SimpleArrayMap<>();

    // The list of colors
    final SimpleArrayMap<String, Integer> colors = new SimpleArrayMap<>();

    // The list of booleans
    final SimpleArrayMap<String, Boolean> booleans = new SimpleArrayMap<>();
}
