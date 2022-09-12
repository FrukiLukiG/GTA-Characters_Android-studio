package hr.tvz.android.zavrsniprojekt.login

import android.util.Log
import hr.tvz.android.zavrsniprojekt.api.RetrofitApi
import hr.tvz.android.zavrsniprojekt.api.SpringBootApi
import hr.tvz.android.zavrsniprojekt.login.LoginContract.Presenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private var view: LoginContract.View) : Presenter {

    private var springBootApi: SpringBootApi = RetrofitApi.getApi()
    private var loginUsers: List<LoginUser> = emptyList()

    init {
        springBootApi.getAllUsers().enqueue(object : Callback<List<LoginUser>?> {
            override fun onResponse(
                call: Call<List<LoginUser>?>,
                response: Response<List<LoginUser>?>
            ) {
                loginUsers = response.body()!!
            }

            override fun onFailure(call: Call<List<LoginUser>?>, t: Throwable) {
                Log.d("LoginPresenter", "onFailure:" + t.message)
            }
        })
    }

    override fun doLogin(email: String, pass: String) {
        var loginFail = true
        for (l in loginUsers){
            if (email == l.email && pass == l.password) {
                view.onSuccess("Successful login")
                loginFail = false
            }
        }
        if (loginFail){
            view.onError("Wrong email or password, please try again")
        }
    }
}