package br.com.alura.orgs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Entity
@Parcelize
data class Produto (
        @PrimaryKey(autoGenerate = true) val id: Long = 0L,
        val nome: String,
        val descricao: String,
        val imagem:String? = null,
        val valor: BigDecimal
) : Parcelable
