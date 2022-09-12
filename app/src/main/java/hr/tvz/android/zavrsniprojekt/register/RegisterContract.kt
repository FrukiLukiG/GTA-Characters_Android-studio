package hr.tvz.android.zavrsniprojekt.register

interface RegisterContract {
    interface View {
        fun onSuccess(msg: String)
        fun onError(msg: String)
    }

    interface Presenter {
        fun doRegister(email: String, pass: String, passConfirm: String)
    }
}