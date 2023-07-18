package br.com.alura.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.databinding.FormularioImagemBinding
import br.com.alura.orgs.extensions.tentarCarregarimage

class FormularioImagemDialog(private val context: Context ) {
  fun show(
    urlPadrao: String? = null,
    quandoImageCarrega: (imagem: String) -> Unit
  ){
   FormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {
      urlPadrao?.let{
        formularioImagemImageview.tentarCarregarimage(it)
        formularioImagemUrl.setText(it)
      }

     formularioImagemBotaoCarregar.setOnClickListener{
       val imageUrl = formularioImagemUrl.text.toString();
       formularioImagemImageview.tentarCarregarimage(imageUrl);
     }
     AlertDialog.Builder(context)
       .setView(root)
       .setPositiveButton("Confirmar"){_, _->
         var imagemUrl = formularioImagemUrl.text.toString();
         quandoImageCarrega(imagemUrl)
       }
       .setNegativeButton("Cancelar"){_, _->}
       .show()
    }
  }
}