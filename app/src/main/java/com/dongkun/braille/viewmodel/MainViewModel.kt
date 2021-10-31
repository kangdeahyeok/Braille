package com.dongkun.braille.viewmodel

import android.os.Handler
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.dongkun.braille.Hangul
import com.dongkun.braille.Repository
import com.dongkun.braille.util.*
import java.nio.charset.Charset

class MainViewModel(private val repository: Repository) : ViewModel() {

    val connected: LiveData<Boolean?>
        get() = repository.connected
    val progressState: LiveData<String>
        get() = repository.progressState
    var btnConnected = ObservableBoolean(false)

    var inProgressView = ObservableBoolean(false)
    var txtProgress: ObservableField<String> = ObservableField("")

    private val _requestBleOn = MutableLiveData<Event<Boolean>>()
    val requestBleOn: LiveData<Event<Boolean>>
        get() = _requestBleOn

    val inProgress: LiveData<Event<Boolean>>
        get() = repository.inProgress

    val connectError: LiveData<Event<Boolean>>
        get() = repository.connectError

    val txtRead: ObservableField<String> = ObservableField("")
    val putTxt: LiveData<String>
        get() = repository.putTxt

    fun setInProgress(en: Boolean) {
        repository.inProgress.value = Event(en)
    }

    fun onClickConnect() {
        if (connected.value == false || connected.value == null) {
            if (repository.isBluetoothSupport()) {   // 블루투스 지원 체크
                if (repository.isBluetoothEnabled()) { // 블루투스 활성화 체크
                    //Progress Bar
                    setInProgress(true)
                    //디바이스 스캔 시작
                    repository.scanDevice()
                } else {
                    // 블루투스를 지원하지만 비활성 상태인 경우
                    // 블루투스를 활성 상태로 바꾸기 위해 사용자 동의 요청
                    _requestBleOn.value = Event(true)
                }
            } else { //블루투스 지원 불가
                Util.showNotification("Bluetooth is not supported.")
            }
        } else {
            repository.disconnect()
        }
    }

    fun unregisterReceiver() {
        repository.unregisterReceiver()
    }

