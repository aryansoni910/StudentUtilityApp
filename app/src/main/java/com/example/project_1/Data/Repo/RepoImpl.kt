package com.example.project_1.Data.Repo

import android.net.Uri
import android.util.Log
import com.example.project_1.Common.ResultState
import com.example.project_1.Common.User_Collection
import com.example.project_1.Domain.Model.UserData
import com.example.project_1.Domain.Model.UserDataParent
import com.example.project_1.Domain.Repo.Repo
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID
import javax.inject.Inject

class RepoImpl @Inject constructor(
    var firebaseAuth: FirebaseAuth,
    var firebaseFirestore: FirebaseFirestore
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


    override fun userProfileImage(uri: Uri): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        FirebaseStorage.getInstance().reference.child("userProfileImages/${System.currentTimeMillis()}+${firebaseAuth.currentUser?.uid}")
            .putFile(uri ?: Uri.EMPTY).addOnCompleteListener {
                it.result.storage.downloadUrl.addOnSuccessListener { imageUrl ->
                    trySend(ResultState.Success(imageUrl.toString()))
                }
                if (it.exception != null) {
                    trySend(ResultState.Error(it.exception?.localizedMessage.toString()))
                }

            }
        awaitClose {
            close()
        }


    }



    override fun  registerUserWithEmailAndPassword(userData: UserData): Flow<ResultState<String>> =
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