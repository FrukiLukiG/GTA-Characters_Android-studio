package hr.tvz.android.zavrsniprojekt.login

import android.os.Parcel
import android.os.Parcelable

class LoginUser (var id: Long, var email: String, var password: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(p: Parcel, p1: Int) {
        p.writeLong(id)
        p.writeString(email)
        p.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginUser> {
        override fun createFromParcel(parcel: Parcel): LoginUser {
            return LoginUser(parcel)
        }

        override fun newArray(size: Int): Array<LoginUser?> {
            return arrayOfNulls(size)
        }
    }

}