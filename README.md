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

##如何使用：
1. app 推廣
```
json:
[
  {
	"package":"com.skystars.dicksonphrase"
    "title": "狄克森片語",
    "desc": "1. 中譯功能。2. 語音功能。3. 可匯入額外片語。5. 單字測驗。",
    "icon": "https://dl.dropbox.com/s/3eb14bimffpxu8u/ic_launcher.png?dl=0",
    "url": "http://goo.gl/QnNWj9"
  }
] 

java:
Intent intent = new Intent();
intent.setClass(MainActivity.this,PromoteActivity.class);
intent.putExtra(PromoteActivity.URL, YOURJSONURL);
startActivity(intent);
```
2. 公告
```
BulletinDialog.newInstance(MainActivity.this, YOURJSON, R.layout.dialog_bulletin)
.show(getSupportFragmentManager(),"B");
```