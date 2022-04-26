This list contains all theming items that are available for Lynx Launcher.
Some **Background** items are optional, some need additional information, like *padding* or *size*.
All **colors** and **icons** have to be set in the theme.xml. All **icons** must have a size of 24dp x 24dp.

**Boolean** items that are not set, result in false.
**Dimension** items that are not set, result in a size of 0;


# All Screens
Theme items that are commonly used in all screens. 

## Background

**MAIN_GRID_ITEM**
Background for items on the main screen when they are shown in a grid.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (4dp ,4dp, 4dp, 4dp).

**MAIN_LIST_ITEM**
Background for items on the main screen when they are shown in a list.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (6dp ,6dp, 6dp, 6dp).

**MAIN_HEADER** [Optional]
Used for the background of header for differnt reagions on the screens. For example on the the application screen, the header devides the apps that begin with different first letters.
 - This item **must** add have a padding defined. The default is (10dp ,0dp, 10dp, 0dp).

**MAIN_SEPARATOR** [Optional]
The separator is the horizontal line used in the header to separate sections from another.
- This item **must** add have a height defined, otherwise it will be invisible. The default height is 1dp.

**SCREEN_BORDER**
Background optionally shown at the background of screens while a transition between two screens takes place.

**MAIN_CONTACT_ACTION**
Background of the buttons to conduct a direct action for a contact.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (10dp , 10dp, 10dp, 10dp).

## Colors

**MAIN_HEADER_TEXT_COLOR**
Color used for the text in the screens for the headers

**MAIN_ITEM_TEXT_COLOR**
Main color used for text of normal items shown on the screens.

**MAIN_ITEM_TEXT_COLOR_SECONDARY**
Secondary color used for text of normal items shown on the screens. E,g, for the phone numbers of contacts.

**MAIN_NAVIGATION_BAR_COLOR**
The color of the navigation bar in the main screen of the launcher.

**MAIN_STATUS_BAR_COLOR**
The color of the status bar in the main screen of the launcher.

## Booleans

**MAIN_DARK_NAVIGATION_BAR_ICONS**
When the the icons in the navigation bar in the main screen are dark, else they are bright.

**MAIN_DARK_STATUS_BAR_ICONS**
When the the icons in the status bar in the main screen are dark, else they are bright.


# Home Screen
The home screen is the main screen which is visible as the first screen of the launcher. It also houses the dock.

## Drawables

**HOME_CLOCK_CENTER**
When the anlog clock is shown on the home screen. This drawable is used as the center above the hour and minute hands.

**HOME_CLOCK_MINUTE_HAND**
When the anlog clock is shown on the home screen. This drawable is used as the minute hand of said clock.
 - This item **must** add have a defined height and width. The default is: width = 4dp, height = 50dp.

**HOME_CLOCK_HOUR_HAND**
When the anlog clock is shown on the home screen. This drawable is used as the hour hand of said clock.
 - This item **must** add have a defined height and width. The default is: width = 5dp, height = 50dp.

**HOME_CLOCK_BACKGROUND**
When the anlog clock is shown on the home screen. This drawable is used as the background of said clock.
 - Depending on the boolean setting *HOME_CLOCK_DRAW_NUMERALS* the background *should* show numbers for the times on its background.
 - This item **must** add have a defined height and width. The default is: width = 7dp, height = 7dp.

## Booleans

**HOME_CLOCK_DRAW_NUMERALS**
When true, the analog clock on the home screen draws numbers at 12, 3, 6 and 9.

**HOME_CLOCK_CAN_COLOR**
When true, the user can change the color of the analog clock. The color is added with a *multiply* filter.

# Dock
The dock is the bar, on the left or right side of the home screen which can house apps, shortcuts and folders.

## Values

**DOCK_VERT_BORDER_SIDES**
The border on the sides of the dock, when it is placed at the bottom of the screen

**DOCK_VERT_BORDER_BOTTOM**
The border below the dock, when it is placed at the bottom of the screen

## Backgrounds

