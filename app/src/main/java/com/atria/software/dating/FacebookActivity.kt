package com.atria.software.dating

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import java.util.*


class FacebookActivity : AppCompatActivity() {

    private lateinit var callbackManager:CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook2)

        FacebookSdk.sdkInitialize(applicationContext);
        AppEventsLogger.activateApp(this);

        val  EMAIL= "email";
        callbackManager = CallbackManager.Factory.create();

        val loginButton = findViewById<View>(R.id.login_button) as LoginButton
        loginButton.setReadPermissions("aryeahtyagi@gmail.com", "public_profile","user_videos","user_posts");
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                Toast.makeText(applicationContext, "HELLO LADY", Toast.LENGTH_SHORT).show()
            }

            override fun onCancel() {
                Toast.makeText(applicationContext, "HELLO LKJ", Toast.LENGTH_SHORT).show()

            }

            override fun onError(exception: FacebookException) {
                exception.printStackTrace()
                Toast.makeText(applicationContext, "HELLO KJLKJ"+exception.message, Toast.LENGTH_SHORT).show()
            }
        })

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    Toast.makeText(applicationContext, "HELLO LADY", Toast.LENGTH_SHORT).show()
                }

                override fun onCancel() {
                    Toast.makeText(applicationContext, "HELLO LKJ", Toast.LENGTH_SHORT).show()

                }

                override fun onError(exception: FacebookException) {
                    exception.printStackTrace()
                    Toast.makeText(applicationContext, "HELLO KJLKJ"+exception.message, Toast.LENGTH_SHORT).show()
                }
            })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}