# skystars-library

#####使用Library注意事項：
1. 在manifest加入 xmlns:tools="http://schemas.android.com/tools"
2. 在application加入tools:replace="android:icon,android:theme,android:label"
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.skystars.apppromote"
    xmlns:tools="http://schemas.android.com/tools">
    
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        tools:replace="android:icon,android:theme,android:label"
        android:theme="@style/AppTheme">
    </application>
</manifest>
```
