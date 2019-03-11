package sinc.com.ssgmartapp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import sinc.com.ssgmartapp.adapter.SlideAdapter;

public class IntroActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private LinearLayout liner;
    private SlideAdapter myadapter;

    private TextView[] mdots;
    private Button next, btnSkip;

    private int mCureentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        viewpager = findViewById(R.id.viewpager);
        liner = findViewById(R.id.dots);

        next = findViewById(R.id.nextBtn);
        btnSkip = findViewById(R.id.backBtn);

        myadapter = new SlideAdapter(this);
        viewpager.setAdapter(myadapter);
        viewpager.setOffscreenPageLimit(3);
        adddots(0);

        viewpager.addOnPageChangeListener(viewlistener);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(next.getText().toString().equals("로그인")){
                    launchHomeScreen();
                }
                viewpager.setCurrentItem(mCureentPage + 1);
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchHomeScreen();
            }
        });
    }

    public void adddots(int i) {

        mdots = new TextView[3];
        liner.removeAllViews();

        for (int x = 0; x < mdots.length; x++) {

            mdots[x] = new TextView(this);
            mdots[x].setText(Html.fromHtml("&#8226;"));
            mdots[x].setTextSize(35);
            mdots[x].setTextColor(getResources().getColor(R.color.Description));

            liner.addView(mdots[x]);
        }
        if (mdots.length > 0) {

            mdots[i].setTextColor(getResources().getColor(R.color.welcome_down_background));

        }

    }

    ViewPager.OnPageChangeListener viewlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            adddots(position);
            mCureentPage = position;

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == mdots.length - 1) {
                // last page. make button text to GOT IT
                next.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                next.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void launchHomeScreen() {
        //주석 풀면 설치하고 한번만 실행됨.
        //prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(IntroActivity.this, LoginActivity.class));
        finish();
    }
}