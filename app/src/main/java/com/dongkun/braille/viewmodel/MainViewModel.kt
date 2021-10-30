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
        when {
            cho2 == "ㄱ" -> {
                repository.toArduino(0, 4, 1)

            }
//            cho2 == "ㄲ" -> {
//                toArduino(0, 1, 1)
//                Handler().postDelayed({
//                    toArduino(0, 4, 1)
//                }, 1000L)
//
//            }
//            cho2 == "ㄴ" -> {
//                toArduino(4, 4, 1)
//
//            }
//            cho2 == "ㄷ" -> {
//                toArduino(2, 4, 1)
//
//            }
//            cho2 == "ㄸ" -> {
//                toArduino(0, 1, 1)
//                Handler().postDelayed({
//                    toArduino(2, 4, 1)
//                }, 1000L)
//
//            }
//            cho2 == "ㄹ" -> {
//                toArduino(0, 2, 1)
//
//            }
//            cho2 == "ㅁ" -> {
//                toArduino(4, 2, 1)
//
//            }
//            cho2 == "ㅂ" -> {
//                toArduino(0, 6, 1)
//
//            }
//            cho2 == "ㅃ" -> {
//                toArduino(0, 1, 1)
//                Handler().postDelayed({
//                    toArduino(0, 6, 1)
//                }, 1000L)
//
//            }
//            cho2 == "ㅅ" -> {
//                toArduino(0, 1, 1)
//
//            }
//            cho2 == "ㅆ" -> {
//                toArduino(0, 1, 1)
//                Handler().postDelayed({
//                    toArduino(0, 1, 1)
//                }, 1000L)
//
//            }
//            cho2 == "ㅇ" -> {
//                toArduino(0, 0, 1)
//
//            }
//            cho2 == "ㅈ" -> {
//                toArduino(0, 5, 1)
//
//            }
//            cho2 == "ㅉ" -> {
//                toArduino(0, 1, 1)
//                Handler().postDelayed({
//                    toArduino(0, 5, 1)
//                }, 1000L)
//
//            }
//            cho2 == "ㅊ" -> {
//                toArduino(0, 3, 1)
//
//            }
//            cho2 == "ㅋ" -> {
//                toArduino(6, 4, 1)
//
//            }
//            cho2 == "ㅌ" -> {
//                toArduino(6, 2, 1)
//
//            }
//            cho2 == "ㅍ" -> {
//                toArduino(4, 6, 1)
//
//            }
//            cho2 == "ㅎ" -> {
//                toArduino(2, 6, 1)
//
//            }
            else -> {
//                    toArduino(-1, -1)
            }
        }

//        val byteArr = sendTxt.toByteArray(Charset.defaultCharset())
//        repository.sendByteData(byteArr)
        Util.showNotification("send data!")
    }


}