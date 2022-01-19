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

import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import your.launcher.theme.ThemeParser.Companion.getBackground
import your.launcher.theme.ThemeParser.Companion.getColor
import your.launcher.theme.ThemeParser.Companion.parseThemes
import java.util.*

class MainActivity : AppCompatActivity() {

    //region Members

    private val sDEBUG = false

    //endregion

    //region Members
    //------------------------------------------------
    // Holds whether the dark mode is active
    private var mDarkMode = false

    // Holds the theme for the dark mode
    private var mDarkTheme: Theme? = null

    // Holds the theme for the light mode
    private var mLightTheme: Theme? = null

    // The theme fragment
    private var mThemeFragment: FragmentTheme? = null

    //endregion

    //region Public methods
    //------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //This section loads all themes and selected those themes that match the selected theme name
        //------------------------------------------------

        //Select the name of your theme
        val themeName = getString(R.string.theme_name)

        //run through all themes and parse them. You should not have to change anything here
        val themes = parseThemes(this)
        for (theme in themes) {
            if (theme.name == themeName) {
                if (theme.isDark) {
                    mDarkTheme = theme
                } else {
                    mLightTheme = theme
                }
            }
        }
        if (mLightTheme == null) {
            mDarkMode = true
        }
        setContentView(if (sDEBUG) R.layout.activity_debug else R.layout.activity_main)

        mThemeFragment = supportFragmentManager.findFragmentById(R.id.main_fragment) as FragmentTheme?
        mThemeFragment?.setTheme(this, if (mDarkMode) mDarkTheme else mLightTheme)
        // Apply the theme to the main components
        applyTheme()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.setting_dark) {
            mDarkMode = !mDarkMode
            item.icon = ContextCompat.getDrawable(this, if (mDarkMode) R.drawable.ic_light else R.drawable.ic_dark)
            mThemeFragment?.setTheme(this, if (mDarkMode) mDarkTheme else mLightTheme)
            applyTheme()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Called when the apply button was pressed to apply the theme
     * @param view The view that was pressed
     */
    @Suppress("UNUSED_PARAMETER")
    fun onApplyClicked(view: View?) {

        val intent = Intent()
        intent.action = "com.pkg.perform.Ruby"
        intent.putExtra("THEME_PACKAGE", packageName)
        intent.putExtra("THEME_NAME", getString(R.string.theme_name))
        intent.putExtra("THEME_TYPE", 1)
        intent.putExtra("THEME_HIDE", mThemeFragment?.hideAfterApply())

        var wallpaper = false
        if (mLightTheme != null) {
            wallpaper = mLightTheme?.hasWallpaper == true
        }
        if (mDarkTheme != null) {
            wallpaper = wallpaper or (mDarkTheme?.hasWallpaper == true)
        }

        intent.putExtra("THEME_WALLPAPER", wallpaper)
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        intent.component = ComponentName(
            "org.n277.lynxlauncher",
            "org.n277.lynxlauncher.visual.ThemeBroadcastReceiver"
        )
        sendBroadcast(intent)
        val startMain = Intent(Intent.ACTION_MAIN)
        startMain.addCategory(Intent.CATEGORY_HOME)
        startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(startMain)
    }

    //endregion

    //region Private methods
    //------------------------------------------------

    //endregion
    //region Private methods
    //------------------------------------------------
    /**
     * Apply the theme to the main activity
     */
    private fun applyTheme() {
        val theme = if (mDarkMode) mDarkTheme!! else mLightTheme!!
        if (supportActionBar != null) {
            supportActionBar!!.setBackgroundDrawable(
                getBackground(
                    theme.backgrounds["TOOLBAR"],
                    this,
                    false
                )
            )
            val toolbarTextColor = getColor(theme.colors["TOOLBAR_TEXT_COLOR"])
            val htmlColor = String.format(
                Locale.US, "#%06X",
                0xFFFFFF and Color.argb(
                    0,
                    Color.red(toolbarTextColor),
                    Color.green(toolbarTextColor),
                    Color.blue(toolbarTextColor)
                )
            )
            supportActionBar!!.title = HtmlCompat.fromHtml("<font color=" + htmlColor + ">"
                    + getString(R.string.theme_name) + "</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
        val window = window
        window.statusBarColor = getColor(theme.colors["SETTINGS_STATUS_BAR_COLOR"])
        window.navigationBarColor = getColor(theme.colors["SETTINGS_NAVIGATION_BAR_COLOR"])
    }

    //endregion
}
