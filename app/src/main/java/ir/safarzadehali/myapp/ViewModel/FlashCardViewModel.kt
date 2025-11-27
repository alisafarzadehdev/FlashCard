package ir.safarzadehali.myapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.safarzadehali.myapp.model.FlashCard
import ir.safarzadehali.myapp.model.add.AddFlashcardResponse
import ir.safarzadehali.myapp.model.mycard.MyFlashcardData
import ir.safarzadehali.myapp.repository.FlashCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// FlashCardViewModel.kt
class FlashCardViewModel(
private val repo: FlashCardRepository = FlashCardRepository()
)
    : ViewModel() {

    private val repository = FlashCardRepository()
    val flashCards = MutableLiveData<List<FlashCard>>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String?>()
    private val _flashCards = MutableLiveData<List<MyFlashcardData>>()
    val myflashCards: LiveData<List<MyFlashcardData>> get() = _flashCards

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    val _flashcards = MutableLiveData<List<ir.safarzadehali.myapp.model.all.FlashCard>>()
    val flashcards: LiveData<List<ir.safarzadehali.myapp.model.all.FlashCard>> get() = _flashcards


    fun loadFlashCards() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loading.postValue(true)
                val response = repository.fetchFlashCards()
                if (response.status) {
                    Log.d("TAGqqq", "loadFlashCards: "+response.status)
                    _flashcards.postValue(response.data)
                } else {
                    Log.d("TAGqqq", "else: "+response.status)
                    _error.postValue("Server returned status=false")
                }
            } catch (e: Exception) {
                Log.d("TAGqqq", "catch: "+e.message)
                _error.postValue(e.message)
            } finally {
                _loading.postValue(false)
            }
        }
    }

    fun loadFlashCards(userId: Int) {
        viewModelScope.launch {
            try {
                _loading.value = true

                val response = repo.getFlashCards(userId)

                if (response.status) {
                    Log.d("TAGstatus", "loadFlashCardsIF: "+response.status)
                    _flashCards.value = response.data
                } else {
                    Log.d("TAGstatus", "loadFlashCardsElse: "+response.status)
                    _error.value = "خطا در دریافت اطلاعات"
                }

            } catch (e: Exception) {
                Log.d("TAGException", "loadFlashCards: "+e.message)
                _error.value = "Error: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }


    val responseLiveData = MutableLiveData<AddFlashcardResponse>()
    val errorLiveData = MutableLiveData<String>()

    fun addFlashCard(
        userId: Int,
        title: String,
        description: String,
        english: List<String>,
        persian: List<String>
    ) {
        viewModelScope.launch {
            try {
                val res = repo.addFlashCard(userId, title, description, english, persian)
                responseLiveData.postValue(res)
                Log.d("ESSTAG", "addFlashCard: "+res.status)
            } catch (e: Exception) {
                Log.d("ESSTAG", "addFlashCard: "+e.message)
                errorLiveData.postValue("Error: ${e.message}")
            }
        }
    }
}
