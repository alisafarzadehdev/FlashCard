package ir.safarzadehali.myapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.safarzadehali.myapp.model.register.RegisterResponse
import ir.safarzadehali.myapp.retro.RetrofitInstance
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    val registerResult = MutableLiveData<RegisterResponse>()
    val errorMessage = MutableLiveData<String>()

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.register(username, email, password)

                if (response.isSuccessful) {
                    registerResult.value = response.body()
                } else {
                    errorMessage.value = "خطا در ارتباط با سرور"
                }

            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }
}