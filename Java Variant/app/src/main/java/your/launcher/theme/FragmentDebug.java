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
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

/**
 * The main fragment of the theme, that is visible to the user
 * @author Nils Büscher
 */
public class FragmentDebug extends Fragment implements FragmentTheme {

    // The theme
    private Theme mTheme;

    public FragmentDebug() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_debug, container, false);
        applyTheme(getContext(), v);
        return v;
    }

    /**
     * Set the dark mode of the view
     * @param context The context of the application
     * @param theme The new theme
     */
    @Override
    public void setTheme(@Nullable Context context, Theme theme) {
        if (mTheme != theme) {
            mTheme = theme;
            applyTheme(context, getView());
        }
    }

    /**
     * Get whether the theme icon of the launcher should be hidden after the theme is applied
     * @return When the the theme should be hidden, else false
     */
    @Override
    public boolean hideAfterApply() {
        return false;
    }

    /**
     * Apply the theme to the
     * @param context The context of the application
     */
    private void applyTheme(Context context, View view) {
        if (context == null || view == null || mTheme == null) {
            return;
        }
        Resources res = context.getResources();

        view.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("SETTINGS_BACKGROUND"), context, false));

        //----Screens----

        //Search bar background
        View background = view.findViewById(R.id.search_background);
        background.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("SEARCH_BAR_BACKGROUND"), context, false, true));

        //Search bar text
        TextView text = view.findViewById(R.id.search_text);
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("SEARCH_BAR_TEXT_HINT")));

        //Search start action
        ImageView image = view.findViewById(R.id.search_action);
        image.setImageDrawable(ThemeParser.getIcon(mTheme.icons.get("ICON_SEARCH"), context, context.getDrawable(R.drawable.ic_search)));

        //Search voice action
        image = view.findViewById(R.id.search_voice);
        image.setImageDrawable(ThemeParser.getIcon(mTheme.icons.get("ICON_SEARCH_VOICE"), context, context.getDrawable(R.drawable.ic_mic)));

        //Dock background
        background = view.findViewById(R.id.dock_background);
        background.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("DOCK_BACKGROUND_RIGHT"), context, false, true));

        //Dock all apps button
        image = view.findViewById(R.id.dock_all_apps);
        image.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("DOCK_ENTRY_BACKGROUND"), context, false));
        image.setImageDrawable(ThemeParser.getBackground(mTheme.backgrounds.get("DOCK_ALL_APPS_ICON"), context, false));

        //Dock app 1
        image = view.findViewById(R.id.dock_app_1);
        image.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("DOCK_ENTRY_BACKGROUND"), context, false));

        //Dock app 1
        image = view.findViewById(R.id.dock_app_2);
        image.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("DOCK_ENTRY_BACKGROUND"), context, false));

        //App header
        background = view.findViewById(R.id.app_header);
        background.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("MAIN_HEADER"), context, true));

        //App header divider
        background = view.findViewById(R.id.app_header_divider);
        background.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("MAIN_SEPARATOR"), context, true));

        // App header text
        text = view.findViewById(R.id.app_header_text);
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("MAIN_HEADER_TEXT_COLOR")));

        // App 1
        text = view.findViewById(R.id.screen_app_1);
        text.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("MAIN_GRID_ITEM"), context, false));
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("MAIN_ITEM_TEXT_COLOR")));

        // App 2
        text = view.findViewById(R.id.screen_app_2);
        text.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("MAIN_GRID_ITEM"), context, false));
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("MAIN_ITEM_TEXT_COLOR")));

        //----Menus----

        //Context menu background
        background = view.findViewById(R.id.context_menu_background);
        background.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("POPUP_BACKGROUND"), context, false));

        //Arrow of menu
        Drawable drawable = ThemeParser.getBackground(mTheme.backgrounds.get("POPUP_ARROW_DOWN"), context, true);
        if (drawable != null) {
            background = view.findViewById(R.id.arrow);
            background.setBackground(drawable);
            background.getLayoutParams().height = drawable.getIntrinsicHeight();
            background.getLayoutParams().width = drawable.getIntrinsicWidth();
        }

        int color = ThemeParser.getColor(mTheme.colors.get("POPUP_ITEM_TEXT_COLOR"));

        //Item 1
        text = view.findViewById(R.id.context_item_1);
        text.setBackground( ThemeParser.getBackground(mTheme.backgrounds.get("POPUP_ITEM_BACKGROUND"), context, false));
        text.setTextColor(color);
        text.setCompoundDrawablesRelativeWithIntrinsicBounds(ThemeParser.getIcon(mTheme.icons.get("ICON_POPUP_EDIT"), context, context.getDrawable(R.drawable.ic_edit)), null, null, null);

        //Item 2
        text = view.findViewById(R.id.context_item_2);
        text.setBackground( ThemeParser.getBackground(mTheme.backgrounds.get("POPUP_ITEM_BACKGROUND"), context, false));
        text.setTextColor(color);
        text.setCompoundDrawablesRelativeWithIntrinsicBounds(ThemeParser.getIcon(mTheme.icons.get("ICON_POPUP_INFO"), context, context.getDrawable(R.drawable.ic_info)), null, null, null);

        //Item 3
        text = view.findViewById(R.id.context_item_3);
        text.setBackground( ThemeParser.getBackground(mTheme.backgrounds.get("POPUP_ITEM_BACKGROUND"), context, false));
        text.setTextColor(color);
        text.setCompoundDrawablesRelativeWithIntrinsicBounds(ThemeParser.getIcon(mTheme.icons.get("ICON_POPUP_REMOVE"), context, context.getDrawable(R.drawable.ic_remove)), null, null, null);

        //Folder background
        background = view.findViewById(R.id.folder_background);
        background.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("FOLDER_BACKGROUND"), context, false, true));

        //Folder title
        text = view.findViewById(R.id.folder_title);
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("FOLDER_NAME_TEXT")));

        //Sort icon
        image = view.findViewById(R.id.folder_sort);
        image.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("FOLDER_SORT_BACKGROUND"), context, false));
        image.setImageDrawable(ThemeParser.getIcon(mTheme.icons.get("ICON_FOLDER_SORT"), context, context.getDrawable(R.drawable.ic_sort)));

        //Folder item 1
        text = view.findViewById(R.id.folder_app_1);
        text.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("FOLDER_ITEM_BACKGROUND"), context, false));
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("FOLDER_ITEM_TEXT")));

        //Folder item 2
        text = view.findViewById(R.id.folder_app_2);
        text.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("FOLDER_ITEM_BACKGROUND"), context, false));
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("FOLDER_ITEM_TEXT")));

        //Dialog background
        background = view.findViewById(R.id.dialog_background);
        background.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("DIALOG_BACKGROUND"), context, false));


        //Dialog title
        background = view.findViewById(R.id.dialog_title);
        background.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("DIALOG_TITLE_TOOLBAR"), context, false));

        //Dialog title text
        text = view.findViewById(R.id.dialog_title_text);
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("DIALOG_TEXT_TITLE_COLOR")));

        //Dialog text
        text = view.findViewById(R.id.dialog_text);
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("DIALOG_TEXT_PRIMARY_COLOR")));

        //Dialog Content Button
        text = view.findViewById(R.id.dialog_content_button);
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("DIALOG_TEXT_BUTTON_COLOR")));
        text.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("DIALOG_CONTENT_BUTTON"), context, false));

        //Divider
        background = view.findViewById(R.id.dialog_top_divider);
        background.setBackgroundColor(ThemeParser.getColor(mTheme.colors.get("DIALOG_DIVIDER")));
        background = view.findViewById(R.id.dialog_bottom_divider);
        background.setBackgroundColor(ThemeParser.getColor(mTheme.colors.get("DIALOG_DIVIDER")));

        //Dialog Button
        Button button = view.findViewById(R.id.dialog_button_done);
        button.setTextColor(ThemeParser.getColor(mTheme.colors.get("DIALOG_TEXT_BUTTON_COLOR")));
        button.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("DIALOG_BUTTON"), context, false));

        //----Settings----
        color = ThemeParser.getColor(mTheme.colors.get("CONTROL_HIGHLIGHT_COLOR"));

        //main background
        background = view.findViewById(R.id.settings_background);
        background.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("SETTINGS_BACKGROUND"), context, false));

        //seekbar background
        background = view.findViewById(R.id.settings_seekbar_background);
        background.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("SETTINGS_CLICKABLE_ITEM_BACKGROUND"), context, false));

        //seekbar name text
        text = view.findViewById(R.id.settings_seekbar_title);
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("SETTINGS_TEXT_SETTING_COLOR")));

        //seekbar value text
        text = view.findViewById(R.id.setting_seekbar_value);
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("SETTINGS_TEXT_SETTING_VALUE_COLOR")));

        //seekbar
        SeekBar seekbar = view.findViewById(R.id.setting_seekBar);
        drawable = ThemeParser.getBackground(mTheme.backgrounds.get("CONTROL_SEEKBAR_PROGRESS"), context, true);
        if (drawable != null) {
            seekbar.setProgressDrawable(drawable);
        }
        else {
            seekbar.setProgressDrawable(new SeekBar(context).getProgressDrawable());
            seekbar.setProgressTintList(ColorStateList.valueOf(color));
        }

        drawable = ThemeParser.getBackground(mTheme.backgrounds.get("CONTROL_SEEKBAR_THUMB"), context, true);
        if (drawable != null) {
            seekbar.setThumb(drawable);
        }
        else {
            seekbar.setThumb(new SeekBar(context).getThumb());
            seekbar.setThumbTintList(ColorStateList.valueOf(color));
        }

        //value background
        background = view.findViewById(R.id.settings_value_background);
        background.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("SETTINGS_CLICKABLE_ITEM_BACKGROUND"), context, false));

        //value name text
        text = view.findViewById(R.id.settings_value_title);
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("SETTINGS_TEXT_SETTING_COLOR")));

        //value value text
        text = view.findViewById(R.id.setting_value_value);
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("SETTINGS_TEXT_SETTING_VALUE_COLOR")));

        //switch background
        background = view.findViewById(R.id.settings_boolean_background);
        background.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("SETTINGS_CLICKABLE_ITEM_BACKGROUND"), context, false));

        //value name text
        text = view.findViewById(R.id.settings_boolean_title);
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("SETTINGS_TEXT_SETTING_COLOR")));

        SwitchCompat swt = view.findViewById(R.id.settings_switch);
        ColorStateList switchStates = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_checked},
                        new int[]{}
                },
                new int[]{
                        mTheme.isDark ? res.getColor(R.color.theme_dark_elem_disabled) : res.getColor(R.color.theme_light_elem_disabled),
                        color,
                        mTheme.isDark ? res.getColor(R.color.theme_dark_elem_default) : res.getColor(R.color.theme_light_elem_default)
                }
        );
        drawable = ThemeParser.getBackground(mTheme.backgrounds.get("CONTROL_SWITCH_THUMB"), context, true);
        if (drawable != null) {
            swt.setThumbDrawable(drawable);
        }
        else {
            swt.setThumbDrawable(new Switch(context).getThumbDrawable());
            swt.getThumbDrawable().setTintList(switchStates);
        }

        drawable = ThemeParser.getBackground(mTheme.backgrounds.get("CONTROL_SWITCH_TRACK"), context, true);
        if (drawable != null) {
            swt.setTrackDrawable(drawable);
        }
        else {
            swt.setTrackDrawable(new Switch(context).getTrackDrawable());
            swt.getTrackDrawable().setTintList(switchStates);
        }
        swt.toggle();
        swt.toggle();

        //value background
        background = view.findViewById(R.id.settings_color_background);
        background.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("SETTINGS_CLICKABLE_ITEM_BACKGROUND"), context, false));

        //value name text
        text = view.findViewById(R.id.settings_color_title);
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("SETTINGS_TEXT_SETTING_COLOR")));

        //value value text
        text = view.findViewById(R.id.setting_color_value);
        text.setTextColor(ThemeParser.getColor(mTheme.colors.get("SETTINGS_TEXT_SETTING_VALUE_COLOR")));

        //Divider
        color = ThemeParser.getColor(mTheme.colors.get("SETTINGS_DIVIDER"));
        background = view.findViewById(R.id.divider_1);
        background.setBackgroundColor(color);
        background = view.findViewById(R.id.divider_2);
        background.setBackgroundColor(color);
        background = view.findViewById(R.id.divider_3);
        background.setBackgroundColor(color);


        //----Settings----
        drawable = ThemeParser.getBackground(mTheme.backgrounds.get("SETTINGS_BACKGROUND_CATEGORY"), context, false);
        color = ThemeParser.getColor(mTheme.colors.get("SETTINGS_TEXT_CATEGORY_COLOR"));

        //screen header
        background = view.findViewById(R.id.header_screen);
        background.setBackground(drawable);

        //screen header text
        text = view.findViewById(R.id.title_screen);
        text.setTextColor(color);

        //menu header
        background = view.findViewById(R.id.header_menu);
        background.setBackground(drawable);

        //menu header text
        text = view.findViewById(R.id.title_menu);
        text.setTextColor(color);

        //settings header
        background = view.findViewById(R.id.header_settings);
        background.setBackground(drawable);

        //settings header text
        text = view.findViewById(R.id.title_settings);
        text.setTextColor(color);
    }
}
