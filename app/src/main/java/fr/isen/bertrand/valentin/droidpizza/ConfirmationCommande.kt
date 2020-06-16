package fr.isen.bertrand.valentin.droidpizza

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConfirmationCommande : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_commande)

        // bouton retour vers la page precedente
        val button_retour = findViewById(R.id.imageButton) as ImageButton

        button_retour.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // bouton d'envoi d'email de confirmation
        val button_email = findViewById(R.id.buttonemailconf) as Button

        button_email.setOnClickListener {
            val recipient = "marc.mollinari@gmail.com".trim()
            val subject = "Confirmation commande".trim()
            val message = "Votre commande a bien été enregistrée".trim()

            sendEmail(recipient, subject, message)

        }
        // récupération des variables enregisttrées
        val intent = getIntent()
        val  envoi_nom = intent.getStringExtra("Name")
        val envoi_firstname = intent.getStringExtra("Firstname")
        val envoi_phone = intent.getStringExtra("Phone")
        val envoi_place = intent.getStringExtra("Place")
        val envoi_pizza = intent.getStringExtra("Pizza")
        val envoi_heure = intent.getStringExtra("Heure")

        // affichage des variables enregistrées
        val resultTv = findViewById<TextView>(R.id.resumecomm)
        //setText
        resultTv.text = "Nom : "+ envoi_nom+"\n\nPrénom : "+envoi_firstname+"\n\nAdresse : "+envoi_phone+"\n\nTéléphone : "+envoi_place+"\n\nPizza séléctionnée : "+envoi_pizza+"\n\nHeure de Livraison : "+envoi_heure
    }
    // fonction d'envoi du mail
    private fun sendEmail(recipient: String, subject: String, message: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            //start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }


    }
}
