package br.com.alura.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {


  @TypeConverter
  fun doDouble(valor: Double?): BigDecimal{
    return valor?.let{
       BigDecimal(valor.toString())
    }?:BigDecimal.ZERO
  }

  @TypeConverter
  fun BigDecimalParaDouble(valor: BigDecimal?): Double?{
    return valor?.let {
      valor.toDouble()
    }
  }
}