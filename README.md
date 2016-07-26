# Material Toolbar Spinner #

![mts_lollipop_example.gif](https://bitbucket.org/repo/RBaEEa/images/36736041-mts_lollipop_example.gif)

# Установка #
## Подключение репозитория ##
В `build.gradle` уровня проекта необходимо добавить репозиторий Magora:
```
#!groovy
allprojects {
    repositories {
        ...
        maven {
            credentials {
                username magoraRepoUsername
                password magoraRepoPassword
            }
            url "http://nexus.java.magora.team/content/repositories/releases/"
        }
    }
}		
```


В файл `~/.gradle/gradle.properties` добавить соответствующие *properties* (логин и пароль нереальные и прописаны лишь для примера):
```
#!properties
magoraRepoUsername=balabol
magoraRepoPassword=x88si!siQ05v
```

## Добавление зависимости ##
```
#!groovy
dependencies {
    compile 'com.magorasystems.android:material-toolbar-spinner:1.0.0'
}
```

# Использование #

В `Toolbar` необходимо добавить `MaterialToolbarSpinner` view:
```
#!xml
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

При этом на `Toolbar` необходимо проставить тему `ThemeOverlay.MTS.Toolbar`.  

Кроме этого в теме приложения необходимо проставить атрибут `toolbarSpinnerStyle`:
```
#!xml
<style name="Theme.SampleApp" parent="Theme.AppCompat.Light.NoActionBar">
    ...
    <item name="toolbarSpinnerStyle">@style/Widget.MTS.Spinner.Toolbar</item>
</style>
```

В коде же этот элемент используется как обычный Spinner:
```
#!java
MaterialToolbarSpinner spinner = (MaterialToolbarSpinner) toolbar.findViewById(R.id.mts_model_name);
ModelNameToolbarSpinnerAdapter spinnerAdapter = new ModelNameToolbarSpinnerAdapter(this);
spinner.setAdapter(spinnerAdapter);
spinner.setOnItemSelectedListener(this);
```

С той лишь оговоркой, что adapter должен наследоваться от класса `MaterialToolbarSpinner.Adapter` и методы для получения `View` для заголовочного элемента и выпадающего элемента имеют немного другое название:
```
#!java
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

При этом для простейшего случая можно использовать уже готовые layout'ы для item'ов:
```
#!java
R.layout.item_mts_dropdown
```

```
#!java
R.layout.item_mts_toolbar
```

В случае использования своей разметки можно использовать следующие стили для текста:
```
#!xml
TextAppearance.MTS.Spinner.Dropdown
```

```
#!xml
TextAppearance.MTS.Spinner.Header
```

## Настройка темы ##

Цвет треугольничка и текста в заголовке Spinner'а задается атрибутом `colorControlNormal` в теме `Toolbar`а.
```
#!xml
<style name="ThemeOverlay.SpinExam.Toolbar" parent="ThemeOverlay.MTS.Toolbar">
    <item name="colorControlNormal">#fffb00</item>
</style>
```

При этом цвет текста будет меняться этим атрибутом только при использовании следующего стиля для текста:
```
#!xml
TextAppearance.MTS.Spinner.Header
```

который по умолчанию используется в разметке:
```
#!java
R.layout.item_mts_toolbar
```
которую можно использовать для заголовочного `View` при создании adapter'а (описано выше).

При использовании своего стиля, цвет текста в нем удобнее проставить равным атрибуту `colorControlNormal`:
```
#!xml
<item name="android:textColor">?colorControlNormal</item>
```