package ir.safarzadehali.myapp.api

import ir.safarzadehali.myapp.model.add.AddFlashcardResponse
import ir.safarzadehali.myapp.model.all.FlashCardResponse
import ir.safarzadehali.myapp.model.mycard.MyFlashcardResponse
import ir.safarzadehali.myapp.model.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("AllCard.php")
    suspend fun getFlashCards(): FlashCardResponse

    @FormUrlEncoded
    @POST("MyCard.php")
    suspend fun getFlashcards(
        @Field("user_id") userId: Int
    ): MyFlashcardResponse


    //rej
    @FormUrlEncoded
    @POST("register.php")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<RegisterResponse>






    @FormUrlEncoded
    @POST("AddCard.php")
    suspend fun addFlashCard(
        @Field("user_id") userId: Int,
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("english[]") english: List<String>,
        @Field("persian[]") persian: List<String>
    ): AddFlashcardResponse
}