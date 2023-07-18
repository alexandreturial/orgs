package br.com.alura.orgs.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityProdutoDetalheBinding
import br.com.alura.orgs.extensions.formataParaMoedaBrasileira
import br.com.alura.orgs.extensions.tentarCarregarimage
import br.com.alura.orgs.model.Produto

class ProdutoDetalheActivity : AppCompatActivity() {
  private var produtoId: Long = 0L
  private var produto: Produto? = null
  private val binding by lazy {
    ActivityProdutoDetalheBinding.inflate(layoutInflater)
  }
  val produtoDao by lazy {
    AppDatabase.instancia(this).produtoDao()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    tentaCarregarProduto()
  }

  override fun onResume() {
    super.onResume()
    buscaProduto()
  }

  private fun buscaProduto() {
    produto = produtoDao.buscaPorId(produtoId)
    produto?.let {
      preencheCampos(it)
    } ?: finish()
  }

  private fun tentaCarregarProduto() {
    produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId){
      R.id.menu_detalhe_produto_remmover ->{
        produto?.let {
          produtoDao.remove(it)
        }
        finish()
      }
      R.id.menu_detalhe_produto_editar ->{
        Intent(this, FormularioProdutoActivity::class.java).apply{
          putExtra(CHAVE_PRODUTO_ID, produtoId)
          startActivity(this)
        }
      }
    }

    return super.onOptionsItemSelected(item)
  }

  private fun preencheCampos(produtoCarregado: Produto) {
    with(binding){

      activityProdutoImagem.tentarCarregarimage(produtoCarregado.imagem)
      activityProdutoPreco.text = produtoCarregado.valor.formataParaMoedaBrasileira()
      activityProdutoDescicao.text = produtoCarregado.descricao
      activityProdutoTitulo.text = produtoCarregado.nome
      Log.i("PRODUTO", "onCreate: " + activityProdutoTitulo.text)
    }
  }
}