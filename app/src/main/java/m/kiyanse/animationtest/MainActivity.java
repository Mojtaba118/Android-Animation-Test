package m.kiyanse.animationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AppCompatImageView imageView;
    AnimationDrawable animDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.header);
        MaterialButton btnValueAnimator=findViewById(R.id.btnValueAnimator);
        btnValueAnimator.setOnClickListener(this);
        MaterialButton btnObjectAnimator=findViewById(R.id.btnObjectAnimator);
        btnObjectAnimator.setOnClickListener(this);
        MaterialButton btnAnimatorSet=findViewById(R.id.btnAnimatorSet);
        btnAnimatorSet.setOnClickListener(this);
        MaterialButton btnCardFlip=findViewById(R.id.btnCarFlipAnimation);
        btnCardFlip.setOnClickListener(this);
        /*btnValueAnimator.animate().translationX(100f).setDuration(500).start();
        btnObjectAnimator.animate().translationX(-100f).setDuration(600).start();
        btnAnimatorSet.animate().translationX(100f).setDuration(700).start();*/
        startAnimationDrawable();
        startTranslationXAnimator(btnValueAnimator,1000,-400f,100f);
        startTranslationXAnimator(btnObjectAnimator,1000,400f,-100f);
        startTranslationXAnimator(btnAnimatorSet,1000,-400f,100f);
        startTranslationXAnimator(btnCardFlip,1000,400f,-100f);

    }

    private void startAnimationDrawable() {
        imageView.setBackgroundResource(R.drawable.anim_drawable);
        animDrawable=(AnimationDrawable)imageView.getBackground();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animDrawable.start();
            }
        });
    }

    private void startTranslationXAnimator(View view, int duration, float fromValue, float toValue) {
        ObjectAnimator animator=ObjectAnimator.ofFloat(view,"translationX",fromValue,toValue);
        animator.setDuration(duration);
        animator.start();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getTag().toString()){
            case AnimatorActivity.CARD_FLIP_ANIMATION:
                intent=new Intent(MainActivity.this,CardFlipActivity.class);
                startActivity(intent);
                break;
            default:
               intent=new Intent(MainActivity.this,AnimatorActivity.class);
               intent.putExtra("animator",v.getTag().toString());
               startActivity(intent);
        }

    }
}
