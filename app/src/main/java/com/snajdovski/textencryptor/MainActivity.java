package com.snajdovski.textencryptor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnDecrypt;
    Button btnEncrypt;
    Button btnGeneratePassword;
    ImageButton btnShare;
    EditText etPassword;
    EditText etText;
    String inputText;
    String password;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        this.etPassword = (EditText) findViewById(R.id.et_password);
        this.etText = (EditText) findViewById(R.id.et_text);
        this.btnGeneratePassword = (Button) findViewById(R.id.btn_generate_password);
        this.btnEncrypt = (Button) findViewById(R.id.btn_encrypt);
        this.btnDecrypt = (Button) findViewById(R.id.btn_decrypt);
        this.btnShare = (ImageButton) findViewById(R.id.btn_share);
        this.btnEncrypt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    MainActivity.this.inputText = MainActivity.this.etText.getText().toString();
                    MainActivity.this.password = MainActivity.this.etPassword.getText().toString();
                    if (MainActivity.this.inputText.length() > 0 && MainActivity.this.password.length() > 0) {
                        MainActivity.this.etText.setText(AES.encrypt(MainActivity.this.inputText, MainActivity.this.password));
                    } else if (MainActivity.this.inputText.length() == 0 && MainActivity.this.password.length() == 0) {
                        Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.no_input_and_password), Toast.LENGTH_LONG).show();
                    } else if (MainActivity.this.inputText.length() == 0) {
                        Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.no_input), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.no_password), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.btnDecrypt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    MainActivity.this.inputText = MainActivity.this.etText.getText().toString();
                    MainActivity.this.password = MainActivity.this.etPassword.getText().toString();
                    if (MainActivity.this.inputText.length() > 0 && MainActivity.this.password.length() > 0) {
                        MainActivity.this.etText.setText(AES.decrypt(MainActivity.this.inputText, MainActivity.this.password));
                    } else if (MainActivity.this.inputText.length() == 0 && MainActivity.this.password.length() == 0) {
                        Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.no_input_and_password), Toast.LENGTH_LONG).show();
                    } else if (MainActivity.this.inputText.length() == 0) {
                        Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.no_input), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, MainActivity.this.getString(R.string.no_password), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.btnShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.TEXT", MainActivity.this.etText.getText().toString());
                intent.setType("text/plain");
                MainActivity.this.startActivity(intent);
            }
        });
        this.btnGeneratePassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.showPasswordGenerator();
            }
        });
    }

    public void showPasswordGenerator() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View inflate = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.dialog_generate_password, (ViewGroup) null);
        builder.setView(inflate);
        builder.setCancelable(false);
        builder.setTitle((CharSequence)
                "Generate password");
        builder.setPositiveButton((CharSequence)
                "Generate password", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = ((EditText) inflate.findViewById(R.id.et_quantity)).getText().toString();
                int parseInt = obj.length() > 0 ? Integer.parseInt(obj) : 0;
                if (parseInt > 0) {
                    boolean isChecked = ((CheckBox) inflate.findViewById(R.id.cb_upper_case)).isChecked();
                    String str = "";
                    if (isChecked) {
                        str = str + "u";
                    }
                    if (((CheckBox) inflate.findViewById(R.id.cb_lower_case)).isChecked()) {
                        str = str + "l";
                    }
                    if (((CheckBox) inflate.findViewById(R.id.cb_number)).isChecked()) {
                        str = str + "n";
                    }
                    if (((CheckBox) inflate.findViewById(R.id.cb_special_case)).isChecked()) {
                        str = str + "s";
                    }
                    if (str.length() > 0) {
                        MainActivity.this.etPassword.setText(PasswordGenerator.generate(str, parseInt));
                    }
                }
            }
        });
        builder.setNegativeButton((CharSequence)
                "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }


}