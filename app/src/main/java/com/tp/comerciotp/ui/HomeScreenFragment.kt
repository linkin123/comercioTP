package com.tp.comerciotp.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.tp.comerciotp.R
import com.tp.comerciotp.core.Resource
import com.tp.comerciotp.data.model.QRRequest
import com.tp.comerciotp.data.remoteeeee.home.HomeDataSource
import com.tp.comerciotp.databinding.FragmentHomeScreenBinding
import com.tp.comerciotp.domain.home.HomeRepoImpl
import com.tp.comerciotp.presentation.home.HomeScreenViewModel
import com.tp.comerciotp.presentation.home.HomeScreenViewModelFactory
import com.tp.comerciotp.presentation.home.TipViewModel
import com.tp.comerciotp.repository.RetrofitClient


class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var sheet: BottomSheetBehavior<ConstraintLayout>
    private val tipViewModel = TipViewModel()
    private val viewModel by viewModels<HomeScreenViewModel> {
        HomeScreenViewModelFactory(HomeRepoImpl(HomeDataSource(RetrofitClient.webService)))
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)
        managerDataBinding()
        setUpBottomSheet()
        setUpToolbar()
        onclicks()
    }

    private fun managerDataBinding() {
        binding.tipViewModel = tipViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setUpBottomSheet() {
        sheet = BottomSheetBehavior.from(binding.bottomQr.constraint)
    }

    private fun onclicks() {
        binding.toolbarId.actionBar.ivBack.setOnClickListener {
            findNavController().navigate(R.id.CloseSessionDialogFragment)
        }

        binding.btnGenerarQr.setOnClickListener {
            if (binding.etMontoStatic.text.toString().isNotEmpty()) {
                sheet.state = BottomSheetBehavior.STATE_EXPANDED
                viewModel.getQR(getRequestQR()).observe(viewLifecycleOwner, { result ->
                    when (result) {
                        is Resource.Loading -> {
                            binding.bottomQr.layerProgress.visibility = View.VISIBLE
                            binding.bottomQr.btnAceptarQr.visibility = View.GONE
                            binding.btnGenerarQr.isEnabled = false
                        }
                        is Resource.Success -> {
                            binding.bottomQr.layerProgress.visibility = View.GONE
                            binding.bottomQr.btnAceptarQr.visibility = View.VISIBLE
                            binding.btnGenerarQr.isEnabled = true
                            result.data.qr?.let { qr ->
                                generateBitmap(qr)
                            }
                        }
                        is Resource.Failure -> {
                            binding.bottomQr.layerProgress.visibility = View.GONE
                            sheet.state = BottomSheetBehavior.STATE_EXPANDED
                            binding.btnGenerarQr.isEnabled = true
                        }
                    }
                })
            } else {
                Toast.makeText(requireContext(), "debe llenar el monto", Toast.LENGTH_SHORT).show()
            }

            binding.bottomQr.btnAceptarQr.setOnClickListener {
                sheet.state = BottomSheetBehavior.STATE_COLLAPSED
                binding.etMontoStatic.setText("")
                binding.bottomQr.ivQr.setImageBitmap(null)
            }
        }
    }




    private fun generateBitmap(str: String) {
        setUpBitmapToQR(BarcodeEncoder().encodeBitmap(str, BarcodeFormat.QR_CODE, 500, 500))

    }

    private fun setUpBitmapToQR(encodeBitmap: Bitmap?) {
        binding.bottomQr.ivQr.setImageBitmap(encodeBitmap)
    }


    private fun getRequestQR(): QRRequest {
        return QRRequest(
            binding.etMontoStatic.text.toString().trim().toDouble(),
            1,
            1,
            0.0,
            59,
            1,
            22,
            0.0
        )
    }

    private fun setUpToolbar() {
        binding.toolbarId.actionBar.tvTitle.text = "generar QR"
    }


}