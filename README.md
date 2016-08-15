# Material Toolbar Spinner #
Small wrapper on Spinner, which let you easily add it to the Toolbar to look in material-style and equally on different Android versions

![mts_lollipop_example.gif](https://cloud.githubusercontent.com/assets/4465288/17552468/afe2e34c-5f32-11e6-9245-72d45ac4624f.gif)
![mts_pre_lollipop_example.gif](https://cloud.githubusercontent.com/assets/4465288/17552500/e5bada10-5f32-11e6-986c-f01266f307ec.gif)

# Setup #

Add the following dependency to the `build.gradle` of the module:
```groovy
dependencies {
    compile 'com.magorasystems.android:material-toolbar-spinner:1.0.0'
}		
```

# Usage #

It's needed to add `MaterialToolbarSpinner` view to the `Toolbar`:
```xml
<android.support.v7.widget.Toolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:theme="@style/ThemeOverlay.SampleApp.Toolbar">

    <com.magorasystems.materialtoolbarspinner.MaterialToolbarSpinner
        android:id="@+id/mts_model_name
        android:layout_width="wrap_content"
        android:layout_height="match_parent"/>
</android.support.v7.widget.Toolbar>
```

And it's needed to set `ThemeOverlay.MTS.Toolbar` theme to the `Toolbar`.

And beside that it's needed to set `toolbarSpinnerStyle` attribute in the Theme:
```xml
<style name="Theme.SampleApp" parent="Theme.AppCompat.Light.NoActionBar">
    ...
    <item name="toolbarSpinnerStyle">@style/Widget.MTS.Spinner.Toolbar</item>
</style>
```

In code this view is used almost as usual `Spinner`:
```java
MaterialToolbarSpinner spinner = (MaterialToolbarSpinner) toolbar.findViewById(R.id.mts_model_name);
ModelNameToolbarSpinnerAdapter spinnerAdapter = new ModelNameToolbarSpinnerAdapter(this);
spinner.setAdapter(spinnerAdapter);
spinner.setOnItemSelectedListener(this);
```

It's only needed to extend adapter from the `MaterialToolbarSpinner.Adapter`, that have little bit different names for methods that it's needed to implement to create views for header and for dropdown:
```java
public class SampleToolbarSpinnerAdapter extends MaterialToolbarSpinner.Adapter {
    @Override
    public View getDownView(int position, View convertView, ViewGroup parent) {
        ...
    }

    @Override
    public View getToolbarView(int position, View convertView, ViewGroup parent) {
        ...
    }
}
```

In simple case it's convenient to use ready layouts for items:
```java
R.layout.item_mts_dropdown
```

```java
R.layout.item_mts_toolbar
```

In case of using custom layouts it's possible to use the following text styles:
```xml
TextAppearance.MTS.Spinner.Dropdown
```

```xml
TextAppearance.MTS.Spinner.Header
```

## Theme Customization ##

The color of the triangle and the text followed by the triangle are defined by `colorControlNormal` attribute of `Toolbar`'s theme:
```xml
<style name="ThemeOverlay.SpinExam.Toolbar" parent="ThemeOverlay.MTS.Toolbar">
    <item name="colorControlNormal">#fffb00</item>
</style>
```

And it's important to notice that color of the text will change only if the following style is used:
```xml
TextAppearance.MTS.Spinner.Header
```

This style is used in this layout by default:
```java
R.layout.item_mts_toolbar
```
which you can use for the header view when creating adapter (read above).

If you use your own style for a text, it's convenient to set the color of a text to be equal to `colorControlNormal` attribute:
```xml
<item name="android:textColor">?colorControlNormal</item>
```
#License (MIT)#

    Copyright (c) 2016 Magora Systems
    
    Permission is hereby granted, free of charge, to any person obtaining a copy 
    of this software and associated documentation files (the "Software"), to deal 
    in the Software without restriction, including without limitation the rights 
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
    copies of the Software, and to permit persons to whom the Software is 
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all 
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
    SOFTWARE.
