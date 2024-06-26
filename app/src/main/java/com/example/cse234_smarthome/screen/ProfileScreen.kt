package com.example.cse234_smarthome.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cse234_smarthome.R
import com.example.cse234_smarthome.component.AddRoomDialog
import com.example.cse234_smarthome.component.ExpandableCard
import com.example.cse234_smarthome.component.UserTag
import com.example.cse234_smarthome.data.UserManager
import com.example.cse234_smarthome.data.remote.HomeFunctions
import com.example.cse234_smarthome.data.remote.RoomFunction
import com.example.cse234_smarthome.data.remote.dto.AddHomeRequest
import com.example.cse234_smarthome.data.remote.dto.AddRoomRequest
import com.example.cse234_smarthome.data.remote.dto.GetAllRoomsByHomeIdResponse
import com.example.cse234_smarthome.data.remote.dto.GetHomesByOwnerIDResponse
import com.example.cse234_smarthome.ui.theme.primaryBackground
import com.example.cse234_smarthome.ui.theme.primaryBlueGray
import kotlinx.coroutines.launch


@Composable
fun ProfileScreen(navController: NavController) {
    var showAddHomeDialog by remember { mutableStateOf(false) }
    var showAddRoomDialog by remember { mutableStateOf(false) }
    var showEditNameDialog by remember { mutableStateOf(false) }
    val ownerId = UserManager.getCurrentUser()?.Id
    var homes by remember { mutableStateOf<List<GetHomesByOwnerIDResponse>?>(emptyList()) }
    var rooms by remember { mutableStateOf<List<GetAllRoomsByHomeIdResponse>?>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var homeId by remember { mutableStateOf(-30) }
    val homeClient  = HomeFunctions.create()
    val roomClient = RoomFunction.create()


    ownerId?.let {
        coroutineScope.launch {
            val response = homeClient.getHomesByOwnerID(ownerId)
            if (response != null) {
                homes = response.data
            }else{
                errorMessage = response?.message
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = primaryBackground)
        ) {
            item {
                UserManager.getCurrentUser()?.let {
                    UserTag(
                        imageRes = painterResource(id = R.drawable.ibo),
                        userName = it.Name
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(homes ?: emptyList()) { home ->
                ExpandableCard(
                    title = home.name,
                    rooms = listOf(),
                    roommates = listOf(),
                    navController = navController,
                    devices = listOf(),
                    onEditClick = {
                        showEditNameDialog = true
                    },
                    onAddRoomClick = {
                        homeId = home.id
                        showAddRoomDialog = true
                    },
                )
            }
            item {
                FloatingActionButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomEnd),
                    containerColor = primaryBlueGray,
                    contentColor = Color.White,
                    onClick = {
                        showAddHomeDialog =true
                    }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.Add, contentDescription = "Add Home")
                        Text("Add Home", fontSize = 10.sp)
                    }
                }
            }
        }

        errorMessage?.let {
            Text(
                text = it,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    if (showAddHomeDialog) {
        AddHomeDialog(
            onDismiss = { showAddHomeDialog = false },
            onSave = {
                     //name, address ->
//                coroutineScope.launch {
//                    try {
//                        val req = AddHomeRequest(
//                            name = name,
//                            address = address,
//                            ownerId = ownerId ,
//                        )
//                        homeId = homeClient.addHome(req)
//                        if (homeId == -1) {
//                            errorMessage  = "-1"
//                        }else if (homeId == -5) {
//                            errorMessage  = "-5"
//                        }else if(homeId == -30){
//                            errorMessage = "-30"
//                        }
//                    } catch (e: Exception) {
//                        errorMessage = "Exception occurred: ${e.message}"
//                    }
//                }
                showAddHomeDialog = false
            }
        )
    }
    if (showAddRoomDialog) {
        AddRoomDialog(onDismiss = { showAddRoomDialog = false }) {
            coroutineScope.launch {
                try {
                    val req = AddRoomRequest(
                        name = it,
                        HomeId = homeId
                    )
                    val response = roomClient.addRoom(req)
                    if (response == -1 ) {
                        errorMessage  = response.toString()
                    }else if (response == -5) {
                        errorMessage  = "-5"
                    }
                } catch (e: Exception) {
                    errorMessage = "Exception occurred: ${e.message}"
                }
            }
            showAddRoomDialog = false
        }
    }
}

@Composable
fun AddHomeDialog(onDismiss: () -> Unit, onSave: Any) {

}
