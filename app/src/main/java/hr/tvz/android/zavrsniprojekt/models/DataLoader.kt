package hr.tvz.android.zavrsniprojekt.models

import hr.tvz.android.zavrsniprojekt.R

class DataLoader {
    companion object {
        val logoImages = arrayOf(
            R.drawable.logogtaiii, R.drawable.logogtavc, R.drawable.logogtasa,
            R.drawable.logogtalcs, R.drawable.logogtavcs,
            R.drawable.logogtaiv, R.drawable.logogtaiv, R.drawable.logogtaiv,
            R.drawable.logogtav, R.drawable.logogtav, R.drawable.logogtav
        )

        val characterImages = arrayOf(
            R.drawable.claude, R.drawable.tommy, R.drawable.cj, R.drawable.toni, R.drawable.vic,
            R.drawable.niko, R.drawable.johnny, R.drawable.luis,
            R.drawable.franklin, R.drawable.michael, R.drawable.trevor
        )

        val backgroundImages = arrayOf(
            R.drawable.gtaiii, R.drawable.gtavc, R.drawable.gtasa, R.drawable.gtalcs, R.drawable.gtavcs,
            R.drawable.gtaiv, R.drawable.gtatlad, R.drawable.gtatbogt,
            R.drawable.gtavtwo, R.drawable.gtavone, R.drawable.gtavthree
        )

        val backgroundSound = arrayOf(
            R.raw.iii, R.raw.vc, R.raw.sa, R.raw.lcs, R.raw.vcs,
            R.raw.iv, R.raw.tlad, R.raw.tbogt,
            R.raw.vfrank, R.raw.vmichael, R.raw.vtrevor
        )
    }
}