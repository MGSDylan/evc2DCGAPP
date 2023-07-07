package com.dcg.evc2_activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dcg.evc2_activity.databinding.ActivityCamaraBinding

class CamaraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCamaraBinding
    private lateinit var openCamaraLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCamaraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("Camara")

        binding.btnTakePhoto.setOnClickListener {
            if (permissionValidated()){
                takePhoto()
            }
        }
        openCamaraLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if (result.resultCode== RESULT_OK){
                val photoBitMap=result.data?.extras?.get("data") as Bitmap
                binding.imgProductPhoto.setImageBitmap(photoBitMap)
            }

        }


    }
    private fun permissionValidated(): Boolean {
        val camaraPermission= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

        val permissionList: MutableList<String> = mutableListOf()
        if (camaraPermission!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.CAMERA)
        }
        if (permissionList.isNotEmpty()){
            ActivityCompat.requestPermissions(this,permissionList.toTypedArray(),1000)
            return false
        }

        return true
    }
    private fun takePhoto() {
        val cameraIntent=Intent()
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE)
        openCamaraLauncher.launch(cameraIntent)

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            1000->{
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    takePhoto()
                }
            }
        }
    }
}