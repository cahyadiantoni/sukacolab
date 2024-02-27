package com.sukacolab.app.ui.feature.user.profile.sub_screen.photo

import android.Manifest
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sukacolab.app.R
import com.sukacolab.app.ui.feature.user.profile.ProfileViewModel
import com.sukacolab.app.ui.feature.user.profile.ui_state.ProfileUiState
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.saveBitmapToFile
import com.sukacolab.app.util.saveUriToFile
import org.koin.androidx.compose.getViewModel
import java.io.File


@Composable
fun PhotoEditScreen(
    navController: NavController,
) {

    val viewModel: PhotoEditViewModel = getViewModel()
    val profileViewModel: ProfileViewModel = getViewModel()

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }
    var visible by remember {
        mutableStateOf(false)
    }

    var cameraPermissionGranted by remember {
        mutableStateOf(false)
    }

    val permissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(), onResult = { isGranted ->
        if (isGranted) {
            Log.d("TAG" , "Permission $isGranted")
            cameraPermissionGranted = true
        }
    } )

    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap  by remember{ mutableStateOf<Bitmap?>(null) }
    var imgFile  by remember{ mutableStateOf<File?>(null) }
    val coroutineScope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()){ result ->
        if (result != null) {
            bitmap = result
            // Convert Bitmap to File
            imgFile = saveBitmapToFile(context, bitmap as Bitmap)
        }
    }

    val launcherGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){ result ->
        if (result != null) {
            imageUri = result
            // Convert Uri to File
            imgFile = saveUriToFile(context, imageUri as Uri)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {


        if (bitmap != null || imageUri != null) {

            imageUri?.let {
                bitmap = if(Build.VERSION.SDK_INT < 28){
                    MediaStore.Images.Media.getBitmap(context.contentResolver,it)
                }else {
                    val source = ImageDecoder.createSource(context.contentResolver,it)
                    ImageDecoder.decodeBitmap(source)
                }
            }
            Image(bitmap = bitmap?.asImageBitmap()!!, contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(320.dp)
                    .border(3.dp, Color.Gray, CircleShape)
                    .padding(10.dp)
                    .clip(
                        CircleShape
                    )
            )
            visible = true
        } else {
            if(profileViewModel.photo == null){
                Image(
                    painter = painterResource(id = R.drawable.img_profile),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .border(2.dp, Color.LightGray, shape = CircleShape)
                        .padding(5.dp)
                        .clip(CircleShape)
                )
            }else{
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(profileViewModel.photo)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.img_profile),
                    modifier = Modifier
                        .border(2.dp, Color.LightGray, shape = CircleShape)
                        .padding(5.dp)
                        .clip(CircleShape)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = {
                if (!cameraPermissionGranted) {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                } else {
                    launcher.launch()
                }
            }) {
                Text(text = "Dari Kamera")
            }
            Button(onClick = {
                if (!cameraPermissionGranted) {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                } else {
                    launcherGallery.launch("image/*")
                }
            }) {
                Text(text = "Dari Galeri")
            }
        }

        Spacer(modifier = Modifier.padding(top = 54.dp))
        val response = viewModel.response.value

        if(response is ProfileUiState.Success) {
            Toast.makeText(context, "Success : Foto berhasil diubah", Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.Profile.route) {
                popUpTo(Screen.Profile.route) {
                    inclusive = true
                }
            }
        }
        if(response is ProfileUiState.Failure) {
            imgFile?.delete()
            Snackbar(
                modifier = Modifier.padding(8.dp),
                contentColor = Color.White,
                containerColor = Color.Red,
            ) {
                androidx.compose.material.Text(
                    text = "Terjadi Kesalahan ${response.error.message}",
                    style = TextStyle(
                        color = Color.White,
                    )
                )
            }
        }
    }
}