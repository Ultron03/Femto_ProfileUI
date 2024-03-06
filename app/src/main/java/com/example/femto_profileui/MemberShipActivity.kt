package com.example.femto_profileui

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.phonepe.intent.sdk.api.B2BPGRequest
import com.phonepe.intent.sdk.api.B2BPGRequestBuilder
import com.phonepe.intent.sdk.api.PhonePe
import org.json.JSONObject
import java.nio.charset.Charset
import java.security.MessageDigest


class MemberShipActivity : AppCompatActivity() {
    private lateinit var back_to_profile: CardView
    private lateinit var btn_basicplan:Button
    private lateinit var btn_standardplan:Button
    private lateinit var btn_premiumplan:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_ship)

        init()

        back_to_profile.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }


        PhonePe.init(this)
        val data = JSONObject()
        data.put("merchantTransactionId" , System.currentTimeMillis().toString())
        data.put("merchantId", "PGTESTPAYUAT")
        data.put("merchantUserId", System.currentTimeMillis().toString())
        data.put("amount", 3500)
        data.put("mobileNumber" ,"8104362445")
        data.put("callbackUrl", "https://webhook.site/d356c195-c606-4fc3-a1ce-91bd0d4a68a0")

        val  mPaymentInstrument = JSONObject()
        mPaymentInstrument.put("type","PAY_PAGE")
        data.put("paymentInstrument", mPaymentInstrument)

        val base64Body:String = Base64.encodeToString(data.toString().toByteArray(
            Charset.defaultCharset()
        ), Base64.NO_WRAP)


        val checksum:String =sha256(base64Body + "/pg/v1/pay" + "099eb0cd-02cf-4e2a-8aca-3e6c6aff0399") + "###1"

        val b2BPGRequest:B2BPGRequest = B2BPGRequestBuilder()
            .setData(base64Body)
            .setChecksum(checksum)
            .setUrl("/pg/v1/pay")
            .build()


        btn_basicplan.setOnClickListener {
            try {

                startActivityForResult(PhonePe.getImplicitIntent(
                    this, b2BPGRequest, "")!!,1);
            } catch(e:Exception) {
                Log.i("PhonePe", "Error getting PhonePe intent", e)
            }
        }

        btn_standardplan.setOnClickListener {
            try {
                startActivityForResult(PhonePe.getImplicitIntent(
                    this, b2BPGRequest, "")!!,1);
            } catch(e:Exception) {
                Log.i("PhonePe", "Error getting PhonePe intent", e)
            }
        }

        btn_premiumplan.setOnClickListener {
            try {
                startActivityForResult(PhonePe.getImplicitIntent(
                    this, b2BPGRequest, "")!!,1);
            } catch(e:Exception) {
                Log.i("PhonePe", "Error getting PhonePe intent", e)
            }
        }



    }

    private fun sha256(input:String):String{
        val bytes:ByteArray = input.toByteArray(Charsets.UTF_8)
        val md:MessageDigest = MessageDigest.getInstance("SHA-256")
        val digest:ByteArray = md.digest(bytes)
        return digest.fold(""){str,it -> str + "%02x".format(it)}
    }
    private fun init(){
        back_to_profile = findViewById(R.id.back_to_profile_from_membership)
        btn_basicplan = findViewById(R.id.basicplan_pay)
        btn_premiumplan = findViewById(R.id.premiumplan_pay)
        btn_standardplan = findViewById(R.id.standardplan_pay)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
        }
    }
}