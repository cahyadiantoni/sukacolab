package com.sukacolab.app.ui.feature.user.profile.sub_screen.edit_photo

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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sukacolab.app.R
import com.sukacolab.app.ui.component.PrimaryButton
import com.sukacolab.app.ui.component.StatelessTopBar
import com.sukacolab.app.ui.feature.user.profile.ProfileViewModel
import com.sukacolab.app.ui.feature.user.profile.ui_state.EditPhotoUiState
import com.sukacolab.app.ui.navigation.Screen
import com.sukacolab.app.util.createTempPictureUri
import com.sukacolab.app.util.form.formatters.dateShort
import com.sukacolab.app.util.reduceFileImage
import com.sukacolab.app.util.saveBitmapToFile
import com.sukacolab.app.util.saveUriToFile
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPhotoScreen(
    navController: NavController
){
    val viewModel: EditPhotoViewModel = getViewModel()
    val profileViewModel: ProfileViewModel = getViewModel()

    val context = LocalContext.current
    var visible by remember { mutableStateOf(false) }

    val editPhotoResult by viewModel.editPhotoResult.observeAsState()
    val isLoading = viewModel.isLoading.value

    var cameraPermissionGranted by remember {
        mutableStateOf(false)
    }

    val permissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(), onResult = { isGranted ->
        if (isGranted) {
            Log.d("DI Permision" , "Permission $isGranted")
            cameraPermissionGranted = true
        }
    } )

    SideEffect {
        if (!cameraPermissionGranted) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    var imageUri by remember { mutableStateOf<Uri?>(null)}
    var tempImageUri by remember { mutableStateOf<Uri?>(null)}
    var bitmap  by remember{ mutableStateOf<Bitmap?>(null)}
    var imgFile  by remember{ mutableStateOf<File?>(null)}
    val coroutineScope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()){ result ->
        if (result != null) {
            bitmap = result
            // Convert Bitmap to File
            imgFile = saveBitmapToFile(context, bitmap as Bitmap)
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                imageUri = tempImageUri

                imgFile = saveUriToFile(context, imageUri as Uri)
            }
        }
    )

    val launcherGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){ result ->
        if (result != null) {
            imageUri = result
            // Convert Uri to File
            imgFile = saveUriToFile(context, imageUri as Uri)
        }
    }

    LaunchedEffect(key1 = editPhotoResult) {
        when (editPhotoResult) {
            is EditPhotoResults.Success -> {
                val message = (editPhotoResult as EditPhotoResults.Success).message
                Log.d("Edit Photo", "Sukses: $message")
                Toast.makeText(context, "Success : $message", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Profile.route) {
                    popUpTo(Screen.Profile.route) {
                        inclusive = true
                    }
                }
            }
            is EditPhotoResults.Error -> {
                val errorMessage = (editPhotoResult as EditPhotoResults.Error).errorMessage
                Log.d("Edit Photo", "Gagal: $errorMessage")
                Toast.makeText(context, "Failed : $errorMessage", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.EditPhoto.route) {
                    popUpTo(Screen.EditPhoto.route) {
                        inclusive = true
                    }
                }
            }
            else -> {
                // Initial state or loading state
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            StatelessTopBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                },
                title = "Edit Photo",
                actionIcon = {
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
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
                                    .size(320.dp)
                                    .border(3.dp, Color.Gray, CircleShape)
                                    .padding(10.dp)
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
                                    .size(320.dp)
                                    .border(3.dp, Color.Gray, CircleShape)
                                    .padding(10.dp)
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
                                tempImageUri = context.createTempPictureUri()
                                cameraLauncher.launch(tempImageUri)
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
                    if (visible) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    val file = reduceFileImage(imgFile as File)
                                    viewModel.editPhoto(imgFile as File)
                                }
                            },
                            shape = RoundedCornerShape(10),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(57.dp)
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(color = Color.White)
                            } else {
                                Text(text = "Submit", style = MaterialTheme.typography.titleMedium.copy(color = Color.White, fontWeight = FontWeight.Bold))
                            }
                        }

                        Spacer(modifier = Modifier.padding(top = 48.dp))
                    }
                }
            }
        }
    )
}