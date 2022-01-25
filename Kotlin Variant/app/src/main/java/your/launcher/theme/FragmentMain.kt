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
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.CompoundButtonCompat
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

/**
 * The main fragment of the theme. This one is shown to the users
 * @author Nils Büscher
 */
class FragmentMain : Fragment(), FragmentTheme {

    //region Members
    //------------------------------------------------

    // The theme
    private var mTheme: Theme? = null

    // Holds the checkbox
    private lateinit var mCheckBox: CheckBox

    //endregion

    //region Public Methods
    //------------------------------------------------

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        mCheckBox = view.findViewById(R.id.checkBox)
        applyTheme(requireContext(), view)

        return view
    }

    /**
     * Set the [theme] used for a fragment. Requires the [context] of the application
     */
    override fun setTheme(context: Context, theme: Theme?) {
        if (mTheme != theme) {
            mTheme = theme
            applyTheme(requireContext(), view ?: return)
        }
    }

    /**
     * This method returns if the theme icon of the launcher should
     * be hidden after the theme is applied
     */
    override fun hideAfterApply() = mCheckBox.isChecked

    //endregion

    //region Private Methods
    //------------------------------------------------
    /** Apply the theme to the [view]. The [context] of the application is required */
    private fun applyTheme(context: Context, view: View) {
        val theme = mTheme ?: return

        //The main background
        view.background = ThemeParser.getBackground(theme.backgrounds[SETTINGS_BACKGROUND], context, false)

        //The preview
        val imageView = view.findViewById<ImageView>(R.id.theme_preview)
        var drawable = ContextCompat.getDrawable(context, if (theme.isDark) R.drawable.preview_dark else R.drawable.preview_light)
        imageView.setImageDrawable(drawable)

        //The text color
        val textView = view.findViewById<TextView>(R.id.theme_text)
        textView.setTextColor(ThemeParser.getColor(theme.colors[SETTINGS_TEXT_SETTING_COLOR]))

        //The button
        val button: ExtendedFloatingActionButton = view.findViewById(R.id.floatingActionButton)
        button.setTextColor(-0x0f0f10)
        button.iconTint = ColorStateList.valueOf(-0x0f0f10)
        mCheckBox.setTextColor(ThemeParser.getColor(theme.colors[SETTINGS_TEXT_SETTING_COLOR]))

        drawable = ThemeParser.getBackground(theme.backgrounds[CONTROL_CHECKBOX], context, true)
        if (drawable != null) {
            mCheckBox.buttonDrawable = drawable
        } else {
            val checkStates = ColorStateList(
                arrayOf(
                    intArrayOf(-android.R.attr.state_enabled),
                    intArrayOf(android.R.attr.state_checked),
                    intArrayOf()
                ),
                intArrayOf(
                    ContextCompat.getColor(context, if (theme.isDark) R.color.theme_dark_elem_disabled
                        else R.color.theme_light_elem_disabled),
                    ThemeParser.getColor(theme.colors[CONTROL_HIGHLIGHT_COLOR]),
                    ContextCompat.getColor(context, if (theme.isDark) R.color.theme_dark_elem_default
                        else R.color.theme_light_elem_disabled),
                )
            )
            mCheckBox.buttonDrawable = CompoundButtonCompat.getButtonDrawable(CheckBox(context))
            mCheckBox.buttonTintList = checkStates
        }
    }

    //endregion
}