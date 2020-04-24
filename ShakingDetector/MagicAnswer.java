package com.example.shakingdetector;

import android.content.Context;

class MagicAnswer {

    private String[] allAnswers;

    public MagicAnswer(Context context) {
        //collect possible answers from strings.xml
        allAnswers = context.getResources().getStringArray(R.array.magic_answer_list);
    }

    public String getRandomAnswer() {
        int i = (int) Math.ceil(Math.random() * (allAnswers.length - 1));
        return allAnswers[i];
    }
}
