package exam.android.kh.myapplicationtodayisgonnabe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void function(View view) {
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat YeatdateFormat = new SimpleDateFormat("yyyy ");
        DateFormat MonthFormat = new SimpleDateFormat("MM");
        DateFormat dayFormat = new SimpleDateFormat("dd ");
        String YearDateStr = YeatdateFormat.format(currentTime);
        String MonthDateStr = MonthFormat.format(currentTime);
        String DayDateStr = dayFormat.format(currentTime);
        Toast.makeText(MainActivity.this.getApplicationContext(),"오늘은 "+YearDateStr+"년"+MonthDateStr+"월"+DayDateStr+"일 입니다",Toast.LENGTH_LONG).show();
    }
}
