package com.tp.comerciotp.data.model

import com.google.gson.annotations.SerializedName

data class QRResponse(

	@field:SerializedName("fecha")
	val fecha: String? = null,

	@field:SerializedName("qr")
	val qr: String? = null,

	@field:SerializedName("propina")
	val propina: Double? = null,

	@field:SerializedName("mensaje")
	val mensaje: String? = null
)
