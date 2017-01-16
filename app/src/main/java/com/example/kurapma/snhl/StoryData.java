package com.example.kurapma.snhl;

import java.util.ArrayList;

/**
 * Created by kurapma on 1/11/17.
 */

public class StoryData {

    public static ArrayList<StoryInformation> getData() {

        ArrayList<StoryInformation> data = new ArrayList<>();
        int[] images = {
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6,
                R.drawable.image7,
                R.drawable.image8,
                R.drawable.image9

        };

        String[] InformationAbout = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (int i = 0; i <= images.length - 1; i++) {
            StoryInformation current = new StoryInformation();
            current.title = InformationAbout[i];
            current.imageId = images[i];

            data.add(current);
        }
        return data;
    }
}

