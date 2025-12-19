Translate text with ML Kit on Android

bookmark_border
Spark icon
Page Summary
You can use ML Kit to translate text between languages. ML Kit can translation between more than 50 languages.

This API supports on demand dynamic model downloads. See this guide for more information.
Try it out
Play around with the sample app to see an example usage of this API.
Before you begin
This API requires Android API level 23 or above. Make sure that your app's build file uses a minSdkVersion value of 23 or higher.
In your project-level build.gradle file, make sure to include Google's Maven repository in both your buildscript and allprojects sections.
Add the dependencies for the ML Kit Android libraries to your module's app-level gradle file, which is usually app/build.gradle:

dependencies {
  // ...

  implementation 'com.google.mlkit:translate:17.0.3'
}
Translate a string of text
To translate a string between two languages:

Create a Translator object, configuring it with the source and target languages:

Kotlin
Java

// Create an English-German translator:
val options = TranslatorOptions.Builder()
    .setSourceLanguage(TranslateLanguage.ENGLISH)
    .setTargetLanguage(TranslateLanguage.GERMAN)
    .build()
val englishGermanTranslator = Translation.getClient(options)
Code Tutor
expand_more
If you don't know the language of the input text, you can use the Language Identification API which gives you a language tag. Then convert the tag to a TranslateLanguage using TranslateLanguage.fromLanguageTag().

Avoid keeping too many language models on the device at once.

Make sure the required translation model has been downloaded to the device. Don't call translate() until you know the model is available.

Kotlin
Java

var conditions = DownloadConditions.Builder()
    .requireWifi()
    .build()
englishGermanTranslator.downloadModelIfNeeded(conditions)
    .addOnSuccessListener {
        // Model downloaded successfully. Okay to start translating.
        // (Set a flag, unhide the translation UI, etc.)
    }
    .addOnFailureListener { exception ->
        // Model couldn’t be downloaded or other internal error.
        // ...
    }
Code Tutor
expand_more
Language models are around 30MB, so don't download them unnecessarily, and only download them using Wi-Fi unless the user has specified otherwise. You should also delete unneeded models. See Explicitly manage translation models.

After you confirm the model has been downloaded, pass a string of text in the source language to translate():

Kotlin
Java

englishGermanTranslator.translate(text)
    .addOnSuccessListener { translatedText ->
        // Translation successful.
    }
    .addOnFailureListener { exception ->
         // Error.
         // ...
    }
Code Tutor
expand_more
The translated text, in the target language you configured, is passed to the success listener.

Ensure that the close() method is called when the Translator object will no longer be used.

If you are using a Translator in a Fragment or AppCompatActivity, one easy way to do that is call LifecycleOwner.getLifecycle() on the Fragment or AppCompatActivity, and then call Lifecycle.addObserver. For example:

Kotlin
Java

val options = ...
val translator = Translation.getClient(options)
getLifecycle().addObserver(translator)
Code Tutor
expand_more
Note that this assumes that the code is inside of a class that implements LifecycleOwner (e.g. a Fragment or AppCompatActivity).

Explicitly manage translation models

When you use the translation API as described above, ML Kit automatically downloads language-specific translation models to the device as required. You can also explicitly manage the translation models you want available on the device by using ML Kit's translation model management API. This can be useful if you want to download models ahead of time, or delete unneeded models from the device.

Kotlin
Java

val modelManager = RemoteModelManager.getInstance()

// Get translation models stored on the device.
modelManager.getDownloadedModels(TranslateRemoteModel::class.java)
    .addOnSuccessListener { models ->
        // ...
    }
    .addOnFailureListener {
        // Error.
    }

// Delete the German model if it's on the device.
val germanModel = TranslateRemoteModel.Builder(TranslateLanguage.GERMAN).build()
modelManager.deleteDownloadedModel(germanModel)
    .addOnSuccessListener {
        // Model deleted.
    }
    .addOnFailureListener {
        // Error.
    }

// Download the French model.
val frenchModel = TranslateRemoteModel.Builder(TranslateLanguage.FRENCH).build()
val conditions = DownloadConditions.Builder()
    .requireWifi()
    .build()
modelManager.download(frenchModel, conditions)
    .addOnSuccessListener {
        // Model downloaded.
    }
    .addOnFailureListener {
        // Error.
    }