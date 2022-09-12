package hr.tvz.android.zavrsniprojekt.register

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.zavrsniprojekt.api.RetrofitApi
import hr.tvz.android.zavrsniprojekt.api.SpringBootApi
import hr.tvz.android.zavrsniprojekt.databinding.ActivityRegisterBinding
import hr.tvz.android.zavrsniprojekt.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var presenter: RegisterContract.Presenter
    private var springBootApi: SpringBootApi = RetrofitApi.getApi()

    private lateinit var emailData: String
    private lateinit var passwordData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = RegisterPresenter(this)
        binding.editEmailReg.showSoftInputOnFocus = false
        binding.editPasswordReg.showSoftInputOnFocus = false
        binding.confirmPasswordReg.showSoftInputOnFocus = false

        binding.signupButtonReg.setOnClickListener{
            emailData = binding.editEmailReg.text.toString()
            passwordData = binding.editPasswordReg.text.toString()
            val passwordConfData = binding.confirmPasswordReg.text.toString()

            if (TextUtils.isEmpty(emailData) || TextUtils.isEmpty(passwordData) || TextUtils.isEmpty(passwordConfData)){
                onError("All fields are required")
            }
            else{
                presenter.doRegister(emailData, passwordData, passwordConfData)
            }
        }

        binding.backButtonReg.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onSuccess(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

        val loginUser = RegisterUser(emailData, passwordData)

        springBootApi.registerUser(loginUser).enqueue(object : Callback<RegisterUser?> {
            override fun onResponse(call: Call<RegisterUser?>, response: Response<RegisterUser?>) {
                Log.i("RegisterActivity", "onSuccess:" + response.code())
            }

            override fun onFailure(call: Call<RegisterUser?>, t: Throwable) {
                Log.d("RegisterActivity", "onFailure:" + t.message)
            }
        })
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}