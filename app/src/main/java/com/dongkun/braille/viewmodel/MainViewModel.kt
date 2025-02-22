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
        // 권동원 211101
        when {
            sendTxt[0].toString() == "것" -> {
                repository.toArduino(Integer.parseInt("000", 2), Integer.parseInt("111", 2), 1)
                repository.toArduino(Integer.parseInt("011", 2), Integer.parseInt("100", 2), 1)

            }
            else -> {

                var cho = ((sendTxt[0] - '\uAC00') / 28) / 21   // 초성
                var joong = (sendTxt[0] - '\uAC00') / 28 % 21   // 중성
                var jong = (sendTxt[0] - '\uAC00') % 28         // 종성
                var cho2 = Hangul(repository).CHO[cho]
                var joong2 = Hangul(repository).JOONG[joong]
                var jong2 = Hangul(repository).JONG[jong]
                when (cho2) {
                    "ㄱ" -> {
                        if (joong2 == "ㅏ") {
                            repository.toArduino(
                                Integer.parseInt("110", 2),
                                Integer.parseInt("101", 2),
                                1
                            )
                            Hangul(repository).checkJONG(jong)

                        } else {
                            repository.toArduino(0, 4, 1)
                            Hangul(repository).yageo(joong2, jong2, joong, jong)

                        }

                    }
                    "ㄲ" -> {
                        repository.toArduino(0, 1, 1)
                        if (joong2 == "ㅏ") {
                            repository.toArduino(
                                Integer.parseInt("110", 2),
                                Integer.parseInt("101", 2),
                                1
                            )
                            Hangul(repository).checkJONG(jong)

                        } else {
                            repository.toArduino(0, 4, 1)
                            Hangul(repository).yageo(joong2, jong2, joong, jong)

                        }

                    }
                    "ㄴ" -> {
                        if (joong2 == "ㅏ") {
                            repository.toArduino(
                                Integer.parseInt("100", 2),
                                Integer.parseInt("100", 2),
                                1
                            )
                            Hangul(repository).checkJONG(jong)

                        } else {
                            repository.toArduino(4, 4, 1)
                            Hangul(repository).yageo(joong2, jong2, joong, jong)

                        }

                    }
                    "ㄷ" -> {
                        if (joong2 == "ㅏ") {
                            repository.toArduino(
                                Integer.parseInt("010", 2),
                                Integer.parseInt("100", 2),
                                1
                            )
                            Hangul(repository).checkJONG(jong)

                        } else {
                            repository.toArduino(2, 4, 1)
                            Hangul(repository).yageo(joong2, jong2, joong, jong)

                        }

                    }
                    "ㄸ" -> {
                        repository.toArduino(0, 1, 1)
                        if (joong2 == "ㅏ") {
                            repository.toArduino(
                                Integer.parseInt("010", 2),
                                Integer.parseInt("100", 2),
                                1
                            )
                            Hangul(repository).checkJONG(jong)

                        } else {
                            repository.toArduino(2, 4, 1)
                            Hangul(repository).yageo(joong2, jong2, joong, jong)

                        }

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

            }
        }

//        val byteArr = sendTxt.toByteArray(Charset.defaultCharset())
//        repository.sendByteData(byteArr)
        Util.showNotification("send data!")
    }


}