    fun onClickSendData(sendTxt: String) {
        var cho = ((sendTxt[0] - '\uAC00') / 28) / 21
        var cho2 = Hangul().CHO[cho]
        when (cho2) {
            "ㄱ" -> {
                repository.toArduino(0, 4, 1)

            }
            "ㄲ" -> {
                repository.toArduino(0, 1, 1)
                Handler().postDelayed({
                    repository.toArduino(0, 4, 1)
                }, 1000L)

            }
            "ㄴ" -> {
                repository.toArduino(4, 4, 1)

            }
            "ㄷ" -> {
                repository.toArduino(2, 4, 1)

            }
            "ㄸ" -> {
                repository.toArduino(0, 1, 1)
                Handler().postDelayed({
                    repository.toArduino(2, 4, 1)
                }, 1000L)

            }
            "ㄹ" -> {
                repository.toArduino(0, 2, 1)

            }
            "ㅁ" -> {
                repository.toArduino(4, 2, 1)

            }
            "ㅂ" -> {
                repository.toArduino(0, 6, 1)

            }
            "ㅃ" -> {
                repository.toArduino(0, 1, 1)
                Handler().postDelayed({
                    repository.toArduino(0, 6, 1)
                }, 1000L)

            }
            "ㅅ" -> {
                repository.toArduino(0, 1, 1)

            }
            "ㅆ" -> {
                repository.toArduino(0, 1, 1)
                Handler().postDelayed({
                    repository.toArduino(0, 1, 1)
                }, 1000L)

            }
            "ㅇ" -> {
                repository.toArduino(0, 0, 1)

            }
            "ㅈ" -> {
                repository.toArduino(0, 5, 1)

            }
            "ㅉ" -> {
                repository.toArduino(0, 1, 1)
                Handler().postDelayed({
                    repository.toArduino(0, 5, 1)
                }, 1000L)

            }
            "ㅊ" -> {
                repository.toArduino(0, 3, 1)

            }
            "ㅋ" -> {
                repository.toArduino(6, 4, 1)

            }
            "ㅌ" -> {
                repository.toArduino(6, 2, 1)

            }
            "ㅍ" -> {
                repository.toArduino(4, 6, 1)

            }
            "ㅎ" -> {
                repository.toArduino(2, 6, 1)

            }
            else -> {
                //                    toArduino(-1, -1)
            }
        }
        var joong = (sendTxt[0] - '\uAC00') / 28 % 21
        var joong2 = Hangul().JOONG[joong]
        when (joong2) {
            "ㅏ" -> {
                repository.toArduino(6, 1, 2)

            }
            "ㅐ" -> {
                repository.toArduino(7, 2, 2)

            }
            "ㅑ" -> {
                repository.toArduino(1, 6, 2)

            }
            "ㅒ" -> {
                repository.toArduino(1, 6, 2)
                Handler().postDelayed({
                    repository.toArduino(7, 2, 2)
                }, 1000L)

            }
            "ㅓ" -> {
                repository.toArduino(3, 4, 2)

            }
            "ㅔ" -> {
                repository.toArduino(5, 6, 2)

            }
            "ㅕ" -> {
                repository.toArduino(4, 3, 2)

            }
            "ㅖ" -> {
                repository.toArduino(1, 4, 2)

            }
            "ㅗ" -> {
                repository.toArduino(5, 1, 2)

            }
            "ㅘ" -> {
                repository.toArduino(7, 1, 2)

            }
            "ㅙ" -> {
                repository.toArduino(7, 1, 2)
                Handler().postDelayed({
                    repository.toArduino(7, 2, 2)
                }, 1000L)

            }
            "ㅚ" -> {
                repository.toArduino(5, 7, 2)

            }
            "ㅛ" -> {
                repository.toArduino(1, 5, 2)

            }
            "ㅜ" -> {
                repository.toArduino(5, 4, 2)

            }
            "ㅝ" -> {
                repository.toArduino(7, 4, 2)

            }
            "ㅞ" -> {
                repository.toArduino(7, 4, 2)
                Handler().postDelayed({
                    repository.toArduino(7, 2, 2)
                }, 1000L)

            }
            "ㅟ" -> {
                repository.toArduino(5, 4, 2)
                Handler().postDelayed({
                    repository.toArduino(7, 2, 2)
                }, 1000L)

            }
            "ㅠ" -> {
                repository.toArduino(4, 5, 2)

            }
            "ㅡ" -> {
                repository.toArduino(2, 5, 2)

            }
            "ㅢ" -> {
                repository.toArduino(2, 7, 2)

            }
            "ㅣ" -> {
                repository.toArduino(5, 2, 2)

            }
            else -> {
                //                    toArduino(-1, -1)
            }
        }
        var jong = (sendTxt[0] - '\uAC00') % 28
        var jong2 = Hangul().JONG[jong]
        when (jong2) {
            "" -> {
                repository.toArduino(0, 0, 3)

            }
            "ㄱ" -> {
                repository.toArduino(4, 0, 3)

            }
            "ㄲ" -> {
                repository.toArduino(4, 0, 3)
                Handler().postDelayed({
                    repository.toArduino(4, 0, 3)
                }, 1000L)

            }
            "ㄳ" -> {
                repository.toArduino(4, 0, 3)
                Handler().postDelayed({
                    repository.toArduino(1, 0, 3)
                }, 1000L)

            }
            "ㄴ" -> {
                repository.toArduino(2, 2, 3)

            }
            "ㄵ" -> {
                repository.toArduino(2, 2, 3)
                Handler().postDelayed({
                    repository.toArduino(5, 0, 3)
                }, 1000L)

            }
            "ㄶ" -> {
                repository.toArduino(2, 2, 3)
                Handler().postDelayed({
                    repository.toArduino(1, 3, 3)
                }, 1000L)

            }
            "ㄷ" -> {
                repository.toArduino(1, 2, 3)

            }
            "ㄹ" -> {
                repository.toArduino(2, 0, 3)

            }
            "ㄺ" -> {
                repository.toArduino(2, 0, 3)
                Handler().postDelayed({
                    repository.toArduino(4, 0, 3)
                }, 1000L)

            }
            "ㄻ" -> {
                repository.toArduino(2, 0, 3)
                Handler().postDelayed({
                    repository.toArduino(2, 1, 3)
                }, 1000L)

            }
            "ㄼ" -> {
                repository.toArduino(2, 0, 3)
                Handler().postDelayed({
                    repository.toArduino(6, 0, 3)
                }, 1000L)

            }
            "ㄽ" -> {
                repository.toArduino(2, 0, 3)
                Handler().postDelayed({
                    repository.toArduino(1, 0, 3)
                }, 1000L)

            }
            "ㄾ" -> {
                repository.toArduino(2, 0, 3)
                Handler().postDelayed({
                    repository.toArduino(3, 1, 3)
                }, 1000L)

            }
            "ㄿ" -> {
                repository.toArduino(2, 0, 3)
                Handler().postDelayed({
                    repository.toArduino(2, 3, 3)
                }, 1000L)

            }
            "ㅀ" -> {
                repository.toArduino(2, 0, 3)
                Handler().postDelayed({
                    repository.toArduino(1, 3, 3)
                }, 1000L)

            }
            "ㅁ" -> {
                repository.toArduino(2, 1, 3)

            }
            "ㅂ" -> {
                repository.toArduino(6, 0, 3)

            }
            "ㅄ" -> {
                repository.toArduino(6, 0, 3)
                Handler().postDelayed({
                    repository.toArduino(1, 0, 3)
                }, 1000L)

            }
            "ㅅ" -> {
                repository.toArduino(1, 0, 3)

            }
            "ㅆ" -> {
                repository.toArduino(1, 0, 3)
                Handler().postDelayed({
                    repository.toArduino(1, 0, 3)
                }, 1000L)

            }
            "ㅇ" -> {
                repository.toArduino(3, 3, 3)

            }
            "ㅈ" -> {
                repository.toArduino(5, 0, 3)

            }
            "ㅊ" -> {
                repository.toArduino(3, 0, 3)

            }
            "ㅋ" -> {
                repository.toArduino(3, 2, 3)

            }
            "ㅌ" -> {
                repository.toArduino(3, 1, 3)

            }
            "ㅍ" -> {
                repository.toArduino(2, 3, 3)

            }
            "ㅎ" -> {
                repository.toArduino(1, 3, 3)

            }
            else -> {
                //                    toArduino(-1, -1)
            }
        }

//        val byteArr = sendTxt.toByteArray(Charset.defaultCharset())
//        repository.sendByteData(byteArr)
        Util.showNotification("send data!")
    }


}