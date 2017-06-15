package com.team.newapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SmsManager smsManager;
    //SMS MERKEZ NUMARASI
    private static final String MERKEZ_NUMARA ; // to send number
    private Context context =this;
    private Button Bakiye;
    private Button KartBilgisi;
    private Button Borcgor;
    private Button SifreAl;
    private Button BeniAra;
    private Button FaturaOde;
    private Button TlGonder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("ING Bank");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bakiye = (Button) findViewById(R.id.bakiye);
        KartBilgisi = (Button) findViewById(R.id.Kartbilgisi);
        Borcgor = (Button) findViewById(R.id.Borcgor);
        SifreAl = (Button) findViewById(R.id.sifreAl);
        BeniAra = (Button) findViewById(R.id.beniara);
        FaturaOde = (Button) findViewById(R.id.faturaode);
        TlGonder = (Button) findViewById(R.id.TlGonder);

        smsManager = SmsManager.getDefault();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},3 );

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    private void DefaultSendMessage(String message){
        smsManager.sendTextMessage(MERKEZ_NUMARA, null, message, null, null);
        Toast.makeText(context,"Talep Gönderildi, Teşekkür Ederiz",Toast.LENGTH_SHORT).show();
    }
    public void ButtonBakiye(View view){
        String smsText = "Bakiye_"; //+ get Default Kart Number
        DefaultSendMessage(smsText);
    }
    public void ButtonKartBilgisi(View view){
        String smsText = "CartInfo_"; //+ get Default Kart Number
        DefaultSendMessage(smsText);
    }
    public void ButtonBorcGor(View view){
        String smsText = "ShowDebt_"; //+ get Default Kart Number
        DefaultSendMessage(smsText);
    }
    public void ButtonSifreAl(View view){
        String smsText = "GetParola_"; //+ get Default Kart Number
        DefaultSendMessage(smsText);
    }
    public void ButtonBeniAra(View view){
        String smsText = "CallMe";
        DefaultSendMessage(smsText);
    }
    public void ButtonFaturaOde(View view){
        /*
        * IsCep Modeli Uygulama
        */
    }
    public void ButtonTlGonder(View view){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.customdialog);
        dialog.setTitle("Ayarlar ");

        final EditText PhoneNumber = (EditText) dialog.findViewById(R.id.User);
        final EditText Amount = (EditText) dialog.findViewById(R.id.Amount);
        final EditText Parola = (EditText) dialog.findViewById(R.id.parola);
        ImageView image = (ImageView) dialog.findViewById(R.id.imageView);
        image.setImageResource(R.drawable.logo);

        Button okButton = (Button) dialog.findViewById(R.id.Ok);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String smsText = "Instant_Money_Send_" + PhoneNumber.getText() + "_" + Amount.getText() + "_"+Parola.getText();
                DefaultSendMessage(smsText);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
