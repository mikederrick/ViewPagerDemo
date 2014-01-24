package com.example.viewpagerdemo;

import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.transformers.ZoomOutPageTransformer;
import com.loopj.android.image.SmartImageView;

public class MainActivity extends ActionBarActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            String hexColor = "#1485CC";
            String imageUrl = "http://nadinechicken.files.wordpress.com/2012/01/android-background.jpg";
            if(position == 1) {
                hexColor = "#B21212";
                imageUrl = "http://4.bp.blogspot.com/-vnCM8oBbVtY/UA-hILRHIzI/AAAAAAAACCA/PeZlf6lHI-4/s1600/3.jpg";
            } else if (position == 2) {
                hexColor = "#FFFC19";
                imageUrl = "http://fc08.deviantart.net/fs70/f/2013/086/e/7/wallpaper_thundercats_for_smartphone_by_kristofbraekevelt-d5zgk0l.jpg";
            }
            return PlaceholderFragment.newInstance(position + 1, hexColor, imageUrl);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        private final String colorHex;

        private String imageUrl;

        public static PlaceholderFragment newInstance(int sectionNumber, String colorHex, String imageUrl) {
            PlaceholderFragment fragment = new PlaceholderFragment(colorHex, imageUrl);
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment(String colorHex, String imageUrl) {
            this.colorHex = colorHex;
            this.imageUrl = imageUrl;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.main_fragment, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            SmartImageView image = (SmartImageView) rootView.findViewById(R.id.image_view);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setImageUrl(imageUrl);
            return rootView;
        }
    }
}
