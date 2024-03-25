package com.example.chapter01;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_text_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setTextViewProperty();
    }

    void setTextViewProperty(){
       TextView textView = findViewById(R.id.textView_first);
       textView.setTextSize(30);
       textView.setTextColor(Color.GREEN);
       textView.setTextColor(0xff00ff00);
       textView.setBackgroundColor(Color.LTGRAY);
       textView.setBackgroundResource(R.color.black);
    }
}