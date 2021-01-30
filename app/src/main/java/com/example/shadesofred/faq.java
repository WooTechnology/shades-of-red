package com.example.shadesofred;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class faq extends AppCompatActivity {
    TextView heading , content , first , second , third , fourth , fifth;
    CardView cardView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("FAQ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_faq);
        heading = findViewById(R.id.heading);
        content = findViewById(R.id.content);
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);
        fifth = findViewById(R.id.fifth);
        cardView1 = findViewById(R.id.cv_1);

        content.setText("Did you know one unit of donated blood can save up to three lives? This is because your blood is separated into its " +
                "components (red blood cells, plasma, and platelet). Moreover, blood is needed on a regular basis for people suffering from blood disorders such as thalassemia and hemophilia," +
                " and also for the treatment of injuries after an accident, major surgeries, anemia, etc." +
                " \nWondering if you are eligible for donating blood? \nHere is what you need to know before you plan for blood donation.");


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void whyDonate(View view) {

        if(first.getVisibility() == view.INVISIBLE){
            first.setText("Blood donation not only saves lives but also has key benefits that we are unaware of.  It balances the level of iron in the body, regulates blood flow, reduces the risk of cardiovascular disease and stroke, triggers production of new blood cells and helps in weight loss. Moreover,  donating blood had a positive effect on a donor’s physical and psychological well-being as well.");
            first.setVisibility(View.VISIBLE);
        }
        else{
            first.setText(" ");
            first.setVisibility(View.INVISIBLE);
        }
    }

    public void eligible(View view) {
        if(second.getVisibility() == view.INVISIBLE){
            second.setText("Any healthy adult, both male and female, can donate blood. A healthy individual can safely donate one unit of blood, that is, 350 ml. Men can donate safely once in every three months while women can donate every four months. However, there are certain factors you need to fulfill to be considered as a donor. These include:\n" +
                    "\nWeight: The donor should not weigh less than 45 kgs\n \nPulse: The pulse rate of the donor should be normal (60 to 100 beats per minute)\n \nBody temperature: Normal body temperature is  98.6°F (37°C)\n" +
                    "\nHemoglobin: It should not be less than 12.5 grams per deciliter\n \nBlood pressure: Systolic and diastolic blood pressure should be within normal range (120/80 mm Hg) \n\nAge: Donors should be in the age group of 18 – 65 years");
            second.setVisibility(View.VISIBLE);
        }
        else{
            second.setText(" ");
            second.setVisibility(View.INVISIBLE);
        }
    }

    public void notEligible(View view) {
        if(third.getVisibility() == View.INVISIBLE){
            third.setText("You cannot donate blood if you: \n\n1. Suffer from common health problems such as cold, flu, sore throat, cold sore, stomach infection or any other infection." +
                    "\n\n2. Had any dental procedures done such as filling, cleaning or restoration the day before the donation." +
                    "\n\n3. Are diabetic and taking insulin injections to manage diabetes. \n\n4. Ever had an intravenous administration of drugs (even once)." +
                    "\n\n5. Have any tattoos or acupuncture done in the last 12 months or have have had tattoo removal surgery in the last six months." +
                    "\n\n6. Are pregnant, have delivered within a year or are breastfeeding.");
            third.setVisibility(View.VISIBLE);
        }

        else {
            third.setText(" ");
            third.setVisibility(View.INVISIBLE);
        }
    }

    public void after(View view) {
        if(fifth.getVisibility() == View.INVISIBLE){
            fifth.setText("1. Eat something that is rich in sugar post blood donation as it can up your energy levels.\n" +
                    "\n2. Increase your fluid intake for the next 24 to 48 hours.\n" +
                    "\n3. Avoid strenuous physical exertion. \n\n4. Eat well balanced meals for the next 24 hours to rejuvenate your body." +
                    "\n\n5. Do not smoke or consume alcohol for at least 24 hours after donation.");
            fifth.setVisibility(View.VISIBLE);
        }
        else{
            fifth.setText(" ");
            fifth.setVisibility(View.INVISIBLE);
        }
    }

    public void before(View view) {
        if(fourth.getVisibility() == View.INVISIBLE){
            fourth.setText("1. Eat something light before donating blood and hydrate yourself as it helps you to avoid weakness.\n" +
                    "\n2. Avoid drinking alcohol the day prior to blood donation.\n" +
                    "\n3. Do not smoke for at least 2 hours before blood donation.");
            fourth.setVisibility(View.VISIBLE);
        }
        else{
            fourth.setText(" ");
            fourth.setVisibility(View.INVISIBLE);
        }
    }
}