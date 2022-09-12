package hr.tvz.android.zavrsniprojekt.register

import android.os.Parcel
import android.os.Parcelable

class RegisterUser(var email: String, var password: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p: Parcel, p1: Int) {
        p.writeString(email)
        p.writeString(password)
    }

    companion object CREATOR : Parcelable.Creator<RegisterUser> {
        override fun createFromParcel(parcel: Parcel): RegisterUser {
            return RegisterUser(parcel)
        }

        override fun newArray(size: Int): Array<RegisterUser?> {
            return arrayOfNulls(size)
        }
    }
}