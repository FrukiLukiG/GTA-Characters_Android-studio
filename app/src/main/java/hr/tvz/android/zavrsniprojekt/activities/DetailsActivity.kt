package hr.tvz.android.zavrsniprojekt.activities

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import hr.tvz.android.zavrsniprojekt.databinding.ActivityDetailsBinding
import hr.tvz.android.zavrsniprojekt.models.Character
import hr.tvz.android.zavrsniprojekt.widget.CharacterWidget

class DetailsActivity : AppCompatActivity(){

    private lateinit var binding : ActivityDetailsBinding
    private lateinit var mediaPlayer: MediaPlayer

    private lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        character = intent.getParcelableExtra("character")!!
        CharacterWidget().setLastCharacter(character, this)

        setupData()
        setupMediaPlayer()
        setupLinks()
    }

    private fun setupLinks() {
        binding.nameDetails.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(character.charLink)))
        }

        binding.gameDetails.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(character.gameLink)))
        }
    }

    private fun setupData() {
        binding.nameDetails.text = character.name
        binding.gameDetails.text = character.game
        binding.descDetails.text = character.desc

        binding.imgDetails.setImageResource(character.pic)
        binding.logoDetails.setImageResource(character.logo)

        binding.layoutDetails.background =
            AppCompatResources.getDrawable(this, character.background)
    }

    private fun setupMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, character.sound)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}