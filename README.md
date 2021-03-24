# Nasa Pictures Android App

An android application which lets user browse through list of images parsed from a JSON file.

## Screens
* Images Grid Screen : This is the home screen. When it launches, show a scrollable grid of pictures starting with the latest images first. When the user taps on an image that should open the image detail screen.

* Image Detail Screen: This screen displays the full size image along with the metadata like title, description, etc. The user should also be able to swipe through images.

## Tests

* Unit Tests have been written using Junit4 and mockito for testing view model class.
* Instrumented Unit test have been written for testing model class.


## Libraries
* [Android Architecture Components](https://developer.android.com/arch)
* [Android Jetpack Libraries](https://developer.android.com/jetpack)
* [Fresco](https://frescolib.org) for Image Loading
* [Junit4](https://developer.android.com/training/testing/unit-testing/local-unit-tests) for Unit tests.
* [Mockito](http://site.mockito.org/) for mocking unit tests.