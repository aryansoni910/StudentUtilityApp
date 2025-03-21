package com.example.project_1.Data.Repo

import android.app.Application
import android.net.Uri
import android.util.Log
import com.example.project_1.Common.Gate_Pass
import com.example.project_1.Common.ResultState
import com.example.project_1.Common.Student_Collection
import com.example.project_1.Common.User_Collection
import com.example.project_1.Data.Database.Dao
import com.example.project_1.Data.Database.PasswordManager
import com.example.project_1.Data.Database.PasswordManagerDataBase
import com.example.project_1.Data.Network.Apiprovider
import com.example.project_1.Data.Network.StudentModel
import com.example.project_1.Domain.Model.AttendanceDataParent
import com.example.project_1.Domain.Model.GatePassdata
import com.example.project_1.Domain.Model.StudentData
import com.example.project_1.Domain.Model.StudentDataParent
import com.example.project_1.Domain.Model.SubjectDataParent
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Domain.Model.UserDataParent
import com.example.project_1.Domain.Repo.Repo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import java.util.UUID
import javax.inject.Inject

class RepoImpl @Inject constructor(
    var firebaseAuth: FirebaseAuth,
    var firebaseFirestore: FirebaseFirestore,
    var passwordManagerDataBase: PasswordManagerDataBase,
    var application: Application,
) : Repo {
    override fun LoginWithEmailAndPassword(userData: UserData): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)



            firebaseAuth.signInWithEmailAndPassword(userData.email, userData.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResultState.Success("User Logged In Successfully"))
                    } else {
                        if (it.exception != null) {
                            trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                        }
                    }
                }



            awaitClose {
                close()
            }
        }


    override fun getuserById(uid: String): Flow<ResultState<UserDataParent>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseFirestore.collection(User_Collection)
            .document(uid).get().addOnCompleteListener {
                if (it.isSuccessful) {
                    val data = it.result.toObject(UserData::class.java)!!
                    val userDataParent = UserDataParent(it.result.id, data)
                    trySend(ResultState.Success(userDataParent))
                } else {
                    if (it.exception != null) {
                        trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                    }
                }
            }
        awaitClose {
            close()
        }
    }

    override fun getallStudent5(): Flow<ResultState<List<StudentData>>> = callbackFlow {
        trySend(ResultState.Loading) // Emit the loading state

        // Perform Firebase query to fetch students in semester 5
        firebaseFirestore.collection(Student_Collection)
            .whereEqualTo("sem", "5")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Convert the result to a list of StudentData
                    val data = task.result?.toObjects(StudentData::class.java) ?: emptyList()

                    // Emit the success state with the data
                    trySend(ResultState.Success(data))
                } else {
                    // If there's an error, send the error message
                    task.exception?.localizedMessage?.let {
                        trySend(ResultState.Error(it))
                    }
                }
            }

        // Ensure the flow is properly closed
        awaitClose {
            // Close the flow when the operation finishes or is cancelled
            close()
        }
    }

    override fun addMarks5(studentDataParent: SubjectDataParent): Flow<ResultState<StudentData>> =
        callbackFlow {
            trySend(ResultState.Loading)

            // Assuming that 'email' is not the document ID, but a field in the document
            firebaseFirestore.collection(Student_Collection)
                .whereEqualTo("email", studentDataParent.nodeId)  // Querying by the 'email' field
                .get()
                .addOnSuccessListener { result ->
                    if (!result.isEmpty) {
                        val document =
                            result.documents[0]  // We assume there's only one student with that email
                        val studentData =
                            document.toObject(StudentData::class.java)  // Deserialize student data

                        // Now add the subject marks to the student's 'subjects' array
                        firebaseFirestore.collection(Student_Collection)
                            .document(document.id) // Use the document's ID
                            .update("subjects", FieldValue.arrayUnion(studentDataParent.subject))
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Log.d("add", "addMarks5: add data${studentData} ")
                                } else {
                                    trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                                }
                            }
                    } else {
                        trySend(ResultState.Error("No student found with that email"))
                    }
                }
                .addOnFailureListener {
                    trySend(ResultState.Error(it.localizedMessage.toString()))
                }

            awaitClose { close() }
        }

    override fun addattendance(attendanceDataParent: AttendanceDataParent): Flow<ResultState<StudentData>> =
        callbackFlow {
            trySend(ResultState.Loading)

            firebaseFirestore.collection(Student_Collection)
                .whereEqualTo(
                    "email",
                    attendanceDataParent.nodeId
                )  // Querying by the 'email' field
                .get()
                .addOnSuccessListener { result ->
                    if (!result.isEmpty) {
                        val document =
                            result.documents[0]  // We assume there's only one student with that email
                        val studentData =
                            document.toObject(StudentData::class.java)  // Deserialize student data

                        // Now add the subject marks to the student's 'subjects' array
                        firebaseFirestore.collection(Student_Collection)
                            .document(document.id) // Use the document's ID
                            .update(
                                "attendance",
                                FieldValue.arrayUnion(attendanceDataParent.attendance)
                            )
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Log.d("add", "addMarks5: add data${studentData} ")
                                } else {
                                    trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                                }
                            }
                    } else {
                        trySend(ResultState.Error("No student found with that email"))
                    }
                }
                .addOnFailureListener {
                    trySend(ResultState.Error(it.localizedMessage.toString()))
                }

            awaitClose { close() }


        }

    override fun StudentLoginWithEmailAndPassword(studentData: StudentData): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)



            firebaseAuth.signInWithEmailAndPassword(studentData.email, studentData.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(ResultState.Success("User Logged In Successfully"))
                    } else {
                        if (it.exception != null) {
                            trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                        }
                    }
                }



            awaitClose {
                close()
            }
        }

    override fun getstudentbyid(uid: String): Flow<ResultState<StudentDataParent>> = callbackFlow {
        trySend(ResultState.Loading)
        firebaseFirestore.collection(Student_Collection)
            .document(uid).get().addOnCompleteListener {
                if (it.isSuccessful) {
                    val data = it.result.toObject(StudentData::class.java)!!
                    val studentdataParent = StudentDataParent(it.result.id, data)
                    trySend(ResultState.Success(studentdataParent))
                } else {
                    if (it.exception != null) {
                        trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                    }
                }
            }
        awaitClose {
            close()
        }
    }

    override fun getmarksbyid(uid: String): Flow<ResultState<StudentDataParent>> = callbackFlow {
        trySend(ResultState.Loading)

        firebaseFirestore.collection(Student_Collection)
            .document(uid).get().addOnCompleteListener {
                if (it.isSuccessful) {
                    val data = it.result.toObject(StudentData::class.java)!!
                    val studentDataParent = StudentDataParent(it.result.id, data)

                    trySend(ResultState.Success(studentDataParent))
                } else {
                    if (it.exception != null) {
                        trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                    }
                }
            }
        awaitClose {
            close()
        }
    }

    override suspend fun newProvider(): Response<StudentModel> {
        return Apiprovider.provideApi().getNewsFromServer()
    }

    override suspend fun upsert(passwordManager: PasswordManager) =
        passwordManagerDataBase.dao().upsertPassword(passwordManager)

    override fun getAllPassword() =
        passwordManagerDataBase.dao().getPassword().onEach { passwords ->
        }

    override suspend fun delete(passwordManager: PasswordManager) =
        passwordManagerDataBase.dao().deletePassword(passwordManager)


    override fun userProfileImage(uri: Uri): Flow<ResultState<String>> = callbackFlow {
        // Start by sending the loading state
        trySend(ResultState.Loading)

        // Reference for the Firebase Storage path
        val storageReference = FirebaseStorage.getInstance().reference
            .child("userProfileImages/${System.currentTimeMillis()}+${firebaseAuth.currentUser?.uid}")

        // Upload the file
        val uploadTask = storageReference.putFile(uri)

        // Add an OnCompleteListener for the upload task
        uploadTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // If successful, get the download URL
                task.result?.storage?.downloadUrl?.addOnSuccessListener { imageUrl ->
                    trySend(ResultState.Success(imageUrl.toString()))
                }?.addOnFailureListener { exception ->
                    trySend(ResultState.Error(exception.localizedMessage ?: "Unknown error"))
                }
            } else {
                // If the upload failed
                trySend(ResultState.Error(task.exception?.localizedMessage ?: "Unknown error"))
            }
        }

        // Await closure of the flow
        awaitClose {
            // Close the flow if the task is cancelled or completed
            close()
        }
    }


    override fun StudentregisterUserWithEmailAndPassword(studentData: StudentData): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)

            firebaseAuth.createUserWithEmailAndPassword(studentData.email, studentData.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        firebaseFirestore.collection(Student_Collection)
                            .document(it.result?.user?.uid.toString()).set(studentData)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    trySend(ResultState.Success("User Registered Successfully"))

                                } else {
                                    if (it.exception != null) {
                                        trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                                    }
                                }
                            }

                    } else {
                        if (it.exception != null) {
                            trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                        }
                    }

                }
            awaitClose {
                close()
            }


        }

    override fun gatepass(gatePassdata: GatePassdata): Flow<ResultState<String>> = callbackFlow {

        trySend(ResultState.Loading)
        val gatepassId = UUID.randomUUID().toString()
        firebaseFirestore.collection(Gate_Pass).document(gatepassId).set(gatePassdata)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    trySend(ResultState.Success("Gate Pass Added Successfully"))
                } else {
                    if (it.exception != null) {
                        trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                    }
                }
            }
        awaitClose {
            close()
        }
    }


    override fun registerUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)

            firebaseAuth.createUserWithEmailAndPassword(userData.email, userData.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        firebaseFirestore.collection(User_Collection)
                            .document(it.result?.user?.uid.toString()).set(userData)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    trySend(ResultState.Success("User Registered Successfully"))

                                } else {
                                    if (it.exception != null) {
                                        trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                                    }
                                }
                            }

                    } else {
                        if (it.exception != null) {
                            trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                        }
                    }

                }
            awaitClose {
                close()
            }

        }
}