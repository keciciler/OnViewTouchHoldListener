# OnViewTouchHoldListener
Android View.OnTouchListener implementation library to get onViewTouchHold callbacks asynchronously with desired polling rate

<img src="media/sample.gif" width="180" height="320"/>

## Usage

```
view.setOnTouchListener(OnViewTouchHoldListener(object : OnTouchHoldCallback {
            override fun onTouchHold(timeElapsed: Long) {

            }
}))
```

### With Kotlin Extensions
```
view.onTouchHold({ timeElapsed ->

})
```

### Configuration
```
view.setOnTouchListener(OnViewTouchHoldListener(
                object : OnTouchHoldCallback {
                    override fun onTouchHold(timeElapsed: Long) {

                    }
                },
                TouchHoldConfig(Starting Delay, Polling Delay, Include Padding)
))
```


## Setup
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.keciciler:OnViewTouchHoldListener:1.0'
}
```

License
--------


    Copyright 2019 Alaettin Altuğ Keçiciler

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.