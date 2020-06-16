package fr.isen.bertrand.valentin.droidpizza

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.name)
        val firstname = findViewById<EditText>(R.id.firstname)
        val place = findViewById<EditText>(R.id.place)
        val phone = findViewById<EditText>(R.id.phone)
        val pref = applicationContext.getSharedPreferences("IDPref", Context.MODE_PRIVATE)
        val editor = pref.edit()

        //bouton pour valider la selection
        val boutonSuivant = findViewById(R.id.bouton_suivant) as Button
        boutonSuivant.setOnClickListener {
            val intent = Intent(this, Commande::class.java)
            // condition nom valide
            if (name.text == null || name.text.toString() == "Nom"){
                val toast = Toast.makeText(applicationContext, "Nom invalide", Toast.LENGTH_LONG)
                toast.show()
            }
            // condition prenom valide
            else if (firstname.text == null || firstname.text.toString() == "Prénom"){
                val toast = Toast.makeText(applicationContext, "Prénom invalide", Toast.LENGTH_LONG)
                toast.show()
            }
            // condition adresse valide
            else if (place.text == null || place.text.toString() == "Adresse"){
                val toast = Toast.makeText(applicationContext, "Adresse invalide", Toast.LENGTH_LONG)
                toast.show()
            }
            // condition telephone valide
            else if (phone.text == null || phone.text.toString() == "Telephone"){
                val toast = Toast.makeText(applicationContext, "Telephone invalide", Toast.LENGTH_LONG)
                toast.show()
            }
            // changement de page
            else {
                // création des variables à transmettre a ConfirmationCommande
                var envoi_nom: String = name.getText().toString()
                var envoi_prenom: String = firstname.getText().toString()
                var envoi_phone: String = phone.getText().toString()
                var envoi_place: String = place.getText().toString()

                // variables transmises via intent a ConfirmationCommande
                val intentconf = Intent(this, ConfirmationCommande::class.java)
                intentconf.putExtra("Name", envoi_nom)
                intentconf.putExtra("Firstname", envoi_prenom)
                intentconf.putExtra("Phone", envoi_phone)
                intentconf.putExtra("Place", envoi_place)

                // stockage des preferences pour nom et prenom
                editor.putString("name", name.getText().toString())
                editor.putString("firstname", firstname.getText().toString())
                editor.commit()
                startActivity(intent)
            }
        }
        // création des variables de nom et prenom enregistrés
        var nom_enr = pref.getString("name", null)
        var prenom_enr = pref.getString("firstname", null);

        // Condition de remplissage par les variables préenregistrées
        if (nom_enr != null){
            name.setText(nom_enr)
            firstname.setText(prenom_enr)
        }
        // TextEdit nom
        name.setOnClickListener {
            // condition pour effacer le texte lorsque l'on clique sur le textEdit
            if (name.text.toString() == "Nom"){
                name.text = null
            }
        }
        // TextEdit prenom
        firstname.setOnClickListener {
            if (firstname.text.toString() == "Prénom") {
                firstname.text = null
            }
        }
        // TextEdit adresse
        place.setOnClickListener {
            if (place.text.toString() == "Adresse"){
                place.text = null
            }
        }
        // TextEdit telephone
        phone.setOnClickListener {
            if (phone.text.toString() == "Numéro de Téléphone"){
                phone.text = null
            }
        }


        class SharedPreference(val context: Context) {
            private val PREFS_NAME = "kotlincodes"
            val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

            fun save(KEY_NAME: String, value: Int) {
                val editor: SharedPreferences.Editor = sharedPref.edit()
                editor.putInt(KEY_NAME, value)
                editor.commit()
            }
        }

    }
}




