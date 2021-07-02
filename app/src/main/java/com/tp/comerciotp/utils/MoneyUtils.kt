package com.tp.comerciotp.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.util.regex.Pattern

object MoneyUtils {

    const val ENTERO = true
    const val FLOAT = false
    const val dos = 2f

    fun getMoneySigned(money: String?): String {
        if (money != null) {
            if (money.contains("-") || money.contains("+")) {
                return "${money.subSequence(0,1)}$${getMoney(money.subSequence(1, money.length).toString())}"
            } else {
                return "$${getMoney(money)}"
            }
        } else {
            return "0.00"
        }
    }

    fun getMoney(money: String?): String {
        return if (money == null || money.isEmpty()) {
            "0.00"
        } else {
            var balanceInt: String = getStrSinComas(money.getBalancePartX(ENTERO))
            var balanceFloat: String = money.getBalancePartX(FLOAT)

            if (balanceInt.length == 8) {
                balanceInt = balanceInt.substring(0, balanceInt.length - 1)
            }

            if (money.contains(".")) {
                /* manejo de la parte entera */
                if (balanceInt.isEmpty()) {
                    balanceInt = "0"
                } else {
                    val balanceInteger = balanceInt.toInt()
                    balanceInt = balanceInteger.toString()
                }

                /* manejo de la parte flotante */
                if (balanceFloat.isEmpty()) {
                    balanceFloat = "00"
                } else if (balanceFloat.equals("00")) {
                    balanceFloat = "00"
                } /*else {
                val balanceInteger = balanceFloat.toInt()
                balanceFloat = balanceInteger.toString()
            }*/

            } else {
                balanceInt = money
                if (balanceInt.substring(0, 1).equals("0") && balanceInt.length > 1) {
                    balanceInt = "0"
                }
                balanceFloat = "00"
            }

            var balanceIntt = balanceInt.reversed().getStrComasX().reversed().checkComaInicialX()

            if (balanceIntt.isEmpty()) {
                balanceIntt = "0"
            }

            if (balanceFloat.isEmpty()) {
                balanceFloat = "00"
            }

            "${balanceIntt}.$balanceFloat"

        }
    }

    fun Float.getMoneyTotal(): String {
        return toString().apply {
            "${getBalancePartX(ENTERO)}.${getBalancePartX(FLOAT)}"
        }
    }

    fun getMoneyTotalF(monto: Float): Float {
        return "%.2f".format((monto.getMoneyTotal().toFloat())).toFloat()
    }


    private fun String.checkComaInicialX(): String {
        return if (substring(0, 1) == ",") substring(1) else this
    }

    private fun String.getStrComasX(): String {
        var j = 0
        var strWithComas = ""
        for (element in this) {
            strWithComas += element
            j++
            if (j == 3) {
                j = 0
                strWithComas = "$strWithComas,"
            }
        }
        return strWithComas
    }

    fun String.getBalancePartX(integer: Boolean): String {
        if (this == "0") {
            return "0"
        } else {
            val separador = Pattern.quote(".")
            val parts = this.split(separador.toRegex()).toTypedArray()
            return if (parts.size == 2) {
                if (integer) {
                    parts[0]
                } else {
                    if (parts[1].length == 1)
                        parts[1] + "0"
                    else
                        parts[1].substring(0, 2)
                }
            } else {
                if (integer) {
                    parts[0]
                } else {
                    "00"
                }
            }
        }
    }

    fun getStrSinComas(currentBalance: String): String {
        val parts = currentBalance.split(",".toRegex()).toTypedArray()
        val current = StringBuilder()
        for (part in parts) {
            current.append(part)
        }
        return current.toString()
    }

    fun String.getStrSinComasX(): String {
        val parts = this.split(",".toRegex()).toTypedArray()
        val current = StringBuilder()
        for (part in parts) {
            current.append(part)
        }
        return current.toString()
    }

    fun String.geTwoDecimals(): String {
        val separador = Pattern.quote(".")
        val parts = split(separador.toRegex()).toTypedArray()
        return if (parts.size > 1) {
            if (parts[1].length == 1) {
                parts[0] + "." + parts[1] + "0"
            } else parts[0] + "." + parts[1].substring(0, 2)
        } else parts[0] + ".00"
    }

    fun getPropina(amount: String?, percent: Int): String {
        return if (amount == null || amount.isEmpty()) {
            "0.00"
        } else {
            val propinaSinComas: String =
                amount.trim().getStrSinComasX().toFloat().getPropina(percent).toString()
                    .geTwoDecimals()
            getMoney(propinaSinComas)
        }

    }

    fun Float.getPropina(propina: Int): Float {
        return this * (propina / 100.0f)
    }

    fun getTotal(amount: String, propina: Int, descuento: String, cashback : String): String {
        try {
            val amountStr = amount.trim().getStrSinComasX().toFloat().getPropina(propina)
            val propinaFloat = amountStr.toString().geTwoDecimals().toFloat()
            val subTotal = amount.toFloat() + propinaFloat
            val total = subTotal - descuento.toFloat() + cashback.toFloat()

            return if (total < 0) {
                "0.00"
            } else {
                getMoney(total.toString())
            }
        } catch (e: Exception) {
            return "0.00"
        }
    }

    fun getTotalCashbak(amount: String, propina: Int, descuento: String, cashback : String): String {
        try {
            val amountStr = amount.trim().getStrSinComasX().toFloat().getPropina(propina)
            val propinaFloat = amountStr.toString().geTwoDecimals().toFloat()
            val subTotal = amount.toFloat() + propinaFloat
            val total = subTotal + cashback.toFloat()

            return if (total < 0) {
                "0.00"
            } else {
                getMoney(total.toString())
            }
        } catch (e: Exception) {
            return "0.00"
        }
    }
}

@BindingAdapter("monto", "percent", "descuento", "cashback")
fun TextView.calculaTotal(monto: String?, percent: Int?, descuento: String?, cashback : String?) {
    text = if (!monto.isNullOrEmpty() && percent != null && descuento != null) {
        "$${MoneyUtils.getTotal(monto, percent, descuento, cashback.getAmount())}"
    } else {
        ""
    }
}

private fun String?.getAmount(): String {
    this?.let {
        return if(this.isEmpty()) "0" else this
    }
    return "0"
}

@BindingAdapter("visibility_view")
fun View.setView(b: Boolean) {
    visibility = if (b) View.VISIBLE else View.GONE
}
