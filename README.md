# Android
android

广播：
https://developer.android.com/guide/components/broadcasts.html#manifest-declared_receivers

隐式intent，当sdk >= 26,无法再manifest中为隐式的广播定义接收器【本地开发工具生成的目标code是26的，所以receiver中的Toast一直无法弹出】
