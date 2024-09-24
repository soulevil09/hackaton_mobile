package br.edu.fatecpg.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import br.edu.fatecpg.ui.R
import br.edu.fatecpg.ui.dao.ContatoDaoImpl
import br.edu.fatecpg.ui.model.Contatos
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    val dao = ContatoDaoImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val edtNome = findViewById<TextInputEditText>(R.id.edt_nome)
         val edtZap = findViewById<TextInputEditText>(R.id.edt_zap)
         val edtLink = findViewById<TextInputEditText>(R.id.edt_link)
         val btnSalvar = findViewById<AppCompatButton>(R.id.btn_salvar)
         val txtContatos = findViewById<TextView>(R.id.txt_contatos)

        btnSalvar.setOnClickListener{
            val nome = edtNome.text.toString()
            val zap = edtZap.text.toString()
            val link = edtLink.text.toString()
            val contato = Contatos(nome, zap, link)
            dao.AdicionarContato(contato)
            Toast.makeText(this, "Contato adicionado", Toast.LENGTH_SHORT).show()
            /*edtNome.text.clear()
            edtZap.text.clear()
            edtLink.text.clear()*/
        }

        txtContatos.setOnClickListener{
            val contato = dao.obterContato().lastOrNull() // Pega o último contato adicionado, por exemplo
            if (contato != null) {
                val intent = Intent(this, ContatosActivity::class.java).apply {
                    putExtra("nome", contato.nome)
                    putExtra("zap", contato.zap)
                    putExtra("link", contato.link)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Nenhum contato encontrado", Toast.LENGTH_SHORT).show()
            }
        }

    }
}