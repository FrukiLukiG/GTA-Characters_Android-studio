package hr.tvz.android.zavrsniprojekt.models

import android.os.Parcel
import android.os.Parcelable

class Character(var id: Long, var name: String, var game: String, var desc: String,
                var charLink: String, var gameLink: String,
                var logo: Int, var pic: Int, var background: Int, var sound: Int)
    : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(game)
        parcel.writeString(desc)
        parcel.writeString(charLink)
        parcel.writeString(gameLink)
        parcel.writeInt(logo)
        parcel.writeInt(pic)
        parcel.writeInt(background)
        parcel.writeInt(sound)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}