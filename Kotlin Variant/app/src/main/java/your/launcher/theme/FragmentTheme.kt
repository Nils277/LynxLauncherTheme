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

/**
 * The interface for the theme fragments to apply themes and get information
 */
interface FragmentTheme {

    /**
     * Set the dark mode of the view
     * @param context The context of the application
     * @param theme The new theme
     */
    fun setTheme(context: Context?, theme: Theme?)

    /**
     * Get whether the theme icon of the launcher should be hidden after the theme is applied
     * @return When the the theme should be hidden, else false
     */
    fun hideAfterApply(): Boolean
}