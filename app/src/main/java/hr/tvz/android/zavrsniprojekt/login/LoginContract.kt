package hr.tvz.android.zavrsniprojekt.login

interface LoginContract {
    interface View {
        fun onSuccess(msg: String)
        fun onError(msg: String)
    }

    interface Presenter {
        fun doLogin(email: String, pass: String)
    }
}