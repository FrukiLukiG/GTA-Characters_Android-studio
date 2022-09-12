package hr.tvz.android.zavrsniprojekt.register

class RegisterPresenter (private var view: RegisterContract.View) : RegisterContract.Presenter {

    override fun doRegister(email: String, pass: String, passConfirm: String) {

        val emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$".toRegex()

        if (email.contains(emailRegex) && pass == passConfirm) {
            view.onSuccess("Account registered successfully. Please login to confirm your data.")
        }
        else if (!email.contains(emailRegex)){
            view.onError("Incorrectly typed email, see regular expression for email...")
        }
        else if (pass != passConfirm) {
            view.onError("Passwords do not match, try again!")
        }
        else {
            view.onError("Unknown error")
        }

    }


}