**DOCK_BACKGROUND_LEFT**
The background of the dock, when it is on the left site of the screen.
 - This item **must** add have a padding defined. The default is (1dp , 3dp, 3dp, 3dp).
 - The dock supports a blurred background. To add support in your theme, this drawable needs to be a [layerDrawable](https://developer.android.com/guide/topics/resources/drawable-resource#LayerList). One layer should have the name *"@android:id/mask"*. This layer acts as the mask to create the blur effect

**DOCK_BACKGROUND_RIGHT**
The background of the dock, when it is on the right site of the screen.
 - This item **must** add have a padding defined. The default is (3dp , 3dp, 1dp, 3dp).
 - The dock supports a blurred background. To add support in your theme, this drawable needs to be a [layerDrawable](https://developer.android.com/guide/topics/resources/drawable-resource#LayerList). One layer should have the name *"@android:id/mask"*. This layer acts as the mask to create the blur effect
 
**DOCK_BACKGROUND_BOTTOM**
The background of the dock, when it is on the bottom of the screen.
 - This item **must** add have a padding defined. The default is (3dp , 3dp, 3dp, 3dp).
 - The dock supports a blurred background. To add support in your theme, this drawable needs to be a [layerDrawable](https://developer.android.com/guide/topics/resources/drawable-resource#LayerList). One layer should have the name *"@android:id/mask"*. This layer acts as the mask to create the blur effect
 
 
**DOCK_ENTRY_BACKGROUND**
The background of items that are placed inside the dock.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (2dp ,4dp, 2dp, 2dp).

**DOCK_ALL_APPS_ICON** [Optional]
The icon shown in the dock to move to the *All Applications* screen.
When not specified, the default drawable will be used.
 - This item **must** add have size of width = 56dp, height = 56dp.

# All applications screen
The applications screen list all applications that are installed on the device.

## Backgrounds

**APP_LIST_INDEXER_SELECTION**
The drawable used to show which letters are currently visible at the application screen.

**APP_LIST_INDEXER**
The background of the indexer for the alphabetical index.

**APP_LIST_INDEXER_HINT**
The background of the hint shown next to the finger tip to show the current starting letter.

**PROFILE_BUTTON**
The background of the buttons to change the profile (work or private).

**PROFILE_BACKGROUND**
The background of the button bar for the profile buttons.

## Colors

**APP_LIST_INDEX_BAR_ACTIVE_TEXT_COLOR**
Color used for the letters used in the alphabetical index bar for letters that are currently visible.

**APP_LIST_INDEX_BAR_INACTIVE_TEXT_COLOR**
Color used for the letters used in the alphabetical index bar for letters that are currently NOT visible.

**APP_LIST_INDEX_HINT_TEXT_COLOR**
Color used for the letter in the hint shown next to the finger tip to show the current starting letter.

**PROFILE_ACTIVE**
The text color of the buttons for the active profile.

**PROFILE_INACTIVE**
The text color of the buttons for the inactive profile.

# Favorites Screen
The favorites screen shows all the favorite apps and contacts.

## Backgrounds

**CONTACT_SELECT_BACKGROUND**
The background of the button in the header for the favorite contacts that lets the user choose which contacts to show in the favorites.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined.

## Icons

**ICON_FAVORITES_CONTACT**
Icon used in the header for the contacts shown in the favorites screen.

**ICON_FAVORITES_CALL**
Icon of the option to dial the number of the contact.

**ICON_FAVORITES_MESSAGE**
Icon of the option to open the messages app for the contact.


# Desktop Screens
The desktop screens can house the widgets, apps, folder and shortcuts

## Background

**DESKTOP_LEFT_SCREEN**
Hint for a screen shown on the left site of a desktop when the user is performing a drag and drop to indicate the the user can drag the item to a screen on the left.
(This drawable is not used in LynxLauncher version 1.4.0 and later)

**DESKTOP_RIGHT_SCREEN**
Hint for a screen shown on the right site of a desktop when the user is performing a drag and drop to indicate the the user can drag the item to a screen on the right.
(This drawable is not used in LynxLauncher version 1.4.0 and later)

**DESKTOP_ITEM_BACKGROUND**
Background for apps, shortcuts and folders places on the desktop.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (4dp ,4dp, 4dp, 4dp).

## Colors

**DESKTOP_DROP_HINT**
Color used to draw the cross-hairs showing where the currently dragged items will be placed when it is dropped.

**DESKTOP_SCALE_HANDLES**
Color used for the handles to scaling a widget.

 **DESKTOP_TEXT**
 Color used for texts shown for entries shown on the desktop.

# Search Screen
The search screen shown the results of a search or previously used search results when no search query was entered yet.

## Icons

**ICON_SEARCH_RESULT_APPS**
Icon for the header of the app search results.

**ICON_SEARCH_RESULT_CONTACT**
Icon for the header of contacts search results.

**ICON_SEARCH_RESULT_SETTING**
Icon for the header of settings search results.

**ICON_SEARCH_RESULT_CALCULATOR**
Icon for the header of search results for a mathematical equation.

**ICON_SEARCH_RESULT_RECENT**
Icon for the header when the recent search results are shown.

**ICON_SEARCH_RESULT_WEB**
Icon for the header for the online search options.

**ICON_SEARCH_RESULT_SHORTCUTS**
Icon for the header of shortcuts search results. 

# Search bar
The search bar is placed on top of most screens and can be used to start a fast search for apps, contacts etc.

## Backgrounds

**SEARCH_BAR_BACKGROUND**
The background of the search bar shown on top of the screens.
 - This item **must** add have a padding defined. The default is (4dp , 0dp, 4dp, 0dp).
 - The searchbar supports a blurred background. To add support in your theme, this drawable needs to be a layer drawable. One layer should have the name *"@android:id/mask"*. This layer acts as the mask to create the blur effect
 
## Colors

**SEARCH_BAR_TEXT**
The color of the text shown in the search bar

**SEARCH_BAR_TEXT_HINT**
The color of the hint shown in the search bar when no query is entered

**SEARCH_BAR_TEXT_HIGHLIGHT**
The color of the highlight when the text in the search bar is selected

**SEARCH_BAR_TEXT_HANDLE**
The color of the selection handle in the search bar. Does not work on Android 10 and above.

**SETTING_ICON_BACKGROUND**
The color of setting items that can be searched for.

## Icons

**ICON_SEARCH**
The search icon used in the search bar.

**ICON_SEARCH_ABORT**
Icon in the search bar to abort searching.

**ICON_SEARCH_VOICE**
Icon in the search bar to start a voice search.

**ICON_SEARCH_RESET**
Icon in the search bar to reset the current search query.

**ICON_SEARCH_SELECT**
Icon in the search bar to select or order the search results.

## Values

**SEARCH_BAR_VERTICAL_BORDER**
Width of the margin to the top and bottom of the search bar. 

**SEARCH_BAR_HORIZONTAL_BORDER**
Width of the margin to the left and right of the search bar. 


# Management Screen
The management screen allows to change and alter the positions of the screens.
The items for this screen are NOT optional but will be replaced with the default theme items when not defined for
compatibility reasons

## Booleans

**MANAGE_DARK_NAVIGATION_BAR_ICONS**
Holds whether the icons in the navigation bar should be dark or light. When set to true, the icons are dark.

## Backgrounds

**MANAGE_SHEET**
Background of the bottom sheet for the management options.

**MANAGE_SCREEN**
Background of a used screen in the management screen.

**MANAGE_EMPTY**
Background of an unused screen / empty space in the management screen.

**MANAGE_ACCEPT**
Background of the button to accept the changed layout.

**MANAGE_ITEM**
Background of the clickable items in the bottom sheet of the management screen.

**MANAGE_REMOVE**
Background of the button to remove a screen.

**MANAGE_ADD**
Background of the button to add a screen.

## Icons

**ICON_MANAGE_ONE_FINGER**
Icon in the management screen to switch to the one finger gestures.

**ICON_MANAGE_TWO_FINGERS**
Icon in the management screen to switch to the two finger gestures.

**ICON_MANAGE_PRESETS**
Icon in the management screen to show available presets for the screen layout.

**ICON_MANAGE_INFINITE_SCROLL**
Icon in the management screen to show the infinite scroll options.

**ICON_MANAGE_MORE**
Icon in the management screen to show more available options.

**ICON_MANAGE_ADVANCED**
Icon in the management screen to switch to the advanced mode.

**ICON_MANAGE_NORMAL**
Icon in the management screen to switch to the normal mode.

**ICON_MANAGE_RESET**
Icon in the management screen to reset the current layout.

**ICON_MANAGE_APPLY**
Icon in the management screen to apply the current layout.

**ICON_MANAGE_SCREEN_REMOVE**
Icon in the management screen to remove a screen.

**ICON_MANAGE_SCREEN_ADD**
Icon in the management screen to add a screen.

**ICON_MANAGE_SCREEN_HOME**
Icon in the management screen for the home screen.

**ICON_MANAGE_SCREEN_APPS**
Icon in the management screen for the applications screen.

**ICON_MANAGE_SCREEN_FAVORITES**
Icon in the management screen for the favorites screen.

**ICON_MANAGE_SCREEN_DESKTOP**
Icon in the management screen for the desktop screen.

**ICON_MANAGE_SCREEN_SEARCH**
Icon in the management screen for the search screen.

## Colors

**MANAGE_TEXT_HEADER**
The color of the text in the header of the screen manager.

**MANAGE_TEXT_SUBTITLE**
The color of the subtitle text in the header of the screen manager.

**MANAGE_TEXT_ITEM**
The color of texts of the selectable options in the screen manager.

**MANAGE_TEXT_SCREEN**
The color of texts of the screens in the screen manager.

## Booleans

**MANAGE_DARK_NAVIGATION_BAR_ICONS**
When the the icons in the navigation bar in the management screen are dark, else they are bright.

# Folders
Folders can house multiple apps and shortcuts at one place. When clicked, the folder is shown with its content.

## Backgrounds

**FOLDER_BACKGROUND**
The background of the folders when they are opened.
 - This item **must** add have a padding defined. The default is (4dp , 4dp, 4dp, 4dp).
 - The folders supports a blurred background. To add support in your theme, this drawable needs to be a [layerDrawable](https://developer.android.com/guide/topics/resources/drawable-resource#LayerList). One layer should have the name *"@android:id/mask"*. This layer acts as the mask to create the blur effect

 **FOLDER_ITEM_BACKGROUND**
The background of items that are placed inside the folder.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (4dp , 4dp, 4dp, 4dp).

**FOLDER_SORT_BACKGROUND**
The background of *sort* button shown in the folder view
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (4dp , 4dp, 4dp, 4dp).

## Colors

**FOLDER_NAME_TEXT**
The color of the text showing the name of the folder.

**FOLDER_NAME_HIGHLIGHT**
The color fo the highlight when the name of the folder is selcted.

**FOLDER_NAME_HANDLE**
The color of the selection handle in the folder view. Does not work on Android 10 and above.

**FOLDER_ITEM_TEXT**
The color of the text shown with items in the folder.

**FOLDER_DEFAULT_BACKGROUND_COLOR**
The default background color of the folder. Used as default color when the user wants to select a color for the background of the folder instead of the provided drawable.

## Icons

**ICON_FOLDER_SORT**
Icon for the button when the items in the folder should be sorted.

# Context Menus
Menus are the context menus to manipulate apps, shortcuts, folders and widgets.

## Backgrounds

**POPUP_BACKGROUND**
The background for the context menus.
 - This item **must** add have a padding defined. The default is (14dp , 14dp, 14dp, 14dp).
 - When arrows are used, the drawable **should** be inset by the size of the arrows. The padding *should* should include the additional border size.
 
**POPUP_ARROW_LEFT** [Optional]
The arrow for the context menus pointing to the left.
 - This item **must** add have a widht and a height defined. The default is: width = 12dp, height = 24dp.

 **POPUP_ARROW_RIGHT** [Optional]
The arrow for the context menus pointing to the right.
 - This item **must** add have a widht and a height defined. The default is: width = 12dp, height = 24dp.

 **POPUP_ARROW_UP** [Optional]
The arrow for the context menus pointing upwards.
 - This item **must** add have a widht and a height defined. The default is: width = 24dp, height = 12dp.

 **POPUP_ARROW_DOWN** [Optional]
The arrow for the context menus pointing downwards.
 - This item **must** add have a widht and a height defined. The default is: width = 24dp, height = 12dp.

**POPUP_ITEM_BACKGROUND**
The background of items that are placed inside the context menus.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (0dp , 0dp, 0dp, 0dp).

**POPUP_DIVIDER**
 The divider separating the top bar with icons and the list items in the context menus.
 - This item **must** add have a height defined to be visible. The default height is 1dp.

## Colors
**POPUP_ITEM_TEXT_COLOR**
The color of the text of items in the context menus

## Icons

**ICON_POPUP_INFO**
Icons in the context menu to show the info screen of an application

**ICON_POPUP_EDIT**
Icon in the context menu to edit an application or folder.

**ICON_POPUP_REMOVE**
Icon in the context menu to remove the item from the dock, screen or folder.

**ICON_POPUP_WALLPAPER**
Icon in the main context menu to change the wallpaper

**ICON_POPUP_SETTINGS**
Icon in the main context menu to open the launchers settings.

**ICON_POPUP_WIDGETS**
Icon in the main context menu to add a widget.

**ICON_POPUP_PIN**
Icon used on the favorites screen to pin an application.

**ICON_POPUP_UNPIN**
Icon used on the favorites screen to unpin an application.

**ICON_POPUP_SCALE**
Icon used in the context menu of a widget to start scaling it.

**ICON_POPUP_REFRESH**
Icon used in the context menu of a widget to refresh it.

**ICON_POPUP_ENABLE_WORK**
Icon in the main context menu to enable the work profile if available.

**ICON_POPUP_DISABLE_WORK**
Icon in the main context menu to disable the work profile if available.

**ICON_POPUP_MANAGE_SCREENS**
Icon in the main context menu to switch to the screen layout management screen.

**ICON_UNINSTALL**
Icon in the main context menu of an app, to uninstall said app.

**ICON_UNINSTALL**
Icon used on the applications screen to uninstall an application.

# Dialogs
Dialogs are used mainly for the settings or to change the appearance of applications or folders.

## Backgrounds

**DIALOG_BACKGROUND**
The background of the dialogs.
 - This item **must NOT** have a padding defined.

**DIALOG_TITLE_TOOLBAR**
The toolbar of the dialogs.
 - This item **must** have a padding defined. The default is (0dp , 0dp, 0dp, 0dp).

**DIALOG_BUTTON**
The background of the standard buttons appearing at the bottom of the dialogs.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (12dp , 0dp, 12dp, 0dp).

**DIALOG_CONTENT_BUTTON**
The background of the buttons shown in the content area of the dialogs
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (12dp , 0dp, 12dp, 0dp).

**DIALOG_SELECTABLE_ITEM**
When the dialog shows a list of selectable items, this drawable is used for their background.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (16dp , 6dp, 16dp, 6dp).

**DIALOG_SELECTABLE_ICON**
Used for the background of the items in dialogs to select another icons for an application or folder.
This background should not be entirely white or black to have a high contrast to most possible icons.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must NOT** add have a padding defined.

**DIALOG_CHECKBOX** [Optional]
The appearance of the checkbox used for checkboxes inside dialogs.
When not set, Lynx launcher uses the standard material drawable and colors it accordingly to *CONTROL_HIGHLIGHT_COLOR*.
 - This item *should* implement a state selector to show when the item is clicked.

**DIALOG_HEADER**
When dialogs show different categories of items (e.g. for the changing the app icons) the header is used for the background of the header for each section.
 - This item **must** add have a padding defined. The default is (0dp , 0dp, 0dp, 0dp).

**DIALOG_LISTENING**
 Drawable used in the listening dialog for showing the icon in its center
 - This item **must** add have a widht and a height defined. The default is: width = 48dp, height = 48dp.

**DIALOG_LISTENING_PULSE**
Drawable used in the listening dialog for pulse behinde the listening icon in its center.
 - This item **must** add have a widht and a height defined. The default is: width = 48dp, height = 48dp.

## Colors

**DIALOG_DIVIDER**
The color of dividers in the dialogs. The dividers are used between the header of the dialog and its content as well as the button bar at the bottom and the content of the dialog.

**DIALOG_TEXT_TITLE_COLOR**
The color of the text shown in the title bar of the dialog.

**DIALOG_TEXT_PRIMARY_COLOR**
The text color used primariliy in the dialog for items

**DIALOG_TEXT_SECONDARY_COLOR**
The color used as a secondary color in the dialog. E.g. as a hint.

**DIALOG_TEXT_HIGHLIGHT**
The color fo the highlight when the name of the folder is selcted.

**DIALOG_TEXT_HANDLE**
The color of the selection handle in the dialog. Does not work on Android 10 and above.

**DIALOG_LICENSE_BACKGROUND**
The background color used in the license dialog for the license text.

**DIALOG_TEXT_HEADER_COLOR**
The color of the text in the header for sections in the dialogs content.

**DIALOG_TEXT_BUTTON_COLOR**
The color of texts shown in a button of the dialog.

**DIALOG_ICON_SHAPE_COLOR**
The color used in the dialog to select a shape for the folder or app icons for shapes that are currently not selected.

**DIALOG_ICON_COLOR_SELECTED**
The color used in the dialog to select a shape for the folder or app icons for the shape that is currently selected.

**DIALOG_SCROLL_BAR**
The color of the scroll bar in the dialog

## Icons

**ICON_HIDE**
Icon used in the applications dialog to hide an application.

**ICON_SHOW**
Icon used in the applications dialog to show an application again.

**ICON_RESET**
Icons used to reset the name of a folder or application

# Settings Activity
The settings activity houses all the settings the user can change in the launcher.

## Backgrounds

**SETTINGS_BACKGROUND**
The background of setting screens
 - This item **must NOT** add have a padding defined.

**SETTINGS_MAIN_BACKGROUND**
The background used in the main screen of the settings
 - This item **must NOT** add have a padding defined.

**SETTINGS_TAB_BACKGROUND**
When tabs are shown in the settings, this drawable is used as their background
 - This item **must NOT** add have a padding defined.

**SETTINGS_BACKGROUND_CATEGORY**
Background of the header used in the settings screens to separate different sections of the settings.
 - This item **must** add have a padding defined. The default is (0dp , 0dp, 0dp, 0dp).

**SETTINGS_CLICKABLE_ITEM_BACKGROUND**
Background of a clickable item in the settings. This item should implement a state selector to show its state.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must NOT** add have a padding defined.

**SETTINGS_CLICKABLE_MAIN_SETTING_BACKGROUND**
Background of a clickable item in the main settings screen. This item should implement a state selector to show its state.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must NOT** add have a padding defined.

## Colors

**SETTINGS_TEXT_CATEGORY_COLOR**
Color of the texts shown in the category header.

**SETTINGS_TAB_TEXT_COLOR_ACTIVE**
Color of the name of a tab when this tab is currently active.

**SETTINGS_TAB_TEXT_COLOR_INACTIVE**
Color of the name of a tab when this tab is currently NOT active.

**SETTINGS_TEXT_TITLE_COLOR**
Color for the texts used for the name of a setting category on the main settings screen

**SETTINGS_TEXT_SETTING_COLOR**
Color for the texts used for the name of a setting.

**SETTINGS_TEXT_SETTING_VALUE_COLOR**
Color used for the current value of a setting.

**SETTINGS_DIVIDER**
Color of the divider between settings in the same category.

**SETTINGS_ICON_COLOR**
Color used for an icon in a shape setting.

**SETTINGS_SCROLL_BAR**
Color used for the scroll bars in the settings screens.

**SETTINGS_BACKGROUND_WARNING**
Color for a warning shown in the settings when some needed permission are not granted. Should be held in a reddish signal color.

**SETTINGS_NAVIGATION_BAR_COLOR**
The color of the navigation bar in the settings screen.

**SETTINGS_STATUS_BAR_COLOR**
The color of the status bar in the settings screen.

## Icons

**ICON_SETTINGS_HOME**
Icon in the main settings screen for the settings of the home screen.

**ICON_SETTINGS_DOCK**
Icon in the main settings screen for the settings of the dock.

**ICON_SETTINGS_APPS**
Icon in the main settings screen for the settings of the all apps screen.

**ICON_SETTINGS_FAVORITES**
Icon in the main settings screen for the settings of the favorites screen.

**ICON_SETTINGS_SEARCH**
Icon in the main settings screen for the settings of the search screen.

**ICON_SETTINGS_DESKTOP**
Icon in the main settings screen for the settings of the desktop screens.

**ICON_SETTINGS_GENERAL**
Icon in the main settings screen for the general settings of the screens.

**ICON_SETTINGS_NOTIFICATIONS**
Icon in the main settings screen for all settings regarding notifications.

**ICON_SETTINGS_FOLDER**
Icon in the main settings screen for all settings regarding folders.

**ICON_SETTINGS_APPEARANCE**
Icon in the main settings screen for the theming options.

**ICON_SETTINGS_GESTURES**
Icon in the main settings screen for screen to change gestures.

**ICON_SETTINGS_HIDDEN_APPS**
Icon in the main settings screen for screen showing all apps that are currently hidden.

**ICON_SETTINGS_SAVE_AND_RESTORE**
Icon in the main settings screen for options to save and restore the launcher or reset it.

**ICON_SETTINGS_FEEDBACK**
Icon in the main settings screen to take the user to the store page of the app to make a feedback.
(This icon is not used in LynxLauncher version 1.4.0 and later)

**ICON_SETTINGS_INFO**
Icon in the main settings screen for options regarding information about the launcher.

**ICON_SETTINGS_HELP**
Icon in the main settings screen for options regarding "help & support" for the launcher.

**ICON_SETTINGS_WORK_PROFILE**
Icon in the main settings screen for options for the work profile if available.

**ICON_SETTINGS_DATA_PROTECTION**
Icon in the main settings screen to show a dialog with the privacy protection texts.
(This icon is not used in LynxLauncher version 1.4.0 and later)

**ICON_SETTINGS_LICENSES**
Icon in the main settings screen to show a dialog showing the licenses used by the launcher.
(This icon is not used in LynxLauncher version 1.4.0 and later)

**ICON_SETTINGS_VERSION**
Icon in the main settings screen for the version of the application

**ICON_SETTINGS_HOME_RESET**
Icon in the main settings screen to reset the launcher on the smartphone and select a new one

**ICON_SETTINGS_SHOW**
Icon in the toolbar in the settings with the hidden apps to show the selected hidden apps again.

**ICON_SETTINGS_INFO**
Icon for the main settings to display information about the launcher

**ICON_SETTINGS_HELP**
Icon for the main settings to display options to get help or solve problems


## Booleans

**SETTINGS_DARK_NAVIGATION_BAR_ICONS**
When the the icons in the navigation bar in the settings screen are dark, else they are bright.

**SETTINGS_DARK_STATUS_BAR_ICONS**
When the the icons in the status bar in the settings screen are dark, else they are bright.

# Controls
Theme items used for controls used in the setting screen of the application

## Backgrounds

**CONTROL_SWITCH_THUMB** [Optional]
The thumb of a switch. When a color is used for this items, Lynx launcher uses the standard material drawable and colors it accordingly.
 - This item *should* implement a state selector to show when the item is clicked, or selected.
 - This item **must** add have defined width and height. Both dimension *should* not be smaller than 24dp.

**CONTROL_SWITCH_TRACK** [Optional]
The track/background of a switch. When not set, Lynx launcher uses the standard material drawable and colors it accordingly.
 - This item *should* implement a state selector to show when the item checked.
 - This item **must** add have defined width and height. Both dimension *should* not be smaller than 24dp.

**CONTROL_SEEKBAR_THUMB** [Optional]
The thumb of a seekbar. When not set, Lynx launcher uses the standard material drawable and colors it accordingly to *CONTROL_HIGHLIGHT_COLOR*.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have defined width and height. Both dimension *should* not be smaller than 24dp.

**CONTROL_SEEKBAR_PROGRESS** [Optional]
The progress bar of the seekbar. When not set, Lynx launcher uses the standard material drawable and colors it accordingly to *CONTROL_HIGHLIGHT_COLOR*.
 - This item *should* implement a state selector to show the progress.
 - This item **must** add have defined width and height.

**CONTROL_CHECKBOX** [Optional]
Drawable for checkboxes. When not set, Lynx launcher uses the standard material drawable and colors it accordingly to *CONTROL_HIGHLIGHT_COLOR*.
 - This item *should* implement a state selector to show when the item is clicked, or checked.
 - This item **must** add have defined width and height. Both dimension *should* not be smaller than 24dp.

**CONTROL_RADIOBUTTON** [Optional]
Drawable for radio buttons. When not set, Lynx launcher uses the standard material drawable and colors it accordingly to *CONTROL_HIGHLIGHT_COLOR*.
 - This item *should* implement a state selector to show when the item is clicked, or selected.
 - This item **must** add have defined width and height. Both dimension *should* not be smaller than 24dp.

**CONTROL_WARNING_BUTTON** [Optional]
Drawable for a button used to solve a problem with permissions. This button should be held in a reddish signal color. This item should implement a state selector to show when the item is clicked.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (12dp , 0dp, 12dp, 0dp).

**CONTROL_BUTTON_SETTINGS**
Background of a button on some screens that lead to other settings
This item should implement a state selector to show when the item is clicked.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must NOT** add have a padding defined.

## Colors

**CONTROL_HIGHLIGHT_COLOR**
Color used to color the controls where the no drawable is provided.

# Miscellaneous
Theme items that do not fit into any other category

## Backgrounds

**BADGE_ICON**
The drawable used for notification badges
 - This item **must NOT** add have a padding defined.

**APP_WIDGET_HEADER**
Background of the header item in the list of seletable widgets when the user wants to add a widget to the launcher.
 - This item **must** add have a padding defined. The default is (4dp , 0dp, 4dp, 0dp).

**APP_WIDGET_PREVIEW**
Background of the widget preview in the list of seletable widgets when the user wants to add a widget to the launcher. This item should implement a state selector to show when the item is clicked.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (12dp , 12dp, 12dp, 12dp).

**DRAG_AND_DROP_SWITCH**
Drawable of the area shown during drag and drop allowing the user to switch between the home screen and the desktop screens. This item should implement a state selector with the *state_drag_hovered* state.
 - This item *should* implement a state selector to show when the item is clicked.
 - This item **must** add have a padding defined. The default is (12dp , 7dp, 12dp, 7dp).

**PAGE_INDICATOR_EMPTY**
Drawable used for the indicator of screen pages. Used for pages that are not visible.
 - This item **must** add have a defined width and height. The default is: width = 12dp, height = 12dp.

**PAGE_INDICATOR_FILLING**
Drawable used for the indicator of screen pages. Used for pages that are partly visible.
 - This item **must** add have a defined width and height. The default is: width = 12dp, height = 12dp.

**PAGE_INDICATOR_FULL**
Drawable used for the indicator of screen pages. Used for pages that are fully visible.
 - This item **must** add have a defined width and height. The default is: width = 12dp, height = 12dp.

**BACKGROUND**
The main background of all screens. Used in all other screens than the Home screen to dim the background to highlight its content. This item should be at least partly transparent to show the wallpaper to the user.

**TOOLBAR**
The background of the toolbars that are shown on some screens in the launcher.
 - This item **must NOT** add have a padding defined.

**APP_FOLDER_SUGGEST**
Drawable used while drag and drop to show that the dragged entry can be dropped on the item below it to move both into a folder.

## Colors

**TOOLBAR_TEXT_COLOR**
Color used for texts in the toolbar

**BADGE_ICON_DEFAULT_COLOR**
Default color used for notification badges. This color is used when the user decices to use a color for the notification badges instead of the provided drawable.

**BADGE_TEXT_COLOR**
Color for the notification count shown in the notification badges.

**APP_WIDGET_TEXT**
Color in the widget selection screen used for the widgets name and size.

**ACTIVITY_NAVIGATION_BAR_COLOR**
Color used for the navigation bar in the settings screen and the screens to select widgets or icons.

**ACTIVITY_STATUS_BAR_COLOR**
Color used for the status bar in the settings screen and the screens to select widgets or icons.

**PLACEHOLDER_TEXT_COLOR**
Color used for texts on screens that no not have content. E.g. on the search screen when there were no previous searcher or on the desktop when it is empty.

**DRAG_AND_DROP_HOVER**
Color used for icon and text on the view which can be used during drag and drop to change the screens. This color is used then whe user currently hovers over the view.

**DRAG_AND_DROP_STANDARD**
Color used for icon and text on the view which can be used during drag and drop to change the screens. This color is used then whe user does currently NOT hover over the view.

**ICON_SWITCH_SCREEN**
Icon used on the screen switch view.
