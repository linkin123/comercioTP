package com.tp.comerciotp.data.model

import com.google.gson.annotations.SerializedName

data class QRRequest(

	@field:SerializedName("monto")
	val monto: Double? = null,

	@field:SerializedName("idSubsidiaria")
	val idSubsidiaria: Int? = null,

	@field:SerializedName("idCajero")
	val idCajero: Int? = null,

	@field:SerializedName("propina")
	val propina: Double? = null,

	@field:SerializedName("idAgente")
	val idAgente: Int? = null,

	@field:SerializedName("idCaja")
	val idCaja: Int? = null,

	@field:SerializedName("idTienda")
	val idTienda: Int? = null,

	@field:SerializedName("montoRetiro")
	val montoRetiro: Double? = null
)
