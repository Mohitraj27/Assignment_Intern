package com.example.assignment_intern;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    TextView  textgetInformation,textRam;
    Button getInfo;



    long bytesAvailable,megAvailable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // this method is for intilaizing method
        initView();

        StatFs statFs=new StatFs(Environment.getDownloadCacheDirectory().getPath());
        bytesAvailable=statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();


        megAvailable =bytesAvailable/(1024 * 1024);
        //initListeners();
    }


    private  void initView(){
        textgetInformation=findViewById(R.id.setInformation);
        getInfo=findViewById(R.id.getinformation);


        textRam=findViewById(R.id.textRam);


        //Id which was created for the button is called here and
        // as the user hits the button using .setOnclickListenner the method is called
        // all the information is extacted  and converted into string fromat using String Builder class
        getInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for getting hardware  and softwatre information we've used here string class to get the info
                // extracted in string format

                String information=getHardwareAndSoftwareInfo();



                textgetInformation.setText(information);


                textRam.setText(getMemoryInfo());


            }
        });
    }

    // This method extract the memory  info of the device  using ActivityManageer class and inside that class subclass memoryinfo is used and a new
    // object is created after that using activty manager memory information is extracted
    private String getMemoryInfo() {
        Context context=getApplicationContext();

        ActivityManager activityManager=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        ActivityManager.MemoryInfo memoryInfo=new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        DecimalFormat twoDecimalFormat=new DecimalFormat("#.##");

        //final value is intialized for getting extracting into two decimal format
    String finalvalue="";

    //This all value of storage size and all converted into higher data type i.e long and double
    long totalMemory=memoryInfo.totalMem;

    double kb=totalMemory/1024.0;
    double mb=totalMemory/1048576.0;
    double gb=totalMemory/1073741824.0;
    double tb=totalMemory/1099511627776.0;


    //Condition for different sizze values
    if(tb>1)
    {
        finalvalue=twoDecimalFormat.format(tb).concat("TB");

    }
    else if(gb>1)
    {
        finalvalue=twoDecimalFormat.format(gb).concat("GB");


    }
    else if(mb>1)
    {
        finalvalue=twoDecimalFormat.format(mb).concat("MB");

    }
else if(kb>1)
    {
        finalvalue=twoDecimalFormat.format(kb).concat("KB");

    }
else {
    finalvalue=twoDecimalFormat.format(totalMemory).concat("Bytes");
    }
//String builder class to extract these value and return in string format using .tostring() function
StringBuilder stringBuilder=new StringBuilder();
stringBuilder.append("RAM ").append(finalvalue).append("\n")
        .append("Available Internal /External free space: "+megAvailable+"MB");


// after appending all the data using stringBuilder class it is returned into string format using .toString() method
return stringBuilder.toString();
    }

    //This method used to extract the Model, Manfucatere etc details which are already present in the OS Android device where
    // the app is running . I've just extracted  all these details from BUILD.OS package
    private String getHardwareAndSoftwareInfo() {
        return "MODEL" + "     "+ Build.SERIAL+  "\n"+
                "ID" +"     " +Build.ID+"\n"+
                "MANUFACTURER" +"     " +Build.MANUFACTURER+"\n"+
                "BRAND" +"     " +Build.BRAND+"\n"+
                "TYPE " + "     "+ Build.TYPE +"\n"+
                "USER" +"     " +Build.USER+"\n"+
                "BASE" +"     " +Build.VERSION_CODES.BASE+"\n"+
                "SDK" +"     " +Build.VERSION.SDK+"\n"+
                "BOARD" +"     " +Build.BOARD+"\n"+
                "HOST" +"     " +Build.HOST+"\n"+
                "FINGERPRINT" +"     " +Build.FINGERPRINT+"\n"+
                "VERSION  CODE" +"     " +Build.VERSION.RELEASE;
    }
}