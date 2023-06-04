package fr.epf.mm.kotlin.CineLove

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.epf.mm.kotlin.CineLove.R
import fr.epf.mm.kotlin.CineLove.model.Film

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_film)

        val extras = intent.extras


        val filmExtra = extras?.get("film") as? Film

        val id = extras?.getInt("ID") ?: 0

        val titleTextview = findViewById<TextView>(R.id.details_film_title_textview)

        // lastNameTextview.text = client.lastName

        titleTextview.text = filmExtra?.title ?: "Non renseigné"

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_film_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_take_picture -> {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, 1211)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        when(requestCode){
            1211 -> {
                if(resultCode == RESULT_OK){
                    val extras = intent?.extras
                    val bitmap = extras?.get("data") as? Bitmap
                    val imageView = findViewById<ImageView>(R.id.details_client_imageview)
                    imageView.setImageBitmap(bitmap)
                }else{

                }
            }
        }
        super.onActivityResult(requestCode, resultCode, intent)
    }
}