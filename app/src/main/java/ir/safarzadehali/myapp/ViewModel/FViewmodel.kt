package ir.safarzadehali.myapp.ViewModel

import androidx.lifecycle.ViewModel
import ir.safarzadehali.myapp.repository.FlashRepository

class FViewmodel(private val repo: FlashRepository) : ViewModel() {

    val flashCards = repo.getFromRoom()

    /*
    fun refresh() {
        viewModelScope.launch {
            val data = repo.getFromServer()
            repo.saveToRoom(data)
        }
    }

     */
}
