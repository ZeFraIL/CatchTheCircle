package zeev.fraiman.catchthecircle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class CatchNow extends AppCompatActivity {
    Context context;
    ImageView ivRC;
    ConstraintLayout CL;
    int maxOffset, tickTime,all,i,ok,screenWidth,screenHeight,
            imageViewWidth,imageViewHeight,maxX,maxY,minX, minY, newX,newY;
    Button bStart;
    CountDownTimer cdt;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_now);

        initComponents();

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdt=new CountDownTimer(all*tickTime, tickTime) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Random random = new Random();
                        int randomX = random.nextInt(maxX);
                        int randomY = random.nextInt(maxY);
                        ivRC.setX(randomX);
                        ivRC.setY(randomY);
                        tvResult.setText("Result = "+ok+" / "+i);
                        i++;
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
                tickTime-=200;
                if (tickTime==0)
                    tickTime=200; // OR WHAT?
            }
        });

        ivRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok++;
                tvResult.setText("Result = "+ok+" / "+i);
            }
        });


    }

    private void initComponents() {
        context=this;
        ivRC=findViewById(R.id.ivRC);
        CL=findViewById(R.id.container);
        bStart=findViewById(R.id.bStart);
        tvResult=findViewById(R.id.tvResult);
        tickTime=1000;
        all=10;
        i=1;
        ok=0;
        screenWidth = getScreenWidth(context);
        screenHeight = getScreenHeight(context);
        imageViewWidth = ivRC.getWidth();
        imageViewHeight = ivRC.getHeight();
        minX=50;
        minY=400;
        maxX = screenWidth - imageViewWidth-minX;
        maxY = screenHeight - imageViewHeight-minY;
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}