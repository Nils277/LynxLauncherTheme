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
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import your.launcher.theme.ThemeParser.Companion.getBackground

/**
 * The debug fragment of the theme, used for developers to test the theme appearance
 * @author Nils Büscher
 */
class FragmentDebug : Fragment(), FragmentTheme {

    // The theme
    private var mTheme: Theme? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_debug, container, false)
        applyTheme(context, view)
        return view
    }

    /**
     * Set the dark mode of the view
     * @param context The context of the application
     * @param theme The new theme
     */
    override fun setTheme(context: Context?, theme: Theme?) {
        if (mTheme != theme) {
            mTheme = theme
            applyTheme(context, view)
        }
    }

    /**
     * Get whether the theme icon of the launcher should be hidden after the theme is applied
     * @return When the the theme should be hidden, else false
     */
    override fun hideAfterApply(): Boolean {
        return false
    }

    /**
     * Apply the theme to the
     * @param context The context of the application
     */
    private fun applyTheme(context: Context?, view: View?) {
        val theme = mTheme
        if (context == null || view == null || theme == null) {
            return
        }

        view.background = getBackground(theme.backgrounds["SETTINGS_BACKGROUND"], context, false)

        //----Screens----

        //Search bar background
        var background = view.findViewById<View>(R.id.search_background)
        background.background = getBackground(theme.backgrounds["SEARCH_BAR_BACKGROUND"], context,
                optional = false, removeMask = true)

        //Search bar text
        var text = view.findViewById<TextView>(R.id.search_text)
        text.setTextColor(ThemeParser.getColor(theme.colors["SEARCH_BAR_TEXT_HINT"]))

        //Search start action
        var image = view.findViewById<ImageView>(R.id.search_action)
        image.setImageDrawable(ThemeParser.getIcon(theme.icons["ICON_SEARCH"],
                context, ContextCompat.getDrawable(context, R.drawable.ic_search)))

        //Search voice action
        image = view.findViewById(R.id.search_voice)
        image.setImageDrawable(ThemeParser.getIcon(theme.icons["ICON_SEARCH_VOICE"],
                context, ContextCompat.getDrawable(context, R.drawable.ic_mic)))

        //Dock background
        background = view.findViewById(R.id.dock_background)
        background.background = getBackground(theme.backgrounds["DOCK_BACKGROUND_RIGHT"], context,
            optional = false, removeMask = true)

        //Dock all apps button
        image = view.findViewById(R.id.dock_all_apps)
        image.background = getBackground(theme.backgrounds["DOCK_ENTRY_BACKGROUND"], context, false)
        image.setImageDrawable(getBackground(theme.backgrounds["DOCK_ALL_APPS_ICON"], context, false))

        //Dock app 1
        image = view.findViewById(R.id.dock_app_1)
        image.background = getBackground(theme.backgrounds["DOCK_ENTRY_BACKGROUND"], context, false)

        //Dock app 1
        image = view.findViewById(R.id.dock_app_2)
        image.background = getBackground(theme.backgrounds["DOCK_ENTRY_BACKGROUND"], context, false)

        //App header
        background = view.findViewById(R.id.app_header)
        background.background = getBackground(theme.backgrounds["MAIN_HEADER"], context, true)

        //App header divider
        background = view.findViewById(R.id.app_header_divider)
        background.background = getBackground(theme.backgrounds["MAIN_SEPARATOR"], context, true)

        // App header text
        text = view.findViewById(R.id.app_header_text)
        text.setTextColor(ThemeParser.getColor(theme.colors["MAIN_HEADER_TEXT_COLOR"]))

        // App 1
        text = view.findViewById(R.id.screen_app_1)
        text.background = getBackground(theme.backgrounds["MAIN_GRID_ITEM"], context, false)
        text.setTextColor(ThemeParser.getColor(theme.colors["MAIN_ITEM_TEXT_COLOR"]))

        // App 2
        text = view.findViewById(R.id.screen_app_2)
        text.background = getBackground(theme.backgrounds["MAIN_GRID_ITEM"], context, false)
        text.setTextColor(ThemeParser.getColor(theme.colors["MAIN_ITEM_TEXT_COLOR"]))

        //----Menus----

        //Context menu background
        background = view.findViewById(R.id.context_menu_background)
        background.background = getBackground(theme.backgrounds["POPUP_BACKGROUND"], context, false)

        //Arrow of menu
        var drawable = getBackground(theme.backgrounds["POPUP_ARROW_DOWN"], context, true)
        if (drawable != null) {
            background = view.findViewById(R.id.arrow)
            background.background = drawable
            background.layoutParams.height = drawable.intrinsicHeight
            background.layoutParams.width = drawable.intrinsicWidth
        }
        var color = ThemeParser.getColor(theme.colors["POPUP_ITEM_TEXT_COLOR"])

        //Item 1
        text = view.findViewById(R.id.context_item_1)
        text.background = getBackground(theme.backgrounds["POPUP_ITEM_BACKGROUND"], context, false)
        text.setTextColor(color)
        text.setCompoundDrawablesRelativeWithIntrinsicBounds(
            ThemeParser.getIcon(theme.icons["ICON_POPUP_EDIT"],
                context, ContextCompat.getDrawable(context, R.drawable.ic_edit)
            ), null, null, null
        )

        //Item 2
        text = view.findViewById(R.id.context_item_2)
        text.background = getBackground(theme.backgrounds["POPUP_ITEM_BACKGROUND"], context, false)
        text.setTextColor(color)
        text.setCompoundDrawablesRelativeWithIntrinsicBounds(
            ThemeParser.getIcon(theme.icons["ICON_POPUP_INFO"],
                context, ContextCompat.getDrawable(context, R.drawable.ic_info)
            ), null, null, null
        )

        //Item 3
        text = view.findViewById(R.id.context_item_3)
        text.background = getBackground(theme.backgrounds["POPUP_ITEM_BACKGROUND"], context, false)
        text.setTextColor(color)
        text.setCompoundDrawablesRelativeWithIntrinsicBounds(
            ThemeParser.getIcon(theme.icons["ICON_POPUP_REMOVE"],
                context, ContextCompat.getDrawable(context, R.drawable.ic_remove)
            ), null, null, null
        )

        //Folder background
        background = view.findViewById(R.id.folder_background)
        background.background = getBackground(theme.backgrounds["FOLDER_BACKGROUND"], context,
                optional = false, removeMask = true)

        //Folder title
        text = view.findViewById(R.id.folder_title)
        text.setTextColor(ThemeParser.getColor(theme.colors["FOLDER_NAME_TEXT"]))

        //Sort icon
        image = view.findViewById(R.id.folder_sort)
        image.background = getBackground(theme.backgrounds["FOLDER_SORT_BACKGROUND"], context, false)
        image.setImageDrawable(ThemeParser.getIcon(theme.icons["ICON_FOLDER_SORT"],
                context, ContextCompat.getDrawable(context, R.drawable.ic_sort)))

        //Folder item 1
        text = view.findViewById(R.id.folder_app_1)
        text.background = getBackground(theme.backgrounds["FOLDER_ITEM_BACKGROUND"], context, false)
        text.setTextColor(ThemeParser.getColor(theme.colors["FOLDER_ITEM_TEXT"]))

        //Folder item 2
        text = view.findViewById(R.id.folder_app_2)
        text.background = getBackground(theme.backgrounds["FOLDER_ITEM_BACKGROUND"], context, false)
        text.setTextColor(ThemeParser.getColor(theme.colors["FOLDER_ITEM_TEXT"]))

        //Dialog background
        background = view.findViewById(R.id.dialog_background)
        background.background = getBackground(theme.backgrounds["DIALOG_BACKGROUND"], context, false)

        //Dialog title
        background = view.findViewById(R.id.dialog_title)
        background.background = getBackground(theme.backgrounds["DIALOG_TITLE_TOOLBAR"], context, false)

        //Dialog title text
        text = view.findViewById(R.id.dialog_title_text)
        text.setTextColor(ThemeParser.getColor(theme.colors["DIALOG_TEXT_TITLE_COLOR"]))

        //Dialog text
        text = view.findViewById(R.id.dialog_text)
        text.setTextColor(ThemeParser.getColor(theme.colors["DIALOG_TEXT_PRIMARY_COLOR"]))

        //Dialog Content Button
        text = view.findViewById(R.id.dialog_content_button)
        text.setTextColor(ThemeParser.getColor(theme.colors["DIALOG_TEXT_BUTTON_COLOR"]))
        text.background = getBackground(theme.backgrounds["DIALOG_CONTENT_BUTTON"], context, false)

        //Divider
        background = view.findViewById(R.id.dialog_top_divider)
        background.setBackgroundColor(ThemeParser.getColor(theme.colors["DIALOG_DIVIDER"]))
        background = view.findViewById(R.id.dialog_bottom_divider)
        background.setBackgroundColor(ThemeParser.getColor(theme.colors["DIALOG_DIVIDER"]))

        //Dialog Button
        val button = view.findViewById<Button>(R.id.dialog_button_done)
        button.setTextColor(ThemeParser.getColor(theme.colors["DIALOG_TEXT_BUTTON_COLOR"]))
        button.background = getBackground(theme.backgrounds["DIALOG_BUTTON"], context, false)

        //----Settings----
        color = ThemeParser.getColor(theme.colors["CONTROL_HIGHLIGHT_COLOR"])

        //main background
        background = view.findViewById(R.id.settings_background)
        background.background = getBackground(theme.backgrounds["SETTINGS_BACKGROUND"], context, false)

        //seekbar background
        background = view.findViewById(R.id.settings_seekbar_background)
        background.background = getBackground(theme.backgrounds["SETTINGS_CLICKABLE_ITEM_BACKGROUND"], context, false)

        //seekbar name text
        text = view.findViewById(R.id.settings_seekbar_title)
        text.setTextColor(ThemeParser.getColor(theme.colors["SETTINGS_TEXT_SETTING_COLOR"]))

        //seekbar value text
        text = view.findViewById(R.id.setting_seekbar_value)
        text.setTextColor(ThemeParser.getColor(theme.colors["SETTINGS_TEXT_SETTING_VALUE_COLOR"]))

        //seekbar
        val seekbar = view.findViewById<SeekBar>(R.id.setting_seekBar)
        drawable = getBackground(theme.backgrounds["CONTROL_SEEKBAR_PROGRESS"], context, true)
        if (drawable != null) {
            seekbar.progressDrawable = drawable
        }
        else {
            seekbar.progressDrawable = SeekBar(context).progressDrawable
            seekbar.progressTintList = ColorStateList.valueOf(color)
        }
        drawable = getBackground(theme.backgrounds["CONTROL_SEEKBAR_THUMB"], context, true)
        if (drawable != null) {
            seekbar.thumb = drawable
        }
        else {
            seekbar.thumb = SeekBar(context).thumb
            seekbar.thumbTintList = ColorStateList.valueOf(color)
        }

        //value background
        background = view.findViewById(R.id.settings_value_background)
        background.background =
            getBackground(theme.backgrounds["SETTINGS_CLICKABLE_ITEM_BACKGROUND"], context, false)

        //value name text
        text = view.findViewById(R.id.settings_value_title)
        text.setTextColor(ThemeParser.getColor(theme.colors["SETTINGS_TEXT_SETTING_COLOR"]))

        //value value text
        text = view.findViewById(R.id.setting_value_value)
        text.setTextColor(ThemeParser.getColor(theme.colors["SETTINGS_TEXT_SETTING_VALUE_COLOR"]))

        //switch background
        background = view.findViewById(R.id.settings_boolean_background)
        background.background = getBackground(theme.backgrounds["SETTINGS_CLICKABLE_ITEM_BACKGROUND"], context, false)

        //value name text
        text = view.findViewById(R.id.settings_boolean_title)
        text.setTextColor(ThemeParser.getColor(theme.colors["SETTINGS_TEXT_SETTING_COLOR"]))
        val swt: SwitchCompat = view.findViewById(R.id.settings_switch)
        val switchStates = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_enabled),
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ),
            intArrayOf(
                ContextCompat.getColor(context, if (theme.isDark) R.color.theme_dark_elem_disabled
                else R.color.theme_light_elem_disabled),
                ThemeParser.getColor(theme.colors["CONTROL_HIGHLIGHT_COLOR"]),
                ContextCompat.getColor(context, if (theme.isDark) R.color.theme_dark_elem_default
                else R.color.theme_light_elem_disabled),
            )
        )
        drawable = getBackground(theme.backgrounds["CONTROL_SWITCH_THUMB"], context, true)
        if (drawable != null) {
            swt.thumbDrawable = drawable
        }
        else {
            swt.thumbDrawable = Switch(context).thumbDrawable
            swt.thumbDrawable.setTintList(switchStates)
        }
        drawable = getBackground(theme.backgrounds["CONTROL_SWITCH_TRACK"], context, true)
        if (drawable != null) {
            swt.trackDrawable = drawable
        }
        else {
            swt.trackDrawable = Switch(context).trackDrawable
            swt.trackDrawable.setTintList(switchStates)
        }
        swt.toggle()
        swt.toggle()

        //value background
        background = view.findViewById(R.id.settings_color_background)
        background.background = getBackground(theme.backgrounds["SETTINGS_CLICKABLE_ITEM_BACKGROUND"], context, false)

        //value name text
        text = view.findViewById(R.id.settings_color_title)
        text.setTextColor(ThemeParser.getColor(theme.colors["SETTINGS_TEXT_SETTING_COLOR"]))

        //value value text
        text = view.findViewById(R.id.setting_color_value)
        text.setTextColor(ThemeParser.getColor(theme.colors["SETTINGS_TEXT_SETTING_VALUE_COLOR"]))

        //Divider
        color = ThemeParser.getColor(theme.colors["SETTINGS_DIVIDER"])
        background = view.findViewById(R.id.divider_1)
        background.setBackgroundColor(color)
        background = view.findViewById(R.id.divider_2)
        background.setBackgroundColor(color)
        background = view.findViewById(R.id.divider_3)
        background.setBackgroundColor(color)

        //----Settings----
        drawable = getBackground(theme.backgrounds["SETTINGS_BACKGROUND_CATEGORY"], context, false)
        color = ThemeParser.getColor(theme.colors["SETTINGS_TEXT_CATEGORY_COLOR"])

        //screen header
        background = view.findViewById(R.id.header_screen)
        background.background = drawable

        //screen header text
        text = view.findViewById(R.id.title_screen)
        text.setTextColor(color)

        //menu header
        background = view.findViewById(R.id.header_menu)
        background.background = drawable

        //menu header text
        text = view.findViewById(R.id.title_menu)
        text.setTextColor(color)

        //settings header
        background = view.findViewById(R.id.header_settings)
        background.background = drawable

        //settings header text
        text = view.findViewById(R.id.title_settings)
        text.setTextColor(color)
    }
}