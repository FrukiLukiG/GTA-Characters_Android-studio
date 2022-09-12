package hr.tvz.android.zavrsniprojekt.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.zavrsniprojekt.activities.RecyclerViewActivity
import hr.tvz.android.zavrsniprojekt.databinding.ActivityLoginBinding
import hr.tvz.android.zavrsniprojekt.register.RegisterActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = LoginPresenter(this)
        binding.editEmailLogin.showSoftInputOnFocus = false
        binding.editPasswordLogin.showSoftInputOnFocus = false

        binding.loginButton.setOnClickListener {
            val emailData = binding.editEmailLogin.text.toString()
            val passwordData = binding.editPasswordLogin.text.toString()

            if (TextUtils.isEmpty(emailData) || TextUtils.isEmpty(passwordData)){
                onError("Email and password required")
            }
            else{
                presenter.doLogin(emailData, passwordData)
            }
        }

        binding.signupButtonLogin.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onSuccess(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

        val intent = Intent(this, RecyclerViewActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}