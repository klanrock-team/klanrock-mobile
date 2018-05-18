package id.ac.poljie.android.belajar.klanrockstudio.flipper;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import id.ac.poljie.android.belajar.klanrockstudio.R;

public class viewflipper extends AppCompatActivity implements View.OnClickListener {

    ViewFlipper v_flipper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipperview);

         v_flipper = (ViewFlipper) this.findViewById(R.id.v_flipper);
         v_flipper.setOnClickListener(this);

//        int images () = (R.drawable.pemandangan, R.drawable.pemandangan2 , R.drawable.pemandangan3);
//
//        v_flipper = (ViewFlipper) findViewById(R.id.v_flipper);
//
//        //berulang
//        /*for (int i= 0; i < image.length ; i++) {
//            flipperImage(image(i));
//        }*/
//
//        for (int image: images){
//            flipperImages (image);
//    }
//
//}
//
//    public void flipperImages(int image) {
//        ImageView imageView = new ImageView(  this);
//        imageView.setBackgroundResource(image);
//
//        v_flipper.addView(imageView);
//        v_flipper.setFlipInterval(4000);
//        v_flipper.setAutoStart(true);
//
//        //animation
//        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
//        v_flipper.setInAnimation(this, android.R.anim.slide_out_right);
//    }
    }

    @Override
    public void onClick(View view) {
        v_flipper.startFlipping();
        v_flipper.setFlipInterval(3000);
    }
}