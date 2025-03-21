package com.example.project_1.Presentation.Screen.StudentScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.Presentation.Screen.Location
import com.example.project_1.R
import com.google.firebase.auth.FirebaseAuth

import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentHomeScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(0) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val selectedItem = remember { mutableStateOf("") }
    var showPopup by remember { mutableStateOf(false) }

    val drawerItem = listOf(
        Screen(
            title = "News",
            image = R.drawable.newspaperfolded
        ) { navController.navigate(Routes.StudentNewsScreen) },
        Screen(
            "PasswordManager",
            R.drawable.customer
        ) { navController.navigate(Routes.PasswordManagerScreen) },
        Screen("Help", R.drawable.help) { navController.navigate(Routes.Help) },
        Screen("Profile", R.drawable.man) { navController.navigate(Routes.StudentProfileScreen) }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    IconButton(onClick = {
                        // Open the navigation drawer
                        scope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(bottom = 5.dp),
                            tint = Color.Black
                        )
                    }
                    Text(
                        text = "Student Dashboard", color = Color.Black,
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic
                        ), modifier = Modifier.padding(start = 50.dp, top = 8.dp)
                    )
                },

                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFF2C9FA) // Custom color for the TopAppBar background
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        content = { innerPadding ->
            // Handle content based on selectedTab
            when (selectedTab) {
                0 -> StudentAttendanceScreen(
                    firebaseAuth = FirebaseAuth.getInstance(),
                    navController = navController
                )

                1 -> StudentMarksScreen(
                    firebaseAuth = FirebaseAuth.getInstance(),
                    navController = navController
                )

                2 -> navController.navigate(Routes.CollegeMap)
                3 -> StudentServicesScreen()
            }

            // Drawer Content
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet(
                        modifier = Modifier
                            .width(300.dp)
                            .padding(top = 80.dp),
                        drawerContainerColor = Color.White
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        listOf(
                                            Color(0xFFEEBB70), Color(
                                                0xFFF89606
                                            ),
                                            Color(0xFFE8CEAB)
                                        )
                                    )
                                )
                        ) {
                            Text(
                                "Menu",
                                style = TextStyle(
                                    fontSize = 35.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.Black
                                )


                            )
                        }
                        drawerItem.forEach { item ->
                            NavigationDrawerItem(
                                icon = {
                                    Image(
                                        painter = painterResource(id = item.image),
                                        contentDescription = item.title,
                                        modifier = Modifier.size(24.dp) // Adjust the size of the image
                                    )
                                },
                                label = { Text(text = item.title, color = Color.Black) },

                                selected = item.title == selectedItem.value,
                                onClick = {
                                    selectedItem.value = item.title
                                    scope.launch {
                                        drawerState.close() // Close the drawer
                                    }
                                    item.onClick() // Navigate to respective route
                                },
                                modifier = Modifier.padding(5.dp).background(Color(0xFFF0DDF3))
                            )
                        }
                    }
                }
            ) {
                // Content of the main screen can go here (inside the Scaffold content block)
            }
        }
    )
}

@Composable
fun BottomNavigationBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    BottomNavigation(
        backgroundColor = Color(0xFFE7B8F5), // Light pink color
        contentColor = Color.Black // Default content color for icons and text
    ) {
        BottomNavigationItem(
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            icon = {
                Image(
                    painter = painterResource(R.drawable.attendance),
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = 50.dp, width = 60.dp)
                        .let {
                            if (selectedTab == 0) {
                                Text("home")
                                it.background(Color.White).shadow(2.dp) // Elevation when selected
                            } else {
                                it
                            }
                        }
                )
            },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.Black
        )
        BottomNavigationItem(
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            icon = {
                Image(
                    painter = painterResource(R.drawable.marks),
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = 50.dp, width = 60.dp)
                        .let {
                            if (selectedTab == 1) {
                                it.background(Color.White).shadow(2.dp) // Elevation when selected
                            } else {
                                it
                            }
                        })
            },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.Black
        )
        BottomNavigationItem(
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) },
            icon = {
                Image(
                    painter = painterResource(R.drawable.map),
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = 50.dp, width = 60.dp)
                        .let {
                            if (selectedTab == 2) {
                                it.background(Color.White).shadow(2.dp) // Elevation when selected
                            } else {
                                it
                            }
                        }
                )
            },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.Black

        )
        BottomNavigationItem(
            selected = selectedTab == 3,
            onClick = { onTabSelected(3) },
            icon = {
                Image(
                    painter = painterResource(R.drawable.customer),
                    contentDescription = null,
                    modifier = Modifier
                        .size(height = 50.dp, width = 60.dp)
                        .let {
                            if (selectedTab == 3) {
                                it.background(Color.White).shadow(2.dp) // Elevation when selected
                            } else {
                                it
                            }
                        }
                )
            }, selectedContentColor = Color.White,
            unselectedContentColor = Color.Black

        )
    }
}

data class Screen(
    val title: String,
    val image: Int,
    val onClick: () -> Unit
)