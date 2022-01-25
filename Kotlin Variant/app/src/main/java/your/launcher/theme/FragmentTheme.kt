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
     * Set the [theme] used for a fragment. Requires the [context] of the application
     */
    fun setTheme(context: Context, theme: Theme?)

    /**
     * This method returns if the theme icon of the launcher should
     * be hidden after the theme is applied
     */
    fun hideAfterApply(): Boolean
}

//Items used for getting resources by their name

const val SEARCH_BAR_TEXT_HINT = "SEARCH_BAR_TEXT_HINT"
const val SEARCH_BAR_BACKGROUND = "SEARCH_BAR_BACKGROUND"

const val ICON_SEARCH = "ICON_SEARCH"
const val ICON_SEARCH_VOICE = "ICON_SEARCH_VOICE"
const val ICON_POPUP_INFO = "ICON_POPUP_INFO"
const val ICON_POPUP_EDIT = "ICON_POPUP_EDIT"
const val ICON_POPUP_REMOVE = "ICON_POPUP_REMOVE"
const val ICON_FOLDER_SORT = "ICON_FOLDER_SORT"

const val DOCK_ENTRY_BACKGROUND = "DOCK_ENTRY_BACKGROUND"
const val DOCK_BACKGROUND_RIGHT = "DOCK_BACKGROUND_RIGHT"
const val DOCK_ALL_APPS_ICON = "DOCK_ALL_APPS_ICON"

const val MAIN_HEADER = "MAIN_HEADER"
const val MAIN_SEPARATOR = "MAIN_SEPARATOR"
const val MAIN_HEADER_TEXT_COLOR = "MAIN_HEADER_TEXT_COLOR"
const val MAIN_GRID_ITEM = "MAIN_GRID_ITEM"
const val MAIN_ITEM_TEXT_COLOR = "MAIN_ITEM_TEXT_COLOR"

const val FOLDER_BACKGROUND = "FOLDER_BACKGROUND"
const val FOLDER_NAME_TEXT = "FOLDER_NAME_TEXT"
const val FOLDER_SORT_BACKGROUND = "FOLDER_SORT_BACKGROUND"
const val FOLDER_ITEM_BACKGROUND = "FOLDER_ITEM_BACKGROUND"
const val FOLDER_ITEM_TEXT = "FOLDER_ITEM_TEXT"

const val DIALOG_BACKGROUND = "DIALOG_BACKGROUND"
const val DIALOG_TITLE_TOOLBAR = "DIALOG_TITLE_TOOLBAR"
const val DIALOG_TEXT_TITLE_COLOR = "DIALOG_TEXT_TITLE_COLOR"
const val DIALOG_TEXT_PRIMARY_COLOR = "DIALOG_TEXT_PRIMARY_COLOR"
const val DIALOG_TEXT_BUTTON_COLOR = "DIALOG_TEXT_BUTTON_COLOR"
const val DIALOG_CONTENT_BUTTON = "DIALOG_CONTENT_BUTTON"
const val DIALOG_DIVIDER = "DIALOG_DIVIDER"
const val DIALOG_BUTTON = "DIALOG_BUTTON"

const val POPUP_BACKGROUND = "POPUP_BACKGROUND"
const val POPUP_ARROW_DOWN = "POPUP_ARROW_DOWN"
const val POPUP_ITEM_TEXT_COLOR = "POPUP_ITEM_TEXT_COLOR"
const val POPUP_ITEM_BACKGROUND = "POPUP_ITEM_BACKGROUND"

const val SETTINGS_BACKGROUND = "SETTINGS_BACKGROUND"
const val SETTINGS_BACKGROUND_CATEGORY = "SETTINGS_BACKGROUND_CATEGORY"
const val SETTINGS_TEXT_CATEGORY_COLOR = "SETTINGS_TEXT_CATEGORY_COLOR"
const val SETTINGS_TEXT_SETTING_COLOR = "SETTINGS_TEXT_SETTING_COLOR"
const val SETTINGS_CLICKABLE_ITEM_BACKGROUND = "SETTINGS_CLICKABLE_ITEM_BACKGROUND"
const val SETTINGS_TEXT_SETTING_VALUE_COLOR = "SETTINGS_TEXT_SETTING_VALUE_COLOR"
const val SETTINGS_DIVIDER = "SETTINGS_DIVIDER"

const val CONTROL_CHECKBOX = "CONTROL_CHECKBOX"
const val CONTROL_HIGHLIGHT_COLOR = "CONTROL_HIGHLIGHT_COLOR"
const val CONTROL_SEEKBAR_PROGRESS = "CONTROL_SEEKBAR_PROGRESS"
const val CONTROL_SEEKBAR_THUMB = "CONTROL_SEEKBAR_THUMB"
const val CONTROL_SWITCH_THUMB = "CONTROL_SWITCH_THUMB"
const val CONTROL_SWITCH_TRACK = "CONTROL_SWITCH_TRACK"