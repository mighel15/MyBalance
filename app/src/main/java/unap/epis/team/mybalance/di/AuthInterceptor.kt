package unap.epis.team.mybalance.di

import okhttp3.Interceptor
import okhttp3.Response
import unap.epis.team.mybalance.data.local.SessionManager
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sessionManager: SessionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val token = sessionManager.getTokenBlocking()
        val newRequest = if (!token.isNullOrBlank()) {
            request.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else request

        return chain.proceed(newRequest)
    }
}