package m.kiyanse.animationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;

public class AnimatorActivity extends AppCompatActivity {
    public static final String VALUE_ANIMATOR="valueAnimator";
    public static final String OBJECT_ANIMATOR="objectAnimator";
    public static final String ANIMATOR_SET="animatorSet";
    public static final String CARD_FLIP_ANIMATION="cardFlipAnimation";
    AppCompatImageView imgTarget;
    RelativeLayout container;
    MaterialButton btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        setupViews();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choseAnimation(getIntent().getStringExtra("animator"));
            }
        });
    }

    private void setupViews() {
        container=findViewById(R.id.rlContainer);
        imgTarget=findViewById(R.id.imgTarget);
        btnStart=findViewById(R.id.btnStart);
    }

    private void choseAnimation(String animatorName) {
        switch (animatorName){
            case VALUE_ANIMATOR:
                startValueAnimator();
                break;
            case OBJECT_ANIMATOR:
                startObjectAnimator();
                break;
            case ANIMATOR_SET:
                startAnimatorSet();
                break;
        }
    }

    private void startValueAnimator(){
        final ValueAnimator animator=ValueAnimator.ofObject(new ArgbEvaluator(),ContextCompat.getColor(this,R.color.colorPrimary),ContextCompat.getColor(this,R.color.colorPrimaryDark),ContextCompat.getColor(this,R.color.colorAccent));
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                container.setBackgroundColor((int)animator.getAnimatedValue());
            }
        });
        animator.start();
    }

    private void startObjectAnimator() {
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(imgTarget,"scaleX",0f,1f);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    private void startAnimatorSet(){
        AnimatorSet animatorSet=new AnimatorSet();
        ObjectAnimator scaleXObjectAnimator=ObjectAnimator.ofFloat(imgTarget,"scaleX",0f,1f);
        scaleXObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleXObjectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        scaleXObjectAnimator.setDuration(1000);

        ObjectAnimator scaleYObjectAnimator=ObjectAnimator.ofFloat(imgTarget,"scaleY",0f,1f);
        scaleYObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleYObjectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        scaleYObjectAnimator.setDuration(1000);

        animatorSet.play(scaleXObjectAnimator).before(scaleYObjectAnimator);
        animatorSet.play(scaleYObjectAnimator).after(scaleXObjectAnimator);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                Log.i("animator", "onAnimationEnd: End");
            }
        });
    }

}
