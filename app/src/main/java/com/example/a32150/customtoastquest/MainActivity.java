package com.example.a32150.customtoastquest;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setOnKeyListener();
    }

    void findView(){
        et1 = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
    }

    void setOnKeyListener(){
        et1.setOnKeyListener(onKeyListener);
        et2.setOnKeyListener(onKeyListener);
        et3.setOnKeyListener(onKeyListener);
    }

    OnKeyListener onKeyListener = new OnKeyListener(){

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event)
        {
            try {
                //判斷使用者是否有按下的動作以及按下的是否為Enter鍵
                if (event.getAction() == KeyEvent.ACTION_DOWN & keyCode == KeyEvent.KEYCODE_ENTER) {

                    //取得使用者輸入的選項，並轉成數值，以便於switch-case的比對
                    int choice = Integer.parseInt(((EditText) v).getText().toString());

                    if (((EditText) v) == et3) {
                        //當使用者互動的是EditText3時，根據使用者的選擇，來顯示特定文字
                        switch (choice) {
                            case 1:
                                customToast(R.string.number1, R.drawable.crispy);
                                break;
                            case 2:
                                customToast(R.string.number2, R.drawable.big_mac);
                                break;
                            case 3:
                                customToast(R.string.number3, R.drawable.spicy_chicken_filet_burger);
                                break;
                            case 4:
                                customToast(R.string.number4, R.drawable.chicken_mc_nuggets_4pcs);
                                break;
                            default:
                                Toast.makeText(MainActivity.this, R.string.wrong, Toast.LENGTH_SHORT).show();
                                et3.setText("");
                        }
                    } else
                        //當使用者互動的是EditText1與EditText2時，就顯示使用者所輸入的資料
                        Toast.makeText(MainActivity.this, ((EditText) v).getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }   catch(NumberFormatException e) {
                e.printStackTrace();
            }   catch(Resources.NotFoundException e) {
                e.printStackTrace();
            }
            // TODO Auto-generated method stub
            return false;
        }
    };

    void customToast(int opt,int pic)  {

        Toast toast = Toast.makeText(MainActivity.this, opt , Toast.LENGTH_SHORT);
        View original = toast.getView();
        LinearLayout linearLayout = new LinearLayout(MainActivity.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        ImageView img = new ImageView(MainActivity.this);
        img.setImageResource(pic);
        linearLayout.addView(img);
        linearLayout.addView(original);
        toast.setView(linearLayout);
        toast.show();
    }
}
