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

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

/**
 * The main activity of the theme
 * YOU SHOULD NOT HAVE TO CHANGE ANYTHING IN THIS CLASS UNLESS YOU WANT
 * TO CHANGE THE WAY THE PREVIEW OR MAIN PAGES LOOK.
 * @author Nils Büscher
 */
public class MainActivity extends AppCompatActivity {

    //region Members
    //------------------------------------------------

    private static final boolean DEBUG = false;

    //endregion

    //region Members
    //------------------------------------------------

    // Holds whether the dark mode is active
    private boolean mDarkMode = false;

    // Holds the theme for the dark mode
    private Theme mDarkTheme = null;

    // Holds the theme for the light mode
    private Theme mLightTheme = null;

    // The theme fragment
    private FragmentTheme mThemeFragment;

    //endregion

    //region Public methods
    //------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This section loads all themes and selected those themes that match the selected theme name
        //------------------------------------------------

        //Select the name of your theme
        String themeName = getString(R.string.theme_name);

        //run through all themes and parse them. You should not have to change anything here
        ArrayList<Theme> themes = ThemeParser.parseThemes(this);
        for (Theme theme : themes) {
            if (theme.name.equals(themeName)) {
                if (theme.isDark) {
                    mDarkTheme = theme;
                }
                else {
                    mLightTheme = theme;
                }
            }
        }
        if (mLightTheme == null) {
            mDarkMode = true;
        }

        setContentView(DEBUG? R.layout.activity_debug : R.layout.activity_main);
        mThemeFragment = (FragmentTheme)getSupportFragmentManager().findFragmentById(R.id.main_fragment);
        if (mThemeFragment != null) {
            mThemeFragment.setTheme(this, mDarkMode ? mDarkTheme : mLightTheme);
        }
        // Apply the theme to the main components
        applyTheme();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.setting_dark) {
            mDarkMode = !mDarkMode;
            item.setIcon(getDrawable(mDarkMode ? R.drawable.ic_light : R.drawable.ic_dark));
            if (mThemeFragment != null) {
                mThemeFragment.setTheme(this, mDarkMode ? mDarkTheme : mLightTheme);
            }
            applyTheme();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when the apply button was pressed to apply the theme
     * @param view The view that was pressed
     */
    @SuppressWarnings("unused")
    public void onApplyClicked(View view) {
        final Intent intent = new Intent();
        intent.setAction("com.pkg.perform.Ruby");
        intent.putExtra("THEME_PACKAGE", getPackageName());
        intent.putExtra("THEME_NAME", getString(R.string.theme_name));
        intent.putExtra("THEME_TYPE", 1);

        if (mThemeFragment != null) {
            intent.putExtra("THEME_HIDE", mThemeFragment.hideAfterApply());
        }

        boolean wallpaper = false;
        if (mLightTheme != null) {
            wallpaper = mLightTheme.hasWallpaper;
        }
        if (mDarkTheme != null) {
            wallpaper |= mDarkTheme.hasWallpaper;
        }
        intent.putExtra("THEME_WALLPAPER", wallpaper);

        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.setComponent(
                new ComponentName("org.n277.lynxlauncher", "org.n277.lynxlauncher.visual.ThemeBroadcastReceiver"));
        sendBroadcast(intent);

        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    //endregion

    //region Private methods
    //------------------------------------------------

    /**
     * Apply the theme to the main activity
     */
    private void applyTheme() {
        Theme theme = mDarkMode? mDarkTheme : mLightTheme;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(ThemeParser.getBackground(theme.backgrounds.get("TOOLBAR"),this, false));
            int toolbarTextColor = ThemeParser.getColor(theme.colors.get("TOOLBAR_TEXT_COLOR"));
            String htmlColor = String.format(Locale.US, "#%06X", (0xFFFFFF & Color.argb(0, Color.red(toolbarTextColor), Color.green(toolbarTextColor), Color.blue(toolbarTextColor))));
            getSupportActionBar().setTitle(Html.fromHtml("<font color=" + htmlColor + ">" + getString(R.string.theme_name) + "</font>"));
        }

        Window window = getWindow();
        window.setStatusBarColor(ThemeParser.getColor(theme.colors.get("SETTINGS_STATUS_BAR_COLOR")));
        window.setNavigationBarColor(ThemeParser.getColor(theme.colors.get("SETTINGS_NAVIGATION_BAR_COLOR")));
    }

    //endregion
}
