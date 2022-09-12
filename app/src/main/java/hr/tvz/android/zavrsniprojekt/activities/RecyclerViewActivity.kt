package hr.tvz.android.zavrsniprojekt.activities

import android.app.AlertDialog
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hr.tvz.android.zavrsniprojekt.R
import hr.tvz.android.zavrsniprojekt.adapters.RecyclerViewAdapter
import hr.tvz.android.zavrsniprojekt.adapters.RecyclerViewInterface
import hr.tvz.android.zavrsniprojekt.api.RetrofitApi
import hr.tvz.android.zavrsniprojekt.api.SpringBootApi
import hr.tvz.android.zavrsniprojekt.broadcasts.WiFiChangeReceiver
import hr.tvz.android.zavrsniprojekt.databinding.ActivityMainBinding
import hr.tvz.android.zavrsniprojekt.login.LoginActivity
import hr.tvz.android.zavrsniprojekt.models.Character
import hr.tvz.android.zavrsniprojekt.models.DataLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewActivity : AppCompatActivity(), RecyclerViewInterface {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer

    private var springBootApi: SpringBootApi = RetrofitApi.getApi()
    private var characters : List<Character> = emptyList()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMediaPlayer()
        setupWiFiReceiver()
        setupRecyclerView()
        getSidemenData()

        binding.logoutBtn?.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout")
                .setPositiveButton("Yes") { _, _ ->
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                .setNegativeButton("No") { dialogInterface, _ -> dialogInterface.cancel() }
                .create()
                .show()
        }
    }

    private fun setupRecyclerView() {
        binding.mRecyclerView.setHasFixedSize(true)
        binding.mRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter(ArrayList(), this)
        binding.mRecyclerView.adapter = adapter
    }

    private fun getSidemenData() {
        springBootApi.getAllCharacters().enqueue(object : Callback<List<Character>> {
            override fun onResponse(
                call: Call<List<Character>>,
                response: Response<List<Character>>
            ) {
                characters = response.body()!!

                for (c in characters){
                    c.logo = DataLoader.logoImages[c.logo]
                    c.pic = DataLoader.characterImages[c.pic]
                    c.background = DataLoader.backgroundImages[c.background]
                    c.sound = DataLoader.backgroundSound[c.sound]

                    adapter.insertData(c)
                    adapter.notifyItemInserted(c.id.toInt())
                }
            }
            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                Log.d("MainActivity", "onFailure:" + t.message)
            }
        })
    }

    override fun onItemClick(position: Number) {
        startActivity(
            Intent(this, DetailsActivity::class.java)
                .putExtra("character", characters[position.toInt()])
        )
    }

    private fun setupWiFiReceiver() {
        IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION).also {
            registerReceiver(WiFiChangeReceiver(), it)
        }
    }

    private fun setupMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.gtaii)
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