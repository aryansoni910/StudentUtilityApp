import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.project_1.Presentation.Navigation.Routes
import com.example.project_1.R

@Composable
fun Choice(navController: NavController) {


    Box(modifier = Modifier.fillMaxSize()) {


        Column(
            modifier = Modifier
                .fillMaxSize()  // This will make the Column take up the whole screen
                .background(Color.LightGray)  // Set background color of the container
                .background(
                    Color(0xFFE3B1FD)
                ), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Card(
                modifier = Modifier

                    .size(width = 280.dp, height = 240.dp).padding(20.dp)
                    .clickable {
                        navController.navigate(Routes.SingUpScreen)
                    },
                elevation = CardDefaults.cardElevation(15.dp),
                shape = RoundedCornerShape(20.dp)

            )

            {
                Column() {
                    Image(
                        painter = painterResource(R.drawable.teacher),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )


                    Text(
                        text = "Teacher",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Color(
                                0xFF130C0E
                            )
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                }

            }
            Spacer(Modifier.padding(30.dp))

            Card(
                modifier = Modifier
                    .size(width = 280.dp, height = 240.dp).padding(20.dp)
                    .clickable {
                        navController.navigate(Routes.StudentLogin)
                    },
                elevation = CardDefaults.cardElevation(15.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(modifier = Modifier.padding(top = 5.dp)) {
                    Image(
                        painter = painterResource(R.drawable.student),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)

                    )


                    Text(
                        text = "Student",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Color(
                                0xFF130C0E
                            )
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                }


            }
        }


    }
}