package com.example.project_1.Presentation.Screen.StudentScreen


import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.Presentation.ViewModel.Project1ViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.json.JsonNull.content

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun StudentProfileScreen(
    viewModel: Project1ViewModel = hiltViewModel(),
    firebaseAuth: FirebaseAuth,
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        viewModel.getStudentbyId(firebaseAuth.currentUser!!.uid)

    }
    val userProfileImageState = viewModel.userProfileImageState.collectAsStateWithLifecycle()
    val studentProfileScreenState = viewModel.studentProfileScreenState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val isEdting = remember { mutableStateOf(true) }

    val imageUri = rememberSaveable { mutableStateOf<Uri?>(null) }
    val imageUrl = remember { mutableStateOf("") }


    val showDialog = remember { mutableStateOf(false) }


    val name =
        remember { mutableStateOf(studentProfileScreenState.value.studentdata?.studentData?.name ?: "") }
    val enrollmentno =
        remember { mutableStateOf(studentProfileScreenState.value.studentdata?.studentData?.enrollmentNumber ?: "") }
    val email =
        remember { mutableStateOf(studentProfileScreenState.value.studentdata?.studentData?.email ?: "") }
    val branch =
        remember { mutableStateOf(studentProfileScreenState.value.studentdata?.studentData?.branch ?: "") }


    val pickMedia =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            if (uri != null) {
                viewModel.upLoadUserProfileImage(uri)
                imageUri.value = uri
            }
        }
    val locationPermission = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.READ_MEDIA_IMAGES
        )
    )

    if (userProfileImageState.value.userData != null) {
        imageUrl.value = userProfileImageState.value.userData.toString()
    } else if (userProfileImageState.value.errorMessage != null) {
        Toast.makeText(context, userProfileImageState.value.errorMessage, Toast.LENGTH_SHORT).show()
    } else if (userProfileImageState.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    LaunchedEffect(key1 = locationPermission.permissions) {
        locationPermission.launchMultiplePermissionRequest()
    }


    LaunchedEffect(studentProfileScreenState.value.studentdata) {
        studentProfileScreenState.value.studentdata?.studentData?.let { studentData ->
            name.value = studentData.name ?: ""
            enrollmentno.value = studentData.enrollmentNumber ?: ""
            email.value = studentData.email ?: ""
            branch.value = studentData.branch ?: ""
            imageUrl.value = studentData.StudentImage ?: ""
        }
    }



    if (studentProfileScreenState.value.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else if (studentProfileScreenState.value.error != null) {
        Text(text = studentProfileScreenState.value.error!!)

    } else if (studentProfileScreenState.value.studentdata != null) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Your Profile", color = Color.Black,
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic
                            ), modifier = Modifier.padding(start = 40.dp)
                        )
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = Color(0xFFE3B1FD) // Custom color for the TopAppBar background
                    )

                )
            },
            content = { innerpadding ->

             Box(Modifier.fillMaxSize()){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerpadding)
                        .background(Color(0xFFFCFAEE)),
                ) {
// is staring we don,t have user iamge so we will show default image and  when user click on edit button then also user will se default image and if user select image then we will show that image then it will show user image
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    ) {
                        SubcomposeAsyncImage(
                            model = if (isEdting.value) imageUri.value else imageUrl.value,
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(140.dp)
                                .clip(CircleShape)
                                .border(2.dp, color = Color(0xFFFCF7D3), CircleShape)
                        ) {
                            when (painter.state) {
                                is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                                is AsyncImagePainter.State.Error -> Icon(
                                    Icons.Default.AccountCircle,
                                    contentDescription = null, tint = Color.Black
                                )

                                else -> SubcomposeAsyncImageContent()
                            }
                        }
                        if (isEdting.value) {
                            IconButton(
                                onClick = {
                                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                },
                                modifier = Modifier
                                    .size(60.dp)
                                    .align(Alignment.BottomEnd)
                                    .background(MaterialTheme.colorScheme.primary, CircleShape)
                            ) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = "Change Picture",
                                    tint = Color.Black
                                )
                            }
                        }
                    }





                    Spacer(modifier = Modifier.size(16.dp))


                    OutlinedTextField(
                        value = name.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 80.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFFFCF7D3),
                            focusedBorderColor = Color(0xFF0E0C01)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        readOnly = if (isEdting.value) false else true,

                        onValueChange = {
                            name.value = it

                        },
                        label = {
                            Text(
                                "Name", style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    )

                    Spacer(modifier = Modifier.size(16.dp))


                    OutlinedTextField(
                        value = enrollmentno.value, modifier = Modifier.fillMaxWidth(),

                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFFFCF7D3),
                            focusedBorderColor = Color(0xFFFCF7D3)
                        ), readOnly = if (isEdting.value) false else true,
                        onValueChange = {
                            enrollmentno.value = it
                        },
                        shape = RoundedCornerShape(10.dp),

                        label = {
                            Text(
                                "Enrollment number", style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                    )

                    Spacer(modifier = Modifier.size(16.dp))


                    OutlinedTextField(
                        value = email.value,
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = if (isEdting.value) false else true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFFFCF7D3),
                            focusedBorderColor = Color(0xFFFCF7D3)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onValueChange = {
                            email.value = it
                        },

                        label = {
                            Text(
                                "Email", style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        })

                    Spacer(modifier = Modifier.size(16.dp))
                    OutlinedTextField(
                        value = branch.value,
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = if (isEdting.value) false else true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFFFCF7D3),
                            focusedBorderColor = Color(0xFF887802)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onValueChange = {
                            branch.value = it
                        },
                        label = {
                            Text(
                                "Branch", style = TextStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                    )
                    Spacer(modifier = Modifier.size(16.dp))

                    OutlinedButton(
                        onClick = {
                            firebaseAuth.signOut()
                            navController.navigate(Routes.StudentLogin) {
                                popUpTo(Routes.StudentLogin) { inclusive = true }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 80.dp, end = 80.dp, top = 40.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF01061F))
                    ) {
                        Text("Log out", color = Color.White)

                    }


                }
            }}
        )
    }
}