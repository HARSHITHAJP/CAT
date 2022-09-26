package com.harshithajp.catapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button fragbtn1;
    Button fragbtn2;

    //------------------optionsmenu-----------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Toast.makeText(this, "selected menu1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuRecycler:
                Toast.makeText(this, "selected menu REcycler", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MainActivity.this, recycler.class);
                startActivity(intent1);
                return true;
            case R.id.menuSharedpref:
                Toast.makeText(this, "selected menu Sharedpref", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this, sharedpref.class);
                startActivity(intent2);
                return true;
            case R.id.menuExit:
                Toast.makeText(this, "selected exit menu", Toast.LENGTH_SHORT).show();
                //-------------------------------notification------------------------------------
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_baseline_info_24);

                int NOTIFICATION_ID = 150;
                NotificationManager notificationManager = (NotificationManager) MainActivity.this.getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
                String CHANNEL_ID = "my_channel_01";

               /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    CHANNEL_ID = "my_channel_01";
                    CharSequence name = "my_channel";
                    String Description = "This is my channel";
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                    mChannel.setDescription(Description);
                    mChannel.enableLights(true);
                    mChannel.setLightColor(Color.RED);
                    mChannel.enableVibration(true);
                    mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                    mChannel.setShowBadge(false);
                    notificationManager.createNotificationChannel(mChannel);
                }*/

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_baseline_perm_device_information_24)
                        .setLargeIcon(bitmap)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(bitmap)
                                .bigLargeIcon(bitmap))
                        .setContentTitle("Exiting")
                        .setContentText(" :-)");

                Intent resultIntent = new Intent(MainActivity.this, NotificationView.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
                stackBuilder.addParentStack(NotificationView.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE);
                builder.setContentIntent(resultPendingIntent);
                notificationManager.notify(NOTIFICATION_ID, builder.build());

                MainActivity.this.finish();
                System.exit(0);
                return true;

            default:
                super.onOptionsItemSelected(item);
                return true;
        }


    }
    //----------------------------popup menu------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--------passing intents from sharedref to tv of mainaciviy-----------------
        Intent intent = getIntent();
        String name=intent.getStringExtra("name");
        TextView tv = (TextView) findViewById(R.id.TVtextview);
        tv.setText(name);

        //-------------------------------------------------------------------

        Button outbtn= (Button) findViewById(R.id.outdoorbtn);
        registerForContextMenu(outbtn);

        Button btnindoor = (Button) findViewById(R.id.indoorbtn);
        btnindoor.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                // Initializing the popup menu and giving the reference as current context
                PopupMenu popup = new PopupMenu(MainActivity.this, btnindoor);

                // Inflating popup menu from popup_menu.xml file
                popup.getMenuInflater().inflate(R.menu.indoor_popup, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        Toast.makeText(MainActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                // Showing the popup menu
                @SuppressLint("RestrictedApi") MenuBuilder menuBuilder = new MenuBuilder(MainActivity.this);
                MenuInflater inflater = new MenuInflater(MainActivity.this);
                inflater.inflate(R.menu.indoor_popup, menuBuilder);

                @SuppressLint("RestrictedApi") MenuPopupHelper menuHelper = new MenuPopupHelper(MainActivity.this, menuBuilder, view);
                menuHelper.setForceShowIcon(true);
                menuHelper.show();
            }
        });
        //-------------------------------------------------------------------



        //------------------fragments-----------------------
        fragbtn1 = (Button) findViewById(R.id.frag1);
        fragbtn2 = (Button) findViewById(R.id.frag2);
        listener();

        FragmentTransaction f1 = getSupportFragmentManager().beginTransaction();
        fragmentone ff1 = new fragmentone();
        f1.replace(R.id.fragment_container, ff1);
        f1.commit();
    }

    private void listener() {
        fragbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction f1 = getSupportFragmentManager().beginTransaction();
                fragmentone ff1 = new fragmentone();
                f1.replace(R.id.fragment_container, ff1);
                f1.commit();
                Toast.makeText(MainActivity.this, "fragment1 selected", Toast.LENGTH_SHORT).show();
            }
        });

        fragbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction f1 = getSupportFragmentManager().beginTransaction();
                fragmenttwo ff1 = new fragmenttwo();
                f1.replace(R.id.fragment_container, ff1);
                f1.commit();
                Toast.makeText(MainActivity.this, "fragment2 selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //------------------------context menu-----------------------------------------------------
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
        {
            super.onCreateContextMenu(menu, v, menuInfo);
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);
            menu.setHeaderTitle("Select The Action");
        }

        @Override
        public boolean onContextItemSelected(MenuItem item){
            if(item.getItemId()==R.id.edit){
                Toast.makeText(getApplicationContext(),"Edit",Toast.LENGTH_LONG).show();
            }
            else if(item.getItemId()==R.id.delete){
                Toast.makeText(getApplicationContext(),"Delete",Toast.LENGTH_LONG).show();
            }
            else{
                return false;
            }
            return true;
        }




}