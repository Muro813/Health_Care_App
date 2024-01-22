package com.example.healthcareapp.data.remote

import android.content.Context
import com.example.healthcareapp.core.navigation.Navigator
import com.example.healthcareapp.data.remote.services.ApiService
import com.example.healthcareapp.domain.repository.DataStoreRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRefreshAuthenticator @Inject constructor(
    private val interceptor: AuthorizationInterceptor,
    private val dataStoreRepository: DataStoreRepository,
    private val navigator : Navigator,
    @ApplicationContext private val context: Context
) : Authenticator {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    internal interface TokenRefreshAuthenticatorEntryPoint {
        fun healthCareService(): ApiService
    }

    private var healthCareService: ApiService? = null
    private var isRefreshInProgress = false

    private fun initHealthCareService() {
        val appContext: Context = context.applicationContext
        val entryPoint = EntryPointAccessors.fromApplication(
            appContext, TokenRefreshAuthenticatorEntryPoint::class.java
        )
        healthCareService = entryPoint.healthCareService()
    }

    private val Response.retryCount: Int
        get() {
            var result = 1
            var currentResponse = priorResponse
            while (currentResponse != null) {
                result++
                currentResponse = currentResponse.priorResponse
            }
            return result
        }

    override fun authenticate(route: Route?, response: Response): Request?  {

//        val oldRefreshToken =  runBlocking{dataStoreRepository.getRefreshToken().first()}
//        return  if (interceptor.isTokenSet() && response.retryCount < 2 && response.request.url.toString() != healthCareService?.refreshToken(
//                RefreshDto(refreshToken = oldRefreshToken)
//            )
//                ?.request()?.url?.toString() && !isRefreshInProgress
//        ) response.createSignedRequest()
//        else {
//            interceptor.clearSession()
//            null
//        }
        return null
    }

    private fun Response.createSignedRequest(): Request? = try {
//        runBlocking {
//            isRefreshInProgress = true
//            if (healthCareService == null) initHealthCareService()
//            val oldRefreshToken = dataStoreRepository.getRefreshToken().first()
            /**If login route no need to sign it
             * */
            /**If login route no need to sign it
             * */
//            if (request.url.toString().contains("login")){
//                isRefreshInProgress = false
//                request
//            }
//            else{
                /**Try to refresh token, if it's expired go back to home screen, leave the request
                 * */
                /**Try to refresh token, if it's expired go back to home screen, leave the request
                 * */
//                val response = healthCareService?.refreshToken(RefreshDto(refreshToken = oldRefreshToken))?.execute()
//                if(response?.code() == 403){
//                    navigator.popUpTo(Screen.ProfileCreationScreen.route,ROOT,true)
//                    isRefreshInProgress = false
//                    request
//                }
//                else{
//                    /**Refresh was good, change tokens and sign
//                     * */
//                    /**Refresh was good, change tokens and sign
//                     * */
//                    val accessToken = response?.body()?.accessToken
//                    val refreshToken = response?.body()?.refreshToken
//                    if (accessToken != null && refreshToken != null) {
//                        interceptor.onTokensChanged(accessToken, refreshToken)
//                    }
//                    isRefreshInProgress = false
//                    request signWithToken accessToken
//                }
//            }
//        }
        null
    } catch (e: Throwable) {
        Timber.e(e, "Failed to resign request")
        null
    }
}