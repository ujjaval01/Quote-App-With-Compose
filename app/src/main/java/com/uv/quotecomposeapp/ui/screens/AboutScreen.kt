package com.uv.quotecomposeapp.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import com.uv.quotecomposeapp.R



@Composable
fun AboutScreen(navController: NavController) {

    val context = LocalContext.current
    val versionName = context.packageManager
        .getPackageInfo(context.packageName, 0).versionName




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        // 🔙 Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "About App",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 🖼 App Logo
        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 📦 About Card
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                Text(
                    text = "Daily Quotes",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Daily Quotes App helps you stay motivated and inspired every day.",
                    fontSize = 15.sp,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Divider()

                Spacer(modifier = Modifier.height(16.dp))

                Text("Version: $versionName")
                Text("Developer: Ujjaval Saini")

                Spacer(modifier = Modifier.height(20.dp))

                // 📧 Clickable Email
                Text(
                    text = "Contact: ujvl.dev@gmail.com",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:ujvl.dev@gmail.com")
                        }
                        context.startActivity(intent)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Divider()

                Spacer(modifier = Modifier.height(16.dp))

                // 🌐 Social Links
                Text(
                    text = "GitHub",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        openUrl(context, "https://github.com/ujjaval01")
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "LinkedIn",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        openUrl(context, "https://linkedin.com/in/ujjavalsaini")
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Made with ❤️ by Ujjaval Saini",
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview() {
    MaterialTheme {
        AboutScreen(
            navController = androidx.navigation.compose.rememberNavController()
        )
    }
}


