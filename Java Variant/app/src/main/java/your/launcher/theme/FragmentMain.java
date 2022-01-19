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

import androidx.annotation.Nullable;
import androidx.core.widget.CompoundButtonCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/**
 * The main fragment of the theme, that is visible to the user
 * @author Nils Büscher
 */
public class FragmentMain extends Fragment implements FragmentTheme {

    // The theme
    private Theme mTheme;

    // Holds the checkbox
    private CheckBox mCheckBox;

    public FragmentMain() {
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
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mCheckBox = v.findViewById(R.id.checkBox);
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
        return mCheckBox.isChecked();
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

        //The main background
        //View background = view.findViewById(R.id.main_layout);
        view.setBackground(ThemeParser.getBackground(mTheme.backgrounds.get("SETTINGS_BACKGROUND"), context, false));

        //The preview
        ImageView imageView = view.findViewById(R.id.theme_preview);
        imageView.setImageDrawable(res.getDrawable(mTheme.isDark? R.drawable.preview_dark : R.drawable.preview_light));

        //The text color
        TextView textView = view.findViewById(R.id.theme_text);
        textView.setTextColor(ThemeParser.getColor(mTheme.colors.get("SETTINGS_TEXT_SETTING_COLOR")));

        //The button
        ExtendedFloatingActionButton button = view.findViewById(R.id.floatingActionButton);
        button.setTextColor(0xFFF0F0F0);
        button.setIconTint(ColorStateList.valueOf(0xFFF0F0F0));

        mCheckBox.setTextColor(ThemeParser.getColor(mTheme.colors.get("SETTINGS_TEXT_SETTING_COLOR")));
        Drawable drawable = ThemeParser.getBackground(mTheme.backgrounds.get("CONTROL_CHECKBOX"), context, true);
        if (drawable != null) {
            mCheckBox.setButtonDrawable(drawable);
        }
        else {
            ColorStateList checkStates = new ColorStateList(
                    new int[][]{
                            new int[]{-android.R.attr.state_enabled},
                            new int[]{android.R.attr.state_checked},
                            new int[]{}
                    },
                    new int[]{
                            mTheme.isDark ? res.getColor(R.color.theme_dark_elem_disabled) : res.getColor(R.color.theme_light_elem_disabled),
                            ThemeParser.getColor(mTheme.colors.get("CONTROL_HIGHLIGHT_COLOR")),
                            mTheme.isDark ? res.getColor(R.color.theme_dark_elem_default) : res.getColor(R.color.theme_light_elem_disabled)
                    }
            );
            mCheckBox.setButtonDrawable(CompoundButtonCompat.getButtonDrawable(new CheckBox(context)));
            mCheckBox.setButtonTintList(checkStates);
        }
    }
}
