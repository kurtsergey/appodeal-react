
# react-native-appodeal

## Getting started

`$ npm install react-native-appodeal --save`

### Mostly automatic installation

`$ react-native link react-native-appodeal`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-appodeal` and add `Appodeal.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libAppodeal.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.reactlibrary.AppodealPackage;` to the imports at the top of the file
  - Add `new AppodealPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-appodeal'
  	project(':react-native-appodeal').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-appodeal/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-appodeal')
  	```

To enable cheetah network append following lines to `android/settings.gradle`:
	```
		include ':cheetah'
		project(':cheetah').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-appodeal/android/cheetah-3.4.7')
	```
After that add the following line  inside dependencies block in `android/app/build.gradle`:
	```
      compile project(':cheetah')
  	```